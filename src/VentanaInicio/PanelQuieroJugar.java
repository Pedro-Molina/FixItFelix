package VentanaInicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import escenario.Ventana;
import manejo.Juego;
import otros.Movimiento;
import personajes.*;
import controladores.ControladorFelix;
import java.util.*;
/**
 * Panel donde el jugador podra jugar
 */
public class PanelQuieroJugar extends JPanel {

	private static final long serialVersionUID = 1L;
	private String felixQuieto = "/imagenes/FelixQuieto.png";
	private String ladrillo = "/imagenes/ladrillo.png";
	private String pajaro = "/imagenes/pajaro.png";
	private String ralph = "/imagenes/ralph.png";
	private String gameOver = "/imagenes/gameOver.png";
	private String pastel = "/imagenes/pastel.png";
	private Image edificioActual;
	private Image img_felix;
	private Image img_ladrillo;
	private Image img_ralph;
	private Image img_pajaro;
	private Image img_gameOver;
	private Image img_pastel;

	private Image estadoVentana[] = new Image[5]; // sana pos 0
	private Image estadoHoja[] = new Image[2];
	private Image estadoBalcon[] = new Image[17];
	private Image estadoPuerta[] = new Image[9];
	private Image secActual[][] = new Image[3][5];
	private Image obstaculos[] = new Image[2];
	private Image estadoEdificio[] = new Image[3];

