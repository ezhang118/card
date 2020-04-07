package com.epam.card;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardSender implements Runnable {

    private Game game;

    private int round;

    private AtomicBoolean hasWinner;

    private CyclicBarrier startRound;

    private CyclicBarrier endRound;

    public CardSender(Game game, AtomicBoolean hasWinner, CyclicBarrier startRound, CyclicBarrier endRound) {
        this.game = game;
        this.hasWinner = hasWinner;
        this.startRound = startRound;
        this.endRound = endRound;
    }

    @Override
    public void run() {
        try {
            game.shuffleCards();
            
            while (!hasWinner.get()) {
                System.out.println("Round " + ++round);
                game.dealWithCards();

                startRound.await();
                endRound.await();
            }
        } catch (Exception e) {
            System.out.println("Card Sender Exception");
        }
    }
}
