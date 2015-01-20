package net.betabears.capstone.main.model.entity;

import net.betabears.capstone.main.model.entity.unit.DynamicEnemy;
import net.betabears.capstone.main.model.entity.unit.Player;
import net.betabears.capstone.main.model.entity.unit.StaticEnemy;

public class EntityFactory {

    /**
     * Creates a new Entity specified by given String
     * @param id identifying the Entity
     * @return created Entity
     */
    public Entity getEntity(String id, int x, int y) {
        switch (id) {
            case "1":
                return new Player(x, y);
            case "3":
                return new StaticEnemy(x, y);
            case "4":
                return new DynamicEnemy(x, y);
            case "5":
                return new Key(x, y);
            default:
                return null;
        }
    }
}
