package com.lmig.gfc.blackjack.models;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private ArrayList<Card> cards;

	public Hand() {
		cards = new ArrayList<Card>();
	}

	public void acceptCardToHand(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getTotal() {
		int total = 0;
		for (Card card : cards) {
			if (card.getRank() == "A" && total > 10) {
				total = total + 1;
			} else {
				total = total + card.getValue();
			}
		}
		return total;
	}

	public boolean isBusted() {
		return (getTotal() > 21);
	}

	public boolean isBlackjack() {
		return (cards.size() == 2 && getTotal() == 21);
	}

}
