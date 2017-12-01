package com.lmig.gfc.blackjack.models;

public class NumberCard extends Card {

	private int value;

	public NumberCard(Suits suit, int value) {
		super(suit);
		this.value = value;
	}

	@Override
	public String getRank() {
		return String.valueOf(value);

	}

	@Override
	public int getValue() {
		return value;
	}

}
