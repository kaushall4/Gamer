package ch.bzz.gamerList.model;

import ch.bzz.gamerList.data.DataHandler;

import java.util.List;
import java.util.Map;

public class User {

    private List<Gamer> gamerList;

    public User() {

    }

    public Map<String, Gamer> getBookMap() {
        return DataHandler.getGamerMap();
    }
    public Map<String, Spiel> getSpielMap() {
        return DataHandler.getSpielMap();
    }

    public Gamer getGamer(String uuid) {
        return DataHandler.readGamer(uuid);
    }
    public Spiel getSpiel(String uuid) {
        return DataHandler.readSpiel(uuid);
    }


}
