package net.betabears.capstone.main.model.map.field;

public class FieldFactory {

    /**
     * Creates a new Field specified by given String
     * @param id identifying the Field
     * @return created Field
     */
    public Field getField(String id) {
        switch (id) {
            case "0":
                return new Wall();
            case "1":
                return new Entrance();
            case "2":
                return new Exit();
            default:
                return new Field();
        }
    }
}
