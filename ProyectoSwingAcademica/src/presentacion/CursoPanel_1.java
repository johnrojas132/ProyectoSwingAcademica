/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

import javax.swing.JOptionPane;
import modelo.Curso;
import excepciones.DatoInvalidoException;
import excepciones.RegistroDuplicadoException;
import java.util.List;

/**
 *
 * @author Chess
 */
//Panel grafico para gestionar cursos(CRUD: crear, leer, actualizar, eliminar)
public class CursoPanel_1 extends javax.swing.JPanel {
    
    private negocio.CursoNegocio cursoNegocio = new negocio.CursoNegocio();
    private String codigoEnEdicion = null; 
    
    public CursoPanel_1() {
    initComponents();
    cargarTabla();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCodigo = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCreditos = new javax.swing.JLabel();
        jLabelProfesor = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCreditos = new javax.swing.JTextField();
        jTextFieldProfesor = new javax.swing.JTextField();
        JCheckBoxActivo = new javax.swing.JCheckBox();
        JBotonGuardar = new javax.swing.JButton();
        JBotonLimpiar = new javax.swing.JButton();
        JBotonEliminar = new javax.swing.JButton();
        JBotonBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        JBotonOrdenarCodigo = new javax.swing.JButton();
        JBotonOrdenarNombre = new javax.swing.JButton();

        jLabelCodigo.setText("Codigo");

        jLabelNombre.setText("Nombre");

        jLabelCreditos.setText("Creditos");

        jLabelProfesor.setText("Profesor");

        jTextFieldCodigo.addActionListener(this::jTextFieldCodigoActionPerformed);

        jTextFieldNombre.addActionListener(this::jTextFieldNombreActionPerformed);

        jTextFieldCreditos.addActionListener(this::jTextFieldCreditosActionPerformed);

        jTextFieldProfesor.addActionListener(this::jTextFieldProfesorActionPerformed);

        JCheckBoxActivo.setSelected(true);
        JCheckBoxActivo.setText("Activo");
        JCheckBoxActivo.addActionListener(this::JCheckBoxActivoActionPerformed);

        JBotonGuardar.setText("Guardar");
        JBotonGuardar.addActionListener(this::JBotonGuardarActionPerformed);

        JBotonLimpiar.setText("Limpiar");
        JBotonLimpiar.addActionListener(this::JBotonLimpiarActionPerformed);

        JBotonEliminar.setText("Eliminar");
        JBotonEliminar.addActionListener(this::JBotonEliminarActionPerformed);

        JBotonBuscar.setText("Buscar");
        JBotonBuscar.addActionListener(this::JBotonBuscarActionPerformed);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Creditos", "Profesor", "Activo"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        JBotonOrdenarCodigo.setText("Ordenar por codigo");
        JBotonOrdenarCodigo.addActionListener(this::JBotonOrdenarCodigoActionPerformed);

        JBotonOrdenarNombre.setText("Ordenar por Nombre");
        JBotonOrdenarNombre.addActionListener(this::JBotonOrdenarNombreActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabelCodigo)
                                .addGap(53, 53, 53)
                                .addComponent(jLabelNombre)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelCreditos)
                                .addGap(46, 46, 46)
                                .addComponent(jLabelProfesor)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(JCheckBoxActivo))
                                    .addComponent(jTextFieldProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JBotonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBotonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBotonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBotonBuscar)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBotonOrdenarCodigo)
                        .addGap(62, 62, 62)
                        .addComponent(JBotonOrdenarNombre))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelProfesor)
                            .addComponent(jLabelCreditos)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCheckBoxActivo)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBotonGuardar)
                            .addComponent(JBotonLimpiar)
                            .addComponent(JBotonEliminar)
                            .addComponent(JBotonBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBotonOrdenarNombre)
                            .addComponent(JBotonOrdenarCodigo))))
                .addContainerGap(217, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
  
    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void JCheckBoxActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCheckBoxActivoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JCheckBoxActivoActionPerformed

    private void JBotonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonGuardarActionPerformed
        try {
            String codigo = jTextFieldCodigo.getText();
            String nombre = jTextFieldNombre.getText();
            int creditos = Integer.parseInt(jTextFieldCreditos.getText());
            String profesor = jTextFieldProfesor.getText();
            boolean activo = JCheckBoxActivo.isSelected();

            Curso curso = new Curso(0, codigo, nombre, creditos, profesor, activo);

            if (codigoEnEdicion == null) {
            cursoNegocio.agregar(curso);
            JOptionPane.showMessageDialog(this, "Curso registrado correctamente");
            } else {
            cursoNegocio.actualizar(codigoEnEdicion, curso);
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente");
            codigoEnEdicion = null;
            }

            limpiarCampos();
            cargarTabla();

        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Los creditos deben ser un numero.");
        } catch (DatoInvalidoException | RegistroDuplicadoException e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
        }
         
      
    }//GEN-LAST:event_JBotonGuardarActionPerformed

    private void JBotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonLimpiarActionPerformed
        // TODO add your handling code here:
        limpiarCampos();
    }//GEN-LAST:event_JBotonLimpiarActionPerformed

    private void JBotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonEliminarActionPerformed
        // TODO add your handling code here:
         int fila = jTable2.getSelectedRow();

    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un curso de la tabla.");
        return;
    }

    String codigo = jTable2.getValueAt(fila, 0).toString();

    int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Seguro que desea eliminar el curso " + codigo + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        for (Curso curso : cursoNegocio.obtenerTodos()) {
            if (curso.getCodigo().equals(codigo)) {
                cursoNegocio.eliminar(curso);
                break;
            }
        }
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Curso eliminado correctamente.");
    }
        
    }//GEN-LAST:event_JBotonEliminarActionPerformed

    private void JBotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonBuscarActionPerformed
        // TODO add your handling code here:
        String texto = jTextFieldNombre.getText();

        if (texto.trim().isEmpty()) {
        cargarTabla();
        return;
        }

        List<Curso> resultados = cursoNegocio.buscar(texto);
        mostrarEnTabla(resultados);

        if (resultados.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No se encontraron cursos.");
        }
    }//GEN-LAST:event_JBotonBuscarActionPerformed
    private void mostrarEnTabla(List<Curso> lista) {
    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
    modelo.addColumn("Código");
    modelo.addColumn("Nombre");
    modelo.addColumn("Créditos");
    modelo.addColumn("Profesor");
    modelo.addColumn("Activo");

    for (Curso curso : lista) {
        Object[] fila = {
            curso.getCodigo(),
            curso.getNombre(),
            curso.getCreditos(),
            curso.getProfesor(),
            curso.isActivo() ? "SI" : "NO"
        };
        modelo.addRow(fila);
    }

    jTable2.setModel(modelo);
    }
    private void JBotonOrdenarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonOrdenarCodigoActionPerformed
        List<Curso> lista = cursoNegocio.ordenarPorCodigo();
        mostrarEnTabla(lista);  
    }//GEN-LAST:event_JBotonOrdenarCodigoActionPerformed

    private void JBotonOrdenarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBotonOrdenarNombreActionPerformed
        List<Curso> lista = cursoNegocio.ordenarPorNombre();
        mostrarEnTabla(lista);
    }//GEN-LAST:event_JBotonOrdenarNombreActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
        int fila = jTable2.getSelectedRow();
        if (fila == -1) return;

        jTextFieldCodigo.setText(jTable2.getValueAt(fila, 0).toString());
        jTextFieldNombre.setText(jTable2.getValueAt(fila, 1).toString());
        jTextFieldCreditos.setText(jTable2.getValueAt(fila, 2).toString());
        jTextFieldProfesor.setText(jTable2.getValueAt(fila, 3).toString());
        String activoTexto = jTable2.getValueAt(fila, 4).toString();
        JCheckBoxActivo.setSelected(activoTexto.equals("Sí"));

        codigoEnEdicion = jTable2.getValueAt(fila, 0).toString();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextFieldCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoActionPerformed

    private void jTextFieldCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCreditosActionPerformed

    private void jTextFieldProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProfesorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProfesorActionPerformed
    private void limpiarCampos() {
    jTextFieldCodigo.setText("");
    jTextFieldNombre.setText("");
    jTextFieldCreditos.setText("");
    jTextFieldProfesor.setText("");
    JCheckBoxActivo.setSelected(false);
    codigoEnEdicion = null;
    }
    private void cargarTabla() {
    
    mostrarEnTabla(cursoNegocio.obtenerTodos());

    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBotonBuscar;
    private javax.swing.JButton JBotonEliminar;
    private javax.swing.JButton JBotonGuardar;
    private javax.swing.JButton JBotonLimpiar;
    private javax.swing.JButton JBotonOrdenarCodigo;
    private javax.swing.JButton JBotonOrdenarNombre;
    private javax.swing.JCheckBox JCheckBoxActivo;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelCreditos;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelProfesor;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldCreditos;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldProfesor;
    // End of variables declaration//GEN-END:variables
}
