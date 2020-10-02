package mx.gob.cdmx.estudioscdmx.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import mx.gob.cdmx.estudioscdmx.R;


public class SubeDatoActivity extends AsyncTask<String, Void, String> {

	private Context context;
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	String imei_num;
	private SQLiteDatabase db;
    public static final int PERMISSION_REQUEST_CODE = 1;


	Calendar c = Calendar.getInstance();
	SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
	String formattedDateFecha = df3.format(c.getTime());
	SimpleDateFormat df5 = new SimpleDateFormat("HH:mm");
	String formattedDateHora = df5.format(c.getTime());

	int serverResponseCode = 0;

	static InputStream is2 = null;


	public SubeDatoActivity(Context context) {
		this.context = context;
	}

	protected void onPreExecute() {

}


	@Override
	protected String doInBackground(String... arg0) {

		String token = arg0[0];

		String latitud = arg0[1];
		String longitud = arg0[2];
		String envviado = arg0[3];
		String orden_entrega = arg0[4];
		String data = arg0[5];
		String delivered = arg0[6];
		String observations = arg0[7];

/*		params.put("latitude", latitude);
		params.put("longitude", longitude);
		params.put("shipping_order", ordenSelect.getIdOrdenEntrega());
		params.put("data", json);
		params.put("delivered", delivered);
		params.put("observations", strDatosObservaciones);*/

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(this.context.getResources().getString(R.string.url_aplicacion) + "/setDelivery?latitude=" + latitud + "&longitude=" + longitud + "&token=" + token + "&delivered="+delivered+"& ");

			HttpResponse response2 = httpclient.execute(httppost);
			HttpEntity entity2 = response2.getEntity();
			is2 = entity2.getContent();

			Log.i("log_tag", "connection success ");
			Log.i("log_tag", "connection: " + this.context.getResources().getString(R.string.url_aplicacion) + "/setDelivery?latitude=" + latitud + "&longitude=" + longitud + "&token=" + token + "&delivered="+delivered+"");
//			Log.i("log_tag", "Imei "+elImei);


		} catch (Exception e) {
			String stackTrace = Log.getStackTraceString(e);
			Log.i("log_tag", "connection error" + stackTrace);
			Log.i("log_tag", "Error in http connection " + e.toString());

		}

		return null;

	}

	@Override
	protected void onPostExecute(String result) {

    }


}
