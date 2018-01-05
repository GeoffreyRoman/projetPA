package moteur;

public class RunningGame {
	
	public void run(FrameWithMenu fwm) {
		fwm.showFrame();
		
		while(true) {
			fwm.chargementDeplacement(fwm.deplacement);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		FrameWithMenu fwm = new FrameWithMenu();
		RunningGame rg = new RunningGame();
		rg.run(fwm);

	}

}
