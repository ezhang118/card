package com.epam.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {

    private AtomicBoolean hasWinner;

    private CyclicBarrier startRound;

    private CyclicBarrier endRound;

    private LinkedList<Card> cards;

    private List<Player> players;

    private CardSender cardSender;

    public Game(int playerNum) {
        this.hasWinner = new AtomicBoolean(false);
        this.startRound = new CyclicBarrier(playerNum + 1);
        this.endRound = new CyclicBarrier(playerNum + 1);

        initCards();
        initPlayers(playerNum);
        initCardSender();
    }

    public void start() {
        players.forEach(player -> new Thread(player).start());
        new Thread(cardSender).start();
    }

    private void initCards() {
        cards = new LinkedList<>();

        for (int i = 0; i < 52; i++) {
            switch (i/4) {
                case 0:
                    cards.add(new Card("A", 1));
                    break;
                case 1:
                    cards.add(new Card("2", 2));
                    break;
                case 2:
                    cards.add(new Card("3", 3));
                    break;
                case 3:
                    cards.add(new Card("4", 4));
                    break;
                case 4:
                    cards.add(new Card("5", 5));
                    break;
                case 5:
                    cards.add(new Card("6", 6));
                    break;
                case 6:
                    cards.add(new Card("7", 7));
                    break;
                case 7:
                    cards.add(new Card("8", 8));
                    break;
                case 8:
                    cards.add(new Card("9", 9));
                    break;
                case 9:
                    cards.add(new Card("10", 10));
                    break;
                case 10:
                    cards.add(new Card("J", 11));
                    break;
                case 11:
                    cards.add(new Card("Q", 12));
                    break;
                case 12:
                    cards.add(new Card("K", 13));
                    break;
            }
        }
        cards.add(new Card("Black Joker", 20));
        cards.add(new Card("Red Joker", 20));
        System.out.println("Init cards: " + cards);
    }

    private void initPlayers(int playerNum) {
        players = new ArrayList<>();

        for (int i = 0; i < playerNum; i++) {
            players.add(new Player("Player " + (i + 1), hasWinner, startRound, endRound));
        }
    }

    private void initCardSender() {
        cardSender = new CardSender(this, hasWinner, startRound, endRound);
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
        System.out.println("After shuffle: " + cards);
    }

    public void dealWithCards() {
        players.forEach(player -> player.receive(cards.pop()));
    }
}
