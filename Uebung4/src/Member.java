import java.util.List;

public interface Member {

    /**
     * ID ist über einen Konstruktor einer abgeleiteten Klasse
     * explizit außerhalb der Container-Klasse zu belegen
     *  -> Primärschlüssel zur Unterscheidung aller Member-Objekte
     */
    
    Integer getID();

    public String toString();

    public void addExpertise(List<Expertise> expertise);

    public String getName();

    public String getVorName();

    public String getRolle();

    public String getAbteilung();

    public int getAnzahlExpertisen();

}
