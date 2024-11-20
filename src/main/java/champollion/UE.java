package champollion;

public class UE {
    private final String intitule;
    private int heuresCM;
    private int heuresTD;
    private int heuresTP;

    public UE(String intitule) {
        this.intitule = intitule;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getHeuresCM() {
        return heuresCM;
    }

    public void setHeuresCM(int heuresCM) {
        this.heuresCM = heuresCM;
    }

    public int getHeuresTD() {
        return heuresTD;
    }

    public void setHeuresTD(int heuresTD) {
        this.heuresTD = heuresTD;
    }

    public int getHeuresTP() {
        return heuresTP;
    }

    public void setHeuresTP(int heuresTP) {
        this.heuresTP = heuresTP;
    }
}
