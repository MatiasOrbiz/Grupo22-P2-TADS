package Tads_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Stack.EmptyStackException;
import uy.edu.um.prog2.adt.Stack.MyStack;
import uy.edu.um.prog2.adt.Stack.MyStackImpl;

public class MyStackImplTest {

    @Test
    public void testCompleto() {
        MyStack<Integer> StackP = new MyStackImpl<>();

        StackP.push(new Integer(2));
        StackP.push(new Integer(4));
        StackP.push(new Integer(7));

        Assertions.assertEquals(new Integer(7), StackP.peek());

        try {

            Assertions.assertEquals(new Integer(7), StackP.pop());

        } catch (EmptyStackException e) {
            System.out.println("Empty Stack");
        }
    }
}
