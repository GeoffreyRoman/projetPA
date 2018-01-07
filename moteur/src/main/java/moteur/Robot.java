package moteur;

import java.awt.Color;
import java.awt.Graphics;

public class Robot {
	int x, y, widht, height, vie, energie;
	Color c;
	String nom;
	Graphics g;
	Robot adversaire;
	Projectile p;

	public Robot(int x, int y, int widht, int height, Color c, String nom) {
		this.vie = 10;
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

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public Projectile getP() {
		return p;
	}

	public Robot getAdversaire() {
		return adversaire;
	}

	/**
	 * Methode permettant de ritirer de la vie d'un robot
	 * 
	 * @param degat
	 */
	public void enleveVie(int degat) {
		this.setVie(this.getVie() - degat);
	}
	
	public boolean estVivant() {
		return this.getVie() > 0;
	}

	/**
	 * Methode permettant de consommer l'energie d'un robot
	 * 
	 * @param consomationEnergie
	 */
	public void consomationEnergie(int consomationEnergie) {
		this.setEnergie(this.getEnergie() - consomationEnergie);
	}

	/**
	 * Methode permettant a un Robot de recharger son energie
	 */
	public void gainEnergie() {
		if (this.getEnergie() < 3) {
			System.out.println(this.getNom() + " vient de gagner 1 point d'energie.");
			setEnergie(this.getEnergie() + 1);
		}
	}

	/**
	 * Methode permettant de savoir si un robot est touche par un projectile
	 * 
	 * @param posXDebut
	 * @param posYDebut
	 * @param posXFin
	 * @param posYFin
	 * @param trajectoire
	 * @return boolean touche = true / non touche = false
	 */
	public boolean robotTouche(double posXDebut, double posYDebut, double posXFin, double posYFin, int trajectoire) {
		boolean result = false;
		switch (trajectoire) {
		case 0: // Le robot attaque a droite
			result = (((this.x + 50) <= posXFin) && (this.x >= posXDebut))
					&& ((this.y <= posYFin) && ((this.y + 50) >= posYFin));
			break;
		case 1: // Le robot attaque en bas
			result = ((posXFin <= (this.x + 50)) && (posXFin >= (this.x)))
					&& (((this.y + 50) <= posYFin) && (this.y >= posYDebut));
			break;
		case 2: // Le robot attaque a gauche
			result = (((this.x + 50) <= posXDebut) && (this.x >= posXFin))
					&& ((this.y <= posYFin) && ((this.y + 50) >= posYFin));
			break;
		case 3: // Le robot attaque en haut
			result = ((posXFin <= (this.x + 50)) && (posXFin >= (this.x)))
					&& ((this.y >= posYFin) && ((this.y + 50) <= posYDebut));
			break;
		}
		return result;
	}

	/**
	 * Methode permettant de s'affronter
	 * 
	 * @param r
	 */
	public static void attributionAdversaire(Robot r1, Robot r2) {
		r1.adversaire = r2;
		r2.adversaire = r1;
		r1.p = new Projectile(r1, r1.adversaire);
		r2.p = new Projectile(r2, r2.adversaire);
	}
}