package com.epam.card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player implements Runnable {

    private String name;

    private AtomicBoolean hasWinner;

    private CyclicBarrier startRound;

    private CyclicBarrier endRound;

    private List<Card> cards;

    public Player(String name, AtomicBoolean hasWinner, CyclicBarrier startRound, CyclicBarrier endRound) {
        this.name = name;
        this.hasWinner = hasWinner;
        this.startRound = startRound;
        this.endRound = endRound;

        this.cards = new ArrayList<>();
    }

    public void receive(Card card) {
        cards.add(card);
    }

    private void checkPoints() {
        int sum = cards.stream().mapToInt(Card::getPoint).sum();
        if (sum > 50) {
            hasWinner.set(true);
            System.out.println(name + " is the winner!");
        }
    }

    @Override
    public void run() {
        try {
            while (!hasWinner.get()) {
                startRound.await();

                System.out.println(name + ": " + cards);
                checkPoints();

                endRound.await();
            }
        } catch (Exception e) {
            System.out.println(name + " Exception");
        }
    }
}
