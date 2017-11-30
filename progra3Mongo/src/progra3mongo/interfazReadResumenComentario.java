package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import static sun.applet.AppletResourceLoader.getImage;

/**
 *
 * @author live
 */
public class interfazReadResumenComentario extends javax.swing.JFrame 
{
    public class Imagen extends javax.swing.JPanel 
    {
        String url;
        
        public Imagen(String url1) 
        {
            this.setSize(78, 78); //se selecciona el tamaño del panel
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
    private ArrayList<Integer> listaResumenes = new ArrayList<>();  //lista de numeros de los resumen
    private ArrayList<Integer> listaComentarios = new ArrayList<>();//lista de numeros de los comentarios
    private ArrayList<Integer> listaRespuestas = new ArrayList<>(); //lista de numeros de las respuestas de un solo comentario
    private int contador = -1;                                      //cont
    private int contadorComent = -1;                                //cont Comentario
    private int contadorResp = -1;                                  //cont Respuesta
    private Controlador control;                                    //controlador                          //Videos urls

    public interfazReadResumenComentario(Controlador control) {
        this.control = control;
        initComponents();
    }

    //CARGAR READ RESUMEN: Muestra los datos del resumen y guarda las URL de los videos
    public void cargarReadResumen(int pnumPar, String peq1, String peq2, String texto, String videos) {
        numPar.setText("" + pnumPar);
        eq1.setText(peq1);
        eq2.setText(peq2);
        textoResumen.setText(texto);
        DefaultComboBoxModel modeloVideos = new DefaultComboBoxModel(videos.split(","));
        listaVideos.setModel(modeloVideos);
    }
    
    //CARGAR READ COMENTARIO
    public void cargarReadComentario(DBObject tupla) {
        numComent.setText("" + ((int) tupla.get("numero_comentario")));
        fechaComent.setText((String) tupla.get("fecha"));
        horaComent.setText((String) tupla.get("hora"));
        textoComent.setText((String) tupla.get("texto"));
        //obtiene el aficionado
        DBObject tuplaAficionado = control.readAficionado((String) tupla.get("codigo_aficionado"));
        String urlFoto = (String)tuplaAficionado.get("foto");
        String indicFoto = (String)tuplaAficionado.get("indicador_foto");
        String dirCorreo = (String)tuplaAficionado.get("correo");
        String indicCorreo = (String)tuplaAficionado.get("indicador_correo");
        String estadoBorrado = (String)tuplaAficionado.get("estado_borrado");
        String fechaBorrado = (String)tuplaAficionado.get("fecha_borrado");
        String horaBorrado = (String)tuplaAficionado.get("hora_borrado");
        //Opcional : muestra correo
        if(indicCorreo.equals("YES")) {
            correoComent.setText(dirCorreo);
        }
        //Opcional : muestra foto
        if(indicFoto.equals("YES")) {
            Imagen Imagen = new Imagen(urlFoto);
            fotoComent.add(Imagen);
            fotoComent.repaint();
        }
        //Verifica borrado
        if(estadoBorrado.equals("YES")) {
            aficionadoHizoComent.setText("BORRADO "+fechaBorrado+" "+horaBorrado);
        }
        else {
            aficionadoHizoComent.setText((String) tupla.get("codigo_aficionado"));
        }
    }
    
    //CARGAR READ RESPUESTA
    public void cargarReadRespuesta(DBObject tupla) {
        numResp.setText("" + ((int) tupla.get("numero_comentario")));
        fechaResp.setText((String) tupla.get("fecha"));
        horaResp.setText((String) tupla.get("hora"));
        textoResp.setText((String) tupla.get("texto"));
        //obtiene el aficionado
        DBObject tuplaAficionado = control.readAficionado((String) tupla.get("codigo_aficionado"));
        String urlFoto = (String)tuplaAficionado.get("foto");
        String indicFoto = (String)tuplaAficionado.get("indicador_foto");
        String dirCorreo = (String)tuplaAficionado.get("correo");
        String indicCorreo = (String)tuplaAficionado.get("indicador_correo");
        String estadoBorrado = (String)tuplaAficionado.get("estado_borrado");
        String fechaBorrado = (String)tuplaAficionado.get("fecha_borrado");
        String horaBorrado = (String)tuplaAficionado.get("hora_borrado");
        //Opcional : muestra correo
        if(indicCorreo.equals("YES")) {
            correoResp.setText(dirCorreo);
        }
        //Opcional : muestra foto
        if(indicFoto.equals("YES")) {
            Imagen Imagen = new Imagen(urlFoto);
            fotoResp.add(Imagen);
            fotoResp.repaint();
        }
        //Verifica borrado
        if(estadoBorrado.equals("YES")) {
            aficionadoResp.setText("BORRADO "+fechaBorrado+" "+horaBorrado);
        }
        else {
            aficionadoResp.setText((String) tupla.get("codigo_aficionado"));
        }
    }

    //CARGAR SIGUIENTE RESUMEN: Obtiene los datos del resumen en la lista de resumenes segun el contador
    public void cargarSiguiente() {
        //Verifica si existe por lo menos un resumen
        if (!listaResumenes.isEmpty()) {
            contador++;
            DBObject tupla = control.readResumen(listaResumenes.get(contador));
            cargarReadResumen((int) tupla.get("numero_partido"),
                    (String) tupla.get("codigo_equi1"),
                    (String) tupla.get("codigo_equi2"),
                    (String) tupla.get("txt_resumen"),
                    (String) tupla.get("videos"));
            //Si llega al ultimo resumen reinicia el contador
            if (contador == listaResumenes.size() - 1) {
                contador = -1;
            }
        }
    }
    
    //CARGAR SIGUIENTE COMENTARIO:
    public void cargarSiguienteComentario() {
        //Verifica si existe por lo menos un comentario
        if (!listaComentarios.isEmpty()) {
            contadorComent++;
            DBObject tupla = control.readComentario(listaComentarios.get(contadorComent));
            cargarReadComentario(tupla);
            //Si llega al ultimo resumen reinicia el contador
            if (contadorComent == listaComentarios.size() - 1) {
                contadorComent = -1;
            }
        }
    }
    
    //CARGAR SIGUIENTE RESPUESTA:
    public void cargarSiguienteRespuesta() {
        //Verifica si existe por lo menos un comentario
        if (!listaRespuestas.isEmpty()) {
            contadorResp++;
            DBObject tupla = control.readComentario(listaRespuestas.get(contadorResp));
            cargarReadRespuesta(tupla);
            //Si llega al ultimo resumen reinicia el contador
            if (contadorResp == listaRespuestas.size() - 1) {
                contadorResp = -1;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        siguiente = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        numPar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        eq1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        eq2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        botonPlayVideo = new javax.swing.JButton();
        numComent = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        aficionadoHizoComent = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fechaComent = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        horaComent = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        correoComent = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        fotoComent = new javax.swing.JPanel();
        salir = new javax.swing.JButton();
        Cargar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoResumen = new javax.swing.JTextArea();
        listaVideos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoComent = new javax.swing.JTextArea();
        botonSiguienteComent = new javax.swing.JButton();
        botonCargarComent = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        numResp = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoResp = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        botonSiguienteRespuesta = new javax.swing.JButton();
        aficionadoResp = new javax.swing.JTextField();
        botonCargarRespuesta = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        fechaResp = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        horaResp = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        correoResp = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        fotoResp = new javax.swing.JPanel();
        botonCrearComent = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Interfaz READ RESUMEN Y COMENTARIO");

        siguiente.setText("SIGUIENTE RESUMEN");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        jLabel3.setText("Numero partido:");

        numPar.setEditable(false);

        jLabel4.setText("Equipo 1:");

        eq1.setEditable(false);

        jLabel5.setText("Equipo 2:");

        eq2.setEditable(false);

        jLabel6.setText("Videos:");

        botonPlayVideo.setText("play");
        botonPlayVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPlayVideoActionPerformed(evt);
            }
        });

        numComent.setEditable(false);

        jLabel8.setText("Comentario N°:");

        aficionadoHizoComent.setEditable(false);

        jLabel9.setText("Aficionado:");

        jLabel10.setText("Fecha:");

        fechaComent.setEditable(false);

        jLabel11.setText("Hora:");

        horaComent.setEditable(false);

        jLabel12.setText("Correo:");

        correoComent.setEditable(false);

        jLabel14.setText("Foto:");

        javax.swing.GroupLayout fotoComentLayout = new javax.swing.GroupLayout(fotoComent);
        fotoComent.setLayout(fotoComentLayout);
        fotoComentLayout.setHorizontalGroup(
            fotoComentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
        fotoComentLayout.setVerticalGroup(
            fotoComentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        salir.setText("SALIR");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        Cargar.setText("CARGAR RESUMEN");
        Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarActionPerformed(evt);
            }
        });

