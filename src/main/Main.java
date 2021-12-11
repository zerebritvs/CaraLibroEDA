/*Practica2 de EDA realizada por Juan Antonio Pages Lopez */
package src.main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        String fileName = "";
        String fileExtra = "";
        ArrayList<Amistad> red = new ArrayList<>();
        DisjointSet graph;
        HashMap<Integer, Integer> grusSelec;
        ArrayList<Amistad> relExtras = new ArrayList<>();

        Practica practica = new Practica();

        System.out.println("ANALISIS DE CARALIBRO\n---------------------");
        System.out.print("Fichero principal: ");

        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        
        red = practica.getRed(fileName);

        System.out.print("Fichero de nuevas conexiones (pulse enter si no existe): ");
        
        fileExtra = scanner.nextLine();
        if(fileExtra.length() != 0){
            red = practica.addNewRels(fileExtra, red);
        }
        
        System.out.println(practica.getNumUsers() + " usuarios, " + practica.getNumConexions() + " conexiones");
        System.out.print("Porcentaje tamaño mayor grumo: ");

        practica.setMayorGrumo(scanner.nextDouble());
        scanner.close();

        long init = System.currentTimeMillis();
        graph = practica.getGraph(red);
        
        grusSelec = practica.selecGrus(graph);
        System.out.println(grusSelec.toString());
        
        long fin = System.currentTimeMillis();
        double tiempo = (double)(fin - init) / 1000;  /*Tiempo total*/
        
        System.out.println("Tiempo total: " + tiempo + " seg.");


        System.out.println("Existen " + practica.getGrumos() + " grumos.");

        HashMap<Integer, Integer> sortedMap = practica.sortByValue( grusSelec, false); //true para ASC, false para DESC (orden)

        Integer[] values = sortedMap.values().toArray(new Integer[0]);

        Integer[] keys = sortedMap.keySet().toArray(new Integer[0]);
        
        
        if(grusSelec.size() < 2){

            System.out.println("El mayor grumo contiene " + values[0] + " usuarios (" + (values[0]/(double)practica.getNumUsers()) * 100 + "%)");
            System.out.println("No son necesarias nuevas relaciones de amistad");
        }else{

            System.out.println("Se deben unir los " + grusSelec.size() + " mayores");
            for(int i = 0; i < grusSelec.size(); i++){
                System.out.println("#" + (i + 1) + ": " + values[i] + " usuarios (" + (values[i] / (double)practica.getNumUsers()) * 100 + "%)");
                if(i != grusSelec.size() - 1){
                    relExtras.add(new Amistad(keys[i], keys[i+1]));
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
