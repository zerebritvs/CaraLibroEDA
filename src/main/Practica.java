/*Practica2 de EDA realizada por Juan Antonio Pages Lopez */
package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


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
     * Obtiene el DisjointSet de la lista de Amistades
     * @param red
     * @return graph
     */
    public DisjointSet getGraph(ArrayList<Amistad> red){

        HashMap<Integer, Integer> usr = new HashMap<>();
        for(int i = 0; i < red.size(); i++){
            if(!usr.containsKey(red.get(i).getAmigo1())){
                usr.put(red.get(i).getAmigo1(), null);
            }
            if(!usr.containsKey(red.get(i).getAmigo2())){
                usr.put(red.get(i).getAmigo2(), null);
            }
        }

        DisjointSet graph = new DisjointSet(usr);
        for(int i = 0; i < red.size(); i++){
            graph.union(red.get(i).getAmigo1(), red.get(i).getAmigo2());
        }

        setGrumos(graph.getSetsSize());

        return graph;
    }

    /**
     * Obtiene un HashMap ordenado por valor
     * Si order es true ordena de manera ascendente, si no descendente
     * @param unsortMap
     * @param order
     * @return sortedHashMap
     */
    public HashMap<Integer, Integer> sortByValue(Map<Integer, Integer> unsortMap, boolean order)
    {
        List<Entry<Integer, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Ordena la lista por valor
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    /**
     * Ordena y selecciona que grumos deben de juntarse para satisfacer el porcentaje introducido por el usuario
     * @param grus
     * @return grusSelec
     */
    public HashMap<Integer, Integer> selecGrus(DisjointSet graph){
        
        HashMap<Integer, Integer> sortedMap = sortByValue(graph.getWeightMap(), false); //true para ASC, false para DESC (orden)
        HashMap<Integer, Integer> grusSelec = new HashMap<>();

        int sumaGrumos = 0;
        boolean primeraVez = true;
        
		int root;
        int size;

        for (Integer key : sortedMap.keySet()) {
            root = key;
            size = sortedMap.get(root);
            if(primeraVez){
                grusSelec.put(root, size);
                sumaGrumos = size;
                primeraVez=false;
            }else{
                if((sumaGrumos/(double)getNumUsers())*100 < getMayorGrumo()){
                    sumaGrumos += size;
                    grusSelec.put(root, size);
                    
                }
            } 
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

