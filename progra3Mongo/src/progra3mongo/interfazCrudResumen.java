package progra3mongo;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class interfazCrudResumen extends javax.swing.JFrame {

    //Variables globales
    Controlador control;

    public interfazCrudResumen(Controlador control1) {

        this.control = control1;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonCrear = new javax.swing.JButton();
        numPartido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextArea();
        video1 = new javax.swing.JTextField();
        video2 = new javax.swing.JTextField();
        numPartidoEliminar = new javax.swing.JTextField();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonCrear.setText("CREAR");
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });

        txtResumen.setColumns(20);
        txtResumen.setRows(5);
        jScrollPane1.setViewportView(txtResumen);

        botonEliminar.setText("ELIMINAR");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setText("MODIFICAR");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        jLabel1.setText("numero del partido");

        jLabel2.setText("texto del resumen");

        jLabel3.setText("Video 1");

        jLabel4.setText("Video 2");

        jLabel5.setText("numero del partido");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CRUD RESUMEN");

        botonSalir.setText("SALIR");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(botonCrear)
                                .addGap(78, 78, 78)
                                .addComponent(botonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonSalir))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(video2)
                            .addComponent(video1)
                            .addComponent(numPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numPartidoEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numPartidoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminar)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(video1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(video2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCrear)
                    .addComponent(botonModificar)
                    .addComponent(botonSalir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Create resumen
    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed
        //Validar que sea int o strings
        int numeroPartido;
        try {
            numeroPartido = Integer.parseInt(numPartido.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El numero de partido debe ser entero");
            return;
        }

        //Valido que el numero de partido este en el BD de oracle y obtengo los equipos
        String eq1 = "";
        String eq2 = "";
        boolean valido = false;
        try {
            //Hago conexion
            Connection oracle = Conexion.getConexion();
            Statement statement = oracle.createStatement();
            //Realiza el query
            String query = "SELECT * FROM PARTIDO_PARTICIPA";
            ResultSet output = statement.executeQuery(query);
            //Obtengo los metadatos
            ResultSetMetaData metaDatos = output.getMetaData();
            int index = metaDatos.getColumnCount();
            //Comparo el numero de partido con los que estan en Oracle
            while (output.next()) {
                if (output.getInt(1) == numeroPartido) {
                    valido = true;
                    if (output.getInt(3) == 1) {
                        eq1 = output.getString(2);
                    } else {
                        eq2 = output.getString(2);
                    }
                }
            }

            if (!valido) {
                JOptionPane.showMessageDialog(null, "No puede registrar el resumen porque el partido aún no está en el sistema");
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los partidos en ORACLE");
            return;
        }

        //Creo un resumen
        int estado = control.createResumen(numeroPartido, eq1, eq2, txtResumen.getText(), video1.getText(), video2.getText());
        if (estado == 0) {
            JOptionPane.showMessageDialog(null, "Resumen creado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al crear el resumen");
        }
    }//GEN-LAST:event_botonCrearActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        //Valido que sea entero
        try {
            int numeroPartido = Integer.parseInt(numPartido.getText());
            //Modifico el resumen
            int estado = control.updateResumen(numeroPartido, txtResumen.getText(), video1.getText(), video2.getText());
            if (estado == 0) {
                JOptionPane.showMessageDialog(null, "Resumen modificado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el resumen");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El numero de partido debe ser entero");
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        //Valido que sea entero
        try {
            int numeroPartido = Integer.parseInt(numPartidoEliminar.getText());
            //Elimino el resumen
            int estado = control.deleteResumen(numeroPartido);
            if (estado == 0) {
                JOptionPane.showMessageDialog(null, "Resumen eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el resumen");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El numero de partido debe ser entero");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numPartido;
    private javax.swing.JTextField numPartidoEliminar;
    private javax.swing.JTextArea txtResumen;
    private javax.swing.JTextField video1;
    private javax.swing.JTextField video2;
    // End of variables declaration//GEN-END:variables
}
