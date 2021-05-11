package escenario;

import otros.*;

/**
 * Tipo de ventana que siempre tiene un obstaculo Persiana no tiene ningun panel
 * roto
 */
public class VentanaHoja extends Ventana {

	public VentanaHoja(Posicion pos) {
		super(pos);
		this.setObstaculo(new Perciana());

	}

	/**
	 * modela el abrir y cerrar una Perciana
	 */
	public void abrirCerrar() {

		if (this.obstaculo.dejarPasar(Movimiento.IZQUIERDA)) // si esta creada una perciana es true;
			this.setObstaculo(new Libre());
		else
			this.setObstaculo(new Perciana());
	}

	@Override
	public void arreglarVentana() {
	}

// solo para el Test
	public boolean getEstado() {
		return true;
	}

	public boolean puedeHaberPastel() {
		return false;
	}

	@Override
	public int cantidadRotos() {
		if (this.obstaculo.dejarPasar(Movimiento.IZQUIERDA))
			return 1;
		else
			return 0;
	}
}