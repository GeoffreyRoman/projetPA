import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PluginLoader extends SecureClassLoader {
	private ArrayList<File> path = new ArrayList<File>();

	public PluginLoader(ArrayList<File> p) {
		this.path = p;
	}

	PluginLoader(String... paths) {
		for (String path : paths) {
			File file = new File(path);
			this.path.add(file);
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		if (b == null) {
			throw new ClassNotFoundException();
		}
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) {
		String filePath = name.replace('.', File.separatorChar) + ".class";
		byte[] bytesTab = null;
		for (File file : path) {
			boolean b = file.exists();
			bytesTab = readBytesFromFile(filePath, bytesTab, file);
			if (bytesTab != null) {
				return bytesTab;
			}
		}

		return bytesTab;
	}

	private byte[] readBytesFromFile(String filePath, byte[] bytesTab, File file) {
		byte[] bytesFromClass = null;
		if (file.isDirectory()) {
			bytesFromClass = readBytesFromClass(filePath, bytesTab, file);
		} else { // C'est un zip ou jar. Le zip doit se trouver dans le bin.
			bytesFromClass = readBytesFromJar(filePath, file);
		}
		return bytesFromClass;
	}

	private byte[] readBytesFromClass(String filePath, byte[] bytesTab, File file) {
		File file2 = new File(file, filePath);
		if (file2.exists()) {
			try {
				bytesTab = Files.readAllBytes(file2.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytesTab;
	}

	private byte[] readBytesFromJar(String filePath, File file) {
		try {
			File file2 = new File(file.getPath());
			if (!file2.exists()) {
				return null;
			}
			ZipFile zip = new ZipFile(file2);
			ZipEntry entry = new ZipEntry(filePath);
			InputStream is = zip.getInputStream(entry);

			if (is == null) {
				return null;
			}

			int size = is.available();
			byte[] bytesTab = new byte[size];
			int len;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = is.read(bytesTab, 0, size)) != -1) {
				bos.write(bytesTab, 0, len);
			}
			bytesTab = bos.toByteArray();
			zip.close();
			return bytesTab;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
