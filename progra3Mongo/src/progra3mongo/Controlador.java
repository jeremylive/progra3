package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author live
 */
public class Controlador {

    //
    private Progra3Mongo cMongo;
    private DBCollection tablaResumen;
    private DBCollection tablaAficionado;
    private DBCollection tablaComentario;
    //Usuario que se esta usando
    private String codigoAficionado;

    //Constructor
    public Controlador(Progra3Mongo mongo) {
        this.cMongo = mongo;
        this.tablaResumen = cMongo.getDb().getCollection("resumen");
        this.tablaAficionado = cMongo.getDb().getCollection("aficionado");
        this.tablaComentario = cMongo.getDb().getCollection("comentario");
    }

    //gets and sets  
    public String getAficionado() {
        return codigoAficionado;
    }

    public void setAficionado(String codigo) {
        this.codigoAficionado = codigo;
    }

    //funciones
    public void ejeActualizar(interfazCrudAficionado crudA, boolean indicadorFoto,
            boolean indicadorCorreo) {
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

        //
    }

    //VERIFICAR AFICIONADO - Utilizado para hacer el login al inicio del programa
    public boolean verificarAficionado(String codigo, String pass) {
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

    //CRUD AFICIONADO
    public int crearAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, boolean indicadorFoto,
            boolean indicadorCorreo) {
        //Validaciones 

        //Query: Busca si ya existe un aficionado con ese codigo
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionado);
        DBCursor cursorQuery = tablaResumen.find(query);
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

    public void updateAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, String indicadorFoto,
            String indicadorCorreo) {

    }

    public void eliminarAficionado(String codAficionadoEliminar) {

    }

    //CRUD COMENTARIO
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

    public void updateComentario(String txtComentario, String codigoAficionado,
            String fechaComentario, String horaComentario, String comentPadre) {

    }

    public void eliminarComentario(int numComentarioEliminar) {

    }

    //CREATE RESUMEN: Crea un nuevo resumen
    public int createResumen(int numero_partido, String codEq1, String codEq2,
            String txtResumen, String video1, String video2) {
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
    public int updateResumen(int numero_partido, String txtResumen, String video1, String video2) {
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
    public int deleteResumen(int numero_partido) {
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
