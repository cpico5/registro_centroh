package mx.gob.cdmx.estudioscdmx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.UUID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    private static final String ENCODING = "ISO-8859-1";


    public static String getHostName(String defValue) {
        try {
            Method getString = Build.class.getDeclaredMethod("getString", String.class);
            getString.setAccessible(true);
            return getString.invoke(null, "net.hostname").toString();
        } catch (Exception ex) {
            return defValue;
        }
    }


    UUID uuid = UUID.randomUUID();

    public String tablet;
    InputStream datos, usuarios, nofue, acambio,prd, pri, pan, morena, independiente= null;

    static Nombre nom= new Nombre();
    static String nombreE = nom.nombreEncuesta();



    static String ID = getHostName(null);
    static String prefix = ID;

// private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() +"/Mis_archivos/" +nombreE+"_"+prefix+"";
    private static final int DATABASE_VERSION = 2;

    public UsuariosSQLiteHelper(Context context, String name,CursorFactory factory, int version, String DATABASE_NAME) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub
        try {
            datos = context.getAssets().open("datos.sql");
            usuarios = context.getAssets().open("usuarios.sql");

        } catch (Exception ex) {
            Log.i(null, "HORROR-1: " + ex.fillInStackTrace());
        }

    }

//////////////////////   TABLA DATOS  //////////////////////////////////////////////////////////////


    public static class TablaDatos{
        public static String TABLA_DATOS = "datos";
        public static String COLUMNA_SECCION = "seccion";
        public static String COLUMNA_DISTRITO = "distrito";
        public static String COLUMNA_MUNICIPIO = "municipio";
        public static String COLUMNA_DELEGACION = "delegacion";
        public static String COLUMNA_EQUIPOS = "equipo";
        public static String COLUMNA_COORDINADOR = "coordinador";

    }

    private static final String DATABASE_DATOS = "create table "
    + TablaDatos.TABLA_DATOS + "("
    + TablaDatos.COLUMNA_SECCION + " INTEGER not null, "
    + TablaDatos.COLUMNA_DISTRITO + " text not null, "
    + TablaDatos.COLUMNA_MUNICIPIO + " text not null, "
    + TablaDatos.COLUMNA_DELEGACION + " integer not null, "
    + TablaDatos.COLUMNA_EQUIPOS + " text not null, "
    + TablaDatos.COLUMNA_COORDINADOR + " text not null); ";






