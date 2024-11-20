package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Salle salle;

	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
		salle = new Salle("Salle 101", 30);
	}

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
				"Un nouvel enseignant doit avoir 0 heures prévues");
	}

	@Test
	public void testAjouteHeures() {
		// Ajouter 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);
		assertEquals(10, untel.heuresPrevuesPourUE(uml),
				"L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

		// Ajouter 20h TD supplémentaires pour UML
		untel.ajouteEnseignement(uml, 0, 20, 0);
		assertEquals(30, untel.heuresPrevuesPourUE(uml),
				"L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");
	}

	@Test
	public void testAjouteIntervention() {
		Intervention intervention = new Intervention(new Date(), 2, 8, TypeIntervention.TD, salle, uml);
		untel.ajouteIntervention(intervention);

		assertDoesNotThrow(() -> untel.ajouteIntervention(intervention),
				"Ajout d'une intervention ne doit pas lever d'exception.");
	}

	@Test
	public void testEnSousService() {
		// Vérifier si l'enseignant est en sous-service avec moins de 192 heures
		untel.ajouteEnseignement(uml, 5, 5, 5);
		assertTrue(untel.enSousService(),
				"Un enseignant avec moins de 192 heures prévues doit être en sous-service.");

		// Ajouter des heures pour dépasser les 192 heures
		untel.ajouteEnseignement(uml, 100, 50, 50);
		assertFalse(untel.enSousService(),
				"Un enseignant avec 192 heures ou plus prévues ne doit pas être en sous-service.");
	}

	@Test
	public void testVolumeHeuresAjoute() {
		// Ajouter des heures pour UML
		untel.ajouteEnseignement(uml, 10, 20, 30);  // 10h CM, 20h TD, 30h TP

		// Vérifier que les heures sont correctement enregistrées pour chaque type
		assertEquals(10, untel.resteAPlanifier(uml, TypeIntervention.CM),
				"Le volume d'heures pour les CM doit être correctement enregistré.");
		assertEquals(20, untel.resteAPlanifier(uml, TypeIntervention.TD),
				"Le volume d'heures pour les TD doit être correctement enregistré.");
		assertEquals(30, untel.resteAPlanifier(uml, TypeIntervention.TP),
				"Le volume d'heures pour les TP doit être correctement enregistré.");
	}

	@Test
	public void testSousServiceAvecAjoutHeures() {
		// L'enseignant commence avec 0 heures prévues
		assertTrue(untel.enSousService(), "L'enseignant doit être en sous-service avec 0 heures.");

		// Ajouter des heures
		untel.ajouteEnseignement(uml, 50, 60, 30);  // 50h CM, 60h TD, 30h TP
		untel.ajouteEnseignement(java, 30, 40, 30);  // 30h CM, 40h TD, 30h TP

		// Vérifier que l'enseignant n'est plus en sous-service après l'ajout de 210 heures
		assertFalse(untel.enSousService(), "L'enseignant ne doit plus être en sous-service après l'ajout de 210 heures.");
	}


}
