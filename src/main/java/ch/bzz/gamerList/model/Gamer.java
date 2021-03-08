package ch.bzz.gamerList.model;

import ch.bzz.gamerList.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Gamer {
    private String gamerUUID;
    private String vorname;
    private String nachname;
    private int alter;
    private Spiel[] spiel;


    public Gamer() {
    }
//todo
    /**
     * gets the uuid of the spiel from the spielList
     * @return the spielUUID
     */
    /*
    @JsonIgnore
    public String getPublisherUUID() {
        Spiel spiel = DataHandler.findSpielByGamer(getGamerUUID());
        return spiel.getSpielUUID();
    }
    */

    /**
     * gets the spiel name from the spielList
     * @return the spielname
     */

    /*
    @JsonIgnore
    public String getSpielName() {
        Spiel spiel = DataHandler.findSpielByGamer(getGamerUUID());
        return spiel.getSpiel();
    }*/
    /**
     * Gets the vorname
     *
     * @return value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets the vorname
     *
     * @param vorname =  the value to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gets the nachname
     *
     * @return value of nachname
     */
    public String getNachname() {
        return nachname;
    }
    /**
     * Sets the nachname
     *
     * @param nachname =  the value to set
     */

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gets the alter
     *
     * @return value of alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * Sets the alter
     *
     * @param alter =  the value to set
     */

    public void setAlter(Integer alter) {
        this.alter = alter;
    }

    /**
     * Gets the spiel
     *
     * @return value of spiel
     */
    public Spiel[] getSpiel() {
        return spiel;
    }
    /**
     * Sets the spiel
     *
     * @param spiel =  the value to set
     */

    public void setSpiel(Spiel[] spiel) {
        this.spiel = spiel;
    }



    public String getGamerUUID() {
        return gamerUUID;
    }

    public void setGamerUUID(String gamerUUID) {
        this.gamerUUID = gamerUUID;
    }
}
