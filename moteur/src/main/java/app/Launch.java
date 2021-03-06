package app;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import gestionPlugins.ChargementPlugin;
import moteur.FrameWithMenu;
import moteur.Robot;

public class Launch {

	public void run(FrameWithMenu fwm) {
		fwm.showFrame();
		Random rnd = new Random();
		int x = 1;
		while (true) {
			x = rnd.nextInt(2);
			ChargementPlugin.chargementDeplacement(fwm.deplacement, fwm);
			if(x == 1) {
				ChargementPlugin.chargementAttaque(fwm.attaque, fwm);
			}
			ChargementPlugin.chargementGraphisme(fwm.graphisme, fwm);
			
			/**
			 * Test pour verifier si un robot est mort
			 */
			for(Robot r : fwm.lesRobots){
				if(!r.estVivant()){
					FrameWithMenu.finishGame(r);
					FrameWithMenu.closeFrame(fwm.frame);
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ArrayList<Robot> mesRobots = new ArrayList<>();
		Robot r1 = new Robot(50, 50, 20, 20, Color.RED, "Robot 1");
		Robot r2 = new Robot(400, 400, 20, 20, Color.BLUE, "Robot 2");
		Robot.attributionAdversaire(r1, r2);
		mesRobots.add(r1);
		mesRobots.add(r2);
		FrameWithMenu fwm = new FrameWithMenu(mesRobots);
		Launch l = new Launch();
		l.run(fwm);

	}

}
