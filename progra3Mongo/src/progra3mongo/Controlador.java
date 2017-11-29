package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author live
 */
public class Controlador 
{
    //Variables globales
    private Progra3Mongo cMongo;
    private DBCollection tablaResumen;
    private DBCollection tablaAficionado;
    private DBCollection tablaComentario;
    private int enteroSecuencial;
    private String fechaMundial;
    private String horaMundial;
    //Usuario que se esta usando
    private String codigoAficionado;

    //Constructor
    public Controlador(Progra3Mongo mongo) 
    {
        this.cMongo = mongo;
        this.tablaResumen = cMongo.getDb().getCollection("resumen");
        this.tablaAficionado = cMongo.getDb().getCollection("aficionado");
        this.tablaComentario = cMongo.getDb().getCollection("comentario");
        this.enteroSecuencial = 0;
    }

    //gets and sets  
    public String getAficionado() 
    {
        return codigoAficionado;
    }

    public void setAficionado(String codigo) 
    {
        this.codigoAficionado = codigo;
    }

    public int getEnteroSecuencial()
    {
        return this.enteroSecuencial;
    }
    
    public void setEnteroSecuencial(int entero)
    {
        this.enteroSecuencial = entero;
    }
    
    public void aumentarEntero()
    {
        this.enteroSecuencial++;
    }
    
    
    
    
    public void actualizarHoraYFecha()
    {
        
        Calendar calendario = Calendar.getInstance();
        Calendar c = new GregorianCalendar();
        
        
        int hora =calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
        
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        
        //Actualizo dvariables globales
        fechaMundial = annio + "-" + mes + "-" + dia;
        horaMundial = hora + ":" + minutos + ":" + segundos;
    }
    
    
    /**
     *                  FUNCIONES
     */
    //Valida la aparecion de las jText en el JFrame de CRUDaficionados
    public void ejeActualizar(interfazCrudAficionado crudA, boolean indicadorFoto, 
            boolean indicadorCorreo) 
    {
        //Valido los JcheckBox que si estan en true se habilita las casillas sino se desabilita
        if (indicadorFoto == true) {
            crudA.fotoAficionado.setEnabled(true);
        } else {
            crudA.fotoAficionado.setEnabled(false);
        }

        if (indicadorCorreo == true) {
            crudA.correoAficionado.setEnabled(true);
        } else {
            crudA.correoAficionado.setEnabled(false);
        }
    }

    //VERIFICAR AFICIONADO - Utilizado para hacer el login al inicio del programa
    public boolean verificarAficionado(String codigo, String pass) 
    {
        boolean existe = false;
        //Creo el query
        BasicDBObject query = new BasicDBObject();
        query.put("codigo_aficionado", codigo);
        query.put("contrasena", pass);
        //Obtengo el resultado
      
        DBCursor cursor = tablaAficionado.find(query);
        //Si existe en la tabla retorna True
        if (cursor.hasNext()) {
            existe = true;
        }
        return existe;
    }

    /**
     *          CRUD AFICIONADO
     */
    
