package otros;

/**
 * Este enumerativo funciona para definir el estado de los paneles de las
 * ventanas.
 */

public enum Estado {
	ROTO, SEMI_ROTO, SANO;
	/**
	 * Este metodo recibe un estado como parametro y retorna el siguiente. El orden
	 * es: ROTO > SEMI_ROTO > SANO. Simula el arreglo de cada panel.
	 */
	public Estado sigEstado(Estado unEstado) {

		switch (unEstado) {
		case ROTO:
			return Estado.SEMI_ROTO;
		default:
			return Estado.SANO;
		}
	}
}
