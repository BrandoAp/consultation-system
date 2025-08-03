package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class reportesvendedores extends JDialog implements ActionListener {
    JRadioButton rbApellido, rbCodigo, rbDepartamento;
    JButton btnGenerar;
    ButtonGroup grupo;

    public reportesvendedores(JFrame parent) {
        super(parent, "Reporte de Vendedores", true);
        setLayout(new GridLayout(5, 1, 10, 10));
        setSize(300, 250);
        setLocationRelativeTo(parent);

        rbApellido = new JRadioButton("Ordenar por Apellido");
        rbCodigo = new JRadioButton("Ordenar por Código");
        rbDepartamento = new JRadioButton("Ordenar por Departamento");

        grupo = new ButtonGroup();
        grupo.add(rbApellido);
        grupo.add(rbCodigo);
        grupo.add(rbDepartamento);

        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(this);

        add(new JLabel("Seleccione el criterio de ordenamiento:", SwingConstants.CENTER));
        add(rbApellido);
        add(rbCodigo);
        add(rbDepartamento);
        add(btnGenerar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String criterio = "";

        if (rbApellido.isSelected())
            criterio = "apellido";
        else if (rbCodigo.isSelected())
            criterio = "codigo";
        else if (rbDepartamento.isSelected())
            criterio = "departamento";

        if (!criterio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Reporte generado por: " + criterio);
            // Aquí iría la llamada real a JasperReport
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un criterio.");
        }
    }
}

