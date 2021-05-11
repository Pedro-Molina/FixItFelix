package VentanaInicio;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controladores.ListenerInicio;
/**
 * En este panel se encuentral las reglas del juego
 */
public class PanelReglas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelReglas() {
		super(new BorderLayout());
		Icon img = new ImageIcon(getClass().getResource("/imagenes/reglas.png"));
		JButton boton = new JButton(img);
		boton.setBackground(Color.BLACK);
		boton.addMouseListener(new ListenerInicio());
		this.add(boton, BorderLayout.CENTER);
	}
}