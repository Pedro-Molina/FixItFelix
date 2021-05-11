package escenario;

import java.util.Random;
import manejo.Juego;
import otros.Posicion;

/**
 * Representa una de las tres secciones del edificio tiene 15 ventanas
 * inicializadas rotas o sanas y con o sin obstaculo de forma random lleva la
 * cantidad de ventanas rotas que hay en la seccion
 */
public class Seccion {

	private Ventana[][] ventanas = new Ventana[3][5];
	private int cantVentanasRotas;
	static final int limC = 4;
	static final int limF = 2;

	public Seccion(int numlSeccion, double probRomperVentana, double probDeObstaculo) {
		Random random = new Random();
		this.cantVentanasRotas = 0;
		boolean condRoto; // condicion para romper o no ventanas
		Posicion pos = new Posicion();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				pos = new Posicion();
				pos.setY(i);
				pos.setX(j);
				if ((numlSeccion == 0) && (i == 2 && j == 2)) { // pos de ventanas semicircular
					condRoto = probRomperVentana > random.nextDouble() * 100;
					ventanas[i][j] = new Puerta(condRoto, pos, probRomperVentana);
				} else {
					if ((numlSeccion == 0) && ((i == 1 && j == 2))) {
						condRoto = probRomperVentana > random.nextDouble() * 100;
						ventanas[i][j] = new Balcon(condRoto, pos, probRomperVentana);
					} else {
						if (random.nextInt(6) == 5 && probDeObstaculo > random.nextDouble() * 100) {
							ventanas[i][j] = new VentanaHoja(pos);
							condRoto = false; // las ventanas nunca estan rotas
						} else {
							condRoto = probRomperVentana > random.nextDouble() * 100;
							ventanas[i][j] = new DoblePanel(condRoto, probDeObstaculo > random.nextDouble() * 100, pos,
									probRomperVentana);// crea
														// normal
						}
					}

				}
				if (condRoto)
					this.cantVentanasRotas++;// cantidad de ventanas rotas en la seccion
			}
		}
	}

	public Ventana getVentana(int posX, int posY) { // devuelve una ventana a Felix
		return (ventanas[posY][posX]);
	}

	void setCantDeVentanasRotas() {
		this.cantVentanasRotas--;// cuenta las ventanas q ya estan arregladas
		System.out.print("cantidad de ventanas rotas son " + this.cantVentanasRotas);
		if (this.cantVentanasRotas == 0) {
			Juego.getInstance().getEdificio().setSeccionActual();// cuando se arreglan todos los vidrios cambia la  seccion
			Juego.getInstance().getJugador().setPuntaje(400);	//400 +100 de ultimo panel arregaldo	(pedro)										
		}

	}

	public static int getLimc() {
		return limC;
	}

	public static int getLimf() {
		return limF;
	}

	// solo para el Test
	public int getCantDeVentansRotas() {
		return this.cantVentanasRotas;
	}

	public Ventana[][] getVentanas() {// pedro
		return ventanas;
	}

	public void setVentanas(Ventana[][] ventanas) {// pedro
		this.ventanas = ventanas;
	}

}