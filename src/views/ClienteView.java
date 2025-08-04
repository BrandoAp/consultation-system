package views;

import controllers.ClientController;
import modules.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ClienteView extends JFrame implements ActionListener {
    private JTextField txtCedula, txtNombre, txtApellido, txtDireccion, txtTelefono, txtProvincia, txtCompraAnual;
    private JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private ClientController controller;

    public ClienteView() {
        controller = new ClientController();

        setTitle("Gestión de Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {"Cédula:", "Nombre:", "Apellido:", "Dirección:", "Teléfono:", "Provincia Código:", "Compra Anual:"};
        JTextField[] fields = {
                txtCedula = new JTextField(),
                txtNombre = new JTextField(),
                txtApellido = new JTextField(),
                txtDireccion = new JTextField(),
                txtTelefono = new JTextField(),
                txtProvincia = new JTextField(),
                txtCompraAnual = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.3;
            fieldsPanel.add(new JLabel(labels[i]), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            fieldsPanel.add(fields[i], gbc);
        }

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        buttonsPanel.add(btnLimpiar);
        buttonsPanel.add(btnBuscar);
        buttonsPanel.add(btnAgregar);
        buttonsPanel.add(btnModificar);
        buttonsPanel.add(btnEliminar);
        buttonsPanel.add(btnListar);

        for (JButton b : new JButton[]{btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar}) {
            b.addActionListener(this);
        }

        mainPanel.add(fieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtCedula.setEditable(true);
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtProvincia.setText("");
        txtCompraAnual.setText("");

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    private boolean validarFormatoCedula(String cedula) {
        return cedula.matches("\\d-\\d{4}-\\d{4}");
    }

    private String formatearNombre(String nombre) {
        String s = nombre.trim().replaceAll("\\s+", "");
        if (s.isEmpty()) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private boolean anyEmpty(String... vals) {
        for (String v : vals) if (v == null || v.isEmpty()) return true;
        return false;
    }

    private boolean validarCamposBasicos() {
        String cedula = txtCedula.getText().trim();
        if (!validarFormatoCedula(cedula)) {
            JOptionPane.showMessageDialog(this, "Cédula debe tener formato 8-2075-7520");
            return false;
        }
        String nombre = formatearNombre(txtNombre.getText());
        String apellido = formatearNombre(txtApellido.getText());
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        if (anyEmpty(nombre, apellido)) {
            JOptionPane.showMessageDialog(this, "Nombre y apellido no pueden estar vacíos");
            return false;
        }
        String telefono = txtTelefono.getText().trim();
        if (telefono.length() != 7) {
            JOptionPane.showMessageDialog(this, "Teléfono debe tener exactamente 7 caracteres");
            return false;
        }
        String prov = txtProvincia.getText().trim();
        if (prov.length() != 2) {
            JOptionPane.showMessageDialog(this, "Código de provincia debe tener 2 caracteres");
            return false;
        }
        try {
            Integer.parseInt(txtCompraAnual.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Compra anual debe ser un entero");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String provincia = txtProvincia.getText().trim();
        int compraAnual = 0;
        try { compraAnual = Integer.parseInt(txtCompraAnual.getText().trim()); } catch (Exception ex) {}

        if (src == btnBuscar) {
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente.");
                return;
            }
            Cliente cliente = controller.getClientByCedula(cedula);
            if (cliente != null) {
                txtNombre.setText(cliente.getNombre());
                txtApellido.setText(cliente.getApellido());
                txtDireccion.setText(cliente.getDireccion());
                txtTelefono.setText(cliente.getTelefono());
                txtProvincia.setText(cliente.getProvincia_codigo());
                txtCompraAnual.setText(String.valueOf(cliente.getCompra_anual()));

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
            if (!validarCamposBasicos()) return;
            controller.createClient(cedula, nombre, apellido, direccion, telefono, provincia, compraAnual);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
            if (!validarCamposBasicos()) return;
            controller.updateClient(cedula, nombre, apellido, direccion, telefono, provincia, compraAnual);
            JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
            estadoInicial();

        } else if (src == btnEliminar) {
            Cliente cliente = controller.getClientByCedula(cedula);
            if (cliente != null) {
                controller.deleteClient(cliente.getCedula());
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
            estadoInicial();

        } else if (src == btnLimpiar) {
            estadoInicial();

        } else if (src == btnListar) {
            java.util.List<Cliente> clientes = controller.viewAllClients();
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay clientes registrados.");
                return;
            }
            String[] columnas = {"Cédula", "Nombre", "Apellido", "Teléfono", "Provincia"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);
            for (Cliente c : clientes) {
                model.addRow(new Object[]{
                        c.getCedula(), c.getNombre(), c.getApellido(), c.getTelefono(), c.getProvincia_codigo()
                });
            }
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 300));
            JOptionPane.showMessageDialog(this, scrollPane, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
