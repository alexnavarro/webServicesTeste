package br.com.alexandrenavarro.ns;

import java.util.ArrayList;
import java.util.List;

public class StringFirstChar {

	public static char firstChar(Stream input) {
		List<Character> list1 = new ArrayList<Character>();
		List<Character> list2 = new ArrayList<Character>();

		while (input.hasNext()) {
			char next = input.getNext();
			if (!list1.contains(next)) {
				list1.add(next);
			} else {
				list2.add(next);
			}
		}

		for (Character c : list1) {
			if (!list2.contains(c)) {
				return c;
			}
		}

		throw new RuntimeException(
				"Todos os caractres se repetem!");

	}

}
