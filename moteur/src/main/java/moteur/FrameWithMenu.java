package moteur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import annotation.Graphisme;
import gestionPlugins.Repository;
import pluginsAttaque.AttaqueSimple;
import pluginsDeplacement.DeplacementSimple;
import pluginsGraphisme.GraphismeSimple;
import sauvegarde.Persistance;

public class FrameWithMenu implements Serializable{
	public JFrame frame;
	private JPanel contentPane;

	public ArrayList<Robot> lesRobots;

	public Class attaque;
	public Class graphisme;
	public Class deplacement;
	public Class barreDeVie;
	public Class nomRobot;

	public FrameWithMenu(ArrayList<Robot> robots) {
		this.lesRobots = new ArrayList<>(robots);
		attaque = new AttaqueSimple().getClass();
		deplacement = new DeplacementSimple().getClass();
		graphisme = new GraphismeSimple().getClass();
	}

	public void showFrame() {
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

		fileM.add(new AbstractAction("Sauvegarder") {
			public void actionPerformed(ActionEvent arg0) {
				Persistance.save(lesRobots, attaque, graphisme, deplacement, barreDeVie, nomRobot, "Sauvegarde");
			}
		});
		
		fileM.add(new AbstractAction("Charger") {
			public void actionPerformed(ActionEvent arg0) {
				Partie partie = Persistance.charger("Sauvegarde");
				lesRobots = partie.getRobots();
				attaque = partie.getAttaque();
				graphisme = partie.getGraphisme();
				deplacement = partie.getDeplacement();
				barreDeVie = partie.getBarreDeVie();
				nomRobot = partie.getNomRobot();
			}
		});

		bar.add(menuDynamic);

		File file = new File("../plugins/target");
		final Repository r = new Repository(file);

		try {
			r.load();
			List<Class> graphismeClass = r.getListePluginsGraphisme();
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
				} else if (method.getAnnotation(Graphisme.class).nom().equals("NomRobot")) {
					nomRobot = classe;
				} else {
					this.graphisme = classe;
				}
				for (Robot r : lesRobots) {
					if (r.estVivant()) {
						method.invoke(myInstance, new Object[] { r, frame.getGraphics() });
					}
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
				for (Robot r : lesRobots) {
					if (r.estVivant()) {
						method.invoke(myInstance, new Object[] { r, frame.getGraphics() });
					}
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
	
	/**
	 * ethode pour fermer une frame
	 * @param f
	 */
	public static void closeFrame(JFrame f){
		f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Methode pour annoncer la mort d'un robot
	 * @param r
	 */
	public static void finishGame(Robot r){
		System.out.println("----------------------------------");
		System.out.println("PARTIE TERMINEE");
		System.out.println(r.getNom() + " est mort !");
		System.out.println("----------------------------------");
	}
}
