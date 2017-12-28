
import java.util.Random;


public class DeplacementSimple {

	@Deplacement(nom = "DeplacementSimple")
	public void deplacement(Robot r) {
		int arenaXMax = 450;
		int arenaYMax = 450;
		Random rnd = new Random();
		int newX = rnd.nextInt(40);
		int newY = rnd.nextInt(40);

		if (newX + r.getX() > arenaXMax) {
			r.setX(r.getX() - newX);
		} else if (newX + r.getX() < 50) {
			r.setX(r.getX() + newX);
		} else {
			r.setX(r.getX() + (newX * (-1 + ((newX % 2) * 2))));
		}

		if (newY + r.getY() > arenaYMax) {
			r.setY(r.getY() - newY);
		} else if (newY + r.getY() < 50) {
			r.setY(r.getY() + newY);
		} else {
			r.setY(r.getY() + (newY * (-1 + ((newY % 2) * 2))));
		}
	}
}
