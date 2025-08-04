package views;

import controllers.ProvinciaController;
import modules.Provincia;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProvinciaView extends JFrame implements ActionListener {
    private final JTextField txtCodigo, txtDescripcion;
    private final JButton btnLimpiar, btnBuscar, btnAgregar,
            btnModificar, btnEliminar, btnListar;
    private final ProvinciaController controller;
    private int currentId = -1;  // guardamos el id tras buscar

    public ProvinciaView() {
        super("Mantenimiento de Provincias");
        controller = new ProvinciaController();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        // Panel de datos
        JPanel panelForm = new JPanel(new GridLayout(2,2,10,10));
        panelForm.setBorder(new TitledBorder("Datos de la Provincia"));
        txtCodigo      = new JTextField();
        txtDescripcion = new JTextField();
        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Descripción:"));
        panelForm.add(txtDescripcion);
        add(panelForm, BorderLayout.CENTER);

        // Panel de acciones
        JPanel panelButtons = new JPanel(new GridLayout(2,3,10,10));
        panelButtons.setBorder(new TitledBorder("Acciones"));
        btnLimpiar   = new JButton("Limpiar");
        btnBuscar    = new JButton("Buscar");
        btnAgregar   = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar  = new JButton("Eliminar");
        btnListar    = new JButton("Listar");
        for (JButton b : new JButton[]{btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar}) {
            b.addActionListener(this);
            panelButtons.add(b);
        }
        add(panelButtons, BorderLayout.SOUTH);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtCodigo.setEditable(true);
        txtCodigo.setText("");
        txtDescripcion.setText("");
        currentId = -1;

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String codigo = txtCodigo.getText().trim();
        String desc   = txtDescripcion.getText().trim();

        if (e.getSource() == btnBuscar) {
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el código de la provincia.");
                return;
            }
            Provincia p = controller.viewProvincia(codigo);
            if (p != null) {
                currentId = p.getId();
                txtDescripcion.setText(p.getDescripcion());
                txtCodigo.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Provincia no encontrada. Puede agregarla.");
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }

        } else if (e.getSource() == btnAgregar) {
            if (codigo.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }
            controller.createProvincia(codigo, desc);
            JOptionPane.showMessageDialog(this, "Provincia creada correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnModificar) {
            if (currentId < 0) return;
            controller.updateProvincia(currentId, codigo, desc);
            JOptionPane.showMessageDialog(this, "Provincia actualizada correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnEliminar) {
            controller.deleteProvincia(codigo);
            JOptionPane.showMessageDialog(this, "Provincia eliminada correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnLimpiar) {
            estadoInicial();

        } else if (e.getSource() == btnListar) {
            List<Provincia> lista = controller.listProvincias();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay provincias registradas.");
                return;
            }
            String[] cols = {"Código", "Descripción"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            for (Provincia pr : lista) {
                model.addRow(new Object[]{pr.getCodigo(), pr.getDescripcion()});
            }
            JTable table = new JTable(model);
            JScrollPane pane = new JScrollPane(table);
            pane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, pane, "Listado de Provincias", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
