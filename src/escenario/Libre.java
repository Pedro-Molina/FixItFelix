package escenario;

import otros.Movimiento;

public class Libre extends Obstaculo {

	public Libre() {
		super(null);
	}

	@Override
	public boolean dejarPasar(Movimiento mov) {
		return true;
	}
}
