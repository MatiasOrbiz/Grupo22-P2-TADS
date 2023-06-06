package Tads_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Heap.MyHeap;
import uy.edu.um.prog2.adt.Heap.MyHeapImpl;


public class HeapTest {

    @Test
    void testInsert(){
        Integer[] arrayP = new Integer[15];
        MyHeap<Integer> heapMax = new MyHeapImpl<>(arrayP, true, false);

        heapMax.insert(5);
        heapMax.insert(7);
        heapMax.insert(3);
        heapMax.insert(10);
        heapMax.insert(12);

        Assertions.assertEquals(12,arrayP[0]);
        Assertions.assertEquals(10,arrayP[1]);

        Integer[] arrayP2 = new Integer[15];
        MyHeap<Integer> heapMin = new MyHeapImpl<>(arrayP2,false,true);

        heapMin.insert(15);
        heapMin.insert(10);
        heapMin.insert(20);
        heapMin.insert(1);
        heapMin.insert(21);

        Assertions.assertEquals(1,arrayP2[0]);
        Assertions.assertEquals(21,arrayP2[4]);
    }


}
