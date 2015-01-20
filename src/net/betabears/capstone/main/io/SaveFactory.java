package net.betabears.capstone.main.io;

import net.betabears.capstone.main.controller.Entities;

import java.io.*;

public class SaveFactory {
    private static final SaveFactory SAVE_FACTORY = new SaveFactory();
    private final String FOLDER = "res/save";

    private String[] saveNames;

    public SaveFactory() {
        init();
    }

    /**
     * Read all SaveFileNames and buffer them
     */
    private void init() {
        File folder = new File(FOLDER);
        saveNames = folder.list((dir, name) -> name.endsWith(".save"));
        for (int i = 0; i < saveNames.length; i++) {
            saveNames[i] = saveNames[i].substring(0, saveNames[i].lastIndexOf('.'));
        }
    }

    /**
     *
     * @return an Instance of this SaveFactory
     */
    public static SaveFactory getInstance() {
        return SAVE_FACTORY;
    }

    /**
     *
     * @return an Array of all Names of saved Games
     */
    public String[] getSaveNames() {
        return saveNames;
    }

    /**
     * Saves given {@link net.betabears.capstone.main.controller.Entities} to File with given Name
     * @param saveName Name for File to save in
     * @param entities Entities to save
     */
    public void saveEntities(String saveName, Entities entities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FOLDER + '/' + saveName + ".save"))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    /**
     * Load {@link net.betabears.capstone.main.controller.Entities} from File with given Name
     * @param saveName Name of File to load from
     * @return Loaded Entities
     */
    public Entities getEntities(String saveName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FOLDER + '/' + saveName + ".save"))) {
            return (Entities)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
