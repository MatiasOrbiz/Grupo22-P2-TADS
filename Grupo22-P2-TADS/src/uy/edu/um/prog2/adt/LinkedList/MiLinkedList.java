package uy.edu.um.prog2.adt.LinkedList;

import java.util.Comparator;

public class MiLinkedList<T> implements MyList<T> {

    private Node<T> first;
    private Node<T> last;

    public MiLinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
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
    public void sort() {
        Node<T> i = first;
        while (i != null) {
            Node<T> j = first;
            while (j.getNext() != null) {

                if (((Comparable<T>)j.getValue()).compareTo(j.getNext().getValue()) > 0) {
                    T temp = j.getValue();
                    j.setValue(j.getNext().getValue());
                    j.getNext().setValue(temp);
                }
                j = j.getNext();
            }
            i = i.getNext();
        }
    }
    public void sort(Comparator<T> comparator) {
        Node<T> i = first;
        while (i != null) {
            Node<T> j = first;
            while (j.getNext() != null) {
                if (comparator.compare(j.getValue(), j.getNext().getValue()) > 0) {
                    T temp = j.getValue();
                    j.setValue(j.getNext().getValue());
                    j.getNext().setValue(temp);
                }
                j = j.getNext();
            }
            i = i.getNext();
        }
    }

    private void addTFirst(T value) {
        if (value != null) {
            Node<T> elementToAdd = new Node<>(value);
            if (this.first == null) {
                this.first = elementToAdd;
                this.last = elementToAdd;
            } else {
                elementToAdd.setPrev(this.last);
                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }
        } else {
        }
    }


    @Override
    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;
        while (temp != null && tempPosition != position) {
            temp = temp.getNext();
            tempPosition++;
        }
        if (tempPosition == position) {
            valueToReturn = temp.getValue();
        }
        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;
        while (temp != null && !temp.getValue().equals(value)) {
            temp = temp.getNext();
        }
        if (temp != null) {
            contains = true;
        }
        return contains;
    }

    @Override
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

     public void removePos(int position){
        Node<T> Temp = this.first;
        int inicio = 0;
        while (Temp.getNext() != null && inicio < position - 1) {
            Temp = Temp.getNext();
            inicio++;
        }
        Temp.setNext(Temp.getNext().getNext());
    }

    public T removeLast(){
            T valueToRemove = null;
            if (this.last != null) {
                valueToRemove = this.last.getValue();
                remove(valueToRemove);
            }
            return valueToRemove;

        }

        @Override
        public int size () {
            int size = 0;
            Node<T> temp = this.first;
            while (temp != null) {
                temp = temp.getNext();
                size++;
            }
            return size;
        }
    }




