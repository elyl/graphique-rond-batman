import java.util.List;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.awt.Color;

public class Sauvegarde {
	public static void export(String file, List<Motif> motifs) {
		String line;
		Iterator<Motif> itr;
		Motif current;
		BufferedWriter writer;

		try {
			writer = new BufferedWriter(new FileWriter(file));
			itr = motifs.iterator();
			while (itr.hasNext()) {
				current = itr.next();
				line = current.getShapeType() + ":" + current.getX() + ":"
						+ current.getY() + ":" + current.getWidth() + ":"
						+ current.getHeight() + ":"
						+ current.getColor().getRGB();
				writer.write(line, 0, line.length());
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void importMotif(String file, Dessin d) {
		String line;
		String tab[];
		BufferedReader r;

		try {
			r = new BufferedReader(new FileReader(file));
			while ((line = r.readLine()) != null && line.length() != 0) {
				tab = line.split(":");
				d.ajouterMotif(new Motif(Integer.parseInt(tab[1]), Integer
						.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer
						.parseInt(tab[4]), new Color(Integer.parseInt(tab[5]),
						true), Integer.parseInt(tab[0])));
			}
			r.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}