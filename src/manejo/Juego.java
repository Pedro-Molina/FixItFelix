package manejo;



import java.util.Timer;
import java.util.TimerTask;
import controladores.Controlador;
import escenario.Edificio;
import personajes.Enemigo;
import personajes.Felix;
import personajes.Ralph;

/**
 * La clase juego es lo que mantiene al juego en funcionamiento.
 */
public class Juego {
	private Edificio edificio;
	private Nivel nivel;
	private Jugador jugador;
	private static Juego juego;

	private Juego() {
		this.nivel = new Nivel();
		this.edificio = new Edificio(this.nivel);
		this.jugador = new Jugador("", 0);
	}

	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	/**
	 * Este metodo instancia a todos los objetos y variables necesarias para poner
	 * al programa en funcionamiento.
	 */
	public void comenzar( ) {
		Timer timer=new Timer();
		Juego juego = Juego.getInstance();
		Ralph ralph = new Ralph();
		ralph.setLadrilloCant(40);
		juego.getEdificio().agregarListaEnemigos((Enemigo) ralph);
		TimerTask actualizarTodo = new TimerTask() {

			@Override
			public void run() {
				juego.getEdificio().actualizarEnemigos();
				juego.getEdificio().getNicelander().ejecutarPasteles();
			}
		};

		TimerTask pastel = new TimerTask() {

			@Override
			public void run() {
				juego.getEdificio().getNicelander().ponerPastel();
			}
		};
		TimerTask chequeo = new TimerTask() {

			@Override
			public void run() {
				if (Felix.getInstance().getVidas() <= 0 || (Juego.getInstance().getNivel().getNivel() == 11 || Juego.getInstance().getNivel().getTiempo() == 0) ) {
					pastel.cancel();
					actualizarTodo.cancel();
					Controlador.getInstance().terminar(timer);	
				}
				else Juego.getInstance().getNivel().setTiempo(Juego.getInstance().getNivel().getTiempo()-1);
			}
		};
		

		
		timer.schedule(actualizarTodo, 0, 100);// actualiza pos enemigos
		timer.schedule(chequeo, 0, 10); // chequea que felix no haya muerto
		timer.schedule(pastel, 0, 40000); //trata de poner pastel en la seccion cada 10 seg
		
	}

	

	public static Juego getInstance() {
		if (juego == null) {
			juego = new Juego();
		}
		return juego;
	}
	
}