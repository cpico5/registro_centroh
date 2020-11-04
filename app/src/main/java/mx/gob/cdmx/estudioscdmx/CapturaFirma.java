package mx.gob.cdmx.estudioscdmx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Firma;
import mx.gob.cdmx.estudioscdmx.model.Usuario;
import mx.gob.cdmx.estudioscdmx.service.GPSTracker;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;
import static mx.gob.cdmx.estudioscdmx.NotificationService.TAG;

public class CapturaFirma extends Activity {


	Usuario usuario;

	Entrevista entrevista;

	Calendar c = Calendar.getInstance();

	SimpleDateFormat df1 = new SimpleDateFormat("yyy-MM-dd");
	String formattedFecha = df1.format(c.getTime());

	SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
	String formattedDate2 = df2.format(c.getTime());

	SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
	String formattedDate3 = df3.format(c.getTime());

	SimpleDateFormat df6 = new SimpleDateFormat("MM");
	String formattedMes = df6.format(c.getTime());

	SimpleDateFormat df7 = new SimpleDateFormat("dd");
	String formattedDia = df7.format(c.getTime());

	SimpleDateFormat df8 = new SimpleDateFormat("yyyy");
	String formattedAno = df8.format(c.getTime());

	// SimpleDateFormat df4 = new SimpleDateFormat("HH:mm:ss a");
	SimpleDateFormat df4 = new SimpleDateFormat("HH:mm:ss");
	String formattedHora = df4.format(c.getTime());

	SimpleDateFormat df5 = new SimpleDateFormat("HH:mm:ss");
	String formattedDate5 = df5.format(c.getTime());

	private String nombreImagen;
	public String tablet;
	public String maximo = "";
	int elMaximo;

	LinearLayout mContent;
	signature mSignature;
	Button mClear, mGetSign;
	public static String tempDir;
	public int count = 1;
	public String current = null;
	private Bitmap mBitmap;
	View mView;
	File mypath;

	private String uniqueId;
	// private EditText yourName;
	UsuariosSQLiteHelper usdbh;
	private SQLiteDatabase db;
	static InputStream is2 = null;

	String Observaciones;

