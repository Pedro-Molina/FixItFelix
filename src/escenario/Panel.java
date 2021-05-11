package escenario;

import java.util.Random;

import manejo.Juego;
import otros.Estado;

/**
 * Simula un panel de las ventanas manteniendo el estado propio del panel
 */
public class Panel {

	private Estado estado;

	public Panel(boolean romper, double prob) {
		if (romper) {
			Random random = new Random();
			if (prob > random.nextDouble() * 100)
				this.estado = Estado.ROTO;
			else
				this.estado = Estado.SEMI_ROTO;
		} else {
			this.estado = Estado.SANO;
		}
	}

	public void arreglar() {
		estado = estado.sigEstado(estado); // ROTO -> SEMI_ROTO
		if(estado == Estado.SANO) {
			Juego.getInstance().getJugador().setPuntaje(100);//agrego pedro
			System.out.println("el jugador tiene "+Juego.getInstance().getJugador().getPuntaje()+" puntos");
			//agrego pedro
		}
	}

	public Estado getEstado() {
		return estado;
	}
}