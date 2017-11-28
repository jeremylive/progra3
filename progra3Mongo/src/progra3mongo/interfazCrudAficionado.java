package progra3mongo;

/**
 *
 * @author live
 */
public class interfazCrudAficionado extends javax.swing.JFrame 
{
    //Variables globales
    Controlador control;
    
    public interfazCrudAficionado(Controlador control1) 
    {
        
        this.control = control1;
        initComponents();
    }
    
    
    public void setfotoAfi(boolean pFoto)
    {
        fotoAficionado.setEnabled(pFoto);
    }

    public void setcorreoAfi(boolean pCorreo)
    {
        correoAficionado.setEnabled(pCorreo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crearAficionado = new javax.swing.JButton();
        codAficionado = new javax.swing.JTextField();
        contraseñaAficionado = new javax.swing.JTextField();
        codAficionadoEliminar = new javax.swing.JTextField();
        eliminarAficionado = new javax.swing.JButton();
        modificarAficionado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        correoAficionado = new javax.swing.JTextField();
        fotoAficionado = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        despliegueFoto = new javax.swing.JCheckBox();
        despliegueCorreo = new javax.swing.JCheckBox();
        ACTUALIZAR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        crearAficionado.setText("CREAR");
        crearAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearAficionadoActionPerformed(evt);
            }
        });

        eliminarAficionado.setText("ELIMINAR");
        eliminarAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarAficionadoActionPerformed(evt);
            }
        });

        modificarAficionado.setText("MODIFICAR");
        modificarAficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarAficionadoActionPerformed(evt);
            }
        });

        jLabel1.setText("codigo aficionado");

        jLabel2.setText("contraseña");

        jLabel3.setText("foto aficionado");

        jLabel4.setText("indicador de despliegue de foto");

        jLabel5.setText("codigo aficionado");

        jLabel6.setText("CRUD AFICIONADO");

        jLabel7.setText("indicador de despliegue de correo");

        jLabel8.setText("correo electrónico");

        correoAficionado.setEnabled(false);

        fotoAficionado.setEnabled(false);

        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        despliegueFoto.setText("true/false");

        despliegueCorreo.setText("true/false");

        ACTUALIZAR.setText("ACTUALIZAR");
        ACTUALIZAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ACTUALIZARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(crearAficionado)
                                .addGap(18, 18, 18)
                                .addComponent(modificarAficionado)
                                .addGap(93, 93, 93))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(contraseñaAficionado)
                                    .addComponent(codAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(correoAficionado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fotoAficionado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eliminarAficionado)
                                    .addComponent(codAficionadoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ACTUALIZAR)
                                .addGap(18, 18, 18)
                                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(despliegueFoto)
                            .addComponent(despliegueCorreo))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codAficionadoEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarAficionado)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(despliegueFoto)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7))
                            .addComponent(despliegueCorreo))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(contraseñaAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fotoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(correoAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(118, 118, 118)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ACTUALIZAR, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(crearAficionado)
                                .addComponent(modificarAficionado))
                            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearAficionadoActionPerformed

        //Creo un aficionado
        control.crearAficionado(codAficionado.getText(), contraseñaAficionado.getText(), 
                fotoAficionado.getText(), correoAficionado.getText(), despliegueFoto.isSelected(),
                despliegueCorreo.isSelected());
       
    }//GEN-LAST:event_crearAficionadoActionPerformed

    private void modificarAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarAficionadoActionPerformed

        
        //Modifico un aficionado
        control.updateAficionado(codAficionado.getText(), contraseñaAficionado.getText(), 
                fotoAficionado.getText(), correoAficionado.getText(), despliegueFoto.getText(),
                despliegueCorreo.getText());
        
    }//GEN-LAST:event_modificarAficionadoActionPerformed

    private void eliminarAficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarAficionadoActionPerformed


        //Elimino un aficionado
        control.eliminarAficionado(codAficionadoEliminar.getText());

    }//GEN-LAST:event_eliminarAficionadoActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void ACTUALIZARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ACTUALIZARActionPerformed


        //
        control.ejeActualizar(this, despliegueFoto.isSelected(), despliegueCorreo.isSelected());

    }//GEN-LAST:event_ACTUALIZARActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ACTUALIZAR;
    private javax.swing.JTextField codAficionado;
    private javax.swing.JTextField codAficionadoEliminar;
    private javax.swing.JTextField contraseñaAficionado;
    public static javax.swing.JTextField correoAficionado;
    private javax.swing.JButton crearAficionado;
    public static javax.swing.JCheckBox despliegueCorreo;
    private javax.swing.JCheckBox despliegueFoto;
    private javax.swing.JButton eliminarAficionado;
    public static javax.swing.JTextField fotoAficionado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton modificarAficionado;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
