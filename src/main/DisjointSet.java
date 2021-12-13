/*Practica2 de EDA realizada por Juan Antonio Pages Lopez */

package src.main;

import java.util.HashMap;
import java.util.Map;
/**
 * Clase que representa el TAD DisjointSet
 */
public class DisjointSet {

    /* Contiene cada elemento y una referencia a su padre en el árbol */
    private Map<Integer, Integer> map;

    /* Contiene el tamaño del árbol con raiz por un elemento */
    private Map<Integer, Integer> weightMap;

    /* Mantiene un seguimiento del número de conjuntos */
    private int setsSize;

    /**
     * Constructor de un DisjointSet relleno de elementos de un ArrayList. 
     * Cada elemento en un counjunto separado
     * @param ArrayList<Integer> items
     */
    public DisjointSet(HashMap<Integer, Integer> items) {
        map = new HashMap<>();
        weightMap = new HashMap<>();
        for (Integer key : items.keySet()) {

            map.put(key, key);
            weightMap.put(key, 1);
        }
        setsSize = items.size();
    }

    /**
     * Fusiona en un conjunto el primer elemento y el conjunto que contiene el segundo elemento.
     * Los conjuntos originales se borran
     * 
     * @param item1
     * @param item2
     */
    public void union(Integer item1, Integer item2) {
        
        Integer rootItem1 = find(item1);
        Integer rootItem2 = find(item2);
        if (rootItem1 != rootItem2) {
            int weightRootItem1 = weightMap.get(rootItem1);
            int weightRootItem2 = weightMap.get(rootItem2);
            if (weightRootItem1 >= weightRootItem2) {
                map.put(rootItem2, rootItem1);
                weightMap.put(rootItem1, weightRootItem1 + weightRootItem2);
            } else {
                map.put(rootItem1, rootItem2);
                weightMap.put(rootItem2, weightRootItem1 + weightRootItem2);
            }
            setsSize--;
        }
    }

    /**
     * Obtiene el elemento representante o raíz del conjunto donde esta el item
     * 
     * @param item
     * @return root       
     */
    public Integer find(Integer item) {

        Integer root = item;
        while (!root.equals(map.get(root)))
            root = map.get(root);
        return root;
    }

    /**
     * Obtiene el Map de padres de DisjointSet
     * @return map
     */
    public Map<Integer, Integer> getMap() {
        return map;
    }

    /**
     * Obtiene el numero de conjuntos del DisjointSet
     * @return setsSize
     */
    public int getSetsSize() {
        return setsSize;
    }

    /**
     * Obtiene el Map de pesos del DisjointSet
     * @return weigthMap
     */
    public Map<Integer, Integer> getWeightMap() {
        return weightMap;
    }

}