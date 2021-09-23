package src.main;

/**
 * Clase donde se almacenan los datos para realizar el analisis
 */
public class Resultados {
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


    public void print(){

        System.out.println("ANALISIS DE CARALIBRO\n");
        System.out.println("---------------------\n");
        System.out.println("Fichero principal: "+getFicheroPrincipal() + " seg.\n");
        System.out.println("Lectura fichero: " + getLecturaFichero() + "\n");
        System.out.println("Fichero de nuevas conexiones (pulse enter si no existe): "+"\n");
        System.out.println(getNumUsers() + "usuarios, " + getNumConexions() + "conexiones\n");
        System.out.println("Porcentaje tamaño mayor grumo: " + getMayorGrumo() + "\n");
        System.out.println("Creación lista usuarios: " + getTiempoListaUsers() + " seg.\n");
        System.out.println("Creación lista grumos: " + getTiempoListaGrumos() + " seg.\n");
        System.out.println("Ordenación y selección de grumos: \n");
        System.out.println("Existen " + getGrumos() + " grumos.");
        
    }


}

