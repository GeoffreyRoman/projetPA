import java.awt.Graphics;

import moteur.Robot;

public class GraphismeSimple {
	
	// Un robot simple est un simple rectangle
	@Graphisme(nom = "GraphismeSimple")
	public void draw(Robot r, Graphics g){
		g.setColor(r.getColor());
		g.fillRect(r.getX(), r.getY(), 50, 50);
	}

}
