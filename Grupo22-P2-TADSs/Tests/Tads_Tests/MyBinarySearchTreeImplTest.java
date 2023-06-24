package Tads_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.BinaryTree.MyBinarySearchTreeImpl;
import uy.edu.um.prog2.adt.BinaryTree.MySearchBinaryTree;

class MyBinarySearchTreeImplTest {

    @Test
    public void testPrincipal() {
        MySearchBinaryTree<Integer, Integer> ArbolP = new MyBinarySearchTreeImpl<>();

        ArbolP.add(3, 478);
        ArbolP.add(5, 934);
        ArbolP.add(1, 1000);
        ArbolP.add(2, 1432);

        Assertions.assertEquals(null, ArbolP.find(4));
        Assertions.assertEquals(934, ArbolP.find(5));

        ArbolP.remove(1);
        ArbolP.add(1, 1111);
        Assertions.assertEquals(1111, ArbolP.find(1));
    }
}