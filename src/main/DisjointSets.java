/*Practica2 de EDA realizada por Juan Antonio Pages Lopez */



public class DisjointSets {
    int[] rank;
    int[] parent;
    int n;

    public DisjointSets(int n){
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    public void makeSet(){

        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public union(int x , int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY){
            re
        }

    }


}

