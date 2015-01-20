package net.betabears.capstone.main.model.entity.unit;

import net.betabears.capstone.main.model.entity.Entity;

public class Unit extends Entity {
    protected int life;

    public Unit(int x, int y, int life) {
        super(x, y);
        this.life = life;
    }

    public boolean damage(int damage) {
        return (life -= damage) <= 0;
    }

    public int getLife() {
        return life;
    }
}
