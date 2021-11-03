package src.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase donde se almacenan los datos para realizar el analisis
 */
public class Practica {
    private String ficheroPrincipal;
    private double lecturaFichero;
    private int numUsers;
    private int numConexions;
    private double mayorGrumo;
    private double tiempoListaUsers;
    private double tiempoListaGrumos;
    private int grumos;
    private double tiempoOrdenarGrumos;
    
    /**
     * Obtiene la red de amistades
     * @param fileName
     * @return ArrayList<Amistad>
     */
    public ArrayList<Amistad> getRed(String fileName){

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
     * @return ArrayList<Integer>
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
     * @return ArrayList<Integer>
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
     * @return ArrayList<ArrayList<Integer>>
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
     * Ordena mediante el metodo de inserccion clasico de mayor a menor la lista de grumos
     * @param grus
     * @return ArrayList<ArrayList<Integer>>
     */
    public ArrayList<ArrayList<Integer>> inserGrus(ArrayList<ArrayList<Integer>> grus){

        ArrayList<ArrayList<Integer>> grusCopy = new ArrayList<>(grus);

        for(int i = 1; i < grusCopy.size(); i++){
            ArrayList<Integer> grumoTmp = grusCopy.get(i);
            int j = i-1;
            while(j > -1 && grusCopy.get(j).size() < grumoTmp.size()){
                grusCopy.set(j+1, grusCopy.get(j));
                j--;
            }
            grusCopy.set(j+1, grumoTmp);
        }

        return grusCopy;
    }

    /**
     * Seleccione que grumos deben de juntarse para satisfacer el porcentaje introducido por el usuario
     * @param sortGrus
     * @return ArrayList<ArrayList<Integer>>
     */
    public ArrayList<ArrayList<Integer>> selecGrus(ArrayList<ArrayList<Integer>> sortGrus){

        ArrayList<ArrayList<Integer>> grusSelec = new ArrayList<>();

            int sumaGrumos = sortGrus.get(0).size();
            int cont = 0;

            while((sumaGrumos/(double)getNumUsers())*100 < getMayorGrumo()){
                sumaGrumos += sortGrus.get(cont+1).size();
                cont++;
            }

            for(int i = 0; i <= cont; i++){
                grusSelec.add(sortGrus.get(i));
            }   
        
        return grusSelec;
    }

    /**
     * Añade las nuevas relaciones de extra.txt a la red de amistades
     * @param fileExtra
     * @param red
     * @return ArrayList<Amistad>
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
                Amistad amistad = new Amistad(Integer.parseInt(newString[0]), Integer.parseInt(newString[1]));      /* creo un nuevo objeto Amistad*/
                red.add(amistad);           /* Almaceno un nuevo objeto Amistad en la red*/
            }
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return red;
    }


    /**
     * Obtiene el nombre del fichero principal
     * @return String
     */
    public String getFicheroPrincipal() {
        return ficheroPrincipal;
    }

    /**
     * Obtiene el tiempo de lectura del fichero principal
     * @return double
     */
    public double getLecturaFichero() {
        return lecturaFichero;
    }

    /**
     * Obtiene el numero de usuarios
     * @return int
     */
    public int getNumUsers() {
        return numUsers;
    }

    /**
     * Obtiene el numero de conexiones
     * @return int
     */
    public int getNumConexions() {
        return numConexions;
    }

    /**
     * Obtiene el valor del grumo mayor
     * @return double
     */
    public double getMayorGrumo() {
        return mayorGrumo;
    }

    /**
     * Obtiene el tiempo en sacar la lista de usuarios
     * @return double
     */
    public double getTiempoListaUsers() {
        return tiempoListaUsers;
    }

    /**
     * Obtiene el tiempo en sacar la lista de grumos
     * @return double
     */
    public double getTiempoListaGrumos() {
        return tiempoListaGrumos;
    }

    /**
     * Obtiene el numero de grumos
     * @return int
     */
    public int getGrumos() {
        return grumos;
    }

    /**
     * Obtiene el tiempo en ordenar los grumos
     * @return double
     */
    public double getTiempoOrdenarGrumos() {
        return tiempoOrdenarGrumos;
    }

    /**
     * Almacena el nombre del fichero principal
     * @param ficheroPrincipal
     */
    public void setFicheroPrincipal(String ficheroPrincipal) {
        this.ficheroPrincipal = ficheroPrincipal;
    }

    /**
     * Almacena el tiempo en ejecutar la lectura del fichero
     * @param lecturaFichero
     */
    public void setLecturaFichero(double lecturaFichero) {
        this.lecturaFichero = lecturaFichero;
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
     * Almacena el tiempo en obtener la lista de usuarios
     * @param tiempoListaUsers
     */
    public void setTiempoListaUsers(double tiempoListaUsers) {
        this.tiempoListaUsers = tiempoListaUsers;
    }

    /**
     * Almacena el tiempo en obtener la lista de grumos
     * @param tiempoListaGrumos
     */
    public void setTiempoListaGrumos(double tiempoListaGrumos) {
        this.tiempoListaGrumos = tiempoListaGrumos;
    }

    /**
     * Almacena el numero de grumos
     * @param grumos
     */
    public void setGrumos(int grumos) {
        this.grumos = grumos;
    }

    /**
     * Almacena el tiempo en ordenar los grumos
     * @param tiempoOrdenarGrumos
     */
    public void setTiempoOrdenarGrumos(double tiempoOrdenarGrumos) {
        this.tiempoOrdenarGrumos = tiempoOrdenarGrumos;
    }

}

