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
	
	public int distance(Projectile p){
		Robot attaquant = p.getAttaquant();
		Robot cible = p.getCible();
		int distance = Math.sqrt(Math.pow(attaquant.getX()-cible.getX(), 2)+Math.pow(attaquant.getY()-cible.getY(), b));
		return distance;
	}
}
