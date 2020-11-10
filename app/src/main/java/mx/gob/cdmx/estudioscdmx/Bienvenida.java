package mx.gob.cdmx.estudioscdmx;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cz.msebera.android.httpclient.Header;
import mx.gob.cdmx.estudioscdmx.db.DaoManager;
import mx.gob.cdmx.estudioscdmx.model.Aplicacion;
import mx.gob.cdmx.estudioscdmx.model.candidatos_cdmx;
import mx.gob.cdmx.estudioscdmx.model.encuestas;
import mx.gob.cdmx.estudioscdmx.service.AndroidLocationServices;
import mx.gob.cdmx.estudioscdmx.service.GPSWidgetProvider;

import static mx.gob.cdmx.estudioscdmx.Nombre.ALCALDIA;
import static mx.gob.cdmx.estudioscdmx.Nombre.customURL;
import static mx.gob.cdmx.estudioscdmx.Nombre.customURLcatalogos;
import static mx.gob.cdmx.estudioscdmx.model.Nombre.APLICACION;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;

public class Bienvenida extends AppCompatActivity {

    private static final String TAG = Bienvenida.class.getName();
    UsuariosSQLiteHelper usdbh;
    private SQLiteDatabase db;
    UsuariosSQLiteHelper2 usdbh2;
    private SQLiteDatabase db2;
    UsuariosSQLiteHelper3 usdbh3;
    private SQLiteDatabase db3;

    SQLiteHelper3 sqLiteHelper3;
    SQLiteDatabase sqLiteDatabase;


    double latitude;
    double longitude;
    private DaoManager daoManager;


    public String maximo = "";
    int elMaximo;
    Boolean bandera = false;

    private View mProgressView;

    Nombre nom = new Nombre();
    String nombreEncuesta = nom.nombreEncuesta();
    String upLoadServerUriBase = "http://35.226.91.72/encuestas/recibeBases" + nombreEncuesta + ".php?encuesta=" + nombreEncuesta + "";
    String upLoadServerUriAudio = "http://35.226.91.72/encuestas/recibeAudios" + nombreEncuesta + ".php?encuesta=" + nombreEncuesta + "";

    int serverResponseCode = 0;

    String token;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df1 = new SimpleDateFormat("yyyMMdd");
    String formattedDate1 = df1.format(c.getTime());

    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
    String formattedDate2 = df2.format(c.getTime());

    SimpleDateFormat df3 = new SimpleDateFormat("yyy-MM-dd");
    String formattedDate3 = df3.format(c.getTime());

    SimpleDateFormat df4 = new SimpleDateFormat("yyy-MM-dd");
    String formattedDateFecha = df4.format(c.getTime());

    public String sacaImei() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
            System.out.println("Mi N�mero: " + szImei);
            return szImei;
        }
        String szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
        System.out.println("Mi N�mero: " + szImei);
        return szImei;
    }


    protected void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenida);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(Bienvenida.this,
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

        } else {

            usdbh3 = new UsuariosSQLiteHelper3(this);
            db3 = usdbh3.getReadableDatabase();

            sqLiteHelper3 = new SQLiteHelper3(this);
            sqLiteDatabase = sqLiteHelper3.getReadableDatabase();
        }

        int ids[] = AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this, GPSWidgetProvider.class));

        if (ids.length == 0) {
            requestPinAppWidget();
        } else {
        }

        mProgressView = findViewById(R.id.login_progressMain);

        Log.i(TAG,"cqs -->> UploadServerUriAudio: "+upLoadServerUriAudio);

//        String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
//                + sacaImei() + "";
//
//        usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);
//        db = usdbh.getReadableDatabase();
//
//        DaoManager daoManager = new DaoManager(db);
//        estudiosCdmx = (encuestas) daoManager.findByNoSend(encuestas.class, "0", null) ;
//
//        if (estudiosCdmx != null){
//
//
//
//
//
//        }


