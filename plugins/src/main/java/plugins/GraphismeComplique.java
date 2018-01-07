package plugins;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Robot;

public class GraphismeComplique {
	@Graphisme(nom = "GraphismeComplique")
	public void draw(Robot r,Graphics g){
		g.setColor(r.getColor());
		g.fillRect(x, y, 40, 60);
		g.fillRect(x-15, y-10, 15, 80);
		g.fillRect(x+40, y-10, 15, 80);
		g.setColor(Color.GRAY);
		g.fillOval(x+5, y+15, 30, 30);
		g.drawLine(x+20, y+20, x+20, y-10);
	}
}
