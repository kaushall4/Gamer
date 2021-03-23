package ch.bzz.gamerList.model;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

public class Gamer {
   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
   @FormParam("gamerUUID")
    private String gamerUUID;

    @FormParam("nachname")
    @NotEmpty
    @Size(min=2, max=40)
    private String nachname;

  @FormParam("vorname")
    @NotEmpty
    @Size(min=2, max=40)
  private String vorname;



    @FormParam("alter")
  //  @NotEmpty
    @Min(value=1)
    @Max(value=120)
    private int alter;

    private Spiel spiel;

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
    public Spiel getSpiel() {
        return spiel;
    }
    /**
     * Sets the spiel
     *
     * @param spiel =  the value to set
     */

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    /**
     * Gets the GamerUUID
     *
     * @return value of gamerUUID
     */

    public String getGamerUUID() {
        return gamerUUID;
    }

    /**
     * Sets the gamerUUID
     *
     * @param gamerUUID =  the value to set
     */
    public void setGamerUUID(String gamerUUID) {
        this.gamerUUID = gamerUUID;
    }
}
