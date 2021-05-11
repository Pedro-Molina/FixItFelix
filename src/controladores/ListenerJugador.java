package controladores;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Excepciones.CaracterMin;
import Excepciones.Espacios;
import VentanaInicio.Inicio;
import VentanaInicio.PanelIngresarNombre;
import manejo.Juego;
import manejo.Puntaje;
/**
 * Listener encargado de enviar el nombre ingresado por teclado para que se guarde
 *
 */
public class ListenerJugador extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		String texto = PanelIngresarNombre.getInstance().getText();
		boolean condicion = true;
		try {
			verificarEspacios(texto);
		} catch (Espacios e1) {
			e1.avisar();
			condicion = false;
		}
		try {
			verificarCaracteresMin(texto);
		} catch (CaracterMin e1) {
			e1.avisar();
			condicion = false;
		}
		if (texto.length() > 20) {
			texto = texto.substring(0, 20);
		}
		if (condicion) {
			Juego.getInstance().getJugador().setNombre(texto);
			Puntaje.getInstance().agregar(Juego.getInstance().getJugador());
			CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
			aux.show(Inicio.getInstance().getContentPane(), "principal");
			Inicio.getInstance().setBounds(50, 50, 1100, 600);
			Inicio.getInstance().setResizable(true);
		}
	}
	/**
	 * verifica que no se haya ingresado un espacio
	 */
	public void verificarEspacios(String text) throws Espacios  {
		 text = text.trim(); //eliminar los posibles espacios en blanco al principio y al final 
		if (text.indexOf(" ") != -1) {	//se busca el primer espacio en blanco
			throw new Espacios();
		}
	}
	/**
	 * verifica que se haya ingresado al menos dos carcteres
	 */
	public void verificarCaracteresMin(String texto) throws CaracterMin {
		int cant = texto.length();
		if (cant < 2) {
			throw new CaracterMin();
		}
	}
}
