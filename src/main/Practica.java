/*Practica1 de EDA realizada por Juan Antonio Pages Lopez */
package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase donde se almacenan los datos para realizar el analisis
 */
public class Practica {

    private int numUsers;
    private int numConexions;
    private double mayorGrumo;
    private int grumos;

    
    /**
     * Obtiene la red de amistades (filename debe estar en src/main/)
     * @param fileName
     * @return red
     */
    public ArrayList<Amistad> getRed(String fileName){

        ArrayList<Amistad> red = new ArrayList<>();

        File file;
        FileReader fr;
        BufferedReader br;

        try {
            file = new File("src/main/" + fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            int cont = 0;
            String linea;
            
            while ((linea=br.readLine())!=null) {

                if(cont == 0){
                    setNumUsers(Integer.parseInt(linea));                 /*Almaceno el numero de usuarios */
                }else if(cont == 1){
                    setNumConexions(Integer.parseInt(linea));           /*Almaceno el numero de conexiones de amistad */
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
     * @return usr
     */
    public ArrayList<Integer> getUsr(ArrayList<Amistad> red){

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
     * @return grumo
     */
    public ArrayList<Integer> uber_amigos(int usuario, ArrayList<Amistad> red, ArrayList<Integer> grumo){

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
     * @return grus
     */
    public ArrayList<ArrayList<Integer>> getGrus(ArrayList<Integer> usr, ArrayList<Amistad> red){
        ArrayList<ArrayList<Integer>> grus = new ArrayList<>();
        ArrayList<Integer> asig = new ArrayList<>();
        ArrayList<Integer> grumo = new ArrayList<>();

        for (int i = 0; i < usr.size(); i++) {
            
            if(!asig.contains(usr.get(i))){
                grumo = uber_amigos(usr.get(i), red, grumo);
                ArrayList<Integer> grumoCopy = new ArrayList<>(grumo);
                grus.add(grumoCopy);
                for (int j = 0; j < grumo.size(); j++) {
                    asig.add(grumo.get(j));
                }
                grumo.clear();
            }
        }
        setGrumos(grus.size());
        return grus;
    }

    /**
     * Ordena y selecciona que grumos deben de juntarse para satisfacer el porcentaje introducido por el usuario
     * @param grus
     * @return grusSelec
     */
    public ArrayList<ArrayList<Integer>> selecGrus(ArrayList<ArrayList<Integer>> grus){
        grus.sort(Comparator.comparing(ArrayList<Integer>::size).reversed());
        ArrayList<ArrayList<Integer>> grusSelec = new ArrayList<>();

        int sumaGrumos = grus.get(0).size();
        int cont = 0;

        while((sumaGrumos/(double)getNumUsers())*100 < getMayorGrumo()){
            sumaGrumos += grus.get(cont+1).size();
            cont++;
        }

        for(int i = 0; i <= cont; i++){
            grusSelec.add(grus.get(i));
        }   
        
        return grusSelec;
    }

    /**
     * Añade las nuevas relaciones de extra.txt a la red de amistades
     * @param fileExtra
     * @param red
     * @return red
     */
    public ArrayList<Amistad> addNewRels(String fileExtra, ArrayList<Amistad> red){

        File file;
        FileReader fr;
        BufferedReader br;

        try {
            file = new File(fileExtra);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String linea;
            
            while ((linea=br.readLine())!=null) {
                String[] newString = linea.split("\\s+");
                Amistad amistad = new Amistad(Integer.parseInt(newString[0]), Integer.parseInt(newString[1]));
                red.add(amistad);
            }
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return red;
    }

    /**
     * Obtiene el numero de usuarios
     * @return numUsers
     */
    public int getNumUsers() {
        return numUsers;
    }

    /**
     * Obtiene el numero de conexiones
     * @return numConexiones
     */
    public int getNumConexions() {
        return numConexions;
    }

    /**
     * Obtiene el valor del grumo mayor
     * @return mayorGrumo
     */
    public double getMayorGrumo() {
        return mayorGrumo;
    }

    /**
     * Obtiene el numero de grumos
     * @return grumos
     */
    public int getGrumos() {
        return grumos;
    }

    /**
     * Almacena el numero de usuarios
     * @param numUsers
     */
    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    /**
     * Almacena el numero de conexiones
     * @param numConexions
     */
    public void setNumConexions(int numConexions) {
        this.numConexions = numConexions;
    }

    /**
     * Almacena el porcentaje del mayor grumo
     * @param mayorGrumo
     */
    public void setMayorGrumo(double mayorGrumo) {
        this.mayorGrumo = mayorGrumo;
    }

    /**
     * Almacena el numero de grumos
     * @param grumos
     */
    public void setGrumos(int grumos) {
        this.grumos = grumos;
    }

}

