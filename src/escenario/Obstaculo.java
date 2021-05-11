package escenario;

import otros.Movimiento;

/**
 * Esta clase es la encargada de representar los distintos tipos de obstaculos
 * en cada ventana, cada obstaculo tiene un moviento asocioado(el que felix no
 * puede hacer)
 */

public abstract class Obstaculo {
	private Movimiento mov;

	protected Obstaculo(Movimiento mov) {
		this.mov = mov;
	}

	/**
	 * este metodo recibe el movimiento que quiere hacer felix y dependiendo de el
	 * obstaculo de la ventana lo deja pasar o no
	 */
	public boolean dejarPasar(Movimiento mov) {

		if (this.mov == mov)
			return false;
		return true;
	}
}
