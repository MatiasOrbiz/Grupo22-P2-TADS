package uy.edu.um.prog2.adt.Hash;

import uy.edu.um.prog2.adt.LinkedList.MiLinkedList;
import uy.edu.um.prog2.adt.LinkedList.MyList;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Implementacion de MyHash Cerrado, que se autodimensiona
 * si el factor de carga supera 0.75
 */
public class MyHashImpl<Key, Value> implements MyHash<Key, Value> {

	private HashMap<Key, Value> map = null;
	
	/**
	 * Crea un hash cerrado con la cantidad de buckets de 16
	 */
	public MyHashImpl() {
		map = new HashMap<Key, Value>();
	}

	/**
	 * Crea un hash cerrado con la cantidad de buckets inicial de "initialCapacity"
	 */
	public MyHashImpl(int initialCapacity) {
		map = new HashMap<Key, Value>(initialCapacity);
	}


	@Override
	public MyList<Key> keys() {
		MyList<Key> toReturn = new MiLinkedList<>();

		map.keySet().stream().forEach(s -> toReturn.add(s));

		return toReturn;
	}

	@Override
	public MyList<Value> values() {
		MyList<Value> toReturn = new MiLinkedList<>();

		map.values().stream().forEach(s -> toReturn.add(s));

		return toReturn;
	}

    @Override
    public int size() {
        return map.size();
    }

    @Override
	public void put(Key key, Value value) {
		map.put(key, value);
	}

	@Override
	public Value get(Key key) {
		return map.get(key);
	}

    @Override
    public boolean contains(Key key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(Key key) {
        map.remove(key);
    }

	@Override
	public Iterator<Key> iterator() {
		return map.keySet().iterator();
	}



}
