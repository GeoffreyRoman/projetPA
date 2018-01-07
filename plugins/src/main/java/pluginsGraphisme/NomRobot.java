package pluginsGraphisme;

import java.awt.Graphics;

import annotation.Graphisme;
import moteur.Robot;

public class NomRobot {

	@Graphisme(nom = "NomRobot")
	public void draw(Robot r, Graphics g){
		g.setColor(r.getColor());
		g.drawString(r.getNom(), r.getX(), r.getY() + (r.getHeight() - 40));
	}
}
