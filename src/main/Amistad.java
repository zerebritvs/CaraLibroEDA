package src.main;


/* Obtiene conexiones de amistad entre pares de amigos*/
public class Amistad {
    private int amigo1;
    private int amigo2;

    /**
     * Constructor de Amistad
     * @param amigo1
     * @param amigo2
     */
    public Amistad(int amigo1, int amigo2){
        this.amigo1 = amigo1;
        this.amigo2 = amigo2;
    }

    /**
     * Obtiene el primer amigo del par
     * @return int
     */
    public int getAmigo1(){
        return amigo1;
    }

    /**
     * Obtiene el segundo amigo del par
     * @return int
     */
    public int getAmigo2(){
        return amigo2;
    }

    /**
     * Almacena el primer amigo del par
     * @param amigo1
     */
    public void setAmigo1(int amigo1){
        this.amigo1= amigo1;
    }

    /**
     * Almacena el segundo amigo del par
     * @param amigo2
     */
    public void setAmigo2(int amigo2){
        this.amigo2= amigo2;
    }
}
