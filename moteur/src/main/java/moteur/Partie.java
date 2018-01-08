package moteur;

import java.util.ArrayList;

public class Partie {
	private ArrayList<Robot> robots;
	private Class attaque;
	private Class graphisme;
	private Class deplacement;
	private Class barreDeVie;
	private Class nomRobot;
	public Partie(ArrayList<Robot> robots, Class attaque, Class graphisme, Class deplacement,
			Class barreDeVie, Class nomRobot) {
		this.robots = robots;
		this.attaque = attaque;
		this.graphisme = graphisme;
		this.deplacement = deplacement;
		this.barreDeVie = barreDeVie;
		this.nomRobot = nomRobot;
	}
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	public Class getAttaque() {
		return attaque;
	}
	public Class getGraphisme() {
		return graphisme;
	}
	public Class getDeplacement() {
		return deplacement;
	}
	public Class getBarreDeVie() {
		return barreDeVie;
	}
	public Class getNomRobot() {
		return nomRobot;
	}
	
	
	
}