        jLabel15.setText("Texto:");

        textoResumen.setEditable(false);
        textoResumen.setColumns(20);
        textoResumen.setRows(5);
        jScrollPane1.setViewportView(textoResumen);

        listaVideos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("COMENTARIOS");

        jLabel2.setText("Texto:");

        textoComent.setEditable(false);
        textoComent.setColumns(20);
        textoComent.setRows(5);
        jScrollPane2.setViewportView(textoComent);

        botonSiguienteComent.setText("Siguiente Comentario");
        botonSiguienteComent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteComentActionPerformed(evt);
            }
        });

        botonCargarComent.setText("Cargar Comentarios");
        botonCargarComent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarComentActionPerformed(evt);
            }
        });

        jLabel13.setText("RESPUESTAS");

        jLabel16.setText("Texto:");

        numResp.setEditable(false);

        textoResp.setEditable(false);
        textoResp.setColumns(20);
        textoResp.setRows(5);
        jScrollPane3.setViewportView(textoResp);

        jLabel17.setText("Comentario N°:");

        botonSiguienteRespuesta.setText("Siguiente Respuesta");
        botonSiguienteRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSiguienteRespuestaActionPerformed(evt);
            }
        });

        aficionadoResp.setEditable(false);

        botonCargarRespuesta.setText("Cargar Respuestas");
        botonCargarRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarRespuestaActionPerformed(evt);
            }
        });

        jLabel18.setText("Aficionado:");

        jLabel19.setText("Fecha:");

        fechaResp.setEditable(false);

        jLabel20.setText("Hora:");

        horaResp.setEditable(false);

        jLabel21.setText("Correo:");

        correoResp.setEditable(false);

        jLabel22.setText("Foto:");

        fotoResp.setPreferredSize(new java.awt.Dimension(78, 78));

        javax.swing.GroupLayout fotoRespLayout = new javax.swing.GroupLayout(fotoResp);
        fotoResp.setLayout(fotoRespLayout);
        fotoRespLayout.setHorizontalGroup(
            fotoRespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
        fotoRespLayout.setVerticalGroup(
            fotoRespLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );

        botonCrearComent.setText("CREAR COMENTARIO");
        botonCrearComent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearComentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(listaVideos, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonPlayVideo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(botonCargarComent)
                                        .addGap(49, 49, 49)
                                        .addComponent(botonSiguienteComent)
                                        .addGap(45, 45, 45))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel8))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(numComent, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(aficionadoHizoComent, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(correoComent, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(fechaComent, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(261, 261, 261)
                                                .addComponent(jLabel10))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(jLabel17))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(numResp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(aficionadoResp, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(56, 56, 56)
                                                            .addComponent(fechaResp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel19))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel21)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(correoResp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(171, 171, 171)
                                                .addComponent(botonCargarRespuesta)
                                                .addGap(53, 53, 53)
                                                .addComponent(botonSiguienteRespuesta)))
                                        .addGap(52, 52, 52)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(botonCrearComent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Cargar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(siguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel14)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(fotoComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(horaComent, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(horaResp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(fotoResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numPar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eq1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eq2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numPar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(eq1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(eq2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonCrearComent, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listaVideos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPlayVideo))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(fechaComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(aficionadoHizoComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(correoComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonSiguienteComent)
                            .addComponent(botonCargarComent))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(fechaResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(aficionadoResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(correoResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonSiguienteRespuesta)
                            .addComponent(botonCargarRespuesta)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(78, 78, 78))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(75, 75, 75))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(horaComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(fotoComent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(horaResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(fotoResp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
        listaResumenes = control.obtenerResumenes();
        cargarSiguiente();
    }//GEN-LAST:event_CargarActionPerformed

    private void botonPlayVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPlayVideoActionPerformed
        // agregar codigo cargar video en (String) listaVideos.getSelectedItem()
    }//GEN-LAST:event_botonPlayVideoActionPerformed

    private void botonCrearComentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearComentActionPerformed
        interfazCrudComentario interfazCrudC = new interfazCrudComentario(control);
        interfazCrudC.setVisible(true);
    }//GEN-LAST:event_botonCrearComentActionPerformed

    private void botonCargarComentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarComentActionPerformed
        listaComentarios = control.obtenerComentarios(Integer.parseInt(numPar.getText()));
        cargarSiguienteComentario();
    }//GEN-LAST:event_botonCargarComentActionPerformed

    private void botonCargarRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarRespuestaActionPerformed
        listaRespuestas = control.obtenerRespuestas(Integer.parseInt(numComent.getText()));
        cargarSiguienteRespuesta();
    }//GEN-LAST:event_botonCargarRespuestaActionPerformed

    private void botonSiguienteComentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteComentActionPerformed
        cargarSiguienteComentario();
    }//GEN-LAST:event_botonSiguienteComentActionPerformed

    private void botonSiguienteRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSiguienteRespuestaActionPerformed
        cargarSiguienteRespuesta();
    }//GEN-LAST:event_botonSiguienteRespuestaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cargar;
    private javax.swing.JTextField aficionadoHizoComent;
    private javax.swing.JTextField aficionadoResp;
    private javax.swing.JButton botonCargarComent;
    private javax.swing.JButton botonCargarRespuesta;
    private javax.swing.JButton botonCrearComent;
    private javax.swing.JButton botonPlayVideo;
    private javax.swing.JButton botonSiguienteComent;
    private javax.swing.JButton botonSiguienteRespuesta;
    private javax.swing.JTextField correoComent;
    private javax.swing.JTextField correoResp;
    private javax.swing.JTextField eq1;
    private javax.swing.JTextField eq2;
    private javax.swing.JTextField fechaComent;
    private javax.swing.JTextField fechaResp;
    private javax.swing.JPanel fotoComent;
    private javax.swing.JPanel fotoResp;
    private javax.swing.JTextField horaComent;
    private javax.swing.JTextField horaResp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> listaVideos;
    private javax.swing.JTextField numComent;
    private javax.swing.JTextField numPar;
    private javax.swing.JTextField numResp;
    private javax.swing.JButton salir;
    private javax.swing.JButton siguiente;
    private javax.swing.JTextArea textoComent;
    private javax.swing.JTextArea textoResp;
    private javax.swing.JTextArea textoResumen;
    // End of variables declaration//GEN-END:variables
}
