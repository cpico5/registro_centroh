package mx.gob.cdmx.estudioscdmx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.InputStream;
import java.util.ArrayList;

import mx.gob.cdmx.estudioscdmx.db.DaoManager;
import mx.gob.cdmx.estudioscdmx.model.Catalogos;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

public class UsuariosSQLiteHelper3 extends SQLiteOpenHelper {

	private static final String ENCODING = "ISO-8859-1";

	public String telefono;
	InputStream ubicacion= null;

	private ArrayList<Class> classArrayList = new ArrayList<>();

	private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() +"/loc/registro_centroh_ZJvI7PooUhZogIarOp8vs";
	private static final int DATABASE_VERSION = 3;
	public UsuariosSQLiteHelper3(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub

		classArrayList.add(Usuario.class);
		classArrayList.add(Catalogos.class);


	}

	public static class TablaUbicacion {
		public static String TABLA_UBICACION = "ubicacion";
		public static String COLUMNA_ID = "id";
		public static String COLUMNA_fecha = "fecha";
		public static String COLUMNA_hora = "hora";
		public static String COLUMNA_LATITUD = "latitud";
		public static String COLUMNA_LONGITUD = "longitud";
		public static String COLUMNA_DIRECCION = "direccion";
	}

	private static final String DATABASE_UBICACION = "create table "
	+ TablaUbicacion.TABLA_UBICACION + "("
	+ "id integer primary key autoincrement,"
	+ TablaUbicacion.COLUMNA_fecha + " text, "
	+ TablaUbicacion.COLUMNA_hora + " text, "
	+ TablaUbicacion.COLUMNA_LATITUD + " text, "
	+ TablaUbicacion.COLUMNA_LONGITUD + " text, "
	+ TablaUbicacion.COLUMNA_DIRECCION + " text); ";

	@Override
	public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
		db.execSQL(DATABASE_UBICACION);
//	        cargaUbicacion(db);
		DaoManager daoManager = new DaoManager(db);
		try {
			for (Class aClass : classArrayList){
				daoManager.createTable(aClass);
			}
		}catch (Exception e){

		}

	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
		db.execSQL("drop table if exists " + TablaUbicacion.TABLA_UBICACION);

		DaoManager daoManager = new DaoManager(db);
		try {
			for (Class aClass : classArrayList){
				daoManager.generateDropIfExistsQueryString(aClass);
			}
		}catch (Exception e){

		}
		onCreate(db);
	}
}
