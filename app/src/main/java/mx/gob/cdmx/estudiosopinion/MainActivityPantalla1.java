package mx.gob.cdmx.estudiosopinion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import mx.gob.cdmx.estudiosopinion.model.DatoContent;
import mx.gob.cdmx.estudiosopinion.model.Usuario;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;
import static mx.gob.cdmx.estudiosopinion.Nombre.USUARIO;
import static mx.gob.cdmx.estudiosopinion.Nombre.customURL;
import static mx.gob.cdmx.estudiosopinion.Nombre.encuesta;

public class MainActivityPantalla1 extends Activity implements AdapterView.OnItemClickListener {

    private static final String LOG_TAG = "Grabadora";
    private static final String TAG = "Pantalla1";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    TelephonyManager t_manager;
    PhoneStateListener p_listener;
    boolean llamada = false;

    private View mProgressView;
    private Usuario usuario;

    final Context context = this;

    private ArrayList<CheckBox> mChecks;
    private ArrayList<CheckBox> mSelectedChecks;

    private ArrayList<CheckBox> mChecks2;
    private ArrayList<CheckBox> mSelectedChecks2;

    public MediaRecorder recorder = new MediaRecorder();
    private String audio;
    private Handler handler;
    public String honestidad;

    public StringBuilder builder0;


    private Button btnGuardar;
    private Button btnSalir;

    private TextView textPreguntaEntrada;

    private TextView textPregunta12;
    private TextView textPregunta16a;
    private TextView textPregunta16b;
    private TextView textPregunta20;
    private TextView textPregunta23;
    private TextView textPregunta24b;
    private TextView textPregunta24_1;
    private TextView textPregunta24_1b;
    private TextView textPregunta24_2;
    private TextView textPregunta24_2b;
    private TextView textPregunta24_3;
    private TextView textPregunta24_3b;
    private TextView textPregunta25;
    private TextView textPregunta26;
    private TextView textPregunta27b;
    private TextView textPregunta27_1;
    private TextView textPregunta27_1b;
    private TextView textPregunta27_2;
    private TextView textPregunta27_2b;
    private TextView textPregunta27_3;
    private TextView textPregunta27_3b;
    private TextView textPregunta28;
    private TextView textPregunta29;
    private TextView textPregunta30b;
    private TextView textPregunta30_1;
    private TextView textPregunta30_1b;
    private TextView textPregunta30_2;
    private TextView textPregunta30_2b;
    private TextView textPregunta30_3;
    private TextView textPregunta30_3b;
    private TextView textPregunta31;
    private TextView textPregunta32;



    double latitude;
    double longitude;

    Random random = new java.util.Random();
    public int rand;

    public RadioGroup rdPreguntaOcupacion, rdPreguntaFocos,   rdPreguntaCuantosCoches,rdPreguntaCuartos, rdPreguntaCuartosDormir,
    rdPreguntaBanos,rdPreguntaRegadera,
    rdPreguntaEstufa, rdPreguntaEdad, rdPreguntaGenero, rdPreguntaTipoVivienda, rdPreguntaTipoPiso;


    public RadioGroup  rdPreguntaAporta,  rdPreguntaAbandono;


    private static final int READ_BLOCK_SIZE = 100000;

    Nombre nom = new Nombre();
    String nombreEncuesta = nom.nombreEncuesta();

    UsuariosSQLiteHelper usdbh;
    UsuariosSQLiteHelper Udb;
    List<String> list;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    private SQLiteDatabase db;

    UsuariosSQLiteHelper2 usdbh2;
    private SQLiteDatabase db2;

    private Spinner spinnerDelegaciones;
    private Spinner spinnerc3a;
    private Spinner spinnerMeses;
    private Spinner spinnerSemana;
    private Spinner spinnerCalifica;
    private Spinner spinner10;

    private Spinner spinner0;

    private Spinner spinner12;
    Timer timer;

    public EditText txtSeccion;

    public String opEstadoCivil="sin datos";
    public String opHijos="sin datos";
    public String opJefe="sin datos";
    public String opAporta="sin datos";
    public String opEstudio="sin datos";
    public String opAbandono="sin datos";
    public String opOcupacion="sin datos";
    public String opCoche="sin datos";
    public String opFocos="sin datos";
    public String opCuantosCoches="sin datos";

    public String opCuartos="sin datos";
    public String opCuartosDormir="sin datos";
    public String opBanos="sin datos";
    public String opRegadera="sin datos";
    public String opInternet="sin datos";
    public String opTrabajaron="sin datos";
    public String opEstufa="sin datos";
    public String opEdad="sin datos";
    public String opGenero="sin datos";
    public String opTipoVivienda="sin datos";
    public String opTipoPiso="sin datos";

    public String opMedio = "sin datos";
    public String opSemana = "sin datos";
    public String opFinSemana = "sin datos";
    public String op1="sin datos";	public RadioGroup rdPregunta1;	public EditText editPregunta1;	public String captura1;	LinearLayout lay1;
    public String op2="sin datos";	public RadioGroup rdPregunta2;	public EditText editPregunta2;	public String captura2;	LinearLayout lay2;
    public String op3="sin datos";	public RadioGroup rdPregunta3;	public EditText editPregunta3;	public String captura3;	LinearLayout lay3;
    public String op4="sin datos";	public RadioGroup rdPregunta4;	public EditText editPregunta4;	public String captura4;	LinearLayout lay4;
    public String op5="sin datos";	public RadioGroup rdPregunta5;	public EditText editPregunta5;	public String captura5;	LinearLayout lay5;
    public String op6="sin datos";	public RadioGroup rdPregunta6;	public EditText editPregunta6;	public String captura6;	LinearLayout lay6;
    public String op7="sin datos";	public RadioGroup rdPregunta7;	public EditText editPregunta7;	public String captura7;	LinearLayout lay7;
    public String oppc="sin datos";	public RadioGroup rdPreguntapc;	public EditText editPreguntapc;	public String capturapc;	LinearLayout laypc;
    public String oppc_1="sin datos";	public RadioGroup rdPreguntapc_1;	public EditText editPreguntapc_1;	public String capturapc_1;	LinearLayout laypc_1;
    public String oppc_2="sin datos";	public RadioGroup rdPreguntapc_2;	public EditText editPreguntapc_2;	public String capturapc_2;	LinearLayout laypc_2;
    public String oppc_3="sin datos";	public RadioGroup rdPreguntapc_3;	public EditText editPreguntapc_3;	public String capturapc_3;	LinearLayout laypc_3;
    public String oppc_4="sin datos";	public RadioGroup rdPreguntapc_4;	public EditText editPreguntapc_4;	public String capturapc_4;	LinearLayout laypc_4;
    public String op8="sin datos";	public RadioGroup rdPregunta8;	public EditText editPregunta8;	public String captura8;	LinearLayout lay8;
    public String op9="sin datos";	public RadioGroup rdPregunta9;	public EditText editPregunta9;	public String captura9;	LinearLayout lay9;
    public String op10="sin datos";	public RadioGroup rdPregunta10;	public EditText editPregunta10;	public String captura10;	LinearLayout lay10;
    public String op11="sin datos";	public RadioGroup rdPregunta11;	public EditText editPregunta11;	public String captura11;	LinearLayout lay11;
    public String op12="sin datos";	public RadioGroup rdPregunta12;	public EditText editPregunta12;	public String captura12;	LinearLayout lay12;
    public String op13="sin datos";	public RadioGroup rdPregunta13;	public EditText editPregunta13;	public String captura13;	LinearLayout lay13;
    public String op14="sin datos";	public RadioGroup rdPregunta14;	public EditText editPregunta14;	public String captura14;	LinearLayout lay14;
    public String op15="sin datos";	public RadioGroup rdPregunta15;	public EditText editPregunta15;	public String captura15;	LinearLayout lay15;
    public String op16="sin datos";	public RadioGroup rdPregunta16;	public EditText editPregunta16;	public String captura16;	LinearLayout lay16;
    public String op16a="sin datos";	public RadioGroup rdPregunta16a;	public EditText editPregunta16a;	public String captura16a;	LinearLayout lay16a;
    public String op16b="sin datos";	public RadioGroup rdPregunta16b;	public EditText editPregunta16b;	public String captura16b;	LinearLayout lay16b;
    public String op17="sin datos";	public RadioGroup rdPregunta17;	public EditText editPregunta17;	public String captura17;	LinearLayout lay17;
    public String op17a="sin datos";	public RadioGroup rdPregunta17a;	public EditText editPregunta17a;	public String captura17a;	LinearLayout lay17a;
    public String op18="sin datos";	public RadioGroup rdPregunta18;	public EditText editPregunta18;	public String captura18;	LinearLayout lay18;
    public String op19="sin datos";	public RadioGroup rdPregunta19;	public EditText editPregunta19;	public String captura19;	LinearLayout lay19;
    public String op19a="sin datos";	public RadioGroup rdPregunta19a;	public EditText editPregunta19a;	public String captura19a;	LinearLayout lay19a;
    public String op19b="sin datos";	public RadioGroup rdPregunta19b;	public EditText editPregunta19b;	public String captura19b;	LinearLayout lay19b;
    public String op20="sin datos";	public RadioGroup rdPregunta20;	public EditText editPregunta20;	public String captura20;	LinearLayout lay20;
    public String op21="sin datos";	public RadioGroup rdPregunta21;	public EditText editPregunta21;	public String captura21;	LinearLayout lay21;
    public String op22="sin datos";	public RadioGroup rdPregunta22;	public EditText editPregunta22;	public String captura22;	LinearLayout lay22;
    public String op23="sin datos";	public RadioGroup rdPregunta23;	public EditText editPregunta23;	public String captura23;	LinearLayout lay23;
    public String op24="sin datos";	public RadioGroup rdPregunta24;	public EditText editPregunta24;	public String captura24;	LinearLayout lay24;
    public String op24_1="sin datos";	public RadioGroup rdPregunta24_1;	public EditText editPregunta24_1;	public String captura24_1;	LinearLayout lay24_1;
    public String op24_1a="sin datos";	public RadioGroup rdPregunta24_1a;	public EditText editPregunta24_1a;	public String captura24_1a;	LinearLayout lay24_1a;
    public String op24_1b="sin datos";	public RadioGroup rdPregunta24_1b;	public EditText editPregunta24_1b;	public String captura24_1b;	LinearLayout lay24_1b;
    public String op24_2="sin datos";	public RadioGroup rdPregunta24_2;	public EditText editPregunta24_2;	public String captura24_2;	LinearLayout lay24_2;
    public String op24_2a="sin datos";	public RadioGroup rdPregunta24_2a;	public EditText editPregunta24_2a;	public String captura24_2a;	LinearLayout lay24_2a;
    public String op24_2b="sin datos";	public RadioGroup rdPregunta24_2b;	public EditText editPregunta24_2b;	public String captura24_2b;	LinearLayout lay24_2b;
    public String op24_3="sin datos";	public RadioGroup rdPregunta24_3;	public EditText editPregunta24_3;	public String captura24_3;	LinearLayout lay24_3;
    public String op24_3a="sin datos";	public RadioGroup rdPregunta24_3a;	public EditText editPregunta24_3a;	public String captura24_3a;	LinearLayout lay24_3a;
    public String op24_3b="sin datos";	public RadioGroup rdPregunta24_3b;	public EditText editPregunta24_3b;	public String captura24_3b;	LinearLayout lay24_3b;
    public String op25="sin datos";	public RadioGroup rdPregunta25;	public EditText editPregunta25;	public String captura25;	LinearLayout lay25;
    public String op26="sin datos";	public RadioGroup rdPregunta26;	public EditText editPregunta26;	public String captura26;	LinearLayout lay26;
    public String op27="sin datos";	public RadioGroup rdPregunta27;	public EditText editPregunta27;	public String captura27;	LinearLayout lay27;
    public String op27_1="sin datos";	public RadioGroup rdPregunta27_1;	public EditText editPregunta27_1;	public String captura27_1;	LinearLayout lay27_1;
    public String op27_1a="sin datos";	public RadioGroup rdPregunta27_1a;	public EditText editPregunta27_1a;	public String captura27_1a;	LinearLayout lay27_1a;
    public String op27_1b="sin datos";	public RadioGroup rdPregunta27_1b;	public EditText editPregunta27_1b;	public String captura27_1b;	LinearLayout lay27_1b;
    public String op27_2="sin datos";	public RadioGroup rdPregunta27_2;	public EditText editPregunta27_2;	public String captura27_2;	LinearLayout lay27_2;
    public String op27_2a="sin datos";	public RadioGroup rdPregunta27_2a;	public EditText editPregunta27_2a;	public String captura27_2a;	LinearLayout lay27_2a;
    public String op27_2b="sin datos";	public RadioGroup rdPregunta27_2b;	public EditText editPregunta27_2b;	public String captura27_2b;	LinearLayout lay27_2b;
    public String op27_3="sin datos";	public RadioGroup rdPregunta27_3;	public EditText editPregunta27_3;	public String captura27_3;	LinearLayout lay27_3;
    public String op27_3a="sin datos";	public RadioGroup rdPregunta27_3a;	public EditText editPregunta27_3a;	public String captura27_3a;	LinearLayout lay27_3a;
    public String op27_3b="sin datos";	public RadioGroup rdPregunta27_3b;	public EditText editPregunta27_3b;	public String captura27_3b;	LinearLayout lay27_3b;
    public String op28="sin datos";	public RadioGroup rdPregunta28;	public EditText editPregunta28;	public String captura28;	LinearLayout lay28;
    public String op29="sin datos";	public RadioGroup rdPregunta29;	public EditText editPregunta29;	public String captura29;	LinearLayout lay29;
    public String op30="sin datos";	public RadioGroup rdPregunta30;	public EditText editPregunta30;	public String captura30;	LinearLayout lay30;
    public String op30_1="sin datos";	public RadioGroup rdPregunta30_1;	public EditText editPregunta30_1;	public String captura30_1;	LinearLayout lay30_1;
    public String op30_1a="sin datos";	public RadioGroup rdPregunta30_1a;	public EditText editPregunta30_1a;	public String captura30_1a;	LinearLayout lay30_1a;
    public String op30_1b="sin datos";	public RadioGroup rdPregunta30_1b;	public EditText editPregunta30_1b;	public String captura30_1b;	LinearLayout lay30_1b;
    public String op30_2="sin datos";	public RadioGroup rdPregunta30_2;	public EditText editPregunta30_2;	public String captura30_2;	LinearLayout lay30_2;
    public String op30_2a="sin datos";	public RadioGroup rdPregunta30_2a;	public EditText editPregunta30_2a;	public String captura30_2a;	LinearLayout lay30_2a;
    public String op30_2b="sin datos";	public RadioGroup rdPregunta30_2b;	public EditText editPregunta30_2b;	public String captura30_2b;	LinearLayout lay30_2b;
    public String op30_3="sin datos";	public RadioGroup rdPregunta30_3;	public EditText editPregunta30_3;	public String captura30_3;	LinearLayout lay30_3;
    public String op30_3a="sin datos";	public RadioGroup rdPregunta30_3a;	public EditText editPregunta30_3a;	public String captura30_3a;	LinearLayout lay30_3a;
    public String op30_3b="sin datos";	public RadioGroup rdPregunta30_3b;	public EditText editPregunta30_3b;	public String captura30_3b;	LinearLayout lay30_3b;
    public String op31="sin datos";	public RadioGroup rdPregunta31;	public EditText editPregunta31;	public String captura31;	LinearLayout lay31;
    public String op32="sin datos";	public RadioGroup rdPregunta32;	public EditText editPregunta32;	public String captura32;	LinearLayout lay32;

    public EditText editTelefono;


    LinearLayout laySocioE;
    LinearLayout layEst;
    LinearLayout layAporta;
    LinearLayout layOcupacion;
    LinearLayout layCuartos;
    LinearLayout layCuartosDormir;
    LinearLayout layFocos;
    LinearLayout layBanos;
    LinearLayout layRegadera;
    LinearLayout layEstufa;
    LinearLayout layEdad;
    LinearLayout layTipoPiso;
    LinearLayout layTipoVivienda;
    LinearLayout layGenero;


    public Resources res;

    UsuariosSQLiteHelper3 usdbh3;
    private SQLiteDatabase db3;


    LinearLayout layCuantosCoches;


    public RadioButton radio1_07;
    public RadioButton radio_abandono1;
    public RadioButton radio_abandono2;
    public RadioButton radio_abandono3;
    public RadioButton radio_abandono4;

    public String captura10a, captura11a, captura13a, captura14a, captura14b, captura14c;
    public String captura18a;
    public String capturaMedio;
    public String capturaSemana;
    public String capturaFinSemana, capturaHijos;

    String cseis_1;
    String cseis_2;
    String cseis_3;
    String cseis_4;
    String cseis_5;
    String cseis_6;
    String cseis_7;

    CheckBox checkcseis_1;
    CheckBox checkcseis_2;
    CheckBox checkcseis_3;
    CheckBox checkcseis_4;
    CheckBox checkcseis_5;
    CheckBox checkcseis_6;
    CheckBox checkcseis_7;

    public String capturaOcupacion, capturaCoche, capturaE3, capturaE4, capturaCuantosCoches, capturaTrabajo, capturaE7,
    capturaFocos, capturaCuartos, capturaCuartosDormir, capturaBanos, capturaInternet, capturaTrabajaron;
    public String capturaRegadera, capturaEstufa, capturaEdad, capturaGenero, capturaTipoVivienda, capturaTipoPiso,
    capturaE17, capturaE18, capturaE19, capturaE20;
    public String capturaJefe, capturaAporta;


    public String maximo = "";
    int elMaximo;
    String tipoEncuesta;

    public String pasoUsuario;

    public String Secc;

    public EditText editUsuario;

    public String str;
    public String variablePrueba;
    public String encuestador;
    public String tablet;
    public String hora;

    public String quien;

    String upLoadServerUri = null;
    ProgressDialog dialog = null;
    final String path = "/mnt/sdcard/Mis_archivos/";

    int serverResponseCode = 0;

