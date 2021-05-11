package personajes;

import otros.Posicion;

/**
 * Esta clase abstracta contiene las variables y metodos que comparten los
 * enemigos Ladrillo y Pajaro.
 */

public abstract class Enemigo {
	private int velocidad;
	protected Posicion pos = new Posicion();

	/**
	 * El método estaFelix hace que el enemigo (Pajaro o Ladrillo) compare su
	 * posicion con la de Felix para poder así golpearlo y quitarle una vida.
	 */
	protected boolean estaFelix() {

		Felix fel = Felix.getInstance();
		// si esta en la posicion de felix y este no es invencible le quita una vida
		if ((fel.getPos().getX() == this.pos.getX()) && (fel.getPos().getY() == this.pos.getY())
				&& (!fel.isInvencible())) {
			fel.golpear(this);
			return true;
		}
		return false;
	}

	protected void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getPosX() {
		return pos.getX();
	}

	public int getPosY() {
		return pos.getY();
	}

	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * El método cayo sirve para verificar si efectivamente el Ladrillo se cayo del
	 * mapa para poder eliminarlo del programa.
	 */
	public abstract boolean cayo();

	/**
	 * Este metodo actualiza cada enemigo.
	 */
	public abstract void ejecutar();
}
