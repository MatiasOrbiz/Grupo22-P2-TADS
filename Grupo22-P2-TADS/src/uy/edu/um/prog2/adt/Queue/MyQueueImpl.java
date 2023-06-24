package uy.edu.um.prog2.adt.Queue;


import uy.edu.um.prog2.adt.LinkedList.Node;
import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.Queue.EmptyQueueException;


public class MyQueueImpl<T> implements MyQueue<T> {
    private Node<T> first;
    private Node<T> last;

    public MyQueueImpl() {
        this.first = null;
        this.last = null;
    }


    public void enqueue(T value) {
        Node nuevoNodo = new Node(value);
        if (first==null){
            first=nuevoNodo;
            last=nuevoNodo;
            nuevoNodo.next=null;
        }
        else {
            last.next=nuevoNodo;
            last=nuevoNodo;
            nuevoNodo.next=null;
        }
    }

    public T dequeue() throws EmptyQueueException {
        if (this.last == null) {
            throw new EmptyQueueException();
        }
        return removeLast();
    }



    public void remove(T value) {
        Node<T> Temp = this.first;
        if (Temp != null) {
            while (Temp != null && !Temp.getValue().equals(value)) {
                Temp = Temp.getNext();
            }

            if (Temp != null) {
                if (Temp.getNext() == null && Temp.getPrev() == null) {
                    this.last = null;
                    this.first = null;
                } else if (Temp.getNext() == null) {
                    Temp.getPrev().setNext(null);
                    this.last = Temp.getPrev();
                    Temp.setPrev(null);
                } else if (Temp.getPrev() == null) {
                    Temp.getNext().setPrev(null);
                    this.first = Temp.getNext();
                    Temp.setNext(null);
                } else {
                    Temp.getNext().setPrev(Temp.getPrev());
                    Temp.getPrev().setNext(Temp.getNext());
                }
            }
        }
    }


    private T removeLast() {
        T valueToRemove = null;
        if (this.last != null) {
            valueToRemove = this.last.getValue();
            remove(valueToRemove);
        }
        return valueToRemove;
    }



    public int size() {
        int size = 0;
        Node<T> temp = this.first;
        while (temp != null) {
            temp = temp.getNext();
            size++;
        }
        return size;
    }
}
