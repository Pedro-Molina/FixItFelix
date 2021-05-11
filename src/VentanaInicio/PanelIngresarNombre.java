package VentanaInicio;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controladores.ListenerJugador;


public class PanelIngresarNombre extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField texto;
	private JButton boton;
	private JLabel informacion;
	private static PanelIngresarNombre instance;

	private PanelIngresarNombre() {
		super(new GridLayout(3, 1));
		this.informacion = new JLabel("ingrese un nombre que contenga entre 2 y 20 caracteres");
		this.informacion.setHorizontalAlignment(SwingConstants.CENTER);
		this.informacion.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		this.add(this.informacion);
		this.texto = new JTextField(10);
		this.add(this.texto);
		this.boton = new JButton(" Aceptar ");
		this.boton.addMouseListener(new ListenerJugador());
		this.add(boton);
	}

	public static PanelIngresarNombre getInstance() {
		if (instance == null)
			instance = new PanelIngresarNombre();
		return instance;
	}

	public String getText() {
		return this.texto.getText();
	}
}