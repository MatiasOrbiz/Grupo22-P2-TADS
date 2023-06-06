package uy.edu.um.prog2.adt.Heap;

public interface MyHeap<T extends Comparable<T>>{

    void insert(T value);

    T deleteAndReturn();

    int size();
}
