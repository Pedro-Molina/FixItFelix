package personajes;

import manejo.Juego;

/**
 * Esta clase simula los ladrillos que Ralph lanza frecuentemente durante la
 * partida.
 */

public class Ladrillo extends Enemigo {
	public Ladrillo(int posX) {
		this.pos.setX(posX);
		this.pos.setY(-1);
	}

	/**
	 * El método ejecutar actualiza la posicion del ladrillo (lo hace descender un
	 * casillero) y llama al método "estaFelix", definido explicitamente en la clase
	 * Enemigo.
	 */
	public void ejecutar() {
		if (this.getVelocidad() == 0) {
			if (getPosY() <= 2) {
				pos.setY(getPosY() + 1);
				this.estaFelix();
				System.out.println("Posicion del ladrillo " + this.getPosX() + " " + this.getPosY());
			}
			this.setVelocidad(Juego.getInstance().getNivel().getVelocidadLadrillo());
		}
		this.setVelocidad(this.getVelocidad()-1);
	}

	public boolean estaFelix() {
		if (super.estaFelix())
			Juego.getInstance().getEdificio().getListaEnemigos().remove(this);//si choco a felix se borra
		return true;
	}

	public boolean cayo() {
		if (getPosY() > 2)
			return true;
		else
			return false;
	}

}