package uy.edu.um.prog2.adt.Stack;

import uy.edu.um.prog2.adt.LinkedList.Node;
import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.Stack.EmptyStackException;

public class MyStackImpl<T> implements MyStack<T> {
    private Node<T> first;
    private Node<T> last;

    public MyStackImpl() {
        this.first = null;
        this.last = null;
    }


    public void push(T value) {
        add(value);
    }

    public void add(T value) {
        if (value != null) {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null) {
                this.first = elementToAdd;
                this.last = elementToAdd;
            } else {
                elementToAdd.setPrev(this.last);
                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }
        }
    }

    public void remove(T value) {
        Node<T> Temp = this.first;
        while (Temp.getNext() != null && Temp.getValue() != value) {
            Temp = Temp.getNext();
        }
        if (Temp.getValue() == value && Temp.getNext() == null) {
            Temp = Temp.getPrev();
            Temp.setNext(null);
        } else if (Temp.getValue() == value) {
            Temp = Temp.getPrev();
            Temp.setNext(Temp.getNext().getNext());
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

    public T pop()   throws EmptyStackException {
        if (this.last == null) {
            throw new EmptyStackException();
        }
        return removeLast();
    }

    public T peek() {
        T valueToReturn = null;
        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }
        return valueToReturn;
    }

    public boolean contains(T value) {
        boolean contains = false;
        Node<T> tmp = this.first;
        while (tmp != null && !tmp.getValue().equals(value)) {
            tmp = tmp.getNext();
        }
        if (tmp != null) {
            contains = true;
        }
        return contains;
    }

    public int size() {
        int size = 0;
        Node<T> tmp = this.first;
        while (tmp != null) {
            tmp = tmp.getNext();
            size++;
        }
        return size;
    }

}