    public String tiempo;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df1 = new SimpleDateFormat("yyy-MM-dd");
    String formattedDate1 = df1.format(c.getTime());

    SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
    String formattedDate2 = df2.format(c.getTime());

    SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
    String formattedDate3 = df3.format(c.getTime());

    SimpleDateFormat df6 = new SimpleDateFormat("MM");
    String formattedDate6 = df6.format(c.getTime());

    SimpleDateFormat df7 = new SimpleDateFormat("dd");
    String formattedDate7 = df7.format(c.getTime());

    SimpleDateFormat df4 = new SimpleDateFormat("HH:mm:ss a");
    String formattedDate4 = df4.format(c.getTime());

    SimpleDateFormat df5 = new SimpleDateFormat("HH:mm:ss");
    String formattedDate5 = df5.format(c.getTime());

    Calendar t1 = Calendar.getInstance();
    long milis1 = t1.getTimeInMillis();

    public static String getHostName(String defValue) {
        try {
            Method getString = Build.class.getDeclaredMethod("getString", String.class);
            getString.setAccessible(true);
            return getString.invoke(null, "net.hostname").toString();
        } catch (Exception ex) {
            return defValue;
        }
    }

    static String ID = getHostName(null);
    static String prefix = ID;

    public String cachaNombre() {
        Bundle datos = this.getIntent().getExtras();
        String Nombre = datos.getString("Nombre");
        return Nombre;
    }

    public String cachaTelefono() {
        Bundle datos = this.getIntent().getExtras();
        String telefono = datos.getString("telefono");
        return telefono;
    }

    public String cachaSeccion() {
        Bundle datos = this.getIntent().getExtras();
        String Seccion = datos.getString("Seccion");
        return Seccion;
    }

    public String cachaDelegacion() {
        Bundle datos = this.getIntent().getExtras();
        String delegacion = datos.getString("delegacion");
        return delegacion;
    }

    public String cachaEquipo() {
        Bundle datos = this.getIntent().getExtras();
        String equipo = datos.getString("equipo");
        return equipo;
    }

//	public long t1() {
//		Bundle datos = this.getIntent().getExtras();
//		long t1 = datos.getLong("t1");
//		return t1;
//	}


    @SuppressLint("MissingPermission")
    public String sacaChip() {
        String szImei;
TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);//Telefono
szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
if (szImei == null) {
szImei = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);// Tableta
}
return szImei;
}

    @SuppressLint("MissingPermission")
    public String sacaImei() {
        String szImei;
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);//Telefono
        szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
        if (szImei == null) {
            szImei = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);// Tableta
        }
        return szImei;
    }

public String hora() {

    if (formattedDate5.matches("")) {
        formattedDate5 = df5.format(c.getTime());
    }
    return formattedDate5;
}

public void dialogo() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Desea continuar Encuestando..?").setTitle("IMPORTANTE").setCancelable(false)
    .setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {

            detenerGrabacion();

            Intent i = new Intent(MainActivityPantalla1.this, Bienvenida.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
System.exit(0); // metodo que se debe implementar
}
}).setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int id) {

            detenerGrabacion();

        Intent i = new Intent(MainActivityPantalla1.this, Bienvenida.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("Nombre", cachaNombre());
        i.putExtra(USUARIO,usuario);
        startActivity(i);
System.exit(0); // metodo que se debe implementar
}
});
AlertDialog alert = builder.create();
alert.show();

}

public void dialogoParoAudio() {
    timer.cancel();
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    MainActivityPantalla1.this.runOnUiThread(new Runnable() {
        public void run() {
            builder.setMessage("¿Se detendrá la grabación y \n se reiniciará la encuesta..?")
            .setTitle("AVISO...!!!").setCancelable(false)
            .setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    detenerGrabacion();

                    Intent i = new Intent(MainActivityPantalla1.this, Entrada.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
System.exit(0); // metodo que se debe
// implementar
}
});
            AlertDialog alert = builder.create();
            alert.show();

        }
    });

}

public void dialogoCierraEncuesta() {
    timer.cancel();

    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    MainActivityPantalla1.this.runOnUiThread(new Runnable() {
        public void run() {
            builder.setMessage("Excediste el tiempo máximo para realizar la encuesta \n"
                + "¡¡¡ Se detendrá la grabación y se reiniciará la Aplicación..!!!").setTitle("AVISO...!!!")
            .setCancelable(false).setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    detenerGrabacion();

                    Intent i = new Intent(MainActivityPantalla1.this, Entrada.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
System.exit(0); // metodo que se debe
// implementar
}
});

            AlertDialog alert = builder.create();

            alert.show();
        }
    });

}

public void dialogoAbandono() {
    timer.cancel();

    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    MainActivityPantalla1.this.runOnUiThread(new Runnable() {
        public void run() {
            builder.setMessage("Deseas abandonar la encuesta?").setTitle("AVISO...!!!").setCancelable(false)
            .setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    detenerGrabacion();
                }
            }).setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            AlertDialog alert = builder.create();

            alert.show();
        }
    });

}

// EVENTO AL PULSAR EL BOTON ATRAS

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
        Toast.makeText(getApplicationContext(), "No puedo ir hacia atrás!!\nEstoy grabando...", Toast.LENGTH_SHORT)
        .show();

// dialogoAbandono();

        return true;
    }
    return super.onKeyDown(keyCode, event);
}

public String nombreArchivo() {
    String date = formattedDate3.toString();
    String var2 = ".txt";
    String var3 = date + var2;

    final String nombre = date + "-" + tablet + "-" + nombreEncuesta + var2;
    return nombre;
}

public String nombreAudio() {

    elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;
    String date = formattedDate3.toString();
    String var2 = ".mp3";

    int consecutivo = Integer.parseInt(sacaConsecutivo().toString()) + 1;
    String elConsecutivo = String.valueOf(consecutivo);
    int Cons = elConsecutivo.length();

    if (Cons == 1) {
        elConsecutivo = "00" + elConsecutivo;
    } else if (Cons == 2) {
        elConsecutivo = "0" + elConsecutivo;
    } else {
        elConsecutivo = elConsecutivo;
    }

    String usuario;

    int tamanoUsuario = cachaNombre().length();

    if (tamanoUsuario == 1) {
        usuario = "00" + cachaNombre();
    } else if (tamanoUsuario == 2) {
        usuario = "0" + cachaNombre();
    } else {
        usuario = cachaNombre();
    }

// nombreEncuesta_fecha_chip_usuario_consecutivo
//    final String nombreAudio = nombreEncuesta + "_" + date + "_" + sacaImei() + "_" + cachaNombre() + "_" + elConsecutivo + "_" + cachaTelefono() + ".mp3";
    final String nombreAudio = nombreEncuesta + "_" + date + "_" + sacaImei() + "_" + cachaNombre() + "_" + elConsecutivo + ".mp3";
// final String nombreAudio =
// nombreEncuesta+"_"+date+"_"+prefix+"_"+elConsecutivo+".mp3";
    return nombreAudio;
}

public String elTiempo() {
// Para la diferenci entre tiempos
    Calendar t2 = Calendar.getInstance();
    long milis2 = t2.getTimeInMillis();
//		long diff = milis2 - t1();
    long diff = milis2 - milis1;

    long diffSegundos = diff / 1000;

    long diffMinutos = diffSegundos / 60;

    long residuo = diffSegundos % 60;

    System.out.println(diffSegundos);
    System.out.println(diffMinutos);
    System.out.println(residuo);

    tiempo = diffMinutos + ":" + residuo;

    return tiempo;

}


private Integer[] mLinearLayoutIds = {
//            R.layout.activity_pantalla1,
//            R.layout.activity_pantalla2,
//            R.layout.activity_pantalla3,
//            R.layout.activity_pantalla4,
//            R.layout.activity_pantalla5,
//            R.layout.activity_pantalla6,
//            R.layout.activity_pantalla7,
//            R.layout.activity_pantalla8,
//            R.layout.activity_pantalla9,
//            R.layout.activity_pantalla10,
////// R.layout.activity_pantalla11,
//// R.layout.activity_pantalla12,
//// R.layout.activity_pantalla13,
//// R.layout.activity_pantalla14,
//// R.layout.activity_pantalla15,
//// R.layout.activity_pantalla16,
//// R.layout.activity_pantalla17,
//// R.layout.activity_pantalla18,
//// R.layout.activity_pantalla19,
};

//    int[] layouts_c1 = new int[] {
//            R.layout.pregc1a,
//            R.layout.pregc1b,
//            R.layout.pregc1c,
//            R.layout.pregc1d,
//            R.layout.pregc1e,
//            R.layout.pregc1f,
//            R.layout.pregc1g,
//            R.layout.pregc1h,
//              };



@Override
protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
    super.onCreate(savedInstanceState);
setContentView(R.layout.activity_pantalla1); // COMENTAR ESTA CUANDO ES ALEATORIO

Intent startingIntent = getIntent();
if (startingIntent == null) {
    Log.e(TAG, "No Intent?  We're not supposed to be here...");
    finish();
    return;
}

    if (savedInstanceState != null) {
        usuario = (Usuario) savedInstanceState.getSerializable(USUARIO);
    } else {
        usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
    }

// Carga las pantallas aleatoriamente
random = new java.util.Random();
//
/*DESCOMENTAR ESTAS 3 LINEAS CUANDO YA ESTA EL NUMERO DE HOJAS ALEATORIO */
//        rand = random.nextInt(9);
//        setContentView(mLinearLayoutIds[rand]);
//        Log.i(null, "El aleatorio: " + rand); // si rand= 11 en el layoud corresponde a uno mas


/*activity_pantalla12*/

// Crea Log cuando falla la app
Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(MainActivityPantalla1.this,this));


cachaNombre(); // llamado al metodo para obtener el numero del
// encuestador

try {

    handler = new Handler();

    new Thread(new Runnable() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Iniciando Grabación");
                    grabar();
                }

            });

        }
    }).start();

} catch (Exception e) {

}

///////////// EL TIMER PARA PARAR LA ENCUESTA /////////////////

timer = new Timer();
//		timer.schedule(new CierraEncuesta(), 1800000); // 8 Minutos 480000

