package plugins;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import moteur.Robot;
import pluginsDeplacement.DeplacementSimple;

public class TestDeplacementSimple {

	/**
	 * Test de la methode deplacement
	 */
	@Test
	public void testDeplacementAleatoire() {
		Robot r = new Robot(50, 50, 20, 20, Color.RED, "Robot1");
		DeplacementSimple d = new DeplacementSimple();
		d.deplacement(r);
		assertNotEquals(50, r.getX());
		assertNotEquals(50, r.getY());
	}

}
