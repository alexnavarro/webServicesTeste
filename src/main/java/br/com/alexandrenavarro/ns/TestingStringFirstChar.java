package br.com.alexandrenavarro.ns;

public class TestingStringFirstChar {
	
	public static void main(String[] args) {
	
    System.out.println(new StringFirstChar().firstChar(new StreamImp("aAbxBABacbc")));	
	System.out.println(new StringFirstChar().firstChar(new StreamImp("aAbBABacbc")));
	
	
	}

}