////////////////////////
mProgressView = findViewById(R.id.login_progressMain);

    txtSeccion = (EditText) findViewById(R.id.txtSeccion);

    txtSeccion.setText(cachaSeccion());
    txtSeccion.setEnabled(false);

    textPreguntaEntrada = (TextView) findViewById(R.id.textPreguntaEntrada);

    // justificar el texto
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        textPreguntaEntrada.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
    }



    res = getResources();

    rdPregunta1 = (RadioGroup)findViewById(R.id.rdPregunta1);	captura1 =res.getString(R.string.PREGUNTA1);	lay1 = (LinearLayout) findViewById(R.id.lay1);
    rdPregunta2 = (RadioGroup)findViewById(R.id.rdPregunta2);	captura2 =res.getString(R.string.PREGUNTA2);	lay2 = (LinearLayout) findViewById(R.id.lay2);
    rdPregunta3 = (RadioGroup)findViewById(R.id.rdPregunta3);	captura3 =res.getString(R.string.PREGUNTA3);	lay3 = (LinearLayout) findViewById(R.id.lay3);
    rdPregunta4 = (RadioGroup)findViewById(R.id.rdPregunta4);	captura4 =res.getString(R.string.PREGUNTA4);	lay4 = (LinearLayout) findViewById(R.id.lay4);
    rdPregunta5 = (RadioGroup)findViewById(R.id.rdPregunta5);	captura5 =res.getString(R.string.PREGUNTA5);	lay5 = (LinearLayout) findViewById(R.id.lay5);
    rdPregunta6 = (RadioGroup)findViewById(R.id.rdPregunta6);	captura6 =res.getString(R.string.PREGUNTA6);	lay6 = (LinearLayout) findViewById(R.id.lay6);
    rdPregunta7 = (RadioGroup)findViewById(R.id.rdPregunta7);	captura7 =res.getString(R.string.PREGUNTA7);	lay7 = (LinearLayout) findViewById(R.id.lay7);
    rdPreguntapc = (RadioGroup)findViewById(R.id.rdPreguntapc);	capturapc =res.getString(R.string.PREGUNTApc);	laypc = (LinearLayout) findViewById(R.id.laypc);
    rdPreguntapc_1 = (RadioGroup)findViewById(R.id.rdPreguntapc_1);	capturapc_1 =res.getString(R.string.PREGUNTApc_1);	laypc_1 = (LinearLayout) findViewById(R.id.laypc_1);
    rdPreguntapc_2 = (RadioGroup)findViewById(R.id.rdPreguntapc_2);	capturapc_2 =res.getString(R.string.PREGUNTApc_2);	laypc_2 = (LinearLayout) findViewById(R.id.laypc_2);
    rdPreguntapc_3 = (RadioGroup)findViewById(R.id.rdPreguntapc_3);	capturapc_3 =res.getString(R.string.PREGUNTApc_3);	laypc_3 = (LinearLayout) findViewById(R.id.laypc_3);
    rdPreguntapc_4 = (RadioGroup)findViewById(R.id.rdPreguntapc_4);	capturapc_4 =res.getString(R.string.PREGUNTApc_4);	laypc_4 = (LinearLayout) findViewById(R.id.laypc_4);
    rdPregunta8 = (RadioGroup)findViewById(R.id.rdPregunta8);	captura8 =res.getString(R.string.PREGUNTA8);	lay8 = (LinearLayout) findViewById(R.id.lay8);
    rdPregunta9 = (RadioGroup)findViewById(R.id.rdPregunta9);	captura9 =res.getString(R.string.PREGUNTA9);	lay9 = (LinearLayout) findViewById(R.id.lay9);
    rdPregunta10 = (RadioGroup)findViewById(R.id.rdPregunta10);	captura10 =res.getString(R.string.PREGUNTA10);	lay10 = (LinearLayout) findViewById(R.id.lay10);
    rdPregunta11 = (RadioGroup)findViewById(R.id.rdPregunta11);	captura11 =res.getString(R.string.PREGUNTA11);	lay11 = (LinearLayout) findViewById(R.id.lay11);
    rdPregunta12 = (RadioGroup)findViewById(R.id.rdPregunta12);	captura12 =res.getString(R.string.PREGUNTA12);	lay12 = (LinearLayout) findViewById(R.id.lay12);
    rdPregunta13 = (RadioGroup)findViewById(R.id.rdPregunta13);	captura13 =res.getString(R.string.PREGUNTA13);	lay13 = (LinearLayout) findViewById(R.id.lay13);
    rdPregunta14 = (RadioGroup)findViewById(R.id.rdPregunta14);	captura14 =res.getString(R.string.PREGUNTA14);	lay14 = (LinearLayout) findViewById(R.id.lay14);
    rdPregunta15 = (RadioGroup)findViewById(R.id.rdPregunta15);	captura15 =res.getString(R.string.PREGUNTA15);	lay15 = (LinearLayout) findViewById(R.id.lay15);
    rdPregunta16 = (RadioGroup)findViewById(R.id.rdPregunta16);	captura16 =res.getString(R.string.PREGUNTA16);	lay16 = (LinearLayout) findViewById(R.id.lay16);
    rdPregunta16a = (RadioGroup)findViewById(R.id.rdPregunta16a);	captura16a =res.getString(R.string.PREGUNTA16a);	lay16a = (LinearLayout) findViewById(R.id.lay16a);
    rdPregunta16b = (RadioGroup)findViewById(R.id.rdPregunta16b);	captura16b =res.getString(R.string.PREGUNTA16b);	lay16b = (LinearLayout) findViewById(R.id.lay16b);
    rdPregunta17 = (RadioGroup)findViewById(R.id.rdPregunta17);	captura17 =res.getString(R.string.PREGUNTA17);	lay17 = (LinearLayout) findViewById(R.id.lay17);
    rdPregunta17a = (RadioGroup)findViewById(R.id.rdPregunta17a);	captura17a =res.getString(R.string.PREGUNTA17a);	lay17a = (LinearLayout) findViewById(R.id.lay17a);
    rdPregunta18 = (RadioGroup)findViewById(R.id.rdPregunta18);	captura18 =res.getString(R.string.PREGUNTA18);	lay18 = (LinearLayout) findViewById(R.id.lay18);
    rdPregunta19 = (RadioGroup)findViewById(R.id.rdPregunta19);	captura19 =res.getString(R.string.PREGUNTA19);	lay19 = (LinearLayout) findViewById(R.id.lay19);
    rdPregunta19a = (RadioGroup)findViewById(R.id.rdPregunta19a);	captura19a =res.getString(R.string.PREGUNTA19a);	lay19a = (LinearLayout) findViewById(R.id.lay19a);
    rdPregunta19b = (RadioGroup)findViewById(R.id.rdPregunta19b);	captura19b =res.getString(R.string.PREGUNTA19b);	lay19b = (LinearLayout) findViewById(R.id.lay19b);
    rdPregunta20 = (RadioGroup)findViewById(R.id.rdPregunta20);	captura20 =res.getString(R.string.PREGUNTA20);	lay20 = (LinearLayout) findViewById(R.id.lay20);
    rdPregunta21 = (RadioGroup)findViewById(R.id.rdPregunta21);	captura21 =res.getString(R.string.PREGUNTA21);	lay21 = (LinearLayout) findViewById(R.id.lay21);
    rdPregunta22 = (RadioGroup)findViewById(R.id.rdPregunta22);	captura22 =res.getString(R.string.PREGUNTA22);	lay22 = (LinearLayout) findViewById(R.id.lay22);
    rdPregunta23 = (RadioGroup)findViewById(R.id.rdPregunta23);	captura23 =res.getString(R.string.PREGUNTA23);	lay23 = (LinearLayout) findViewById(R.id.lay23);
    rdPregunta24 = (RadioGroup)findViewById(R.id.rdPregunta24);	captura24 =res.getString(R.string.PREGUNTA24);	lay24 = (LinearLayout) findViewById(R.id.lay24);
    rdPregunta24_1 = (RadioGroup)findViewById(R.id.rdPregunta24_1);	captura24_1 =res.getString(R.string.PREGUNTA24_1);	lay24_1 = (LinearLayout) findViewById(R.id.lay24_1);
    rdPregunta24_1a = (RadioGroup)findViewById(R.id.rdPregunta24_1a);	captura24_1a =res.getString(R.string.PREGUNTA24_1a);	lay24_1a = (LinearLayout) findViewById(R.id.lay24_1a);
    rdPregunta24_1b = (RadioGroup)findViewById(R.id.rdPregunta24_1b);	captura24_1b =res.getString(R.string.PREGUNTA24_1b);	lay24_1b = (LinearLayout) findViewById(R.id.lay24_1b);
    rdPregunta24_2 = (RadioGroup)findViewById(R.id.rdPregunta24_2);	captura24_2 =res.getString(R.string.PREGUNTA24_2);	lay24_2 = (LinearLayout) findViewById(R.id.lay24_2);
    rdPregunta24_2a = (RadioGroup)findViewById(R.id.rdPregunta24_2a);	captura24_2a =res.getString(R.string.PREGUNTA24_2a);	lay24_2a = (LinearLayout) findViewById(R.id.lay24_2a);
    rdPregunta24_2b = (RadioGroup)findViewById(R.id.rdPregunta24_2b);	captura24_2b =res.getString(R.string.PREGUNTA24_2b);	lay24_2b = (LinearLayout) findViewById(R.id.lay24_2b);
    rdPregunta24_3 = (RadioGroup)findViewById(R.id.rdPregunta24_3);	captura24_3 =res.getString(R.string.PREGUNTA24_3);	lay24_3 = (LinearLayout) findViewById(R.id.lay24_3);
    rdPregunta24_3a = (RadioGroup)findViewById(R.id.rdPregunta24_3a);	captura24_3a =res.getString(R.string.PREGUNTA24_3a);	lay24_3a = (LinearLayout) findViewById(R.id.lay24_3a);
    rdPregunta24_3b = (RadioGroup)findViewById(R.id.rdPregunta24_3b);	captura24_3b =res.getString(R.string.PREGUNTA24_3b);	lay24_3b = (LinearLayout) findViewById(R.id.lay24_3b);
    rdPregunta25 = (RadioGroup)findViewById(R.id.rdPregunta25);	captura25 =res.getString(R.string.PREGUNTA25);	lay25 = (LinearLayout) findViewById(R.id.lay25);
    rdPregunta26 = (RadioGroup)findViewById(R.id.rdPregunta26);	captura26 =res.getString(R.string.PREGUNTA26);	lay26 = (LinearLayout) findViewById(R.id.lay26);
    rdPregunta27 = (RadioGroup)findViewById(R.id.rdPregunta27);	captura27 =res.getString(R.string.PREGUNTA27);	lay27 = (LinearLayout) findViewById(R.id.lay27);
    rdPregunta27_1 = (RadioGroup)findViewById(R.id.rdPregunta27_1);	captura27_1 =res.getString(R.string.PREGUNTA27_1);	lay27_1 = (LinearLayout) findViewById(R.id.lay27_1);
    rdPregunta27_1a = (RadioGroup)findViewById(R.id.rdPregunta27_1a);	captura27_1a =res.getString(R.string.PREGUNTA27_1a);	lay27_1a = (LinearLayout) findViewById(R.id.lay27_1a);
    rdPregunta27_1b = (RadioGroup)findViewById(R.id.rdPregunta27_1b);	captura27_1b =res.getString(R.string.PREGUNTA27_1b);	lay27_1b = (LinearLayout) findViewById(R.id.lay27_1b);
    rdPregunta27_2 = (RadioGroup)findViewById(R.id.rdPregunta27_2);	captura27_2 =res.getString(R.string.PREGUNTA27_2);	lay27_2 = (LinearLayout) findViewById(R.id.lay27_2);
    rdPregunta27_2a = (RadioGroup)findViewById(R.id.rdPregunta27_2a);	captura27_2a =res.getString(R.string.PREGUNTA27_2a);	lay27_2a = (LinearLayout) findViewById(R.id.lay27_2a);
    rdPregunta27_2b = (RadioGroup)findViewById(R.id.rdPregunta27_2b);	captura27_2b =res.getString(R.string.PREGUNTA27_2b);	lay27_2b = (LinearLayout) findViewById(R.id.lay27_2b);
    rdPregunta27_3 = (RadioGroup)findViewById(R.id.rdPregunta27_3);	captura27_3 =res.getString(R.string.PREGUNTA27_3);	lay27_3 = (LinearLayout) findViewById(R.id.lay27_3);
    rdPregunta27_3a = (RadioGroup)findViewById(R.id.rdPregunta27_3a);	captura27_3a =res.getString(R.string.PREGUNTA27_3a);	lay27_3a = (LinearLayout) findViewById(R.id.lay27_3a);
    rdPregunta27_3b = (RadioGroup)findViewById(R.id.rdPregunta27_3b);	captura27_3b =res.getString(R.string.PREGUNTA27_3b);	lay27_3b = (LinearLayout) findViewById(R.id.lay27_3b);
    rdPregunta28 = (RadioGroup)findViewById(R.id.rdPregunta28);	captura28 =res.getString(R.string.PREGUNTA28);	lay28 = (LinearLayout) findViewById(R.id.lay28);
    rdPregunta29 = (RadioGroup)findViewById(R.id.rdPregunta29);	captura29 =res.getString(R.string.PREGUNTA29);	lay29 = (LinearLayout) findViewById(R.id.lay29);
    rdPregunta30 = (RadioGroup)findViewById(R.id.rdPregunta30);	captura30 =res.getString(R.string.PREGUNTA30);	lay30 = (LinearLayout) findViewById(R.id.lay30);
    rdPregunta30_1 = (RadioGroup)findViewById(R.id.rdPregunta30_1);	captura30_1 =res.getString(R.string.PREGUNTA30_1);	lay30_1 = (LinearLayout) findViewById(R.id.lay30_1);
    rdPregunta30_1a = (RadioGroup)findViewById(R.id.rdPregunta30_1a);	captura30_1a =res.getString(R.string.PREGUNTA30_1a);	lay30_1a = (LinearLayout) findViewById(R.id.lay30_1a);
    rdPregunta30_1b = (RadioGroup)findViewById(R.id.rdPregunta30_1b);	captura30_1b =res.getString(R.string.PREGUNTA30_1b);	lay30_1b = (LinearLayout) findViewById(R.id.lay30_1b);
    rdPregunta30_2 = (RadioGroup)findViewById(R.id.rdPregunta30_2);	captura30_2 =res.getString(R.string.PREGUNTA30_2);	lay30_2 = (LinearLayout) findViewById(R.id.lay30_2);
    rdPregunta30_2a = (RadioGroup)findViewById(R.id.rdPregunta30_2a);	captura30_2a =res.getString(R.string.PREGUNTA30_2a);	lay30_2a = (LinearLayout) findViewById(R.id.lay30_2a);
    rdPregunta30_2b = (RadioGroup)findViewById(R.id.rdPregunta30_2b);	captura30_2b =res.getString(R.string.PREGUNTA30_2b);	lay30_2b = (LinearLayout) findViewById(R.id.lay30_2b);
    rdPregunta30_3 = (RadioGroup)findViewById(R.id.rdPregunta30_3);	captura30_3 =res.getString(R.string.PREGUNTA30_3);	lay30_3 = (LinearLayout) findViewById(R.id.lay30_3);
    rdPregunta30_3a = (RadioGroup)findViewById(R.id.rdPregunta30_3a);	captura30_3a =res.getString(R.string.PREGUNTA30_3a);	lay30_3a = (LinearLayout) findViewById(R.id.lay30_3a);
    rdPregunta30_3b = (RadioGroup)findViewById(R.id.rdPregunta30_3b);	captura30_3b =res.getString(R.string.PREGUNTA30_3b);	lay30_3b = (LinearLayout) findViewById(R.id.lay30_3b);
    rdPregunta31 = (RadioGroup)findViewById(R.id.rdPregunta31);	captura31 =res.getString(R.string.PREGUNTA31);	lay31 = (LinearLayout) findViewById(R.id.lay31);
    rdPregunta32 = (RadioGroup)findViewById(R.id.rdPregunta32);	captura32 =res.getString(R.string.PREGUNTA32);	lay32 = (LinearLayout) findViewById(R.id.lay32);

    editPregunta6= (EditText)findViewById(R.id.editPregunta6);
    editPregunta7= (EditText)findViewById(R.id.editPregunta7);

laySocioE = (LinearLayout) findViewById(R.id.laySocioE);
layEst = (LinearLayout) findViewById(R.id.layEst);
layAporta = (LinearLayout) findViewById(R.id.layAporta);
layOcupacion = (LinearLayout) findViewById(R.id.layOcupacion);
layCuartos = (LinearLayout) findViewById(R.id.layCuartos);
layCuartosDormir = (LinearLayout) findViewById(R.id.layCuartosDormir);
layFocos = (LinearLayout) findViewById(R.id.layFocos);
layBanos = (LinearLayout) findViewById(R.id.layBanos);
layRegadera = (LinearLayout) findViewById(R.id.layRegadera);
layEstufa = (LinearLayout) findViewById(R.id.layEstufa);
//layEdad = (LinearLayout) findViewById(R.id.layEdad);
//layTipoPiso = (LinearLayout) findViewById(R.id.layTipoPiso);
layTipoVivienda = (LinearLayout) findViewById(R.id.layTipoVivienda);
layGenero = (LinearLayout) findViewById(R.id.layGenero);

radio_abandono1 = (RadioButton) findViewById(R.id.radio_abandono1);
radio_abandono2 = (RadioButton) findViewById(R.id.radio_abandono2);
radio_abandono3 = (RadioButton) findViewById(R.id.radio_abandono3);
radio_abandono4 = (RadioButton) findViewById(R.id.radio_abandono4);

    spinner12 =(Spinner) findViewById(R.id.spinner12);


rdPreguntaAporta = (RadioGroup) findViewById(R.id.rdPreguntaAporta);
rdPreguntaAbandono = (RadioGroup) findViewById(R.id.rdPreguntaAbandono);
rdPreguntaOcupacion = (RadioGroup) findViewById(R.id.rdPreguntaOcupacion);
rdPreguntaCuantosCoches = (RadioGroup) findViewById(R.id.rdPreguntaCuantosCoches);
rdPreguntaCuartos = (RadioGroup) findViewById(R.id.rdPreguntaCuartos);
rdPreguntaCuartosDormir = (RadioGroup) findViewById(R.id.rdPreguntaCuartosDormir);
rdPreguntaFocos = (RadioGroup) findViewById(R.id.rdPreguntaFocos);
rdPreguntaBanos = (RadioGroup) findViewById(R.id.rdPreguntaBanos);
rdPreguntaRegadera = (RadioGroup) findViewById(R.id.rdPreguntaRegadera);
rdPreguntaEstufa = (RadioGroup) findViewById(R.id.rdPreguntaEstufa);
rdPreguntaEdad = (RadioGroup) findViewById(R.id.rdPreguntaEdad);
rdPreguntaGenero = (RadioGroup) findViewById(R.id.rdPreguntaGenero);
rdPreguntaTipoVivienda = (RadioGroup) findViewById(R.id.rdPreguntaTipoVivienda);
rdPreguntaTipoPiso = (RadioGroup) findViewById(R.id.rdPreguntaTipoPiso);


    capturaAporta = res.getString(R.string.PREGUNTAAPORTA);
capturaOcupacion = res.getString(R.string.PREGUNTAOCUPACION);
capturaCuantosCoches = res.getString(R.string.PREGUNTACUANTOSCOCHES);
capturaFocos = res.getString(R.string.PREGUNTAFOCOS);
capturaCuartos = res.getString(R.string.PREGUNTACUARTOS);
capturaCuartosDormir = res.getString(R.string.PREGUNTACUARTOSDORMIR);
capturaBanos = res.getString(R.string.PREGUNTABANOS);
capturaEstufa = res.getString(R.string.PREGUNTAESTUFA);
//capturaEdad = res.getString(R.string.PREGUNTAEDAD);
capturaGenero = res.getString(R.string.PREGUNTAGENERO);
capturaTipoVivienda = res.getString(R.string.PREGUNTA_TIPO_VIVIENDA);
//capturaTipoPiso = res.getString(R.string.PREGUNTA_TIPO_PISO);




btnGuardar = (Button) findViewById(R.id.btnGuardar);
btnSalir = (Button) findViewById(R.id.btnSalir);
btnSalir.setEnabled(false);
btnSalir.setVisibility(View.GONE);


//    String laDelegacion = sacaDelegacion(cachaSeccion()).toString();
//
//    if (laDelegacion.matches("Álvaro Obregón")) {
//        elDelegado = "Layda Sansores";
//    } else if (laDelegacion.matches("Azcapotzalco")) {
//        elDelegado = "Vidal Llerenas";
//    } else if (laDelegacion.matches("Benito Juárez")) {
//        elDelegado = "Santiago Taboada";
//    } else if (laDelegacion.matches("Coyoacán")) {
//        elDelegado = "Manuel Negrete";
//    } else if (laDelegacion.matches("Cuajimalpa de Morelos")) {
//        elDelegado = "Adrián Rubalcava";
//    } else if (laDelegacion.matches("Cuauhtémoc")) {
//        elDelegado = "Néstor López Núñez";
//    } else if (laDelegacion.matches("Gustavo A. Madero")) {
//        elDelegado = "Francisco Chiguil";
//    } else if (laDelegacion.matches("Iztacalco")) {
//        elDelegado = "Armando Quintero";
//    } else if (laDelegacion.matches("Iztapalapa")) {
//        elDelegado = "Clara Brugada";
//    } else if (laDelegacion.matches("La Magdalena Contreras")) {
//        elDelegado = "Patricia Ximena Ortiz Couturier";
//    } else if (laDelegacion.matches("Miguel Hidalgo")) {
//        elDelegado = "Víctor Hugo Romo";
//    } else if (laDelegacion.matches("Milpa Alta")) {
//        elDelegado = "Octavio Rivero Villaseñor";
//    } else if (laDelegacion.matches("Tláhuac")) {
//        elDelegado = "Raymundo Martínez Vite";
//    } else if (laDelegacion.matches("Tlalpan")) {
//        elDelegado = "Patricia Elena Aceves Pastrana";
//    } else if (laDelegacion.matches("Venustiano Carranza")) {
//        elDelegado = "Julio César Moreno";
//    } else if (laDelegacion.matches("Xochimilco")) {
//        elDelegado = "José Carlos Acosta Ruiz";
//    }





//
//

