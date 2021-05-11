package VentanaInicio;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import VentanaInicio.PanelQuieroJugar;
import manejo.Estadisticas;

/**
 * Frame que contendra los distintos paneles.
 *
 */
public class Inicio extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel principal;
	private JPanel reglas;
	private JPanel top5;
	private JPanel configuracion;
	private JPanel estadisticas;
	private JPanel juego;
	private JPanel ganador;
	private static Inicio ini;

	private Inicio() {
		super("Inicio");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // al cerrar el programa lo saca de la memoria
		this.getContentPane().setLayout(new CardLayout());
		this.setBounds(50, 50, 1100, 600);
		this.setVisible(true);
		this.setResizable(false);

		this.principal = new PanelInicio();
		this.principal.setName("principal");
		this.getContentPane().add(principal, "principal");

		this.reglas = new PanelReglas();
		this.reglas.setName("reglas");
		this.getContentPane().add(this.reglas, "reglas");

		this.configuracion = new PanelConfiguracion();
		this.configuracion.setName("configuracion");
		this.getContentPane().add(this.configuracion, "configuracion");

		this.top5 = new PanelTop5();
		this.top5.setName("top5");
		this.getContentPane().add(this.top5, "top5");

		this.juego = new PanelQuieroJugar();
		this.juego.setName("jugar");
		this.getContentPane().add(this.juego, "jugar");

		this.estadisticas = PanelEstadisticas.getInstance();
		this.estadisticas.setName("estadisticas");
		this.getContentPane().add(this.estadisticas, "estadisticas");
		Estadisticas.getInstance().aumentarEjecucion();

		this.ganador = PanelIngresarNombre.getInstance();
		this.ganador.setName("ganador");
		this.getContentPane().add(this.ganador, "ganador");
	}

	public JPanel getJuego() {
		return juego;
	}

	public static Inicio getInstance() {
		if (ini == null) {
			ini = new Inicio();
		}
		return ini;
	}

	public JPanel getTop5() {
		return this.top5;
	}

	public JPanel getEstadisticas() {
		return this.estadisticas;
	}

	public void actualizarJuego() {
		juego.repaint();
	}

	public JPanel getPrincipal() {
		return principal;
	}

	public void setPrincipal(JPanel principal) {
		this.principal = principal;
	}
	
	public static void main(String argv[]) {
		Inicio i = Inicio.getInstance();
	}
	

}
