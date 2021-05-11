package VentanaInicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controladores.ListenerInicio;
import manejo.Estadisticas;

public class PanelEstadisticas extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel textoEjecucion;
	private JLabel textoJuego;
	private JLabel textoGanadores;

	private static PanelEstadisticas estadisticas;

	private PanelEstadisticas() {
		super(new GridLayout(4, 1));
		this.textoEjecucion = new JLabel("la cantidad de veces que se ejecuto la aplicacion fueron "
				+ Estadisticas.getInstance().getEjecucion());
		this.textoEjecucion.setHorizontalAlignment(SwingConstants.CENTER);
		this.textoEjecucion.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.add(this.textoEjecucion);

		textoJuego = new JLabel("la cantidad de veces que alguien hizo click en quiero jugar fueron "
				+ Estadisticas.getInstance().getJuego());
		textoJuego.setHorizontalAlignment(SwingConstants.CENTER);
		textoJuego.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.add(textoJuego);

		textoGanadores = new JLabel("la cantidad de jugadores en el ramking a lo largo del tiempo fueron "
				+ Estadisticas.getInstance().getGanadores());
		textoGanadores.setHorizontalAlignment(SwingConstants.CENTER);
		textoGanadores.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		this.add(textoGanadores);

		ImageIcon volver = new ImageIcon(getClass().getResource("/imagenes/flecha.png"));
		JButton boton = new JButton(volver);
		boton.setBackground(Color.lightGray);
		boton.setBorder(null);
		boton.addMouseListener(new ListenerInicio());
		this.add(boton);

	}

	public static PanelEstadisticas getInstance() {
		if (estadisticas == null)
			estadisticas = new PanelEstadisticas();
		return estadisticas;
	}

	public void actualizarEstadisticas() {
		this.textoEjecucion.setText("la cantidad de veces que se ejecuto la aplicacion fueron "
				+ Estadisticas.getInstance().getEjecucion());
		this.textoJuego.setText("la cantidad de veces que alguien hizo click en quiero jugar fueron "
				+ Estadisticas.getInstance().getJuego());
		this.textoGanadores.setText("la cantidad de jugadores en el ramking a lo largo del tiempo fueron "
				+ Estadisticas.getInstance().getGanadores());
	}

}
