package progra3mongo;

import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class ThreadVideo implements Runnable
{

    public JFXPanel jfxPanel = new JFXPanel();    
    public File file;
    
    public ThreadVideo()
    {
                /*
        chooser();
        System.out.println(getFile());
        createScene();
               */ 

        

         
        System.out.println("entro al thread0");
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
/*
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
    */

    @Override
    public void run() 
    {                 
        System.out.println("entro al thread1-----");
       //Logica selecciono donde se encuentra el video 
       File file1 = new File("C:\\Users\\Usuario1\\Videos\\Progras.mp4");

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

    

    
}