//        showProgress(true);

        String SQLFprint = "CREATE TABLE fprint (" +
                "id integer primary key autoincrement," +
                "user TEXT NOT NULL," +
                "pass TEXT NOT NULL," +
                "activo INTEGER NOT NULL );";

        try {

            db3.execSQL(SQLFprint);
            Log.i("cqs --->> Crea Tabla", "Se crea la tabla: " + "fprint");
        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i("cqs --->> Crea tabla", "Error al crear la tabla fprint" + stackTrace);
        }


        sacaUsuario();
        Log.i(TAG, "cqs ------------->> N�mero de usuarios onCreate: " + sacaUsuario());

        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this, this));

        ///////////////// actualiza catalogos
        elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
        String elMaximoFecha = String.valueOf(elMaximo);

        if (elMaximoFecha.matches("1")) {
            Log.i(TAG, " =====> El numero inicial: " + elMaximoFecha);
            Log.i(TAG, " =====> El nombre de la encuestas: " + nombreEncuesta);
            Log.i(TAG, " =====> Cuantas secciones hay: " + sacaCuantosSecciones());
            int haySecciones = Integer.parseInt(sacaCuantosSecciones());
            if (haySecciones == 0) {
                catalogoCandidatosWS(nombreEncuesta);
            }
        }

        ////////// finaliza actualiza catalogos

        bandera = pregunta(AndroidLocationServices.class);
        if (bandera) {
//            Toast.makeText(this, "EL SERVICIO YA EST� ARRRIBA", Toast.LENGTH_LONG).show();
//
//            finish();
        } else {
            startService(new Intent(this, AndroidLocationServices.class));

//            finish();
        }


        if (!verificaConexion(this)) {
            dialogoErrorConexion();
        } else {

            //limpia los token de usuario
//            List<Usuario> listaUsuarios = daoManager.find(Usuario.class,null,null,null, null, null);
//            if(listaUsuarios != null && !listaUsuarios.isEmpty()){
//                for(Usuario usuario : listaUsuarios){
//                    usuario.setToken(null);
//                    daoManager.update(usuario,"idUsuario = " + usuario.getIdUsuario(),null);
//                }
//            }

            String version = BuildConfig.VERSION_NAME;
            String project = getResources().getString(R.string.app_project);
            String sdk = String.valueOf(Build.VERSION.SDK_INT);

            RequestParams params = new RequestParams();
            params.put("version", version);
            params.put("project", project);
            params.put("android", sdk);

            AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.setTimeout(20000);

            RequestHandle requestHandle = client.post(getResources().getString(R.string.url_upgrade) + "/api/global/version", params, new AsyncHttpResponseHandler() {
                String jsonHost = "";
                String jsonLatest = "";
                String jsonUpgrade = "";
                String jsonCurrent = "";
                String jsonStatus = "";

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d(TAG, "Realizo la conexi�n");
                    Log.d(TAG, "cqs conexi�n 1 -----------> " + new String(responseBody));
                    try {

                        String json = new String(responseBody);
                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "cqs -----------> Data: " + jsonObject.get("data"));

                        jsonHost = jsonObject.getJSONObject("data").getString("host");
                        jsonCurrent = jsonObject.getJSONObject("data").getString("current");
                        jsonLatest = jsonObject.getJSONObject("data").getString("latest");
                        jsonUpgrade = jsonObject.getJSONObject("data").getString("upgrade");
                        jsonStatus = jsonObject.getJSONObject("data").getString("status");

                        if (jsonLatest.toString().equals(jsonCurrent.toString())) {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    String dato = sacaUbicacion();
                                    Log.i(TAG,"cqs --->> Ubicacion: "+ dato);

                                    if (dato.equals("0")) {

                                        showAlertDialog("AVISO", "No hay datos de Ubicacion", false);

                                    } else {

//
//                                        Intent intent = new Intent(Bienvenida.this, Bienvenida.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                        Bundle bundle = new Bundle();
//                                        intent.putExtras(bundle);
//
//                                        finish();
//                                        startActivity(intent);

                                    }


                                }
                            }, 3000);
                        } else {

                            Aplicacion aplicacion = new Aplicacion();
                            aplicacion.setUpgrade(jsonUpgrade);
                            aplicacion.setHost(jsonHost);
                            aplicacion.setStatus(jsonStatus);

                            Intent intent = new Intent(Bienvenida.this, upgradeActivity.class);
                            intent.putExtra(APLICACION, aplicacion);
                            finish();
                            startActivity(intent);
                        }


                    } catch (JSONException e) {
                        Log.e(TAG, e.getMessage());
                    }


                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.e(TAG, "existe un error en la conexi�n on Failure ");
                    if (responseBody != null) {
                        Log.d(TAG, "cqs conexion on failure -----------> " + new String(responseBody));
                    }

                    String dato = sacaUbicacion();
                    Log.i(TAG,"cqs --->> Ubicacion: "+ dato);

                    if (dato.equals("0")) {

                        showAlertDialog("AVISO", "No hay datos de ubicacion", false);

                    } else {

//                        Intent intent = new Intent(Bienvenida.this, Bienvenida.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        Bundle bundle = new Bundle();
//                        intent.putExtras(bundle);
//
//                        finish();
//                        startActivity(intent);
                    }


                }
            });


//            new UpdateBases().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sacaImei());

            try {
                new uploadData.UpdateAudios().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }catch (Exception e){
                String stackTrace = Log.getStackTraceString(e);
                Log.i(TAG,"Error Manda Audios"+ stackTrace);
            }

//            Log.i(TAG,"cqs ------------>> TOKEN A PASAR: "+ObtenerToken());
//            ObtenerToken();
//            NotificacionIDTokenService notificacionIDTokenService = new NotificacionIDTokenService();
//            notificacionIDTokenService.onTokenRefresh();
        }


        Executor newExecutor = Executors.newSingleThreadExecutor();


        FragmentActivity activity = this;

        final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {

                    Log.i(TAG, "cqs ------->>  Pulse el bot�n cancelar");
                    finishAffinity();

                } else {
                    Log.i(TAG, "cqs ------->> A ocurrido un error");
                    finishAffinity();
                }

//                    if (errorCode == BiometricPrompt.ERROR_USER_CANCELED) {
//                        Log.i(TAG, "cqs ------->>  Pulse afuera del cuadro");
//                    }

            }


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Log.d(TAG, "Reconocimiento exitoso");
//                    Intent intent = new Intent(getApplicationContext(), Entrada.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                    finish();

                /*Si tiene internet y si es activo, pasa a sacar usuario, si no tiene internet, sigue a sacar usuario, si tine internet, verifica que este activo*/

                if (!verificaConexion(Bienvenida.this)) {
//                        Toast.makeText(getBaseContext(), "Sin conexi�n", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "cqs ------------->> Sin conexi�n: " + sacaUsuario());
//                    showProgress(false);
                    dialogoConexion();
                } else {

                    Log.i(TAG, "cqs ------------->> Con conexi�n: " + sacaUsuario());

                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                usuarioWS(sacaUsr().toString(), sacaPss().toString());
                            } catch (Exception e) {
                                Log.i(TAG, "cqs ------------->> Error usuarioWS va para registro: " + sacaUsuario());
                                Intent intent = new Intent(getApplicationContext(), Registro.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }

                        }
                    };
                    mainHandler.post(myRunnable);
                }


