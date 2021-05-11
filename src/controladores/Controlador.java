package controladores;

import VentanaInicio.Inicio;
import manejo.Juego;
import manejo.Puntaje;


import java.awt.CardLayout;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Se encarga de la interaccion
 * entre la grafica y el juego
 */
public class Controlador {
	
	private static Controlador controlador;
	
	public Controlador() {
	}
	
	public static Controlador getInstance() {
		if(controlador==null) {
			controlador=new Controlador();
		}
		return controlador;
	}
	/**
	 * Comienza la ejecucion del juego
	 */
	public void comenzar() {
		Timer timer=new Timer();
		TimerTask grafica = new TimerTask() {
			@Override
			public void run() {
				Inicio.getInstance().actualizarJuego();
				
			}
		};
		timer.schedule(grafica, 0, 100);
		Juego.getInstance().comenzar();
	}
	/**
	 * Termina la ejecucion del juego
	 */
	public void terminar(Timer timer) {
		timer.cancel();
		String aux;
		if (Puntaje.getInstance().puntosMin() < Juego.getInstance().getJugador().getPuntaje())
			aux = "ganador";
		else {
			aux = "principal";
			Inicio.getInstance().setBounds(50, 50, 1100, 600);
			Inicio.getInstance().setResizable(true);
		}
		((CardLayout)Inicio.getInstance().getContentPane().getLayout()).show(Inicio.getInstance().getContentPane(), aux);
	}



	
	
}