    //CREATE AFICIONADO
    public int crearAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, boolean indicadorFoto,
            boolean indicadorCorreo) 
    {    
        //Valida que no se pase de 15 digitos
        if(codAficionado.length() > 15){
            JOptionPane.showMessageDialog(null, "El largo del codigo de aficionado se paso de 15 caracteres");
            return 1;
        }
        //Query: Busca si ya existe un aficionado con ese codigo
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionado);
        DBCursor cursorQuery = tablaAficionado.find(query);
        if (cursorQuery.hasNext()) { //Si entra aqui significa que ya existe
            JOptionPane.showMessageDialog(null, "Ya existe un aficionado con ese codigo");
            return 1;
        }

        //Creo la tupla
        BasicDBObject tupla = new BasicDBObject();
        tupla.put("codigo_aficionado", codAficionado);
        tupla.put("contrasena", contraseñaAficionado);
        tupla.put("foto", fotoAficionado);
        if (indicadorFoto) {
            tupla.put("indicador_foto", "YES");
        } else {
            tupla.put("indicador_foto", "NO");
        }
        tupla.put("correo", correoAficionado);
        if (indicadorCorreo) {
            tupla.put("indicador_correo", "YES");
        } else {
            tupla.put("indicador_correo", "NO");
        }
        //Inserto la tupla
        tablaAficionado.insert(tupla);

        return 0;
    }

    //UPDATE AFICIONADO:
    public int updateAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, String indicadorFoto,
            String indicadorCorreo) 
    {
        
        //Valida que no se pase de 15 digitos
        if(codAficionado.length() > 15){
            JOptionPane.showMessageDialog(null, "El codigo aficionado es mayor a 15 caracteres");
            return 1;
        }
        
        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_aficionado", codAficionado);

        //Verifico si el resumen existe y lo obtengo
        DBCursor cursorExistente = tablaAficionado.find(query);
        if (cursorExistente.hasNext()) {
            cursorExistente.next();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();
        aumentarEntero();
        document.put("codigo_aficionado", codAficionado);
        document.put("contraseña", contraseñaAficionado);
        document.put("foto", fotoAficionado);
        document.put("correo", correoAficionado);
        document.put("indicador_foto", indicadorFoto);
        document.put("indicador_correo", indicadorCorreo);
       
        //Actualiza la tupla
        tablaAficionado.update(query, document);

        return 0;
    }

    //ELIMINAR AFICIONADO:
    public int eliminarAficionado(String codAficionadoEliminar) 
    {
        //Verifico si el resumen existe
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionadoEliminar);
        DBCursor cursor = tablaAficionado.find(query);
        if (cursor.hasNext()) {
            //Borro el resumen
            //tablaAficionado.remove(query);
            String borrar = "BORRADO";  //Se debe insertar en el comentario

            //Falta Actualizar un nuevo atributo con el formato BORRADO
            
            
        }
        //Se obtienen la fecha y hora global
        actualizarHoraYFecha();
    }

    /**
     *          CRUD COMENTARIO
     */
    
    //CREATE COMENTARIO:
    public int crearComentario(int cont, String txtComentario, String codigoAficionado,
            String fechaComentario, String horaComentario, String comentPadre) {
        //Validaciones

        //Creo la tupla
        BasicDBObject tupla = new BasicDBObject();
        tupla.put("numero_comentario", cont);
        tupla.put("codigo_aficionado", codigoAficionado);
        tupla.put("fecha", fechaComentario);
        tupla.put("hora", horaComentario);
        tupla.put("texto", txtComentario);
        tupla.put("numero_comentario_padre", comentPadre);

        //Inserto la tupla
        tablaComentario.insert(tupla);

        return 0;
    }

    //UPDATE COMENTARIO:
    public int updateComentario(String txtComentario, String codigoAficionado,
            String fechaComentario, String horaComentario, String comentPadre) 
    {
        //Valido info
        
        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_comentario", txtComentario);

        //Verifico si el resumen existe y lo obtengo
        DBCursor cursorExistente = tablaComentario.find(query);
        if (cursorExistente.hasNext()) {
            cursorExistente.next();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();
        aumentarEntero();
        document.put("numero_comentario", getEnteroSecuencial());
        document.put("codigo_aficionado", codigoAficionado);
        //verificar si se inserto la fecha y hora bien
        document.put("fecha", fechaComentario);
        document.put("hora", horaComentario);
        document.put("comentario_padre", comentPadre);
       
        //Actualiza la tupla
        tablaResumen.update(query, document);

        return 0;
    }

    //ELIMINAR COMENTARIO:
    public int eliminarComentario(int numComentarioEliminar) 
    {
        //Verifico si el resumen existe
        BasicDBObject query = new BasicDBObject("numero_comentario", numComentarioEliminar);
        DBCursor cursor = tablaComentario.find(query);
        if (cursor.hasNext()) {
            //Borro el resumen
            tablaComentario.remove(query);
            return 0;
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }
    }

    /**
     *          CRUD RESUMEN
     */
    
    //CREATE RESUMEN: Crea un nuevo resumen
    public int createResumen(int numero_partido, String codEq1, String codEq2,
            String txtResumen, String video1, String video2) 
    {
        //Validaciones
        
        
        //Busca si ya existe un resumen con ese numero de partido
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);
        DBCursor cursorQuery = tablaResumen.find(query);
        if (cursorQuery.hasNext()) { //Si entra aqui significa que ya existe
            JOptionPane.showMessageDialog(null, "Ya existe un resumen para ese partido");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido", numero_partido);
        document.put("codigo_equi1", codEq1);
        document.put("codigo_equi2", codEq2);
        document.put("txt_resumen", txtResumen);
        document.put("video1", video1);
        document.put("video2", video2);

        //Inserto la tupla
        tablaResumen.insert(document);

        return 0;
    }

    //UPDATE RESUMEN: Modifica el texto y url de los videos de un resumen existente
    public int updateResumen(int numero_partido, String txtResumen, String video1, String video2) 
    {
        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);

        //Verifico si el resumen existe y lo obtengo
        DBObject tuplaExistente = null;
        DBCursor cursorExistente = tablaResumen.find(query);
        if (cursorExistente.hasNext()) {
            tuplaExistente = cursorExistente.next();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();
        document.put("numero_partido", tuplaExistente.get("numero_partido"));
        document.put("codigo_equi1", tuplaExistente.get("codigo_equi1"));
        document.put("codigo_equi2", tuplaExistente.get("codigo_equi2"));
        document.put("txt_resumen", txtResumen);
        document.put("video1", video1);
        document.put("video2", video2);

        //Actualiza la tupla
        tablaResumen.update(query, document);

        return 0;
    }

    //OBTENER RESUMENES: Retorna un array con el numero_partido de todos los resumenes existentes
    public ArrayList<Integer> obtenerResumenes() {
        ArrayList<Integer> resultado = new ArrayList<>();

        //Obtengo todas las tuplas
        DBCursor cursor = tablaResumen.find();
        while (cursor.hasNext()) {
            DBObject tupla = cursor.next();
            resultado.add((int) tupla.get("numero_partido"));
        }

        return resultado;
    }

    //READ RESUMEN: Busca con el numero partido y retorna la tupla como un BasicDBObject
    public DBObject readResumen(int numero_partido) {
        DBObject resultado = null;

        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);
        //Obtengo el resultado
        DBCursor cursor = tablaResumen.find(query);
        while (cursor.hasNext()) {
            resultado = cursor.next();
        }

        return resultado;
    }

    //DELETE RESUMEN: Busca con el numero partido y borra la tupla encontrada
    public int deleteResumen(int numero_partido) 
    {
        //Verifico si el resumen existe
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);
        DBCursor cursor = tablaResumen.find(query);
        if (cursor.hasNext()) {
            //Borro el resumen
            tablaResumen.remove(query);
            return 0;
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }
    }
}
