package com.pentalog.us.service;

import java.util.List;
import com.pentalog.us.model.Card;

/**
 * The card service
 * @authors acozma and bpopovici
 *
 */
public interface CardService {
	
	/**
	 * Method that get cards
	 * @return
	 */
	List<Card> getCards();
	
	/**
	 * Method that get card by id
	 * @param id
	 * @return
	 */
	Card getCardById(int id);
	
	/**
	 * Method that post card
	 * @param card
	 */
	void postCard(Card card);
	
	/**
	 * Method that put card
	 * @param card
	 */
	Card putCard(Card card);
	
	/**
	 * Method that delete card
	 * @param card
	 */
	void deleteCard(Card card);
	
	Card getCardByCard(Card card);
}