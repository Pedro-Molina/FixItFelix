package controladores;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import VentanaInicio.Inicio;
/**
 *vuelve al panel principal
 */
public class ListenerInicio extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
		aux.show(Inicio.getInstance().getContentPane(), "principal");
	}
}
