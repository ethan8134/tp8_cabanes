package champollion;

import java.util.Date;

public class Intervention {
    private final Date debut;
    private final int duree;
    private boolean annule = false;
    private final int heureDebut;
    private final TypeIntervention type;
    private final Salle salle;
    private final UE ue;

    public Intervention(Date debut, int duree, int heureDebut, TypeIntervention type, Salle salle, UE ue) {
        this.debut = debut;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.type = type;
        this.salle = salle;
        this.ue = ue;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnule() {
        return annule;
    }

    public void setAnnule(boolean annule) {
        this.annule = annule;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public TypeIntervention getType() {
        return type;
    }

    public Salle getSalle() {
        return salle;
    }

    public UE getUE() {
        return ue;
    }
}
