package com.dentist.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class Person {
	private String name;
}

class Driver extends Person {
	private String licenseNo;
}

public class Test1 {

	public static void main(String[] args) {
		Driver p = new Driver();
		Collection<Driver> d = new ArrayList<Driver>();
		d.add(p);
		Queue<String> q = new PriorityQueue<String>();
		ArrayList<Driver> d1 = new ArrayList<Driver>();
		LinkedList<String> strings = new LinkedList<String>();
		strings.add("Welcome");
		strings.add("are");
		strings.add("you");
		strings.add("?");
		strings.add("what");
		strings.add("are");
		strings.add("you");
		strings.add("doing");
		strings.add("?");
		Queue<String> q1 = new PriorityQueue<String>(strings);

		String[] s1 = strings.toArray(new String[0]);
		Set<String> s = new TreeSet<String>(Arrays.asList("where", "are", "are"));
		Random rand = new Random();
		Map<Integer, String> m = new HashMap<Integer, String>();
		m.put(1, "Srikanth");

		for (Entry<Integer, String> e : m.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());

			System.out.println(e.getKey() + "   :   " + e.getValue());
		}

		try {
			FileInputStream in = new FileInputStream("");
			FileOutputStream out = new FileOutputStream("outagain.txt");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Iterator<Integer> i = m.keySet().iterator(); i.hasNext();) {
			i.next();
			i.remove();
		}

		shuffle(strings, rand);
		for (String s2 : strings) {
			System.out.println(s2);
		}

	}

	public static void shuffle(List<?> list, Random rand) {
		for (int i = list.size(); i > 1; i--)
			swap(list, i - 1, rand.nextInt(i));
	}

	public static <E> void swap(List<E> a, int i, int j) {
		E tmp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, tmp);
	}

}