/////////////////////  TABLA ENCUESTAS  ///////////////////////////////////////////////

    public static class TablaEncuestas{
        public static String TABLA_ENCUESTAS = "encuestas";
        public static String COLUMNA_CONSECUTIVO_DIARIO = "consecutivo_diario";
        public static String COLUMNA_EQUIPO = "equipo";
        public static String COLUMNA_USUARIO = "usuario";
        public static String COLUMNA_NOMBRE_ENCUESTA = "nombre_encuesta";
        public static String COLUMNA_FECHA = "fecha";
        public static String COLUMNA_HORA = "hora";
        public static String COLUMNA_imei = "imei";
        public static String COLUMNA_entidad = "entidad";
        public static String COLUMNA_alcaldia = "alcaldia";
        public static String COLUMNA_dtto_federal = "dtto_federal";
        public static String COLUMNA_dtto_local = "dtto_local";
        public static String COLUMNA_SECCION = "seccion";
        public static String COLUMNA_latitud = "latitud";
        public static String COLUMNA_longitud = "longitud";
//INICIAN PREGUNTAS
public static String COLUMNA_pregunta_1="pregunta_1";
        public static String COLUMNA_pregunta_2="pregunta_2";
        public static String COLUMNA_pregunta_3="pregunta_3";
        public static String COLUMNA_pregunta_4="pregunta_4";
        public static String COLUMNA_pregunta_5="pregunta_5";
        public static String COLUMNA_pregunta_6="pregunta_6";
        public static String COLUMNA_pregunta_7="pregunta_7";
        public static String COLUMNA_pregunta_pc="pregunta_pc";
        public static String COLUMNA_pregunta_pc_1="pregunta_pc_1";
        public static String COLUMNA_pregunta_pc_2="pregunta_pc_2";
        public static String COLUMNA_pregunta_pc_3="pregunta_pc_3";
        public static String COLUMNA_pregunta_pc_4="pregunta_pc_4";
        public static String COLUMNA_pregunta_8="pregunta_8";
        public static String COLUMNA_pregunta_9="pregunta_9";
        public static String COLUMNA_pregunta_10="pregunta_10";
        public static String COLUMNA_pregunta_11="pregunta_11";
        public static String COLUMNA_pregunta_12="pregunta_12";
        public static String COLUMNA_pregunta_13="pregunta_13";
        public static String COLUMNA_pregunta_14="pregunta_14";
        public static String COLUMNA_pregunta_15="pregunta_15";
        public static String COLUMNA_pregunta_16="pregunta_16";
        public static String COLUMNA_pregunta_16a="pregunta_16a";
        public static String COLUMNA_pregunta_16b="pregunta_16b";
        public static String COLUMNA_pregunta_17="pregunta_17";
        public static String COLUMNA_pregunta_17a="pregunta_17a";
        public static String COLUMNA_pregunta_18="pregunta_18";
        public static String COLUMNA_pregunta_19="pregunta_19";
        public static String COLUMNA_pregunta_19a="pregunta_19a";
        public static String COLUMNA_pregunta_19b="pregunta_19b";
        public static String COLUMNA_pregunta_20="pregunta_20";
        public static String COLUMNA_pregunta_21="pregunta_21";
        public static String COLUMNA_pregunta_22="pregunta_22";
        public static String COLUMNA_pregunta_23="pregunta_23";
        public static String COLUMNA_pregunta_24="pregunta_24";
        public static String COLUMNA_pregunta_24_1="pregunta_24_1";
        public static String COLUMNA_pregunta_24_1a="pregunta_24_1a";
        public static String COLUMNA_pregunta_24_1b="pregunta_24_1b";
        public static String COLUMNA_pregunta_24_2="pregunta_24_2";
        public static String COLUMNA_pregunta_24_2a="pregunta_24_2a";
        public static String COLUMNA_pregunta_24_2b="pregunta_24_2b";
        public static String COLUMNA_pregunta_24_3="pregunta_24_3";
        public static String COLUMNA_pregunta_24_3a="pregunta_24_3a";
        public static String COLUMNA_pregunta_24_3b="pregunta_24_3b";
        public static String COLUMNA_pregunta_24_4="pregunta_24_4";
        public static String COLUMNA_pregunta_24_4a="pregunta_24_4a";
        public static String COLUMNA_pregunta_24_4b="pregunta_24_4b";
        public static String COLUMNA_pregunta_24_5="pregunta_24_5";
        public static String COLUMNA_pregunta_24_5a="pregunta_24_5a";
        public static String COLUMNA_pregunta_24_5b="pregunta_24_5b";
        public static String COLUMNA_pregunta_24_6 ="pregunta_24_6";
        public static String COLUMNA_pregunta_24_6a="pregunta_24_6a";
        public static String COLUMNA_pregunta_24_6b="pregunta_24_6b";
        public static String COLUMNA_pregunta_24_7 ="pregunta_24_7";
        public static String COLUMNA_pregunta_24_7a="pregunta_24_7a";
        public static String COLUMNA_pregunta_24_7b="pregunta_24_7b";
        public static String COLUMNA_pregunta_24_8 ="pregunta_24_8";
        public static String COLUMNA_pregunta_24_8a="pregunta_24_8a";
        public static String COLUMNA_pregunta_24_8b="pregunta_24_8b";
        public static String COLUMNA_pregunta_24_9 ="pregunta_24_9";
        public static String COLUMNA_pregunta_24_9a="pregunta_24_9a";
        public static String COLUMNA_pregunta_24_9b="pregunta_24_9b";
        public static String COLUMNA_pregunta_24_10 ="pregunta_24_10";
        public static String COLUMNA_pregunta_24_10a="pregunta_24_10a";
        public static String COLUMNA_pregunta_24_10b="pregunta_24_10b";
        public static String COLUMNA_pregunta_25="pregunta_25";
        public static String COLUMNA_pregunta_25_1="pregunta_25_1";
        public static String COLUMNA_pregunta_25_1a="pregunta_25_1a";
        public static String COLUMNA_pregunta_25_1b="pregunta_25_1b";
        public static String COLUMNA_pregunta_25_1c="pregunta_25_1c";
        public static String COLUMNA_pregunta_25_1d="pregunta_25_1d";
        public static String COLUMNA_pregunta_25_2="pregunta_25_2";
        public static String COLUMNA_pregunta_25_2a="pregunta_25_2a";
        public static String COLUMNA_pregunta_25_2b="pregunta_25_2b";
        public static String COLUMNA_pregunta_25_2c="pregunta_25_2c";
        public static String COLUMNA_pregunta_25_2d="pregunta_25_2d";
        public static String COLUMNA_pregunta_25_3="pregunta_25_3";
        public static String COLUMNA_pregunta_25_3a="pregunta_25_3a";
        public static String COLUMNA_pregunta_25_3b="pregunta_25_3b";
        public static String COLUMNA_pregunta_25_3c="pregunta_25_3c";
        public static String COLUMNA_pregunta_25_3d="pregunta_25_3d";
        public static String COLUMNA_pregunta_25_4="pregunta_25_4";
        public static String COLUMNA_pregunta_25_4a="pregunta_25_4a";
        public static String COLUMNA_pregunta_25_4b="pregunta_25_4b";
        public static String COLUMNA_pregunta_25_4c="pregunta_25_4c";
        public static String COLUMNA_pregunta_25_4d="pregunta_25_4d";
        public static String COLUMNA_pregunta_25_5="pregunta_25_5";
        public static String COLUMNA_pregunta_25_5a="pregunta_25_5a";
        public static String COLUMNA_pregunta_25_5b="pregunta_25_5b";
        public static String COLUMNA_pregunta_25_5c="pregunta_25_5c";
        public static String COLUMNA_pregunta_25_5d="pregunta_25_5d";
        public static String COLUMNA_pregunta_25_6="pregunta_25_6";
        public static String COLUMNA_pregunta_25_6a="pregunta_25_6a";
        public static String COLUMNA_pregunta_25_6b="pregunta_25_6b";
        public static String COLUMNA_pregunta_25_6c="pregunta_25_6c";
        public static String COLUMNA_pregunta_25_6d="pregunta_25_6d";
        public static String COLUMNA_pregunta_25_7="pregunta_25_7";
        public static String COLUMNA_pregunta_25_7a="pregunta_25_7a";
        public static String COLUMNA_pregunta_25_7b="pregunta_25_7b";
        public static String COLUMNA_pregunta_25_7c="pregunta_25_7c";
        public static String COLUMNA_pregunta_25_7d="pregunta_25_7d";
        public static String COLUMNA_pregunta_25_8="pregunta_25_8";
        public static String COLUMNA_pregunta_25_8a="pregunta_25_8a";
        public static String COLUMNA_pregunta_25_8b="pregunta_25_8b";
        public static String COLUMNA_pregunta_25_8c="pregunta_25_8c";
        public static String COLUMNA_pregunta_25_8d="pregunta_25_8d";
        public static String COLUMNA_pregunta_25_9="pregunta_25_9";
        public static String COLUMNA_pregunta_25_9a="pregunta_25_9a";
        public static String COLUMNA_pregunta_25_9b="pregunta_25_9b";
        public static String COLUMNA_pregunta_25_9c="pregunta_25_9c";
        public static String COLUMNA_pregunta_25_9d="pregunta_25_9d";
        public static String COLUMNA_pregunta_25_10="pregunta_25_10";
        public static String COLUMNA_pregunta_25_10a="pregunta_25_10a";
        public static String COLUMNA_pregunta_25_10b="pregunta_25_10b";
        public static String COLUMNA_pregunta_25_10c="pregunta_25_10c";
        public static String COLUMNA_pregunta_25_10d="pregunta_25_10d";
        public static String COLUMNA_pregunta_26="pregunta_26";
        public static String COLUMNA_pregunta_27="pregunta_27";



        public static String COLUMNA_aporta  		="aporta";
        public static String COLUMNA_ocupacion  	="ocupacion";
        public static String COLUMNA_cuantos_coches ="cuantos_coches";
        public static String COLUMNA_cuartos  		="cuartos";
        public static String COLUMNA_cuartos_dormir ="cuartos_dormir";
        public static String COLUMNA_focos  		="focos";
        public static String COLUMNA_banos  		="banos";
        public static String COLUMNA_regadera  	="regadera";
        public static String COLUMNA_estufa  		="estufa";
        public static String COLUMNA_edad  		="edad";
        public static String COLUMNA_genero  		="genero";
        public static String COLUMNA_tipo_vivienda ="tipo_vivienda";
        public static String COLUMNA_tipo_piso 	="tipo_piso";


        public static String COLUMNA_abandono="abandono";
        public static String COLUMNA_suma="suma";
        public static String COLUMNA_status="status";
// FINALIZAN PREGUNTAS
        public static String COLUMNA_TIEMPO = "tiempo";
        public static String COLUMNA_TIPO_CAPTURA = "tipo_captura";
        public static String COLUMNA_enviado = "enviado";
    }




    private static final String DATABASE_ENCUESTA= "create table "
            + TablaEncuestas.TABLA_ENCUESTAS + "("
            + "id integer primary key autoincrement,"
            + TablaEncuestas.COLUMNA_CONSECUTIVO_DIARIO + " text not null, "
            + TablaEncuestas.COLUMNA_EQUIPO + " text not null, "
            + TablaEncuestas.COLUMNA_USUARIO + " text not null, "
            + TablaEncuestas.COLUMNA_NOMBRE_ENCUESTA + " text not null, "
            + TablaEncuestas.COLUMNA_FECHA + " date not null, "
            + TablaEncuestas.COLUMNA_HORA + " text not null, "
            + TablaEncuestas.COLUMNA_imei + " text not null, "
            + TablaEncuestas.COLUMNA_entidad + " text not null, "
            + TablaEncuestas.COLUMNA_alcaldia + " text not null, "
            + TablaEncuestas.COLUMNA_dtto_federal + " text not null, "
            + TablaEncuestas.COLUMNA_dtto_local + " text not null, "
            + TablaEncuestas.COLUMNA_SECCION + " INTEGER not null, "
            + TablaEncuestas.COLUMNA_latitud + " text, "
            + TablaEncuestas.COLUMNA_longitud + " text, "

            + TablaEncuestas.COLUMNA_pregunta_1 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_2 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_3 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_4 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_5 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_6 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_7 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_pc +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_pc_1 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_pc_2 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_pc_3 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_pc_4 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_8 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_9 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_10 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_11 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_12 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_13 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_14 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_15 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_16 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_16a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_16b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_17 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_17a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_18 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_19 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_19a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_19b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_20 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_21 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_22 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_23 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_1 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_1a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_1b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_2 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_2a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_2b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_3 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_3a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_3b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_4 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_4a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_4b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_5 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_5a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_5b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_6 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_6a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_6b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_7 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_7a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_7b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_8 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_8a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_8b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_9 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_9a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_9b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_10 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_10a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_24_10b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_1 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_1a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_1b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_1c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_1d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_2 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_2a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_2b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_2c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_2d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_3 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_3a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_3b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_3c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_3d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_4 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_4a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_4b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_4c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_4d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_5 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_5a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_5b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_5c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_5d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_6 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_6a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_6b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_6c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_6d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_7 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_7a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_7b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_7c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_7d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_8 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_8a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_8b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_8c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_8d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_9 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_9a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_9b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_9c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_9d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_10 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_10a +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_10b +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_10c +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_25_10d +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_26 +  " text, "
            + TablaEncuestas.COLUMNA_pregunta_27 +  " text, "

            + TablaEncuestas.COLUMNA_aporta  +  " text, "
            + TablaEncuestas.COLUMNA_ocupacion  +  " text, "
            + TablaEncuestas.COLUMNA_cuantos_coches  +  " text, "
            + TablaEncuestas.COLUMNA_cuartos  +  " text, "
            + TablaEncuestas.COLUMNA_cuartos_dormir  +  " text, "
            + TablaEncuestas.COLUMNA_focos  +  " text, "
            + TablaEncuestas.COLUMNA_banos  +  " text, "
            + TablaEncuestas.COLUMNA_regadera  +  " text, "
            + TablaEncuestas.COLUMNA_estufa  +  " text, "
            + TablaEncuestas.COLUMNA_edad  +  " text, "
            + TablaEncuestas.COLUMNA_genero  +  " text, "
            + TablaEncuestas.COLUMNA_tipo_vivienda  +  " text, "
            + TablaEncuestas.COLUMNA_tipo_piso  +  " text, "

    + TablaEncuestas.COLUMNA_abandono +  " text, "

    + TablaEncuestas.COLUMNA_suma +  " text, "
    + TablaEncuestas.COLUMNA_status +  " text, "
    + TablaEncuestas.COLUMNA_TIEMPO + " text, "
    + TablaEncuestas.COLUMNA_TIPO_CAPTURA + " text, "
    + TablaEncuestas.COLUMNA_enviado + " text not null); ";

