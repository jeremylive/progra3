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
        
        chooser();
        System.out.println("--"+getFile());
        createScene();
        
        setVisible(true);
        setTitle("Java Swing Video con FX");
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Añadimos el panel de JavaFX al JPanel Swing
        video.setLayout(new BorderLayout());
        video.add(jfxPanel,BorderLayout.CENTER);


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
    
    public void createScene()
    {
        Platform.runLater(new Runnable() 
        {
             @Override
             public void run() 
             {                 
                //Logica selecciono donde se encuentra el video 
                File file1 = getFile();
                 
                MediaPlayer oracleVid = new MediaPlayer(                                       
                    new Media(file1.toURI().toString())
                );
                //se añade video al jfxPanel
                jfxPanel.setScene(new Scene(new Group(new MediaView(oracleVid))));                    
                oracleVid.setVolume(0.7);//volumen
                oracleVid.setCycleCount(MediaPlayer.INDEFINITE);//repite video
                oracleVid.play();//play video
                 System.out.println("termina el thread");
             }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        video = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout videoLayout = new javax.swing.GroupLayout(video);
        video.setLayout(videoLayout);
        videoLayout.setHorizontalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        videoLayout.setVerticalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(video, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(video, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel video;
    // End of variables declaration//GEN-END:variables
}
