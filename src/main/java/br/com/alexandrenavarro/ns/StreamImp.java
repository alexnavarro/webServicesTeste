package br.com.alexandrenavarro.ns;

public class StreamImp implements Stream {

	private String string;
	private int counter = 0;

	StreamImp(String string) {
		this.string = string;
	}

	@Override
	public char getNext() {
		char charAt = string.charAt(counter);
		counter++;
		return charAt;
	}

	@Override
	public boolean hasNext() {
		return counter < string.length();
	}

}
