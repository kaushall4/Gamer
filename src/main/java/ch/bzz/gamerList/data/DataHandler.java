package ch.bzz.gamerList.data;

import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.Spiel;
import ch.bzz.gamerList.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 *
 * @author Kaushall Vimalarajah
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String,Gamer> gamerList;
    private static Map<String, Spiel>spielMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        gamerList = new HashMap<>();
        spielMap = new HashMap<>();
        readJSON();
    }


    public static Map<String,Gamer> getGamerList() {
        return gamerList;
    }

    public static Map<String, Spiel>getSpielMap(){
        return spielMap;
    }



    /**
     * gets a Gamer by its uuid
     *
     * @param gamerUUID the uuid of the Gamer
     * @return gamer-object
     */

    public static Gamer readGamer(String gamerUUID) {
        Gamer gamer = null;
        if (getGamerList().containsKey(gamerUUID)) {
            gamer = getGamerList().get(gamerUUID);
        }
        return gamer;
    }


    /**
     * reads a single spiel identified by its uuid
     * @param spielUUID  the identifiers
     * @return publisher-object
     */
    public static Spiel readSpiel(String spielUUID) {
        Spiel spiel = new Spiel();
        if (getGamerList().containsKey(spielUUID)) {
            spiel = getSpielMap().get(spielUUID);
        }
        return spiel;
    }
    /**
     * reads the json-file into the gamerList
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("gamerJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Gamer[] gamers = objectMapper.readValue(jsonData, Gamer[].class);
            for (Gamer gamer : gamers) {
                getGamerList().put(gamer.getGamerUUID(), gamer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}