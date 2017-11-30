package progra3mongo;

import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Group;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 *
 * @author live
 */
public class MuestraVideo extends javax.swing.JFrame 
{

    public JFXPanel jfxPanel = new JFXPanel();    
    public File file;
    
    public MuestraVideo() 
    {
        
        initComponents();
        
        //chooser();
        //System.out.println(getFile());
        //createScene();
        
        this.setLayout(new BorderLayout());
        jfxPanel = new JFXPanel();
        this.add(jfxPanel, BorderLayout.CENTER);
        
        
        ThreadVideo video = new ThreadVideo();
        new Thread(video).start();
        
        this.setTitle("Java Swing Video con FX");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        //Añadimos el panel de JavaFX al JPanel Swing
        this.setLayout(new BorderLayout());
        this.add(jfxPanel,BorderLayout.CENTER);
        
        //jfxPanel.setVisible(true);
        //this.setVisible(true);
        
        /*
        setVisible(true);
        setTitle("Java Swing Video con FX");
        setResizable(false);
        setLocationRelativeTo(null);
        */
        //Añadimos el panel de JavaFX al JPanel Swing
        //video.setLayout(new BorderLayout());
        //video.add(jfxPanel,BorderLayout.CENTER);


    }

    public JFXPanel getFX()
    {
        return this.jfxPanel;
    }
    
    public File getFile()
    {
        return this.file;
    }
    
    public void setFile(File fil)
    {
        this.file = fil;
    }

    public void chooser()
    {
        //muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
        JFileChooser file=new JFileChooser();
        file.showOpenDialog(this);
        
        setFile(file.getSelectedFile()); // obtiene el archivo seleccionado

        // muestra error si es inválido
        if ((getFile() == null) || (getFile().getName().equals(""))) 
        {
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido");
        } 
    }
    /*
    private void createScene()
    {
        Platform.runLater(new Runnable() 
        {
            @Override
            public void run() 
            {                 
               //Logica selecciono donde se encuentra el video 
               File file1 = new File("C:\\Users\\Usuario1\\Videos\\Progras.mp4");

               MediaPlayer oracleVid = new MediaPlayer(                                       
                   new Media(file1.toURI().toString())
               );
               //se añade video al jfxPanel
               
               getFX().setScene(new Scene(new Group(new MediaView(oracleVid))));                    
               
               oracleVid.setVolume(0.7);//volumen
               oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
               oracleVid.play();//play video
                System.out.println("termina el thread");
            }
        });
    }
    */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
