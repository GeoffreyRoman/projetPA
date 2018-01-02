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

}
