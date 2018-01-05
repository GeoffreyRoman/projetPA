package moteur;

public class Projectile {
	
	private int x, y, taille, direction;
	private Robot attaquant, cible;
	
	public Projectile(Robot attaquant,Robot cible) {
		this.taille = 100;
		this.attaquant = attaquant;
		this.cible = cible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTaille() {
		return taille;
	}

	public int getDirection() {
		return direction;
	}

	public Robot getAttaquant() {
		return attaquant;
	}

	public Robot getCible() {
		return cible;
	}
	
}
