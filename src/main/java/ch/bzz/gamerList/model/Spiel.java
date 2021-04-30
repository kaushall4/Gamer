package ch.bzz.gamerList.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * a spiel of a gamer
 * <p>
 * Gamer
 *
 * @author Kaushall Vimalarajah
 */

public class Spiel {



    @FormParam("spielUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
    private String spielUUID;



    @FormParam("titel")
    @NotEmpty
    private String titel;

    @FormParam("konsole")
    @NotEmpty
    @Size(min=2, max=40)
    private String konsole;

    @FormParam("genre")
    @NotEmpty
    @Size(min=2, max=40)
    private String genre;


     @FormParam("altersgrenze")
    @NotEmpty
     @Min(value=3)
     @Max(value=18)
     private int altersgrenze;



    private List<Gamer> gamerList;








    /**
     * Gets the titel
     *
     * @return value of titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Sets the titel
     *
     * @param titel the value to set
     */
    public void setTitel(String titel) {
        this.titel = titel;
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