layCuantosCoches = (LinearLayout) findViewById(R.id.layCuantosCoches);


     textPregunta12= (TextView) findViewById(R.id.textPregunta12);
     textPregunta16a= (TextView) findViewById(R.id.textPregunta16a );
     textPregunta16b= (TextView) findViewById(R.id.textPregunta16b );
     textPregunta20= (TextView) findViewById(R.id.textPregunta20 );
     textPregunta23= (TextView) findViewById(R.id.textPregunta23 );
     textPregunta24_1= (TextView) findViewById(R.id.textPregunta24_1 );
     textPregunta24_1b= (TextView) findViewById(R.id.textPregunta24_1b );
     textPregunta24_2= (TextView) findViewById(R.id.textPregunta24_2 );
     textPregunta24_2b= (TextView) findViewById(R.id.textPregunta24_2b );
     textPregunta24_3= (TextView) findViewById(R.id.textPregunta24_3 );
     textPregunta24_3b= (TextView) findViewById(R.id.textPregunta24_3b );
     textPregunta25= (TextView) findViewById(R.id.textPregunta25 );
     textPregunta26= (TextView) findViewById(R.id.textPregunta26 );
     textPregunta27_1= (TextView) findViewById(R.id.textPregunta27_1 );
     textPregunta27_1b= (TextView) findViewById(R.id.textPregunta27_1b );
     textPregunta27_2= (TextView) findViewById(R.id.textPregunta27_2 );
     textPregunta27_2b= (TextView) findViewById(R.id.textPregunta27_2b );
     textPregunta27_3= (TextView) findViewById(R.id.textPregunta27_3 );
     textPregunta27_3b= (TextView) findViewById(R.id.textPregunta27_3b );
     textPregunta28= (TextView) findViewById(R.id.textPregunta28 );
     textPregunta29= (TextView) findViewById(R.id.textPregunta29 );
     textPregunta30_1= (TextView) findViewById(R.id.textPregunta30_1 );
     textPregunta30_1b= (TextView) findViewById(R.id.textPregunta30_1b );
     textPregunta30_2= (TextView) findViewById(R.id.textPregunta30_2 );
     textPregunta30_2b= (TextView) findViewById(R.id.textPregunta30_2b );
     textPregunta30_3= (TextView) findViewById(R.id.textPregunta30_3 );
     textPregunta30_3b= (TextView) findViewById(R.id.textPregunta30_3b );
     textPregunta31= (TextView) findViewById(R.id.textPregunta31 );
     textPregunta32= (TextView) findViewById(R.id.textPregunta32 );

    textPregunta12.setText("Podría decirme el nombre del alcalde/ alcaldesa de " + sacaAlcaldia(cachaSeccion()));
    textPregunta16a.setText("");
    textPregunta16b.setText("");
    textPregunta20.setText("");
    textPregunta23.setText("");
    textPregunta24b.setText("");
    textPregunta24_1.setText("");
    textPregunta24_1b.setText("");
    textPregunta24_2.setText("");
    textPregunta24_2b.setText("");
    textPregunta24_3.setText("");
    textPregunta24_3b.setText("");
    textPregunta25.setText("");
    textPregunta26.setText("");
    textPregunta27b.setText("");
    textPregunta27_1.setText("");
    textPregunta27_1b.setText("");
    textPregunta27_2.setText("");
    textPregunta27_2b.setText("");
    textPregunta27_3.setText("");
    textPregunta27_3b.setText("");
    textPregunta28.setText("");
    textPregunta29.setText("");
    textPregunta30b.setText("");
    textPregunta30_1.setText("");
    textPregunta30_1b.setText("");
    textPregunta30_2.setText("");
    textPregunta30_2b.setText("");
    textPregunta30_3.setText("");
    textPregunta30_3b.setText("");
    textPregunta31.setText("");
    textPregunta32.setText("");



    CargaSpinner12();






    rdPregunta1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op1 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op1 = "1";
            }
            else if (checkedId == R.id.radio3) {
                op1 = "Televisión";
            }
            else if (checkedId == R.id.radio4) {
                op1 = "Radio";
            }
            else if (checkedId == R.id.radio5) {
                op1 = "Periódico";
            }
            else if (checkedId == R.id.radio6) {
                op1 = "Redes sociales";
            }
            else if (checkedId == R.id.radio7) {
                op1 = "Internet";
            }
            else if (checkedId == R.id.radio8) {
                op1 = "Otra";
            }
            else if (checkedId == R.id.radio0) {
                op1 = "No sabe / No contestó";
            }
        }
    });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op2 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op2 = "2";
            }
            else if (checkedId == R.id.radio3) {
                op2 = "Sí";
            }
            else if (checkedId == R.id.radio4) {
                op2 = "No";
            }
            else if (checkedId == R.id.radio0) {
                op2 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op3 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op3 = "3";
            }
            else if (checkedId == R.id.radio3) {
                op3 = "Sí";
            }
            else if (checkedId == R.id.radio4) {
                op3 = "No";
            }
            else if (checkedId == R.id.radio0) {
                op3 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op4 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op4 = "4";
            }
            else if (checkedId == R.id.radio3) {
                op4 = "Sí";
            }
            else if (checkedId == R.id.radio4) {
                op4 = "No";
            }
            else if (checkedId == R.id.radio0) {
                op4 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op5 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op5 = "5";
            }
            else if (checkedId == R.id.radio3) {
                op5 = "Mejor";
            }
            else if (checkedId == R.id.radio4) {
                op5 = "Igual de bien";
            }
            else if (checkedId == R.id.radio5) {
                op5 = "Igual de mal";
            }
            else if (checkedId == R.id.radio6) {
                op5 = "Peor";
            }
            else if (checkedId == R.id.radio0) {
                op5 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op6 = "1";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio2) {
                op6 = "6";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio3) {
                op6 = "Inseguridad";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio4) {
                op6 = "Desempleo";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio5) {
                op6 = "Asesinatos/ Violencia";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio6) {
                op6 = "Mala economía/ crisis";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio7) {
                op6 = "Bajos salarios";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio8) {
                op6 = "Inflación/ alza de precios";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio9) {
                op6 = "Corrupción";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio10) {
                op6 = "Pobreza";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio11) {
                op6 = "Educación";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio12) {
                op6 = "Otro (registrar)";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio13) {
                op6 = "Ninguno";
                editPregunta6.setText("");
            }
            else if (checkedId == R.id.radio0) {
                op6 = "No sabe / No contestó";
                editPregunta6.setText("");
            }
        }
    });
    editPregunta6.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            return false;
        }
    });
    editPregunta6.addTextChangedListener(new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start,int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start,int before, int count) {
            if(s.length() != 0){
                rdPregunta6.clearCheck();
            }
        }
    });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op7 = "1";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio2) {
                op7 = "7";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio3) {
                op7 = "Inseguridad";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio4) {
                op7 = "Falta de agua";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio5) {
                op7 = "Asaltos";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio6) {
                op7 = "Narcomenudeo/ venta de drogas";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio7) {
                op7 = "Pavimentación/ baches";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio8) {
                op7 = "Falta de transporte";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio9) {
                op7 = "Mala economía";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio10) {
                op7 = "Pobreza";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio11) {
                op7 = "Educación";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio12) {
                op7 = "Otro (registrar)";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio13) {
                op7 = "Ninguno";
                editPregunta7.setText("");
            }
            else if (checkedId == R.id.radio0) {
                op7 = "No sabe / No contestó";
                editPregunta7.setText("");
            }
        }
    });
    editPregunta7.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.setFocusable(true);
            v.setFocusableInTouchMode(true);
            return false;
        }
    });
    editPregunta7.addTextChangedListener(new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {}
        @Override
        public void beforeTextChanged(CharSequence s, int start,int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start,int before, int count) {
            if(s.length() != 0){
                rdPregunta7.clearCheck();
            }
        }
    });

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    rdPreguntapc.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                oppc = "?0";
            }
            else if (checkedId == R.id.radio2) {
                oppc = "pc";
            }
        }
    });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPreguntapc_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                oppc_1 = "0";
            }
            else if (checkedId == R.id.radio2) {
                oppc_1 = "pc_1";
            }
            else if (checkedId == R.id.radio3) {
                oppc_1 = "Bien";
            }
            else if (checkedId == R.id.radio4) {
                oppc_1 = "Regular";
            }
            else if (checkedId == R.id.radio5) {
                oppc_1 = "Mal";
            }
            else if (checkedId == R.id.radio0) {
                oppc_1 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPreguntapc_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                oppc_2 = "0";
            }
            else if (checkedId == R.id.radio2) {
                oppc_2 = "pc_2";
            }
            else if (checkedId == R.id.radio3) {
                oppc_2 = "Bien";
            }
            else if (checkedId == R.id.radio4) {
                oppc_2 = "Regular";
            }
            else if (checkedId == R.id.radio5) {
                oppc_2 = "Mal";
            }
            else if (checkedId == R.id.radio0) {
                oppc_2 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPreguntapc_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                oppc_3 = "0";
            }
            else if (checkedId == R.id.radio2) {
                oppc_3 = "pc_3";
            }
            else if (checkedId == R.id.radio3) {
                oppc_3 = "Bien";
            }
            else if (checkedId == R.id.radio4) {
                oppc_3 = "Regular";
            }
            else if (checkedId == R.id.radio5) {
                oppc_3 = "Mal";
            }
            else if (checkedId == R.id.radio0) {
                oppc_3 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPreguntapc_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                oppc_4 = "0";
            }
            else if (checkedId == R.id.radio2) {
                oppc_4 = "pc_4";
            }
            else if (checkedId == R.id.radio3) {
                oppc_4 = "Bien";
            }
            else if (checkedId == R.id.radio4) {
                oppc_4 = "Regular";
            }
            else if (checkedId == R.id.radio5) {
                oppc_4 = "Mal";
            }
            else if (checkedId == R.id.radio0) {
                oppc_4 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op8 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op8 = "8";
            }
            else if (checkedId == R.id.radio3) {
                op8 = "De acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op8 = "Ni de acuerdo, ni en desacuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op8 = "En desacuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op8 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op9 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op9 = "9";
            }
            else if (checkedId == R.id.radio3) {
                op9 = "De acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op9 = "Ni de acuerdo, ni en desacuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op9 = "En desacuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op9 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op10 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op10 = "10";
            }
            else if (checkedId == R.id.radio3) {
                op10 = "De acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op10 = "Ni de acuerdo, ni en desacuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op10 = "En desacuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op10 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta11.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op11 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op11 = "11";
            }
            else if (checkedId == R.id.radio3) {
                op11 = "De acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op11 = "Ni de acuerdo, ni en desacuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op11 = "En desacuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op11 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta12.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op12 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op12 = "12";
            }
