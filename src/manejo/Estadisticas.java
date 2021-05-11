package manejo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import VentanaInicio.PanelEstadisticas;
/**
 * Guarda las estaditicas acerca del juego
 *
 */
public class Estadisticas implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ejecucion;
	private int juego;
	private int ganadores;
	private static Estadisticas est;

	private Estadisticas(int ejecucion, int juego, int ganadores) { // constructor cuando no existe el archivo
		this.ejecucion = ejecucion;
		this.juego = juego;
		this.ganadores = ganadores;
	}

	public static Estadisticas getInstance() {
		if (est == null) {
			cargar();
		}
		return est;
	}

	private static void cargar() {
		try {
			FileInputStream file = new FileInputStream("estadisticas.dat");
			ObjectInputStream tubEntrada = new ObjectInputStream(file);
			est = (Estadisticas) tubEntrada.readObject();
			file.close();
			tubEntrada.close();
		} catch (/* FileNotFoundException | */ IOException | ClassNotFoundException e) {
			File file = new File("estadisticas.dat");
			try {
				file.createNewFile();
				est = new Estadisticas(0, 0, 0);
				guardar();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void guardar() {
		try {
			FileOutputStream file = new FileOutputStream("estadisticas.dat");
			ObjectOutputStream tubSalida = new ObjectOutputStream(file);
			tubSalida.writeObject(est);
			tubSalida.close();
			file.close();
			PanelEstadisticas.getInstance().actualizarEstadisticas(); // aviasa que hay que actualizar las Estadisticas

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public int getEjecucion() {
		return ejecucion;
	}

	public void aumentarEjecucion() {
		this.ejecucion++;
		guardar();
	}

	public int getJuego() {
		return juego;
	}

	public void aumentarJuego() {
		this.juego++;
		guardar();
	}

	public int getGanadores() {
		return ganadores;
	}

	public void aumentarGanadores() {
		this.ganadores++;
		guardar();
	}
}
