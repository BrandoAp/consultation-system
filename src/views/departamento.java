package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class departamento extends JFrame implements ActionListener {
    private JTextField txtId, txtCodigo, txtDescripcion;
    private JButton btnLimpiar, btnBuscar, btnAgregar, btnModificar, btnEliminar, btnListar;
    private HashMap<String, Departamento> bdDepartamentos = new HashMap<>();

    public departamento() {
        setTitle("Mantenimiento de Departamento");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        txtId = new JTextField();
        txtCodigo = new JTextField();
        txtDescripcion = new JTextField();

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

        add(new JLabel("ID Departamento:")); add(txtId);
        add(new JLabel("Código:")); add(txtCodigo);
        add(new JLabel("Descripción:")); add(txtDescripcion);
        add(btnLimpiar); add(btnBuscar);
        add(btnAgregar); add(btnModificar);
        add(btnEliminar); add(btnListar);

        estadoInicial();
        setVisible(true);
    }

    private void estadoInicial() {
        txtId.setEditable(true);
        txtCodigo.setText("");
        txtDescripcion.setText("");

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
    }


    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String id = txtId.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (src == btnBuscar) {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID del departamento.");
                return;
            }

            if (bdDepartamentos.containsKey(id)) {
                Departamento depto = bdDepartamentos.get(id);
                txtCodigo.setText(depto.codigo);
                txtDescripcion.setText(depto.descripcion);
                txtId.setEditable(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnAgregar.setEnabled(false);
                btnBuscar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Departamento no encontrado. Puede agregarlo.");
                btnAgregar.setEnabled(true);
            }

        } else if (src == btnAgregar) {
            if (codigo.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            bdDepartamentos.put(id, new Departamento(id, codigo, descripcion));
            JOptionPane.showMessageDialog(this, "Departamento agregado correctamente.");
            estadoInicial();

        } else if (src == btnModificar) {
            bdDepartamentos.put(id, new Departamento(id, codigo, descripcion));
            JOptionPane.showMessageDialog(this, "Departamento modificado correctamente.");
            estadoInicial();

        } else if (src == btnEliminar) {
            bdDepartamentos.remove(id);
            JOptionPane.showMessageDialog(this, "Departamento eliminado correctamente.");
            estadoInicial();

        } else if (src == btnLimpiar) {
            txtId.setText("");
            txtCodigo.setText("");
            txtDescripcion.setText("");
            estadoInicial();

        } else if (src == btnListar) {
            if (bdDepartamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay departamentos registrados.");
            } else {
                StringBuilder lista = new StringBuilder("Departamentos:\n");
                for (String clave : bdDepartamentos.keySet()) {
                    Departamento d = bdDepartamentos.get(clave);
                    lista.append("ID: ").append(d.id)
                            .append(", Código: ").append(d.codigo)
                            .append(", Descripción: ").append(d.descripcion)
                            .append("\n");
                }
                JOptionPane.showMessageDialog(this, lista.toString());
            }
        }
    }

    class Departamento {
        String id, codigo, descripcion;

        Departamento(String id, String codigo, String descripcion) {
            this.id = id;
            this.codigo = codigo;
            this.descripcion = descripcion;
        }
    }
}