//            else if (checkedId == R.id.radio3) {
//                op12 = "Combo de alcaldes (aparecerá de acuerdo a la sección electoral)";
//            }
            else if (checkedId == R.id.radio4) {
                op12 = "Correcto";
                spinner12.setSelection(0);
            }
            else if (checkedId == R.id.radio5) {
                op12 = "Incorrecto";
                spinner12.setSelection(0);
            }
            else if (checkedId == R.id.radio0) {
                op12 = "No sabe / No contestó";
                spinner12.setSelection(0);
            }
        }
    });


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta13.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op13 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op13 = "13";
            }
            else if (checkedId == R.id.radio3) {
                op13 = "Sí";
            }
            else if (checkedId == R.id.radio4) {
                op13 = "No";
            }
            else if (checkedId == R.id.radio0) {
                op13 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta14.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op14 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op14 = "14";
            }
            else if (checkedId == R.id.radio3) {
                op14 = "Totalmente";
            }
            else if (checkedId == R.id.radio4) {
                op14 = "Bastante";
            }
            else if (checkedId == R.id.radio5) {
                op14 = "Poco";
            }
            else if (checkedId == R.id.radio6) {
                op14 = "Nada/ no va a votar";
            }
            else if (checkedId == R.id.radio0) {
                op14 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta15.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op15 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op15 = "15";
            }
            else if (checkedId == R.id.radio3) {
                op15 = "Totalmente";
            }
            else if (checkedId == R.id.radio4) {
                op15 = "Bastante";
            }
            else if (checkedId == R.id.radio5) {
                op15 = "Poco";
            }
            else if (checkedId == R.id.radio6) {
                op15 = "Nada/ no va a votar";
            }
            else if (checkedId == R.id.radio0) {
                op15 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta16.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op16 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op16 = "16";
            }
            else if (checkedId == R.id.radio3) {
                op16 = "Sí";
                lay16a .setVisibility(View.VISIBLE);	rdPregunta16a.clearCheck();	op16a="sin datos";
                lay16b .setVisibility(View.VISIBLE);	rdPregunta16b.clearCheck();	op16b="No aplica";
            }
            else if (checkedId == R.id.radio4) {
                op16 = "No";
                lay16a .setVisibility(View.GONE);	rdPregunta16a.clearCheck();	op16a="No aplica";
                lay16b .setVisibility(View.VISIBLE);	rdPregunta16b.clearCheck();	op16b="No aplica";
            }
            else if (checkedId == R.id.radio0) {
                op16 = "No sabe / No contestó";
                lay16a .setVisibility(View.GONE);	rdPregunta16a.clearCheck();	op16a="No aplica";
                lay16b .setVisibility(View.GONE);	rdPregunta16b.clearCheck();	op16b="No aplica";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta16a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op16a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op16a = "16a";
            }
            else if (checkedId == R.id.radio3) {
                op16a = "Morena";
            }
            else if (checkedId == R.id.radio4) {
                op16a = "PRI";
            }
            else if (checkedId == R.id.radio5) {
                op16a = "PAN";
            }
            else if (checkedId == R.id.radio6) {
                op16a = "PRD";
            }
            else if (checkedId == R.id.radio7) {
                op16a = "P.Verde";
            }
            else if (checkedId == R.id.radio8) {
                op16a = "Otro";
            }
            else if (checkedId == R.id.radio0) {
                op16a = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta16b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op16b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op16b = "16b";
            }
            else if (checkedId == R.id.radio3) {
                op16b = "Morena";
            }
            else if (checkedId == R.id.radio4) {
                op16b = "PRI";
            }
            else if (checkedId == R.id.radio5) {
                op16b = "PAN";
            }
            else if (checkedId == R.id.radio6) {
                op16b = "PRD";
            }
            else if (checkedId == R.id.radio7) {
                op16b = "P.Verde";
            }
            else if (checkedId == R.id.radio8) {
                op16b = "Otro";
            }
            else if (checkedId == R.id.radio0) {
                op16b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta17.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op17 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op17 = "17";
            }
            else if (checkedId == R.id.radio3) {
                op17 = "Priista";
            }
            else if (checkedId == R.id.radio4) {
                op17 = "Morenista";
            }
            else if (checkedId == R.id.radio5) {
                op17 = "Panista";
            }
            else if (checkedId == R.id.radio6) {
                op17 = "Perredista";
            }
            else if (checkedId == R.id.radio7) {
                op17 = "PT";
            }
            else if (checkedId == R.id.radio8) {
                op17 = "Partido Verde";
            }
            else if (checkedId == R.id.radio9) {
                op17 = "Muy perredista";
            }
            else if (checkedId == R.id.radio10) {
                op17 = "De ningún partido";
            }
            else if (checkedId == R.id.radio0) {
                op17 = "No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta17a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op17a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op17a = "17a";
            }
            else if (checkedId == R.id.radio3) {
                op17a = "Completamente seguro";
            }
            else if (checkedId == R.id.radio4) {
                op17a = "Pudiera cambiar de opción";
            }
            else if (checkedId == R.id.radio0) {
                op17a = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta18.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op18 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op18 = "18";
            }
            else if (checkedId == R.id.radio3) {
                op18 = "Morena";
            }
            else if (checkedId == R.id.radio4) {
                op18 = "PRI";
            }
            else if (checkedId == R.id.radio5) {
                op18 = "PAN";
            }
            else if (checkedId == R.id.radio6) {
                op18 = "PRD";
            }
            else if (checkedId == R.id.radio7) {
                op18 = "PT";
            }
            else if (checkedId == R.id.radio8) {
                op18 = "P.Verde";
            }
            else if (checkedId == R.id.radio9) {
                op18 = "Movimiento Ciudadano";
            }
            else if (checkedId == R.id.radio0) {
                op18 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta19.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op19 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op19 = "19";
            }
            else if (checkedId == R.id.radio3) {
                op19 = "Claudia Sheinbaum/ Morena/ PT/ Encuentro social";
                lay19a .setVisibility(View.VISIBLE);	rdPregunta19a.clearCheck();	op19a="sin datos";
            }
            else if (checkedId == R.id.radio4) {
                op19 = "Alejandra Barrales/ PAN/ PRD/ MC";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio5) {
                op19 = "Mikel Arriola/ PRI";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio6) {
                op19 = "Mariana Boy/ PVEM";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio7) {
                op19 = "Lorena Osornio/ Independiente";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio8) {
                op19 = "Marco Rascón/ PHCDMX";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio9) {
                op19 = "Purificación Carpinteyro/ PANAL";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio10) {
                op19 = "No voto";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio11) {
                op19 = "Anuló su voto";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
            else if (checkedId == R.id.radio0) {
                op19 = "No sabe / No contestó";
                lay19a .setVisibility(View.GONE);	rdPregunta19a.clearCheck();	op19a="No aplica";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta19a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op19a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op19a = "19a";
            }
            else if (checkedId == R.id.radio3) {
                op19a = "Si, fue la mejor";
            }
            else if (checkedId == R.id.radio4) {
                op19a = "En parte";
            }
            else if (checkedId == R.id.radio5) {
                op19a = "No fue la mejor";
            }
            else if (checkedId == R.id.radio0) {
                op19a = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta19b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op19b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op19b = "19b";
            }
            else if (checkedId == R.id.radio3) {
                op19b = "Bien";
            }
            else if (checkedId == R.id.radio4) {
                op19b = "Regular";
            }
            else if (checkedId == R.id.radio5) {
                op19b = "Mal";
            }
            else if (checkedId == R.id.radio0) {
                op19b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta20.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op20 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op20 = "20";
            }
            else if (checkedId == R.id.radio3) {
                op20 = "Que continúe el mismo partido";
            }
            else if (checkedId == R.id.radio4) {
                op20 = "Que se alterne el partido que gobierna";
            }
            else if (checkedId == R.id.radio0) {
                op20 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta21.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op21 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op21 = "21";
            }
            else if (checkedId == R.id.radio3) {
                op21 = "Muy de acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op21 = "Algo de acuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op21 = "Poco de acuerdo";
            }
            else if (checkedId == R.id.radio6) {
                op21 = "Nada de acuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op21 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta22.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op22 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op22 = "22";
            }
            else if (checkedId == R.id.radio3) {
                op22 = "Muy de acuerdo";
            }
            else if (checkedId == R.id.radio4) {
                op22 = "Algo de acuerdo";
            }
            else if (checkedId == R.id.radio5) {
                op22 = "Poco de acuerdo";
            }
            else if (checkedId == R.id.radio6) {
                op22 = "Nada de acuerdo";
            }
            else if (checkedId == R.id.radio0) {
                op22 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta23.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op23 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op23 = "23";
            }
            else if (checkedId == R.id.radio3) {
                op23 = "Muy dispuesto";
            }
            else if (checkedId == R.id.radio4) {
                op23 = "Dispuesto";
            }
            else if (checkedId == R.id.radio5) {
                op23 = "Poco dispuesto";
            }
            else if (checkedId == R.id.radio6) {
                op23 = "Nada dispuesto";
            }
            else if (checkedId == R.id.radio0) {
                op23 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    rdPregunta24.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            if (checkedId == R.id.radio1) {
//                op24 = "0";
//            }
//            else if (checkedId == R.id.radio2) {
//                op24 = "24";
//            }
//        }
//    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_1 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_1 = "24_1";
            }
            else if (checkedId == R.id.radio3) {
                op24_1 = "No";
                lay24_1a .setVisibility(View.GONE);	rdPregunta24_1a.clearCheck();	op24_1a="No aplica";
                lay24_1b .setVisibility(View.GONE);	rdPregunta24_1b.clearCheck();	op24_1b="No aplica";

            }
            else if (checkedId == R.id.radio4) {
                op24_1 = "Si";
                lay24_1a .setVisibility(View.VISIBLE);	rdPregunta24_1a.clearCheck();	op24_1a="sin datos";
                lay24_1b .setVisibility(View.VISIBLE);	rdPregunta24_1b.clearCheck();	op24_1b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_1a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_1a = "24_1a";
            }
            else if (checkedId == R.id.radio3) {
                op24_1a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op24_1a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op24_1a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op24_1a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op24_1a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op24_1a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_1b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_1b = "24_1b";
            }
            else if (checkedId == R.id.radio3) {
                op24_1b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op24_1b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op24_1b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_2 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_2 = "24_2";
            }
            else if (checkedId == R.id.radio3) {
                op24_2 = "No";
                lay24_2a .setVisibility(View.GONE);	rdPregunta24_2a.clearCheck();	op24_2a="sin datos";
                lay24_2b .setVisibility(View.GONE);	rdPregunta24_2b.clearCheck();	op24_2b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op24_2 = "Si";
                lay24_2a .setVisibility(View.VISIBLE);	rdPregunta24_2a.clearCheck();	op24_2a="sin datos";
                lay24_2b .setVisibility(View.VISIBLE);	rdPregunta24_2b.clearCheck();	op24_2b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_2a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_2a = "24_2a";
            }
            else if (checkedId == R.id.radio3) {
                op24_2a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op24_2a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op24_2a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op24_2a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op24_2a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op24_2a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_2b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_2b = "24_2b";
            }
            else if (checkedId == R.id.radio3) {
                op24_2b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op24_2b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op24_2b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_3 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_3 = "24_3";
            }
            else if (checkedId == R.id.radio3) {
                op24_3 = "No";
                lay24_3a .setVisibility(View.GONE);	rdPregunta24_3a.clearCheck();	op24_3a="sin datos";
                lay24_3b .setVisibility(View.GONE);	rdPregunta24_3b.clearCheck();	op24_3b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op24_3 = "Si";
                lay24_3a .setVisibility(View.VISIBLE);	rdPregunta24_3a.clearCheck();	op24_3a="sin datos";
                lay24_3b .setVisibility(View.VISIBLE);	rdPregunta24_3b.clearCheck();	op24_3b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_3a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_3a = "24_3a";
            }
            else if (checkedId == R.id.radio3) {
                op24_3a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op24_3a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op24_3a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op24_3a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op24_3a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op24_3a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta24_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op24_3b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op24_3b = "24_3b";
            }
            else if (checkedId == R.id.radio3) {
                op24_3b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op24_3b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op24_3b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta25.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op25 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op25 = "25";
            }
            else if (checkedId == R.id.radio3) {
                op25 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op25 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op25 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op25 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op25 = "Otro";
            }
            else if (checkedId == R.id.radio0) {
                op25 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta26.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op26 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op26 = "26";
            }
            else if (checkedId == R.id.radio3) {
                op26 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op26 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op26 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op26 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op26 = "Otro";
            }
            else if (checkedId == R.id.radio8) {
                op26 = "Ninguno";
            }
            else if (checkedId == R.id.radio0) {
                op26 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    rdPregunta27.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            if (checkedId == R.id.radio1) {
//                op27 = "0";
//            }
//            else if (checkedId == R.id.radio2) {
//                op27 = "27";
//            }
//        }
//    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_1 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_1 = "27_1";
            }
            else if (checkedId == R.id.radio3) {
                op27_1 = "No";
                lay27_1a .setVisibility(View.GONE);	rdPregunta27_1a.clearCheck();	op27_1a="sin datos";
                lay27_1b .setVisibility(View.GONE);	rdPregunta27_1b.clearCheck();	op27_1b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op27_1 = "Si";
                lay27_1a .setVisibility(View.VISIBLE);	rdPregunta27_1a.clearCheck();	op27_1a="sin datos";
                lay27_1b .setVisibility(View.VISIBLE);	rdPregunta27_1b.clearCheck();	op27_1b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_1a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_1a = "27_1a";
            }
            else if (checkedId == R.id.radio3) {
                op27_1a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op27_1a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op27_1a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op27_1a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op27_1a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op27_1a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_1b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_1b = "27_1b";
            }
            else if (checkedId == R.id.radio3) {
                op27_1b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op27_1b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op27_1b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_2 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_2 = "27_2";
            }
            else if (checkedId == R.id.radio3) {
                op27_2 = "No";
                lay27_2a .setVisibility(View.GONE);	rdPregunta27_2a.clearCheck();	op27_2a="sin datos";
                lay27_2b .setVisibility(View.GONE);	rdPregunta27_2b.clearCheck();	op27_2b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op27_2 = "Si";
                lay27_2a .setVisibility(View.VISIBLE);	rdPregunta27_2a.clearCheck();	op27_2a="sin datos";
                lay27_2b .setVisibility(View.VISIBLE);	rdPregunta27_2b.clearCheck();	op27_2b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_2a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_2a = "27_2a";
            }
            else if (checkedId == R.id.radio3) {
                op27_2a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op27_2a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op27_2a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op27_2a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op27_2a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op27_2a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_2b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_2b = "27_2b";
            }
            else if (checkedId == R.id.radio3) {
                op27_2b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op27_2b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op27_2b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_3 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_3 = "27_3";
            }
            else if (checkedId == R.id.radio3) {
                op27_3 = "No";
                lay27_3a .setVisibility(View.GONE);	rdPregunta27_3a.clearCheck();	op27_3a="sin datos";
                lay27_3b .setVisibility(View.GONE);	rdPregunta27_3b.clearCheck();	op27_3b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op27_3 = "Si";
                lay27_3a .setVisibility(View.VISIBLE);	rdPregunta27_3a.clearCheck();	op27_3a="sin datos";
                lay27_3b .setVisibility(View.VISIBLE);	rdPregunta27_3b.clearCheck();	op27_3b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_3a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_3a = "27_3a";
            }
            else if (checkedId == R.id.radio3) {
                op27_3a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op27_3a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op27_3a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op27_3a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op27_3a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op27_3a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta27_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op27_3b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op27_3b = "27_3b";
            }
            else if (checkedId == R.id.radio3) {
                op27_3b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op27_3b = "Si";
            }
            else if (checkedId == R.id.radio5) {
                op27_3b = "No sabe / No contestó";
            }
            else if (checkedId == R.id.radio0) {
                op27_3b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta28.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op28 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op28 = "28";
            }
            else if (checkedId == R.id.radio3) {
                op28 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op28 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op28 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op28 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op28 = "Otro";
            }
            else if (checkedId == R.id.radio0) {
                op28 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta29.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op29 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op29 = "29";
            }
            else if (checkedId == R.id.radio3) {
                op29 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op29 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op29 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op29 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op29 = "Otro";
            }
            else if (checkedId == R.id.radio8) {
                op29 = "Ninguno";
            }
            else if (checkedId == R.id.radio0) {
                op29 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    rdPregunta30.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            if (checkedId == R.id.radio1) {
//                op30 = "0";
//            }
//            else if (checkedId == R.id.radio2) {
//                op30 = "30";
//            }
//        }
//    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_1 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_1 = "30_1";
            }
            else if (checkedId == R.id.radio3) {
                op30_1 = "No";
                lay30_1a .setVisibility(View.GONE);	rdPregunta30_1a.clearCheck();	op30_1a="sin datos";
                lay30_1b .setVisibility(View.GONE);	rdPregunta30_1b.clearCheck();	op30_1b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op30_1 = "Si";
                lay30_1a .setVisibility(View.VISIBLE);	rdPregunta30_1a.clearCheck();	op30_1a="sin datos";
                lay30_1b .setVisibility(View.VISIBLE);	rdPregunta30_1b.clearCheck();	op30_1b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_1a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_1a = "30_1a";
            }
            else if (checkedId == R.id.radio3) {
                op30_1a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op30_1a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op30_1a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op30_1a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op30_1a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op30_1a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_1b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_1b = "30_1b";
            }
            else if (checkedId == R.id.radio3) {
                op30_1b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op30_1b = "Si";
            }
            else if (checkedId == R.id.radio5) {
                op30_1b = "No sabe / No contestó";
            }
            else if (checkedId == R.id.radio0) {
                op30_1b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_2 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_2 = "30_2";
            }
            else if (checkedId == R.id.radio3) {
                op30_2 = "No";
                lay30_2a .setVisibility(View.GONE);	rdPregunta30_2a.clearCheck();	op30_2a="sin datos";
                lay30_2b .setVisibility(View.GONE);	rdPregunta30_2b.clearCheck();	op30_2b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op30_2 = "Si";
                lay30_2a .setVisibility(View.VISIBLE);	rdPregunta30_2a.clearCheck();	op30_2a="sin datos";
                lay30_2b .setVisibility(View.VISIBLE);	rdPregunta30_2b.clearCheck();	op30_2b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_2a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_2a = "30_2a";
            }
            else if (checkedId == R.id.radio3) {
                op30_2a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op30_2a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op30_2a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op30_2a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op30_2a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op30_2a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_2b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_2b = "30_2b";
            }
            else if (checkedId == R.id.radio3) {
                op30_2b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op30_2b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op30_2b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_3 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_3 = "30_3";
            }
            else if (checkedId == R.id.radio3) {
                op30_3 = "No";
                lay30_3a .setVisibility(View.GONE);	rdPregunta30_3a.clearCheck();	op30_3a="sin datos";
                lay30_3b .setVisibility(View.GONE);	rdPregunta30_3b.clearCheck();	op30_3b="sin datos";

            }
            else if (checkedId == R.id.radio4) {
                op30_3 = "Si";
                lay30_3a .setVisibility(View.VISIBLE);	rdPregunta30_3a.clearCheck();	op30_3a="sin datos";
                lay30_3b .setVisibility(View.VISIBLE);	rdPregunta30_3b.clearCheck();	op30_3b="sin datos";

            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_3a = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_3a = "30_3a";
            }
            else if (checkedId == R.id.radio3) {
                op30_3a = "Muy buena";
            }
            else if (checkedId == R.id.radio4) {
                op30_3a = "buena";
            }
            else if (checkedId == R.id.radio5) {
                op30_3a = "regular";
            }
            else if (checkedId == R.id.radio6) {
                op30_3a = "mala";
            }
            else if (checkedId == R.id.radio7) {
                op30_3a = "muy mala";
            }
            else if (checkedId == R.id.radio8) {
                op30_3a = "conoce sin opinión";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta30_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op30_3b = "0";
            }
            else if (checkedId == R.id.radio2) {
                op30_3b = "30_3b";
            }
            else if (checkedId == R.id.radio3) {
                op30_3b = "No";
            }
            else if (checkedId == R.id.radio4) {
                op30_3b = "Si";
            }
            else if (checkedId == R.id.radio0) {
                op30_3b = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta31.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op31 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op31 = "31";
            }
            else if (checkedId == R.id.radio3) {
                op31 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op31 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op31 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op31 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op31 = "Otro";
            }
            else if (checkedId == R.id.radio0) {
                op31 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    rdPregunta32.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radio1) {
                op32 = "0";
            }
            else if (checkedId == R.id.radio2) {
                op32 = "32";
            }
            else if (checkedId == R.id.radio3) {
                op32 = "Candidato 1";
            }
            else if (checkedId == R.id.radio4) {
                op32 = "Candidato 2";
            }
            else if (checkedId == R.id.radio5) {
                op32 = "Candidato 3";
            }
            else if (checkedId == R.id.radio6) {
                op32 = "Candidato 4";
            }
            else if (checkedId == R.id.radio7) {
                op32 = "Otro";
            }
            else if (checkedId == R.id.radio8) {
                op32 = "Ninguno";
            }
            else if (checkedId == R.id.radio0) {
                op32 = "No sabe / No contestó";
            }
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////// SOCIOECONOMICOS  ///////////////////////////////////// //////////////////////////////////////////////////////////////////////

//        rdPreguntaHijos.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if (checkedId == R.id.radio1) {
//                    opHijos = "Si";
//                } else if (checkedId == R.id.radio2) {
//                    opHijos = "No";
//                } else if (checkedId == R.id.radio0) {
//                    opHijos = "No sabe / No contestó";
//                } else {
//                    opHijos = "";
//
//                }
//
//            }
//        });

rdPreguntaAporta.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opAporta = "No completó primaria";
        } else if (checkedId == R.id.radio2) {
            opAporta = "Primaria o secundaria";
        } else if (checkedId == R.id.radio3) {
            opAporta = "Preparatoria o carrera técnica";
        } else if (checkedId == R.id.radio4) {
            opAporta = "Licenciatura";
        } else if (checkedId == R.id.radio5) {
            opAporta = "Posgrado";
        } else if (checkedId == R.id.radio0) {
            opAporta = "No contestó";
        } else {
            opAporta = "";

        }

    }
});

rdPreguntaOcupacion.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opOcupacion = "Hogar";
        } else if (checkedId == R.id.radio2) {
            opOcupacion = "Estudiante";
        } else if (checkedId == R.id.radio3) {
            opOcupacion = "Profesionista";
        } else if (checkedId == R.id.radio4) {
            opOcupacion = "Empleado";
        } else if (checkedId == R.id.radio5) {
            opOcupacion = "Obrero / oficio";
        } else if (checkedId == R.id.radio6) {
            opOcupacion = "Comerciante";
        } else if (checkedId == R.id.radio7) {
            opOcupacion = "Jubilado";
        } else if (checkedId == R.id.radio8) {
            opOcupacion = "Otro";
        } else if (checkedId == R.id.radio9) {
            opOcupacion = "Desempleado";
        } else if (checkedId == R.id.radio0) {
            opOcupacion = "No contestó";
        } else {
            opOcupacion = "";

        }

    }
});

//    rdPreguntaCoche.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            if (checkedId == R.id.radio1) {
//                opCoche = "Si";
//                layCuantosCoches.setVisibility(View.VISIBLE);
//                opCuantosCoches = "sin datos";
//            } else if (checkedId == R.id.radio2) {
//                opCoche = "No";
//                layCuantosCoches.setVisibility(View.GONE);
//                rdPreguntaCuantosCoches.clearCheck();
//                opCuantosCoches = "Ninguno";
//
//            } else if (checkedId == R.id.radio0) {
//                opCoche = "No contestó";
//                layCuantosCoches.setVisibility(View.GONE);
//                rdPreguntaCuantosCoches.clearCheck();
//                opCuantosCoches = "Ninguno";
//
//            } else {
//                opCoche = "";
//
//            }
//
//        }
//    });


rdPreguntaCuantosCoches.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opCuantosCoches = "Ninguno";
        } else if (checkedId == R.id.radio2) {
            opCuantosCoches = "Uno";
        } else if (checkedId == R.id.radio3) {
            opCuantosCoches = "Dos";
        } else if (checkedId == R.id.radio4) {
            opCuantosCoches = "Tres";
        } else if (checkedId == R.id.radio5) {
            opCuantosCoches = "Cuatro o más";
        } else if (checkedId == R.id.radio0) {
            opCuantosCoches = "No contestó";
        } else {
            opCuantosCoches = "";

        }

    }
});

rdPreguntaCuartos.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opCuartos = "1";
        } else if (checkedId == R.id.radio2) {
            opCuartos = "2";
        } else if (checkedId == R.id.radio3) {
            opCuartos = "3";
        } else if (checkedId == R.id.radio4) {
            opCuartos = "4";
        } else if (checkedId == R.id.radio5) {
            opCuartos = "5";
        } else if (checkedId == R.id.radio6) {
            opCuartos = "6";
        } else if (checkedId == R.id.radio7) {
            opCuartos = "7";
        } else if (checkedId == R.id.radio8) {
            opCuartos = "8";
        } else if (checkedId == R.id.radio9) {
            opCuartos = "9";
        } else if (checkedId == R.id.radio10) {
            opCuartos = "10";
        } else if (checkedId == R.id.radio0) {
            opCuartos = "No contestó";
        } else {
            opCuartos = "";

        }

    }
});

rdPreguntaCuartosDormir.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opCuartosDormir = "1";
        } else if (checkedId == R.id.radio2) {
            opCuartosDormir = "2";
        } else if (checkedId == R.id.radio3) {
            opCuartosDormir = "3";
        } else if (checkedId == R.id.radio4) {
            opCuartosDormir = "4";
        } else if (checkedId == R.id.radio5) {
            opCuartosDormir = "5";
        } else if (checkedId == R.id.radio6) {
            opCuartosDormir = "6";
        } else if (checkedId == R.id.radio7) {
            opCuartosDormir = "7";
        } else if (checkedId == R.id.radio8) {
            opCuartosDormir = "8";
        } else if (checkedId == R.id.radio9) {
            opCuartosDormir = "9";
        } else if (checkedId == R.id.radio10) {
            opCuartosDormir = "10";
        } else if (checkedId == R.id.radio0) {
            opCuartosDormir = "No contestó";
        } else {
            opCuartosDormir = "";

        }

    }
});

rdPreguntaFocos.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opFocos = "0 a 5";
        } else if (checkedId == R.id.radio2) {
            opFocos = "6 a 10";
        } else if (checkedId == R.id.radio3) {
            opFocos = "11 a 15";
        } else if (checkedId == R.id.radio4) {
            opFocos = "16 a 20";
        } else if (checkedId == R.id.radio5) {
            opFocos = "21 o más";
        } else if (checkedId == R.id.radio0) {
            opFocos = "No contestó";
        } else {
            opFocos = "";

        }

    }
});

rdPreguntaBanos.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opBanos = "Ninguno";
        } else if (checkedId == R.id.radio2) {
            opBanos = "Uno";
        } else if (checkedId == R.id.radio3) {
            opBanos = "Dos o Tres";
        } else if (checkedId == R.id.radio4) {
            opBanos = "Cuatro o más";
        } else if (checkedId == R.id.radio0) {
            opBanos = "No contestó";
        } else {
            opBanos = "";

        }

    }
});

rdPreguntaRegadera.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opRegadera = "Si";

        } else if (checkedId == R.id.radio2) {
            opRegadera = "No";
        } else if (checkedId == R.id.radio0) {
            opRegadera = "No contestó";
        } else {
            opRegadera = "";

        }

    }
});

//    rdPreguntaInternet.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//
//            if (checkedId == R.id.radio1) {
//                opInternet="Si";
//
//            }
//            else if (checkedId == R.id.radio2) {
//                opInternet="No";
//            }
//            else if (checkedId == R.id.radio0) {
//                opInternet="No sabe / no contestó";
//            }
//            else{
//                opInternet="";
//
//            }
//
//
//        }
//    });
//
//
//
//    rdPreguntaTrabajaron.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//
//            if (checkedId == R.id.radio1) {
//                opTrabajaron="Ninguno";
//
//            }
//            else if (checkedId == R.id.radio2) {
//                opTrabajaron="Uno";
//            }
//            else if (checkedId == R.id.radio3) {
//                opTrabajaron="Dos";
//            }
//            else if (checkedId == R.id.radio4) {
//                opTrabajaron="Tres";
//            }
//            else if (checkedId == R.id.radio5) {
//                opTrabajaron="Cuatro o más";
//            }
//            else if (checkedId == R.id.radio0) {
//                opTrabajaron="No sabe / no contestó";
//            }
//            else{
//                opTrabajaron="";
//
//            }
//
//
//        }
//    });


