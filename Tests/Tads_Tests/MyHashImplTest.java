package Tads_Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Hash.ClosedHashImpl;
import uy.edu.um.prog2.adt.Hash.MyHash;


public class MyHashImplTest {

    @Test
    void flujo() {

        MyHash<Integer,Integer> HashP = new ClosedHashImpl<>(11,true);

        HashP.put(1,1111);
        HashP.put(2,549);
        HashP.put(3,158);

        Assertions.assertEquals(549,HashP.get(2));

        HashP.put(4,5678);

        HashP.remove(3);

        Assertions.assertEquals(3,HashP.size());

        HashP.put(3,510);

        Assertions.assertEquals(4,HashP.size());
    }
}
