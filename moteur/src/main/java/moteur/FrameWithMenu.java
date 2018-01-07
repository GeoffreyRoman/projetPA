package moteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import annotation.Graphisme;
import pluginsAttaque.AttaqueSimple;
import pluginsDeplacement.DeplacementSimple;
import pluginsGraphisme.GraphismeSimple;

public class FrameWithMenu {
	JFrame frame;
	private JPanel contentPane;
	Robot r1;
	Robot r2;

	Class attaque;
	Class graphisme;
	Class deplacement;
	Class barreDeVie;
	Class nomRobot;

	FrameWithMenu(Robot r1,Robot r2) {
		this.r1 = r1;
		this.r2 = r2;
		attaque = new AttaqueSimple().getClass();
		deplacement = new DeplacementSimple().getClass();
		graphisme = new GraphismeSimple().getClass();
	}
	

	void showFrame() {
		if (frame == null) {
			frame = new JFrame("Robot War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 900, 900);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			frame.setContentPane(contentPane);
		}
		buildMenu();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	void buildMenu() {
		FrameWithMenu fwm = this;
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		JMenu fileM = new JMenu("Fichier");
		JMenu menuDynamic = new JMenu("Plugins Graphiques");
		bar.add(fileM);

		fileM.add(new AbstractAction("Save") {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("DoSaveAction:" + arg0);
			}
		});

		bar.add(menuDynamic);

		File file = new File("../plugins/target");
		final Repository r = new Repository(file);

		try {
			r.load();
//			List<Class> attaqueClass = r.getListePluginsAttaque();
//			List<Class> deplacementClass = r.getListePluginsDeplacment();
			List<Class> graphismeClass = r.getListePluginsGraphisme();

//			for (final Class<?> classe : attaqueClass) {
//				menuDynamic.add(new AbstractAction(classe.getName()) {
//					public void actionPerformed(ActionEvent arg0) {
//						System.out.println("Click sur plugin attaque : " + classe.getName());
//					}
//
//				});
//			}
//			for (final Class<?> classe : deplacementClass) {
//				menuDynamic.add(new AbstractAction(classe.getName()) {
//					public void actionPerformed(ActionEvent arg0) {
//						chargementDeplacement(classe);
//						System.out.println("Click sur plugin deplacement : " + classe.getName());
//					}
//
//				});
//			}
			for (final Class<?> classe : graphismeClass) {
				menuDynamic.add(new AbstractAction(classe.getName()) {
					public void actionPerformed(ActionEvent arg0) {
						chargementGraphisme(classe);
						System.out.println("Click sur plugin graphisme : " + classe.getName());
					}

				});
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void chargementGraphisme(Class classe) {
		if (classe != null) {
			Object myInstance;
			try {
				myInstance = classe.getConstructors()[0].newInstance();
				Method method = classe.getMethod("draw", new Class[] { Robot.class, Graphics.class });
				// Pour tester, sinon aller chercher tous les robots du jeu.

				if (method.getAnnotation(Graphisme.class).nom().equals("BarreDeVie")) {
					barreDeVie = classe;
				} else if(method.getAnnotation(Graphisme.class).nom().equals("NomRobot")){
					nomRobot = classe;
				} else {
					this.graphisme = classe;
				}
				if(r1.estVivant()) {
					method.invoke(myInstance, new Object[] { r1, frame.getGraphics() });
				}
				if(r2.estVivant()) {
					method.invoke(myInstance, new Object[] { r2, frame.getGraphics() });
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	public void chargementDeplacement(Class classe) {
		if (classe != null) {
			this.deplacement = classe;
			Object myInstance;
			try {
				myInstance = classe.getConstructors()[0].newInstance();
				Method method = classe.getMethod("deplacement", new Class[] { Robot.class });

				if(r1.estVivant()) {
					method.invoke(myInstance, new Object[] { r1 });
				}
				if(r2.estVivant()) {
					method.invoke(myInstance, new Object[] { r2 });
				}
				frame.paintComponents(frame.getGraphics());
				chargementGraphisme(graphisme);
				chargementGraphisme(barreDeVie);
				chargementGraphisme(nomRobot);

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | SecurityException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void main(String[] args) {
//		FrameWithMenu fwm = new FrameWithMenu();
//		fwm.showFrame();
//
//		while (true) {
//			fwm.chargementDeplacement(fwm.deplacement);
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
