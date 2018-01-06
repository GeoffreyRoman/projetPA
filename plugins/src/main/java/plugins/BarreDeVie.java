package plugins;

import java.awt.Color;
import java.awt.Graphics;

import annotation.Graphisme;
import moteur.Robot;

public class BarreDeVie {
	
	@Graphisme(nom = "BarreDeVie")
	public void draw(Robot r, Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(r.getX(), r.getY() - 10, r.getVie()*5, 5);
	}
	
}