//                    if(Integer.parseInt(sacaUsuario().toString())==0){
//
//                        Log.i(TAG,"cqs ------------->> N�mero de usuarios: "+sacaUsuario());
//                        Intent intent = new Intent(getApplicationContext(), Registro.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//
//                    }else {
//
//                        Intent intent = new Intent(Bienvenida.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Nombre", sacaUsr());
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//
//                        runOnUiThread(new Runnable() {
//                            public void run() {
//                                showToast("Acceso OK");
//                            }
//                        });
//                    }


            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Log.d(TAG, "Huella no reconocida");
            }


        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Use su huella para acceder")
                .setSubtitle("toca el sensor de huellas digitales")
                //.setDescription("This is the description")
                .setNegativeButtonText("Cancelar")
                .build();


        myBiometricPrompt.authenticate(promptInfo);

//        findViewById(R.id.launchAuthentication).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myBiometricPrompt.authenticate(promptInfo);
//            }
//        });


    }

    private boolean pregunta(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    private String sacaUsuario() {
        String acceso = null;
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select count(*) from fprint";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                acceso = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db3.close();

        return acceso;
    }

    private String sacaUsr() {
        String usr = null;
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select user from fprint limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                usr = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db3.close();
        return usr;
    }

    private String sacaPss() {
        String pass = null;
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select pass from fprint limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pass = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db3.close();
        return pass;
    }

    private Integer sacaActivo() {
        Integer activo = null;
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select activo from fprint limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                activo = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db3.close();
        return activo;
    }

    /////// METODO PARA VERIFICAR LA CONEXI�N A INTERNET
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        // No s�lo wifi, tambi�n GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle deber�a no ser tan �apa
        for (int i = 0; i < 2; i++) {
            // �Tenemos conexi�n? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    /*Obtener el ID Firebase*/

//    public String ObtenerToken(){
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @RequiresApi(api = Build.VERSION_CODES.O)
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        token= task.getResult().getToken();
//
//                        if(token==null){
//                            token="sin token";
//                        }else {
//                            token = task.getResult().getToken();
//                            Log.i(TAG,"cqs -------------->> El token Bienvenida: "+ token);
//                            tokenWS(sacaUsr().toString(),sacaPss().toString(),token);
//
//                            // Log and toast
//                            String msg = getString(R.string.msg_token_fmt, token);
//                            Log.d(TAG, msg);
//                            Toast.makeText(Bienvenida.this, msg, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//        return token;
//
//    }

    /*Saca usuario WebService*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void usuarioWS(final String user, final String password) {

//        showProgress(true);

        RequestParams params = new RequestParams();
        params.put("api", "loginSemanal");
        params.put("usuario", user);
        params.put("pass", password);
        params.put("imei", sacaImei());

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        //client.addHeader("Authorization", "Bearer " + usuario.getToken());
        client.setTimeout(60000);

        RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String nombreStr = "";
                Log.d(TAG, "cqs ----------->> Respuesta OK ");
                Log.d(TAG, "cqs ----------->> ResponseBody" + new String(responseBody));
                try {


                    String json = new String(responseBody);

                    if (json != null && !json.isEmpty()) {

                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "cqs ----------->> Data: " + jsonObject.get("data"));

                        String login = jsonObject.getJSONObject("response").get("code").toString();
                        Log.d(TAG, "cqs ----------->> login: " + login);

                        String usuario = jsonObject.getJSONObject("data").getJSONObject("user").get("usuario").toString();
                        Log.d(TAG, "cqs ----------->> usuario: " + usuario);
                        String password = jsonObject.getJSONObject("data").getJSONObject("user").get("password").toString();
                        Log.d(TAG, "cqs ----------->> password: " + password);

                        if (Integer.valueOf(login) == 1) {
                            Log.d(TAG, "cqs ----------->> login: " + "Entra");
                            if (Integer.parseInt(sacaUsuario().toString()) == 0) {

                                Log.i(TAG, "cqs ------------->> N�mero de usuarios: " + sacaUsuario());
                                Intent intent = new Intent(getApplicationContext(), Registro.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            } else if (Integer.parseInt(sacaUsuario().toString()) != 0) {

                                Integer activo = sacaActivo();

                                if (activo == 0) {
                                    activ();

                                    /*
                                    /*si el usuario el igual con 1 de pruebas va directo a MainActivity
                                    /*si no, verifica que est� en el poligono de la alcaldia
                                    */

                                    if (user.equals("1")) {
                                        pasaEncuesta();
                                    } else {
                                        if (ALCALDIA.matches("Todas")) {
                                            pasaEncuesta();
                                        } else {
                                            seccionWS(user);
                                        }
                                    }
                                    /*ESTAS PARTE PARA PROBAR TODAS LAS ALCALDIAS*/
//                                    if (ALCALDIA.matches("Todas")) {
//                                        pasaEncuesta();
//                                    } else {
//                                        seccionWS(user);
//                                    }
                                } else {
                                    /*
                                    /*si el usuario el igual con 1 de pruebas va directo a MainActivity
                                    /*si no, verifica que est� en el poligono de la alcaldia
                                    */
                                    if (user.equals("1")) {
                                        pasaEncuesta();
                                    } else {
                                        if (ALCALDIA.matches("Todas")) {
                                            pasaEncuesta();
                                        } else {
                                            seccionWS(user);
                                        }
                                    }

                                    /*ESTAS PARTE PARA PROBAR TODAS LAS ALCALDIAS*/
//                                    if (ALCALDIA.matches("Todas")) {
//                                        pasaEncuesta();
//                                    } else {
//                                        seccionWS(user);
//                                    }
                                }


                            }
                        } else {
                            dialogoBaja();
                            Log.d(TAG, "cqs ----------->> Entrada: " + "No entra");
                        }
                    }

                } catch (Exception e) {
//                    showProgress(false);
                    String stackTrace = Log.getStackTraceString(e);
                    Log.i("cqs ---------->> FALLA", "FALLA: " + stackTrace);
                    dialogoBaja();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                showProgress(false);
                try {
                    Log.e(TAG, "cqs ----------------->> existe un error en la conexi�n on failure 1-----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs ----------->> " + new String(responseBody));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (statusCode != 200) {
                    Log.e(TAG, "Existe un error en la conexi�n on failure != 200-----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "pimc -----------> " + new String(responseBody));

                }

                Toast.makeText(Bienvenida.this, "Error de conexi�n, intente de nuevo", Toast.LENGTH_SHORT).show();
//                showProgress(true);
                dialogoErrorConexion();
//                finishAffinity();

            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void seccionWS(final String user) {

//        showProgress(true);

        GPSTracker gps = new GPSTracker(this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();

        if (latitude == 0.0) {
            if (sacaLatitud() != null) {
                latitude = Double.valueOf(sacaLatitud());
            } else {
                latitude = 0.0;
            }
        }

        if (longitude == 0.0) {
            if (sacaLongitud() != null) {
                longitude = Double.valueOf(sacaLongitud());
            } else {
                longitude = 0.0;
            }
        }

        String strLatitud = String.valueOf(latitude);
        String strLongitud = String.valueOf(longitude);
        String laAlcaldia = ALCALDIA;

        if (ALCALDIA == "�lvaro Obreg�n") {
            laAlcaldia = "10";
        } else if (ALCALDIA == "Azcapotzalco") {
            laAlcaldia = "2";
        } else if (ALCALDIA == "Benito Ju�rez") {
            laAlcaldia = "14";
        } else if (ALCALDIA == "Coyoac�n") {
            laAlcaldia = "3";
        } else if (ALCALDIA == "Cuajimalpa de Morelos") {
            laAlcaldia = "4";
        } else if (ALCALDIA == "Cuauht�moc") {
            laAlcaldia = "15";
        } else if (ALCALDIA == "Gustavo A. Madero") {
            laAlcaldia = "5";
        } else if (ALCALDIA == "Iztacalco") {
            laAlcaldia = "6";
        } else if (ALCALDIA == "Iztapalapa") {
            laAlcaldia = "7";
        } else if (ALCALDIA == "La Magdalena Contreras") {
            laAlcaldia = "8";
        } else if (ALCALDIA == "Miguel Hidalgo") {
            laAlcaldia = "16";
        } else if (ALCALDIA == "Milpa Alta") {
            laAlcaldia = "9";
        } else if (ALCALDIA == "Tl�huac") {
            laAlcaldia = "11";
        } else if (ALCALDIA == "Tlalpan") {
            laAlcaldia = "12";
        } else if (ALCALDIA == "Venustiano Carranza") {
            laAlcaldia = "17";
        } else if (ALCALDIA == "Xochimilco") {
            laAlcaldia = "13";
        }

        RequestParams params = new RequestParams();
        params.put("api", "dentroSeccion");
        params.put("usuario", user);
        params.put("alcaldia", laAlcaldia);
        params.put("latitud", strLatitud);
        params.put("longitud", strLongitud);
        params.put("imei", sacaImei());

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        //client.addHeader("Authorization", "Bearer " + usuario.getToken());
        client.setTimeout(60000);

        RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String nombreStr = "";
                Log.d(TAG, "cqs ----------->> Respuesta OK ");
                Log.d(TAG, "cqs ----------->> ALCALDIA: " + ALCALDIA);
                Log.d(TAG, "cqs ----------->> ResponseBody" + new String(responseBody));
                try {


                    String json = new String(responseBody);

                    if (json != null && !json.isEmpty()) {

                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "cqs ----------->> Data: " + jsonObject.get("data"));

                        String code = jsonObject.getJSONObject("response").get("code").toString();
                        Log.d(TAG, "cqs ----------->> code alcaldia: " + code);

                        String esta = jsonObject.getJSONObject("data").get("esta").toString();
                        Log.d(TAG, "cqs ----------->> esta: " + esta);
//                        String password = jsonObject.getJSONObject("data").getJSONObject("user").get("password").toString();
//                        Log.d(TAG, "cqs ----------->> password: " + password);

                        if (Integer.valueOf(code) == 1) {
                            Log.d(TAG, "cqs ----------->> Entrada: " + "Entra a seccion");

                            pasaEncuesta();

                        } else {
                            dialogoFueraLugar();
                            Log.d(TAG, "cqs ----------->> Entrada: " + "No entra a seccion");
                        }
                    }

                } catch (Exception e) {
//                    showProgress(false);
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(Bienvenida.this, "Usuario y/o Contase�a no v�lidos", Toast.LENGTH_SHORT).show();
                    dialogoFueraLugar();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                showProgress(false);
                try {
                    Log.e(TAG, "cqs ----------------->> existe un error en la conexi�n secciones -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs ----------->> " + new String(responseBody));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (statusCode != 200) {
                    Log.e(TAG, "Existe un error en la conexi�n secciones != 200 -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs ----------->> " + new String(responseBody));

                }

                Toast.makeText(Bienvenida.this, "Error de conexi�n, intente de nuevo", Toast.LENGTH_SHORT).show();
                finishAffinity();

            }
        });
    }

    /*Saca usuario WebService*/

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void tokenWS(final String user, final String password, final String token_firebase) {

//        showProgress(true);

        RequestParams params = new RequestParams();
        params.put("api", "token_firebase");
        params.put("usuario", user);
        params.put("pass", password);
        params.put("imei", sacaImei());
        params.put("token_firebase", token_firebase);

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        //client.addHeader("Authorization", "Bearer " + usuario.getToken());
        client.setTimeout(60000);

        RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String nombreStr = "";
                Log.d(TAG, "cqs ----------->> Respuesta OK ");
                Log.d(TAG, "cqs ----------->> ResponseBody" + new String(responseBody));
                try {


                    String json = new String(responseBody);

                    if (json != null && !json.isEmpty()) {

                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "cqs ----------->> Data: " + jsonObject.get("data"));

                        String login = jsonObject.getJSONObject("response").get("code").toString();
                        Log.d(TAG, "cqs ----------->> login: " + login);

                        String usuario = jsonObject.getJSONObject("data").getJSONObject("user").get("usuario").toString();
                        Log.d(TAG, "cqs ----------->> usuario: " + usuario);
                        String password = jsonObject.getJSONObject("data").getJSONObject("user").get("password").toString();
                        Log.d(TAG, "cqs ----------->> password: " + password);
                        String token_fire = jsonObject.getJSONObject("data").getJSONObject("user").get("token_firebase").toString();
                        Log.d(TAG, "cqs ----------->> Token Firebase: " + token_fire);

                        if (Integer.valueOf(login) == 1) {
                            Log.d(TAG, "cqs ----------->> login: " + "Entra");
                            if (Integer.parseInt(sacaUsuario().toString()) == 0) {


                            } else if (Integer.parseInt(sacaUsuario().toString()) != 0) {

                            }
                        } else {

                            Log.d(TAG, "cqs ----------->> Entrada: " + "No entra");
                        }
                    }

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                showProgress(false);
                try {
                    Log.e(TAG, "cqs ----------------->> existe un error en la conexi�n on failure tokenWS-----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs ----------->> " + new String(responseBody));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (statusCode != 200) {
                    Log.e(TAG, "Existe un error en la conexi�n on failure tokenWS != 200  -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs -----------> " + new String(responseBody));

                }

                Toast.makeText(Bienvenida.this, "Error de conexi�n, intente de nuevo", Toast.LENGTH_SHORT).show();
                finishAffinity();

            }
        });
    }

    public void dialogoBaja() {
        // timer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Bienvenida.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage("Ponte en contacto con tu supervisor")
                        .setTitle("El usuario no esta Activo").setCancelable(false)
                        .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Log.i("cqs --->> Actualiza", "Entra P: " + "dialogo");
                                noActiv();
                                finishAffinity();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    public void dialogoFueraLugar() {
        // timer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Bienvenida.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage("Ponte en contacto con tu supervisor")
                        .setTitle("No te encuentras en la alcadia que corresponde").setCancelable(false)
                        .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                finishAffinity();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }


    public void dialogoConexion() {
        // timer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Bienvenida.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage("Sin conexi�n a internet")
                        .setTitle("Importante").setCancelable(false)
                        .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (Integer.parseInt(sacaUsuario().toString()) == 0) {

                                    Log.i(TAG, "cqs ------------->> N�mero de usuarios: " + sacaUsuario());
                                    Intent intent = new Intent(getApplicationContext(), Registro.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                } else {

                                    pasaEncuesta();
                                }

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    public void dialogoErrorConexion() {
        // timer.cancel();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Bienvenida.this.runOnUiThread(new Runnable() {
            public void run() {
                builder.setMessage("Existe un error en la conexi�n \n" +
                        "se trabajar� localmente")
                        .setTitle("Aviso").setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (Integer.parseInt(sacaUsuario().toString()) == 0) {

                                    Log.i(TAG, "cqs ------------->> N�mero de usuarios: " + sacaUsuario());
                                    Intent intent = new Intent(getApplicationContext(), Registro.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                } else {

                                    pasaEncuesta();
                                }

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    public void pasaEncuesta() {
        Intent intent = new Intent(Bienvenida.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putString("Nombre", sacaUsr());
        intent.putExtras(bundle);
        startActivity(intent);

        runOnUiThread(new Runnable() {
            public void run() {
                showToast("Acceso OK");
            }
        });
    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
//    private void showProgress(final boolean show) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
//
//            mUsuario.setVisibility(show ? View.GONE : View.VISIBLE);
//            mUsuario.animate().setDuration(shortAnimTime).alpha(
//                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mUsuario.setVisibility(show ? View.GONE : View.VISIBLE);
//                }
//            });
//
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mProgressView.animate().setDuration(shortAnimTime).alpha(
//                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//                }
//            });
//        } else {
//            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//            mUsuario.setVisibility(show ? View.GONE : View.VISIBLE);
//        }
//    }

    public void activ() {
        String SQLFprint = "update fprint set activo='1' where activo ='0' ";

        try {

            usdbh3 = new UsuariosSQLiteHelper3(Bienvenida.this);
            db3 = usdbh3.getReadableDatabase();

            db3.execSQL(SQLFprint);
            Log.i("cqs --->> Actualiza", "Se Actualiza el usuario: " + "fprint");
        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i("cqs --->>", "Error al actualizar el usuario" + stackTrace);
        }
    }

    public void noActiv() {
        String SQLFprint = "update fprint set activo='0' where activo ='1' ";

        try {

            usdbh3 = new UsuariosSQLiteHelper3(Bienvenida.this);
            db3 = usdbh3.getReadableDatabase();

            db3.execSQL(SQLFprint);
            Log.i("cqs --->> Actualiza", "Se Actualiza el usuario: " + "fprint");
        } catch (Exception e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i("cqs --->>", "Error al actualizar el usuario" + stackTrace);
        }
    }


    //Enviar Base
    public int uploadBase(String sourceFileUri) {

        File sdCard;
        sdCard = Environment.getExternalStorageDirectory();
        final String pathBase = sdCard.getAbsolutePath() + "/Mis_archivos";

        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

//				             dialog.dismiss();
            Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + pathBase + "" + "/" + "20161124_002_359083065132816_1.jpg");
            runOnUiThread(new Runnable() {
                public void run() {

                }
            });

            return 0;

        } else {
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUriBase);
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\""
                        + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("TAG", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);

                if (serverResponseCode == 200) {

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    + " http://www.androidexample.com/media/uploads/"
                                    + "20161124_002_359083065132816_1.jpg";

//				                              Toast.makeText(Entrada.this, "File Upload Complete."+msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

//				                dialog.dismiss();
                ex.printStackTrace();


                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server " + "error: " + ex.getMessage());

//				                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

//				                dialog.dismiss();
                e.printStackTrace();

                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server Exception " + "Exception : " + e.getMessage());

//				                Log.e("Upload file to server Exception", "Exception : "
//				                                                 + e.getMessage(), e);
            }
            return serverResponseCode;

        } // End else block
    }

    class UpdateBases extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            File sdCard;
            sdCard = Environment.getExternalStorageDirectory();
            final String pathBase = sdCard.getAbsolutePath() + "/Mis_archivos";
            String sDirectorio = pathBase;
            final File f = new File(sDirectorio);
            Log.i(TAG, "lista" + pathBase);
            final String customURL = "http://35.226.91.72/cgi-bin/bases/";
            Log.i(TAG, " =====> lista 1: " + pathBase);
            File F = new File(pathBase);
            try {
                if (F.exists()) {
                    File[] ficheros = F.listFiles();
                    for (int i = 0; i < ficheros.length; i++) {
                        //Simulamos cierto retraso
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        publishProgress(i / (float) (ficheros.length)); //Actualizamos los valores
                    }
                    String[] s = new String[ficheros.length];
                    String[] t = new String[ficheros.length];
                    for (int x = 0; x < ficheros.length; x++) {
                        Log.i(TAG, " =====> lista: " + ficheros[x].getName());
                        s[x] = pathBase + "/" + nombreEncuesta + "_" + sacaImei();
                        Log.i(TAG, " =====> Nombre del Archivo: " + s[x]);
                        uploadBase(s[x]);
                    }
                } else {
                    Log.i(TAG, " =====> lista 2: " + "No existe el directorio");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.i(TAG, " =====> error zip: " + "_" + e.getMessage());
            }
            return null;
        }

        //tomo
        protected void onPostExecute(String date2) {
            super.onPostExecute(date2);
            Toast.makeText(getApplicationContext(), "Archivo Enviado", Toast.LENGTH_LONG).show();
        }
    }


    //Enviar audios
    class UpdateAudios extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            super.onPreExecute();

//					dialog = new ProgressDialog(CalendarViewFotos.this);
//			        dialog.setMessage("Enviando Fotograf�as...");
//			        dialog.setTitle("Progreso");
//			        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//			        dialog.setCancelable(false);
//			        dialog.setProgress(0);
//			        dialog.setMax(100);
//			        dialog.show(); //Mostramos el di�logo antes de comenzar
        }


        @Override
        protected String doInBackground(String... params) {


            File sdCard;
            sdCard = Environment.getExternalStorageDirectory();
//						final String pathAudios = sdCard.getAbsolutePath() + "/" + nombreEncuesta+"-Audio"+date2;
            final String pathAudios = sdCard.getAbsolutePath() + "/" + nombreEncuesta +"-Audio" + formattedDate3 + "/";

            String sDirectorio = pathAudios;
            final File f = new File(sDirectorio);
            Log.i(TAG,"lista"+pathAudios);

//						final String customURL = "https://opinion.cdmx.gob.mx/cgi-bin/fotos/programas_sociales/";
            final String customURL = "http://35.226.91.72/audios/"+nombreEncuesta+ "/";

            Log.i(TAG, " =====> URL audios: " + customURL);
            Log.i(TAG, " =====> lista audios 1: " + pathAudios);

            File F = new File(pathAudios);

            try {

                if (F.exists()) {

                    File[] ficheros = F.listFiles();

                    for (int i = 0; i <ficheros.length; i++) {
                        //Simulamos cierto retraso
                        try {Thread.sleep(500); }
                        catch (InterruptedException e) {}

                        publishProgress(i/(float)(ficheros.length)); //Actualizamos los valores
                    }



                    String[] s = new String[ficheros.length];
                    String[] t = new String[ficheros.length];
                    for (int x = 0; x < ficheros.length; x++) {
                        Log.i(TAG, " =====> lista audios: " + ficheros[x].getName());
                        s[x] = pathAudios + "/" + ficheros[x].getName();
                        t[x] = ficheros[x].getName();

//								 uploadFotos(s[x],date2);


                        URL u = new URL (customURL+t[x]);
                        Log.i(TAG, " =====> Archivo Audios custom: "+customURL+t[x] );
                        HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection ();
                        huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD");
                        huc.connect () ;
                        huc.getResponseCode();
                        Log.i(TAG, " =====> Archivo:  lista De Audios ==>" + huc.getResponseCode());
                        if(huc.getResponseCode()==200){

                            //moveFile(pathFotosN, t[x], pathFotosF);
                            Log.i(TAG, " =====> Archivo:  En el servidor custom no hace nada==>" + t[x] );

                        }else if(huc.getResponseCode()==404){

                            uploadAudios(s[x]);
                            Log.i(TAG, " =====> Archivo:  Enviado al servidor custom==>" + t[x] );


                        }

                    }
                    // first parameter is d files second parameter is zip file name

                } else {
                    Log.i(TAG, " =====> lista 2: " + "No existe el directorio");
                }
                // first parameter is d files second parameter is zip file name

            } catch (Exception e) {
                String stackTrace = Log.getStackTraceString(e);
                Log.i("Manda Audios","Error Manda Audios"+ stackTrace);
            }


            return null;
        }


//				protected void onProgressUpdate (Float... valores) {
//			          int p = Math.round(100*valores[0]);
//			          dialog.setProgress(p);
//			      }


        //tomo
        protected void onPostExecute(String date2) {
            super.onPostExecute(date2);
//					dialog.dismiss();

            //	Toast.makeText(CalendarViewFotos.this, "Env�o de Fotografias completo ",Toast.LENGTH_LONG).show();

//					correo(date2, prefix);
//					correo(date2, sacaChip());

        }

    }

    public int uploadAudios(String sourceFileUri) {

        File sdCard;
        sdCard = Environment.getExternalStorageDirectory();
        //final String pathFotos = sdCard.getAbsolutePath() + "/"+ nombreEncuesta+"-Audio"+fech;
        final String pathAudios = sdCard.getAbsolutePath() + nombreEncuesta +"-Audio" + formattedDate3 + "/";

        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

//			     dialog.dismiss();
            Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + pathAudios + "" + "/" + "20161124_002_359083065132816_1.jpg");
            runOnUiThread(new Runnable() {
                public void run() {

                }
            });

            return 0;

        }
        else
        {
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUriAudio);
                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\""
                        + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "+ serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    +" http://www.androidexample.com/media/uploads/"
                                    +"20161124_002_359083065132816_1.jpg";

//			                      Toast.makeText(Entrada.this, "File Upload Complete."+msg,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

//			        dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//			                messageText.setText("MalformedURLException Exception : check script url.");
//			                Toast.makeText(CalendarViewFotos.this, "MalformedURLException",
//			                                                    Toast.LENGTH_SHORT).show();
                    }
                });

                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server "+ "error: " + ex.getMessage());