rdPreguntaEstufa.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opEstufa = "Si";

        } else if (checkedId == R.id.radio2) {
            opEstufa = "No";
        } else if (checkedId == R.id.radio0) {
            opEstufa = "No contestó";
        } else {
            opEstufa = "";

        }

    }
});

rdPreguntaEdad.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opEdad = "Menor de 18 años";
        } else if (checkedId == R.id.radio2) {
            opEdad = "18 a 29";
        } else if (checkedId == R.id.radio3) {
            opEdad = "30 a 39";
        } else if (checkedId == R.id.radio4) {
            opEdad = "40 a 49";
        } else if (checkedId == R.id.radio5) {
            opEdad = "50 a 59";
        } else if (checkedId == R.id.radio6) {
            opEdad = "60 a 69";
        } else if (checkedId == R.id.radio7) {
            opEdad = "70 o más";
        } else if (checkedId == R.id.radio0) {
            opEdad = "No contestó";
        } else {
            opEdad = "";

        }

    }
});

rdPreguntaGenero.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opGenero = "Masculino";


        } else if (checkedId == R.id.radio2) {
            opGenero = "Femenino";


        }

    }
});

rdPreguntaTipoVivienda.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opTipoVivienda = "Casa";
        } else if (checkedId == R.id.radio2) {
            opTipoVivienda = "Condominio";
        } else if (checkedId == R.id.radio0) {
            opTipoVivienda = "No contestó";
        } else {
            opTipoVivienda = "";

        }

    }
});
//
rdPreguntaTipoPiso.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio1) {
            opTipoPiso = "Tierra o cemento";
        } else if (checkedId == R.id.radio2) {
            opTipoPiso = "Cualquier otro";
        } else if (checkedId == R.id.radio0) {
            opTipoPiso = "No contestó";
        } else {
            opTipoPiso = "";

        }

    }
});

rdPreguntaAbandono.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == R.id.radio_abandono1) {
            opAbandono = "1";
            tipoEncuesta = "NORMAL";
            btnGuardar.setText("Guardar Normal");
        } else if (checkedId == R.id.radio_abandono2) {
            opAbandono = "2";
            tipoEncuesta = "ABANDONO";
            btnGuardar.setText("Guardar Abandono");
        } else if (checkedId == R.id.radio_abandono3) {
            opAbandono = "3";
            tipoEncuesta = "RECHAZO";
            btnGuardar.setText("Guardar Rechazo");
        } else if (checkedId == R.id.radio_abandono4) {
            opAbandono = "4";
            tipoEncuesta = "FILTRO";
            btnGuardar.setText("Guardar Filtro");
        }


    }
});

}

////// FIN ONCREATE/////////////////////////////

@Override
protected void onPause() {
    super.onPause();

}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class CierraEncuesta extends TimerTask {

    @Override
    public void run() {

        dialogoCierraEncuesta();

    }

}

public void drawResults() {
    for (CheckBox c : mChecks) {
        c.setChecked(mSelectedChecks.contains(c));
    }
}

public void drawResults2() {
    for (CheckBox d : mChecks2) {
        d.setChecked(mSelectedChecks2.contains(d));
    }
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}

public void valores() {

    String str = "";

    String seg = formattedDate5.substring(7);
    System.out.println("El segundo: " + seg);
    System.out.println("El IMEI" + sacaImei());

    String mes = formattedDate6.toString();
    System.out.println("El mes" + mes);

    String dia = formattedDate7.toString();
    System.out.println("El dia" + dia);

    sacaChip();

    cachaNombre();

    txtSeccion.setText(cachaSeccion());

    String strSecc = txtSeccion.getText().toString();
    String strId = String.valueOf(rand + 1);



    elMaximo = Integer.parseInt(sacaMaximo().toString()) + 1;

    String strText6;
    if(editPregunta6.getText().toString().trim().length()==0){
        strText6=op6;
    }else{
        strText6=editPregunta6.getText().toString().trim();
        rdPregunta6.clearCheck();
    }


    String strText7;
    if(editPregunta7.getText().toString().trim().length()==0){
        strText7=op7;
    }else{
        strText7=editPregunta7.getText().toString().trim();
        rdPregunta7.clearCheck();
    }


    String strText12;
    if(spinner12.getSelectedItem().toString().equals("Selecciona")){
        strText12=op12;
    }else{
        strText12=spinner12.getSelectedItem().toString();
        rdPregunta12.clearCheck();
    }

    String str1 = op1;
    String str2 = op2;
    String str3 = op3;
    String str4 = op4;
    String str5 = op5;
    String str6 = strText6;
    String str7 = strText7;
    String strpc = oppc;
    String strpc_1 = oppc_1;
    String strpc_2 = oppc_2;
    String strpc_3 = oppc_3;
    String strpc_4 = oppc_4;
    String str8 = op8;
    String str9 = op9;
    String str10 = op10;
    String str11 = op11;
    String str12 = strText12;
    String str13 = op13;
    String str14 = op14;
    String str15 = op15;
    String str16 = op16;
    String str16a = op16a;
    String str16b = op16b;
    String str17 = op17;
    String str17a = op17a;
    String str18 = op18;
    String str19 = op19;
    String str19a = op19a;
    String str19b = op19b;
    String str20 = op20;
    String str21 = op21;
    String str22 = op22;
    String str23 = op23;
    String str24 = op24;
    String str24_1 = op24_1;
    String str24_1a = op24_1a;
    String str24_1b = op24_1b;
    String str24_2 = op24_2;
    String str24_2a = op24_2a;
    String str24_2b = op24_2b;
    String str24_3 = op24_3;
    String str24_3a = op24_3a;
    String str24_3b = op24_3b;
    String str25 = op25;
    String str26 = op26;
    String str27 = op27;
    String str27_1 = op27_1;
    String str27_1a = op27_1a;
    String str27_1b = op27_1b;
    String str27_2 = op27_2;
    String str27_2a = op27_2a;
    String str27_2b = op27_2b;
    String str27_3 = op27_3;
    String str27_3a = op27_3a;
    String str27_3b = op27_3b;
    String str28 = op28;
    String str29 = op29;
    String str30 = op30;
    String str30_1 = op30_1;
    String str30_1a = op30_1a;
    String str30_1b = op30_1b;
    String str30_2 = op30_2;
    String str30_2a = op30_2a;
    String str30_2b = op30_2b;
    String str30_3 = op30_3;
    String str30_3a = op30_3a;
    String str30_3b = op30_3b;
    String str31 = op31;
    String str32 = op32;


    String strAporta = opAporta;
    String strOcupacion = opOcupacion;
    String strCuantosCoches = opCuantosCoches;
    String strCuartos = opCuartos;
    String strCuartosDormir = opCuartosDormir;
    String strFocos = opFocos;
    String strBanos = opBanos;
    String strRegadera = opRegadera;
    String strEstufa = opEstufa;
    String strEdad = opEdad;
    String strGenero = opGenero;
    String strTipoVivienda = opTipoVivienda;
    String strTipoPiso = opTipoPiso;
    String strAbandono = opAbandono;

    if (strAbandono.matches("1")) {
        tipoEncuesta = "NORMAL";
    }

    String strEstudios = opAporta;
    String strCocheCuantos = opCuantosCoches;
    String strFoco = opFocos;
    String strCuarto = opCuartos;
    String strBano = opBanos;
    String strRega = opRegadera;
    String strEstu = opEstufa;
    String strPiso = opTipoPiso;

// estudios
    if (strEstudios.matches("sin datos")) {
        strEstudios = "0";
    } else if (strEstudios.matches("No completó primaria")) {
        strEstudios = "0";
    } else if (strEstudios.matches("Primaria o secundaria")) {
        strEstudios = "22";
    } else if (strEstudios.matches("Preparatoria o carrera técnica")) {
        strEstudios = "38";
    } else if (strEstudios.matches("Licenciatura")) {
        strEstudios = "52";
    } else if (strEstudios.matches("Posgrado")) {
        strEstudios = "72";
    } else if (strEstudios.matches("No contestó")) {
        strEstudios = "0";
    } else if (strEstudios.matches("No aplica")) {
        strEstudios = "0";
    }
// coches
    if (strCocheCuantos.matches("sin datos")) {
        strCocheCuantos = "0";
    } else if (strCocheCuantos.matches("Ninguno")) {
        strCocheCuantos = "0";
    } else if (strCocheCuantos.matches("Uno")) {
        strCocheCuantos = "32";
    } else if (strCocheCuantos.matches("Dos")) {
        strCocheCuantos = "41";
    } else if (strCocheCuantos.matches("Tres")) {
        strCocheCuantos = "58";
    } else if (strCocheCuantos.matches("Cuatro o más")) {
        strCocheCuantos = "58";
    } else if (strCocheCuantos.matches("No aplica")) {
        strCocheCuantos = "0";
    } else if (strCocheCuantos.matches("No contestó")) {
        strCocheCuantos = "0";
    } else if (strCocheCuantos.matches("No aplica")) {
        strCocheCuantos = "0";
    }
// Focos
    if (strFoco.matches("sin datos")) {
        strFoco = "0";
    } else if (strFoco.matches("0 a 5")) {
        strFoco = "0";
    } else if (strFoco.matches("6 a 10")) {
        strFoco = "15";
    } else if (strFoco.matches("11 a 15")) {
        strFoco = "27";
    } else if (strFoco.matches("16 a 20")) {
        strFoco = "32";
    } else if (strFoco.matches("21 o más")) {
        strFoco = "46";
    } else if (strFoco.matches("No contestó")) {
        strFoco = "0";
    } else if (strFoco.matches("No aplica")) {
        strFoco = "0";
    }
// Cuartos
    if (strCuarto.matches("sin datos")) {
        strCuarto = "0";
    } else if (strCuarto.matches("1")) {
        strCuarto = "0";
    } else if (strCuarto.matches("2")) {
        strCuarto = "0";
    } else if (strCuarto.matches("3")) {
        strCuarto = "0";
    } else if (strCuarto.matches("4")) {
        strCuarto = "0";
    } else if (strCuarto.matches("5")) {
        strCuarto = "8";
    } else if (strCuarto.matches("6")) {
        strCuarto = "8";
    } else if (strCuarto.matches("7")) {
        strCuarto = "14";
    } else if (strCuarto.matches("8")) {
        strCuarto = "14";
    } else if (strCuarto.matches("9")) {
        strCuarto = "14";
    } else if (strCuarto.matches("10")) {
        strCuarto = "14";
    } else if (strCuarto.matches("No contestó")) {
        strCuarto = "0";
    } else if (strCuarto.matches("No aplica")) {
        strCuarto = "0";
    }
// Banos
    if (strBano.matches("sin datos")) {
        strBano = "0";
    } else if (strBano.matches("Ninguno")) {
        strBano = "0";
    } else if (strBano.matches("Uno")) {
        strBano = "13";
    } else if (strBano.matches("Dos o Tres")) {
        strBano = "31";
    } else if (strBano.matches("Cuatro o más")) {
        strBano = "48";
    } else if (strBano.matches("No contestó")) {
        strBano = "0";
    } else if (strBano.matches("No aplica")) {
        strBano = "0";
    }
// Regadera
    if (strRega.matches("sin datos")) {
        strRega = "0";
    } else if (strRega.matches("Si")) {
        strRega = "10";
    } else if (strRega.matches("No")) {
        strRega = "0";
    } else if (strRega.matches("No contestó")) {
        strRega = "0";
    } else if (strRega.matches("No aplica")) {
        strRega = "0";
    }
// Estufa
    if (strEstu.matches("sin datos")) {
        strEstu = "0";
    } else if (strEstu.matches("Si")) {
        strEstu = "20";
    } else if (strEstu.matches("No")) {
        strEstu = "0";
    } else if (strEstu.matches("No contestó")) {
        strEstu = "0";
    } else if (strEstu.matches("No aplica")) {
        strEstu = "0";
    }
// Piso
    if (strPiso.matches("sin datos")) {
        strPiso = "0";
    } else if (strPiso.matches("Tierra o cemento")) {
        strPiso = "0";
    } else if (strPiso.matches("Cualquier otro")) {
        strPiso = "11";
    } else if (strPiso.matches("No contestó")) {
        strPiso = "0";
    } else if (strPiso.matches("No aplica")) {
        strPiso = "0";
    }else if (strPiso.matches("No sabe / No contestó")) {
        strPiso = "0";
    }



    Integer estudios = Integer.valueOf(strEstudios);
    Integer coches = Integer.valueOf(strCocheCuantos);
    Integer focos = Integer.valueOf(strFoco);
    Integer cuartos = Integer.valueOf(strCuarto);
    Integer banos = Integer.valueOf(strBano);
    Integer regadera = Integer.valueOf(strRega);
    Integer estufa = Integer.valueOf(strEstu);
    Integer piso = Integer.valueOf(strPiso);

    Integer suma = (estudios + coches + focos + cuartos + banos + regadera + estufa + piso);

    String status = null;

    if (suma >= 0 && suma <= 32) {
        status = "E";
    } else if (suma >= 33 && suma <= 79) {
        status = "D";
    } else if (suma >= 80 && suma <= 104) {
        status = "D+";
    } else if (suma >= 105 && suma <= 127) {
        status = "C-";
    } else if (suma >= 128 && suma <= 154) {
        status = "C";
    } else if (suma >= 155 && suma <= 192) {
        status = "C+";
    } else if (suma >= 193) {
        status = "AB";
    }

    String strFinal = "\n";

// Clase que permite grabar texto en un archivo
    FileOutputStream fout = null;
    try {
// INSERTA EN LA BASE DE DATOS //

        final String F = "File dbfile";

        String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
        + sacaImei() + "";

// Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

        db = usdbh.getWritableDatabase();

// NORMAL
        Nombre nom = new Nombre();
        String nombreE = nom.nombreEncuesta();

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
        long consecutivoObtenido = 0;
        ContentValues values = new ContentValues();
        if (db != null) {

            values.put("consecutivo_diario", elMaximo);
            values.put("equipo", cachaEquipo().toUpperCase());
            values.put("usuario", cachaNombre().toUpperCase());
            values.put("nombre_encuesta", nombreE.toUpperCase());
            values.put("fecha", formattedDate1);
            values.put("hora", formattedDate5);
            values.put("imei", sacaImei());
            values.put("seccion", strSecc);
            values.put("latitud", strLatitud);
            values.put("longitud", strLongitud);

            values.put("pregunta_1",str1);
            values.put("pregunta_2",str2);
            values.put("pregunta_3",str3);
            values.put("pregunta_4",str4);
            values.put("pregunta_5",str5);
            values.put("pregunta_6",str6);
            values.put("pregunta_7",str7);
            values.put("pregunta_pc",strpc);
            values.put("pregunta_pc_1",strpc_1);
            values.put("pregunta_pc_2",strpc_2);
            values.put("pregunta_pc_3",strpc_3);
            values.put("pregunta_pc_4",strpc_4);
            values.put("pregunta_8",str8);
            values.put("pregunta_9",str9);
            values.put("pregunta_10",str10);
            values.put("pregunta_11",str11);
            values.put("pregunta_12",str12);
            values.put("pregunta_13",str13);
            values.put("pregunta_14",str14);
            values.put("pregunta_15",str15);
            values.put("pregunta_16",str16);
            values.put("pregunta_16a",str16a);
            values.put("pregunta_16b",str16b);
            values.put("pregunta_17",str17);
            values.put("pregunta_17a",str17a);
            values.put("pregunta_18",str18);
            values.put("pregunta_19",str19);
            values.put("pregunta_19a",str19a);
            values.put("pregunta_19b",str19b);
            values.put("pregunta_20",str20);
            values.put("pregunta_21",str21);
            values.put("pregunta_22",str22);
            values.put("pregunta_23",str23);
            values.put("pregunta_24",str24);
            values.put("pregunta_24_1",str24_1);
            values.put("pregunta_24_1a",str24_1a);
            values.put("pregunta_24_1b",str24_1b);
            values.put("pregunta_24_2",str24_2);
            values.put("pregunta_24_2a",str24_2a);
            values.put("pregunta_24_2b",str24_2b);
            values.put("pregunta_24_3",str24_3);
            values.put("pregunta_24_3a",str24_3a);
            values.put("pregunta_24_3b",str24_3b);
            values.put("pregunta_25",str25);
            values.put("pregunta_26",str26);
            values.put("pregunta_27",str27);
            values.put("pregunta_27_1",str27_1);
            values.put("pregunta_27_1a",str27_1a);
            values.put("pregunta_27_1b",str27_1b);
            values.put("pregunta_27_2",str27_2);
            values.put("pregunta_27_2a",str27_2a);
            values.put("pregunta_27_2b",str27_2b);
            values.put("pregunta_27_3",str27_3);
            values.put("pregunta_27_3a",str27_3a);
            values.put("pregunta_27_3b",str27_3b);
            values.put("pregunta_28",str28);
            values.put("pregunta_29",str29);
            values.put("pregunta_30",str30);
            values.put("pregunta_30_1",str30_1);
            values.put("pregunta_30_1a",str30_1a);
            values.put("pregunta_30_1b",str30_1b);
            values.put("pregunta_30_2",str30_2);
            values.put("pregunta_30_2a",str30_2a);
            values.put("pregunta_30_2b",str30_2b);
            values.put("pregunta_30_3",str30_3);
            values.put("pregunta_30_3a",str30_3a);
            values.put("pregunta_30_3b",str30_3b);
            values.put("pregunta_31",str31);
            values.put("pregunta_32",str32);


            values.put("aporta", strAporta);
            values.put("ocupacion", strOcupacion);
            values.put("cuantos_coches", strCuantosCoches);
            values.put("cuartos", strCuartos);
            values.put("cuartos_dormir", strCuartosDormir);
            values.put("focos", strFocos);
            values.put("banos", strBanos);
            values.put("regadera", strRegadera);
            values.put("estufa", strEstufa);
            values.put("edad", strEdad);
            values.put("genero", strGenero);
            values.put("tipo_vivienda", strTipoVivienda);
            values.put("tipo_piso", strTipoPiso);

            values.put("abandono", strAbandono.toUpperCase());

            values.put("suma", suma);
            values.put("status", status);

            values.put("tiempo", elTiempo());
            values.put("tipo_captura", tipoEncuesta);

            if (!verificaConexion(this)) {
                Toast.makeText(getBaseContext(),"Sin conexión",Toast.LENGTH_LONG).show();
                values.put("enviado", "0");
                db.insert("encuestas", null, values);
            }else{
                values.put("enviado", "1");
                consecutivoObtenido = db.insert("encuestas", null, values);
            }
        }
        db.close();

        values.put("consecutivo", consecutivoObtenido);

        guardaEncuestaWS(values);


        System.out.println("consecutivo_diario " + elMaximo);
        System.out.println("usuario " + cachaNombre().toUpperCase());
        System.out.println("nombre_encuesta " + nombreE.toUpperCase());
        System.out.println("fecha " + formattedDate1);
        System.out.println("hora " + formattedDate5);
        System.out.println("imei " + sacaImei());
        System.out.println("Seccion " + str);
        System.out.println("Latitud  " + strLatitud);
        System.out.println("Longitud  " + strLongitud);

        System.out.println("pregunta_1  " +   str1);
        System.out.println("pregunta_2  " +   str2);
        System.out.println("pregunta_3  " +   str3);
        System.out.println("pregunta_4  " +   str4);
        System.out.println("pregunta_5  " +   str5);
        System.out.println("pregunta_6  " +   str6);
        System.out.println("pregunta_7  " +   str7);
        System.out.println("pregunta_pc  " +   strpc);
        System.out.println("pregunta_pc_1  " +   strpc_1);
        System.out.println("pregunta_pc_2  " +   strpc_2);
        System.out.println("pregunta_pc_3  " +   strpc_3);
        System.out.println("pregunta_pc_4  " +   strpc_4);
        System.out.println("pregunta_8  " +   str8);
        System.out.println("pregunta_9  " +   str9);
        System.out.println("pregunta_10  " +   str10);
        System.out.println("pregunta_11  " +   str11);
        System.out.println("pregunta_12  " +   str12);
        System.out.println("pregunta_13  " +   str13);
        System.out.println("pregunta_14  " +   str14);
        System.out.println("pregunta_15  " +   str15);
        System.out.println("pregunta_16  " +   str16);
        System.out.println("pregunta_16a  " +   str16a);
        System.out.println("pregunta_16b  " +   str16b);
        System.out.println("pregunta_17  " +   str17);
        System.out.println("pregunta_17a  " +   str17a);
        System.out.println("pregunta_18  " +   str18);
        System.out.println("pregunta_19  " +   str19);
        System.out.println("pregunta_19a  " +   str19a);
        System.out.println("pregunta_19b  " +   str19b);
        System.out.println("pregunta_20  " +   str20);
        System.out.println("pregunta_21  " +   str21);
        System.out.println("pregunta_22  " +   str22);
        System.out.println("pregunta_23  " +   str23);
        System.out.println("pregunta_24  " +   str24);
        System.out.println("pregunta_24_1  " +   str24_1);
        System.out.println("pregunta_24_1a  " +   str24_1a);
        System.out.println("pregunta_24_1b  " +   str24_1b);
        System.out.println("pregunta_24_2  " +   str24_2);
        System.out.println("pregunta_24_2a  " +   str24_2a);
        System.out.println("pregunta_24_2b  " +   str24_2b);
        System.out.println("pregunta_24_3  " +   str24_3);
        System.out.println("pregunta_24_3a  " +   str24_3a);
        System.out.println("pregunta_24_3b  " +   str24_3b);
        System.out.println("pregunta_25  " +   str25);
        System.out.println("pregunta_26  " +   str26);
        System.out.println("pregunta_27  " +   str27);
        System.out.println("pregunta_27_1  " +   str27_1);
        System.out.println("pregunta_27_1a  " +   str27_1a);
        System.out.println("pregunta_27_1b  " +   str27_1b);
        System.out.println("pregunta_27_2  " +   str27_2);
        System.out.println("pregunta_27_2a  " +   str27_2a);
        System.out.println("pregunta_27_2b  " +   str27_2b);
        System.out.println("pregunta_27_3  " +   str27_3);
        System.out.println("pregunta_27_3a  " +   str27_3a);
        System.out.println("pregunta_27_3b  " +   str27_3b);
        System.out.println("pregunta_28  " +   str28);
        System.out.println("pregunta_29  " +   str29);
        System.out.println("pregunta_30  " +   str30);
        System.out.println("pregunta_30_1  " +   str30_1);
        System.out.println("pregunta_30_1a  " +   str30_1a);
        System.out.println("pregunta_30_1b  " +   str30_1b);
        System.out.println("pregunta_30_2  " +   str30_2);
        System.out.println("pregunta_30_2a  " +   str30_2a);
        System.out.println("pregunta_30_2b  " +   str30_2b);
        System.out.println("pregunta_30_3  " +   str30_3);
        System.out.println("pregunta_30_3a  " +   str30_3a);
        System.out.println("pregunta_30_3b  " +   str30_3b);
        System.out.println("pregunta_31  " +   str31);
        System.out.println("pregunta_32  " +   str32);

        System.out.println(" aporta   " + strAporta);
        System.out.println(" ocupacion   " + strOcupacion);
        System.out.println(" cuantos_coches   " + strCuantosCoches);
        System.out.println(" cuartos   " + strCuartos);
        System.out.println(" cuartos_dormir   " + strCuartosDormir);
        System.out.println(" focos   " + strFocos);

        System.out.println(" baños   " + strBanos);
        System.out.println(" regadera   " + strRegadera);
        System.out.println(" estufa   " + strEstufa);
        System.out.println(" edad   " + strEdad);
        System.out.println(" genero   " + strGenero);
        System.out.println(" tipo_vivienda   " + strTipoVivienda);
        System.out.println(" tipo_piso   " + strTipoPiso);

        System.out.println("abandono  " + strAbandono);

        System.out.println("suma  " + suma);
        System.out.println("status  " + status);

// FIN INSERTA BASE DE DATOS //

    } catch (Exception e) {
        System.out.println("algo pasó...1");
    }

}

