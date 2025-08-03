package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class vendedor extends JFrame implements ActionListener {
    private JTextField txtCodigo, txtNombre, txtApellido;
    private JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private HashMap<String, String[]> bdVendedores = new HashMap<>();

    public vendedor() {
        setTitle("Vendedor");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();

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
        add(new JLabel("Apellido:")); add(txtApellido);
        add(btnLimpiar); add(btnBuscar);
        add(btnAgregar); add(btnModificar);
        add(btnEliminar); add(btnListar);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtCodigo.setEditable(true);
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

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (src == btnBuscar) {
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el código del vendedor.");
                return;
            }

            if (bdVendedores.containsKey(codigo)) {
                String[] datos = bdVendedores.get(codigo);
                txtNombre.setText(datos[0]);
                txtApellido.setText(datos[1]);

                txtCodigo.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Vendedor no encontrado. Puede agregarlo.");
                btnAgregar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }

        } else if (src == btnAgregar) {
            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            bdVendedores.put(codigo, new String[]{nombre, apellido});
            JOptionPane.showMessageDialog(this, "Vendedor agregado correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
            bdVendedores.put(codigo, new String[]{nombre, apellido});
            JOptionPane.showMessageDialog(this, "Vendedor modificado correctamente.");
            estadoInicial();

        } else if (src == btnEliminar) {
            bdVendedores.remove(codigo);
            JOptionPane.showMessageDialog(this, "Vendedor eliminado correctamente.");
            estadoInicial();

        } else if (src == btnLimpiar) {
            txtCodigo.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            estadoInicial();

        } else if (src == btnListar) {
            if (bdVendedores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay vendedores registrados.");
            } else {
                StringBuilder lista = new StringBuilder("Vendedores registrados:\n");
                for (String c : bdVendedores.keySet()) {
                    String[] datos = bdVendedores.get(c);
                    lista.append(c).append(": ").append(datos[0]).append(" ").append(datos[1]).append("\n");
                }
                JOptionPane.showMessageDialog(this, lista.toString());
            }
        }
    }
}

