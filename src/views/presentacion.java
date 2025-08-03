package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class presentacion implements ActionListener {
    JFrame ventana;
    JPanel panelPresentacion;
    JMenuBar menuBar;
    JMenu mnu_archivo, mnu_modulos, mnu_reportes;
    JMenuItem mni_abrir, mni_salir;
    JMenuItem mni_cliente, mni_vendedor, mni_provincia, mni_departamento;
    JMenuItem mni_reporteClientes, mni_reporteVendedores;

    public presentacion() {
        ventana = new JFrame("Presentación");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setLayout(new BorderLayout());


        menuBar = new JMenuBar();
        mnu_archivo = new JMenu("Archivo");

        mni_abrir = new JMenuItem("Abrir");
        mni_abrir.addActionListener(this);

        mni_salir = new JMenuItem("Salir");
        mni_salir.addActionListener(this);

        mnu_archivo.add(mni_abrir);
        mnu_archivo.add(mni_salir);
        menuBar.add(mnu_archivo);


        mnu_modulos = new JMenu("Módulos");

        mni_cliente = new JMenuItem("Cliente");
        mni_cliente.addActionListener(this);

        mni_vendedor = new JMenuItem("Vendedor");
        mni_vendedor.addActionListener(this);

        mni_provincia = new JMenuItem("Provincia");
        mni_provincia.addActionListener(this);

        mni_departamento = new JMenuItem("Departamento");
        mni_departamento.addActionListener(this);

        mnu_modulos.add(mni_provincia);
        mnu_modulos.add(mni_cliente);
        mnu_modulos.add(mni_vendedor);
        mnu_modulos.add(mni_departamento);
        menuBar.add(mnu_modulos);

        mnu_reportes = new JMenu("Reportes");
        mni_reporteClientes = new JMenuItem("Reporte de Clientes");
        mni_reporteClientes.addActionListener(this);

        mni_reporteVendedores = new JMenuItem("Reporte de Vendedores");
        mni_reporteVendedores.addActionListener(this);

        mnu_reportes.add(mni_reporteClientes);
        mnu_reportes.add(mni_reporteVendedores);
        menuBar.add(mnu_reportes);

        ventana.setJMenuBar(menuBar);

        configurarPresentacion();
        ventana.setVisible(true);
    }

    public void configurarPresentacion() {
        panelPresentacion = new JPanel();
        panelPresentacion.setLayout(new BoxLayout(panelPresentacion, BoxLayout.Y_AXIS));
        panelPresentacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPresentacion.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel uni = new JLabel("Universidad Tecnología de Panamá", SwingConstants.CENTER);
        JLabel facu = new JLabel("Facultad de Ingeniería en Sistemas", SwingConstants.CENTER);
        JLabel nombre = new JLabel(" Nombre ", SwingConstants.CENTER);
        JLabel nombre1 = new JLabel("Alexander Castroverde", SwingConstants.CENTER);
        JLabel cedula = new JLabel(" Cédula ", SwingConstants.CENTER);
        JLabel cedula1 = new JLabel("8-1017-805", SwingConstants.CENTER);
        JLabel materia = new JLabel(" Materia ", SwingConstants.CENTER);
        JLabel materia1 = new JLabel("Desarrollo de Software", SwingConstants.CENTER);
        JLabel docente = new JLabel(" Docente ", SwingConstants.CENTER);
        JLabel docente1 = new JLabel("Ing. Ricardo Chan", SwingConstants.CENTER);
        JLabel proyecto = new JLabel("Proyecto final ", SwingConstants.CENTER);

        JLabel[] etiquetas = {
                uni, facu, nombre, nombre1, cedula, cedula1, materia, materia1, docente, docente1, proyecto
        };
        for (JLabel lbl : etiquetas) {
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelPresentacion.add(lbl);
        }

        ventana.add(panelPresentacion, BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        if (fuente == mni_salir) {
            System.exit(0);
        } else if (fuente == mni_abrir) {
            JOptionPane.showMessageDialog(ventana, "Has hecho clic en 'Abrir'");
        } else if (fuente == mni_cliente) {
            new ClienteView();
        }else if (fuente == mni_vendedor) {
                new VendedorView();
        }else if (fuente == mni_provincia) {
            new provincia();
        }else if (fuente == mni_departamento) {
            new DepartamentoView();
        }



    }

    private void abrirModulo(String titulo) {
        JFrame frameModulo = new JFrame(titulo);
        frameModulo.setSize(600, 300);
        frameModulo.setLocationRelativeTo(ventana);
        frameModulo.add(new JLabel(titulo, SwingConstants.CENTER), BorderLayout.CENTER);
        frameModulo.setVisible(true);
    }
}


