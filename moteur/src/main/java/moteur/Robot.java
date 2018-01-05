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
	
	public int getEnergie(){
		return energie;
	}
	
	public void setEnergie(int energie){
		this.energie = energie;
	}
	
	public void enleveVie(int degat){
		this.setVie(this.getVie() - degat);
	}
	
	public void consomationEnergie(int consomationEnergie){
		this.setEnergie(this.getEnergie()-consomationEnergie);
	}
	
	/**
	 * Methode permettant de savoir si un robot est touche par un projectile
	 * @param posXDebut
	 * @param posYDebut
	 * @param posXFin
	 * @param posYFin
	 * @param trajectoire
	 * @return boolean touche = true / non touche = false
	 */
	public boolean robotTouche(double posXDebut, double posYDebut, double posXFin,
			double posYFin, int trajectoire) {
			boolean result = false;
			switch(trajectoire)
			{
				case 0: // Le robot attaque a droite
					result = (((x+50) <= posXFin) && (x >= posXDebut)) && ((y <= posYFin) && ((y+50) >= posYFin));
					break;
				case 1: // Le robot attaque en bas
					result = ((posXFin <= (x + 50)) && (posXFin >= (x))) && (((y+50) <= posYFin) && (y >= posYDebut));
					break;
				case 2: // Le robot attaque a gauche
					result = (((x+50) <= posXDebut) && (x >= posXFin)) && ((y <= posYFin) && ((y+50) >= posYFin));
					break;
				case 3: // Le robot attaque en haut
					result = ((posXFin <= (x + 50)) && (posXFin >= (x))) && ((y >= posYFin) && ((y+50) <= posYDebut));
					break;
			}
			return result;
	}
}