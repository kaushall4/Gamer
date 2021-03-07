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
    private static List<Spiel> spielList = null;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {

    }

    /**
     * gets a list of all spiele with their gamer
     *
     * @return
     */
    public static List<Spiel> getSpielList() {
        if (spielList == null) {
            spielList = new ArrayList<>();
            readJSON();
        }
        return spielList;
    }

    public static List<Gamer> getGamerList() {
        List<Gamer> gamerList = new ArrayList<>();

        for (Spiel spiel : getSpielList()) {
            for (Gamer gamer : spiel.getGamerList()) {
                gamerList.add(gamer);
            }
        }
        return gamerList;
    }

    /**
     * find the spiel for a gamer
     *
     * @param gamerUUID
     * @return
     */
    public static Spiel findSpielByGamer(String gamerUUID) {
        for (Spiel spiel : getSpielList()) {
            for (Gamer gamer : spiel.getGamerList()) {
                if (gamer.getGamerUUID().equals(gamerUUID))
                    return spiel;
            }
        }
        return null;
    }

    /**
     * gets a Gamer by its uuid
     *
     * @param uuid the uuid of the Gamer
     * @return gamer-object
     */
    public static Gamer findGamerByUUID(String uuid) {
        List<Gamer> gamerList = getGamerList();
        for (Gamer gamer : gamerList) {
            if (gamer != null && gamer.getGamerUUID().equals(uuid))
                return gamer;
        }

        return null;
    }

    /**
     * reads the json-file into the spielList
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("gamerJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Spiel[] spiele = objectMapper.readValue(jsonData, Spiel[].class);
            for (Spiel spiel : spiele) {
                getSpielList().add(spiel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}