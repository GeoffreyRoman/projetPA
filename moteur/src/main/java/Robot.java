

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Robot {
	int x, y, widht, height;
	Color c;
	String nom;
	Graphics g;

	public Robot(int x, int y, int widht, int height, Color c, String nom) {
		this.x = x;
		this.y = y;
		this.widht = widht;
		this.height = height;
		this.c = c;
		this.nom = nom;
	}
	
	public void setGraphics(Graphics g) {
		this.g = g;
	}

	public void drawRobot() {
		g.setColor(c);
		g.drawRect(x, y, widht, height);
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidht() {
		return widht;
	}

	public int getHeight() {
		return height;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}