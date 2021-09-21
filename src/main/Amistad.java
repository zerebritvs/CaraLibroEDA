package src.main;


/* Obtiene conexiones de amistad entre pares*/
public class Amistad {
    private int amigo1;
    private int amigo2;

    public Amistad(int amigo1, int amigo2){
        this.amigo1 = amigo1;
        this.amigo2 = amigo2;
    }

    public int getAmigo1(){
        return amigo1;
    }

    public int getAmigo2(){
        return amigo2;
    }

    public void setAmigo1(int amigo1){
        this.amigo1= amigo1;
    }

    public void setAmigo2(int amigo2){
        this.amigo2= amigo2;
    }
}
