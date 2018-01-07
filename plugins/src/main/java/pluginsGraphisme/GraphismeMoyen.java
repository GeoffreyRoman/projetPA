package pluginsGraphisme;
import java.awt.Graphics;

import annotation.Graphisme;
import moteur.Robot;

public class GraphismeMoyen {
	
	// Un robot simple est un simple rectangle
	@Graphisme(nom = "GraphismeMoyen")
	public void draw(Robot r, Graphics g){
		g.setColor(r.getColor());
		g.fillRect(r.getX(), r.getY(), 50, 50);
		g.fillRect(r.getX()-10, r.getY()+50, 70, 70);
		g.fillRect(r.getX()-8, r.getY()+120, 30, 30);
		g.fillRect(r.getX()+28, r.getY()+120, 30, 30);
		
		g.fillRect(r.getX()-30, r.getY()+70, 30, 30);
		g.fillRect(r.getX()+50, r.getY()+70, 30, 30);
	}

}
