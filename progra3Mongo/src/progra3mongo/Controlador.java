package progra3mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.awt.Desktop;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author live
 */
public class Controlador {

    //Variables globales
    private Progra3Mongo cMongo;
    private DBCollection tablaResumen;
    private DBCollection tablaAficionado;
    private DBCollection tablaComentario;

    private int enteroSecuencial; //Contador de numero de comentario
    private String fechaMundial; //Fecha de borrado
    private String horaMundial; //Hora de borrado
    private String codigoAficionado; //Usuario que se esta usando

    //Constructor
    public Controlador(Progra3Mongo mongo) {
        this.cMongo = mongo;
        this.tablaResumen = cMongo.getDb().getCollection("resumen");
        this.tablaAficionado = cMongo.getDb().getCollection("aficionado");
        this.tablaComentario = cMongo.getDb().getCollection("comentario");
        this.enteroSecuencial = 0;
        this.fechaMundial = "";
        this.horaMundial = "";
    }

    //gets and sets  
    public String getAficionado() {
        return codigoAficionado;
    }

    public void setAficionado(String codigo) {
        this.codigoAficionado = codigo;
    }

    public int getEnteroSecuencial() {
        return this.enteroSecuencial;
    }

    public void setEnteroSecuencial(int entero) {
        this.enteroSecuencial = entero;
    }

    public void aumentarEntero() {
        this.enteroSecuencial++;
    }

