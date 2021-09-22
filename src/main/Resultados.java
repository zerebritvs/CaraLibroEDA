package src.main;

public class Resultados {
    private String ficheroPrincipal;
    private double lecturaFichero;
    private int numUsers;
    private int numConexions;
    private double mayorGrumo;
    private double tiempoListaUsers;
    private double tiempoListaGrumos;
    private int grumos;
    


    public String getFicheroPrincipal() {
        return ficheroPrincipal;
    }

    public double getLecturaFichero() {
        return lecturaFichero;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public int getNumConexions() {
        return numConexions;
    }

    public double getMayorGrumo() {
        return mayorGrumo;
    }

    public double getTiempoListaUsers() {
        return tiempoListaUsers;
    }

    public double getTiempoListaGrumos() {
        return tiempoListaGrumos;
    }

    public int getGrumos() {
        return grumos;
    }


    public void setFicheroPrincipal(String ficheroPrincipal) {
        this.ficheroPrincipal = ficheroPrincipal;
    }

    public void setLecturaFichero(double lecturaFichero) {
        this.lecturaFichero = lecturaFichero;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public void setNumConexions(int numConexions) {
        this.numConexions = numConexions;
    }

    public void setMayorGrumo(double mayorGrumo) {
        this.mayorGrumo = mayorGrumo;
    }

    public void setTiempoListaUsers(double tiempoListaUsers) {
        this.tiempoListaUsers = tiempoListaUsers;
    }

    public void setTiempoListaGrumos(double tiempoListaGrumos) {
        this.tiempoListaGrumos = tiempoListaGrumos;
    }

    public void setGrumos(int grumos) {
        this.grumos = grumos;
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

