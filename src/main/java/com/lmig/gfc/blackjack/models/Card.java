package com.lmig.gfc.blackjack.models;

public abstract class Card {

	private Suits suit;

	public Card(Suits suit) {
		this.suit = suit;

	}

	public Suits getSuit() {
		return suit;
	}

	public abstract String getRank();

	public abstract int getValue();

	@Override
	public String toString() {
		return getRank() + " of " + getSuit();
	}

}
