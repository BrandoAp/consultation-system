package views;

import controllers.VendedorController;
import modules.Vendedor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VendedorView extends JFrame implements ActionListener {
    private final JTextField txtCodigo, txtNombre, txtApellido,
            txtDepartamentoCodigo, txtCargo, txtVentaMensual, txtVentaAnual;
    private final JButton btnLimpiar, btnBuscar, btnAgregar,
            btnModificar, btnEliminar, btnListar;
    private final VendedorController controller;

    public VendedorView() {
        super("Gestión de Vendedores");
        controller = new VendedorController();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel de datos
        JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));
        panelForm.setBorder(new TitledBorder("Datos del Vendedor"));
        txtCodigo = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDepartamentoCodigo = new JTextField();
        txtCargo = new JTextField();
        txtVentaMensual = new JTextField();
        txtVentaAnual = new JTextField();

        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Apellido:"));
        panelForm.add(txtApellido);
        panelForm.add(new JLabel("Departamento Código:"));
        panelForm.add(txtDepartamentoCodigo);
        panelForm.add(new JLabel("Cargo:"));
        panelForm.add(txtCargo);
        panelForm.add(new JLabel("Venta Mensual:"));
        panelForm.add(txtVentaMensual);
        panelForm.add(new JLabel("Venta Anual:"));
        panelForm.add(txtVentaAnual);

        add(panelForm, BorderLayout.CENTER);

        // Panel de acciones
        JPanel panelButtons = new JPanel(new GridLayout(2, 3, 10, 10));
        panelButtons.setBorder(new TitledBorder("Acciones"));
        btnLimpiar   = new JButton("Limpiar");
        btnBuscar    = new JButton("Buscar");
        btnAgregar   = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar  = new JButton("Eliminar");
        btnListar    = new JButton("Listar");

        for (JButton b : new JButton[]{
                btnLimpiar, btnBuscar, btnAgregar,
                btnModificar, btnEliminar, btnListar
        }) {
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
        txtNombre.setText("");
        txtApellido.setText("");
        txtDepartamentoCodigo.setText("");
        txtCargo.setText("");
        txtVentaMensual.setText("");
        txtVentaAnual.setText("");

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String codigo      = txtCodigo.getText().trim();
        String nombre      = txtNombre.getText().trim();
        String apellido    = txtApellido.getText().trim();
        String deptoCodigo = txtDepartamentoCodigo.getText().trim();
        String cargo       = txtCargo.getText().trim();
        int ventasMensual  = parseInt(txtVentaMensual.getText().trim());
        int ventasAnual    = parseInt(txtVentaAnual.getText().trim());

        if (e.getSource() == btnBuscar) {
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el código del vendedor.");
                return;
            }
            Vendedor v = controller.viewVendedorByCode(codigo);
            if (v != null) {
                // llena campos
                txtNombre.setText(v.getNombre());
                txtApellido.setText(v.getApellido());
                txtDepartamentoCodigo.setText(v.getDepartamento_codigo());
                txtCargo.setText(v.getCargo());
                txtVentaMensual.setText(String.valueOf(v.getVentas_mensuales()));
                txtVentaAnual.setText(String.valueOf(v.getVentas_anuales()));
                // estados
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

        } else if (e.getSource() == btnAgregar) {
            if (anyEmpty(codigo, nombre, apellido, deptoCodigo, cargo,
                    txtVentaMensual.getText(), txtVentaAnual.getText())) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }
            controller.createVendedor(codigo, nombre, apellido,
                    deptoCodigo, cargo, ventasMensual, ventasAnual);
            JOptionPane.showMessageDialog(this, "Vendedor agregado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnModificar) {
            controller.updateVendedor(codigo, nombre, apellido,
                    deptoCodigo, cargo, ventasMensual, ventasAnual);
            JOptionPane.showMessageDialog(this, "Vendedor modificado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnEliminar) {
            controller.deleteVendedor(codigo);
            JOptionPane.showMessageDialog(this, "Vendedor eliminado correctamente.");
            estadoInicial();

        } else if (e.getSource() == btnLimpiar) {
            estadoInicial();

        } else if (e.getSource() == btnListar) {
            List<Vendedor> lista = controller.getVendedores();
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay vendedores registrados.");
                return;
            }
            String[] cols = {"Código", "Nombre", "Apellido", "Depto Código", "Cargo", "Venta Mensual", "Venta Anual"};
            DefaultTableModel model = new DefaultTableModel(cols, 0);
            for (Vendedor v : lista) {
                model.addRow(new Object[]{
                        v.getCodigo(), v.getNombre(), v.getApellido(),
                        v.getDepartamento_codigo(), v.getCargo(),
                        v.getVentas_mensuales(), v.getVentas_anuales()
                });
            }
            JTable table = new JTable(model);
            JScrollPane pane = new JScrollPane(table);
            pane.setPreferredSize(new Dimension(600, 250));
            JOptionPane.showMessageDialog(this, pane, "Listado de Vendedores", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private boolean anyEmpty(String... vals) {
        for (String s : vals) if (s == null || s.isEmpty()) return true;
        return false;
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s); }
        catch (NumberFormatException ex) { return 0; }
    }

}
