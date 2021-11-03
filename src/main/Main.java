/*Practica1 de EDA realizada por Juan Antonio Pages Lopez */
package src.main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        String fileName = "";
        String fileExtra = "";
        ArrayList<Amistad> red = new ArrayList<>();
        ArrayList<Integer> usr = new ArrayList<>();
        ArrayList<ArrayList<Integer>> grus = new ArrayList<>();
        ArrayList<ArrayList<Integer>> sortGrus = new ArrayList<>();
        ArrayList<ArrayList<Integer>> grusSelec = new ArrayList<>();
        ArrayList<Amistad> relExtras = new ArrayList<>();
        Practica practica = new Practica();

        System.out.println("ANALISIS DE CARALIBRO\n---------------------");
        System.out.print("Fichero principal: ");

        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();

        long init = System.currentTimeMillis();
        red = practica.getRed(fileName);
        long fin = System.currentTimeMillis();
        double tiempo = (double)(fin - init) / 1000;  /*tiempo en obtener lista de red del fichero */

        System.out.println("Lectura fichero: " + tiempo + " seg.");
        System.out.print("Fichero de nuevas conexiones (pulse enter si no existe): ");

        fileExtra = scanner.nextLine();
        if(fileExtra.length() != 0){
            red = practica.addNewRels(fileExtra, red);
        }
        
        System.out.println(practica.getNumUsers() + " usuarios, " + practica.getNumConexions() + " conexiones");
        System.out.print("Porcentaje tamaño mayor grumo: ");

        practica.setMayorGrumo(scanner.nextDouble());

        scanner.close();

        init = System.currentTimeMillis();
        usr = practica.getUsr(red);
        fin = System.currentTimeMillis();
        tiempo = (double)((fin - init) / 1000);  /*tiempo en obtener lista de usuarios */
        
        System.out.println("Creación lista usuarios: " + tiempo + " seg.");

        init = System.currentTimeMillis();
        grus = practica.getGrus(usr, red);
        fin = System.currentTimeMillis();
        tiempo = (double)(fin - init) / 1000; /*tiempo en obtener lista de grumos */
        
        System.out.println("Creación lista grumos: " + tiempo + " seg.");
        
        init = System.currentTimeMillis();
        sortGrus = practica.inserGrus(grus);
        grusSelec = practica.selecGrus(sortGrus);
        fin = System.currentTimeMillis();
        tiempo = (double)(fin - init) / 1000; /*tiempo en ordenar y selecionar grumos */

        System.out.println("Ordenación y selección de grumos: " + tiempo + " seg.");
        System.out.println("Existen " + practica.getGrumos() + " grumos.");

        if(grusSelec.size() < 2){

            System.out.println("El mayor grumo contiene " + grusSelec.get(0).size() + " usuarios (" + (grusSelec.get(0).size()/(double)practica.getNumUsers()) * 100 + "%)");
            System.out.println("No son necesarias nuevas relaciones de amistad");
        }else{

            System.out.println("Se deben unir los " + grusSelec.size() + " mayores");
            for(int i = 0; i < grusSelec.size(); i++){
                System.out.println("#" + (i + 1) + ": " + grusSelec.get(i).size() + " usuarios (" + (grusSelec.get(i).size() / (double)practica.getNumUsers()) * 100 + "%)");
                if(i != grusSelec.size() - 1){
                    relExtras.add(new Amistad(grusSelec.get(i).get(0), grusSelec.get(i+1).get(0)));
                }
            } 
            System.out.println("Nuevas relaciones de amistad (salvadas en extra.txt)");
            for(int i = 0; i < grusSelec.size()-1; i++){
                System.out.println(relExtras.get(i).getAmigo1() + " <-> " + relExtras.get(i).getAmigo2());
            }

            FileWriter fichero;

            try{
            fichero = new FileWriter("extra.txt");
            
            for (int i = 0; i < relExtras.size(); i++){
                fichero.write(relExtras.get(i).getAmigo1() + " " + relExtras.get(i).getAmigo2() + "\n");
            }
            fichero.close();
                
            } catch (Exception e) {
            e.printStackTrace();
            }
        }

    }

}
