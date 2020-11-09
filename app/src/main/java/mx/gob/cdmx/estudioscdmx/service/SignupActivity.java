package mx.gob.cdmx.estudioscdmx.service;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import mx.gob.cdmx.estudioscdmx.MainActivityPantalla1;
import mx.gob.cdmx.estudioscdmx.Nombre;
import mx.gob.cdmx.estudioscdmx.UsuariosSQLiteHelper;
import mx.gob.cdmx.estudioscdmx.db.DaoManager;
import mx.gob.cdmx.estudioscdmx.model.DatoContent;
import mx.gob.cdmx.estudioscdmx.model.Usuario;
import mx.gob.cdmx.estudioscdmx.UsuariosSQLiteHelper3;
import mx.gob.cdmx.estudioscdmx.R;
import mx.gob.cdmx.estudioscdmx.model.encuestas;

import static mx.gob.cdmx.estudioscdmx.Nombre.URLApi;
import static mx.gob.cdmx.estudioscdmx.Nombre.customURL;
import static mx.gob.cdmx.estudioscdmx.Nombre.encuesta;


public class SignupActivity extends AsyncTask<String, Void, String> {

	final static String TAG = "SignupActivity";
	private Context context;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	String imei_num;
	UsuariosSQLiteHelper3 usdbh3;
	private SQLiteDatabase db3;

	UsuariosSQLiteHelper usdbh;
	private SQLiteDatabase db;
	private encuestas estudiosCdmx = new encuestas();

    public static final int PERMISSION_REQUEST_CODE = 1;
    private WifiState wifiState;
    private Imei imei;

    private encuestas estudios_cdmxs = new encuestas();

    private String latitud;
    private String longitud;
	SimpleDateFormat sdFecha = new SimpleDateFormat("yyyy/MM/dd");
	SimpleDateFormat sdHora = new SimpleDateFormat("HH:mm:ss");
	String fechaStr = "";
	String horaStr = "";

	DaoManager daoManager;

	private Usuario usuario;

    private Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	Calendar c = Calendar.getInstance();
	SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
	String formattedDateFecha = df3.format(c.getTime());
	SimpleDateFormat df5 = new SimpleDateFormat("HH:mm");
	String formattedDateHora = df5.format(c.getTime());
	SimpleDateFormat ayer = new SimpleDateFormat("yyyyMMdd");
	String formattedDateAyer = ayer.format(yesterday());

	int serverResponseCode = 0;

	static InputStream is2 = null;


	public SignupActivity(Context context) {
		this.context = context;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
    protected void onPreExecute() {

        Intent serviceIntent = new Intent(context, GPSWidgetProvider.GPSWidgetService.class);
//        ContextCompat.startForegroundService(context, serviceIntent );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ContextCompat.startForegroundService(context, serviceIntent );
		} else {
            context.startService(serviceIntent);
        }

        wifiState = new WifiState(context);

        try{
            imei = new Imei(this.context);
            imei_num = imei.getImei().toString();
            Log.i(null, "El chip: " + imei_num);

			usdbh3 = new UsuariosSQLiteHelper3(this.context);
			db3 = usdbh3.getWritableDatabase();
			latitud = "";
			longitud = "";

			usdbh = new UsuariosSQLiteHelper(this.context, null, null, 0, Nombre.encuesta);
			db = usdbh.getWritableDatabase();


        }catch(Exception ex){
            ex.printStackTrace();
        }

	}


	@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
	protected String doInBackground(String... arg0) {

		latitud = arg0[0];
		longitud = arg0[1];
		String direccion = arg0[2];

		String newDireccion = direccion.replaceAll("\\s", "_");
		final String laDireccion = newDireccion.trim();

		final String hora = formattedDateHora;
		final String elImei = imei_num;
		final String token = "ZJvI7PooUhZogIarOp8v";
		final String project = this.context.getResources().getString((R.string.app_project));
		final String url;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			url=this.context.getResources().getString(R.string.urls_gps);
		} else {
			url=this.context.getResources().getString(R.string.url_gps);
		}