	public PanelQuieroJugar() {
		cargarObstaculos();
		cargarEstadoEdificio();
		this.img_felix = loadImage(this.felixQuieto);
		this.img_ladrillo = loadImage(this.ladrillo);
		this.img_pajaro = loadImage(this.pajaro);
		this.img_ralph = loadImage(this.ralph);
		this.img_gameOver = loadImage(this.gameOver);
		this.img_pastel = loadImage(this.pastel);
		cargarEstadoBalcon();
		cargarEstadoHoja();
		cargarEstadoPuerta();
		cargarEstadoVentana();
		ControladorFelix flechas = new ControladorFelix();
		this.addKeyListener(flechas);
		this.setFocusable(true);
	}
	/**
	 * Metodo para carcar imagenes
	 */
	public Image loadImage(String imgFileName) {
		java.net.URL imgUrl = getClass().getResource(imgFileName);
		if (imgUrl == null) {
			System.err.println("No se encuentra el archivo:" + imgFileName);
		} else {
			try {
				return ImageIO.read(imgUrl); // carga imagen en img
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Se ejecuta cada vez que se actualiza la grafica del juego
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.edificioActual = this.estadoEdificio[Juego.getInstance().getEdificio().decirSeccion()];
		g.drawImage(edificioActual, 0, 0, Inicio.getInstance().getWidth(), Inicio.getInstance().getHeight(), null);
		int ancho = (Inicio.getInstance().getWidth() / 5) - 20;
		int alto = Inicio.getInstance().getHeight() / 5;
		pintarSeccion(g, ancho, alto);
		pintarFelix(g, ancho, alto);
		pintarEnemigos(g, ancho, alto);
		pintarPastel(g, ancho, alto);
		pintarTiempoYVida(g);
		if (Felix.getInstance().getVidas() <= 0)
			finDelJuego(g);
	}
	/**
	 * Grafica la imagen de Game Over al perder en el juego
	 */
	public void finDelJuego(Graphics g) {
		g.drawImage(img_gameOver, 0, 0, Inicio.getInstance().getWidth(), Inicio.getInstance().getHeight(), this);
	}
	/**
	 * Grfica el/los pasteles en sus respectivas pocisiones
	 */
	public void pintarPastel(Graphics g, int ancho, int alto) {
		Nicelander nicelander = Juego.getInstance().getEdificio().getNicelander();
		ArrayList<Pastel> listaPastel = nicelander.getPasteles();
		for (int i = 0; i < listaPastel.size(); i++) {
			Pastel pastel = listaPastel.get(i);
			g.drawImage(img_pastel, (pastel.getPos().getX() + 1) * ancho -29, (pastel.getPos().getY() + 2) * alto,
					img_pastel.getWidth(null) + 30, img_pastel.getHeight(null) + 30, null);
		}

	}
	/**
	 * Grafica todas las ventanas 
	 * @param g
	 * @param ancho
	 * @param alto
	 */
	private void pintarSeccion(Graphics g, int ancho, int alto) {
		Ventana[][] seccion = Juego.getInstance().getEdificio().getSeccionActual().getVentanas();
		cargarSeccActual(seccion);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				g.drawImage(this.secActual[j][i], (seccion[j][i].getPos().getX()) * ancho + 64,
						(seccion[j][i].getPos().getY() + 1) * alto + 90, this.estadoVentana[0].getWidth(null) * 2,
						this.estadoVentana[0].getHeight(null) * 2, null);// pos x(j) pos y(i)
			}
		}
		if (this.edificioActual != this.estadoEdificio[2]) { // no pone ventananas en la ultima seccion
			for (int i = 0; i < 5; i++) {
				g.drawImage(this.estadoVentana[0], (i) * ancho + 64, 59, this.estadoVentana[0].getWidth(null) * 2,
						this.estadoVentana[0].getHeight(null) * 2, null);
			}
		}
		pintarObstaculos(g, seccion, ancho, alto); // agrgado nuevo
	}
	/**
	 * Grafica los obstaculos sobre las ventanas
	 * @param g
	 * @param ventanas
	 * @param ancho
	 * @param alto
	 */
	private void pintarObstaculos(Graphics g, Ventana[][] ventanas, int ancho, int alto) {// agregado nuevo
		int posicion = 0;
		Image obstaculoActual = null;
		boolean pintar;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				switch (ventanas[j][i].getObstaculo().getClass().getName()) {
				case ("escenario.Macetero"): {
					posicion = 2;
					obstaculoActual = this.obstaculos[0];
					pintar = true;
					break;
				}
				case ("escenario.Moldura"): {
					posicion = 1;
					obstaculoActual = this.obstaculos[1];
					pintar = true;
					break;
				}
				default:
					pintar = false;
				}
				if (pintar) {
					g.drawImage(obstaculoActual, ventanas[j][i].getPos().getX() * ancho + 64,
							(ventanas[j][i].getPos().getY() + 1) * alto + 90 * posicion,
							this.estadoVentana[0].getWidth(null) * 2, obstaculoActual.getHeight(null) * 2, null);
				}
			}
		}
	}
	/**
	 * Grafica a los enemigos
	 * @param g
	 * @param ancho
	 * @param alto
	 */
	private void pintarEnemigos(Graphics g, int ancho, int alto) {
		ArrayList<Enemigo> lista = Juego.getInstance().getEdificio().getListaEnemigos();
		for (int i = 0; i < lista.size(); i++) {
			Enemigo elemento = lista.get(i);
			switch (elemento.getClass().getName()) {
			case ("personajes.Ladrillo"):
				g.drawImage(img_ladrillo, (elemento.getPosX() + 1) * ancho, (elemento.getPosY() + 2) * alto,
						img_ladrillo.getWidth(this) * 2, img_ladrillo.getHeight(this) * 2, this);
				break;
			case ("personajes.Pajaro"):
				Pajaro paj = (Pajaro) elemento;
				if (paj.getMov() == Movimiento.DERECHA) {
					g.drawImage(img_pajaro, (elemento.getPosX()+1) * ancho-25, (elemento.getPosY() + 2) * alto,
							img_pajaro.getWidth(this), img_pajaro.getWidth(this), this);
				} else
					g.drawImage(img_pajaro, (elemento.getPosX()+1) * ancho+10, (elemento.getPosY() + 2) * alto,
							-img_pajaro.getWidth(this), img_pajaro.getWidth(this), this);
				break;
			case ("personajes.Ralph"):
				if (Juego.getInstance().getEdificio().decirSeccion() != 2)
					g.drawImage(img_ralph, (elemento.getPosX()) * ancho + 30, alto - 90, img_ralph.getWidth(null) * 2,
							img_ralph.getHeight(null) * 2 - 20, this);
					else
						g.drawImage(img_ralph, (elemento.getPosX()) * ancho + 30, alto - 120, img_ralph.getWidth(null) * 2,
								img_ralph.getHeight(null) * 2 - 20, this);
					break;
			}
		}

	}
	/**
	 * Grafica a felix
	 * @param g
	 * @param ancho
	 * @param alto
	 */
	private void pintarFelix(Graphics g, int ancho, int alto) {
		Felix fel = Felix.getInstance();
		g.drawImage(img_felix, (fel.getPos().getX() * ancho) + 64, (fel.getPos().getY() + 1) * alto + 90,
				img_felix.getWidth(this) * 2, img_felix.getHeight(this) * 2, this);
	}
	/**
	 * Carga una matriz con las imagenes correspondientes a cada ventana
	 * @param ventana
	 */
	private void cargarSeccActual(Ventana[][] ventana) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				switch (ventana[i][j].getClass().getName()) {
				case ("escenario.DoblePanel"): {
					this.secActual[i][j] = this.estadoVentana[ventana[i][j].cantidadRotos()];
					break;
				}
				case ("escenario.VentanaHoja"): {
					this.secActual[i][j] = this.estadoHoja[ventana[i][j].cantidadRotos()];
					break;
				}
				case ("escenario.Balcon"): {
					this.secActual[i][j] = this.estadoBalcon[ventana[i][j].cantidadRotos()];
					break;
				}
				case ("escenario.Puerta"): {
					this.secActual[i][j] = this.estadoPuerta[ventana[i][j].cantidadRotos()];
				}
				}
			}
		}
	}
	private void pintarTiempoYVida(Graphics g) {
		g.setColor(Color.WHITE);
		//g.fillRect(1, 1, 120, 40);
		//g.setColor(Color.BLACK);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 30));
		g.drawString("" + Juego.getInstance().getNivel().getTiempo(), this.getWidth()/2 - 35, 32);
		g.setFont(new Font ("Times New Roman", Font.BOLD, 15));
		g.drawString("VIDAS: " + Felix.getInstance().getVidas(), 62, 30);
	}
	/**
	 * Carga todas las imagenes de las ventanas
	 */
	private void cargarEstadoVentana() {
		this.estadoVentana[0] = loadImage("/imagenes/ventanaSana.png");
		this.estadoVentana[1] = loadImage("/imagenes/ventanaRota1.png");
		this.estadoVentana[2] = loadImage("/imagenes/ventanaRota2.png");
		this.estadoVentana[3] = loadImage("/imagenes/ventanaRota3.png");
		this.estadoVentana[4] = loadImage("/imagenes/ventanaRota.png");
	}
	/**
	 * Carga las imagenes de las hojas
	 */
	private void cargarEstadoHoja() {
		this.estadoHoja[0] = loadImage("/imagenes/ventanaHojaAbierta.png");
		this.estadoHoja[1] = loadImage("/imagenes/ventanaHojaCerrada.png");
	}
	/**
	 * Carga todas las imagenes de las puerta
	 */
	private void cargarEstadoPuerta() {
		this.estadoPuerta[0] = loadImage("/imagenes/puertaSana.png");
		this.estadoPuerta[1] = loadImage("/imagenes/puertaRota1.png");
		this.estadoPuerta[2] = loadImage("/imagenes/puertaRota2.png");
		this.estadoPuerta[3] = loadImage("/imagenes/puertaRota3.png");
		this.estadoPuerta[4] = loadImage("/imagenes/puertaRota3.png"); // faltan imagenes
		this.estadoPuerta[5] = loadImage("/imagenes/puertaRota3.png");
		this.estadoPuerta[6] = loadImage("/imagenes/puertaRota3.png");
		this.estadoPuerta[7] = loadImage("/imagenes/puertaRota3.png");
		this.estadoPuerta[8] = loadImage("/imagenes/puertaRota3.png");
	}
	/**
	 * Carga todas las imagenes del balcon
	 */
	private void cargarEstadoBalcon() {
		this.estadoBalcon[0] = loadImage("/imagenes/balconSano.png");
		this.estadoBalcon[1] = loadImage("/imagenes/balconRoto1.png");
		this.estadoBalcon[2] = loadImage("/imagenes/balconRoto2.png");
		this.estadoBalcon[3] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[4] = loadImage("/imagenes/balconRoto3.png"); // faltan imagenes
		this.estadoBalcon[5] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[6] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[7] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[8] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[9] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[10] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[11] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[12] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[13] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[14] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[15] = loadImage("/imagenes/balconRoto3.png");
		this.estadoBalcon[16] = loadImage("/imagenes/balconRoto3.png");
	}
	/**
	 * Carga las imagenes de los obstaculos
	 */
	private void cargarObstaculos() { // agregado nuevo
		this.obstaculos[0] = loadImage("/imagenes/macetero.png");
		this.obstaculos[1] = loadImage("/imagenes/moldura.png");
	}
	/**
	 * Carga las imagenes del edificio
	 */
	private void cargarEstadoEdificio() { // agregado nuevo
		this.estadoEdificio[0] = loadImage("/imagenes/edificioAbajo.png");
		this.estadoEdificio[1] = loadImage("/imagenes/edificioMedio.png");
		this.estadoEdificio[2] = loadImage("/imagenes/edificioArriba.png");
	}

}