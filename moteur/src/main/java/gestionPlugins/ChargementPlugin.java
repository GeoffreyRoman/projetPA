package gestionPlugins;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import annotation.Graphisme;
import moteur.FrameWithMenu;
import moteur.Projectile;
import moteur.Robot;

public class ChargementPlugin {

	public static void chargementGraphisme(Class pluginClass, FrameWithMenu f) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("draw", new Class[] { Robot.class, Graphics.class });
				// Pour tester, sinon aller chercher tous les robots du jeu.
				for (Robot r : f.lesRobots) {
					if (r.estVivant()) {
						method.invoke(myInstance, new Object[] { r, f.frame.getGraphics() });
					}
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public static void chargementDeplacement(Class pluginClass, FrameWithMenu f) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("deplacement", new Class[] { Robot.class });
				for (Robot r : f.lesRobots) {
					if (r.estVivant()) {
						method.invoke(myInstance, new Object[] { r });
					}
				}
				f.frame.paintComponents(f.frame.getGraphics());
				chargementGraphisme(f.graphisme, f);
				chargementGraphisme(f.barreDeVie, f);
				chargementGraphisme(f.nomRobot, f);

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public static void chargementAttaque(Class pluginClass, FrameWithMenu f) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("attaque", new Class[] { Graphics.class, Projectile.class, Robot.class });
				for (Robot r : f.lesRobots) {
					if (r.estVivant()) {
						method.invoke(myInstance,
								new Object[] { f.frame.getGraphics(), r.getP(), r.getP().getCible() });
					}
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

}
