package pluginsAttaque;

import java.awt.Color;
import java.awt.Graphics;

import annotation.Attaque;
import moteur.Projectile;
import moteur.Robot;

public class AttaqueDistante {
	
	private static final int degatMini = 1;
	private static final int degatMaxi = 2;
	
	@Attaque(nom = "AttaqueDistante")
	public void attaqueDistante(Graphics gr, Projectile p, Robot cible){
		gr.setColor(Color.ORANGE);
		if(sousPorteeDeTir(p)){
			int trajectoire;
			if (cible.getX() >= p.getAttaquant().getX() - 50 && cible.getX() < p.getAttaquant().getX()  + 50
					&& (p.getAttaquant().getEnergie() - 2) >= 0) {
				/**
				 * Si la cible est en dessous du tireur
				 */
				if (cible.getY() <= p.getAttaquant().getY() + 500 && cible.getY() > p.getAttaquant().getY() - 50) {
					p.getAttaquant().consomationEnergie(1);
					trajectoire = 1; // Le projectile par vers le bas
					p.setX(p.getAttaquant().getX() + 25);
					p.setY(p.getAttaquant().getY() + 50);
					gr.drawLine(p.getAttaquant().getX(), p.getAttaquant().getY(), p.getCible().getX(), p.getCible().getY());
					if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX(), p.getY() + distance(p), trajectoire)) {
						toucher(p);
						System.out.println(p.getAttaquant().getNom() + " touche " + cible.getNom() + " en tirant vers le bas !");
					}
				/**
				 * Si la cible est au dessus du tireur
				 */
				} else if (cible.getY() > p.getAttaquant().getY() - 500 && cible.getY() < p.getAttaquant().getY()) {
					trajectoire = 3; // Le projectile part vers le haut
					p.setX(p.getAttaquant().getX() + 25);
					p.setY(p.getAttaquant().getY());
					gr.drawLine(p.getAttaquant().getX(), p.getAttaquant().getY(), p.getX(), p.getCible().getY());
					if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX(), p.getY() - distance(p), trajectoire)) {
						toucher(p);
						System.out.println(p.getAttaquant().getNom() + " touche " + cible.getNom() + " en tirant vers le haut !");
					}
				}
			}
			if (cible.getY() >= p.getAttaquant().getY() - 50 && cible.getY() < p.getAttaquant().getY() + 50
					&& (p.getAttaquant().getEnergie() - 2) >= 0) {
				/**
				 * Si la cible se trouve a droite du tireur
				 */
				if (cible.getX() <= p.getAttaquant().getX() + 500 && cible.getX() > p.getAttaquant().getX() + 50) {
					p.getAttaquant().consomationEnergie(1);
					trajectoire = 0; // Le projectile part a droite
					p.setX(p.getAttaquant().getX() + 50);
					p.setY(p.getAttaquant().getY() + 25);
					gr.drawLine(p.getAttaquant().getX(), p.getAttaquant().getY(), p.getCible().getX(), p.getCible().getY());
					if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX() + distance(p), p.getY(), trajectoire)) {
						toucher(p);
						System.out.println(p.getAttaquant().getNom() + " touche " + cible.getNom() + " en tirant vers la droite !");
					}
				/**
				 * Si la cible se trouve a gauche du tireur
				 */
				} else if (cible.getX() > p.getAttaquant().getX() - 500 && cible.getX() < p.getAttaquant().getX()) {
					trajectoire = 2; // Le projectile part a gauche
					p.setX(p.getAttaquant().getX());
					p.setY(p.getAttaquant().getY() + 25);
					gr.drawLine(p.getAttaquant().getX(), p.getAttaquant().getY(), p.getCible().getX(), p.getCible().getY());
					if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX() - distance(p), p.getY(), trajectoire)) {
						toucher(p);
						System.out.println(p.getAttaquant().getNom() + " touche " + cible.getNom() + " en tirant vers la gauche !");
					}
				}
			}
		}
	}
	
	public void toucher(Projectile p) {
		p.getCible().enleveVie((int) (Math.random() * (degatMaxi - degatMini)) + degatMini);
	}
	/**
	 * 
	 * @param p
	 * @return La distance entre le robot attaquant et le robot cible
	 */
	public boolean sousPorteeDeTir(Projectile p){
		if(distance(p) <= 500){
			return true;
		}
		return false;
	}
	
	public double distance(Projectile p){
		Robot attaquant = p.getAttaquant();
		Robot cible = p.getCible();
		double distance = Math.sqrt(Math.pow(attaquant.getX()-cible.getX(), 2)+Math.pow(attaquant.getY()-cible.getY(), 2));
		return distance;
	}
}
