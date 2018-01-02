package moteur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class FrameWithMenu {
	JFrame frame;
	private JPanel contentPane;
	
	void showFrame() {
		if (frame == null) {
			frame = new JFrame("Robot War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 450, 300);
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
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		JMenu fileM = new JMenu("Fichier");
		JMenu menuDynamic = new JMenu("Plugins");
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
			List<Class> attaqueClass = r.getListePluginsAttaque();
			List<Class> deplacementClass = r.getListePluginsDeplacment();
			List<Class> graphismeClass = r.getListePluginsGraphisme();

			for (final Class<?> classe : attaqueClass) {
				menuDynamic.add(new AbstractAction(classe.getName()) {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("Click sur plugin attaque : " + classe.getName());
					}

				});
			}
			for (final Class<?> classe : deplacementClass) {
				menuDynamic.add(new AbstractAction(classe.getName()) {
					public void actionPerformed(ActionEvent arg0) {
						chargementDeplacement(classe);
						System.out.println("Click sur plugin deplacement : " + classe.getName());
					}

				});
			}
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

		Object myInstance;
		try {
			myInstance = classe.getConstructors()[0].newInstance();
			Method method = classe.getMethod("draw", new Class[] { Robot.class, Graphics.class });
			// Pour tester, sinon aller chercher tous les robots du jeu.
			Robot r = new Robot(60, 60, 50, 50, Color.RED, "Test");
			method.invoke(myInstance, new Object[] { r, frame.getGraphics() });
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public void chargementDeplacement(Class classe) {

		Object myInstance;
		try {
			myInstance = classe.getConstructors()[0].newInstance();
			Method method = classe.getMethod("deplacement", new Class[] { Robot.class });
			Robot r = new Robot(60, 60, 50, 50, Color.RED, "Test");
			method.invoke(myInstance, new Object[] { r });
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FrameWithMenu().showFrame();
	}
}
