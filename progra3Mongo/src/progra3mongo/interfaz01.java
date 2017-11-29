package progra3mongo;

import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class interfaz01 extends javax.swing.JFrame {

    //Variables globales
    Controlador control;

    public interfaz01(Controlador control1) {

        this.control = control1;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonCrudResumen = new javax.swing.JButton();
        botonCrudAficionado = new javax.swing.JButton();
        botonCrudComentario = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        ayuda = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        botonReadResumen = new javax.swing.JButton();
        botonReadAficionado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonCrudResumen.setText("CRUD RESUMEN");
        botonCrudResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrudResumenActionPerformed(evt);
            }
        });

        botonCrudAficionado.setText("CRUD AFICIONADO");
        botonCrudAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrudAficionadoActionPerformed(evt);
            }
        });

        botonCrudComentario.setText("CRUD COMENTARIO");

        jLabel6.setText("PROGRA 3 - BASE DE DATOS NOSQL: MONGODB");

        ayuda.setText("AYUDA");

        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        botonReadResumen.setText("READ RESUMEN CON COMENTARIOS");
        botonReadResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReadResumenActionPerformed(evt);
            }
        });

        botonReadAficionado.setText("READ AFICIONADO");
        botonReadAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReadAficionadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botonReadResumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(botonCrudResumen)
                                .addGap(46, 46, 46)
                                .addComponent(botonCrudComentario)))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonCrudAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonReadAficionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(ayuda)
                .addGap(18, 18, 18)
                .addComponent(salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonCrudComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonCrudResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonCrudAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonReadResumen, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonReadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Verifica que el usuario sea ADMINISTRADOR e inicia la interfaz de CRUD de Resumen
    private void botonCrudResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrudResumenActionPerformed
        if (!control.getAficionado().equals("ADMINISTRADOR")) {
            JOptionPane.showMessageDialog(null, "Solo el usuario ADMINISTRADOR puede crear resumenes");
        } else {
            interfazCrudResumen interfazResumen = new interfazCrudResumen(control);
            interfazResumen.setVisible(true);
        }
    }//GEN-LAST:event_botonCrudResumenActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void botonReadResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReadResumenActionPerformed
        interfazReadResumenComentario interfaz12 = new interfazReadResumenComentario(control);
        interfaz12.setVisible(true);
    }//GEN-LAST:event_botonReadResumenActionPerformed

    private void botonReadAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReadAficionadoActionPerformed
        interfazReadAficionados interfaz13 = new interfazReadAficionados();
        interfaz13.setVisible(true);
    }//GEN-LAST:event_botonReadAficionadoActionPerformed

    private void botonCrudAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrudAficionadoActionPerformed
        interfazCrudAficionado interfazCrudA = new interfazCrudAficionado(control);
        interfazCrudA.setVisible(true);
    }//GEN-LAST:event_botonCrudAficionadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ayuda;
    private javax.swing.JButton botonCrudAficionado;
    private javax.swing.JButton botonCrudComentario;
    private javax.swing.JButton botonCrudResumen;
    private javax.swing.JButton botonReadAficionado;
    private javax.swing.JButton botonReadResumen;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
