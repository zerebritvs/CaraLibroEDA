/*Practica1 de EDA realizada por Juan Antonio Pages Lopez */

package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        String fileName = "";
        ArrayList<Amistad> red = new ArrayList<>();


        System.out.println("ANALISIS DE CARALIBRO\n");
        System.out.println("---------------------\n");
        System.out.println("Fichero principal: ");

        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        
        red = getRed(fileName);
    }


    /**
     * Obtiene la red de amistades
     * @param fileName
     * @return ArrayList<Amistad>
     */
    public ArrayList<Amistad> getRed(String fileName){
        ArrayList<Amistad> red = new ArrayList<>();

        File file = null;
        FileReader fr = null;
        BufferedReader br = null;

        int numUsers = 0;
        int numConexions = 0;

        try {
            file = new File("test/" + fileName);
            br = new BufferedReader(fr);

            int cont = 0;
            String linea;
            while ((linea=br.readLine())!=null) {

                if(cont == 0){
                    numUsers = Integer.parseInt(linea);                 /*Almaceno el numero de usuarios */
                }else if(cont == 1){
                    numConexions = Integer.parseInt(linea);             /*Almaceno el numero de conexiones de amistad */
                }else{
                    
                }
                cont++;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally{                                                       /*En finally nos aseguramos cerrar el fichero */
            try{
                if(null != fr){
                    fr.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
            
        }
        return red;
    }
}