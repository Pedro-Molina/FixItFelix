package controladores;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import otros.Movimiento;
import personajes.Felix;
/**
 * controlador encargado del movimiento
 * de felix a travez de la interaccion con el teclado
 */
public class ControladorFelix implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case (KeyEvent.VK_DOWN):
			Felix.getInstance().movimiento(Movimiento.ABAJO);
			// Inicio.getInstance().actualizarJuego();
			break;
		case (KeyEvent.VK_UP):
			Felix.getInstance().movimiento(Movimiento.ARRIBA);
			// Inicio.getInstance().actualizarJuego();
			break;
		case (KeyEvent.VK_LEFT):
			Felix.getInstance().movimiento(Movimiento.IZQUIERDA);
			// Inicio.getInstance().actualizarJuego();
			break;
		case (KeyEvent.VK_RIGHT):
			Felix.getInstance().movimiento(Movimiento.DERECHA);
			// Inicio.getInstance().actualizarJuego();
			break;
		case (KeyEvent.VK_SPACE):
			Felix.getInstance().repararVentana();
			// Inicio.getInstance().actualizarJuego();
		default:
			Felix.getInstance().movimiento(Movimiento.QUIETO);
			// Inicio.getInstance().actualizarJuego();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
