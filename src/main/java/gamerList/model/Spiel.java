package gamerList.model;

public class Spiel {
    private String spiel;

    public String getSpiel() {
        return spiel;
    }

    public void setSpiel(String spiel) {
        this.spiel = spiel;
    }

    public String getKonsole() {
        return konsole;
    }

    public void setKonsole(String konsole) {
        this.konsole = konsole;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAltersgrenze() {
        return altersgrenze;
    }

    public void setAltersgrenze(int altersgrenze) {
        this.altersgrenze = altersgrenze;
    }

    private String konsole;
    private String genre;
    private int altersgrenze;
}
