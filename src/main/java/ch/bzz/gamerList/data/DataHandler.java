package ch.bzz.gamerList.data;

import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.Spiel;
import ch.bzz.gamerList.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * data handler for reading and writing the csv files
 * <p>
 *
 * @author Kaushall Vimalarajah
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String,Gamer> gamerMap;
    private static Map<String, Spiel>spielMap;

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
        gamerMap = new HashMap<>();
        spielMap = new HashMap<>();
        readJSON();
    }

    /**
     * gets the bookMap
     *
     * @return the bookMap
     */
    public static Map<String,Gamer> getGamerMap() {
        return gamerMap;
    }

    /**
     * gets the publisherMap
     *
     * @return the publisherMap
     */
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
        if (getGamerMap().containsKey(gamerUUID)) {
            gamer = getGamerMap().get(gamerUUID);
        }
        return gamer;
    }



    /**
     * inserts a new book into the bookmap
     *
     * @param gamer the gamer to be saved
     */
    public static void insertGamer(Gamer gamer) {
        getGamerMap().put(gamer.getGamerUUID(), gamer);
        writeJSON();
    }

    public static void insertGamer(Gamer gamer,String spielUUID) {
        Spiel spiel = getSpielMap().get(spielUUID);
        gamer.setSpiel(spiel);
        getGamerMap().put(gamer.getGamerUUID(), gamer);
        writeJSON();
    }
    /**
     * updates the bookmap
     */
    public static void updateGamer() {
        writeJSON();
    }
    public static void updateSpiel() {
        writeJSON();
    }


    /**
     * removes a book from the bookmap
     *
     * @param gamerUUID the uuid of the book to be removed
     * @return success
     */
    public static boolean deleteGamer(String gamerUUID) {
        if (getGamerMap().remove(gamerUUID) != null) {
            writeJSON();
            return true;
        } else
            return false;

    }

    /**
     * removes a book from the bookmap
     *
     * @param spielUUID the uuid of the book to be removed
     * @return success
     */
    public static int deleteSpiel(String spielUUID) {
        int errorcode = 1;
        for (Map.Entry<String, Gamer> entry : getGamerMap().entrySet()) {
            Gamer gamer = entry.getValue();
            if (gamer.getSpiel().getSpielUUID().equals(spielUUID)) {
                if (gamer.getNachname() == null || gamer.getNachname().equals("")) {
                    deleteGamer(gamer.getGamerUUID());
                    errorcode = 0;
                } else {
                    return -1;
                }
            }
        }
        writeJSON();
        return errorcode;
    }


    /**
     * reads a single spiel identified by its uuid
     * @param spielUUID  the identifiers
     * @return publisher-object
     */
    public static Spiel readSpiel(String spielUUID) {
        Spiel spiel = new Spiel();
        if (getGamerMap().containsKey(spielUUID)) {
            spiel = getSpielMap().get(spielUUID);
        }
        return spiel;
    }

    /**
     * inserts a new spiel in an empty Gamer
     * @param spiel
     */
    public static void insertSpiel(Spiel spiel) {
        Gamer gamer = new Gamer();
        gamer.setGamerUUID(UUID.randomUUID().toString());
        gamer.setVorname("");
        gamer.setNachname("");
        gamer.setSpiel(spiel);
        insertGamer(gamer);
    }

    public static void insertSpiel(Spiel spiel,String gamerUUID) {
        Gamer gamer = getGamerMap().get(gamerUUID);
        gamer.setSpiel(spiel);
        getSpielMap().put(spiel.getSpielUUID(), spiel);
        writeJSON();
    }



    public static boolean updateSpiel(Spiel spiel) {
        boolean found = false;
        for (Map.Entry<String, Gamer> entry : getGamerMap().entrySet()) {
            Gamer gamer = entry.getValue();
            if (gamer.getSpiel().getSpielUUID().equals(spiel.getSpielUUID())) {
                gamer.setSpiel(spiel);
                found = true;
            }
        }
        writeJSON();
        return found;
    }


    /**
     * deletes a spiel, if it has no gamer
     * @param spielUUID
     * @return errorcode  0=ok, -1=referential integrity, 1=not found

    public static int deleteSpiel(String spielUUID) {
        int errorcode = 1;
        for (Map.Entry<String, Gamer> entry : getGamerMap().entrySet()) {
            Gamer gamer = entry.getValue();
            if (gamer.getSpiel().getSpielUUID().equals(spielUUID)) {
                if (gamer.getNachname() == null || gamer.getNachname().equals("")) {
                    deleteGamer(gamer.getGamerUUID());
                    errorcode = 0;
                } else {
                    return -1;
                }
            }
        }
        writeJSON();
        return errorcode;
    }

 */

    /**
     * reads the json-file into the gamerList
     */
    private static void readJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("gamerJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Gamer[] gamers = objectMapper.readValue(jsonData, Gamer[].class);
            for (Gamer gamer : gamers) {
                getGamerMap().put(gamer.getGamerUUID(), gamer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * write the books and publishers
     */

    private static void writeJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String gamerPath = Config.getProperty("gamerJSON");
        try {
            fileOutputStream = new FileOutputStream(gamerPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGamerMap().values());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}