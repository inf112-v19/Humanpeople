package inf112.skeleton.app.Player;

import inf112.skeleton.app.Cards.ProgramCard;

import java.util.ArrayList;

public class Player {
    private int health;
    private int damageTaken;
    private String name;
    private boolean isAlive;
    private boolean isDead;
    private ArrayList<ProgramCard> programCardHand;
    private ArrayList<ProgramCard> programSet;

    public Player(String name) {
        this.isAlive = true;
        this.isDead = false;
        this.name = name;
        this.damageTaken = 0;
        this.health = 10;
        this.programCardHand = new ArrayList<ProgramCard>();
        programSet = new ArrayList<ProgramCard>();


    }

    public void killPlayer() {
        while (this.isAlive()) {
            this.reduceHealthBy1();
        }
    }
    public void reduceHealthBy1() {
        if (isAlive()) {
        this.health--;
        this.damageTaken++;
        }
    }

    private boolean isDead() {
        return (this.health < 1);
    }

    private boolean isAlive() {
        return (this.health > 0);
    }

    public int getHealth() {
        return this.health;
    }
    
    public int getProgramCardHandSize() {
        return this.programCardHand.size();
    }
}
