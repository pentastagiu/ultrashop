package com.pentalog.us.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pentalog.us.model.Card;

/**
 * The card data access layer
 * @authors acozma and bpopovici
 *
 */
public interface CardDAO extends JpaRepository<Card, Integer> {
	
	Card findCardByCardnumberAndCardnameAndCardcodAndCardexpire(String cardnumber, String cardname, String cardcod, String cardexpire);
}