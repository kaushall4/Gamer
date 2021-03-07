package ch.bzz.gamerList.model;

import java.util.List;

/**
 * a spiel of a gamer
 * <p>
 * Gamer
 *
 * @author Kaushall Vimalarajah
 */

public class Spiel {
    private String spiel;

    /**
     * Gets the titel
     *
     * @return value of titel
     */
    public String getSpiel() {
        return spiel;
    }

    /**
     * Sets the titel
     *
     * @param spiel the value to set
     */
    public void setSpiel(String spiel) {
        this.spiel = spiel;
    }

    /**
     * Gets the konsole
     *
     * @return value of konsole
     */

    public String getKonsole() {
        return konsole;
    }

    /**
     * Sets the konsole
     *
     * @param konsole the value to set
     */
    public void setKonsole(String konsole) {
        this.konsole = konsole;
    }

    /**
     * Gets the genre
     *
     * @return value of genre
     */

    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre
     *
     * @param genre the value to set
     */

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the altersgrenze
     *
     * @return value of altersgrenze
     */

    public int getAltersgrenze() {
        return altersgrenze;
    }

    /**
     * Sets the altersgrenze
     *
     * @param altersgrenze the value to set
     */

    public void setAltersgrenze(int altersgrenze) {
        this.altersgrenze = altersgrenze;
    }


    private String konsole;
    private String genre;
    private int altersgrenze;
    private List<Gamer> gamerList;
    private String spielUUID;


    /**
     * Gets the spielUUID
     *
     * @return value of spielUUID
     */
    public String getSpielUUID() {
        return spielUUID;
    }


    /**
     * Sets the spielUUID
     *
     * @param spielUUID the value to set
     */
    public void setSpielUUID(String spielUUID) {
        this.spielUUID = spielUUID;
    }

    /**
     * gets the gamerMap
     *
     * @return map of gamer
     */
    public List<Gamer> getGamerList() {
        return gamerList;
    }

    /**
     * sets the gamerMap
     *
     * @param gamerList
     */
    public void setGamerList(List<Gamer> gamerList) {
        this.gamerList = gamerList;
    }
}
