package plugins;

import java.awt.Color;
import java.awt.Graphics;


import annotation.Graphisme;
import moteur.Robot;

public class GraphismeComplique {
	@Graphisme(nom = "GraphismeComplique")
	public void draw(Robot r,Graphics g){
		g.setColor(r.getColor());
		g.fillRect(r.getX(), r.getY(), 40, 60);
		g.fillRect(r.getX()-15, r.getY()-10, 15, 80);
		g.fillRect(r.getX()+40, r.getY()-10, 15, 80);
		g.setColor(Color.GRAY);
		g.fillOval(r.getX()+5, r.getY()+15, 30, 30);
		g.drawLine(r.getX()+20, r.getY()+20, r.getX()+20, r.getY()-10);
	}
}
