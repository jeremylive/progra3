package progra3mongo;

import com.mongodb.DBObject;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static sun.applet.AppletResourceLoader.getImage;

/**
 *
 * @author live
 */
public class interfazReadAficionados extends javax.swing.JFrame 
{

    public class Imagen extends javax.swing.JPanel 
    {
        String url;
        
        public Imagen(String url1) 
        {
            this.setSize(300, 400); //se selecciona el tamaño del panel
            this.url = url1;
        }

        //Se crea un método cuyo parámetro debe ser un objeto Graphics

        public void paint(Graphics grafico) 
        {
           
            try {
                 Dimension height = getSize();
            
                JLabel etiqueta = new JLabel();
                Image img=getImage(new URL(url));
                etiqueta.setIcon(new ImageIcon(img));
                //repaint();
                
                grafico.drawImage(img, 0, 0, height.width, height.height, null);

                setOpaque(false);
                super.paintComponent(grafico);

               } catch (MalformedURLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
               }
            
        }
    }
    
    
    //Variables globales
    private ArrayList<String> listaAficionado = new ArrayList<>();  //lista de numeros de los resumen
    private int contador = -1;                                      //cont
    private Controlador control;                                    //controlador
    private String urlVideo1, urlVideo2;                            //Videos urls

        
    public interfazReadAficionados(Controlador control) 
    {
        this.control = control;
        initComponents();
    }
    
    //CARGAR READ RESUMEN: Muestra los datos del resumen y guarda las URL de los videos
    public void cargarReadAficionado(String codAfi1, String contra, String foto, 
            String indicadorFoto, String correo, String indicadorCorreo) 
    {
        //Seteo los datos en la interfaz
        codAfi.setText(codAfi1);
        contraAfi.setText(contra);
        //Imprimo foto en la interfaz
        //#######################################
        fotoAfi.setText(foto);
        correoAfi.setText(correo);
       
    }

    //CARGAR SIGUIENTE: Obtiene los datos del resumen en la lista de resumenes segun el contador
    public void cargarSiguiente() {
        //Verifica si existe por lo menos un resumen
        if (listaAficionado.size() != 0) {
            contador++;
            DBObject tupla = control.readAficionado(Integer.parseInt(listaAficionado.get(contador)));
            cargarReadAficionado((String) tupla.get("codigo_aficionado"),
                    (String) tupla.get("contraseña"),
                    (String) tupla.get("foto"),
                    (String) tupla.get("indicador_foto"),
                    (String) tupla.get("correo"),
                    (String) tupla.get("indicador_correo"));
            //Si llega al ultimo resumen reinicia el contador
            if (contador == listaAficionado.size() - 1) {
                contador = -1;
            }
        }
    }
    
 
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        codAfi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contraAfi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        correoAfi = new javax.swing.JTextField();
        siguiente = new javax.swing.JButton();
        Cargar = new javax.swing.JButton();
        fotoAfi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jFoto = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Interfaz READ de AFICIONADOS");

        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        codAfi.setEnabled(false);
        codAfi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codAfiActionPerformed(evt);
            }
        });

        jLabel2.setText("Codigo Aficionado");

        jLabel3.setText("Contraseña");

        contraAfi.setEnabled(false);

        jLabel4.setText("Foto");

        jLabel8.setText("correo electrónico");

        correoAfi.setEnabled(false);

        siguiente.setText("SIGUIENTE");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        Cargar.setText("CARGAR");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });

        fotoAfi.setEnabled(false);

        jButton1.setText("ver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jFotoLayout = new javax.swing.GroupLayout(jFoto);
        jFoto.setLayout(jFotoLayout);
        jFotoLayout.setHorizontalGroup(
            jFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        jFotoLayout.setVerticalGroup(
            jFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jLabel1)
                .addGap(0, 340, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(correoAfi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contraAfi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fotoAfi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(codAfi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(siguiente)
                        .addGap(35, 35, 35)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codAfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contraAfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fotoAfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correoAfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 178, Short.MAX_VALUE))
                    .addComponent(jFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed

        cargarSiguiente();
        
        
    }//GEN-LAST:event_siguienteActionPerformed

    private void CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarActionPerformed

        listaAficionado = control.obtenerAficionados();
        cargarSiguiente();    
        
    }//GEN-LAST:event_CargarActionPerformed

    private void codAfiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codAfiActionPerformed
       
        
        
    }//GEN-LAST:event_codAfiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Imagen Imagen = new Imagen(fotoAfi.getText());
        jFoto.add(Imagen);
        jFoto.repaint();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JTextField codAfi;
    private javax.swing.JTextField contraAfi;
    private javax.swing.JTextField correoAfi;
    private javax.swing.JTextField fotoAfi;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jFoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton salir;
    private javax.swing.JButton siguiente;
    // End of variables declaration//GEN-END:variables
}