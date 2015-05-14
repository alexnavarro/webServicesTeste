package br.com.alexandrenavarro.ns.exercise3;

import java.util.ArrayList;
import java.util.List;

public class StringFirstChar {

	public static char firstChar(Stream input) {
		List<Character> character = new ArrayList<Character>();
		List<Character> repeatedChar = new ArrayList<Character>();

		while (input.hasNext()) {
			char next = input.getNext();
			if (!character.contains(next)) {
				character.add(next);
			} else {
				repeatedChar.add(next);
			}
		}

		for (Character c : character) {
			if (!repeatedChar.contains(c)) {
				return c;
			}
		}

		throw new RuntimeException("Todos os caractres se repetem!");

	}

}
