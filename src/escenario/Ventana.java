package escenario;

import otros.*;

/**
 * Clase abstracta que tiene una variable obstaculo con un constructor para
 * instanciarlo
 */
public abstract class Ventana {
	private Posicion pos;// pedro

	public Ventana(Posicion pos) {
		this.pos = pos;
	}

	protected Obstaculo obstaculo;

	protected void setObstaculo(Obstaculo obs) {
		this.obstaculo = obs;
	}

	public Obstaculo getObstaculo() {
		return obstaculo;
	}

	public Posicion getPos() {
		return this.pos;
	}
	/**
	 * Arregla uno de los paneles de la ventana
	 */
	public abstract void arreglarVentana();
	/**
	 * Devuelve un boolean dependiendo de si se puede poner o no pastel en la ventana
	 */
	public abstract boolean puedeHaberPastel();
	/**
	 * Devuelve el estado de la ventana
	 */
	public abstract boolean getEstado();
	/**
	 * Devuelve la cantidad de paneles rotos
	 */
	public abstract int cantidadRotos();
}