		if (wifiState.haveNetworkConnection()) {


			fechaStr = sdFecha.format(new Date());
			horaStr = sdHora.format(new Date());


			imei = new Imei(this.context);
			Nombre nom = new Nombre();
			String nombreEncuesta = nom.nombreEncuesta();

			String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
					+ imei.getImei() + "";

			usdbh = new UsuariosSQLiteHelper(context, "F", null, 1, DATABASE_NAME);
			db = usdbh.getReadableDatabase();

			final DaoManager daoManager = new DaoManager(db);
			//estudiosCdmx = (encuestas) daoManager.findByNoSend(encuestas.class, "0", null) ;

			estudiosCdmx = null;

			if (estudiosCdmx != null){

				ContentValues values = new ContentValues();

				values.put("consecutivo",estudiosCdmx.getId());
				values.put("consecutivo_diario",estudiosCdmx.getConsecutivo_diario());
				values.put("equipo",estudiosCdmx.getEquipo());
				values.put("usuario",estudiosCdmx.getUsuario());
				values.put("nombre_encuesta",estudiosCdmx.getNombre_encuesta());
				values.put("fecha",estudiosCdmx.getFecha());
				values.put("hora",estudiosCdmx.getHora());
				values.put("imei",estudiosCdmx.getImei());
				values.put("entidad",estudiosCdmx.getEntidad());
				values.put("alcaldia",estudiosCdmx.getAlcaldia());
				values.put("dtto_federal",estudiosCdmx.getDtto_federal());
				values.put("dtto_local",estudiosCdmx.getDtto_local());
				values.put("seccion",estudiosCdmx.getSeccion());
				values.put("latitud",estudiosCdmx.getLatitud());
				values.put("longitud",estudiosCdmx.getLongitud());
				values.put("pregunta_1",estudiosCdmx.getPregunta_1());
				values.put("pregunta_2",estudiosCdmx.getPregunta_2());
				values.put("pregunta_3",estudiosCdmx.getPregunta_3());
				values.put("pregunta_4",estudiosCdmx.getPregunta_4());
				values.put("pregunta_5",estudiosCdmx.getPregunta_5());
				values.put("pregunta_6",estudiosCdmx.getPregunta_6());
				values.put("pregunta_7",estudiosCdmx.getPregunta_7());
				values.put("pregunta_pc",estudiosCdmx.getPregunta_pc());
				values.put("pregunta_pc_1",estudiosCdmx.getPregunta_pc_1());
				values.put("pregunta_pc_2",estudiosCdmx.getPregunta_pc_2());
				values.put("pregunta_pc_3",estudiosCdmx.getPregunta_pc_3());
				values.put("pregunta_pc_4",estudiosCdmx.getPregunta_pc_4());
				values.put("pregunta_8",estudiosCdmx.getPregunta_8());
				values.put("pregunta_9",estudiosCdmx.getPregunta_9());
				values.put("pregunta_10",estudiosCdmx.getPregunta_10());
				values.put("pregunta_11",estudiosCdmx.getPregunta_11());
				values.put("pregunta_12",estudiosCdmx.getPregunta_12());
				values.put("pregunta_13",estudiosCdmx.getPregunta_13());
				values.put("pregunta_14",estudiosCdmx.getPregunta_14());
				values.put("pregunta_15",estudiosCdmx.getPregunta_15());
				values.put("pregunta_16",estudiosCdmx.getPregunta_16());
				values.put("pregunta_16a",estudiosCdmx.getPregunta_16a());
				values.put("pregunta_16b",estudiosCdmx.getPregunta_16b());
				values.put("pregunta_17",estudiosCdmx.getPregunta_17());
				values.put("pregunta_17a",estudiosCdmx.getPregunta_17a());
				values.put("pregunta_18",estudiosCdmx.getPregunta_18());
				values.put("pregunta_19",estudiosCdmx.getPregunta_19());
				values.put("pregunta_19a",estudiosCdmx.getPregunta_19a());
				values.put("pregunta_19b",estudiosCdmx.getPregunta_19b());
				values.put("pregunta_20",estudiosCdmx.getPregunta_20());
				values.put("pregunta_21",estudiosCdmx.getPregunta_21());
				values.put("pregunta_22",estudiosCdmx.getPregunta_22());
				values.put("pregunta_23",estudiosCdmx.getPregunta_23());
				values.put("pregunta_24",estudiosCdmx.getPregunta_24());
				values.put("pregunta_24_1",estudiosCdmx.getPregunta_24_1());
				values.put("pregunta_24_1a",estudiosCdmx.getPregunta_24_1a());
				values.put("pregunta_24_1b",estudiosCdmx.getPregunta_24_1b());
				values.put("pregunta_24_2",estudiosCdmx.getPregunta_24_2());
				values.put("pregunta_24_2a",estudiosCdmx.getPregunta_24_2a());
				values.put("pregunta_24_2b",estudiosCdmx.getPregunta_24_2b());
				values.put("pregunta_24_3",estudiosCdmx.getPregunta_24_3());
				values.put("pregunta_24_3a",estudiosCdmx.getPregunta_24_3a());
				values.put("pregunta_24_3b",estudiosCdmx.getPregunta_24_3b());
				values.put("pregunta_24_4",estudiosCdmx.getPregunta_24_4());
				values.put("pregunta_24_4a",estudiosCdmx.getPregunta_24_4a());
				values.put("pregunta_24_4b",estudiosCdmx.getPregunta_24_4b());
				values.put("pregunta_24_5",estudiosCdmx.getPregunta_24_5());
				values.put("pregunta_24_5a",estudiosCdmx.getPregunta_24_5a());
				values.put("pregunta_24_5b",estudiosCdmx.getPregunta_24_5b());
				values.put("pregunta_24_6",estudiosCdmx.getPregunta_24_6());
				values.put("pregunta_24_6a",estudiosCdmx.getPregunta_24_6a());
				values.put("pregunta_24_6b",estudiosCdmx.getPregunta_24_6b());
				values.put("pregunta_24_7",estudiosCdmx.getPregunta_24_7());
				values.put("pregunta_24_7a",estudiosCdmx.getPregunta_24_7a());
				values.put("pregunta_24_7b",estudiosCdmx.getPregunta_24_7b());
				values.put("pregunta_24_8",estudiosCdmx.getPregunta_24_8());
				values.put("pregunta_24_8a",estudiosCdmx.getPregunta_24_8a());
				values.put("pregunta_24_8b",estudiosCdmx.getPregunta_24_8b());
				values.put("pregunta_24_9",estudiosCdmx.getPregunta_24_9());
				values.put("pregunta_24_9a",estudiosCdmx.getPregunta_24_9a());
				values.put("pregunta_24_9b",estudiosCdmx.getPregunta_24_9b());
				values.put("pregunta_24_10",estudiosCdmx.getPregunta_24_10());
				values.put("pregunta_24_10a",estudiosCdmx.getPregunta_24_10a());
				values.put("pregunta_24_10b",estudiosCdmx.getPregunta_24_10b());
				values.put("pregunta_25",estudiosCdmx.getPregunta_25());
				values.put("pregunta_25_1",estudiosCdmx.getPregunta_25_1());
				values.put("pregunta_25_1a",estudiosCdmx.getPregunta_25_1a());
				values.put("pregunta_25_1b",estudiosCdmx.getPregunta_25_1b());
				values.put("pregunta_25_1c",estudiosCdmx.getPregunta_25_1c());
				values.put("pregunta_25_1d",estudiosCdmx.getPregunta_25_1d());
				values.put("pregunta_25_2",estudiosCdmx.getPregunta_25_2());
				values.put("pregunta_25_2a",estudiosCdmx.getPregunta_25_2a());
				values.put("pregunta_25_2b",estudiosCdmx.getPregunta_25_2b());
				values.put("pregunta_25_2c",estudiosCdmx.getPregunta_25_2c());
				values.put("pregunta_25_2d",estudiosCdmx.getPregunta_25_2d());
				values.put("pregunta_25_3",estudiosCdmx.getPregunta_25_3());
				values.put("pregunta_25_3a",estudiosCdmx.getPregunta_25_3a());
				values.put("pregunta_25_3b",estudiosCdmx.getPregunta_25_3b());
				values.put("pregunta_25_3c",estudiosCdmx.getPregunta_25_3c());
				values.put("pregunta_25_3d",estudiosCdmx.getPregunta_25_3d());
				values.put("pregunta_25_4",estudiosCdmx.getPregunta_25_4());
				values.put("pregunta_25_4a",estudiosCdmx.getPregunta_25_4a());
				values.put("pregunta_25_4b",estudiosCdmx.getPregunta_25_4b());
				values.put("pregunta_25_4c",estudiosCdmx.getPregunta_25_4c());
				values.put("pregunta_25_4d",estudiosCdmx.getPregunta_25_4d());
				values.put("pregunta_25_5",estudiosCdmx.getPregunta_25_5());
				values.put("pregunta_25_5a",estudiosCdmx.getPregunta_25_5a());
				values.put("pregunta_25_5b",estudiosCdmx.getPregunta_25_5b());
				values.put("pregunta_25_5c",estudiosCdmx.getPregunta_25_5c());
				values.put("pregunta_25_5d",estudiosCdmx.getPregunta_25_5d());
				values.put("pregunta_25_6",estudiosCdmx.getPregunta_25_6());
				values.put("pregunta_25_6a",estudiosCdmx.getPregunta_25_6a());
				values.put("pregunta_25_6b",estudiosCdmx.getPregunta_25_6b());
				values.put("pregunta_25_6c",estudiosCdmx.getPregunta_25_6c());
				values.put("pregunta_25_6d",estudiosCdmx.getPregunta_25_6d());
				values.put("pregunta_25_7",estudiosCdmx.getPregunta_25_7());
				values.put("pregunta_25_7a",estudiosCdmx.getPregunta_25_7a());
				values.put("pregunta_25_7b",estudiosCdmx.getPregunta_25_7b());
				values.put("pregunta_25_7c",estudiosCdmx.getPregunta_25_7c());
				values.put("pregunta_25_7d",estudiosCdmx.getPregunta_25_7d());
				values.put("pregunta_25_8",estudiosCdmx.getPregunta_25_8());
				values.put("pregunta_25_8a",estudiosCdmx.getPregunta_25_8a());
				values.put("pregunta_25_8b",estudiosCdmx.getPregunta_25_8b());
				values.put("pregunta_25_8c",estudiosCdmx.getPregunta_25_8c());
				values.put("pregunta_25_8d",estudiosCdmx.getPregunta_25_8d());
				values.put("pregunta_25_9",estudiosCdmx.getPregunta_25_9());
				values.put("pregunta_25_9a",estudiosCdmx.getPregunta_25_9a());
				values.put("pregunta_25_9b",estudiosCdmx.getPregunta_25_9b());
				values.put("pregunta_25_9c",estudiosCdmx.getPregunta_25_9c());
				values.put("pregunta_25_9d",estudiosCdmx.getPregunta_25_9d());
				values.put("pregunta_25_10",estudiosCdmx.getPregunta_25_10());
				values.put("pregunta_25_10a",estudiosCdmx.getPregunta_25_10a());
				values.put("pregunta_25_10b",estudiosCdmx.getPregunta_25_10b());
				values.put("pregunta_25_10c",estudiosCdmx.getPregunta_25_10c());
				values.put("pregunta_25_10d",estudiosCdmx.getPregunta_25_10d());
				values.put("pregunta_26",estudiosCdmx.getPregunta_26());
				values.put("pregunta_27",estudiosCdmx.getPregunta_27());
				values.put("aporta",estudiosCdmx.getAporta());
				values.put("ocupacion",estudiosCdmx.getOcupacion());
				values.put("cuantos_coches",estudiosCdmx.getCuantos_coches());
				values.put("cuartos",estudiosCdmx.getCuartos());
				values.put("cuartos_dormir",estudiosCdmx.getCuartos_dormir());
				values.put("focos",estudiosCdmx.getFocos());
				values.put("banos",estudiosCdmx.getBanos());
				values.put("regadera",estudiosCdmx.getRegadera());
				values.put("estufa",estudiosCdmx.getEstufa());
				values.put("edad",estudiosCdmx.getEdad());
				values.put("genero",estudiosCdmx.getGenero());
				values.put("tipo_vivienda",estudiosCdmx.getTipo_vivienda());
				values.put("tipo_piso",estudiosCdmx.getTipo_piso());
				values.put("abandono",estudiosCdmx.getAbandono());
				values.put("suma",estudiosCdmx.getSuma());
				values.put("status",estudiosCdmx.getStatus());
				values.put("tiempo",estudiosCdmx.getTiempo());
				values.put("tipo_captura",estudiosCdmx.getTipo_captura());


				DatoContent datoContent = new DatoContent();
				List<DatoContent> listaContenido = new ArrayList();
				Set<Map.Entry<String, Object>> s = values.valueSet();
				Iterator itr = s.iterator();
				while (itr.hasNext()) {
					Map.Entry me = (Map.Entry) itr.next();
					String key = me.getKey().toString();
					Object value = me.getValue();

					datoContent = new DatoContent();
					datoContent.setKey(key);
					datoContent.setValue(String.valueOf(value));

					listaContenido.add(datoContent);
				}

				Gson gson = new Gson();
				Type collectionType = new TypeToken<List<DatoContent>>() {
				}.getType();
				String json = gson.toJson(listaContenido, collectionType);

				RequestParams params = new RequestParams();
				params.put("api", "guarda_encuesta");
				params.put("encuestas", encuesta);
				params.put("data", json);


				AsyncHttpClient client = new AsyncHttpClient();
				client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
//client.addHeader("Authorization", "Bearer " + usuario.getToken());
				client.setTimeout(60000);

				RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler(Looper.getMainLooper()) {

					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						Log.d(TAG, "cqs -----------> Respuesta OK ");
						Log.d(TAG, "cqs -----------> " + new String(responseBody));
						try {


							String json = new String(responseBody);

							if (json != null && !json.isEmpty()) {

								Gson gson = new Gson();
								JSONObject jsonObject = new JSONObject(json);
								Log.d(TAG, "cqs -----------> Data: " + jsonObject.get("data"));

								String login = jsonObject.getJSONObject("response").get("code").toString();
								if (Integer.valueOf(login) == 1) {
									estudiosCdmx.setEnviado("1");
									daoManager.update(estudiosCdmx,"id=?",new String[]{String.valueOf(estudiosCdmx.getId())});

								} else {
								}
							}





						} catch (Exception e) {
							Log.e(TAG, e.getMessage());
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
						try {
							Log.e(TAG, "PIMC-----------------> existe un error en la conexi?n -----> " + error.getMessage());
							if (responseBody != null)
								Log.d(TAG, "pimc -----------> " + new String(responseBody));

						} catch (Exception ex) {
							ex.printStackTrace();
						}

						if (statusCode != 200) {
							Log.e(TAG, "Existe un error en la conexi?n -----> " + error.getMessage());
							if (responseBody != null)
								Log.d(TAG, "pimc -----------> " + new String(responseBody));

						}

											}
				});


			}


