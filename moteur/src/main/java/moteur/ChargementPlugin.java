package moteur;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import annotation.Graphisme;

public class ChargementPlugin {

	public static void chargementGraphisme(Class pluginClass, FrameWithMenu f) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("draw", new Class[] { Robot.class, Graphics.class });
				// Pour tester, sinon aller chercher tous les robots du jeu.
				if(f.r1.estVivant()) {
					method.invoke(myInstance, new Object[] { f.r1, f.frame.getGraphics() });
				}
				if(f.r2.estVivant()) {
					method.invoke(myInstance, new Object[] { f.r2, f.frame.getGraphics() });
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

				if(f.r1.estVivant()) {
					method.invoke(myInstance, new Object[] { f.r1 });
				}
				if(f.r2.estVivant()) {
					method.invoke(myInstance, new Object[] { f.r2 });
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

				if(f.r1.estVivant()) {
					method.invoke(myInstance, new Object[] { f.frame.getGraphics(), f.r1.getP(), f.r1.getP().getCible() });
				}
				if(f.r2.estVivant()) {
					method.invoke(myInstance, new Object[] { f.frame.getGraphics(), f.r2.getP(), f.r2.getP().getCible() });
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
	
}
