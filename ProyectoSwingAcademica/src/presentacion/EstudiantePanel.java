package presentacion;

import modelo.Estudiante;
import negocio.EstudianteNegocio;
import excepciones.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EstudiantePanel extends javax.swing.JPanel {

    private EstudianteNegocio negocio;
    private DefaultTableModel modeloTabla;

    public EstudiantePanel() {
        initComponents();
        negocio = new EstudianteNegocio();
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Edad", "Carrera", "Genero", "Activo", "Correo", "Telefono", "Observaciones"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEstudiantes.setModel(modeloTabla);

        //Conectar botones con logica
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnEditar.addActionListener(e -> editarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnBuscar.addActionListener(e -> buscarEstudiante());
        btnOrdenarNombre.addActionListener(e -> ordenarPorNombre());
        btnOrdenarEdad.addActionListener(e -> ordenarPorEdad());
        btnExportar.addActionListener(e -> exportarLista());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        //cargar datos al seleccionar fila
        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaEstudiantes.getSelectedRow();
            if (fila >= 0) {
                Estudiante est = negocio.listar().get(fila);
                txtNombre.setText(est.getNombre());
                txtEdad.setText(String.valueOf(est.getEdad()));
                txtCorreo.setText(est.getCorreo());
                txtTelefono.setText(est.getTelefono());
                txtObservaciones.setText(est.getObservaciones());
                cmbCarrera.setSelectedItem(est.getCarrera());
                rbMasculino.setSelected(est.getGenero().equals("Masculino"));
                rbFemenino.setSelected(est.getGenero().equals("Femenino"));
                chkActivo.setSelected(est.isActivo());
            }
        });
    }

    //carga la tabla 
    private void cargarTabla(List<Estudiante> lista) {
        modeloTabla.setRowCount(0);
        for (Estudiante e : lista) {
            modeloTabla.addRow(new Object[]{
                e.getId(), e.getNombre(), e.getEdad(), e.getCarrera(),
                e.getGenero(), e.isActivo(), e.getCorreo(),
                e.getTelefono(), e.getObservaciones()
            });
        }
    }

    //Se agrega el estudiante con su respectiva informacion a la tabla
    private void agregarEstudiante() {
        try {
            Estudiante est = new Estudiante(
                    negocio.listar().size() + 1, // genera ID
                    txtNombre.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    cmbCarrera.getSelectedItem().toString(),
                    rbMasculino.isSelected() ? "Masculino" : "Femenino",
                    chkActivo.isSelected(),
                    txtCorreo.getText(),
                    txtTelefono.getText(),
                    txtObservaciones.getText()
            );
            negocio.agregar(est);
            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente");
            cargarTabla(negocio.listar());
            limpiarFormulario(); //limpia
        } catch (DatoInvalidoException | RegistroDuplicadoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad y telefono deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //carga los datos para poder editarlos y que se carguen nuevamente
    private void editarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila >= 0) {
            try {
                Estudiante est = negocio.listar().get(fila);
                est.setNombre(txtNombre.getText());
                est.setEdad(Integer.parseInt(txtEdad.getText()));
                est.setCarrera(cmbCarrera.getSelectedItem().toString());
                est.setGenero(rbMasculino.isSelected() ? "Masculino" : "Femenino");
                est.setActivo(chkActivo.isSelected());
                est.setCorreo(txtCorreo.getText());
                est.setTelefono(txtTelefono.getText());
                est.setObservaciones(txtObservaciones.getText());

                negocio.editar(est);
                JOptionPane.showMessageDialog(this, "Estudiante editado");
                cargarTabla(negocio.listar()); 
                limpiarFormulario(); // impia campos
            } catch (DatoInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Edad y teléfono deben ser numeros", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para editar");
        }
    }

    // Se selecciona el estudiante que se desea borrar
    private void eliminarEstudiante() {
        int fila = tablaEstudiantes.getSelectedRow();
        if (fila >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar estudiante seleccionado?");
            if (confirm == JOptionPane.YES_OPTION) {
                Estudiante est = negocio.listar().get(fila);
                negocio.eliminar(est);
                cargarTabla(negocio.listar()); //refresca la tabla
                limpiarFormulario(); //limpia campos
                JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un estudiante para eliminar");
        }
    }

    //Busca el estudiante por el nombre
    private void buscarEstudiante() {
        String criterio = txtNombre.getText();
        List<Estudiante> resultado = negocio.buscarPorNombreOCarreraOEstado(criterio);
        cargarTabla(resultado);
    }

    //Ordena estudiante por letra del nombre
    private void ordenarPorNombre() {
        negocio.ordenarPorNombre();
        cargarTabla(negocio.listar());
    }

    //Ordena de menor a mayor 
    private void ordenarPorEdad() {
        negocio.ordenarPorEdad();
        cargarTabla(negocio.listar());
    }

    private void exportarLista() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            java.io.File archivo = chooser.getSelectedFile();
            try (java.io.PrintWriter pw = new java.io.PrintWriter(archivo)) {
                for (Estudiante e : negocio.listar()) {
                    pw.println(e.getId() + "," + e.getNombre() + "," + e.getEdad() + "," + e.getCarrera() + ","
                            + e.getGenero() + "," + e.isActivo() + "," + e.getCorreo() + ","
                            + e.getTelefono() + "," + e.getObservaciones());
                }
                JOptionPane.showMessageDialog(this, "Lista exportada correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Limpia todos los campos
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtObservaciones.setText("");
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        chkActivo.setSelected(false);
        cmbCarrera.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnOrdenarEdad = new javax.swing.JButton();
        btnOrdenarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstudiantes = new javax.swing.JTable();
        cmbCarrera = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();

        btnAgregar.setText("Agregar");

        btnBuscar.setText("Buscar");
        btnBuscar.setActionCommand("");

        btnEditar.setText("Editar");

        btnEliminar.setText("Eliminar");

        btnExportar.setText("Exportar");

        btnLimpiar.setText("Limpiar");

        btnOrdenarEdad.setText("Ordenar por Edad");

        btnOrdenarNombre.setText("Ordenar por Nombre");

        jLabel1.setText("Carrera:");

        jLabel2.setText("Activo:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Edad:");

        jLabel5.setText("telefono:");

        jLabel6.setText("Correo:");

        jLabel7.setText("Genero:");

        jLabel8.setText("Observaciones:");

        chkActivo.setText("Activo");

        rbMasculino.setText("Masculino");

        rbFemenino.setText(" Femenino");

        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaEstudiantes);

        cmbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieria de sistemas", "Derecho", "Informatica", "Ingenieria civil" }));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkActivo)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(rbMasculino)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rbFemenino))
                                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cmbCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnOrdenarEdad, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnOrdenarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1))
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOrdenarNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(btnOrdenarEdad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rbMasculino)
                            .addComponent(rbFemenino))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(chkActivo))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnOrdenarEdad;
    private javax.swing.JButton btnOrdenarNombre;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JComboBox<String> cmbCarrera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tablaEstudiantes;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
