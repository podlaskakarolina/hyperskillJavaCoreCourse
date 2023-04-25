package org.java.com.kpodlaska.flashcards.production;

import java.util.Objects;


class Card {
    private final String cardName;
    private String cardDefinition;
    private Integer errorCounter;

    public Card(String cardName, String cardDefinition, Integer errorCounter) {
        this.cardName = cardName;
        this.cardDefinition = cardDefinition;
        this.errorCounter = errorCounter;
    }

    public Card(String cardName, String cardDefinition) {
        this.cardName = cardName;
        this.cardDefinition = cardDefinition;
        this.errorCounter = 0;
    }

    public Integer getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(Integer errorCounter) {
        this.errorCounter = errorCounter;
    }

    public void clearErrorCounter() {
        errorCounter = 0;
    }

    public void incrementErrorCounter() {
        errorCounter++;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDefinition() {
        return cardDefinition;
    }

    public void setCardDefinition(String cardDefinition) {
        this.cardDefinition = cardDefinition;
    }

    @Override
    public String toString() {
        return cardName + ":" + cardDefinition + ":" + errorCounter + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card1 = (Card) o;
        return Objects.equals(cardName, card1.cardName) &&
                Objects.equals(cardDefinition, card1.cardDefinition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName, cardDefinition);
    }}