package uy.edu.um.prog2.adt.Stack;

public interface MyStack<T> {

    void push(T value);

    T pop() throws EmptyStackException;

    T peek();

    boolean contains(T value);

    int size();
}
