package com.pentalog.us.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pentalog.us.dao.CardDAO;
import com.pentalog.us.model.Card;
import com.pentalog.us.service.CardService;

/**
 * The card service implementation
 * @author acozma and bpopovici
 *
 */
@Service("cardService")
public class CardServiceImpl implements CardService {
	
	/**
	 * The card data access object 
	 */
	@Autowired
	private CardDAO cardDAO;

	/**
	 * @see {@link CardService.getCards}
	 */
	@Override
	public List<Card> getCards() {
		return cardDAO.findAll();
	}

	/**
	 * @see {@link CardService.getCardById}
	 */
	@Override
	public Card getCardById(int id) {
		return cardDAO.findOne(id);
	}

	/**
	 * @see {@link CardService.postCard}
	 */
	@Override
	public void postCard(Card card) {
		cardDAO.save(card);
	}

	/**
	 * @see {@link CardService.putCard}
	 */
	@Override
	public Card putCard(Card card) {
		return cardDAO.save(card);
	}

	/**
	 * @see {@link CardService.deleteCard}
	 */
	@Override
	public void deleteCard(Card card) {
		cardDAO.delete(card);
	}

	@Override
	public Card getCardByCard(Card card) {
		return cardDAO.findCardByCardnumberAndCardnameAndCardcodAndCardexpire(card.getCardnumber(), card.getCardname(), card.getCardcod(), card.getCardexpire());
	}
}