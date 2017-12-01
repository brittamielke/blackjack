package com.lmig.gfc.blackjack.models;

public class House {

	private Hand houseHand;

	public House() {
		// houseHand = new Hand();
	}

	public void acceptCardFromGame(Card c) {
		houseHand.acceptCardToHand(c);
	}

	public Hand getHouseHand() {
		return houseHand;
	}

	public void createNewHand() {
		houseHand = new Hand();
	}
}
