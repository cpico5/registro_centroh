package mx.gob.cdmx.estudioscdmx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.InputStream;
import java.util.ArrayList;

import mx.gob.cdmx.estudioscdmx.db.DaoManager;
import mx.gob.cdmx.estudioscdmx.model.Catalogos;
import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Firma;
import mx.gob.cdmx.estudioscdmx.model.Foto;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

public class SQLiteHelper3 extends SQLiteOpenHelper {

	private static final String ENCODING = "ISO-8859-1";

	public String telefono;
	InputStream ubicacion= null;

	private ArrayList<Class> classArrayList = new ArrayList<>();

	private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() +"/loc/registro_centroh_ZJvI7PooUhZogIarOp8vs_l";
	private static final int DATABASE_VERSION = 1;
	public SQLiteHelper3(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
// TODO Auto-generated constructor stub

		classArrayList.add(Entrevista.class);
		classArrayList.add(Foto.class);
		classArrayList.add(Firma.class);


	}


	@Override
	public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
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