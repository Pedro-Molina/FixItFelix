package personajes;

import escenario.*;
import manejo.Juego;

import java.util.*;

public class Nicelander {
	private ArrayList<Pastel> pasteles = new ArrayList<Pastel>();

	public void ponerPastel() {
		Ventana[][] mSeccion = Juego.getInstance().getEdificio().getSeccionActual().getVentanas();
		outerLoop: for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				Ventana v=mSeccion[i][j];
				if (mSeccion[i][j].cantidadRotos()>=2
						&& (!hayPastel(v.getPos().getX(), v.getPos().getY())&& (v.puedeHaberPastel()))) {
					pasteles.add(new Pastel(v.getPos().getX(), v.getPos().getY()));
					break outerLoop;
				}
			}
		}
	}

	public void ejecutarPasteles() {
		for (int i = 0; i < pasteles.size(); i++) {
			pasteles.get(i).estaFelix();
		}
	}

	public ArrayList<Pastel> getPasteles() {
		return pasteles;
	}

	public boolean hayPastel(int x, int y) {
		int i;
		for (i = 0; i < pasteles.size(); i++) {
			Pastel pastel = pasteles.get(i);
			if (pastel.getPos().getX() == x && pastel.getPos().getY() == y) {
				return true;
			}
		}
		return false;
	}
}
