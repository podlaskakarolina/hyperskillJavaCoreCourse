package com.kpodlaska.flashcards.production;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getErrorCounter() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        Integer result = card.getErrorCounter();
        //then
        assertEquals(3,result);
    }

    @Test
    void setErrorCounter() {
        //given
        Card card = new Card("cat","furry pet");
        card.setErrorCounter(2);
        //when
        Integer result = card.getErrorCounter();
        //then
        assertEquals(2,result);
    }

    @Test
    void clearErrorCounter() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        card.clearErrorCounter();
        Integer result = card.getErrorCounter();
        //then
        assertEquals(0,result);
    }

    @Test
    void incrementErrorCounter() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        card.incrementErrorCounter();
        Integer result = card.getErrorCounter();
        //then
        assertEquals(4,result);

    }

    @Test
    void getCardName() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        String result = card.getCardName();
        //then
        assertEquals("cat",result);
    }

    @Test
    void getCardDefinition() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        String result = card.getCardDefinition();
        //then
        assertEquals("furry pet",result);
    }

    @Test
    void setCardDefinition() {
        //given
        Card card = new Card("dog","",3);
        //when
        card.setCardDefinition("another furry bastard");
        String result = card.getCardDefinition();
        //then
        assertEquals("another furry bastard",result);
    }

    @Test
    void testToString() {
        //given
        Card card = new Card("cat","furry pet",3);
        //when
        String result = card.toString();
        //then
        assertEquals("cat:furry pet:3\n",result);
    }

}