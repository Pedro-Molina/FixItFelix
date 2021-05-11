package escenario;

import manejo.Juego;
import otros.Posicion;
import otros.Estado;

/**
 * Representa las ventanas el balcon de la primera seccion estas no pueden
 * tener obstaculos
 */
public class Balcon extends Ventana {

	private Panel paneles[] = new Panel[8];

	public Balcon(boolean romperVentanas, Posicion p, double prob) {
		super(p);
		this.setObstaculo(new Libre());
		for (int i = 0; i < 8; i++) {
			this.paneles[i] = new Panel(romperVentanas, prob);
		}
	}

	@Override
	public void arreglarVentana() {

		if (!this.comprobarTodosSanos()) {
			for (int i = 0; i < 8; i++) {
				if (paneles[i].getEstado() != Estado.SANO) {
					paneles[i].arreglar();
					break;
				}
			}
			if (this.comprobarTodosSanos()) {
				// System.out.println("felix arreglo una ventana");
				Juego.getInstance().getEdificio().getSeccionActual().setCantDeVentanasRotas();
			}
		}
	}

	private boolean comprobarTodosSanos() {
		for (int i = 0; i < 8; i++) {
			if (this.paneles[i].getEstado() != Estado.SANO)
				return false;
		}
		return true;
	}

	// solo para el Test
	public boolean getEstado() {
		for (int i = 0; i < 8; i++) {
			if (paneles[i].getEstado() != Estado.SANO)
				return false;
		}
		return true;
	}

	public boolean puedeHaberPastel() {
		return false;
	}

	@Override
	public int cantidadRotos() {
		int cant = 0;
		for (int i = 0; i < 8; i++) {
			switch (paneles[i].getEstado()) {
			case SANO:
				break;
			case SEMI_ROTO: {
				cant++;
				break;
			}
			case ROTO: {
				cant = cant + 2;
				break;
			}
			}
		}
		return cant;
	}
}