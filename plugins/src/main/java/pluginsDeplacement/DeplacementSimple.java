package pluginsDeplacement;

import java.util.Random;

import annotation.Deplacement;
import moteur.Robot;

@Deplacement(nom = "DeplacementSimple")
public class DeplacementSimple {

	public void deplacement(Robot r) {
		int arenaXMax = 450;
		int arenaYMax = 450;
		Random rnd = new Random();
		int newX = rnd.nextInt(40);
		int newY = rnd.nextInt(40);

		if (newX + r.getX() > arenaXMax) {
			r.setX(r.getX() - newX);
		} else if (newX + r.getX() < 100) {
			r.setX(r.getX() + newX);
		} else {
			r.setX(r.getX() + (newX * (-1 + ((newX % 2) * 2))));
		}

		if (newY + r.getY() > arenaYMax) {
			r.setY(r.getY() - newY);
		} else if (newY + r.getY() < 100) {
			r.setY(r.getY() + newY);
		} else {
			r.setY(r.getY() + (newY * (-1 + ((newY % 2) * 2))));
		}
		
		/**
		 * Le robot a une chance de regagner son energie
		*/
		int chance = 20; //Facteur chance : plus ce nombre est grand moins le robot n'a de chance de recuperer son energie
		if(rnd.nextInt(chance) == 1){
			r.gainEnergie();
		}
	}
}