//			        Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

//			        dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
//			                messageText.setText("Error de Internet");
//			                Toast.makeText(CalendarViewFotos.this, "Error de Internet",
//			                        Toast.LENGTH_SHORT).show();
                    }
                });
                Log.i(TAG, " =====> archivo:  El Archivo no existe... :" + "Upload file to server Exception "+ "Exception : "+ e.getMessage());

//			        Log.e("Upload file to server Exception", "Exception : "
//			                                         + e.getMessage(), e);
            }
            return serverResponseCode;

        } // End else block
    }



    private String sacaLatitud() {
        Set<String> set = new HashSet<String>();
        String acceso = null;
        final String F = "File dbfile";
// Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select latitud from ubicacion order by id desc limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                acceso = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
 db3.close();

        return acceso;
    }

    private String sacaUbicacion() {
        String maximo=null;
        Set<String> set = new HashSet<String>();
        final String F = "File dbfile";
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "SELECT count(*) FROM ubicacion where " +
                "datetime(substr(fecha,0,5)||'-'||substr(fecha,5,2) ||'-'||substr(fecha,7,2)||' '|| hora)  >= datetime('now','localtime','-1 hour')  order by hora";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int val = cursor.getInt(0);
                maximo = String.valueOf(val);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db3.close();
        return maximo;
    }



    private String sacaLongitud() {
        Set<String> set = new HashSet<String>();
        String acceso = null;
        final String F = "File dbfile";
// Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh3 = new UsuariosSQLiteHelper3(this);
        db3 = usdbh3.getReadableDatabase();
        String selectQuery = "select longitud from ubicacion order by id desc limit 1";
        Cursor cursor = db3.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                acceso = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
 db3.close();

        return acceso;
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    ////////////// �para actulizal los catalogo por fecha y si est�n vacios

    private String sacaMaximo() {
        Set<String> set = new HashSet<String>();
        final String F = "File dbfile";
        // Abrimos la base de datos 'DBUsuarios' en modo escritura
        String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_" + sacaImei() + "";
        usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);
        db = usdbh.getReadableDatabase();
        String selectQuery = "SELECT count(*) FROM encuestas where fecha='" + formattedDate3 + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                maximo = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return maximo;
    }

    private String sacaCuantosSecciones() {
        String cuantos = null;
        // Abrimos la base de datos en modo lectura
        usdbh2 = new UsuariosSQLiteHelper2(this);
        db2 = usdbh2.getReadableDatabase();
        String selectQuery = "SELECT count(*) FROM candidatos_cdmx";
        Cursor cursor = db2.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                cuantos = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db2.close();
        return cuantos;
    }


    private void catalogoCandidatosWS(String laEncuesta) {

        RequestParams params = new RequestParams();
        params.put("api", "candidatoscdmx");
        params.put("encuestas", laEncuesta);
        params.put("tabla", "candidatos_cdmx");

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        //client.addHeader("Authorization", "Bearer " + usuario.getToken());
        client.setTimeout(60000);

        RequestHandle requestHandle = client.post(customURLcatalogos, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                showProgress(false);
                Log.d(TAG, "cqs -----------> Respuesta catalogo Candidatos OK ");
                Log.d(TAG, "cqs -----------> " + new String(responseBody));

                try {

                    String json = new String(responseBody);

                    if (json != null && !json.isEmpty()) {

                        Gson gson = new Gson();
                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "cqs ----------->> Data: " + jsonObject.get("data"));

                        String login = jsonObject.getJSONObject("response").get("code").toString();
                        Log.d(TAG, "cqs ----------->> Code: " + login);
                        if (Integer.valueOf(login) == 1) {

                            Log.d(TAG, "cqs ----------->> Code estoy dentro Candidatos:  " + login);
                            //obtiene usuarios
                            String jsonCandidatos = jsonObject.getJSONObject("data").getJSONArray("candidatos").toString();
                            Type collectionType = new TypeToken<List<candidatos_cdmx>>() {
                            }.getType();
                            List<candidatos_cdmx> listacandidatos = gson.fromJson(jsonCandidatos, collectionType);

                            Log.d(TAG, "cqs ----------->> listaCandidatos:  " + listacandidatos);

                            usdbh2 = new UsuariosSQLiteHelper2(Bienvenida.this);
                            db2 = usdbh2.getReadableDatabase();
                            daoManager = new DaoManager(db2);
                            if (listacandidatos != null && !listacandidatos.isEmpty()) {
                                daoManager.delete(candidatos_cdmx.class);
                                for (candidatos_cdmx candidatos_cdmx : listacandidatos) {
                                    daoManager.insert(candidatos_cdmx);
                                }
                            }

                        } else {
                            Toast.makeText(Bienvenida.this, "Consulta incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
//                    showProgress(false);
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(Bienvenida.this, "Response Incorrecto", Toast.LENGTH_SHORT).show();
                    String stackTrace = Log.getStackTraceString(e);
                    Log.i(TAG, "cqs ----------->> Response incorrecto: " + stackTrace);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                showProgress(false);
                try {
                    Log.e(TAG, "cqs-----------------> existe un error en la conexion catalogoCandidatosWS  -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs -----------> " + new String(responseBody));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (statusCode != 200) {
                    Log.e(TAG, "Existe un error en la conexion catalogoCandidatosWS !=200 -----> " + error.getMessage());
                    if (responseBody != null)
                        Log.d(TAG, "cqs -----------> " + new String(responseBody));

                }

                Toast.makeText(Bienvenida.this, "Error de conexion al servidor\n int�ntelo de nuevo", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestPinAppWidget() {

        AppWidgetManager appWidgetManager =
                this.getSystemService(AppWidgetManager.class);
        ComponentName myProvider =
                new ComponentName(this, mx.gob.cdmx.estudioscdmx.service.GPSWidgetProvider.class);

        if (appWidgetManager.isRequestPinAppWidgetSupported()) {
            // Create the PendingIntent object only if your app needs to be notified
            // that the user allowed the widget to be pinned. Note that, if the pinning
            // operation fails, your app isn't notified.
            Intent pinnedWidgetCallbackIntent = new Intent(this, mx.gob.cdmx.estudioscdmx.service.GPSWidgetProvider.class);

            // Configure the intent so that your app's broadcast receiver gets
            // the callback successfully. This callback receives the ID of the
            // newly-pinned widget (EXTRA_APPWIDGET_ID).
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0,
                    pinnedWidgetCallbackIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            appWidgetManager.requestPinAppWidget(myProvider, null, successCallback);
        }

        finish();
    }

    public void showAlertDialog(String mensaje, String descripcion, final boolean acceder) {

        //builder.setTitle("�xito");
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(Bienvenida.this);
        //builder.setMessage(mensaje);


        LinearLayout diagLayout = new LinearLayout(this);
        diagLayout.setOrientation(LinearLayout.VERTICAL);

        TextView titulo = new TextView(this);
        titulo.setText(mensaje);
        titulo.setPadding(10, 10, 10, 10);
        titulo.setGravity(Gravity.CENTER);
        titulo.setTextSize(22);
        titulo.setTextColor(Color.parseColor("#FFFFFF"));

        TextView text = new TextView(this);
        text.setText(descripcion);
        text.setPadding(10, 60, 10, 10);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(18);
        text.setTextColor(Color.parseColor("#FFFFFF"));
        //diagLayout.addView();
        builder.setView(diagLayout);


        LayoutInflater inflater = this.getLayoutInflater();

        View titleView = inflater.inflate(R.layout.alert_personalizado, null);

        ImageView imageView = titleView.findViewById(R.id.robotImageView);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.no_internet));
        LinearLayout msgLinearLayout = titleView.findViewById(R.id.messageLinearLayout);
        msgLinearLayout.setBackground(getResources().getDrawable(R.color.robot_sin_internet));
        msgLinearLayout.addView(titulo);
        msgLinearLayout.addView(text);

        builder.setCustomTitle(titleView);


        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

//                if (acceder) {
//                    Intent intent = new Intent(Bienvenida.this, Entrada.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    Bundle bundle = new Bundle();
//                    intent.putExtras(bundle);
//
//                    finish();
//                    startActivity(intent);
//                } else {
//
//                    finish();
//
//                    //System.exit(0);
//                }


            }
        });
/*		builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});*/
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }
}
