package moteur;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Robot {
	int x, y, widht, height, vie, energie;
	Color c;
	String nom;
	Graphics g;
	Robot adversaire;

	public Robot(int x, int y, int widht, int height, Color c, String nom) {
		this.vie = 100;
		this.energie = 3;
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

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}
	
	public void enleveVie(int degat){
		this.setVie(this.getVie() - degat);
	}
	
	public void enleveEnergie(int baisse){
		this.setEnergie(this.getEnergie() - baisse);
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}
}