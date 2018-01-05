package moteur;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;

import annotation.Graphisme;

public class ChargementPlugin {

	public void chargementGraphisme(Class pluginClass, Robot r, JFrame frame) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("draw", new Class[] { Robot.class, Graphics.class });
				// Pour tester, sinon aller chercher tous les robots du jeu.
				method.invoke(myInstance, new Object[] { r, frame.getGraphics() });
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public void chargementDeplacement(Class pluginClass, Robot r) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("deplacement", new Class[] { Robot.class });

				method.invoke(myInstance, new Object[] { r });

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void chargementAttaque(Class pluginClass, Projectile p, Robot cible, JFrame frame) {
		if (pluginClass != null) {
			Object myInstance;
			try {
				myInstance = pluginClass.getConstructors()[0].newInstance();
				Method method = pluginClass.getMethod("attaque", new Class[] { Graphics.class, Projectile.class, Robot.class });

				method.invoke(myInstance, new Object[] { frame.getGraphics(), p, cible });

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	
}
