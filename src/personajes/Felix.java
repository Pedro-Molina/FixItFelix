package personajes;

import escenario.Edificio;
import escenario.Ventana;
import manejo.Juego;
import otros.Movimiento;
import otros.Posicion;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Esta clase simula al personaje principal Felix. Felix es un Singleton.
 * Contiene informacion de la cantidad de vidas, si es invencible, y su
 * posicion.
 */
public class Felix {
	private int vidas;
	private boolean invencible; // true si es invenvible
	private Posicion pos ;
	private static Felix instance;

	private Felix() {
		pos= new Posicion();
		this.pos.setX(0);
		this.pos.setY(2);
		this.vidas = 3;
		this.invencible = false;
		
	}

	public static Felix getInstance() {
		if (instance == null) {
			instance = new Felix();
		}
		return instance;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public boolean isInvencible() {
		return invencible;
	}

	public void setInvencible(boolean invencible) {
		if (invencible == true) {
		Timer T= new Timer();
		this.invencible = invencible;
		 System.out.println("Felix es invencible");
		TimerTask sacarInvencible=new TimerTask() {

			@Override
			public void run() {
				Felix.getInstance().setInvencible(false);
				T.cancel();
			}
			
		};
		
		T.schedule(sacarInvencible, 5000, 10000);
		}
		else this.invencible = invencible;
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion p) {
		pos = p;
	}

	/**
	 * Felix recibe un movimiento (ARRIBA, ABAJO, IZQUIERDA, DERECHA) y se desplaza
	 * segun lo que haya recibido.
	 */
	public void movimiento(Movimiento M) {

		// felix el movimiento y el objeto al q se quiere mover
		Edificio E = Juego.getInstance().getEdificio(); // la instancia del edificio
		Ventana V;
		switch (M) {
		case DERECHA:
			if (this.pos.getX() < 4) {// si esta dentro de los limites
				V = E.getSeccionActual().getVentana(this.pos.getX(), this.pos.getY()); // ventana a la que se mueve
				if (V.getObstaculo().dejarPasar(M)) { // si puedo pasar me muevo
					this.pos.setX(this.pos.getX() + 1);// actualizo posicion
					System.out.println("Felix se mueve a la derecha");
				} else
					System.out.println("Felix no se pudo mover por obstaculo");
			} else
				System.out.println("Felix no se pudo mover derecha");
			break;
		case ARRIBA:
			if (this.pos.getY() > 0) {// si esta dentro de los limites
				V = E.getSeccionActual().getVentana(this.pos.getX(), this.pos.getY());// ventana a la que se mueve
				if (V.getObstaculo().dejarPasar(M)) {// si puedo pasar me muevo
					this.pos.setY(this.pos.getY() - 1);// actualizo posicion
					System.out.println("Felix se mueve hacia arriba");
				} else
					System.out.println("Felix no se pudo mover por obstaculo");
			} else
				System.out.println("Felix no se pudo mover arriba");
			break;
		case ABAJO:
			if (this.pos.getY() < 2) {// si esta dentro de los limites
				V = E.getSeccionActual().getVentana(this.pos.getX(), this.pos.getY());// ventana a la que se mueve
				if (V.getObstaculo().dejarPasar(M)) {// si puedo pasar me muevo
					this.pos.setY(this.pos.getY() + 1);// actualizo posicion
					System.out.println("Felix se mueve hacia abajo");
				} else
					System.out.println("Felix no se pudo mover por obstaculo");
			} else
				System.out.println("Felix no se pudo mover abajo");
			break;
		case IZQUIERDA:
			if (this.pos.getX() > 0) {// si esta dentro de los limites
				V = E.getSeccionActual().getVentana(this.pos.getX(), this.pos.getY());// ventana a la que se mueve
				if (V.getObstaculo().dejarPasar(M)) {// si puedo pasar me muevo
					this.pos.setX(this.pos.getX() - 1);// actualizo posicion
					System.out.println("Felix se mueve a la izquierda");
				} else
					System.out.println("Felix no se pudo mover por obstaculo");
			} else
				System.out.println("Felix no se pudo mover izquierda");
			break;
		case QUIETO:
			System.out.println("felix se quedo quieto");
			break;
		}
	}

	/**
	 * Felix toma la ventana de la posicion en la que está ubicado y llama a su
	 * metodo arreglar.
	 */
	public void repararVentana() {

		Edificio E = Juego.getInstance().getEdificio();
		Ventana V = E.getSeccionActual().getVentana(this.pos.getX(), this.pos.getY());// pide la ventana en donde est //
																						// parado
		if (V.cantidadRotos() != 0)
			V.arreglarVentana();// arregla la ventana en la que esta parado
	}

	/**
	 * Los enemigos llaman a este metodo para quitarle una vida a Felix. Si tiene
	 * invencibilidad, no surte efecto.
	 */
	public void golpear(Enemigo E) {

		if (!this.invencible) { // si felix es golpeado y no es invencible, pierde una vida
			this.vidas--;
			System.out.println("Felix Jr perdio una vida, fue impactado por un: " + E.getClass().getName());
			System.out.println("Vidas restantes: " + getVidas());
		}
	}
	
}
