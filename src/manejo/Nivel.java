package manejo;

/**
 * Representa el nivel actual en el que se encuetra el jugador
 */
public class Nivel {

	
	
	private int nivel;
	private int velocidadPajaro;
	private int velocidadLadrillo;
	private int velocidadRalph;
	private int freciencaLadrillos;
	private int romperVentana;
	private double obstaculo;
	private int tiempo;
	private int tInicial;

	public Nivel() {
		this.nivel = 1;
		this.obstaculo = 10;
		this.velocidadPajaro = 14;
		this.velocidadRalph=14;
		this.velocidadLadrillo=12;
		this.romperVentana = 50;
		this.freciencaLadrillos = 50;
		this.tInicial = 18000;
		this.tiempo=tInicial;
	}

	
	public int getNivel() {
		return this.nivel;
	}
	
	public double getObstaculo() {
		return obstaculo;
	}

	public int getVelocidadPajaro() {
		return velocidadPajaro;
	}


	public int getVelocidadLadrillo() {
		return velocidadLadrillo;
	}


	public int getVelocidadRalph() {
		return velocidadRalph;
	}

	public int getFreciencaLadrillos() {
		return freciencaLadrillos;
	}

	public int getRomperVentana() {
		return romperVentana;
	}

	public int getTiempo() {
		return tiempo;
	}

	/**
	 * Al pasar de nivel se actualizan las varibles de dificultad de nivel
	 */
	public void aumentarNivel() {

		this.nivel++;
		this.freciencaLadrillos -= 5;
		this.romperVentana += 5;
		this.velocidadLadrillo-=1;
		this.tiempo = tInicial - 1000;
		this.obstaculo += 5;
		
	}


	public void setTiempo(int tiempo) {
		this.tiempo=tiempo;
		
	}

}