private void guardaEncuestaWS(ContentValues values){

    showProgress(true);

//RECORRE CONTENTVALUES
    DatoContent datoContent = new DatoContent();
    List<DatoContent> listaContenido = new ArrayList();
    Set<Map.Entry<String, Object>> s=values.valueSet();
    Iterator itr = s.iterator();
    while(itr.hasNext()) {
        Map.Entry me = (Map.Entry)itr.next();
        String key = me.getKey().toString();
        Object value =  me.getValue();

        datoContent = new DatoContent();
        datoContent.setKey(key);
        datoContent.setValue(String.valueOf(value));

        listaContenido.add(datoContent);
    }

    Gson gson  = new Gson();
    Type collectionType = new TypeToken<List<DatoContent>>() { }.getType();
    String json = gson.toJson(listaContenido,collectionType);

    RequestParams params = new RequestParams();
    params.put("api", "guarda_encuesta");
    params.put("encuesta", encuesta);
    params.put("data", json);

    Log.d(TAG, "pimc -----------> " + json);

    AsyncHttpClient client = new AsyncHttpClient();
    client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
//client.addHeader("Authorization", "Bearer " + usuario.getToken());
    client.setTimeout(60000);

    RequestHandle requestHandle = client.post(customURL, params, new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            showProgress(false);
            Log.d(TAG, "pimc -----------> Respuesta OK ");
            Log.d(TAG, "pimc -----------> " + new String(responseBody));
            try {


                String json = new String(responseBody);

                if (json != null && !json.isEmpty()) {

                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(json);
                    Log.d(TAG, "pimc -----------> Data: " + jsonObject.get("data"));

                    String login = jsonObject.getJSONObject("response").get("code").toString();
                    if (Integer.valueOf(login) == 1) {

/*JSONObject jsonUser = jsonObject.getJSONObject("data").getJSONObject("respuesta");
Type collectionType = new TypeToken<Usuario>() {}.getType();
usuario = gson.fromJson(jsonUser.toString(), collectionType);*/
//
//if(!opAbandono.equals("5")){
//    dialogo();
//}
/*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intent.putExtra("Nombre", encuestaQuien);
startActivity(intent);
finish();*/

                        dialogo();


} else {
    Toast.makeText(MainActivityPantalla1.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
}
}

} catch (Exception e) {
    showProgress(false);
    Log.e(TAG, e.getMessage());
    Toast.makeText(MainActivityPantalla1.this, "Error al subir los datos", Toast.LENGTH_SHORT).show();
}
}

@Override
public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
    showProgress(false);
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

    Toast.makeText(MainActivityPantalla1.this, "Error de conexion, Se guarda en la base interna", Toast.LENGTH_SHORT).show();
    btnGuardar.setEnabled(true);

    dialogo();

}
});


}

public void guardar(View v) {
    System.out.println(cachaDelegacion());

    timer.cancel();

    String str = "";

    if (opAbandono.matches("sin datos")) {
        opAbandono = "1";
    }

    int tipo = Integer.parseInt(opAbandono);

    switch (tipo) {
        case 1:

            if (lay1.getVisibility() == View.VISIBLE && op1.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura1,Toast.LENGTH_LONG).show();}
            else if (lay2.getVisibility() == View.VISIBLE && op2.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura2,Toast.LENGTH_LONG).show();}
            else if (lay3.getVisibility() == View.VISIBLE && op3.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura3,Toast.LENGTH_LONG).show();}
            else if (lay4.getVisibility() == View.VISIBLE && op4.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura4,Toast.LENGTH_LONG).show();}
            else if (lay5.getVisibility() == View.VISIBLE && op5.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura5,Toast.LENGTH_LONG).show();}
            else if (lay6.getVisibility() == View.VISIBLE && op6.matches("sin datos") && editPregunta6.getText().toString().trim().length() == 0 ){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura6,Toast.LENGTH_LONG).show();}
            else if (lay7.getVisibility() == View.VISIBLE && op7.matches("sin datos") && editPregunta7.getText().toString().trim().length() == 0 ){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura7,Toast.LENGTH_LONG).show();}
//            else if (laypc.getVisibility() == View.VISIBLE && oppc.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc,Toast.LENGTH_LONG).show();}
            else if (laypc_1.getVisibility() == View.VISIBLE && oppc_1.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc_1,Toast.LENGTH_LONG).show();}
            else if (laypc_2.getVisibility() == View.VISIBLE && oppc_2.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc_2,Toast.LENGTH_LONG).show();}
            else if (laypc_3.getVisibility() == View.VISIBLE && oppc_3.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc_3,Toast.LENGTH_LONG).show();}
            else if (laypc_4.getVisibility() == View.VISIBLE && oppc_4.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc_4,Toast.LENGTH_LONG).show();}
            else if (lay8.getVisibility() == View.VISIBLE && op8.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura8,Toast.LENGTH_LONG).show();}
            else if (lay9.getVisibility() == View.VISIBLE && op9.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura9,Toast.LENGTH_LONG).show();}
            else if (lay10.getVisibility() == View.VISIBLE && op10.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura10,Toast.LENGTH_LONG).show();}
            else if (lay11.getVisibility() == View.VISIBLE && op11.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura11,Toast.LENGTH_LONG).show();}
            else if (lay12.getVisibility() == View.VISIBLE && op12.matches("sin datos")&& spinner12.getSelectedItem().toString().equals("Selecciona")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura12,Toast.LENGTH_LONG).show();}
            else if (lay13.getVisibility() == View.VISIBLE && op13.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura13,Toast.LENGTH_LONG).show();}
            else if (lay14.getVisibility() == View.VISIBLE && op14.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura14,Toast.LENGTH_LONG).show();}
            else if (lay15.getVisibility() == View.VISIBLE && op15.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura15,Toast.LENGTH_LONG).show();}
            else if (lay16.getVisibility() == View.VISIBLE && op16.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura16,Toast.LENGTH_LONG).show();}
            else if (lay16a.getVisibility() == View.VISIBLE && op16a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura16a,Toast.LENGTH_LONG).show();}
            else if (lay16b.getVisibility() == View.VISIBLE && op16b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura16b,Toast.LENGTH_LONG).show();}
            else if (lay17.getVisibility() == View.VISIBLE && op17.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura17,Toast.LENGTH_LONG).show();}
            else if (lay17a.getVisibility() == View.VISIBLE && op17a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura17a,Toast.LENGTH_LONG).show();}
            else if (lay18.getVisibility() == View.VISIBLE && op18.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura18,Toast.LENGTH_LONG).show();}
            else if (lay19.getVisibility() == View.VISIBLE && op19.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura19,Toast.LENGTH_LONG).show();}
            else if (lay19a.getVisibility() == View.VISIBLE && op19a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura19a,Toast.LENGTH_LONG).show();}
            else if (lay19b.getVisibility() == View.VISIBLE && op19b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura19b,Toast.LENGTH_LONG).show();}
            else if (lay20.getVisibility() == View.VISIBLE && op20.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura20,Toast.LENGTH_LONG).show();}
            else if (lay21.getVisibility() == View.VISIBLE && op21.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura21,Toast.LENGTH_LONG).show();}
            else if (lay22.getVisibility() == View.VISIBLE && op22.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura22,Toast.LENGTH_LONG).show();}
            else if (lay23.getVisibility() == View.VISIBLE && op23.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura23,Toast.LENGTH_LONG).show();}
//            else if (lay24.getVisibility() == View.VISIBLE && op24.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24,Toast.LENGTH_LONG).show();}
            else if (lay24_1.getVisibility() == View.VISIBLE && op24_1.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_1,Toast.LENGTH_LONG).show();}
            else if (lay24_1a.getVisibility() == View.VISIBLE && op24_1a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_1a,Toast.LENGTH_LONG).show();}
            else if (lay24_1b.getVisibility() == View.VISIBLE && op24_1b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_1b,Toast.LENGTH_LONG).show();}
            else if (lay24_2.getVisibility() == View.VISIBLE && op24_2.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_2,Toast.LENGTH_LONG).show();}
            else if (lay24_2a.getVisibility() == View.VISIBLE && op24_2a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_2a,Toast.LENGTH_LONG).show();}
            else if (lay24_2b.getVisibility() == View.VISIBLE && op24_2b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_2b,Toast.LENGTH_LONG).show();}
            else if (lay24_3.getVisibility() == View.VISIBLE && op24_3.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_3,Toast.LENGTH_LONG).show();}
            else if (lay24_3a.getVisibility() == View.VISIBLE && op24_3a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_3a,Toast.LENGTH_LONG).show();}
            else if (lay24_3b.getVisibility() == View.VISIBLE && op24_3b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24_3b,Toast.LENGTH_LONG).show();}
            else if (lay25.getVisibility() == View.VISIBLE && op25.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura25,Toast.LENGTH_LONG).show();}
            else if (lay26.getVisibility() == View.VISIBLE && op26.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura26,Toast.LENGTH_LONG).show();}
//            else if (lay27.getVisibility() == View.VISIBLE && op27.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27,Toast.LENGTH_LONG).show();}
            else if (lay27_1.getVisibility() == View.VISIBLE && op27_1.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_1,Toast.LENGTH_LONG).show();}
            else if (lay27_1a.getVisibility() == View.VISIBLE && op27_1a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_1a,Toast.LENGTH_LONG).show();}
            else if (lay27_1b.getVisibility() == View.VISIBLE && op27_1b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_1b,Toast.LENGTH_LONG).show();}
            else if (lay27_2.getVisibility() == View.VISIBLE && op27_2.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_2,Toast.LENGTH_LONG).show();}
            else if (lay27_2a.getVisibility() == View.VISIBLE && op27_2a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_2a,Toast.LENGTH_LONG).show();}
            else if (lay27_2b.getVisibility() == View.VISIBLE && op27_2b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_2b,Toast.LENGTH_LONG).show();}
            else if (lay27_3.getVisibility() == View.VISIBLE && op27_3.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_3,Toast.LENGTH_LONG).show();}
            else if (lay27_3a.getVisibility() == View.VISIBLE && op27_3a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_3a,Toast.LENGTH_LONG).show();}
            else if (lay27_3b.getVisibility() == View.VISIBLE && op27_3b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27_3b,Toast.LENGTH_LONG).show();}
            else if (lay28.getVisibility() == View.VISIBLE && op28.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura28,Toast.LENGTH_LONG).show();}
            else if (lay29.getVisibility() == View.VISIBLE && op29.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura29,Toast.LENGTH_LONG).show();}
