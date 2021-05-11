package personajes;

import otros.Movimiento;

import manejo.Juego;

/**
 * Esta clase simula a Ralph. Ralph contiene su posicion en X (inicializado en
 * el medio), un enumerativo de tipo Direccion, y una variable de tipo entero
 * que define la cantidad de ladrillos que puede lanzar.
 */
public class Ralph extends Enemigo {
	private Movimiento mov = Movimiento.DERECHA;
	private int ladrilloCant;
	private int frecuencia;

	public Ralph() {
		
		frecuencia=Juego.getInstance().getNivel().getFreciencaLadrillos();
		super.pos.setX(2);
	}

	/**
	 * Ralph "lanza" un ladrillo (lo instancia) y lo devuelve a la clase Juego,
	 * donde es guardado en una lista de objetos en funcionamiento. Ralph comienza
	 * por defecto en direccion Este y si detecta que esta en un extremo del mapa,
	 * cambia su direccion a Oeste, y así viceversa.
	 */
	@Override
	public void ejecutar() {
		if (this.getVelocidad() == 0) {
			switch (mov) {
			case DERECHA: {
				this.pos.setX(this.pos.getX() + 1);
				if (this.pos.getX() == 4)
					mov = Movimiento.IZQUIERDA;
				break;
			}
			case IZQUIERDA: {
				this.pos.setX(this.pos.getX() - 1);
				if (this.pos.getX() == 0)
					mov = Movimiento.DERECHA;
				break;
			}
			case ARRIBA: {
				break;
			}
			case ABAJO: {
				break;
			}
			case QUIETO: {
				break;
			}
			}
			this.setVelocidad(Juego.getInstance().getNivel().getVelocidadRalph());
		}
		this.setVelocidad(this.getVelocidad() - 1);
		if(this.frecuencia == 0) {
			this.tirarLadrillo();
			frecuencia=Juego.getInstance().getNivel().getFreciencaLadrillos();
		}
		frecuencia--;
		
	}

	public void tirarLadrillo() {
		if (ladrilloCant > 0) {
			Ladrillo l = new Ladrillo(this.pos.getX());
			Juego.getInstance().getEdificio().agregarListaEnemigos(l);
			ladrilloCant--;
		}
	}

	public int getLadrilloCant() {
		return ladrilloCant;
	}

	public void setLadrilloCant(int ladrilloCant) {
		this.ladrilloCant = ladrilloCant;
	}

	@Override
	public boolean cayo() {
		// TODO Auto-generated method stub
		return false;
	}

}
