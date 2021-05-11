package VentanaInicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controladores.ListenerCambioNivel;
import controladores.ListenerInicio;

public class PanelConfiguracion extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel texto;
	private JComboBox<Integer> niveles;
	private JButton boton;

	public PanelConfiguracion() {
		super(new GridBagLayout());
		this.setBackground(Color.lightGray);
		GridBagConstraints grid = new GridBagConstraints();

		texto = new JLabel("Configuracion");
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		texto.setFont(new Font("Trebuchet MS", Font.BOLD, 25));

		grid.gridx = 1;
		grid.gridy = 0;
		grid.gridwidth = 1; // cantidad de columnas que quiero que ocupe
		grid.gridheight = 1; // cantida de filas que quiero que ocupe
		this.add(this.texto, grid);

		texto = new JLabel("Nivel");
		texto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		texto.setHorizontalAlignment(SwingConstants.LEFT);

		grid.gridx = 0;
		grid.gridy = 1;
		grid.gridwidth = 1; // cantidad de columnas que quiero que ocupe
		grid.gridheight = 1; // cantida de filas que quiero que ocupe
		this.add(this.texto, grid);

		niveles = new JComboBox<Integer>();
		niveles.setBounds(50, 100, 90, 20);
		niveles.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 , 9, 10}));
		niveles.addActionListener(new ListenerCambioNivel());

		grid.gridx = 1;
		grid.gridy = 1;
		grid.gridwidth = 1;
		grid.gridheight = 1;
		grid.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.niveles, grid);

		ImageIcon volver = new ImageIcon(getClass().getResource("/imagenes/flecha.png"));
		boton = new JButton(volver);
		boton.setBackground(Color.lightGray);
		boton.setBorder(null);
		boton.addMouseListener(new ListenerInicio());
		grid.gridx = 1;
		grid.gridy = 2;
		grid.gridwidth = 1; // cantidad de columnas que quiero que ocupe
		grid.gridheight = 1; // cantida de filas que quiero que ocupe
		grid.fill = GridBagConstraints.NONE;
		this.add(this.boton, grid);

	}
}
