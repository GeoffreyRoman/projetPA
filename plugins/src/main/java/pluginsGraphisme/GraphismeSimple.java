package pluginsGraphisme;
import java.awt.Graphics;

import annotation.Graphisme;
import moteur.Robot;

@Graphisme(nom = "GraphismeSimple")
public class GraphismeSimple {
	
	// Un robot simple est un simple rectangle
	public void draw(Robot r, Graphics g){
		g.setColor(r.getColor());
		g.fillRect(r.getX(), r.getY(), 50, 50);
	}

}
