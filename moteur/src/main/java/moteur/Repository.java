package moteur;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import annotation.Attaque;
import annotation.Deplacement;
import annotation.Graphisme;

public class Repository {

	File base;
	List<Class> listePluginsAttaque = new ArrayList<Class>();
	List<Class> listePluginsDeplacment = new ArrayList<Class>();
	List<Class> listePluginsGraphisme = new ArrayList<Class>();

	public Repository(File base) {
		this.base = base;
	}

	public List<Class> load() throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		ArrayList<Path> paths = FileVisitor.visitFile(base.getAbsolutePath(), ".class");
		for (Path path : paths) {
			if (!path.toString().contains("test-classes")) {
				String fileName = path.toString().replace(base.getAbsolutePath(), "");
				String dir = base.getAbsolutePath() + "/classes/";

				fileName = fileName.replace(".class", "");
				fileName = fileName.replaceAll("/", ".");
				fileName = fileName.replaceAll("\\\\", ".");
				fileName = fileName.replaceAll("classes", "").substring(2);
				PluginLoader mcl = new PluginLoader(dir);
				Class<?> classe = mcl.loadClass(fileName);
				Method[] methods = classe.getMethods();
				for (Method method : methods) {
					Annotation annotationAttaque = method.getAnnotation(Attaque.class);
					Annotation annotationDeplacement = method.getAnnotation(Deplacement.class);
					Annotation annotationGraphisme = method.getAnnotation(Graphisme.class);
					Annotation[] a = method.getAnnotations();
					if (annotationAttaque != null) {
						listePluginsAttaque.add(classe);
						System.out.println(annotationAttaque);
						System.out.println(classe.getName());
					}
					if (annotationDeplacement != null) {
						listePluginsDeplacment.add(classe);
						System.out.println(annotationDeplacement);
						System.out.println(classe.getName());
					}
					if (annotationGraphisme != null) {
						listePluginsGraphisme.add(classe);
						System.out.println(annotationGraphisme);
						System.out.println(classe.getName());
					}
				}
				classes.add(classe);
			}
		}
		return classes;
	}

	static class FileVisitor {

		public static ArrayList<Path> visitFile(String dir, final String filter) {
			final ArrayList<Path> files = new ArrayList<Path>();
			try {
				Files.walkFileTree(new File(dir).toPath(), new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (file.toString().endsWith(filter)) {
							files.add(file);
						}
						return FileVisitResult.CONTINUE;
					}

				});
			} catch (IOException e) {
				e.printStackTrace();
			}

			return files;
		}
	}

	public List<Class> getListePluginsAttaque() {
		return listePluginsAttaque;
	}

	public List<Class> getListePluginsDeplacment() {
		return listePluginsDeplacment;
	}

	public List<Class> getListePluginsGraphisme() {
		return listePluginsGraphisme;
	}

	public static void main(String[] args) {
		File base = new File("../plugins/target");
		Repository r = new Repository(base);
		try {
			List<Class> list = r.load();
			List<Class> listeA = r.getListePluginsAttaque();
			List<Class> listeD = r.getListePluginsDeplacment();
			for (Class classe : listeA) {
				System.out.println("\n" + classe.getName());
			}
			for (Class classe : listeD) {
				System.out.println(classe.getName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
