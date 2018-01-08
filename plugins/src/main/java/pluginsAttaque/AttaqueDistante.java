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
}
