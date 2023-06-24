package Tads_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.LinkedList.MyList;

public class LinkedListTest {

    @Test
    public void testAdd(){
        MyList<Integer> prueba = new MiLinkedList();

        prueba.add(7);
        prueba.add(9);
        prueba.add(3);
        prueba.add(4);

        Assertions.assertEquals(4, prueba.get(3));
        Assertions.assertEquals(7, prueba.get(0));

        Assertions.assertTrue(prueba.contains(4));
    }

    @Test

    public void testRemove(){
        MyList<Integer> prueba = new MiLinkedList();

        prueba.add(7);
        prueba.add(9);
        prueba.add(3);
        prueba.add(4);

        prueba.remove(9);
        Assertions.assertNotEquals(9, prueba.get(2));

        Assertions.assertFalse(prueba.contains(9));

        Assertions.assertEquals(3, prueba.size());

        prueba.remove(7);
        prueba.remove(4);

        Assertions.assertEquals(1, prueba.size());
    }

}

