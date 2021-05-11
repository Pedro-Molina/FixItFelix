package escenario;

import java.util.ArrayList;

import manejo.*;
import otros.Posicion;
import personajes.*;
import java.util.Random;
/**
 * Esta clase hace referencia al edifio que contiene las tres secciones se deve
 * instanciar uno en cada nivel
 */
public class Edificio {

	private Seccion[] secciones = new Seccion[3];
	private int secActual; // seccion actual de juego
	private ArrayList<Enemigo> listaEnemigos;
	private Nicelander nicelander;

	public Edificio(Nivel nivel) { // constructor
		double cantVentanas_romper = nivel.getRomperVentana();
		for (int i = 0; i < 3; i++) {
			secciones[i] = new Seccion(i, cantVentanas_romper, nivel.getObstaculo());
		}
		this.secActual = 0;
		this.listaEnemigos = new ArrayList<Enemigo>();
		this.nicelander = new Nicelander();
	}

	public Seccion getSeccionActual() {
		return secciones[secActual];
	}

	/**
	 * posiciona a Felix en la proxima seccion si termino el nivel pasa de nivel
	 */
	void setSeccionActual() {
		if (this.secActual < 3) {
			System.out.println("Felix reparo toda la seccion " + this.decirSeccion());
			Felix felix = Felix.getInstance();
			Posicion posicion = new Posicion();
			posicion.setX(0);
			posicion.setY(2);
			felix.setPos(posicion);
			secActual++;
			if (secActual == 3) {
				actualizarNivel();
			}
		}
	}
	/**
	 * Actualiza el nivel a reparar todas la secciones
	 */
	private void actualizarNivel() {
		System.out.println("Felix reparo las 3 secciones del nivel. Avanza de nivel");
		Juego.getInstance().getNivel().aumentarNivel();
		Ralph ralph=new Ralph();
		ralph.setLadrilloCant(40);
		listaEnemigos=new ArrayList<Enemigo>();
		listaEnemigos.add(ralph);
		this.secActual = 0;
		for (int i = 0; i < 3; i++) {
			secciones[i] = new Seccion(i, Juego.getInstance().getNivel().getRomperVentana(),
					Juego.getInstance().getNivel().getObstaculo());
		}
		
	}

	/**
	 * para cuando a felix lo golpea un pajaro, se deve reiniciar la seccion
	 */
	public void reiniciarSeccion() {

		double cantVentanas_romper = Juego.getInstance().getNivel().getRomperVentana();
		this.secciones[this.secActual] = new Seccion(this.secActual, cantVentanas_romper,
				Juego.getInstance().getNivel().getObstaculo());
	}

	// para el Test
	public int decirSeccion() {
		return this.secActual;
	}
	/**
	 * Actualiza las posiciones de todos los enemigos
	 */
	public void actualizarEnemigos() {
		ArrayList<Enemigo> listaEliminar = new ArrayList<Enemigo>();
		for (int i = 0; i < this.listaEnemigos.size(); i++) {// recorro la lista de enemigos
			Enemigo enemigo = listaEnemigos.get(i);
			enemigo.ejecutar();// actualizo
			if (enemigo.cayo()) {
				listaEliminar.add(enemigo);// si se cayo lo agrego a otra lista para eliminarlo posteriormente
			}
		}
		for (int i = 0; i < listaEliminar.size(); i++) {
			listaEnemigos.remove(listaEliminar.get(i));// elimino el enemigo caido
		}
		if (Juego.getInstance().getEdificio().decirSeccion() == 1 && (!this.hayPajaros())) {
			Random ran=new Random();
			int numero= ran.nextInt(11);
			if(numero>6) {
				this.listaEnemigos.add(new Pajaro());
			}
			else {
				this.listaEnemigos.add(new Pajaro());
				this.listaEnemigos.add(new Pajaro());
			}
		}
	}
	/**
	 * Actualiza si los pasteles fueron tomados o no
	 */
	public void actualizarNicelander() {
		nicelander.ejecutarPasteles();
	}

	public void agregarListaEnemigos(Enemigo enemigo) {
		this.listaEnemigos.add(enemigo);
	}

	public ArrayList<Enemigo> getListaEnemigos() {
		return listaEnemigos;
	}

	public Nicelander getNicelander() {
		return nicelander;
	}
	/**
	 * Chequea si hay pajaros en la lista de enemigos
	 */
	public boolean hayPajaros() {
		for(int i=0;i<listaEnemigos.size();i++) {
			if(listaEnemigos.get(i).getClass().getName() == "personajes.Pajaro") {
				return true;
			}
		}
		return false;
	}

}