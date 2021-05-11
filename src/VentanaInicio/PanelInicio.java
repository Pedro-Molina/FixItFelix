package VentanaInicio;
import java.awt.CardLayout;
import controladores.Controlador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import manejo.Estadisticas;
/**
 * En este panel se muestran los votones para acceder a las diferentes cosas(Estadistica,juego,
 * configuraciones,top5,reglas)
 */
public class PanelInicio extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton reglas;
	private JButton jugar;
	private JButton top5;
	private JButton configuracion;
	private JButton estadisticas;

	public PanelInicio() {
		super(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(); // restricciones a cada uno de los objetos

		this.estadisticas = new JButton("Estadisticas");
		estadisticas.setFont(new Font("Tahoma", Font.PLAIN, 45));
		estadisticas.setForeground(Color.WHITE);
		estadisticas.setOpaque(false);
		estadisticas.setContentAreaFilled(false);
		estadisticas.setBorderPainted(false);
		estadisticas.addMouseListener(new MouseAdapter() {
			/**
			 * cambia al panel de estadisticas
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
				aux.show(Inicio.getInstance().getContentPane(), "estadisticas");
			}
		});
		gbc = this.cargar(gbc, 1, 0, 1, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(estadisticas, gbc);

		Icon img = new ImageIcon(getClass().getResource("/imagenes/configuracion.png"));
		configuracion = new JButton(img);
		configuracion.setSize(50, 50);
		configuracion.setBackground(Color.WHITE);
		configuracion.addMouseListener(new MouseAdapter() {
			/**
			 * cambia al panel de configuracion
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
				aux.show(Inicio.getInstance().getContentPane(), "configuracion");
			}
		});
		gbc = this.cargar(gbc, 4, 0, 1, 1);
		gbc.fill = GridBagConstraints.EAST;
		this.add(configuracion, gbc);
		
		
		gbc.insets = new Insets(300, 0, 0, 0);
		gbc.weighty = 0.5;
		gbc.weightx = 1.0;
		
		reglas = new JButton("Reglas de Juego");
		reglas.addMouseListener(new MouseAdapter() {
			/**
			 * cambia al panel de reglas
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
				aux.show(Inicio.getInstance().getContentPane(), "reglas");
			}
		});
		reglas.setFont(new Font("Tahoma", Font.PLAIN, 50));
		reglas.setForeground(Color.WHITE);
		reglas.setOpaque(false); // hacer el boton transparente
		reglas.setContentAreaFilled(false);
		reglas.setBorderPainted(false);
		gbc = this.cargar(gbc, 0, 1, 1, 1);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(reglas, gbc);

		jugar = new JButton("Quiero Jugar!");
		jugar.setFont(new Font("Tahoma", Font.PLAIN, 50));
		jugar.setForeground(Color.WHITE);
		jugar.setOpaque(false); // hacer el boton transparente
		jugar.setContentAreaFilled(false);
		jugar.setBorderPainted(false);
		jugar.addMouseListener(new MouseAdapter() {
			/**
			 * cambia al panel donde se ejecuta la aplicacion
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				Estadisticas.getInstance().aumentarJuego();
				Inicio.getInstance().setBounds(250, 50, 630, 680);
				Inicio.getInstance().setResizable(false);
				CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
				aux.show(Inicio.getInstance().getContentPane(), "jugar");
				Inicio.getInstance().getJuego().requestFocus();// profe
				Controlador.getInstance().comenzar();
			}
		});
		gbc = this.cargar(gbc, 1, 1, 1, 1);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(jugar, gbc);

		top5 = new JButton("Top 5");
		top5.setFont(new Font("Tahoma", Font.PLAIN, 50));
		top5.setForeground(Color.WHITE);
		top5.setOpaque(false);
		top5.setContentAreaFilled(false);
		top5.setBorderPainted(false);

		top5.addMouseListener(new MouseAdapter() {
			/**
			 * cambia al panel con el top de ganadores
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout aux = (CardLayout) Inicio.getInstance().getContentPane().getLayout();
				aux.show(Inicio.getInstance().getContentPane(), "top5");
			}
		});
		gbc = this.cargar(gbc, 2, 1, 2, 1);
		gbc.fill = GridBagConstraints.BOTH;
		this.add(top5, gbc);
	}

	private GridBagConstraints cargar(GridBagConstraints gbc, int posX, int posY, int cantX, int cantY) {
		gbc.gridx = posX;
		gbc.gridy = posY;
		gbc.gridwidth = cantX;
		gbc.gridheight = cantY;
		return gbc;
	}
	/**
	 * dibuja el fondo del inicio
	 */
	@Override
	public void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);
		Dimension height = getSize();
		// Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
		ImageIcon fondo = new ImageIcon(getClass().getResource("/imagenes/Fix-it-Felix-Jr_TITLE.jpg"));
		// se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
		grafico.drawImage(fondo.getImage(), 0, 0, height.width, height.height, null);
		setOpaque(false);
	}
}