//            else if (lay30.getVisibility() == View.VISIBLE && op30.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30,Toast.LENGTH_LONG).show();}
            else if (lay30_1.getVisibility() == View.VISIBLE && op30_1.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_1,Toast.LENGTH_LONG).show();}
            else if (lay30_1a.getVisibility() == View.VISIBLE && op30_1a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_1a,Toast.LENGTH_LONG).show();}
            else if (lay30_1b.getVisibility() == View.VISIBLE && op30_1b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_1b,Toast.LENGTH_LONG).show();}
            else if (lay30_2.getVisibility() == View.VISIBLE && op30_2.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_2,Toast.LENGTH_LONG).show();}
            else if (lay30_2a.getVisibility() == View.VISIBLE && op30_2a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_2a,Toast.LENGTH_LONG).show();}
            else if (lay30_2b.getVisibility() == View.VISIBLE && op30_2b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_2b,Toast.LENGTH_LONG).show();}
            else if (lay30_3.getVisibility() == View.VISIBLE && op30_3.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_3,Toast.LENGTH_LONG).show();}
            else if (lay30_3a.getVisibility() == View.VISIBLE && op30_3a.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_3a,Toast.LENGTH_LONG).show();}
            else if (lay30_3b.getVisibility() == View.VISIBLE && op30_3b.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30_3b,Toast.LENGTH_LONG).show();}

            else if (opAporta.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaAporta, Toast.LENGTH_LONG).show();
            } else if (opOcupacion.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaOcupacion, Toast.LENGTH_LONG).show();
            } else if (layCuantosCoches.getVisibility() == View.VISIBLE && opCuantosCoches.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaCuantosCoches, Toast.LENGTH_LONG).show();
            } else if (opCuartos.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaCuartos, Toast.LENGTH_LONG).show();
            } else if (opCuartosDormir.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaCuartosDormir, Toast.LENGTH_LONG).show();
            } else if (opFocos.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaFocos, Toast.LENGTH_LONG).show();
            } else if (opBanos.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaBanos, Toast.LENGTH_LONG).show();
            } else if (opEstufa.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaEstufa, Toast.LENGTH_LONG).show();
            } else if (opEdad.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaEdad, Toast.LENGTH_LONG).show();
            } else if (opGenero.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaGenero, Toast.LENGTH_LONG).show();
            } else if (opTipoVivienda.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaTipoVivienda, Toast.LENGTH_LONG).show();
            } else if (opTipoPiso.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaTipoPiso, Toast.LENGTH_LONG).show();
            } else {

// para valor por default
            if (opAbandono.matches("sin datos")) {
                opAbandono = "1";
            }

            valores();
            btnGuardar.setEnabled(false);
//            dialogo();

            } // Finaliza else
            break;

            case 2:

                if (opGenero.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaGenero, Toast.LENGTH_LONG).show();
            }
            else {
                valores();
                btnGuardar.setEnabled(false);
//                dialogo();
            }

            break;

            case 3:

            if (opGenero.matches("sin datos")) {
                Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaGenero, Toast.LENGTH_LONG).show();
            }
            else {
                valores();
                btnGuardar.setEnabled(false);
//                dialogo();
            }
            break;
            }

}

public void Salir(View view) {
    finish();
}

private String sacaMaximo() {

    Set<String> set = new HashSet<String>();

    final String F = "File dbfile";

// Abrimos la base de datos 'DBUsuarios' en modo escritura
    String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
    + sacaImei() + "";
    usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

    db = usdbh.getReadableDatabase();

    String selectQuery = "SELECT count(*) FROM encuestas where fecha='" + formattedDate1 + "'";

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

private String sacaConsecutivo() {

    String consecutivo = null;

    Set<String> set = new HashSet<String>();

    final String F = "File dbfile";

// Abrimos la base de datos 'DBUsuarios' en modo escritura

    String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/Mis_archivos/" + nombreEncuesta + "_"
    + sacaImei() + "";
    usdbh = new UsuariosSQLiteHelper(this, "F", null, 1, DATABASE_NAME);

    db = usdbh.getReadableDatabase();

    String selectQuery = "SELECT count(*) FROM encuestas order by id desc limit 1";

    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
        do {

            consecutivo = cursor.getString(0);

        } while (cursor.moveToNext());
    }

    cursor.close();
    db.close();

    return consecutivo;
}

//	public void CargaSpinnerMeses() {
//		String var = "Selecciona";
//		if (var == null) {
//			var = "";
//		}
//		final String[] datos = new String[] { "" + var + "",
//				"Enero 2019",
//				"Febrero 2019",
//				"Marzo 2019",
//				"Abril 2019",
//				"Mayo 2019",
//				"Junio 2019",
//				"Julio 2019",
//				"Agosto 2019",
//				"Septiembre 2019",
//				"Octubre 2019",
//				"Noviembre 2019",
//				"Diciembre 2019",
//				"Enero 2020",
//				"Febrero 20"
//		};
//		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
//		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerMeses.setAdapter(adaptador);
//		spinnerMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//				rdPregunta42.clearCheck();
//
//			}
//
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}

    public void CargaSpinner12() {
        String var = "Selecciona";
        if (var == null) {
            var = "";
        }
        final String[] datos = new String[]{"" + var + "",
                "Layda Sansores",
                "Vidal Llerenas",
                "Santiago Taboada",
                "Manuel Negrete",
                "Adrián Rubalcava",
                "Néstor López Núñez",
                "Francisco Chiguil",
                "Armando Quintero",
                "Clara Brugada",
                "Patricia Ximena Ortiz Couturier",
                "Víctor Hugo Romo",
                "Octavio Rivero Villaseñor",
                "Raymundo Martínez Vite",
                "Patricia Elena Aceves Pastrana",
                "Julio César Moreno",
                "José Carlos Acosta Ruiz"
        };
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
                datos);
        adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        spinner12.setAdapter(adaptador);
        spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                rdPregunta12.clearCheck();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

public void CargaSpinnerEscala() {
    String var = "Selecciona";
    if (var == null) {
        var = "";
    }
    final String[] datos = new String[]{"" + var + "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    "No sabe / No contestó"};
    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
        datos);
    adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    spinnerCalifica.setAdapter(adaptador);
    spinnerCalifica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

            Log.i(TAG ,"cqs ----------->>"+spinnerCalifica.getSelectedItem().toString());
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
}

    public void CargaSpinnerc3a() {
        String var = "Selecciona";
        if (var == null) {
            var = "";
        }
        final String[] datos = new String[]{"" + var + "", "1", "2", "3", "4", "5", "6", "7","No sabe / No contestó"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
                datos);
        adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        spinnerc3a.setAdapter(adaptador);
        spinnerc3a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//
//                rdPreguntac3a.clearCheck();
//
//                String dia=spinnerc3a.getSelectedItem().toString();
//
//                if(dia.equals("Selecciona")){
//                    layc3b .setVisibility(View.GONE);	rdPreguntac3b.clearCheck();
//                }else{
//                    layc3b .setVisibility(View.VISIBLE);	rdPreguntac3b.clearCheck();
//
//                }
//


            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }





public void CargaSpinnerSemana() {
    String var = "Selecciona";
    if (var == null) {
        var = "";
    }
    final String[] datos = new String[]{"" + var + "", "Dictan sentencia de cadena perpetua al Chapo en EU",
    "Se presenta plan de acción para rescatar PEMEX", "Se harán subastas de joyas incautadas",
    "Derrame de ácido en el Mar de Cortés",
    "Detienen a presuntos culpables del asesinato de Norberto Ronquillo",
    "Asesinatos/ muertos/ secuestros sin especificar", "Robos/ asaltos/ inseguridad sin especificar"};
    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
        datos);
    adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
    spinnerSemana.setAdapter(adaptador);
    spinnerSemana.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

//				rdPregunta10.clearCheck();
//				editPregunta10.setText("");

        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
}

/////////////// SPINNER /////////////////
public void CargaSpinnerAlcaldia() {
    String var = "Selecciona";
    if (var == null) {
        var = "";
    }
    final String[] datos = new String[]{"" + var + "", "ALVARO OBREGON", "AZCAPOTZALCO", "BENITO JUAREZ",
    "COYOACAN", "CUAJIMALPA DE MORELOS", "CUAUHTEMOC", "GUSTAVO A. MADERO", "IZTACALCO", "IZTAPALAPA",
    "MAGDALENA CONTRERAS", "MIGUEL HIDALGO", "MILPA ALTA", "TLAHUAC", "TLALPAN", "VENUSTIANO CARRANZA",
    "XOCHIMILCO", "No sabe / No contestó"};
    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerDelegaciones.setAdapter(adaptador);
    spinnerDelegaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
}

//    public void CargaSpinnerAlcaldia() {
//        String var = "Selecciona";
//        if (var == null) {
//            var = "";
//        }
//        final String[] datos = new String[]{"" + var + "", "Álvaro Obregón", "Azcapotzalco", "Benito Juárez",
//                "Coyoacán", "Cuajimalpa de Morelos", "Cuauhtémoc", "Gustavo A. Madero", "Iztacalco", "Iztapalapa",
//                "La Magdalena Contreras", "Miguel Hidalgo", "Milpa Alta", "Tláhuac", "Tlalpan", "Venustiano Carranza",
//                "Xochimilco"};
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
//        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerAlcaldia.setAdapter(adaptador);
//        spinnerAlcaldia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//
//                String laDelegacion=spinnerAlcaldia.getSelectedItem().toString();
//
//                if (laDelegacion.matches("Álvaro Obregón")) {
//                    elDelegado = "Layda Sansores";
//                } else if (laDelegacion.matches("Azcapotzalco")) {
//                    elDelegado = "Vidal Llerenas";
//                } else if (laDelegacion.matches("Benito Juárez")) {
//                    elDelegado = "Santiago Taboada";
//                } else if (laDelegacion.matches("Coyoacán")) {
//                    elDelegado = "Manuel Negrete";
//                } else if (laDelegacion.matches("Cuajimalpa de Morelos")) {
//                    elDelegado = "Adrián Rubalcava";
//                } else if (laDelegacion.matches("Cuauhtémoc")) {
//                    elDelegado = "Néstor López Núñez";
//                } else if (laDelegacion.matches("Gustavo A. Madero")) {
//                    elDelegado = "Francisco Chiguil";
//                } else if (laDelegacion.matches("Iztacalco")) {
//                    elDelegado = "Armando Quintero";
//                } else if (laDelegacion.matches("Iztapalapa")) {
//                    elDelegado = "Clara Brugada";
//                } else if (laDelegacion.matches("La Magdalena Contreras")) {
//                    elDelegado = "Patricia Ximena Ortiz Couturier";
//                } else if (laDelegacion.matches("Miguel Hidalgo")) {
//                    elDelegado = "Víctor Hugo Romo";
//                } else if (laDelegacion.matches("Milpa Alta")) {
//                    elDelegado = "Octavio Rivero Villaseñor";
//                } else if (laDelegacion.matches("Tláhuac")) {
//                    elDelegado = "Raymundo Martínez Vite";
//                } else if (laDelegacion.matches("Tlalpan")) {
//                    elDelegado = "Patricia Elena Aceves Pastrana";
//                } else if (laDelegacion.matches("Venustiano Carranza")) {
//                    elDelegado = "Julio César Moreno";
//                } else if (laDelegacion.matches("Xochimilco")) {
//                    elDelegado = "José Carlos Acosta Ruiz";
//                }
//
//                textPreguntac7 .setText("En una escala del 1 al 10, donde 1 es muy mala y 10 muy buena, ¿cómo califica la labor realizada hasta hoy por su actual alcalde  " + elDelegado + "  (antes delegado)?");
//                textPreguntac8.setText("Si su alcalde actual " + elDelegado + " buscara la reelección en el 2021 por un periodo de tres años más en su puesto  ¿Qué tan dispuesto estaría a votar por él (ella)?");
//
//            }
//
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }



//public void CargaSpinner9() {
//    String var = "Selecciona";
//    if (var == null) {
//        var = "";
//    }
//    final String[] datos = new String[]{"" + var + "",
//    "1", "2", "3",
//    "4", "5", "6", "7", "8", "9",
//    "10"
//};
//ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
//adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//spinner9.setAdapter(adaptador);
//spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//
//        rdPregunta9.clearCheck();
//        op9 = "sin datos";
//    }
//
//    public void onNothingSelected(AdapterView<?> parent) {
//    }
//});
//}

//
//public void CargaSpinner0() {
//    String var = "Selecciona";
//    if (var == null) {
//        var = "";
//    }
//    final String[] datos = new String[]{"" + var + "", "ALVARO OBREGON", "AZCAPOTZALCO", "BENITO JUAREZ",
//    "COYOACAN", "CUAJIMALPA DE MORELOS", "CUAUHTEMOC", "GUSTAVO A. MADERO", "IZTACALCO", "IZTAPALAPA",
//    "MAGDALENA CONTRERAS", "MIGUEL HIDALGO", "MILPA ALTA", "TLAHUAC", "TLALPAN", "VENUSTIANO CARRANZA",
//    "XOCHIMILCO"};
//    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
//    adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//    spinner0.setAdapter(adaptador);
//    spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//        public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//
//
//            rdPregunta0.clearCheck();
//            op0 = "sin datos";
//
//            radio_abandono1.setVisibility(View.VISIBLE);
//            radio_abandono2.setVisibility(View.VISIBLE);
//            radio_abandono3.setVisibility(View.VISIBLE);
//            radio_abandono4.setVisibility(View.GONE);
//            radio_abandono4.setChecked(false);
//            radio_abandono1.setChecked(true);
//
//            lay1 .setVisibility(View.VISIBLE);
//            lay2 .setVisibility(View.VISIBLE);
//            lay3 .setVisibility(View.VISIBLE);
//            lay4 .setVisibility(View.VISIBLE);
//            lay5 .setVisibility(View.VISIBLE);
//            lay6 .setVisibility(View.VISIBLE);
//            lay7 .setVisibility(View.VISIBLE);
//            layc1 .setVisibility(View.VISIBLE);
//            layc2 .setVisibility(View.VISIBLE);
//            layc3 .setVisibility(View.VISIBLE);
//            layc4 .setVisibility(View.VISIBLE);
//            layc5 .setVisibility(View.VISIBLE);
//            layc6 .setVisibility(View.VISIBLE);
//            layc6a .setVisibility(View.VISIBLE);
//            layc6b .setVisibility(View.VISIBLE);
//            layc7 .setVisibility(View.VISIBLE);
//            layc7a .setVisibility(View.VISIBLE);
//            layc8 .setVisibility(View.VISIBLE);
//            layc8a .setVisibility(View.VISIBLE);
//            layc9 .setVisibility(View.VISIBLE);
//            layc9a .setVisibility(View.VISIBLE);
//            layc10 .setVisibility(View.VISIBLE);
//            layc10a .setVisibility(View.VISIBLE);
//            layc11 .setVisibility(View.VISIBLE);
//            layc11a .setVisibility(View.VISIBLE);
//            layc12 .setVisibility(View.VISIBLE);
//            layc12a .setVisibility(View.VISIBLE);
//            layc13 .setVisibility(View.VISIBLE);
//            layc13a .setVisibility(View.VISIBLE);
//
//            laySocioE.setVisibility(View.VISIBLE);
//            layEst.setVisibility(View.VISIBLE);
//            layAporta.setVisibility(View.VISIBLE);
//            layOcupacion.setVisibility(View.VISIBLE);
//            layCuantosCoches.setVisibility(View.VISIBLE);
//            layCuartos.setVisibility(View.VISIBLE);
//            layCuartosDormir.setVisibility(View.VISIBLE);
//            layFocos.setVisibility(View.VISIBLE);
//            layBanos.setVisibility(View.VISIBLE);
//            layRegadera.setVisibility(View.VISIBLE);
//            layEstufa.setVisibility(View.VISIBLE);
//            layEdad.setVisibility(View.VISIBLE);
//            layTipoPiso.setVisibility(View.VISIBLE);
//            layTipoVivienda.setVisibility(View.VISIBLE);
//            layGenero.setVisibility(View.VISIBLE);
//
//        }
//
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//    });
//}

//	public void CargaSpinner63() {
//		String var = "Selecciona";
//		if (var == null) {
//			var = "";
//		}
//		final String[] datos = new String[] { "" + var + "", "ÁLVARO OBREGÓN", "AZCAPOTZALCO", "BENITO JUÁREZ",
//				"COYOACÁN", "CUAJIMALPA DE MORELOS", "CUAUHTÉMOC", "GUSTAVO A. MADERO", "IZTACALCO", "IZTAPALAPA",
//				"MAGDALENA CONTRERAS", "MIGUEL HIDALGO", "MILPA ALTA", "TLÁHUAC", "TLALPAN", "VENUSTIANO CARRANZA",
//				"XOCHIMILCO" };
//		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
//		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner63.setAdapter(adaptador);
//		spinner63.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//
//				rdPregunta63.clearCheck();
//
//			}
//
//			public void onNothingSelected(AdapterView<?> parent) {
//			}
//		});
//	}

///////////// FIN SPINNER /////////////

private String sacaAlcaldia(String seccion) {
    Set<String> set = new HashSet<String>();
    final String F = "File dbfile";
    String Dele = "";
// Abrimos la base de datos 'DBUsuarios' en modo escritura
    usdbh2 = new UsuariosSQLiteHelper2(this);
    db2 = usdbh2.getReadableDatabase();
    String selectQuery = "SELECT delegacion FROM datos where seccion='" + seccion + "'";
    Cursor cursor = db2.rawQuery(selectQuery, null);
    if (cursor.moveToFirst()) {
        do {
            Dele = cursor.getString(0);
        } while (cursor.moveToNext());
    }
    cursor.close();
    db2.close();
    return Dele;
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
// db.close();

    return acceso;
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
// db.close();

    return acceso;
}

public void grabar() {
    try {
// sacaMaximo();
        String pathAudio = "/mnt/sdcard/Audio1" + formattedDate3 + "";

        Nombre nom = new Nombre();
        String nombreEncuesta = nom.nombreEncuesta();

        File sdCard = null, directory, file = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
// Obtenemos el directorio de la memoria externa
            sdCard = Environment.getExternalStorageDirectory();
        }
        directory = new File(sdCard.getAbsolutePath() + "/" + nombreEncuesta + "-Audio" + formattedDate3 + "");
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile(
            "/mnt/sdcard/" + nombreEncuesta + "-Audio" + formattedDate3 + "/" + nombreAudio() + "");

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            String stackTrace = Log.getStackTraceString(e);
            Log.i(TAG, String.valueOf("Fallo en grabacion: " + e.getMessage()));
        }
    } catch (Exception e) {
        String stackTrace = Log.getStackTraceString(e);
        Log.i(TAG, "Fallo en grabando" + stackTrace);
    }

}

public void detenerGrabacion() {
    Thread thread = new Thread() {
        public void run() {
            if (recorder != null) {

                try {
                    Log.i(TAG, String.valueOf("Grabadora detenida correctamente "));
                    recorder.stop();
recorder.reset(); // You can reuse the object by going back to
// setAudioSource() step
recorder.release();

} catch (Exception e) {
    String stackTrace = Log.getStackTraceString(e);
    Log.i("Manda Audios", "Al detener grabacion" + stackTrace);
}

}
}
};
thread.start();
}

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
}
