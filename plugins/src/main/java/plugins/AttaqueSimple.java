package plugins;

import java.awt.Graphics;

import annotation.Attaque;
import moteur.Projectile;
import moteur.Robot;

public class AttaqueSimple {

	private static final int degatMini = 10;
	private static final int degatMaxi = 30;
	private GraphismeAttaque g;
	
	public AttaqueSimple() {
		g = new GraphismeAttaque();
	}

	@Attaque(nom = "AttaqueSimple")
	public void attaque(Graphics gr, Projectile p, Robot cible) {
		int trajectoire;
		if (cible.getX() >= p.getAttaquant().getX() - 50 && cible.getX() < p.getAttaquant().getX() + 50
				&& (p.getAttaquant().getEnergie() - 2) >= 0) {
			p.getAttaquant().consomationEnergie(1);
			/**
			 * Si la cible est en dessous du tireur
			 */
			if (cible.getY() <= p.getAttaquant().getY() + 200 && cible.getY() > p.getAttaquant().getY() - 50) {
				trajectoire = 1; // Le projectile par vers le bas
				p.setX(p.getAttaquant().getX() + 25);
				p.setY(p.getAttaquant().getY() + 50);
				g.drawAttaque(gr, p, trajectoire);
				if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX(), p.getY() + p.getTaille(), trajectoire)) {
					toucher(p);
				}
			/**
			 * Si la cible est au dessus du tireur
			 */
			} else if (cible.getY() > p.getAttaquant().getY() - 150 && cible.getY() < p.getAttaquant().getY()) {
				trajectoire = 3; // Le projectile part vers le haut
				p.setX(p.getAttaquant().getX() + 25);
				p.setY(p.getAttaquant().getY());
				g.drawAttaque(gr, p, trajectoire);
				if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX(), p.getY() - p.getTaille(), trajectoire)) {
					toucher(p);
				}
			}
		}
		if (cible.getY() >= p.getAttaquant().getY() - 50 && cible.getY() < p.getAttaquant().getY() + 50
				&& (p.getAttaquant().getEnergie() - 2) >= 0) {
			p.getAttaquant().consomationEnergie(1);
			/**
			 * Si la cible se trouve a droite du tireur
			 */
			if (cible.getX() <= p.getAttaquant().getX() + 200 && cible.getX() > p.getAttaquant().getX() + 50) {
				trajectoire = 0; // Le projectile part a droite
				p.setX(p.getAttaquant().getX() + 50);
				p.setY(p.getAttaquant().getY() + 25);
				g.drawAttaque(gr, p, trajectoire);
				if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX() + p.getTaille(), p.getY(), trajectoire)) {
					toucher(p);
				}
			/**
			 * Si la cible se trouve a gauche du tireur
			 */
			} else if (cible.getX() > p.getAttaquant().getX() - 150 && cible.getX() < p.getAttaquant().getX()) {
				trajectoire = 2; // Le projectile part a gauche
				p.setX(p.getAttaquant().getX());
				p.setY(p.getAttaquant().getY() + 25);
				g.drawAttaque(gr, p, trajectoire);
				if (p.getCible().robotTouche(p.getX(), p.getY(), p.getX() - p.getTaille(), p.getY(), trajectoire)) {
					toucher(p);
				}
			}
		}
	}

	public void toucher(Projectile p) {
		p.getCible().enleveVie((int) (Math.random() * (degatMaxi - degatMini)) + degatMini);
	}

}
