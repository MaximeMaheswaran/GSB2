import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Modele.Conference;
import Modele.Modele;

class test {

	@Test
	void testconnexionBD() {
		
		Assert.assertNotNull("Echec de la connexion à la base de données" ,Modele.connexionBD());

	}
	
	@Test
	void testdeconnexionBD() {
		Assert.assertNotNull("Echec de la déconnexion à la base de données" , Modele.deconnexionBD());
	}
	
	@Test
	void testverification() {
		Assert.assertEquals("verification() ne fonctionne pas" , true , Modele.verification("mcalvo" , "root"));
		Assert.assertEquals("verification() ne fonctionne pas" , true , Modele.verification("mmaheswaran" , "user"));
	}
	
	@Test
	void testverificationAdmin() {
		Assert.assertEquals("verification() ne fonctionne pas" , true , Modele.verificationAdmin("mcalvo" , "root"));
		Assert.assertEquals("verification() ne fonctionne pas" , false , Modele.verificationAdmin("mmaheswaran" , "user"));
	}
	
	@Test
	void testgetPrenomVerif() {
		Assert.assertEquals("testgetPrenomVerif() ne fonctionne pas", "Maxence", Modele.getPrenomVerif("mcalvo"));
	}
			
	@Test
	void testgetNbVisiteur() {
		Assert.assertEquals("getNbVisiteur() ne fonctionne pas", 27 , Modele.getNbVisiteur());
	}
	
	@Test
	void testgetNbPresentation() {
		Assert.assertEquals("getNbPresentation() ne fonctionne pas" , 0 , Modele.getNbPresentation());
	}
	
	@Test
	void testgetNbConference() {
		Assert.assertEquals("getNbConference() ne fonctionne pas" , 0 , Modele.getNbConference());
	}
		
	@Test
	void testgetLesConferences() {
		ArrayList<Conference> list = Modele.getLesConferences();
		Assert.assertEquals("getLesConferences() ne fonctionne pas", 0 , list.size());		
	}
	
	@Test
	void testgetLesPresentation() {
		Assert.assertEquals("getLesPresentation() ne fonctionne pas", 0 , Modele.getLesPresentations(0).size());
	}
		
}
