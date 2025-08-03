package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Cliente extends JFrame implements ActionListener {
    private JTextField txtCedula, txtNombre, txtApellido;
    private JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private HashMap<String, String[]> bdClientes = new HashMap<>();

    public Cliente() {
        setTitle("Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Campos
        txtCedula = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();

        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        // Listeners
        btnLimpiar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnAgregar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnListar.addActionListener(this);

        // UI
        add(new JLabel("Cédula:")); add(txtCedula);
        add(new JLabel("Nombre:")); add(txtNombre);
        add(new JLabel("Apellido:")); add(txtApellido);
        add(btnLimpiar); add(btnBuscar);
        add(btnAgregar); add(btnModificar);
        add(btnEliminar); add(btnListar);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtCedula.setEditable(true);
        txtNombre.setText("");
        txtApellido.setText("");

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (src == btnBuscar) {
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente.");
                return;
            }

            if (bdClientes.containsKey(cedula)) {
                String[] datos = bdClientes.get(cedula);
                txtNombre.setText(datos[0]);
                txtApellido.setText(datos[1]);

                txtCedula.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado. Puede agregarlo.");
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }

        } else if (src == btnAgregar) {
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            bdClientes.put(cedula, new String[]{nombre, apellido});
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
            bdClientes.put(cedula, new String[]{nombre, apellido});
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
            estadoInicial();

        } else if (src == btnEliminar) {
            bdClientes.remove(cedula);
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
            estadoInicial();

        } else if (src == btnLimpiar) {
            txtCedula.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            estadoInicial();

        } else if (src == btnListar) {
            if (bdClientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay clientes registrados.");
            } else {
                StringBuilder lista = new StringBuilder("Clientes registrados:\n");
                for (String c : bdClientes.keySet()) {
                    String[] datos = bdClientes.get(c);
                    lista.append(c).append(": ").append(datos[0]).append(" ").append(datos[1]).append("\n");
                }
                JOptionPane.showMessageDialog(this, lista.toString());
            }
        }
    }
}
