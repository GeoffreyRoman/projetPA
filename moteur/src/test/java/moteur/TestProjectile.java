package moteur;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TestProjectile {

	@Test
	public void createProjectile() {
		Robot r1 = new Robot(10, 20, 100, 110, Color.RED, "Toto");
		Robot r2 = new Robot(100, 200, 100, 110, Color.BLUE, "Titi");
		Projectile p = new Projectile(r1, r2);
		assertEquals(100, p.getTaille());
		assertEquals(r1, p.getAttaquant());
		assertEquals(r2, p.getCible());
	}

}
