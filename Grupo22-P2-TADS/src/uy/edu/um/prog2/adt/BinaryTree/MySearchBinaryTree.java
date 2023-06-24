package uy.edu.um.prog2.adt.BinaryTree;

public interface MySearchBinaryTree<K extends Comparable<K>, V> {

    void add(K key, V value);

    void remove(K key);

    V find(K key);
}
