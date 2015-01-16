package net.betabears.capstone.main.model.map;

import net.betabears.capstone.main.controller.Entities;
import net.betabears.capstone.main.controller.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MapFactory {
    private static final MapFactory MAP_FACTORY = new MapFactory();
    private final String FOLDER = "res/maps";

    private String[] mapNames;

    public MapFactory() {
        File folder = new File(FOLDER);
        mapNames = folder.list((dir, name) -> name.endsWith(".properties"));
        for (int i = 0; i < mapNames.length; i++) {
            mapNames[i] = mapNames[i].substring(0, mapNames[i].lastIndexOf('.'));
        }
    }

    public static MapFactory getInstance() {
        return MAP_FACTORY;
    }

    public String[] getMapNames() {
        return mapNames;
    }

    public Entities getEntities(String mapName) {
        try (FileInputStream fis = new FileInputStream(FOLDER + '/' + mapName + ".properties")) {
            Properties props = new Properties();
            props.load(fis);
            Map map = new Map(props);
            return new Entities(props, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
