package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import manejo.Juego;
import manejo.Nivel;
/**
 * configura el nivel en el que va iniciar el juego
 *
 */
public class ListenerCambioNivel implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int numNivel = (int) ((JComboBox<?>) e.getSource()).getSelectedItem();
		Nivel nivel = new Nivel();
		for (int i = 1; i < numNivel; i++)
			nivel.aumentarNivel();
		Juego.getInstance().setNivel(nivel);
	}
}
