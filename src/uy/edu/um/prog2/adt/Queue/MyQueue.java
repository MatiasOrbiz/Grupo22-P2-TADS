package uy.edu.um.prog2.adt.Queue;


public interface MyQueue<T> {

    void enqueue(T value);

    T dequeue() throws EmptyQueueException;



    int size();
}
