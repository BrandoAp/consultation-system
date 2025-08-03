package views;

import controllers.DepartamentoController;
import modules.Departamento;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DepartamentoView extends JFrame implements ActionListener {
    private final JTextField txtCodigo, txtDescripcion;
    private final JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private final DepartamentoController controller;

    public DepartamentoView() {
        super("Mantenimiento de Departamento");
        controller = new DepartamentoController();

        // Configuración básica
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel formulario con título
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));
        panelForm.setBorder(new TitledBorder("Datos del Departamento"));
        txtCodigo      = new JTextField();
        txtDescripcion = new JTextField();
        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Descripción:"));
        panelForm.add(txtDescripcion);
        add(panelForm, BorderLayout.CENTER);

        // Panel botones con título
        JPanel panelButtons = new JPanel(new GridLayout(2, 3, 10, 10));
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

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String codigo      = txtCodigo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (e.getSource() == btnBuscar) {
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el código del departamento.");
                return;
            }
            Departamento depto = controller.viewDepto(codigo);
            if (depto != null) {
                txtDescripcion.setText(depto.getDescripcion());
                txtCodigo.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Departamento no encontrado. Puede agregarlo.");
                btnAgregar.setEnabled(true);
            }

        } else if (e.getSource() == btnAgregar) {
            if (codigo.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }
            controller.createDepartamento(codigo, descripcion);
            JOptionPane.showMessageDialog(this, "Departamento agregado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnModificar) {
            controller.updateDepartamento(codigo, descripcion);
            JOptionPane.showMessageDialog(this, "Departamento modificado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnEliminar) {
            controller.deleteDepto(codigo);
            JOptionPane.showMessageDialog(this, "Departamento eliminado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnLimpiar) {
            estadoInicial();

        } else if (e.getSource() == btnListar) {
            List<Departamento> lista = controller.listDepto();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay departamentos registrados.");
                return;
            }
            String[] cols = {"Código", "Descripción"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            for (Departamento d : lista) {
                model.addRow(new Object[]{ d.getCodigo(), d.getDescripcion() });
            }
            JTable table = new JTable(model);
            JScrollPane pane = new JScrollPane(table);
            pane.setPreferredSize(new Dimension(400, 200));
            JOptionPane.showMessageDialog(this, pane, "Departamentos", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
