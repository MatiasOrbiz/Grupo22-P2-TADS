package uy.edu.um.prog2.adt.LinkedList;

import java.util.Comparator;

public interface MyList<T> {
    void sort();
    void add(T value);
    T get(int position);
    boolean contains(T value);
    void remove(T value);
    int size();
    void sort(Comparator<T> comparator);

}
