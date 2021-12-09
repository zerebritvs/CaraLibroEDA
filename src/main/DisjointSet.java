
package src.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    /* Contiene cada elemento y una referencia a su padre en el árbol */
    private Map<Integer, Integer> map;

    /* Contiene el tamaño del árbol con raiz por un elemento */
    private Map<Integer, Integer> weightMap;

    /* Mantiene un seguimiento del número de conjuntos */
    private int setsSize;

    /**
     * Constructor de un DisjointSet vacío
     */
    public DisjointSet() {
        map = new HashMap<>();
        weightMap = new HashMap<>();
        setsSize = 0;
    }

    /**
     * Constructor de un DisjointSet relleno de elementos de un ArrayList. 
     * Cada elemento en un counjunto separado
     * @param ArrayList<Integer> items
     */
    public DisjointSet(ArrayList<Integer> items) {
        map = new HashMap<>();
        weightMap = new HashMap<>();
        for (Integer item : items) {

            map.put(item, item);
            weightMap.put(item, 1);
        }
        setsSize = items.size();
    }

    /**
     * Crea un nuevo conjunto de un elemento(item).
     * 
     * @param item
     * @return true si el conjunto se creo correctamente, si no false
     */
    public boolean makeSet(Integer item) {
        if (contains(item))
            return false;
        map.put(item, item);
        weightMap.put(item, 1);
        setsSize++;
        return true;
    }

    /**
     * Returns true if this DisjointSet contains no set.
     *
     * @return true if this DisjointSet contains no set
     */
    public boolean isEmpty() {
        if (map.isEmpty())
            return true;
        return false;
    }

    /**
     * Returns the number of items in this DisjointSet.
     *
     * @return size the number of items in this DisjointSet.
     */
    public int itemsSize() {
        return map.size();
    }

    /**
     * Returns the number of sets in this DisjointSet.
     *
     * @return size the number of sets in this DisjointSet.
     */
    public int itemsSetSize() {
        return setsSize;
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
     * Obtiene el elemento representante o raíz del conjunto donde esta el item.
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
     * Returns true if this DisjointSet contains the specified item.
     *
     * @param item
     *            the item to look for.
     *
     * @return true if the DisjointSet contains the item and false if not.
     * 
     * @exception java.lang.NullPointerException
     *                if the given items if null.
     */
    public boolean contains(Integer item) {
        if (item == null)
            throw new NullPointerException("The given item is null.");
        return map.containsKey(item);
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public int getSetsSize() {
        return setsSize;
    }

    public Map<Integer, Integer> getWeightMap() {
        return weightMap;
    }

}