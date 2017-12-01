package com.lmig.gfc.blackjack.models;

import java.util.Collections;
import java.util.Stack;

public class Deck {

	private Stack<Card> deck;

	public Deck() {
		this.deck = new Stack<Card>();
		this.generateDeck();
		this.shuffleDeck();
	}

	public void generateDeck() {

		for (Suits suit : Suits.values()) {
			deck.push(new AceCard(suit));
			for (int i = 2; i <= 10; i = i + 1) {
				deck.push(new NumberCard(suit, i));
			}
			for (Faces face : Faces.values()) {
				deck.push(new FaceCard(suit, face));
			}
		}
	}

	public void shuffleDeck() {
		Collections.shuffle(deck);
		// printing shuffled cards to console for testing
		for (Card c : deck) {
			System.out.println(c);
		}
	}

	public Card drawCard() {
		return deck.pop();

	}

	public boolean isDeckEmpty() {
		return deck.isEmpty();
	}

	public int getDeckSize() {
		return deck.size();
	}
}