////////////////////////////  TABLA USUARIOS	 /////////////////////////////////////////////////////////

    public static class TablaUsuarios{
        public static String TABLA_USUARIOS = "usuarios";
        public static String COLUMNA_USUARIO = "usuario";
        public static String COLUMNA_PASSWORD = "password";

    }

    private static final String DATABASE_USUARIOS = "create table "
    + TablaUsuarios.TABLA_USUARIOS + "("
    + TablaUsuarios.COLUMNA_USUARIO + " text not null, "
    + TablaUsuarios.COLUMNA_PASSWORD+ " text not null); ";


    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        db.execSQL(DATABASE_ENCUESTA);



    }


    public void cargaDatos(SQLiteDatabase db){
        InputStream tabla=datos;
        try {

            if (tabla != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(tabla,ENCODING));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.i(null, "HORROR-2: " + ex.getMessage());
        } finally {
            db.endTransaction();
            if (tabla != null) {
                try {
                    tabla.close();
                } catch (IOException e) {
                    Log.i(null, "HORROR-3; " + e.getMessage());
                }
            }
        }

    }

    public void cargaUsuarios(SQLiteDatabase db){
        InputStream tabla=usuarios;
        try {

            if (tabla != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(tabla,ENCODING));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.i(null, "HORROR-2: " + ex.getMessage());
        } finally {
            db.endTransaction();
            if (tabla != null) {
                try {
                    tabla.close();
                } catch (IOException e) {
                    Log.i(null, "HORROR-3; " + e.getMessage());
                }
            }
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        db.execSQL("drop table if exists " + TablaEncuestas.TABLA_ENCUESTAS);
        onCreate(db);
    }
}
