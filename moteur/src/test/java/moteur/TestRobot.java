package moteur;

import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Test;

public class TestRobot {

	/**
	 * Simple test pour la création d'un robot
	 */
	@Test
	public void createRobot() {
		Robot r = new Robot(10, 20, 100, 110, Color.RED, "Toto");
		assertEquals(10, r.getX());
		assertEquals(20, r.getY());
		assertEquals(100, r.getWidht());
		assertEquals(110, r.getHeight());
		assertEquals(Color.RED, r.getColor());
		assertEquals("Toto", r.getNom());
	}

	@Test
	public void vieRobot(){
		Robot r = new Robot(10, 20, 100, 110, Color.RED, "Toto");
		assertEquals(10, r.getVie());
		assertEquals(true, r.estVivant());
		r.enleveVie(10);
		assertEquals(0, r.getVie());
		assertEquals(false, r.estVivant());
	}
	
	@Test
	public void energieRobot(){
		Robot r = new Robot(10, 20, 100, 110, Color.RED, "Toto");
		assertEquals(3, r.getEnergie());
		r.consomationEnergie(3);
		assertEquals(0, r.getEnergie());
	}
	
	@Test
	public void adversaireRobot(){
		Robot r1 = new Robot(10, 20, 100, 110, Color.RED, "Toto");
		Robot r2 = new Robot(100, 200, 100, 110, Color.BLUE, "Titi");
		Robot.attributionAdversaire(r1, r2);
		assertEquals("Titi", r1.getAdversaire().getNom());
		assertEquals("Toto", r2.getAdversaire().getNom());
	}
}