			Calendar calendar = Calendar.getInstance();
			Date horaActual= null;
			Date FechaActual= null;

			Log.i("log_tag","---------->> Hora sacada de la Base: "+sacaHora());
			Log.i("log_tag","---------->> Fecha sacada de la Base: "+sacaFecha());

			if(sacaHora()==null||sacaFecha()==null){

				Log.i("log_tag","---------->> Fecha Base entra en  null: "+ sacaFecha());
			Log.i("log_tag","---------->> Hora Base entra en null: "+ sacaHora());


			try {

				Log.i("log_tag", "connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

				RequestParams params = new RequestParams();
				params.put("api", "guarda_ubicacion");
				params.put("hora", sacaHora());
				params.put("latitude", latitud);
				params.put("longitude", longitud);
				params.put("data", laDireccion);
				params.put("imei", imei_num);
				params.put("token", token);
				params.put("project", project);

				AsyncHttpClient client = new AsyncHttpClient();
				RequestHandle requestHandle = client.post(URLApi, params, new AsyncHttpResponseHandler(Looper.getMainLooper()) {

					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

						Log.d(TAG, "log_tag" + new String(responseBody));
						String json = new String(responseBody);
						try {

							Log.i("log_tag", "Borrando Ubicacion anterior");

							Log.i("log_tag", "Insertando ubicacion actual");


						} catch (Exception ex) {
							Log.e(TAG, ex.getMessage());
						}


					}

					@Override
					public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
						if (statusCode != 200) {
							Log.e(TAG, "existe un error en la conexión -----> " + error.getMessage());
							if(responseBody != null){
								Log.d(TAG, "cqs -----------> " + new String(responseBody));
								String json = new String(responseBody);
							}

						}
					}
				});




				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitud=" + latitud + "&longitud=" + longitud + "&direccion=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

				HttpResponse response2 = httpclient.execute(httppost);
				HttpEntity entity2 = response2.getEntity();
				is2 = entity2.getContent();

				Log.i("log_tag", "connection success ");
				Log.i("log_tag", "connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);


				if (db3 != null) {

					db3.execSQL("DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
					Log.i("log_tag", "connection: " + "DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
					db3.execSQL("INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
					Log.i("log_tag", "connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");


					if(usuario !=  null){
                        String[] enviado = {"0"};

					    //encuestas = (Encuesta) objectRepository.findOne(Encuesta.class,"enviado=?",
                          //      enviado,null,null,null,null);

					}

				} else {
					Log.i("log_tag", "mal connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
				}


			} catch (Exception e) {
				String stackTrace = Log.getStackTraceString(e);
				Log.i("log_tag", "connection error" + stackTrace);
				Log.i("log_tag", "Error in http connection " + e.toString());

			}

			}else {


				Calendar c = Calendar.getInstance();

				SimpleDateFormat df1 = new SimpleDateFormat("yyyMMdd");
				String formattedDate1 = df1.format(c.getTime());

				SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
				String formattedDate2 = df2.format(c.getTime());

				SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
				String formattedDate3 = df3.format(c.getTime());

				SimpleDateFormat df4 = new SimpleDateFormat("HH:mm:ss a");
				String formattedDate4 = df4.format(c.getTime());

				SimpleDateFormat df5 = new SimpleDateFormat("HH:mm:ss");
				String formattedDate5 = df5.format(c.getTime());





				//Obtiene la Fecha
				try {
					FechaActual = new SimpleDateFormat("yyyy/MM/dd").parse(fechaStr);
					Log.i("log_tag","---------->> La Fecha actual: "+formattedDate3);
					Log.i("log_tag","---------->> La Fecha base: "+sacaFecha());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				Date horaBase = null;
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				try {
					horaBase = format.parse(sacaHora());
					Log.i("log_tag","---------->> Hora sacada de la Base: "+horaBase);
				} catch (ParseException e) {
					e.printStackTrace();
					Log.i("log_tag","---------->> Error Hora sacada de la Base: "+horaBase);
				}

				try {
					horaActual = new SimpleDateFormat("HH:mm").parse(horaStr);
					Log.i("log_tag","---------->> Hora actual: "+horaActual);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				calendar.setTime(horaBase);
				calendar.add(Calendar.MINUTE, 2);
				Date horaActualizacion = calendar.getTime();
				Log.i("log_tag","---------->> Hora de la actualización: "+horaActualizacion);



				if(formattedDate3.equals(sacaFecha())){

					Log.i("log_tag","---------->> SON IGUALES ");



					//valida el tiempo
					if(horaActual.after(horaActualizacion)){
						Log.i("log_tag","---------->> Hora actualización: "+ "esta despues  Si mando");

						try {

							Log.i("log_tag", "connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

							RequestParams params = new RequestParams();
							params.put("api", "guarda_ubicacion");
							params.put("latitude", latitud);
							params.put("longitude", longitud);
							params.put("data", laDireccion);
							params.put("imei", imei_num);
							params.put("token", token);
							params.put("project", project);

							AsyncHttpClient client = new AsyncHttpClient();
							RequestHandle requestHandle = client.post(URLApi + "ubicaciones.php", params, new AsyncHttpResponseHandler(Looper.getMainLooper()) {

								@Override
								public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

									Log.d(TAG, "pimc ---------->> " + new String(responseBody));
									String json = new String(responseBody);
									try {

										Log.i("log_tag", "---------->> Borrando Ubicacion anterior");

										Log.i("log_tag", "---------->> Insertando ubicacion actual");

										//ejecuta el background

										if(usuario !=  null){
											String[] enviado = {"0"};

											//encuestas = (Encuesta) objectRepository.findOne(Encuesta.class,"enviado=?",
											//      enviado,null,null,null,null);

										}

										if(json != null && !json.equals("")){

											//cacha el msj del alerta

										}


									} catch (Exception ex) {
										Log.e(TAG, ex.getMessage());
									}


								}

								@Override
								public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
									if (statusCode != 200) {
										Log.e(TAG, "existe un error en la conexión ---------->>  " + error.getMessage());
										if(responseBody != null){
											Log.d(TAG, "e2lira ---------->>  " + new String(responseBody));
											String json = new String(responseBody);
										}

									}
								}
							});




							HttpClient httpclient = new DefaultHttpClient();
							HttpPost httppost = new HttpPost(URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

							HttpResponse response2 = httpclient.execute(httppost);
							HttpEntity entity2 = response2.getEntity();
							is2 = entity2.getContent();

							Log.i("log_tag", "---------->> connection success ");
							Log.i("log_tag", "---------->> connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);


							if (db3 != null) {

								db3.execSQL("DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
								Log.i("log_tag", "connection: " + "DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
								db3.execSQL("INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
								Log.i("log_tag", "connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");


								if(usuario !=  null){
									String[] enviado = {"0"};

									//encuestas = (Encuesta) objectRepository.findOne(Encuesta.class,"enviado=?",
									//      enviado,null,null,null,null);

								}

							} else {
								Log.i("log_tag", " ---------->> mal connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
							}


						} catch (Exception e) {
							String stackTrace = Log.getStackTraceString(e);
							Log.i("log_tag", "---------->> connection error" + stackTrace);
							Log.i("log_tag", "---------->> Error in http connection " + e.toString());

						}

					}else{
						Log.i("log_tag","---------->> Hora actualización: "+ "esta antes NO mando");
					}




				}else{
					Log.i("log_tag","---------->> SON DIFERENTES ");


					try {

						Log.i("log_tag", "connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

						RequestParams params = new RequestParams();
						params.put("api", "guarda_ubicacion");
						params.put("latitude", latitud);
						params.put("longitude", longitud);
						params.put("data", laDireccion);
						params.put("imei", imei_num);
						params.put("token", token);
						params.put("project", project);

						AsyncHttpClient client = new AsyncHttpClient();
						RequestHandle requestHandle = client.post(URLApi + "ubicaciones.php", params, new AsyncHttpResponseHandler(Looper.getMainLooper()) {

							@Override
							public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

								Log.d(TAG, "pimc -----------> " + new String(responseBody));
								String json = new String(responseBody);
								try {

									Log.i("log_tag", "Borrando Ubicacion anterior");

									Log.i("log_tag", "Insertando ubicacion actual");

									//ejecuta el background

									if(usuario !=  null){
										String[] enviado = {"0"};

										//encuestas = (Encuesta) objectRepository.findOne(Encuesta.class,"enviado=?",
										//      enviado,null,null,null,null);

									}

									if(json != null && !json.equals("")){

										//cacha el msj del alerta

									}


								} catch (Exception ex) {
									Log.e(TAG, ex.getMessage());
								}


							}

							@Override
							public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
								if (statusCode != 200) {
									Log.e(TAG, "existe un error en la conexión -----> " + error.getMessage());
									if(responseBody != null){
										Log.d(TAG, "e2lira -----------> " + new String(responseBody));
										String json = new String(responseBody);
									}

								}
							}
						});




						HttpClient httpclient = new DefaultHttpClient();
						HttpPost httppost = new HttpPost(URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);

						HttpResponse response2 = httpclient.execute(httppost);
						HttpEntity entity2 = response2.getEntity();
						is2 = entity2.getContent();

						Log.i("log_tag", "connection success ");
						Log.i("log_tag", "connection: " + URLApi + "ubicaciones.php?hora="+sacaHora()+"&latitude=" + latitud + "&longitude=" + longitud + "&data=" + laDireccion + "&imei=" + elImei + "&token=" + token + "&project=" + project);


						if (db3 != null) {

							db3.execSQL("DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
							Log.i("log_tag", "connection: " + "DELETE FROM ubicacion where fecha='" + formattedDateAyer + "'");
							db3.execSQL("INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
							Log.i("log_tag", "connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");


							if(usuario !=  null){
								String[] enviado = {"0"};

								//encuestas = (Encuesta) objectRepository.findOne(Encuesta.class,"enviado=?",
								//      enviado,null,null,null,null);

							}

						} else {
							Log.i("log_tag", "mal connection: " + "INSERT INTO ubicacion (fecha,hora,latitud,longitud,direccion) values (" + "'" + formattedDateFecha + "'" + "," + "'" + hora + "'" + "," + "'" + latitud + "'" + "," + "'" + longitud + "'" + "," + "'" + laDireccion + "');");
						}


					} catch (Exception e) {
						String stackTrace = Log.getStackTraceString(e);
						Log.i("log_tag", "connection error" + stackTrace);
						Log.i("log_tag", "Error in http connection " + e.toString());

					}



				}



			}



		}

		return null;

	}

	@TargetApi(Build.VERSION_CODES.O)
	@RequiresApi(api = Build.VERSION_CODES.O)
	private void cargaEncuestaWS(){

		String json = "";

		try{

			Gson gson  = new Gson();
			//Type collectionType = new TypeToken<Encuesta>() { }.getType();
			//json = gson.toJson(encuestas,collectionType);

			imei = new Imei(context);

		} catch (Exception ex){
			Log.d(TAG, "pimc -----------> " + ex.getMessage());
		}

		Log.d(TAG, "Token JWT: " + usuario.getToken_type());

		RequestParams params = new RequestParams();
		params.put("data", json);

		params.put("latitude", latitud);
		params.put("longitude", longitud);
		params.put("project", "SituacionCalle");
		params.put("imei", imei.getImei().toString());

		AsyncHttpClient client = new AsyncHttpClient();
		client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
		client.addHeader("Authorization", "Bearer " + usuario.getToken_type());

		RequestHandle requestHandle = client.post(context.getResources().getString(R.string.url_aplicacion) + "/api/situacion_calle/people", params, new AsyncHttpResponseHandler(Looper.getMainLooper()) {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

				Log.d(TAG, "pimc -----------> Realizo la conexión para subir los datos");
				Log.d(TAG, "pimc -----------> " + new String(responseBody));

			/*	try {

					String json = new String(responseBody);
					JSONObject jsonObject = new JSONObject(json);
					Log.d(TAG, "pimc -----------> Data: " + jsonObject.get("data"));
					String idReporte = jsonObject.getJSONObject("data").getString("id");

					encuestas.setIdEncuesta(Integer.valueOf(idReporte));
					encuestas.setEnviado(1);

					String[] idEnc = {String.valueOf(encuestas.getId())};
					objectRepository.update(encuestas,idEnc);

				} catch (JSONException e){
					Log.e(TAG, e.getMessage());
				}  */
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				if (statusCode != 200) {
					Log.e(TAG, "existe un error en la conexión -----> " + error.getMessage());
					if(responseBody != null)
						Log.d(TAG, "e2lira -----------> " + new String(responseBody));

				}

			}
		});
	}


	@Override
	protected void onPostExecute(String result) {
		//db.close();
		System.gc();
    }


	private String sacaFecha() {
		Set<String> set = new HashSet<String>();
		String fecha = null;
		final String F = "File dbfile";
//// Abrimos la base de datos 'DBUsuarios' en modo escritura
//		usdbh = new UsuariosSQLiteHelper(this);
//		db = usdbh.getReadableDatabase();
		String selectQuery = "select fecha from ubicacion order by id desc limit 1";
		Cursor cursor = db3.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				fecha = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		cursor.close();
// db.close();

		return fecha;
	}

	private String sacaHora() {
		Set<String> set = new HashSet<String>();
		String hora = null;
		final String F = "File dbfile";
//// Abrimos la base de datos 'DBUsuarios' en modo escritura
//		usdbh = new UsuariosSQLiteHelper(this);
//		db = usdbh.getReadableDatabase();
		String selectQuery = "select hora from ubicacion order by id desc limit 1";
		Cursor cursor = db3.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				hora = cursor.getString(0);
			} while (cursor.moveToNext());
		}
		cursor.close();
// db.close();

		return hora;
	}


}
