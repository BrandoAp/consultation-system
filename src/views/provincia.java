package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class provincia extends JFrame implements ActionListener {
    private JTextField txtCodigo, txtNombre;
    private JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private HashMap<String, String> bdProvincias = new HashMap<>();

    public provincia() {
        setTitle("Mantenimiento de Provincia");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();

        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        btnLimpiar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnAgregar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnListar.addActionListener(this);

        add(new JLabel("Código:")); add(txtCodigo);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(btnLimpiar); add(btnBuscar);
        add(btnAgregar); add(btnModificar);
        add(btnEliminar); add(btnListar);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtCodigo.setEditable(true);
        txtNombre.setText("");

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (src == btnBuscar) {
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el código de la provincia.");
                return;
            }

            if (bdProvincias.containsKey(codigo)) {
                txtNombre.setText(bdProvincias.get(codigo));
                txtCodigo.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Provincia no encontrada. Puede agregarla.");
                btnAgregar.setEnabled(true);
            }

        } else if (src == btnAgregar) {
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre de la provincia.");
                return;
            }

            bdProvincias.put(codigo, nombre);
            JOptionPane.showMessageDialog(this, "Provincia agregada correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
            bdProvincias.put(codigo, nombre);
            JOptionPane.showMessageDialog(this, "Provincia modificada correctamente.");
            estadoInicial();

        } else if (src == btnEliminar) {
            bdProvincias.remove(codigo);
            JOptionPane.showMessageDialog(this, "Provincia eliminada correctamente.");
            estadoInicial();

        } else if (src == btnLimpiar) {
            txtCodigo.setText("");
            txtNombre.setText("");
            estadoInicial();

        } else if (src == btnListar) {
            if (bdProvincias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay provincias registradas.");
            } else {
                StringBuilder lista = new StringBuilder("Provincias registradas:\n");
                for (String c : bdProvincias.keySet()) {
                    lista.append(c).append(": ").append(bdProvincias.get(c)).append("\n");
                }
                JOptionPane.showMessageDialog(this, lista.toString());
            }
        }
    }
}
