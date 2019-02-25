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

    public Player(String name) {
        this.name = name;
        this.damageTaken = 0;
        this.health = 10;
        this.programCardHand = new ArrayList<ProgramCard>();

    }

    public void killPlayer() {
        while (this.health>0) {
            this.reduceHealthBy1();
        }
    }
    public void reduceHealthBy1() {
        this.health--;
        this.damageTaken++;
        this.isDead();
    }

    private void isDead() {
        if(health<=0) {
            this.isAlive = false;
            this.isDead = true;
        }
    }
}
