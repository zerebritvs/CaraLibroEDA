/*Practica1 de EDA realizada por Juan Antonio Pages Lopez */

package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Resultados resultados;
    public static void main(String[] args) {
        
        String fileName = "";
        String fileNameExtra = "";
        ArrayList<Amistad> red = new ArrayList<>();
        ArrayList<Integer> usr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> grus = new ArrayList<>();
        ArrayList<ArrayList<Integer>> sortGrus = new ArrayList<>();
        resultados = new Resultados();
        


        System.out.println("ANALISIS DE CARALIBRO\n---------------------");
        System.out.print("Fichero principal: ");

        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();

        long init = System.currentTimeMillis();
        red = getRed(fileName);
        long fin = System.currentTimeMillis();
        resultados.setLecturaFichero((double)(fin - init)/1000);

        System.out.println("Lectura fichero: " + resultados.getLecturaFichero() + " seg.");
        System.out.print("Fichero de nuevas conexiones (pulse enter si no existe): ");

        fileNameExtra = scanner.nextLine();
        
        System.out.println(resultados.getNumUsers() + " usuarios, " + resultados.getNumConexions() + " conexiones");
        System.out.print("Porcentaje tamaño mayor grumo: ");

        resultados.setMayorGrumo(scanner.nextDouble());

        scanner.close();

        init = System.currentTimeMillis();
        usr = getUsr(red);
        fin = System.currentTimeMillis();
        resultados.setTiempoListaUsers((double)(fin - init)/1000);
        System.out.println("Creación lista usuarios: " + resultados.getTiempoListaUsers() + " seg.");

        init = System.currentTimeMillis();
        grus = getGrus(usr, red);
        fin = System.currentTimeMillis();
        resultados.setTiempoListaGrumos((double)(fin - init)/1000);
        System.out.println("Creación lista grumos: " + resultados.getTiempoListaGrumos() + " seg.");

        init = System.currentTimeMillis();
        sortGrus = sortGrus(grus);
        fin = System.currentTimeMillis();
        resultados.setTiempoOrdenarGrumos((double)(fin - init)/1000);
        System.out.println("Ordenación y selección de grumos: " + resultados.getTiempoOrdenarGrumos() + " seg.");
        
        System.out.println("Existen " + resultados.getGrumos() + " grumos.");

        
        

        

    }

    /**
     * Obtiene la red de amistades
     * @param fileName
     * @return ArrayList<Amistad>
     */
    public static ArrayList<Amistad> getRed(String fileName){

        ArrayList<Amistad> red = new ArrayList<>();

        File file;
        FileReader fr;
        BufferedReader br;

        try {
            file = new File("test/" + fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            int cont = 0;
            String linea;
            
            while ((linea=br.readLine())!=null) {

                if(cont == 0){
                    resultados.setNumUsers(Integer.parseInt(linea));                 /*Almaceno el numero de usuarios */
                }else if(cont == 1){
                    resultados.setNumConexions(Integer.parseInt(linea));           /*Almaceno el numero de conexiones de amistad */
                }else{
                    String[] newString = linea.split("\\s+");
                    Amistad amistad = new Amistad(Integer.parseInt(newString[0]), Integer.parseInt(newString[1]));      /* creo un nuevo objeto Amistad*/
                    red.add(amistad);           /* Almaceno un nuevo objeto Amistad en la red*/
                }
                cont++;
            }
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return red;
    }

    /**
     * Obtiene la lista de usuarios
     * @param red
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> getUsr(ArrayList<Amistad> red){

        ArrayList<Integer> usr = new ArrayList<>();

        for(int i = 0; i < red.size(); i++){
            if(!usr.contains(red.get(i).getAmigo1())){
                usr.add(red.get(i).getAmigo1());
            }
            if (!usr.contains(red.get(i).getAmigo2())) {
                usr.add(red.get(i).getAmigo2());
            }
        }

        return usr;
    }

    /**
     * Obtiene todos los usuarios de un grumo de manera recursiva
     * @param usuario
     * @param red
     * @param grumo
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> uber_amigos(int usuario, ArrayList<Amistad> red, ArrayList<Integer> grumo){
        for (int i = 0; i < red.size(); i++) {
            if(red.get(i).getAmigo1() == usuario){
                if(!grumo.contains(red.get(i).getAmigo2())){
                    grumo.add(red.get(i).getAmigo2());
                    uber_amigos(red.get(i).getAmigo2(), red, grumo);
                }
            }else if(red.get(i).getAmigo2() == usuario){
                if(!grumo.contains(red.get(i).getAmigo1())){
                    grumo.add(red.get(i).getAmigo1());
                    uber_amigos(red.get(i).getAmigo1(), red, grumo);
                }
            }
        }
        return grumo;
    }

    /**
     * Obtiene la lista de grumos
     * @param usr
     * @param red
     * @return ArrayList<ArrayList<Integer>>
     */
    public static ArrayList<ArrayList<Integer>> getGrus(ArrayList<Integer> usr, ArrayList<Amistad> red){
        ArrayList<ArrayList<Integer>> grus = new ArrayList<>();
        ArrayList<Integer> asig = new ArrayList<>();
        ArrayList<Integer> grumo = new ArrayList<>();
        ArrayList<Integer> newGrumo = new ArrayList<>();

        for (int i = 0; i < usr.size(); i++) {
            
            if(!asig.contains(usr.get(i))){
                newGrumo = uber_amigos(usr.get(i), red, grumo);
                ArrayList<Integer> newGrumoCopy = new ArrayList<>(newGrumo);
                grus.add(newGrumoCopy);
                for (int j = 0; j < newGrumo.size(); j++) {
                    asig.add(newGrumo.get(j));
                }
                newGrumo.clear();
            }
        }
        resultados.setGrumos(grus.size());
        return grus;
    }

    /**
     * Obtiene la lista de grumos ordenada de mayor a menor tamaño
     * @param grus
     * @return ArrayList<ArrayList<Integer>>
     */
    public static ArrayList<ArrayList<Integer>> sortGrus(ArrayList<ArrayList<Integer>> grus){

        ArrayList<ArrayList<Integer>> newGrus = new ArrayList<>();
        ArrayList<ArrayList<Integer>> grusCopy = new ArrayList<>(grus);

        while(grusCopy.size() != 0){
            
            newGrus.add(maxGru(grusCopy));
            grusCopy.remove(maxGru(grusCopy));

            if(grusCopy.size() == 2){
                if(grusCopy.get(0).size() >= grusCopy.get(1).size()){
                    newGrus.add(grusCopy.get(0));
                    newGrus.add(grusCopy.get(1));
                    grusCopy.clear();  
                }else{
                    newGrus.add(grusCopy.get(1));
                    newGrus.add(grusCopy.get(0));
                    grusCopy.clear();  
                }  
            }  
        } 
        return newGrus;
    }

    /**
     * Obtiene el grumo con mayor numero de usuarios
     * @param grus
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> maxGru(ArrayList<ArrayList<Integer>> grus){

        ArrayList<Integer> grumoMax = new ArrayList<>();

        grumoMax = grus.get(0);

        for(int i = 1; i < grus.size(); i++){
            if(grus.get(i).size() > grumoMax.size()){
                grumoMax.clear();
                grumoMax = grus.get(i);
            }
        }
        return grumoMax;
    }

    
}
