package personajes;

import java.util.Random;
import manejo.Juego;
import otros.Movimiento;

/**
 * Esta clase simula los pajaros que aparecen en el juego. Al instanciarse, el
 * pajaro se situa en la posicion X = -1 (fuera del mapa) y en una posicion
 * aleatoria en Y (entre 0 y 2) El pajaro inicia por defecto en direccion Este y
 * cuando se comprueba que se salio del mapa cambia su direccion a Oeste, y asi
 * viceversa.
 */

public class Pajaro extends Enemigo {
	Movimiento direc;

	public Pajaro() {
		Random r = new Random();
		this.pos.setX(6);
		this.pos.setY(r.nextInt(3));
		this.direc = Movimiento.DERECHA;
		
	}

	public void ejecutar() {
		if(this.getVelocidad()==0) {
			switch (direc) {
			case DERECHA: {
				pos.setX(getPosX() + 1);
				if (getPosX() > 5)
					this.direc = Movimiento.IZQUIERDA;
				break;
			}
			case IZQUIERDA: {
				pos.setX(getPosX() - 1);
				if (getPosX() < 0)
					this.direc = Movimiento.DERECHA;
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
			System.out.println("Posicion del pajaro: " + this.getPosX() + " " + this.getPosY());
			this.estaFelix();
			this.setVelocidad(Juego.getInstance().getNivel().getVelocidadPajaro());
		}
		else
		this.setVelocidad(this.getVelocidad()-1);
	}

	public boolean estaFelix() {
		if (super.estaFelix()) {
			Juego.getInstance().getEdificio().reiniciarSeccion();
		}
		return true;
	}

	public boolean cayo() {
		return false;
	}

	public Movimiento getMov() {
		return direc;
	}

}