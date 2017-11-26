package progra3mongo;

import static java.awt.SystemColor.control;

/**
 *
 * @author live
 */
public class Main 
{
    public static void main(String args[]) 
    {
        Controlador control = new Controlador();
        interfaz01 interfaz1 = new interfaz01(control);
        
        interfaz1.setVisible(true);
        
    }
}
