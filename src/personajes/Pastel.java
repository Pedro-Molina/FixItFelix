package personajes;

import manejo.Juego;
import otros.Posicion;

public class Pastel {
	private Posicion pos = new Posicion();

	public Pastel(int x, int y) {
		this.pos.setX(x);
		this.pos.setY(y);
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	public void estaFelix() {
		if (Felix.getInstance().getPos().getX() == pos.getX() && Felix.getInstance().getPos().getY() == pos.getY()) {
			Felix.getInstance().setInvencible(true);
			Juego.getInstance().getEdificio().getNicelander().getPasteles().remove(this);
		}
	}

}
