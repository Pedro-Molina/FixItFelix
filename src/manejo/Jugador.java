package manejo;

import java.io.Serializable;
/**
 * Representa al jugador
 */
public class Jugador implements Comparable<Jugador>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int puntaje = 0;

	public Jugador(String nombre, int puntaje) {
		setNombre(nombre);
		setPuntaje(puntaje);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje+= puntaje;
	}

	@Override
	public int compareTo(Jugador J) {
		if (this.getPuntaje() < J.getPuntaje()) {
			return 1;
		} else {
			if (this.getPuntaje() > J.getPuntaje())
				return -1;
			else
				return 0;
		}
	}
}