    //
    // FUNCIONES
    //
    //Valida la aparecion de las jText en el JFrame de CRUDaficionados
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
    }

    //VERIFICAR AFICIONADO - Utilizado para hacer el login al inicio del programa
    public boolean verificarAficionado(String codigo, String pass) {
        boolean existe = false;
        //Creo el query
        BasicDBObject query = new BasicDBObject();
        query.put("codigo_aficionado", codigo);
        query.put("contraseña", pass);
        //Obtengo el resultado

        DBCursor cursor = tablaAficionado.find(query);
        //Si existe en la tabla retorna True
        if (cursor.hasNext()) {
            existe = true;
        }
        return existe;
    }

    //ACTUALIZO A LA HORA MUNDIAL Y FECHA AL DARLE CLICK EN ELIMINAR AFICIONADO
    public void actualizarHoraYFecha() {
        //
        Calendar calendario = Calendar.getInstance();
        Calendar c = new GregorianCalendar();

        //
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);

        //
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));

        //Actualizo dvariables globales
        fechaMundial = annio + "-" + mes + "-" + dia;
        horaMundial = hora + ":" + minutos + ":" + segundos;
    }

    //
    //  CRUD AFICIONADO
    //
    //CREATE AFICIONADO
    public int crearAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, boolean indicadorFoto,
            boolean indicadorCorreo) {
        //Valido....
        //Query: Busca si ya existe un aficionado con ese codigo
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionado);
        DBCursor cursorQuery = tablaAficionado.find(query);
        BasicDBObject tupla = new BasicDBObject(); //Creo la tupla

        //Valido si existe el aficionado en la tabla
        if (cursorQuery.hasNext()) {
            JOptionPane.showMessageDialog(null, "Ya existe un aficionado con ese codigo");
            return 1;
        }

        //Valida que no se pase de 15 digitos
        if (codAficionado.length() > 15) {
            JOptionPane.showMessageDialog(null, "El largo del codigo de aficionado se paso de 15 caracteres");
            return 1;
        }

        //Inserto en el documento datos
        tupla.put("codigo_aficionado", codAficionado);  //Cod Aficionado
        tupla.put("contraseña", contraseñaAficionado);  //Contraseña
        tupla.put("foto", fotoAficionado);              //URL de la foto
        if (indicadorFoto) {                            //Indicador foto
            tupla.put("indicador_foto", "YES");
        } else {
            tupla.put("indicador_foto", "NO");
        }
        tupla.put("correo", correoAficionado);          //Direccion de correo
        if (indicadorCorreo) {                          //Indicador correo
            tupla.put("indicador_correo", "YES");
        } else {
            tupla.put("indicador_correo", "NO");
        }
        tupla.put("estado_borrado", "NO");
        tupla.put("fecha_borrado", "");
        tupla.put("hora_borrado", "");
        //Inserto la tupla
        tablaAficionado.insert(tupla);

        return 0;
    }

    //UPDATE AFICIONADO:
    public int updateAficionado(String codAficionado, String contraseñaAficionado,
            String fotoAficionado, String correoAficionado, boolean indicadorFoto,
            boolean indicadorCorreo, String estadoBorrado, String fechaBorrado, String horaBorrado) {

        //Valida que no se pase de 15 digitos
        if (codAficionado.length() > 15) {
            JOptionPane.showMessageDialog(null, "El codigo aficionado es mayor a 15 caracteres");
            return 1;
        }
        //Contraseña puede ser caracr, int, pueden ser de cualquier signo
        //Foto debe ser unar URL, pueden ser de cualquier signo
        //Correo, pueden ser de cualquier signo
        //Indicador foto o correo son true o false
        //Creo el query, con la condicion
        //SELECT * FROM AFICIONADO WHERE NUMERO_AFICIONADO = ""
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionado);
        DBObject tuplaExistente;
        //Valido si existe el numero de aficionado en la tabla SI o NO
        DBCursor cursorExistente = tablaAficionado.find(query);
        if (cursorExistente.hasNext()) {
            tuplaExistente = cursorExistente.next();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un aficionado con ese codigo");
            return 1;
        }
        //Valido que el aficionado no este borrado
        if (((String) tuplaExistente.get("estado_borrado")).equals("YES")) {
            JOptionPane.showMessageDialog(null, "No puede modificar un aficionado borrado");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();

        //Inseto datos en la tupla....
        //Seteo codigo de aficionado
        document.put("codigo_aficionado", codAficionado);
        //seteo contraseña
        document.put("contraseña", contraseñaAficionado);
        //seteo foto y el indicador
        document.put("foto", fotoAficionado);
        if (indicadorFoto) {
            document.put("indicador_foto", "YES");
        } else {
            document.put("indicador_foto", "NO");
        }
        //seteo correo y el indicador
        document.put("correo", correoAficionado);
        if (indicadorCorreo) {
            document.put("indicador_correo", "YES");
        } else {
            document.put("indicador_correo", "NO");
        }
        //seteo estado borrado
        document.put("estado_borrado", estadoBorrado);
        document.put("fecha_borrado", fechaBorrado);
        document.put("hora_borrado", horaBorrado);
        //Actualiza la tupla
        tablaAficionado.update(query, document);

        return 0;
    }

    //ELIMINAR AFICIONADO:
    public int eliminarAficionado(String codAficionadoEliminar) {
        //Verifico si el aficionado existe en la tabla de aficionado
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAficionadoEliminar);
        DBObject tuplaExistente;
        DBCursor cursor = tablaAficionado.find(query);
        //Valido si existe el aficionado en la tabla y obtengo la tupla
        if (cursor.hasNext()) {
            tuplaExistente = cursor.next();
            //Se obtienen la fecha y hora global
            actualizarHoraYFecha();
            //Obtengo el valor de los indicadores para poder hacer el update
            String indicadorFoto = (String) tuplaExistente.get("indicador_foto");
            boolean valorIndicadorFoto = false;
            if (indicadorFoto.equals("YES")) {
                valorIndicadorFoto = true;
            }

            String indicadorCorreo = (String) tuplaExistente.get("indicador_correo");
            boolean valorIndicadorCorreo = false;
            if (indicadorCorreo.equals("YES")) {
                valorIndicadorCorreo = true;
            }
            //Actualiza el aficionado, colocando YES en estado_borrado y la fecha y hora del borrado
            updateAficionado((String) tuplaExistente.get("codigo_aficionado"),
                    (String) tuplaExistente.get("contraseña"),
                    (String) tuplaExistente.get("foto"),
                    (String) tuplaExistente.get("correo"),
                    valorIndicadorFoto, valorIndicadorCorreo, "YES", fechaMundial, horaMundial);
            return 0;
        } else {
            JOptionPane.showMessageDialog(null, "No existe ese un aficionado con ese codigo");
            return 1;
        }
    }

    //
    //  CRUD COMENTARIO
    //
    //CREATE COMENTARIO: Crea un comentario para el usuario que inició sesión
    public int crearComentario(String txtComentario, int numeroPartido,
            String fechaComentario, String horaComentario, int numeroComentPadre) {
        //Validaciones
        //El aficionado debe de existir
        BasicDBObject queryAficionado = new BasicDBObject("codigo_aficionado", codigoAficionado);
        DBCursor cursor = tablaAficionado.find(queryAficionado);
        if (!cursor.hasNext()) {
            JOptionPane.showMessageDialog(null, "No existe un aficionado con ese codigo");
            return 1;
        }
        //El resumen de ese partido debe de existir
        BasicDBObject queryPartido = new BasicDBObject("numero_partido", numeroPartido);
        DBCursor cursorRes = tablaResumen.find(queryPartido);
        if (!cursorRes.hasNext()) {
            JOptionPane.showMessageDialog(null, "No existe un resumen para el partido");
            return 1;
        }
        //Si tiene un comentario padre, el comentario debe ser válido
        if (numeroComentPadre != 0) {
            BasicDBObject queryPadre = new BasicDBObject("numero_comentario", numeroComentPadre);
            DBCursor cursorPadre = tablaComentario.find(queryPadre);
            if (!cursorPadre.hasNext()) {
                JOptionPane.showMessageDialog(null, "El numero de comentario padre es invalido");
                return 1;
            }
        }

        //Aumento el numero de comentario
        aumentarEntero();

        //Creo la tupla
        BasicDBObject tupla = new BasicDBObject();
        tupla.put("numero_comentario", getEnteroSecuencial());
        tupla.put("numero_partido", numeroPartido);
        tupla.put("codigo_aficionado", codigoAficionado);
        tupla.put("fecha", fechaComentario);
        tupla.put("hora", horaComentario);
        tupla.put("texto", txtComentario);
        tupla.put("numero_comentario_padre", numeroComentPadre);

        //Inserto la tupla
        tablaComentario.insert(tupla);

        return 0;
    }

    //UPDATE COMENTARIO:
    /*public int updateComentario(String txtComentario, String codigoAficionado,
            String fechaComentario, String horaComentario, String comentPadre) {
        //Validaciones....
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

        //Valido que este codigo de aficionado ESTE EN LA TABLA DE AFICIONADO
        query = new BasicDBObject("codigo_aficionado", codigoAficionado);

        //Verifico si el resumen existe y lo obtengo
        cursorExistente = tablaComentario.find(query);
        if (cursorExistente.hasNext()) {
            cursorExistente.next();
        } else {
            JOptionPane.showMessageDialog(null, "No existe un resumen con ese numero de partido");
            return 1;
        }

        //Creo la tupla
        BasicDBObject document = new BasicDBObject();
        //Entero que lleVA EL NUMERO DE COMENTARIO
        aumentarEntero();
        //Inserto en la tupla
        document.put("numero_comentario", getEnteroSecuencial());
        document.put("codigo_aficionado", codigoAficionado);
        //Verificar si esta BORRADO LOGICAMENTE EN La MONGODB
        //#################################################
        document.put("fecha", fechaComentario);
        document.put("hora", horaComentario);

        //Seteo comentario del aficionado padre
        document.put("comentario_padre", comentPadre);

        //Actualiza la tupla
        tablaResumen.update(query, document);

        return 0;
    }*/
    //ELIMINAR COMENTARIO:
    /*public int eliminarComentario(int numComentarioEliminar) {
        //Verifico si el resumen existe
        BasicDBObject query = new BasicDBObject("numero_comentario", numComentarioEliminar);
        DBCursor cursor = tablaComentario.find(query);
        if (cursor.hasNext()) {
            //Borro el resumen
            tablaComentario.remove(query);
            return 0;
        } else {
            JOptionPane.showMessageDialog(null, "No existe un comentario con ese numero de partido");
            return 1;
        }
    }*/
    //
    //  CRUD RESUMEN
    //
    //CREATE RESUMEN: Crea un nuevo resumen
    public int createResumen(int numero_partido, String codEq1, String codEq2,
            String txtResumen, String videos) {
        //Validaciones........   
        //Valido que este en ORACLE
        //###########################################################

        //Saco de Oracle codEq1 y codEq2, ya salen validos
        //txtResumen se obtiene de la interfaz y puede ser cualquier signo
        //URLS de videos
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
        document.put("videos", videos);

        //Inserto la tupla
        tablaResumen.insert(document);

        return 0;
    }

    //UPDATE RESUMEN: Modifica el texto y url de los videos de un resumen existente
    public int updateResumen(int numero_partido, String txtResumen, String videos) {
        //Valido......
        //Creo el query, con la condicion numero de partido
        BasicDBObject query = new BasicDBObject("numero_partido", numero_partido);

        //Verifico si el resumen existe y lo obtengo
        DBObject tuplaExistente;
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
        document.put("videos", videos);

        //Actualiza la tupla
        tablaResumen.update(query, document);

        return 0;
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

    //
    //  FUNCIONES
    //
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

    //OBTENER COMENTARIOS: Retorna un array con el numero_comentario de los comentarios para un resumen específico y que no tengan padre
    public ArrayList<Integer> obtenerComentarios(int numeroPartido) {
        ArrayList<Integer> resultado = new ArrayList<>();

        //Obtengo las tuplas
        BasicDBObject query = new BasicDBObject();
        query.put("numero_partido", numeroPartido);
        query.put("numero_comentario_padre", 0);
        DBCursor cursor = tablaComentario.find(query);
        while (cursor.hasNext()) {
            DBObject tupla = cursor.next();
            resultado.add((int) tupla.get("numero_comentario"));
        }

        return resultado;
    }

    //OBTENER RESPUESTAS: Retorna un array con el numero_comentario de los comentarios con un numero_comentario_padre específico
    public ArrayList<Integer> obtenerRespuestas(int numeroComentarioPadre) {
        ArrayList<Integer> resultado = new ArrayList<>();

        //Obtengo las tuplas
        BasicDBObject query = new BasicDBObject("numero_comentario_padre", numeroComentarioPadre);
        DBCursor cursor = tablaComentario.find(query);
        while (cursor.hasNext()) {
            DBObject tupla = cursor.next();
            resultado.add((int) tupla.get("numero_comentario"));
        }

        return resultado;
    }

    //OBTENER AFICIONADOS
    public ArrayList<String> obtenerAficionados() {
        ArrayList<String> resultado = new ArrayList<>();

        //Obtengo todas las tuplas
        DBCursor cursor = tablaAficionado.find();
        while (cursor.hasNext()) {
            DBObject tupla = cursor.next();
            resultado.add((String) tupla.get("codigo_aficionado"));
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

    //READ COMENTARIO
    public DBObject readComentario(int numeroComentario) {
        DBObject resultado = null;

        //Creo el query
        BasicDBObject query = new BasicDBObject("numero_comentario", numeroComentario);
        //Obtengo el resultado
        DBCursor cursor = tablaComentario.find(query);
        while (cursor.hasNext()) {
            resultado = cursor.next();
        }

        return resultado;
    }

    //READ AFICIONADO
    public DBObject readAficionado(String codAfi) {
        DBObject resultado = null;

        //Creo el query
        BasicDBObject query = new BasicDBObject("codigo_aficionado", codAfi);
        //Obtengo el resultado
        DBCursor cursor = tablaAficionado.find(query);
        while (cursor.hasNext()) {
            resultado = cursor.next();
        }

        return resultado;
    }

    //ABRIR VIDEO EN EXPLORADOR
    private void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void openWebpage(String direccion) {
        try {
            URL link = new URL(direccion);
            openWebpage(link.toURI());
        } catch (MalformedURLException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
