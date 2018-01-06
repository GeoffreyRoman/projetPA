package moteur;

import java.awt.Color;

public class Launch {

	public void run(FrameWithMenu fwm) {
		fwm.showFrame();
		while (true) {
			ChargementPlugin.chargementDeplacement(fwm.deplacement, fwm);
			ChargementPlugin.chargementAttaque(fwm.attaque, fwm);
			ChargementPlugin.chargementGraphisme(fwm.graphisme, fwm);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Robot r1 = new Robot(50, 50, 20, 20, Color.RED, "Robot 1");
		Robot r2 = new Robot(100, 100, 20, 20, Color.BLUE, "Robot 2");
		Robot.attributionAdversaire(r1, r2);
		FrameWithMenu fwm = new FrameWithMenu(r1, r2);
		Launch l = new Launch();
		l.run(fwm);

	}

}
