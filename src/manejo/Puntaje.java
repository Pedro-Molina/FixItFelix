package manejo;

import java.io.*;
import java.util.Arrays;
import VentanaInicio.Inicio;
import VentanaInicio.*;
/**
 * Se encarga de actualizar los puntajes del top 5
 */
public class Puntaje {
	private Jugador top5[];
	private static Puntaje punt;

	private Puntaje() { // lo carga desde el archivo
		try {
			FileInputStream file = new FileInputStream("datos.dat");
			ObjectInputStream tubEntrada = new ObjectInputStream(file);
			top5 = (Jugador[]) tubEntrada.readObject();
			file.close();
			tubEntrada.close();
		} catch (/* FileNotFoundException | */ IOException | ClassNotFoundException e) {
			File file = new File("datos.dat");
			// if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			top5 = new Jugador[5];
			for (int i = 0; i < 5; i++) { // inicializo el top
				top5[i] = new Jugador("Juador", 0);
			}
			try {
				guardar();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// }
		}
	}

	public static Puntaje getInstance() {
		if (punt == null) {
			punt = new Puntaje();
		}
		return punt;
	}

	public void agregar(Jugador player) {
		if (player.compareTo(top5[4]) < 0) {
			top5[4] = player;
			Arrays.sort(top5);
			Estadisticas.getInstance().aumentarGanadores(); // avisa que un nuevo jugador entro al top a estadisticas
			try {
				guardar();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void guardar() throws IOException, FileNotFoundException, ClassNotFoundException {
		FileOutputStream file = new FileOutputStream("datos.dat");
		ObjectOutputStream tubSalida = new ObjectOutputStream(file);
		tubSalida.writeObject(top5);
		file.close();
		tubSalida.close();
		((PanelTop5) Inicio.getInstance().getTop5()).actualizarTalba(); // aviasa que hay que actualizar la tabla

	}

	public Jugador[] getTop5() {
		return top5;
	}
	
	public int puntosMin() {
		return top5[4].getPuntaje();
	}
}
