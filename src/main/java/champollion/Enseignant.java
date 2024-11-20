package champollion;

import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Personne {
    private final List<ServicePrevu> servicesPrevus = new ArrayList<>();
    private final List<Intervention> interventions = new ArrayList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        servicesPrevus.add(new ServicePrevu(ue, volumeCM, volumeTD, volumeTP));
    }

    public int heuresPrevues() {
        int total = 0;
        for (ServicePrevu service : servicesPrevus) {
            total += service.getVolumeCM() * 1.5 + service.getVolumeTD() + service.getVolumeTP() * 0.75;
        }
        return (int) Math.round(total);
    }

    public int heuresPrevuesPourUE(UE ue) {
        int total = 0;
        for (ServicePrevu service : servicesPrevus) {
            if (service.getUE().equals(ue)) {
                total += service.getVolumeCM() * 1.5 + service.getVolumeTD() + service.getVolumeTP() * 0.75;
            }
        }
        return (int) Math.round(total);
    }

    public void ajouteIntervention(Intervention intervention) {
        interventions.add(intervention);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) {
        int totalPrevu = 0;
        int totalPlanifie = 0;

        for (ServicePrevu service : servicesPrevus) {
            if (service.getUE().equals(ue)) {
                switch (type) {
                    case CM -> totalPrevu += service.getVolumeCM();
                    case TD -> totalPrevu += service.getVolumeTD();
                    case TP -> totalPrevu += service.getVolumeTP();
                }
            }
        }

        for (Intervention intervention : interventions) {
            if (intervention.getUE().equals(ue) && intervention.getType() == type) {
                totalPlanifie += intervention.getDuree();
            }
        }

        return totalPrevu - totalPlanifie;
    }

    public boolean enSousService() {
        return heuresPrevues() < 192;
    }
}