	/////// METODO PARA VERIFICAR LA CONEXIÓN A INTERNET
	public static boolean verificaConexion(Context ctx) {
		boolean bConectado = false;
		ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		// No sólo wifi, también GPRS
		NetworkInfo[] redes = connec.getAllNetworkInfo();
		// este bucle debería no ser tan ñapa
		for (int i = 0; i < 2; i++) {
			// ¿Tenemos conexión? ponemos a true
			if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
				bConectado = true;
			}
		}
		return bConectado;
	}

	public String sacaImei() {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
			// Log.i("guarda","Mi Número: " + szImei);

			return szImei;
		}
		String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
		// Log.i("guarda","Mi Número: " + szImei);

		return szImei;
	}



	public String cachaDescripcion() {
		Bundle datos = this.getIntent().getExtras();
		String descripcion = datos.getString("descripcion");
		return descripcion;
	}

	public String cachaNombre() {
		Bundle datos = this.getIntent().getExtras();
		String Nombre = datos.getString("Nombre");
		return Nombre;
	}

	public String cachaDiario() {
		Bundle datos = this.getIntent().getExtras();
		String diario = datos.getString("diario");
		return diario;
	}

	public String cachaUsuario() {

//		Bundle datos = this.getIntent().getExtras();
//		String usuario = datos.getString("usuario");

		String usuario ="Usuario";
		return usuario;
	}

	public String cachaNombreE() {
		Bundle datos = this.getIntent().getExtras();
		String nombre_encuesta = datos.getString("nombre_encuesta");
		return nombre_encuesta;
	}

	public String cachaLatitud() {
		Bundle datos = this.getIntent().getExtras();
		String latitud = datos.getString("latitud");
		return latitud;
	}

	public String cachaLongitud() {
		Bundle datos = this.getIntent().getExtras();
		String longitud = datos.getString("longitud");
		return longitud;
	}

	public String cachaTablet() {
		Bundle datos = this.getIntent().getExtras();
		String tablet = datos.getString("tablet");
		return tablet;
	}

	public String cachaAbandono() {
		Bundle datos = this.getIntent().getExtras();
		String abandono = datos.getString("abandono");
		return abandono;
	}

	public String cachaTiempo() {
		Bundle datos = this.getIntent().getExtras();
		String tiempo = datos.getString("tiempo");
		return tiempo;
	}

	public String cachaTipoCaptura() {
		Bundle datos = this.getIntent().getExtras();
		String tipocaptura = datos.getString("tipocaptura");
		return tipocaptura;
	}

	public String nombreArchivo() {
		String date = formattedDate3.toString();
		String var2 = ".txt";
		String var3 = date + var2;
		final String nombre = date + "_" + cachaUsuario();
		return nombre;
	}


	private void copiaImagen() {

		File directory;

		elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
		String diario = String.valueOf(elMaximo);

		if (diario.length() == 1) {
			diario = "00" + diario;
		} else if (diario.length() == 2) {
			diario = "0" + diario;
		} else {
			diario = diario;
		}

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin_firma);
		directory = new File(Environment.getExternalStorageDirectory() + "/Fotos/Firmas_CentroH_"+formattedDate3+"N");
		nombreImagen = getTodaysDate() + "_" + "_" + cachaUsuario() + "_"+ diario + "_4";
		current = nombreImagen + ".jpg";

		String fileName = current;
		File dest = new File(directory, fileName);
		try {
			FileOutputStream out;
			out = new FileOutputStream(dest);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Nombre nom = new Nombre();
	String nombreEncuesta = nom.nombreEncuesta();


	private String sacaMaximo() {

		Set<String> set = new HashSet<String>();

		final String F = "File dbfile";

// Abrimos la base de datos 'DBUsuarios' en modo escritura
		String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"+ sacaImei() + "";
		usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

		db = usdbh.getReadableDatabase();

		String selectQuery = "SELECT count(*) FROM encuestas where fecha='" + formattedFecha + "'";

		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				maximo = cursor.getString(0);

			} while (cursor.moveToNext());
		}

		cursor.close();
		// db.close();

		return maximo;
	}




	public void dialogoObservaciones() {

		final EditText txtObservaciones = new EditText(this);
		txtObservaciones.setHint("Observaciones");
		txtObservaciones.setLines(5);
		
		

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

	    LayoutInflater layoutInflater = CapturaFirma.this.getLayoutInflater();

	    builder.setView(txtObservaciones);

	    builder.setTitle("Observaciones para Bit�cora");
	    builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {

	           Observaciones=txtObservaciones.getText().toString().toUpperCase();
	            dialog.dismiss();

	        }
	    });
	   // builder.setNegativeButton("cancel", null);


	    final AlertDialog alertDialog = builder.create();
	    alertDialog.setCanceledOnTouchOutside(false);
	    alertDialog.setCancelable(false);
	    alertDialog.show();

	    
	    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
	    txtObservaciones.addTextChangedListener(new TextWatcher() {
	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	        }

	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {

	        }

	        @Override
	        public void afterTextChanged(Editable s) {

	            if(s.length()>=1)
	            {
	                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
	            }
	            else {
	                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

	            }

	        }
	    });

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.firma);

		Intent startingIntent = getIntent();
		if(startingIntent == null) {
			Log.e(TAG,"No Intent?  We're not supposed to be here...");
			finish();
			return;
		}

		if (savedInstanceState != null) {
			// Restore value of members from saved state
			usuario = (Usuario) savedInstanceState.getSerializable(USUARIO);
			entrevista = (Entrevista) savedInstanceState.getSerializable(ENTREVISTA);


		} else {
			// Probably initialize members with default values for a new instance
			usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
			entrevista = (Entrevista) startingIntent.getSerializableExtra(ENTREVISTA);
		}

		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			if (Build.VERSION.SDK_INT >= 23) {
				ActivityCompat.requestPermissions(CapturaFirma.this,
						new String[]{Manifest.permission.CAMERA,
								Manifest.permission.WRITE_EXTERNAL_STORAGE,
								Manifest.permission.FOREGROUND_SERVICE,
								Manifest.permission.ACCESS_COARSE_LOCATION,
								Manifest.permission.READ_PHONE_STATE,
								Manifest.permission.READ_EXTERNAL_STORAGE,
								Manifest.permission.ACCESS_FINE_LOCATION,
								Manifest.permission.INTERNET,
								Manifest.permission.ACCESS_NETWORK_STATE,
								Manifest.permission.RECORD_AUDIO,
								Manifest.permission.LOCATION_HARDWARE,
								Manifest.permission.MODIFY_AUDIO_SETTINGS,
								Manifest.permission.MODIFY_PHONE_STATE,
								Manifest.permission.SYSTEM_ALERT_WINDOW,
								Manifest.permission.PROCESS_OUTGOING_CALLS,
								Manifest.permission.CALL_PHONE,
								Manifest.permission.ACCESS_WIFI_STATE},
						1);
			}

		}



		File sdCard, directory, file = null;
		sdCard = Environment.getExternalStorageDirectory();
		// Obtenemos el direcorio donde se encuentra nuestro archivo a leer
		//dialogoObservaciones();
		elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
		String diario = String.valueOf(elMaximo);
		Log.i("consulta", "el Maximo: " + elMaximo);

		try {

			directory = new File(sdCard.getAbsolutePath() + "/Mis_archivos");
			directory.mkdirs();
			directory = new File(sdCard.getAbsolutePath()+ "/Fotos/Firmas_CentroH_"+formattedDate3);
			directory.mkdirs();
			directory = new File(sdCard.getAbsolutePath()+ "/Fotos/Firmas_CentroH_"+formattedDate3+"N");
			directory.mkdirs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (diario.length() == 1) {
			diario = "00" + diario;
		} else if (diario.length() == 2) {
			diario = "0" + diario;
		} else {
			diario = diario;
		}

		sacaImei();
		nombreImagen = getTodaysDate() + "_" +"_" + cachaUsuario() + "_"
				+ diario + "_Firma";
		uniqueId = getTodaysDate() + "_" + getCurrentTime() + "_" + Math.random();
		current = nombreImagen + ".jpg";

		if (!verificaConexion(this)) {
			directory = new File(Environment.getExternalStorageDirectory() + "/Fotos/Firmas_CentroH_"+formattedDate3+"N" + "/");
		} else {
			directory = new File(Environment.getExternalStorageDirectory() + "/Fotos/Firmas_CentroH_"+formattedDate3+"N" + "/");
			mypath = new File(directory, current);
		}

		mypath = new File(directory, current);
		mContent = (LinearLayout) findViewById(R.id.linearLayoutFirma);
		mSignature = new signature(this, null);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mClear = (Button) findViewById(R.id.clear);
		mGetSign = (Button) findViewById(R.id.getsign);
		mGetSign.setEnabled(false);
		mView = mContent;

		mClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.v("log_tag", "Panel Cleared");
				mSignature.clear();
				mGetSign.setEnabled(false);
			}
		});

		mGetSign.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.v("log_tag", "Panel Saved");
				boolean error = captureSignature();
				if (!error) {

					mView.setDrawingCacheEnabled(true);
					mSignature.save(mView);

					GPSTracker gpsTracker = new GPSTracker(CapturaFirma.this);

					UUID uuid = UUID.randomUUID();

					Firma firma = new Firma();

					firma.setId(1);
					firma.setUuid(uuid);
					firma.setLatitude(gpsTracker.getLatitude());
					firma.setLongitude(gpsTracker.getLongitude());
					firma.setFirmaPath(String.valueOf(mypath));

					entrevista.setFirma(firma);
					entrevista.setObservaciones(Observaciones);
					Intent intent = new Intent(getApplicationContext(), FormatoFirmaActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intent.putExtra(USUARIO, usuario);
					intent.putExtra(ENTREVISTA, entrevista);
					startActivity(intent);

					finish();

				}
			}
		});



	}

	@Override
	protected void onDestroy() {
		Log.w("GetSignature", "onDestory");
		super.onDestroy();
	}

	private boolean captureSignature() {

		boolean error = false;
		String errorMessage = "";

		if (error) {
			Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 105, 50);
			toast.show();
		}

		return error;
	}


	private String getTodaysDate() {

		final Calendar c = Calendar.getInstance();
		int todaysDate = (c.get(Calendar.YEAR) * 10000) + ((c.get(Calendar.MONTH) + 1) * 100)
				+ (c.get(Calendar.DAY_OF_MONTH));
		Log.w("DATE:", String.valueOf(todaysDate));
		return (String.valueOf(todaysDate));

	}

	private String getCurrentTime() {

		final Calendar c = Calendar.getInstance();
		int currentTime = (c.get(Calendar.HOUR_OF_DAY) * 10000) + (c.get(Calendar.MINUTE) * 100)
				+ (c.get(Calendar.SECOND));
		Log.w("TIME:", String.valueOf(currentTime));
		return (String.valueOf(currentTime));

	}

	private boolean prepareDirectory() {
		try {
			if (makedirs()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Could not initiate File System.. Is Sdcard mounted properly?", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
	}

	private boolean makedirs() {
		File tempdir = new File(tempDir);
		if (!tempdir.exists())
			tempdir.mkdirs();

		if (tempdir.isDirectory()) {
			File[] files = tempdir.listFiles();
			for (File file : files) {
				if (!file.delete()) {
					Log.i("guarda", "Failed to delete " + file);
				}
			}
		}
		return (tempdir.isDirectory());
	}

	public class signature extends View {
		private static final float STROKE_WIDTH = 5f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		private Paint paint = new Paint();
		private Path path = new Path();

		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();

		public signature(Context context, AttributeSet attrs) {
			super(context, attrs);
			paint.setAntiAlias(true);
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
		}

		public void save(View v) {
			Log.v("log_tag", "Width: " + v.getWidth());
			Log.v("log_tag", "Height: " + v.getHeight());
			if (mBitmap == null) {
				mBitmap = Bitmap.createBitmap(mContent.getWidth(), mContent.getHeight(), Bitmap.Config.RGB_565);
				;
			}
			Canvas canvas = new Canvas(mBitmap);
			try {
				FileOutputStream mFileOutStream = new FileOutputStream(mypath);

				v.draw(canvas);
				mBitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
				mFileOutStream.flush();
				mFileOutStream.close();
				String url = Images.Media.insertImage(getContentResolver(), mBitmap, "title", null);
				Log.v("log_tag", "url: " + url);

			} catch (Exception e) {
				Log.v("log_tag", e.toString());
			}
		}

		public void clear() {
			path.reset();
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(path, paint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			mGetSign.setEnabled(true);

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(eventX, eventY);
				lastTouchX = eventX;
				lastTouchY = eventY;
				return true;

			case MotionEvent.ACTION_MOVE:

			case MotionEvent.ACTION_UP:

				resetDirtyRect(eventX, eventY);
				int historySize = event.getHistorySize();
				for (int i = 0; i < historySize; i++) {
					float historicalX = event.getHistoricalX(i);
					float historicalY = event.getHistoricalY(i);
					expandDirtyRect(historicalX, historicalY);
					path.lineTo(historicalX, historicalY);
				}
				path.lineTo(eventX, eventY);
				break;

			default:
				debug("Ignored touch event: " + event.toString());
				return false;
			}

			invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH), (int) (dirtyRect.top - HALF_STROKE_WIDTH),
					(int) (dirtyRect.right + HALF_STROKE_WIDTH), (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

			lastTouchX = eventX;
			lastTouchY = eventY;

			return true;
		}

		private void debug(String string) {
		}

		private void expandDirtyRect(float historicalX, float historicalY) {
			if (historicalX < dirtyRect.left) {
				dirtyRect.left = historicalX;
			} else if (historicalX > dirtyRect.right) {
				dirtyRect.right = historicalX;
			}

			if (historicalY < dirtyRect.top) {
				dirtyRect.top = historicalY;
			} else if (historicalY > dirtyRect.bottom) {
				dirtyRect.bottom = historicalY;
			}
		}

		private void resetDirtyRect(float eventX, float eventY) {
			dirtyRect.left = Math.min(lastTouchX, eventX);
			dirtyRect.right = Math.max(lastTouchX, eventX);
			dirtyRect.top = Math.min(lastTouchY, eventY);
			dirtyRect.bottom = Math.max(lastTouchY, eventY);
		}
	}

}