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

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de campos con GridBagLayout
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

        // Panel de botones
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

        // Listeners
        btnLimpiar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnAgregar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnListar.addActionListener(this);

        // Añadir paneles al principal
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

        try {
            compraAnual = Integer.parseInt(txtCompraAnual.getText().trim());
        } catch (NumberFormatException ex) {
            // Si no es un número válido, se queda en 0
        }

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
            if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios (cédula, nombre, apellido).");
                return;
            }

            controller.createClient(cedula, nombre, apellido, direccion, telefono, provincia, compraAnual);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
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

            // Tabla
            String[] columnas = {"Cédula", "Nombre", "Apellido", "Teléfono", "Provincia"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);

            for (Cliente c : clientes) {
                model.addRow(new Object[]{
                        c.getCedula(),
                        c.getNombre(),
                        c.getApellido(),
                        c.getTelefono(),
                        c.getProvincia_codigo()
                });
            }

            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(600, 300));

            JOptionPane.showMessageDialog(this, scrollPane, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
