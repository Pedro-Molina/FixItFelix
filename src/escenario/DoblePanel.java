package escenario;

import java.util.Random;
import otros.Posicion;
import manejo.Juego;

import otros.Estado;

/**
 * ventana con 4 paneles rotos o sanos puede o no tener obstaculo
 */
public class DoblePanel extends Ventana {
	private Panel paneles[] = new Panel[2];

	public DoblePanel(boolean romper, boolean obstaculo, Posicion pos, double probRomper) {
		super(pos);// pedro
		for (int i = 0; i < 2; i++)
			paneles[i] = new Panel(romper, probRomper);

		if (obstaculo) { // crea obstaculo con tipo random
			Random random = new Random();
			int tipo_de_obstaculo = random.nextInt(2);
			switch (tipo_de_obstaculo) {
			case (0):
				this.setObstaculo(new Macetero());
				break;
			case (1):
				this.setObstaculo(new Moldura());
				break;
			}
		} else
			this.setObstaculo(new Libre());
	}

	/**
	 * aumenta el estato de los paneles ROTO -> SEMI-ROTO -> SANO asi para cada
	 * panel
	 */
	public void arreglarVentana() {

		if (!this.comprobarTodosSanos()) {
			for (int i = 0; i < 2; i++) {
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
		for (int i = 0; i < 2; i++) {
			if (this.paneles[i].getEstado() != Estado.SANO)
				return false;
		}
		return true;
	}

	// solo para el Test
	public boolean getEstado() {
		for (int i = 0; i < 2; i++) {
			if (paneles[i].getEstado() != Estado.SANO)
				return false;
		}
		return true;
	}

	public boolean puedeHaberPastel() {
		return true;
	}

	@Override
	public int cantidadRotos() {
		int cant = 0;
		for (int i = 0; i < 2; i++) {
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