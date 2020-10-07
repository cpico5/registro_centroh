package mx.gob.cdmx.estudioscdmx;

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
import mx.gob.cdmx.estudioscdmx.model.DatoContent;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;
import static mx.gob.cdmx.estudioscdmx.Nombre.customURL;
import static mx.gob.cdmx.estudioscdmx.Nombre.encuesta;

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
    private TextView textPreguntaAlcaldes;
    private String elDelegado;

    private RadioButton radio25_1;
    private RadioButton radio25_2;
    private RadioButton radio25_3;
    private RadioButton radio25_4;
    private RadioButton radio25_5;
    private RadioButton radio25_6;
    private RadioButton radio25_7;
    private RadioButton radio25_8;
    private RadioButton radio25_9;
    private RadioButton radio25_10;
    private RadioButton radio25_11;
    private RadioButton radio25_12;
    private RadioButton radio25_13;
    private RadioButton radio25_0;

    private RadioButton radio26_1;
    private RadioButton radio26_2;
    private RadioButton radio26_3;
    private RadioButton radio26_4;
    private RadioButton radio26_5;
    private RadioButton radio26_6;
    private RadioButton radio26_7;
    private RadioButton radio26_8;
    private RadioButton radio26_9;
    private RadioButton radio26_10;
    private RadioButton radio26_11;
    private RadioButton radio26_12;
    private RadioButton radio26_13;
    private RadioButton radio26_0;

    private RadioButton radio28_1;
    private RadioButton radio28_2;
    private RadioButton radio28_3;
    private RadioButton radio28_4;
    private RadioButton radio28_5;
    private RadioButton radio28_6;
    private RadioButton radio28_7;
    private RadioButton radio28_8;
    private RadioButton radio28_9;
    private RadioButton radio28_10;
    private RadioButton radio28_11;
    private RadioButton radio28_12;
    private RadioButton radio28_13;
    private RadioButton radio28_0;

    private RadioButton radio29_1;
    private RadioButton radio29_2;
    private RadioButton radio29_3;
    private RadioButton radio29_4;
    private RadioButton radio29_5;
    private RadioButton radio29_6;
    private RadioButton radio29_7;
    private RadioButton radio29_8;
    private RadioButton radio29_9;
    private RadioButton radio29_10;
    private RadioButton radio29_11;
    private RadioButton radio29_12;
    private RadioButton radio29_13;
    private RadioButton radio29_0;

    private RadioButton radio31_1;
    private RadioButton radio31_2;
    private RadioButton radio31_3;
    private RadioButton radio31_4;
    private RadioButton radio31_5;
    private RadioButton radio31_6;
    private RadioButton radio31_7;
    private RadioButton radio31_8;
    private RadioButton radio31_9;
    private RadioButton radio31_10;
    private RadioButton radio31_11;
    private RadioButton radio31_12;
    private RadioButton radio31_13;
    private RadioButton radio31_0;

    private RadioButton radio32_1;
    private RadioButton radio32_2;
    private RadioButton radio32_3;
    private RadioButton radio32_4;
    private RadioButton radio32_5;
    private RadioButton radio32_6;
    private RadioButton radio32_7;
    private RadioButton radio32_8;
    private RadioButton radio32_9;
    private RadioButton radio32_10;
    private RadioButton radio32_11;
    private RadioButton radio32_12;
    private RadioButton radio32_13;
    private RadioButton radio32_0;

    private List<String> alcaldes;
    private List<String> federales;
    private List<String> locales;
    private List<String> partidos_alcaldes;
    private List<String> alcaldes_morena;
    private List<String> federales_morena;
    private List<String> locales_morena;
    private List<String> partidos_federales;
    private List<String> partidos_locales;



    String PAN="PAN";
    String PRI="PRI";
    String MORENA="MORENA";
    String PRD="PRD";

    String candidato_1_alcalde;
    String candidato_2_alcalde;
    String candidato_3_alcalde;
    String candidato_4_alcalde;

    String candidato_1_federal;
    String candidato_2_federal;
    String candidato_3_federal;
    String candidato_4_federal;

    String candidato_1_local;
    String candidato_2_local;
    String candidato_3_local;
    String candidato_4_local;

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
    private TextView textPregunta24_4;
    private TextView textPregunta24_4b;
    private TextView textPregunta24_5;
    private TextView textPregunta24_5b;
    private TextView textPregunta24_6;
    private TextView textPregunta24_6b;
    private TextView textPregunta24_7;
    private TextView textPregunta24_7b;
    private TextView textPregunta24_8;
    private TextView textPregunta24_8b;
    private TextView textPregunta24_9;
    private TextView textPregunta24_9b;
    private TextView textPregunta24_10;
    private TextView textPregunta24_10b;
    private TextView textPregunta25;
    private TextView textPregunta26;
    private TextView textPregunta27b;
    private TextView textPregunta27_1;
    private TextView textPregunta27_1b;
    private TextView textPregunta27_2;
    private TextView textPregunta27_2b;
    private TextView textPregunta27_3;
    private TextView textPregunta27_3b;
    private TextView textPregunta27_4;
    private TextView textPregunta27_4b;
    private TextView textPregunta27_5;
    private TextView textPregunta27_5b;
    private TextView textPregunta27_6;
    private TextView textPregunta27_6b;
    private TextView textPregunta27_7;
    private TextView textPregunta27_7b;
    private TextView textPregunta27_8;
    private TextView textPregunta27_8b;
    private TextView textPregunta27_9;
    private TextView textPregunta27_9b;
    private TextView textPregunta27_10;
    private TextView textPregunta27_10b;
    private TextView textPregunta28;
    private TextView textPregunta29;
    private TextView textPregunta30b;
    private TextView textPregunta30_1;
    private TextView textPregunta30_1b;
    private TextView textPregunta30_2;
    private TextView textPregunta30_2b;
    private TextView textPregunta30_3;
    private TextView textPregunta30_3b;
    private TextView textPregunta30_4;
    private TextView textPregunta30_4b;
    private TextView textPregunta30_5;
    private TextView textPregunta30_5b;
    private TextView textPregunta30_6;
    private TextView textPregunta30_6b;
    private TextView textPregunta30_7;
    private TextView textPregunta30_7b;
    private TextView textPregunta30_8;
    private TextView textPregunta30_8b;
    private TextView textPregunta30_9;
    private TextView textPregunta30_9b;
    private TextView textPregunta30_10;
    private TextView textPregunta30_10b;
    private TextView textPregunta31;
    private TextView textPregunta32;


    double latitude;
    double longitude;

    Random random = new java.util.Random();
    public int rand;

    public RadioGroup rdPreguntaOcupacion, rdPreguntaFocos, rdPreguntaCuantosCoches, rdPreguntaCuartos, rdPreguntaCuartosDormir,
            rdPreguntaBanos, rdPreguntaRegadera,
            rdPreguntaEstufa, rdPreguntaEdad, rdPreguntaGenero, rdPreguntaTipoVivienda, rdPreguntaTipoPiso;


    public RadioGroup rdPreguntaAporta, rdPreguntaAbandono;


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

    public String opEstadoCivil = "sin datos";
    public String opHijos = "sin datos";
    public String opJefe = "sin datos";
    public String opAporta = "sin datos";
    public String opEstudio = "sin datos";
    public String opAbandono = "sin datos";
    public String opOcupacion = "sin datos";
    public String opCoche = "sin datos";
    public String opFocos = "sin datos";
    public String opCuantosCoches = "sin datos";

    public String opCuartos = "sin datos";
    public String opCuartosDormir = "sin datos";
    public String opBanos = "sin datos";
    public String opRegadera = "sin datos";
    public String opInternet = "sin datos";
    public String opTrabajaron = "sin datos";
    public String opEstufa = "sin datos";
    public String opEdad = "sin datos";
    public String opGenero = "sin datos";
    public String opTipoVivienda = "sin datos";
    public String opTipoPiso = "sin datos";

    public String opMedio = "sin datos";
    public String opSemana = "sin datos";
    public String opFinSemana = "sin datos";
    public String op1 = "sin datos";
    public RadioGroup rdPregunta1;
    public EditText editPregunta1;
    public String captura1;
    LinearLayout lay1;
    public String op2 = "sin datos";
    public RadioGroup rdPregunta2;
    public EditText editPregunta2;
    public String captura2;
    LinearLayout lay2;
    public String op3 = "sin datos";
    public RadioGroup rdPregunta3;
    public EditText editPregunta3;
    public String captura3;
    LinearLayout lay3;
    public String op4 = "sin datos";
    public RadioGroup rdPregunta4;
    public EditText editPregunta4;
    public String captura4;
    LinearLayout lay4;
    public String op5 = "sin datos";
    public RadioGroup rdPregunta5;
    public EditText editPregunta5;
    public String captura5;
    LinearLayout lay5;
    public String op6 = "sin datos";
    public RadioGroup rdPregunta6;
    public EditText editPregunta6;
    public String captura6;
    LinearLayout lay6;
    public String op7 = "sin datos";
    public RadioGroup rdPregunta7;
    public EditText editPregunta7;
    public String captura7;
    LinearLayout lay7;
    public String oppc = "sin datos";
    public RadioGroup rdPreguntapc;
    public EditText editPreguntapc;
    public String capturapc;
    LinearLayout laypc;
    public String oppc_1 = "sin datos";
    public RadioGroup rdPreguntapc_1;
    public EditText editPreguntapc_1;
    public String capturapc_1;
    LinearLayout laypc_1;
    public String oppc_2 = "sin datos";
    public RadioGroup rdPreguntapc_2;
    public EditText editPreguntapc_2;
    public String capturapc_2;
    LinearLayout laypc_2;
    public String oppc_3 = "sin datos";
    public RadioGroup rdPreguntapc_3;
    public EditText editPreguntapc_3;
    public String capturapc_3;
    LinearLayout laypc_3;
    public String oppc_4 = "sin datos";
    public RadioGroup rdPreguntapc_4;
    public EditText editPreguntapc_4;
    public String capturapc_4;
    LinearLayout laypc_4;
    public String op8 = "sin datos";
    public RadioGroup rdPregunta8;
    public EditText editPregunta8;
    public String captura8;
    LinearLayout lay8;
    public String op9 = "sin datos";
    public RadioGroup rdPregunta9;
    public EditText editPregunta9;
    public String captura9;
    LinearLayout lay9;
    public String op10 = "sin datos";
    public RadioGroup rdPregunta10;
    public EditText editPregunta10;
    public String captura10;
    LinearLayout lay10;
    public String op11 = "sin datos";
    public RadioGroup rdPregunta11;
    public EditText editPregunta11;
    public String captura11;
    LinearLayout lay11;
    public String op12 = "sin datos";
    public RadioGroup rdPregunta12;
    public EditText editPregunta12;
    public String captura12;
    LinearLayout lay12;
    public String op13 = "sin datos";
    public RadioGroup rdPregunta13;
    public EditText editPregunta13;
    public String captura13;
    LinearLayout lay13;
    public String op14 = "sin datos";
    public RadioGroup rdPregunta14;
    public EditText editPregunta14;
    public String captura14;
    LinearLayout lay14;
    public String op15 = "sin datos";
    public RadioGroup rdPregunta15;
    public EditText editPregunta15;
    public String captura15;
    LinearLayout lay15;
    public String op16 = "sin datos";
    public RadioGroup rdPregunta16;
    public EditText editPregunta16;
    public String captura16;
    LinearLayout lay16;
    public String op16a = "sin datos";
    public RadioGroup rdPregunta16a;
    public EditText editPregunta16a;
    public String captura16a;
    LinearLayout lay16a;
    public String op16b = "sin datos";
    public RadioGroup rdPregunta16b;
    public EditText editPregunta16b;
    public String captura16b;
    LinearLayout lay16b;
    public String op17 = "sin datos";
    public RadioGroup rdPregunta17;
    public EditText editPregunta17;
    public String captura17;
    LinearLayout lay17;
    public String op17a = "sin datos";
    public RadioGroup rdPregunta17a;
    public EditText editPregunta17a;
    public String captura17a;
    LinearLayout lay17a;
    public String op18 = "sin datos";
    public RadioGroup rdPregunta18;
    public EditText editPregunta18;
    public String captura18;
    LinearLayout lay18;
    public String op19 = "sin datos";
    public RadioGroup rdPregunta19;
    public EditText editPregunta19;
    public String captura19;
    LinearLayout lay19;
    public String op19a = "sin datos";
    public RadioGroup rdPregunta19a;
    public EditText editPregunta19a;
    public String captura19a;
    LinearLayout lay19a;
    public String op19b = "sin datos";
    public RadioGroup rdPregunta19b;
    public EditText editPregunta19b;
    public String captura19b;
    LinearLayout lay19b;
    public String op20 = "sin datos";
    public RadioGroup rdPregunta20;
    public EditText editPregunta20;
    public String captura20;
    LinearLayout lay20;
    public String op21 = "sin datos";
    public RadioGroup rdPregunta21;
    public EditText editPregunta21;
    public String captura21;
    LinearLayout lay21;
    public String op22 = "sin datos";
    public RadioGroup rdPregunta22;
    public EditText editPregunta22;
    public String captura22;
    LinearLayout lay22;
    public String op23 = "sin datos";
    public RadioGroup rdPregunta23;
    public EditText editPregunta23;
    public String captura23;
    LinearLayout lay23;
    public String op24 = "sin datos";
    public RadioGroup rdPregunta24;
    public EditText editPregunta24;
    public String captura24;
    LinearLayout lay24;

    public String op24_1 = "sin datos";
    public RadioGroup rdPregunta24_1;
    public EditText editPregunta24_1;
    public String captura24_1;
    LinearLayout lay24_1;
    public String op24_1a = "sin datos";
    public RadioGroup rdPregunta24_1a;
    public EditText editPregunta24_1a;
    public String captura24_1a;
    LinearLayout lay24_1a;
    public String op24_1b = "sin datos";
    public RadioGroup rdPregunta24_1b;
    public EditText editPregunta24_1b;
    public String captura24_1b;
    LinearLayout lay24_1b;
    public String op24_2 = "sin datos";
    public RadioGroup rdPregunta24_2;
    public EditText editPregunta24_2;
    public String captura24_2;
    LinearLayout lay24_2;
    public String op24_2a = "sin datos";
    public RadioGroup rdPregunta24_2a;
    public EditText editPregunta24_2a;
    public String captura24_2a;
    LinearLayout lay24_2a;
    public String op24_2b = "sin datos";
    public RadioGroup rdPregunta24_2b;
    public EditText editPregunta24_2b;
    public String captura24_2b;
    LinearLayout lay24_2b;
    public String op24_3 = "sin datos";
    public RadioGroup rdPregunta24_3;
    public EditText editPregunta24_3;
    public String captura24_3;
    LinearLayout lay24_3;
    public String op24_3a = "sin datos";
    public RadioGroup rdPregunta24_3a;
    public EditText editPregunta24_3a;
    public String captura24_3a;
    LinearLayout lay24_3a;
    public String op24_3b = "sin datos";
    public RadioGroup rdPregunta24_3b;
    public EditText editPregunta24_3b;
    public String captura24_3b;
    LinearLayout lay24_3b;
    public String op24_4 = "sin datos";
    public RadioGroup rdPregunta24_4;
    public EditText editPregunta24_4;
    public String captura24_4;
    LinearLayout lay24_4;
    public String op24_4a = "sin datos";
    public RadioGroup rdPregunta24_4a;
    public EditText editPregunta24_4a;
    public String captura24_4a;
    LinearLayout lay24_4a;
    public String op24_4b = "sin datos";
    public RadioGroup rdPregunta24_4b;
    public EditText editPregunta24_4b;
    public String captura24_4b;
    LinearLayout lay24_4b;
    public String op24_5 = "sin datos";
    public RadioGroup rdPregunta24_5;
    public EditText editPregunta24_5;
    public String captura24_5;
    LinearLayout lay24_5;
    public String op24_5a = "sin datos";
    public RadioGroup rdPregunta24_5a;
    public EditText editPregunta24_5a;
    public String captura24_5a;
    LinearLayout lay24_5a;
    public String op24_5b = "sin datos";
    public RadioGroup rdPregunta24_5b;
    public EditText editPregunta24_5b;
    public String captura24_5b;
    LinearLayout lay24_5b;
    public String op24_6 = "sin datos";
    public RadioGroup rdPregunta24_6;
    public EditText editPregunta24_6;
    public String captura24_6;
    LinearLayout lay24_6;
    public String op24_6a = "sin datos";
    public RadioGroup rdPregunta24_6a;
    public EditText editPregunta24_6a;
    public String captura24_6a;
    LinearLayout lay24_6a;
    public String op24_6b = "sin datos";
    public RadioGroup rdPregunta24_6b;
    public EditText editPregunta24_6b;
    public String captura24_6b;
    LinearLayout lay24_6b;
    public String op24_7 = "sin datos";
    public RadioGroup rdPregunta24_7;
    public EditText editPregunta24_7;
    public String captura24_7;
    LinearLayout lay24_7;
    public String op24_7a = "sin datos";
    public RadioGroup rdPregunta24_7a;
    public EditText editPregunta24_7a;
    public String captura24_7a;
    LinearLayout lay24_7a;
    public String op24_7b = "sin datos";
    public RadioGroup rdPregunta24_7b;
    public EditText editPregunta24_7b;
    public String captura24_7b;
    LinearLayout lay24_7b;
    public String op24_8 = "sin datos";
    public RadioGroup rdPregunta24_8;
    public EditText editPregunta24_8;
    public String captura24_8;
    LinearLayout lay24_8;
    public String op24_8a = "sin datos";
    public RadioGroup rdPregunta24_8a;
    public EditText editPregunta24_8a;
    public String captura24_8a;
    LinearLayout lay24_8a;
    public String op24_8b = "sin datos";
    public RadioGroup rdPregunta24_8b;
    public EditText editPregunta24_8b;
    public String captura24_8b;
    LinearLayout lay24_8b;
    public String op24_9 = "sin datos";
    public RadioGroup rdPregunta24_9;
    public EditText editPregunta24_9;
    public String captura24_9;
    LinearLayout lay24_9;
    public String op24_9a = "sin datos";
    public RadioGroup rdPregunta24_9a;
    public EditText editPregunta24_9a;
    public String captura24_9a;
    LinearLayout lay24_9a;
    public String op24_9b = "sin datos";
    public RadioGroup rdPregunta24_9b;
    public EditText editPregunta24_9b;
    public String captura24_9b;
    LinearLayout lay24_9b;
    public String op24_10 = "sin datos";
    public RadioGroup rdPregunta24_10;
    public EditText editPregunta24_10;
    public String captura24_10;
    LinearLayout lay24_10;
    public String op24_10a = "sin datos";
    public RadioGroup rdPregunta24_10a;
    public EditText editPregunta24_10a;
    public String captura24_10a;
    LinearLayout lay24_10a;
    public String op24_10b = "sin datos";
    public RadioGroup rdPregunta24_10b;
    public EditText editPregunta24_10b;
    public String captura24_10b;
    LinearLayout lay24_10b;


    public String op25 = "sin datos";
    public RadioGroup rdPregunta25;
    public EditText editPregunta25;
    public String captura25;
    LinearLayout lay25;
    public String op26 = "sin datos";
    public RadioGroup rdPregunta26;
    public EditText editPregunta26;
    public String captura26;
    LinearLayout lay26;
    public String op27 = "sin datos";
    public RadioGroup rdPregunta27;
    public EditText editPregunta27;
    public String captura27;
    LinearLayout lay27;

    public String op27_1 = "sin datos";
    public RadioGroup rdPregunta27_1;
    public EditText editPregunta27_1;
    public String captura27_1;
    LinearLayout lay27_1;
    public String op27_1a = "sin datos";
    public RadioGroup rdPregunta27_1a;
    public EditText editPregunta27_1a;
    public String captura27_1a;
    LinearLayout lay27_1a;
    public String op27_1b = "sin datos";
    public RadioGroup rdPregunta27_1b;
    public EditText editPregunta27_1b;
    public String captura27_1b;
    LinearLayout lay27_1b;
    public String op27_2 = "sin datos";
    public RadioGroup rdPregunta27_2;
    public EditText editPregunta27_2;
    public String captura27_2;
    LinearLayout lay27_2;
    public String op27_2a = "sin datos";
    public RadioGroup rdPregunta27_2a;
    public EditText editPregunta27_2a;
    public String captura27_2a;
    LinearLayout lay27_2a;
    public String op27_2b = "sin datos";
    public RadioGroup rdPregunta27_2b;
    public EditText editPregunta27_2b;
    public String captura27_2b;
    LinearLayout lay27_2b;
    public String op27_3 = "sin datos";
    public RadioGroup rdPregunta27_3;
    public EditText editPregunta27_3;
    public String captura27_3;
    LinearLayout lay27_3;
    public String op27_3a = "sin datos";
    public RadioGroup rdPregunta27_3a;
    public EditText editPregunta27_3a;
    public String captura27_3a;
    LinearLayout lay27_3a;
    public String op27_3b = "sin datos";
    public RadioGroup rdPregunta27_3b;
    public EditText editPregunta27_3b;
    public String captura27_3b;
    LinearLayout lay27_3b;
    public String op27_4 = "sin datos";
    public RadioGroup rdPregunta27_4;
    public EditText editPregunta27_4;
    public String captura27_4;
    LinearLayout lay27_4;
    public String op27_4a = "sin datos";
    public RadioGroup rdPregunta27_4a;
    public EditText editPregunta27_4a;
    public String captura27_4a;
    LinearLayout lay27_4a;
    public String op27_4b = "sin datos";
    public RadioGroup rdPregunta27_4b;
    public EditText editPregunta27_4b;
    public String captura27_4b;
    LinearLayout lay27_4b;
    public String op27_5 = "sin datos";
    public RadioGroup rdPregunta27_5;
    public EditText editPregunta27_5;
    public String captura27_5;
    LinearLayout lay27_5;
    public String op27_5a = "sin datos";
    public RadioGroup rdPregunta27_5a;
    public EditText editPregunta27_5a;
    public String captura27_5a;
    LinearLayout lay27_5a;
    public String op27_5b = "sin datos";
    public RadioGroup rdPregunta27_5b;
    public EditText editPregunta27_5b;
    public String captura27_5b;
    LinearLayout lay27_5b;
    public String op27_6 = "sin datos";
    public RadioGroup rdPregunta27_6;
    public EditText editPregunta27_6;
    public String captura27_6;
    LinearLayout lay27_6;
    public String op27_6a = "sin datos";
    public RadioGroup rdPregunta27_6a;
    public EditText editPregunta27_6a;
    public String captura27_6a;
    LinearLayout lay27_6a;
    public String op27_6b = "sin datos";
    public RadioGroup rdPregunta27_6b;
    public EditText editPregunta27_6b;
    public String captura27_6b;
    LinearLayout lay27_6b;
    public String op27_7 = "sin datos";
    public RadioGroup rdPregunta27_7;
    public EditText editPregunta27_7;
    public String captura27_7;
    LinearLayout lay27_7;
    public String op27_7a = "sin datos";
    public RadioGroup rdPregunta27_7a;
    public EditText editPregunta27_7a;
    public String captura27_7a;
    LinearLayout lay27_7a;
    public String op27_7b = "sin datos";
    public RadioGroup rdPregunta27_7b;
    public EditText editPregunta27_7b;
    public String captura27_7b;
    LinearLayout lay27_7b;
    public String op27_8 = "sin datos";
    public RadioGroup rdPregunta27_8;
    public EditText editPregunta27_8;
    public String captura27_8;
    LinearLayout lay27_8;
    public String op27_8a = "sin datos";
    public RadioGroup rdPregunta27_8a;
    public EditText editPregunta27_8a;
    public String captura27_8a;
    LinearLayout lay27_8a;
    public String op27_8b = "sin datos";
    public RadioGroup rdPregunta27_8b;
    public EditText editPregunta27_8b;
    public String captura27_8b;
    LinearLayout lay27_8b;
    public String op27_9 = "sin datos";
    public RadioGroup rdPregunta27_9;
    public EditText editPregunta27_9;
    public String captura27_9;
    LinearLayout lay27_9;
    public String op27_9a = "sin datos";
    public RadioGroup rdPregunta27_9a;
    public EditText editPregunta27_9a;
    public String captura27_9a;
    LinearLayout lay27_9a;
    public String op27_9b = "sin datos";
    public RadioGroup rdPregunta27_9b;
    public EditText editPregunta27_9b;
    public String captura27_9b;
    LinearLayout lay27_9b;
    public String op27_10 = "sin datos";
    public RadioGroup rdPregunta27_10;
    public EditText editPregunta27_10;
    public String captura27_10;
    LinearLayout lay27_10;
    public String op27_10a = "sin datos";
    public RadioGroup rdPregunta27_10a;
    public EditText editPregunta27_10a;
    public String captura27_10a;
    LinearLayout lay27_10a;
    public String op27_10b = "sin datos";
    public RadioGroup rdPregunta27_10b;
    public EditText editPregunta27_10b;
    public String captura27_10b;
    LinearLayout lay27_10b;

    public String op28 = "sin datos";
    public RadioGroup rdPregunta28;
    public EditText editPregunta28;
    public String captura28;
    LinearLayout lay28;
    public String op29 = "sin datos";
    public RadioGroup rdPregunta29;
    public EditText editPregunta29;
    public String captura29;
    LinearLayout lay29;
    public String op30 = "sin datos";
    public RadioGroup rdPregunta30;
    public EditText editPregunta30;
    public String captura30;
    LinearLayout lay30;

    public String op30_1 = "sin datos";
    public RadioGroup rdPregunta30_1;
    public EditText editPregunta30_1;
    public String captura30_1;
    LinearLayout lay30_1;
    public String op30_1a = "sin datos";
    public RadioGroup rdPregunta30_1a;
    public EditText editPregunta30_1a;
    public String captura30_1a;
    LinearLayout lay30_1a;
    public String op30_1b = "sin datos";
    public RadioGroup rdPregunta30_1b;
    public EditText editPregunta30_1b;
    public String captura30_1b;
    LinearLayout lay30_1b;
    public String op30_2 = "sin datos";
    public RadioGroup rdPregunta30_2;
    public EditText editPregunta30_2;
    public String captura30_2;
    LinearLayout lay30_2;
    public String op30_2a = "sin datos";
    public RadioGroup rdPregunta30_2a;
    public EditText editPregunta30_2a;
    public String captura30_2a;
    LinearLayout lay30_2a;
    public String op30_2b = "sin datos";
    public RadioGroup rdPregunta30_2b;
    public EditText editPregunta30_2b;
    public String captura30_2b;
    LinearLayout lay30_2b;
    public String op30_3 = "sin datos";
    public RadioGroup rdPregunta30_3;
    public EditText editPregunta30_3;
    public String captura30_3;
    LinearLayout lay30_3;
    public String op30_3a = "sin datos";
    public RadioGroup rdPregunta30_3a;
    public EditText editPregunta30_3a;
    public String captura30_3a;
    LinearLayout lay30_3a;
    public String op30_3b = "sin datos";
    public RadioGroup rdPregunta30_3b;
    public EditText editPregunta30_3b;
    public String captura30_3b;
    LinearLayout lay30_3b;
    public String op30_4 = "sin datos";
    public RadioGroup rdPregunta30_4;
    public EditText editPregunta30_4;
    public String captura30_4;
    LinearLayout lay30_4;
    public String op30_4a = "sin datos";
    public RadioGroup rdPregunta30_4a;
    public EditText editPregunta30_4a;
    public String captura30_4a;
    LinearLayout lay30_4a;
    public String op30_4b = "sin datos";
    public RadioGroup rdPregunta30_4b;
    public EditText editPregunta30_4b;
    public String captura30_4b;
    LinearLayout lay30_4b;
    public String op30_5 = "sin datos";
    public RadioGroup rdPregunta30_5;
    public EditText editPregunta30_5;
    public String captura30_5;
    LinearLayout lay30_5;
    public String op30_5a = "sin datos";
    public RadioGroup rdPregunta30_5a;
    public EditText editPregunta30_5a;
    public String captura30_5a;
    LinearLayout lay30_5a;
    public String op30_5b = "sin datos";
    public RadioGroup rdPregunta30_5b;
    public EditText editPregunta30_5b;
    public String captura30_5b;
    LinearLayout lay30_5b;
    public String op30_6 = "sin datos";
    public RadioGroup rdPregunta30_6;
    public EditText editPregunta30_6;
    public String captura30_6;
    LinearLayout lay30_6;
    public String op30_6a = "sin datos";
    public RadioGroup rdPregunta30_6a;
    public EditText editPregunta30_6a;
    public String captura30_6a;
    LinearLayout lay30_6a;
    public String op30_6b = "sin datos";
    public RadioGroup rdPregunta30_6b;
    public EditText editPregunta30_6b;
    public String captura30_6b;
    LinearLayout lay30_6b;
    public String op30_7 = "sin datos";
    public RadioGroup rdPregunta30_7;
    public EditText editPregunta30_7;
    public String captura30_7;
    LinearLayout lay30_7;
    public String op30_7a = "sin datos";
    public RadioGroup rdPregunta30_7a;
    public EditText editPregunta30_7a;
    public String captura30_7a;
    LinearLayout lay30_7a;
    public String op30_7b = "sin datos";
    public RadioGroup rdPregunta30_7b;
    public EditText editPregunta30_7b;
    public String captura30_7b;
    LinearLayout lay30_7b;
    public String op30_8 = "sin datos";
    public RadioGroup rdPregunta30_8;
    public EditText editPregunta30_8;
    public String captura30_8;
    LinearLayout lay30_8;
    public String op30_8a = "sin datos";
    public RadioGroup rdPregunta30_8a;
    public EditText editPregunta30_8a;
    public String captura30_8a;
    LinearLayout lay30_8a;
    public String op30_8b = "sin datos";
    public RadioGroup rdPregunta30_8b;
    public EditText editPregunta30_8b;
    public String captura30_8b;
    LinearLayout lay30_8b;
    public String op30_9 = "sin datos";
    public RadioGroup rdPregunta30_9;
    public EditText editPregunta30_9;
    public String captura30_9;
    LinearLayout lay30_9;
    public String op30_9a = "sin datos";
    public RadioGroup rdPregunta30_9a;
    public EditText editPregunta30_9a;
    public String captura30_9a;
    LinearLayout lay30_9a;
    public String op30_9b = "sin datos";
    public RadioGroup rdPregunta30_9b;
    public EditText editPregunta30_9b;
    public String captura30_9b;
    LinearLayout lay30_9b;
    public String op30_10 = "sin datos";
    public RadioGroup rdPregunta30_10;
    public EditText editPregunta30_10;
    public String captura30_10;
    LinearLayout lay30_10;
    public String op30_10a = "sin datos";
    public RadioGroup rdPregunta30_10a;
    public EditText editPregunta30_10a;
    public String captura30_10a;
    LinearLayout lay30_10a;
    public String op30_10b = "sin datos";
    public RadioGroup rdPregunta30_10b;
    public EditText editPregunta30_10b;
    public String captura30_10b;
    LinearLayout lay30_10b;

    public String op31 = "sin datos";
    public RadioGroup rdPregunta31;
    public EditText editPregunta31;
    public String captura31;
    LinearLayout lay31;
    public String op32 = "sin datos";
    public RadioGroup rdPregunta32;
    public EditText editPregunta32;
    public String captura32;
    LinearLayout lay32;

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


    public String cachaequipo() {
        Bundle datos = this.getIntent().getExtras();
        String equipo = datos.getString("equipo");
        return equipo;
    }

    public String cachaentidad() {
        Bundle datos = this.getIntent().getExtras();
        String entidad = datos.getString("entidad");
        return entidad;
    }

    public String cachaalcaldia() {
        Bundle datos = this.getIntent().getExtras();
        String alcaldia = datos.getString("alcaldia");
        return alcaldia;
    }

    public String cachafederal() {
        Bundle datos = this.getIntent().getExtras();
        String federal = datos.getString("federal");
        return federal;
    }

    public String cachalocal() {
        Bundle datos = this.getIntent().getExtras();
        String local = datos.getString("local");
        return local;
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
                i.putExtra(USUARIO, usuario);
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
                builder.setMessage("�Se detendr� la grabaci�n y \n se reiniciar� la encuesta..?")
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
                builder.setMessage("Excediste el tiempo m�ximo para realizar la encuesta \n"
                        + "��� Se detendr� la grabaci�n y se reiniciar� la Aplicaci�n..!!!").setTitle("AVISO...!!!")
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
// Esto es lo que hace mi bot�n al pulsar ir a atr�s
            Toast.makeText(getApplicationContext(), "No puedo ir hacia atr�s!!\nEstoy grabando...", Toast.LENGTH_SHORT)
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
            R.layout.activity_pantalla1,
            R.layout.activity_pantalla2,
            R.layout.activity_pantalla3,
            R.layout.activity_pantalla4,
            R.layout.activity_pantalla5,
            R.layout.activity_pantalla6,
            R.layout.activity_pantalla7,
            R.layout.activity_pantalla8,
            R.layout.activity_pantalla9,
            R.layout.activity_pantalla10,
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
//        setContentView(R.layout.activity_pantalla1); // COMENTAR ESTA CUANDO ES ALEATORIO

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
        random = new Random();
//
        /*DESCOMENTAR ESTAS 3 LINEAS CUANDO YA ESTA EL NUMERO DE HOJAS ALEATORIO */
        rand = random.nextInt(9);
        setContentView(mLinearLayoutIds[rand]);
        Log.i(null, "El aleatorio: " + rand); // si rand= 11 en el layoud corresponde a uno mas


        /*activity_pantalla12*/

// Crea Log cuando falla la app
        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(MainActivityPantalla1.this, this));


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
                            Log.i(TAG, "Iniciando Grabaci�n");
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

        rdPregunta1 = (RadioGroup) findViewById(R.id.rdPregunta1);
        captura1 = res.getString(R.string.PREGUNTA1);
        lay1 = (LinearLayout) findViewById(R.id.lay1);
        rdPregunta2 = (RadioGroup) findViewById(R.id.rdPregunta2);
        captura2 = res.getString(R.string.PREGUNTA2);
        lay2 = (LinearLayout) findViewById(R.id.lay2);
        rdPregunta3 = (RadioGroup) findViewById(R.id.rdPregunta3);
        captura3 = res.getString(R.string.PREGUNTA3);
        lay3 = (LinearLayout) findViewById(R.id.lay3);
        rdPregunta4 = (RadioGroup) findViewById(R.id.rdPregunta4);
        captura4 = res.getString(R.string.PREGUNTA4);
        lay4 = (LinearLayout) findViewById(R.id.lay4);
        rdPregunta5 = (RadioGroup) findViewById(R.id.rdPregunta5);
        captura5 = res.getString(R.string.PREGUNTA5);
        lay5 = (LinearLayout) findViewById(R.id.lay5);
        rdPregunta6 = (RadioGroup) findViewById(R.id.rdPregunta6);
        captura6 = res.getString(R.string.PREGUNTA6);
        lay6 = (LinearLayout) findViewById(R.id.lay6);
        rdPregunta7 = (RadioGroup) findViewById(R.id.rdPregunta7);
        captura7 = res.getString(R.string.PREGUNTA7);
        lay7 = (LinearLayout) findViewById(R.id.lay7);
        rdPreguntapc = (RadioGroup) findViewById(R.id.rdPreguntapc);
        capturapc = res.getString(R.string.PREGUNTApc);
        laypc = (LinearLayout) findViewById(R.id.laypc);
        rdPreguntapc_1 = (RadioGroup) findViewById(R.id.rdPreguntapc_1);
        capturapc_1 = res.getString(R.string.PREGUNTApc_1);
        laypc_1 = (LinearLayout) findViewById(R.id.laypc_1);
        rdPreguntapc_2 = (RadioGroup) findViewById(R.id.rdPreguntapc_2);
        capturapc_2 = res.getString(R.string.PREGUNTApc_2);
        laypc_2 = (LinearLayout) findViewById(R.id.laypc_2);
        rdPreguntapc_3 = (RadioGroup) findViewById(R.id.rdPreguntapc_3);
        capturapc_3 = res.getString(R.string.PREGUNTApc_3);
        laypc_3 = (LinearLayout) findViewById(R.id.laypc_3);
        rdPreguntapc_4 = (RadioGroup) findViewById(R.id.rdPreguntapc_4);
        capturapc_4 = res.getString(R.string.PREGUNTApc_4);
        laypc_4 = (LinearLayout) findViewById(R.id.laypc_4);
        rdPregunta8 = (RadioGroup) findViewById(R.id.rdPregunta8);
        captura8 = res.getString(R.string.PREGUNTA8);
        lay8 = (LinearLayout) findViewById(R.id.lay8);
        rdPregunta9 = (RadioGroup) findViewById(R.id.rdPregunta9);
        captura9 = res.getString(R.string.PREGUNTA9);
        lay9 = (LinearLayout) findViewById(R.id.lay9);
        rdPregunta10 = (RadioGroup) findViewById(R.id.rdPregunta10);
        captura10 = res.getString(R.string.PREGUNTA10);
        lay10 = (LinearLayout) findViewById(R.id.lay10);
        rdPregunta11 = (RadioGroup) findViewById(R.id.rdPregunta11);
        captura11 = res.getString(R.string.PREGUNTA11);
        lay11 = (LinearLayout) findViewById(R.id.lay11);
        rdPregunta12 = (RadioGroup) findViewById(R.id.rdPregunta12);
        captura12 = res.getString(R.string.PREGUNTA12);
        lay12 = (LinearLayout) findViewById(R.id.lay12);
        rdPregunta13 = (RadioGroup) findViewById(R.id.rdPregunta13);
        captura13 = res.getString(R.string.PREGUNTA13);
        lay13 = (LinearLayout) findViewById(R.id.lay13);
        rdPregunta14 = (RadioGroup) findViewById(R.id.rdPregunta14);
        captura14 = res.getString(R.string.PREGUNTA14);
        lay14 = (LinearLayout) findViewById(R.id.lay14);
        rdPregunta15 = (RadioGroup) findViewById(R.id.rdPregunta15);
        captura15 = res.getString(R.string.PREGUNTA15);
        lay15 = (LinearLayout) findViewById(R.id.lay15);
        rdPregunta16 = (RadioGroup) findViewById(R.id.rdPregunta16);
        captura16 = res.getString(R.string.PREGUNTA16);
        lay16 = (LinearLayout) findViewById(R.id.lay16);
        rdPregunta16a = (RadioGroup) findViewById(R.id.rdPregunta16a);
        captura16a = res.getString(R.string.PREGUNTA16a);
        lay16a = (LinearLayout) findViewById(R.id.lay16a);
        rdPregunta16b = (RadioGroup) findViewById(R.id.rdPregunta16b);
        captura16b = res.getString(R.string.PREGUNTA16b);
        lay16b = (LinearLayout) findViewById(R.id.lay16b);
        rdPregunta17 = (RadioGroup) findViewById(R.id.rdPregunta17);
        captura17 = res.getString(R.string.PREGUNTA17);
        lay17 = (LinearLayout) findViewById(R.id.lay17);
        rdPregunta17a = (RadioGroup) findViewById(R.id.rdPregunta17a);
        captura17a = res.getString(R.string.PREGUNTA17a);
        lay17a = (LinearLayout) findViewById(R.id.lay17a);
        rdPregunta18 = (RadioGroup) findViewById(R.id.rdPregunta18);
        captura18 = res.getString(R.string.PREGUNTA18);
        lay18 = (LinearLayout) findViewById(R.id.lay18);
        rdPregunta19 = (RadioGroup) findViewById(R.id.rdPregunta19);
        captura19 = res.getString(R.string.PREGUNTA19);
        lay19 = (LinearLayout) findViewById(R.id.lay19);
        rdPregunta19a = (RadioGroup) findViewById(R.id.rdPregunta19a);
        captura19a = res.getString(R.string.PREGUNTA19a);
        lay19a = (LinearLayout) findViewById(R.id.lay19a);
        rdPregunta19b = (RadioGroup) findViewById(R.id.rdPregunta19b);
        captura19b = res.getString(R.string.PREGUNTA19b);
        lay19b = (LinearLayout) findViewById(R.id.lay19b);
        rdPregunta20 = (RadioGroup) findViewById(R.id.rdPregunta20);
        captura20 = res.getString(R.string.PREGUNTA20);
        lay20 = (LinearLayout) findViewById(R.id.lay20);
        rdPregunta21 = (RadioGroup) findViewById(R.id.rdPregunta21);
        captura21 = res.getString(R.string.PREGUNTA21);
        lay21 = (LinearLayout) findViewById(R.id.lay21);
        rdPregunta22 = (RadioGroup) findViewById(R.id.rdPregunta22);
        captura22 = res.getString(R.string.PREGUNTA22);
        lay22 = (LinearLayout) findViewById(R.id.lay22);
        rdPregunta23 = (RadioGroup) findViewById(R.id.rdPregunta23);
        captura23 = res.getString(R.string.PREGUNTA23);
        lay23 = (LinearLayout) findViewById(R.id.lay23);
        rdPregunta24 = (RadioGroup) findViewById(R.id.rdPregunta24);
        captura24 = res.getString(R.string.PREGUNTA24);
        lay24 = (LinearLayout) findViewById(R.id.lay24);

        rdPregunta24_1 = (RadioGroup) findViewById(R.id.rdPregunta24_1);
        captura24_1 = res.getString(R.string.PREGUNTA24_1);
        lay24_1 = (LinearLayout) findViewById(R.id.lay24_1);
        rdPregunta24_1a = (RadioGroup) findViewById(R.id.rdPregunta24_1a);
        captura24_1a = res.getString(R.string.PREGUNTA24_1a);
        lay24_1a = (LinearLayout) findViewById(R.id.lay24_1a);
        rdPregunta24_1b = (RadioGroup) findViewById(R.id.rdPregunta24_1b);
        captura24_1b = res.getString(R.string.PREGUNTA24_1b);
        lay24_1b = (LinearLayout) findViewById(R.id.lay24_1b);
        rdPregunta24_2 = (RadioGroup) findViewById(R.id.rdPregunta24_2);
        captura24_2 = res.getString(R.string.PREGUNTA24_2);
        lay24_2 = (LinearLayout) findViewById(R.id.lay24_2);
        rdPregunta24_2a = (RadioGroup) findViewById(R.id.rdPregunta24_2a);
        captura24_2a = res.getString(R.string.PREGUNTA24_2a);
        lay24_2a = (LinearLayout) findViewById(R.id.lay24_2a);
        rdPregunta24_2b = (RadioGroup) findViewById(R.id.rdPregunta24_2b);
        captura24_2b = res.getString(R.string.PREGUNTA24_2b);
        lay24_2b = (LinearLayout) findViewById(R.id.lay24_2b);
        rdPregunta24_3 = (RadioGroup) findViewById(R.id.rdPregunta24_3);
        captura24_3 = res.getString(R.string.PREGUNTA24_3);
        lay24_3 = (LinearLayout) findViewById(R.id.lay24_3);
        rdPregunta24_3a = (RadioGroup) findViewById(R.id.rdPregunta24_3a);
        captura24_3a = res.getString(R.string.PREGUNTA24_3a);
        lay24_3a = (LinearLayout) findViewById(R.id.lay24_3a);
        rdPregunta24_3b = (RadioGroup) findViewById(R.id.rdPregunta24_3b);
        captura24_3b = res.getString(R.string.PREGUNTA24_3b);
        lay24_3b = (LinearLayout) findViewById(R.id.lay24_3b);
        rdPregunta24_4 = (RadioGroup) findViewById(R.id.rdPregunta24_4);
        captura24_4 = res.getString(R.string.PREGUNTA24_4);
        lay24_4 = (LinearLayout) findViewById(R.id.lay24_4);
        rdPregunta24_4a = (RadioGroup) findViewById(R.id.rdPregunta24_4a);
        captura24_4a = res.getString(R.string.PREGUNTA24_4a);
        lay24_4a = (LinearLayout) findViewById(R.id.lay24_4a);
        rdPregunta24_4b = (RadioGroup) findViewById(R.id.rdPregunta24_4b);
        captura24_4b = res.getString(R.string.PREGUNTA24_4b);
        lay24_4b = (LinearLayout) findViewById(R.id.lay24_4b);
        rdPregunta24_5 = (RadioGroup) findViewById(R.id.rdPregunta24_5);
        captura24_5 = res.getString(R.string.PREGUNTA24_5);
        lay24_5 = (LinearLayout) findViewById(R.id.lay24_5);
        rdPregunta24_5a = (RadioGroup) findViewById(R.id.rdPregunta24_5a);
        captura24_5a = res.getString(R.string.PREGUNTA24_5a);
        lay24_5a = (LinearLayout) findViewById(R.id.lay24_5a);
        rdPregunta24_5b = (RadioGroup) findViewById(R.id.rdPregunta24_5b);
        captura24_5b = res.getString(R.string.PREGUNTA24_5b);
        lay24_5b = (LinearLayout) findViewById(R.id.lay24_5b);
        rdPregunta24_6 = (RadioGroup) findViewById(R.id.rdPregunta24_6);
        captura24_6 = res.getString(R.string.PREGUNTA24_6);
        lay24_6 = (LinearLayout) findViewById(R.id.lay24_6);
        rdPregunta24_6a = (RadioGroup) findViewById(R.id.rdPregunta24_6a);
        captura24_6a = res.getString(R.string.PREGUNTA24_6a);
        lay24_6a = (LinearLayout) findViewById(R.id.lay24_6a);
        rdPregunta24_6b = (RadioGroup) findViewById(R.id.rdPregunta24_6b);
        captura24_6b = res.getString(R.string.PREGUNTA24_6b);
        lay24_6b = (LinearLayout) findViewById(R.id.lay24_6b);
        rdPregunta24_7 = (RadioGroup) findViewById(R.id.rdPregunta24_7);
        captura24_7 = res.getString(R.string.PREGUNTA24_7);
        lay24_7 = (LinearLayout) findViewById(R.id.lay24_7);
        rdPregunta24_7a = (RadioGroup) findViewById(R.id.rdPregunta24_7a);
        captura24_7a = res.getString(R.string.PREGUNTA24_7a);
        lay24_7a = (LinearLayout) findViewById(R.id.lay24_7a);
        rdPregunta24_7b = (RadioGroup) findViewById(R.id.rdPregunta24_7b);
        captura24_7b = res.getString(R.string.PREGUNTA24_7b);
        lay24_7b = (LinearLayout) findViewById(R.id.lay24_7b);
        rdPregunta24_8 = (RadioGroup) findViewById(R.id.rdPregunta24_8);
        captura24_8 = res.getString(R.string.PREGUNTA24_8);
        lay24_8 = (LinearLayout) findViewById(R.id.lay24_8);
        rdPregunta24_8a = (RadioGroup) findViewById(R.id.rdPregunta24_8a);
        captura24_8a = res.getString(R.string.PREGUNTA24_8a);
        lay24_8a = (LinearLayout) findViewById(R.id.lay24_8a);
        rdPregunta24_8b = (RadioGroup) findViewById(R.id.rdPregunta24_8b);
        captura24_8b = res.getString(R.string.PREGUNTA24_8b);
        lay24_8b = (LinearLayout) findViewById(R.id.lay24_8b);
        rdPregunta24_9 = (RadioGroup) findViewById(R.id.rdPregunta24_9);
        captura24_9 = res.getString(R.string.PREGUNTA24_9);
        lay24_9 = (LinearLayout) findViewById(R.id.lay24_9);
        rdPregunta24_9a = (RadioGroup) findViewById(R.id.rdPregunta24_9a);
        captura24_9a = res.getString(R.string.PREGUNTA24_9a);
        lay24_9a = (LinearLayout) findViewById(R.id.lay24_9a);
        rdPregunta24_9b = (RadioGroup) findViewById(R.id.rdPregunta24_9b);
        captura24_9b = res.getString(R.string.PREGUNTA24_9b);
        lay24_9b = (LinearLayout) findViewById(R.id.lay24_9b);
        rdPregunta24_10 = (RadioGroup) findViewById(R.id.rdPregunta24_10);
        captura24_10 = res.getString(R.string.PREGUNTA24_10);
        lay24_10 = (LinearLayout) findViewById(R.id.lay24_10);
        rdPregunta24_10a = (RadioGroup) findViewById(R.id.rdPregunta24_10a);
        captura24_10a = res.getString(R.string.PREGUNTA24_10a);
        lay24_10a = (LinearLayout) findViewById(R.id.lay24_10a);
        rdPregunta24_10b = (RadioGroup) findViewById(R.id.rdPregunta24_10b);
        captura24_10b = res.getString(R.string.PREGUNTA24_10b);
        lay24_10b = (LinearLayout) findViewById(R.id.lay24_10b);

        rdPregunta25 = (RadioGroup) findViewById(R.id.rdPregunta25);
        captura25 = res.getString(R.string.PREGUNTA25);
        lay25 = (LinearLayout) findViewById(R.id.lay25);
        rdPregunta26 = (RadioGroup) findViewById(R.id.rdPregunta26);
        captura26 = res.getString(R.string.PREGUNTA26);
        lay26 = (LinearLayout) findViewById(R.id.lay26);
        rdPregunta27 = (RadioGroup) findViewById(R.id.rdPregunta27);
        captura27 = res.getString(R.string.PREGUNTA27);
        lay27 = (LinearLayout) findViewById(R.id.lay27);

        rdPregunta27_1 = (RadioGroup) findViewById(R.id.rdPregunta27_1);
        captura27_1 = res.getString(R.string.PREGUNTA27_1);
        lay27_1 = (LinearLayout) findViewById(R.id.lay27_1);
        rdPregunta27_1a = (RadioGroup) findViewById(R.id.rdPregunta27_1a);
        captura27_1a = res.getString(R.string.PREGUNTA27_1a);
        lay27_1a = (LinearLayout) findViewById(R.id.lay27_1a);
        rdPregunta27_1b = (RadioGroup) findViewById(R.id.rdPregunta27_1b);
        captura27_1b = res.getString(R.string.PREGUNTA27_1b);
        lay27_1b = (LinearLayout) findViewById(R.id.lay27_1b);
        rdPregunta27_2 = (RadioGroup) findViewById(R.id.rdPregunta27_2);
        captura27_2 = res.getString(R.string.PREGUNTA27_2);
        lay27_2 = (LinearLayout) findViewById(R.id.lay27_2);
        rdPregunta27_2a = (RadioGroup) findViewById(R.id.rdPregunta27_2a);
        captura27_2a = res.getString(R.string.PREGUNTA27_2a);
        lay27_2a = (LinearLayout) findViewById(R.id.lay27_2a);
        rdPregunta27_2b = (RadioGroup) findViewById(R.id.rdPregunta27_2b);
        captura27_2b = res.getString(R.string.PREGUNTA27_2b);
        lay27_2b = (LinearLayout) findViewById(R.id.lay27_2b);
        rdPregunta27_3 = (RadioGroup) findViewById(R.id.rdPregunta27_3);
        captura27_3 = res.getString(R.string.PREGUNTA27_3);
        lay27_3 = (LinearLayout) findViewById(R.id.lay27_3);
        rdPregunta27_3a = (RadioGroup) findViewById(R.id.rdPregunta27_3a);
        captura27_3a = res.getString(R.string.PREGUNTA27_3a);
        lay27_3a = (LinearLayout) findViewById(R.id.lay27_3a);
        rdPregunta27_3b = (RadioGroup) findViewById(R.id.rdPregunta27_3b);
        captura27_3b = res.getString(R.string.PREGUNTA27_3b);
        lay27_3b = (LinearLayout) findViewById(R.id.lay27_3b);
        rdPregunta27_4 = (RadioGroup) findViewById(R.id.rdPregunta27_4);
        captura27_4 = res.getString(R.string.PREGUNTA27_4);
        lay27_4 = (LinearLayout) findViewById(R.id.lay27_4);
        rdPregunta27_4a = (RadioGroup) findViewById(R.id.rdPregunta27_4a);
        captura27_4a = res.getString(R.string.PREGUNTA27_4a);
        lay27_4a = (LinearLayout) findViewById(R.id.lay27_4a);
        rdPregunta27_4b = (RadioGroup) findViewById(R.id.rdPregunta27_4b);
        captura27_4b = res.getString(R.string.PREGUNTA27_4b);
        lay27_4b = (LinearLayout) findViewById(R.id.lay27_4b);
        rdPregunta27_5 = (RadioGroup) findViewById(R.id.rdPregunta27_5);
        captura27_5 = res.getString(R.string.PREGUNTA27_5);
        lay27_5 = (LinearLayout) findViewById(R.id.lay27_5);
        rdPregunta27_5a = (RadioGroup) findViewById(R.id.rdPregunta27_5a);
        captura27_5a = res.getString(R.string.PREGUNTA27_5a);
        lay27_5a = (LinearLayout) findViewById(R.id.lay27_5a);
        rdPregunta27_5b = (RadioGroup) findViewById(R.id.rdPregunta27_5b);
        captura27_5b = res.getString(R.string.PREGUNTA27_5b);
        lay27_5b = (LinearLayout) findViewById(R.id.lay27_5b);
        rdPregunta27_6 = (RadioGroup) findViewById(R.id.rdPregunta27_6);
        captura27_6 = res.getString(R.string.PREGUNTA27_6);
        lay27_6 = (LinearLayout) findViewById(R.id.lay27_6);
        rdPregunta27_6a = (RadioGroup) findViewById(R.id.rdPregunta27_6a);
        captura27_6a = res.getString(R.string.PREGUNTA27_6a);
        lay27_6a = (LinearLayout) findViewById(R.id.lay27_6a);
        rdPregunta27_6b = (RadioGroup) findViewById(R.id.rdPregunta27_6b);
        captura27_6b = res.getString(R.string.PREGUNTA27_6b);
        lay27_6b = (LinearLayout) findViewById(R.id.lay27_6b);
        rdPregunta27_7 = (RadioGroup) findViewById(R.id.rdPregunta27_7);
        captura27_7 = res.getString(R.string.PREGUNTA27_7);
        lay27_7 = (LinearLayout) findViewById(R.id.lay27_7);
        rdPregunta27_7a = (RadioGroup) findViewById(R.id.rdPregunta27_7a);
        captura27_7a = res.getString(R.string.PREGUNTA27_7a);
        lay27_7a = (LinearLayout) findViewById(R.id.lay27_7a);
        rdPregunta27_7b = (RadioGroup) findViewById(R.id.rdPregunta27_7b);
        captura27_7b = res.getString(R.string.PREGUNTA27_7b);
        lay27_7b = (LinearLayout) findViewById(R.id.lay27_7b);
        rdPregunta27_8 = (RadioGroup) findViewById(R.id.rdPregunta27_8);
        captura27_8 = res.getString(R.string.PREGUNTA27_8);
        lay27_8 = (LinearLayout) findViewById(R.id.lay27_8);
        rdPregunta27_8a = (RadioGroup) findViewById(R.id.rdPregunta27_8a);
        captura27_8a = res.getString(R.string.PREGUNTA27_8a);
        lay27_8a = (LinearLayout) findViewById(R.id.lay27_8a);
        rdPregunta27_8b = (RadioGroup) findViewById(R.id.rdPregunta27_8b);
        captura27_8b = res.getString(R.string.PREGUNTA27_8b);
        lay27_8b = (LinearLayout) findViewById(R.id.lay27_8b);
        rdPregunta27_9 = (RadioGroup) findViewById(R.id.rdPregunta27_9);
        captura27_9 = res.getString(R.string.PREGUNTA27_9);
        lay27_9 = (LinearLayout) findViewById(R.id.lay27_9);
        rdPregunta27_9a = (RadioGroup) findViewById(R.id.rdPregunta27_9a);
        captura27_9a = res.getString(R.string.PREGUNTA27_9a);
        lay27_9a = (LinearLayout) findViewById(R.id.lay27_9a);
        rdPregunta27_9b = (RadioGroup) findViewById(R.id.rdPregunta27_9b);
        captura27_9b = res.getString(R.string.PREGUNTA27_9b);
        lay27_9b = (LinearLayout) findViewById(R.id.lay27_9b);
        rdPregunta27_10 = (RadioGroup) findViewById(R.id.rdPregunta27_10);
        captura27_10 = res.getString(R.string.PREGUNTA27_10);
        lay27_10 = (LinearLayout) findViewById(R.id.lay27_10);
        rdPregunta27_10a = (RadioGroup) findViewById(R.id.rdPregunta27_10a);
        captura27_10a = res.getString(R.string.PREGUNTA27_10a);
        lay27_10a = (LinearLayout) findViewById(R.id.lay27_10a);
        rdPregunta27_10b = (RadioGroup) findViewById(R.id.rdPregunta27_10b);
        captura27_10b = res.getString(R.string.PREGUNTA27_10b);
        lay27_10b = (LinearLayout) findViewById(R.id.lay27_10b);

        rdPregunta28 = (RadioGroup) findViewById(R.id.rdPregunta28);
        captura28 = res.getString(R.string.PREGUNTA28);
        lay28 = (LinearLayout) findViewById(R.id.lay28);
        rdPregunta29 = (RadioGroup) findViewById(R.id.rdPregunta29);
        captura29 = res.getString(R.string.PREGUNTA29);
        lay29 = (LinearLayout) findViewById(R.id.lay29);
        rdPregunta30 = (RadioGroup) findViewById(R.id.rdPregunta30);
        captura30 = res.getString(R.string.PREGUNTA30);
        lay30 = (LinearLayout) findViewById(R.id.lay30);

        rdPregunta30_1 = (RadioGroup) findViewById(R.id.rdPregunta30_1);
        captura30_1 = res.getString(R.string.PREGUNTA30_1);
        lay30_1 = (LinearLayout) findViewById(R.id.lay30_1);
        rdPregunta30_1a = (RadioGroup) findViewById(R.id.rdPregunta30_1a);
        captura30_1a = res.getString(R.string.PREGUNTA30_1a);
        lay30_1a = (LinearLayout) findViewById(R.id.lay30_1a);
        rdPregunta30_1b = (RadioGroup) findViewById(R.id.rdPregunta30_1b);
        captura30_1b = res.getString(R.string.PREGUNTA30_1b);
        lay30_1b = (LinearLayout) findViewById(R.id.lay30_1b);
        rdPregunta30_2 = (RadioGroup) findViewById(R.id.rdPregunta30_2);
        captura30_2 = res.getString(R.string.PREGUNTA30_2);
        lay30_2 = (LinearLayout) findViewById(R.id.lay30_2);
        rdPregunta30_2a = (RadioGroup) findViewById(R.id.rdPregunta30_2a);
        captura30_2a = res.getString(R.string.PREGUNTA30_2a);
        lay30_2a = (LinearLayout) findViewById(R.id.lay30_2a);
        rdPregunta30_2b = (RadioGroup) findViewById(R.id.rdPregunta30_2b);
        captura30_2b = res.getString(R.string.PREGUNTA30_2b);
        lay30_2b = (LinearLayout) findViewById(R.id.lay30_2b);
        rdPregunta30_3 = (RadioGroup) findViewById(R.id.rdPregunta30_3);
        captura30_3 = res.getString(R.string.PREGUNTA30_3);
        lay30_3 = (LinearLayout) findViewById(R.id.lay30_3);
        rdPregunta30_3a = (RadioGroup) findViewById(R.id.rdPregunta30_3a);
        captura30_3a = res.getString(R.string.PREGUNTA30_3a);
        lay30_3a = (LinearLayout) findViewById(R.id.lay30_3a);
        rdPregunta30_3b = (RadioGroup) findViewById(R.id.rdPregunta30_3b);
        captura30_3b = res.getString(R.string.PREGUNTA30_3b);
        lay30_3b = (LinearLayout) findViewById(R.id.lay30_3b);
        rdPregunta30_4 = (RadioGroup) findViewById(R.id.rdPregunta30_4);
        captura30_4 = res.getString(R.string.PREGUNTA30_4);
        lay30_4 = (LinearLayout) findViewById(R.id.lay30_4);
        rdPregunta30_4a = (RadioGroup) findViewById(R.id.rdPregunta30_4a);
        captura30_4a = res.getString(R.string.PREGUNTA30_4a);
        lay30_4a = (LinearLayout) findViewById(R.id.lay30_4a);
        rdPregunta30_4b = (RadioGroup) findViewById(R.id.rdPregunta30_4b);
        captura30_4b = res.getString(R.string.PREGUNTA30_4b);
        lay30_4b = (LinearLayout) findViewById(R.id.lay30_4b);
        rdPregunta30_5 = (RadioGroup) findViewById(R.id.rdPregunta30_5);
        captura30_5 = res.getString(R.string.PREGUNTA30_5);
        lay30_5 = (LinearLayout) findViewById(R.id.lay30_5);
        rdPregunta30_5a = (RadioGroup) findViewById(R.id.rdPregunta30_5a);
        captura30_5a = res.getString(R.string.PREGUNTA30_5a);
        lay30_5a = (LinearLayout) findViewById(R.id.lay30_5a);
        rdPregunta30_5b = (RadioGroup) findViewById(R.id.rdPregunta30_5b);
        captura30_5b = res.getString(R.string.PREGUNTA30_5b);
        lay30_5b = (LinearLayout) findViewById(R.id.lay30_5b);
        rdPregunta30_6 = (RadioGroup) findViewById(R.id.rdPregunta30_6);
        captura30_6 = res.getString(R.string.PREGUNTA30_6);
        lay30_6 = (LinearLayout) findViewById(R.id.lay30_6);
        rdPregunta30_6a = (RadioGroup) findViewById(R.id.rdPregunta30_6a);
        captura30_6a = res.getString(R.string.PREGUNTA30_6a);
        lay30_6a = (LinearLayout) findViewById(R.id.lay30_6a);
        rdPregunta30_6b = (RadioGroup) findViewById(R.id.rdPregunta30_6b);
        captura30_6b = res.getString(R.string.PREGUNTA30_6b);
        lay30_6b = (LinearLayout) findViewById(R.id.lay30_6b);
        rdPregunta30_7 = (RadioGroup) findViewById(R.id.rdPregunta30_7);
        captura30_7 = res.getString(R.string.PREGUNTA30_7);
        lay30_7 = (LinearLayout) findViewById(R.id.lay30_7);
        rdPregunta30_7a = (RadioGroup) findViewById(R.id.rdPregunta30_7a);
        captura30_7a = res.getString(R.string.PREGUNTA30_7a);
        lay30_7a = (LinearLayout) findViewById(R.id.lay30_7a);
        rdPregunta30_7b = (RadioGroup) findViewById(R.id.rdPregunta30_7b);
        captura30_7b = res.getString(R.string.PREGUNTA30_7b);
        lay30_7b = (LinearLayout) findViewById(R.id.lay30_7b);
        rdPregunta30_8 = (RadioGroup) findViewById(R.id.rdPregunta30_8);
        captura30_8 = res.getString(R.string.PREGUNTA30_8);
        lay30_8 = (LinearLayout) findViewById(R.id.lay30_8);
        rdPregunta30_8a = (RadioGroup) findViewById(R.id.rdPregunta30_8a);
        captura30_8a = res.getString(R.string.PREGUNTA30_8a);
        lay30_8a = (LinearLayout) findViewById(R.id.lay30_8a);
        rdPregunta30_8b = (RadioGroup) findViewById(R.id.rdPregunta30_8b);
        captura30_8b = res.getString(R.string.PREGUNTA30_8b);
        lay30_8b = (LinearLayout) findViewById(R.id.lay30_8b);
        rdPregunta30_9 = (RadioGroup) findViewById(R.id.rdPregunta30_9);
        captura30_9 = res.getString(R.string.PREGUNTA30_9);
        lay30_9 = (LinearLayout) findViewById(R.id.lay30_9);
        rdPregunta30_9a = (RadioGroup) findViewById(R.id.rdPregunta30_9a);
        captura30_9a = res.getString(R.string.PREGUNTA30_9a);
        lay30_9a = (LinearLayout) findViewById(R.id.lay30_9a);
        rdPregunta30_9b = (RadioGroup) findViewById(R.id.rdPregunta30_9b);
        captura30_9b = res.getString(R.string.PREGUNTA30_9b);
        lay30_9b = (LinearLayout) findViewById(R.id.lay30_9b);
        rdPregunta30_10 = (RadioGroup) findViewById(R.id.rdPregunta30_10);
        captura30_10 = res.getString(R.string.PREGUNTA30_10);
        lay30_10 = (LinearLayout) findViewById(R.id.lay30_10);
        rdPregunta30_10a = (RadioGroup) findViewById(R.id.rdPregunta30_10a);
        captura30_10a = res.getString(R.string.PREGUNTA30_10a);
        lay30_10a = (LinearLayout) findViewById(R.id.lay30_10a);
        rdPregunta30_10b = (RadioGroup) findViewById(R.id.rdPregunta30_10b);
        captura30_10b = res.getString(R.string.PREGUNTA30_10b);
        lay30_10b = (LinearLayout) findViewById(R.id.lay30_10b);

        rdPregunta31 = (RadioGroup) findViewById(R.id.rdPregunta31);
        captura31 = res.getString(R.string.PREGUNTA31);
        lay31 = (LinearLayout) findViewById(R.id.lay31);
        rdPregunta32 = (RadioGroup) findViewById(R.id.rdPregunta32);
        captura32 = res.getString(R.string.PREGUNTA32);
        lay32 = (LinearLayout) findViewById(R.id.lay32);

        editPregunta6 = (EditText) findViewById(R.id.editPregunta6);
        editPregunta7 = (EditText) findViewById(R.id.editPregunta7);

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

        radio25_1 = (RadioButton) findViewById(R.id.radio25_1);
        radio25_2 = (RadioButton) findViewById(R.id.radio25_2);
        radio25_3 = (RadioButton) findViewById(R.id.radio25_3);
        radio25_4 = (RadioButton) findViewById(R.id.radio25_4);
        radio25_5 = (RadioButton) findViewById(R.id.radio25_5);
        radio25_6 = (RadioButton) findViewById(R.id.radio25_6);
        radio25_7 = (RadioButton) findViewById(R.id.radio25_7);
        radio25_8 = (RadioButton) findViewById(R.id.radio25_8);
        radio25_9 = (RadioButton) findViewById(R.id.radio25_9);
        radio25_10 = (RadioButton) findViewById(R.id.radio25_10);
        radio25_11 = (RadioButton) findViewById(R.id.radio25_11);
        radio25_12 = (RadioButton) findViewById(R.id.radio25_12);
        radio25_13 = (RadioButton) findViewById(R.id.radio25_13);
        radio25_0 = (RadioButton) findViewById(R.id.radio25_0);

        radio26_1 = (RadioButton) findViewById(R.id.radio26_1);
        radio26_2 = (RadioButton) findViewById(R.id.radio26_2);
        radio26_3 = (RadioButton) findViewById(R.id.radio26_3);
        radio26_4 = (RadioButton) findViewById(R.id.radio26_4);
        radio26_5 = (RadioButton) findViewById(R.id.radio26_5);
        radio26_6 = (RadioButton) findViewById(R.id.radio26_6);
        radio26_7 = (RadioButton) findViewById(R.id.radio26_7);
        radio26_8 = (RadioButton) findViewById(R.id.radio26_8);
        radio26_9 = (RadioButton) findViewById(R.id.radio26_9);
        radio26_10 = (RadioButton) findViewById(R.id.radio26_10);
        radio26_11 = (RadioButton) findViewById(R.id.radio26_11);
        radio26_12 = (RadioButton) findViewById(R.id.radio26_12);
        radio26_13 = (RadioButton) findViewById(R.id.radio26_13);
        radio26_0 = (RadioButton) findViewById(R.id.radio26_0);

        radio28_1 = (RadioButton) findViewById(R.id.radio28_1);
        radio28_2 = (RadioButton) findViewById(R.id.radio28_2);
        radio28_3 = (RadioButton) findViewById(R.id.radio28_3);
        radio28_4 = (RadioButton) findViewById(R.id.radio28_4);
        radio28_5 = (RadioButton) findViewById(R.id.radio28_5);
        radio28_6 = (RadioButton) findViewById(R.id.radio28_6);
        radio28_7 = (RadioButton) findViewById(R.id.radio28_7);
        radio28_8 = (RadioButton) findViewById(R.id.radio28_8);
        radio28_9 = (RadioButton) findViewById(R.id.radio28_9);
        radio28_10 = (RadioButton) findViewById(R.id.radio28_10);
        radio28_11 = (RadioButton) findViewById(R.id.radio28_11);
        radio28_12 = (RadioButton) findViewById(R.id.radio28_12);
        radio28_13 = (RadioButton) findViewById(R.id.radio28_13);
        radio28_0 = (RadioButton) findViewById(R.id.radio28_0);

        radio29_1 = (RadioButton) findViewById(R.id.radio29_1);
        radio29_2 = (RadioButton) findViewById(R.id.radio29_2);
        radio29_3 = (RadioButton) findViewById(R.id.radio29_3);
        radio29_4 = (RadioButton) findViewById(R.id.radio29_4);
        radio29_5 = (RadioButton) findViewById(R.id.radio29_5);
        radio29_6 = (RadioButton) findViewById(R.id.radio29_6);
        radio29_7 = (RadioButton) findViewById(R.id.radio29_7);
        radio29_8 = (RadioButton) findViewById(R.id.radio29_8);
        radio29_9 = (RadioButton) findViewById(R.id.radio29_9);
        radio29_10 = (RadioButton) findViewById(R.id.radio29_10);
        radio29_11 = (RadioButton) findViewById(R.id.radio29_11);
        radio29_12 = (RadioButton) findViewById(R.id.radio29_12);
        radio29_13 = (RadioButton) findViewById(R.id.radio29_13);
        radio29_0 = (RadioButton) findViewById(R.id.radio29_0);

        radio31_1 = (RadioButton) findViewById(R.id.radio31_1);
        radio31_2 = (RadioButton) findViewById(R.id.radio31_2);
        radio31_3 = (RadioButton) findViewById(R.id.radio31_3);
        radio31_4 = (RadioButton) findViewById(R.id.radio31_4);
        radio31_5 = (RadioButton) findViewById(R.id.radio31_5);
        radio31_6 = (RadioButton) findViewById(R.id.radio31_6);
        radio31_7 = (RadioButton) findViewById(R.id.radio31_7);
        radio31_8 = (RadioButton) findViewById(R.id.radio31_8);
        radio31_9 = (RadioButton) findViewById(R.id.radio31_9);
        radio31_10 = (RadioButton) findViewById(R.id.radio31_10);
        radio31_11 = (RadioButton) findViewById(R.id.radio31_11);
        radio31_12 = (RadioButton) findViewById(R.id.radio31_12);
        radio31_13 = (RadioButton) findViewById(R.id.radio31_13);
        radio31_0 = (RadioButton) findViewById(R.id.radio31_0);

        radio32_1 = (RadioButton) findViewById(R.id.radio32_1);
        radio32_2 = (RadioButton) findViewById(R.id.radio32_2);
        radio32_3 = (RadioButton) findViewById(R.id.radio32_3);
        radio32_4 = (RadioButton) findViewById(R.id.radio32_4);
        radio32_5 = (RadioButton) findViewById(R.id.radio32_5);
        radio32_6 = (RadioButton) findViewById(R.id.radio32_6);
        radio32_7 = (RadioButton) findViewById(R.id.radio32_7);
        radio32_8 = (RadioButton) findViewById(R.id.radio32_8);
        radio32_9 = (RadioButton) findViewById(R.id.radio32_9);
        radio32_10 = (RadioButton) findViewById(R.id.radio32_10);
        radio32_11 = (RadioButton) findViewById(R.id.radio32_11);
        radio32_12 = (RadioButton) findViewById(R.id.radio32_12);
        radio32_13 = (RadioButton) findViewById(R.id.radio32_13);
        radio32_0 = (RadioButton) findViewById(R.id.radio32_0);



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
//    if (laDelegacion.matches("�lvaro Obreg�n")) {
//        elDelegado = "Layda Sansores";
//    } else if (laDelegacion.matches("Azcapotzalco")) {
//        elDelegado = "Vidal Llerenas";
//    } else if (laDelegacion.matches("Benito Ju�rez")) {
//        elDelegado = "Santiago Taboada";
//    } else if (laDelegacion.matches("Coyoac�n")) {
//        elDelegado = "Manuel Negrete";
//    } else if (laDelegacion.matches("Cuajimalpa de Morelos")) {
//        elDelegado = "Adri�n Rubalcava";
//    } else if (laDelegacion.matches("Cuauht�moc")) {
//        elDelegado = "N�stor L�pez N��ez";
//    } else if (laDelegacion.matches("Gustavo A. Madero")) {
//        elDelegado = "Francisco Chiguil";
//    } else if (laDelegacion.matches("Iztacalco")) {
//        elDelegado = "Armando Quintero";
//    } else if (laDelegacion.matches("Iztapalapa")) {
//        elDelegado = "Clara Brugada";
//    } else if (laDelegacion.matches("La Magdalena Contreras")) {
//        elDelegado = "Patricia Ximena Ortiz Couturier";
//    } else if (laDelegacion.matches("Miguel Hidalgo")) {
//        elDelegado = "V�ctor Hugo Romo";
//    } else if (laDelegacion.matches("Milpa Alta")) {
//        elDelegado = "Octavio Rivero Villase�or";
//    } else if (laDelegacion.matches("Tl�huac")) {
//        elDelegado = "Raymundo Mart�nez Vite";
//    } else if (laDelegacion.matches("Tlalpan")) {
//        elDelegado = "Patricia Elena Aceves Pastrana";
//    } else if (laDelegacion.matches("Venustiano Carranza")) {
//        elDelegado = "Julio C�sar Moreno";
//    } else if (laDelegacion.matches("Xochimilco")) {
//        elDelegado = "Jos� Carlos Acosta Ruiz";
//    }


//
//

        layCuantosCoches = (LinearLayout) findViewById(R.id.layCuantosCoches);


        textPregunta12 = (TextView) findViewById(R.id.textPregunta12);
        textPreguntaAlcaldes = (TextView) findViewById(R.id.textPreguntaAlcaldes);
        textPregunta16a = (TextView) findViewById(R.id.textPregunta16a);
        textPregunta16b = (TextView) findViewById(R.id.textPregunta16b);
        textPregunta20 = (TextView) findViewById(R.id.textPregunta20);
        textPregunta23 = (TextView) findViewById(R.id.textPregunta23);

        textPregunta24_1 = (TextView) findViewById(R.id.textPregunta24_1);
        textPregunta24_1b = (TextView) findViewById(R.id.textPregunta24_1b);
        textPregunta24_2 = (TextView) findViewById(R.id.textPregunta24_2);
        textPregunta24_2b = (TextView) findViewById(R.id.textPregunta24_2b);
        textPregunta24_3 = (TextView) findViewById(R.id.textPregunta24_3);
        textPregunta24_3b = (TextView) findViewById(R.id.textPregunta24_3b);
        textPregunta24_4 = (TextView) findViewById(R.id.textPregunta24_4);
        textPregunta24_4b = (TextView) findViewById(R.id.textPregunta24_4b);
        textPregunta24_5 = (TextView) findViewById(R.id.textPregunta24_5);
        textPregunta24_5b = (TextView) findViewById(R.id.textPregunta24_5b);
        textPregunta24_6 = (TextView) findViewById(R.id.textPregunta24_6);
        textPregunta24_6b = (TextView) findViewById(R.id.textPregunta24_6b);
        textPregunta24_7 = (TextView) findViewById(R.id.textPregunta24_7);
        textPregunta24_7b = (TextView) findViewById(R.id.textPregunta24_7b);
        textPregunta24_8 = (TextView) findViewById(R.id.textPregunta24_8);
        textPregunta24_8b = (TextView) findViewById(R.id.textPregunta24_8b);
        textPregunta24_9 = (TextView) findViewById(R.id.textPregunta24_9);
        textPregunta24_9b = (TextView) findViewById(R.id.textPregunta24_9b);
        textPregunta24_10 = (TextView) findViewById(R.id.textPregunta24_10);
        textPregunta24_10b = (TextView) findViewById(R.id.textPregunta24_10b);

        textPregunta25 = (TextView) findViewById(R.id.textPregunta25);
        textPregunta26 = (TextView) findViewById(R.id.textPregunta26);

        textPregunta27_1 = (TextView) findViewById(R.id.textPregunta27_1);
        textPregunta27_1b = (TextView) findViewById(R.id.textPregunta27_1b);
        textPregunta27_2 = (TextView) findViewById(R.id.textPregunta27_2);
        textPregunta27_2b = (TextView) findViewById(R.id.textPregunta27_2b);
        textPregunta27_3 = (TextView) findViewById(R.id.textPregunta27_3);
        textPregunta27_3b = (TextView) findViewById(R.id.textPregunta27_3b);
        textPregunta27_4 = (TextView) findViewById(R.id.textPregunta27_4);
        textPregunta27_4b = (TextView) findViewById(R.id.textPregunta27_4b);
        textPregunta27_5 = (TextView) findViewById(R.id.textPregunta27_5);
        textPregunta27_5b = (TextView) findViewById(R.id.textPregunta27_5b);
        textPregunta27_6 = (TextView) findViewById(R.id.textPregunta27_6);
        textPregunta27_6b = (TextView) findViewById(R.id.textPregunta27_6b);
        textPregunta27_7 = (TextView) findViewById(R.id.textPregunta27_7);
        textPregunta27_7b = (TextView) findViewById(R.id.textPregunta27_7b);
        textPregunta27_8 = (TextView) findViewById(R.id.textPregunta27_8);
        textPregunta27_8b = (TextView) findViewById(R.id.textPregunta27_8b);
        textPregunta27_9 = (TextView) findViewById(R.id.textPregunta27_9);
        textPregunta27_9b = (TextView) findViewById(R.id.textPregunta27_9b);
        textPregunta27_10 = (TextView) findViewById(R.id.textPregunta27_10);
        textPregunta27_10b = (TextView) findViewById(R.id.textPregunta27_10b);

        textPregunta28 = (TextView) findViewById(R.id.textPregunta28);
        textPregunta29 = (TextView) findViewById(R.id.textPregunta29);

        textPregunta30_1 = (TextView) findViewById(R.id.textPregunta30_1);
        textPregunta30_1b = (TextView) findViewById(R.id.textPregunta30_1b);
        textPregunta30_2 = (TextView) findViewById(R.id.textPregunta30_2);
        textPregunta30_2b = (TextView) findViewById(R.id.textPregunta30_2b);
        textPregunta30_3 = (TextView) findViewById(R.id.textPregunta30_3);
        textPregunta30_3b = (TextView) findViewById(R.id.textPregunta30_3b);
        textPregunta30_4 = (TextView) findViewById(R.id.textPregunta30_4);
        textPregunta30_4b = (TextView) findViewById(R.id.textPregunta30_4b);
        textPregunta30_5 = (TextView) findViewById(R.id.textPregunta30_5);
        textPregunta30_5b = (TextView) findViewById(R.id.textPregunta30_5b);
        textPregunta30_6 = (TextView) findViewById(R.id.textPregunta30_6);
        textPregunta30_6b = (TextView) findViewById(R.id.textPregunta30_6b);
        textPregunta30_7 = (TextView) findViewById(R.id.textPregunta30_7);
        textPregunta30_7b = (TextView) findViewById(R.id.textPregunta30_7b);
        textPregunta30_8 = (TextView) findViewById(R.id.textPregunta30_8);
        textPregunta30_8b = (TextView) findViewById(R.id.textPregunta30_8b);
        textPregunta30_9 = (TextView) findViewById(R.id.textPregunta30_9);
        textPregunta30_9b = (TextView) findViewById(R.id.textPregunta30_9b);
        textPregunta30_10 = (TextView) findViewById(R.id.textPregunta30_10);
        textPregunta30_10b = (TextView) findViewById(R.id.textPregunta30_10b);

        textPregunta31 = (TextView) findViewById(R.id.textPregunta31);
        textPregunta32 = (TextView) findViewById(R.id.textPregunta32);


        String laDelegacion = sacaAlcaldia(cachaSeccion());
        Log.i(TAG, "cqs -------->> la alcaldia: " + laDelegacion);

        if (laDelegacion.matches("�lvaro Obreg�n")) {
            elDelegado = "Layda Sansores";
        } else if (laDelegacion.matches("Azcapotzalco")) {
            elDelegado = "Vidal Llerenas";
        } else if (laDelegacion.matches("Benito Ju�rez")) {
            elDelegado = "Santiago Taboada";
        } else if (laDelegacion.matches("Coyoac�n")) {
            elDelegado = "Manuel Negrete";
        } else if (laDelegacion.matches("Cuajimalpa de Morelos")) {
            elDelegado = "Adri�n Rubalcava";
        } else if (laDelegacion.matches("Cuauht�moc")) {
            elDelegado = "N�stor L�pez N��ez";
        } else if (laDelegacion.matches("Gustavo A. Madero")) {
            elDelegado = "Francisco Chiguil";
        } else if (laDelegacion.matches("Iztacalco")) {
            elDelegado = "Armando Quintero";
        } else if (laDelegacion.matches("Iztapalapa")) {
            elDelegado = "Clara Brugada";
        } else if (laDelegacion.matches("La Magdalena Contreras")) {
            elDelegado = "Patricia Ximena Ortiz Couturier";
        } else if (laDelegacion.matches("Miguel Hidalgo")) {
            elDelegado = "V�ctor Hugo Romo";
        } else if (laDelegacion.matches("Milpa Alta")) {
            elDelegado = "Octavio Rivero Villase�or";
        } else if (laDelegacion.matches("Tl�huac")) {
            elDelegado = "Raymundo Mart�nez Vite";
        } else if (laDelegacion.matches("Tlalpan")) {
            elDelegado = "Patricia Elena Aceves Pastrana";
        } else if (laDelegacion.matches("Venustiano Carranza")) {
            elDelegado = "Julio C�sar Moreno";
        } else if (laDelegacion.matches("Xochimilco")) {
            elDelegado = "Jos� Carlos Acosta Ruiz";
        }

        textPregunta12.setText("Podr�a decirme el nombre del alcalde/ alcaldesa de " + sacaAlcaldia(cachaSeccion()) +" (No leer el nombre del Alcalde / Alcaldesa)");
        textPreguntaAlcaldes.setText(elDelegado);
        textPregunta16a.setText("�Y en esas las elecciones del a�o 2018, por cu�l partido vot� para alcalde/ alcaldesa de " + sacaAlcaldia(cachaSeccion()));
        textPregunta16b.setText("Y si hubiera podido votar, �por cu�l partido hubiera votado usted para alcalde/ alcaldesa de " + sacaAlcaldia(cachaSeccion()));
        textPregunta20.setText("A usted que cree que beneficie m�s a su alcald�a " + sacaAlcaldia(cachaSeccion()) + ", �qu� contin�e el mismo partido tres a�os m�s o que cambie el partido que gobierna la alcald�a?");
        textPregunta23.setText("Si su alcalde actual " + elDelegado + "  buscara la reelecci�n en el 2021 por un periodo de tres a�os m�s en su puesto  �Qu� tan dispuesto estar�a a votar por �l (ella)?");


//        textPregunta24_2.setText("");
//        textPregunta24_2b.setText("");
//        textPregunta24_3.setText("");
//        textPregunta24_3b.setText("");
//        textPregunta24_4.setText("");
//        textPregunta24_4b.setText("");
//        textPregunta24_5.setText("");
//        textPregunta24_5b.setText("");
//        textPregunta24_6.setText("");
//        textPregunta24_6b.setText("");
//        textPregunta24_7.setText("");
//        textPregunta24_7b.setText("");
//        textPregunta24_8.setText("");
//        textPregunta24_8b.setText("");
//        textPregunta24_9.setText("");
//        textPregunta24_9b.setText("");
//        textPregunta24_10.setText("");
//
//
//
//
//        textPregunta27_1.setText("");
//        textPregunta27_1b.setText("");
//        textPregunta27_2.setText("");
//        textPregunta27_2b.setText("");
//        textPregunta27_3.setText("");
//        textPregunta27_3b.setText("");
//        textPregunta27_4.setText("");
//        textPregunta27_4b.setText("");
//        textPregunta27_5.setText("");
//        textPregunta27_5b.setText("");
//        textPregunta27_6.setText("");
//        textPregunta27_6b.setText("");
//        textPregunta27_7.setText("");
//        textPregunta27_7b.setText("");
//        textPregunta27_8.setText("");
//        textPregunta27_8b.setText("");
//        textPregunta27_9.setText("");
//        textPregunta27_9b.setText("");
//        textPregunta27_10.setText("");
//
//        textPregunta28.setText("");
//        textPregunta29.setText("");
//
//        textPregunta30_1.setText("");
//        textPregunta30_1b.setText("");
//        textPregunta30_2.setText("");
//        textPregunta30_2b.setText("");
//        textPregunta30_3.setText("");
//        textPregunta30_3b.setText("");
//        textPregunta30_4.setText("");
//        textPregunta30_4b.setText("");
//        textPregunta30_5.setText("");
//        textPregunta30_5b.setText("");
//        textPregunta30_6.setText("");
//        textPregunta30_6b.setText("");
//        textPregunta30_7.setText("");
//        textPregunta30_7b.setText("");
//        textPregunta30_8.setText("");
//        textPregunta30_8b.setText("");
//        textPregunta30_9.setText("");
//        textPregunta30_9b.setText("");
//        textPregunta30_10.setText("");
//
//        textPregunta31.setText("");
//        textPregunta32.setText("");


        cargaAlcaldes(laDelegacion);
        cargaPartidoAlcaldes(laDelegacion);
        cargaAlcaldesMorena(laDelegacion);

        cargaFederales(laDelegacion,cachafederal());
        cargaPartidoFederales(laDelegacion,cachafederal());
        cargaFederalesMorena(laDelegacion,cachafederal());
        
        cargaLocales(laDelegacion,cachalocal());
        cargaPartidoLocales(laDelegacion,cachalocal());
        cargaLocalesMorena(laDelegacion,cachalocal());

        for (int i=0;i<partidos_alcaldes.size();i++){

            String cand;
            cand=partidos_alcaldes.get(i);

            if(cand.contains("PAN")){
                candidato_1_alcalde=partidos_alcaldes.get(i);
            }
            if(cand.contains("PRI")){
                candidato_2_alcalde=partidos_alcaldes.get(i);

            }
            if(cand.contains("MORENA")){
                candidato_3_alcalde=partidos_alcaldes.get(i);

            }
            if(cand.contains("PRD")){
                candidato_4_alcalde=partidos_alcaldes.get(i);

            }

        }

        for (int i=0;i<partidos_federales.size();i++){

            String cand;
            cand=partidos_federales.get(i);

            if(cand.contains("PAN")){
                candidato_1_federal=partidos_federales.get(i);
            }
            if(cand.contains("PRI")){
                candidato_2_federal=partidos_federales.get(i);

            }
            if(cand.contains("MORENA")){
                candidato_3_federal=partidos_federales.get(i);

            }
            if(cand.contains("PRD")){
                candidato_4_federal=partidos_federales.get(i);

            }

        }

        for (int i=0;i<partidos_locales.size();i++){

            String cand;
            cand=partidos_locales.get(i);

            if(cand.contains("PAN")){
                candidato_1_local=partidos_locales.get(i);
            }
            if(cand.contains("PRI")){
                candidato_2_local=partidos_locales.get(i);

            }
            if(cand.contains("MORENA")){
                candidato_3_local=partidos_locales.get(i);

            }
            if(cand.contains("PRD")){
                candidato_4_local=partidos_locales.get(i);

            }

        }


        try {
            if (alcaldes_morena.get(0) != null) {
                Log.i(TAG, "cqs --->> alcalde 1 " + "esta");
                radio26_3.setText(alcaldes_morena.get(0));
            }
        } catch (Exception e) {
            radio26_3.setVisibility(View.GONE);
        }
        try {
            if (alcaldes_morena.get(1) != null) {
                Log.i(TAG, "cqs --->> alcalde 1 " + "esta");
                radio26_4.setText(alcaldes_morena.get(1));
            }
        } catch (Exception e) {
            radio26_4.setVisibility(View.GONE);
        }
        try {
            if (alcaldes_morena.get(2) != null) {
                Log.i(TAG, "cqs --->> alcalde 1 " + "esta");
                radio26_5.setText(alcaldes_morena.get(2));
            }
        } catch (Exception e) {
            radio26_5.setVisibility(View.GONE);
        }
        try {
            if (alcaldes_morena.get(3) != null) {
                Log.i(TAG, "cqs --->> alcalde 1 " + "esta");
                radio26_6.setText(alcaldes_morena.get(3));
            }
        } catch (Exception e) {
            radio26_6.setVisibility(View.GONE);
        }


        try {
            if (federales_morena.get(0) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio29_3.setText(federales_morena.get(0));
            }
        } catch (Exception e) {
            radio29_3.setVisibility(View.GONE);
        }
        try {
            if (federales_morena.get(1) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio29_4.setText(federales_morena.get(1));
            }
        } catch (Exception e) {
            radio29_4.setVisibility(View.GONE);
        }
        try {
            if (federales_morena.get(2) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio29_5.setText(federales_morena.get(2));
            }
        } catch (Exception e) {
            radio29_5.setVisibility(View.GONE);
        }
        try {
            if (federales_morena.get(3) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio29_6.setText(federales_morena.get(3));
            }
        } catch (Exception e) {
            radio29_6.setVisibility(View.GONE);
        }

        try {
            if (locales_morena.get(0) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio32_3.setText(locales_morena.get(0));
            }
        } catch (Exception e) {
            radio32_3.setVisibility(View.GONE);
        }
        try {
            if (locales_morena.get(1) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio32_4.setText(locales_morena.get(1));
            }
        } catch (Exception e) {
            radio32_4.setVisibility(View.GONE);
        }
        try {
            if (locales_morena.get(2) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio32_5.setText(locales_morena.get(2));
            }
        } catch (Exception e) {
            radio32_5.setVisibility(View.GONE);
        }
        try {
            if (locales_morena.get(3) != null) {
                Log.i(TAG, "cqs --->> federal 1 " + "esta");
                radio32_6.setText(locales_morena.get(3));
            }
        } catch (Exception e) {
            radio32_6.setVisibility(View.GONE);
        }



        textPregunta25.setText("Si los �nicos candidatos a alcalde fueran " +(candidato_1_alcalde!=null?candidato_1_alcalde+", ":"")+(candidato_2_alcalde!=null?candidato_2_alcalde+", ":"")+(candidato_3_alcalde!=null?candidato_3_alcalde+", ":"")+(candidato_4_alcalde!=null?candidato_4_alcalde+", ":"")+" �por cu�l votar�a usted?");
        textPregunta26.setText("Usted a qui�n de los siguientes candidatos prefiere que sea el candidato MORENA a la alcald�a? ");

        textPregunta28.setText("Si los �nicos candidatos a diputado federal fueran " +(candidato_1_federal!=null?candidato_1_federal+", ":"")+(candidato_2_federal!=null?candidato_2_federal+", ":"")+(candidato_3_federal!=null?candidato_3_federal+", ":"")+(candidato_4_federal!=null?candidato_4_federal+", ":"")+" �por cu�l votar�a usted?");
        textPregunta29.setText("Usted a qui�n de los siguientes candidatos prefiere que sea el candidato MORENA a diputado federal? ");

        textPregunta31.setText("Si los �nicos candidatos a diputado local fueran " +(candidato_1_local!=null?candidato_1_local+", ":"")+(candidato_2_local!=null?candidato_2_local+", ":"")+(candidato_3_local!=null?candidato_3_local+", ":"")+(candidato_4_local!=null?candidato_4_local+", ":"")+" �por cu�l votar�a usted?");
        textPregunta32.setText("Usted a qui�n de los siguientes candidatos prefiere que sea el candidato MORENA a dipuatdo local? ");


        Log.i(TAG, "cqs ------->> los Alcaldes: " + alcaldes);
        Log.i(TAG, "cqs ------->> cuantos Alcaldes: " + alcaldes.size());

        //////////////  opciones alcaldes  /////////
        
        try {
            if (alcaldes.get(0) != null) {
                Log.i(TAG, "cqs --->> alcalde 1 " + "esta");
                lay24_1.setVisibility(View.VISIBLE);
                textPregunta24_1.setText(alcaldes.get(0).toString());
                textPregunta24_1b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(0).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_1.setVisibility(View.GONE);
            lay24_1a.setVisibility(View.GONE);
            lay24_1b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(1) != null) {
                Log.i(TAG, "cqs --->> alcalde 2  " + "esta");
                lay24_2.setVisibility(View.VISIBLE);
                textPregunta24_2.setText(alcaldes.get(1).toString());
                textPregunta24_2b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(1).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_2.setVisibility(View.GONE);
            lay24_2a.setVisibility(View.GONE);
            lay24_2b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(2) != null) {
                Log.i(TAG, "cqs --->> alcalde 3 " + "esta");
                lay24_3.setVisibility(View.VISIBLE);
                textPregunta24_3.setText(alcaldes.get(2).toString());
                textPregunta24_3b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(2).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_3.setVisibility(View.GONE);
            lay24_3a.setVisibility(View.GONE);
            lay24_3b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(3) != null) {
                Log.i(TAG, "cqs --->> alcalde 4 " + "esta");
                lay24_4.setVisibility(View.VISIBLE);
                textPregunta24_4.setText(alcaldes.get(3).toString());
                textPregunta24_4b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(3).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_4.setVisibility(View.GONE);
            lay24_4a.setVisibility(View.GONE);
            lay24_4b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(4) != null) {
                Log.i(TAG, "cqs --->> alcalde 5 " + "esta");
                lay24_5.setVisibility(View.VISIBLE);
                textPregunta24_5.setText(alcaldes.get(4).toString());
                textPregunta24_5b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(4).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_5.setVisibility(View.GONE);
            lay24_5a.setVisibility(View.GONE);
            lay24_5b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(5) != null) {
                Log.i(TAG, "cqs --->> alcalde 6 " + "esta");
                lay24_6.setVisibility(View.VISIBLE);
                textPregunta24_6.setText(alcaldes.get(5).toString());
                textPregunta24_6b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(5).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_6.setVisibility(View.GONE);
            lay24_6a.setVisibility(View.GONE);
            lay24_6b.setVisibility(View.GONE);
        }


        try {
            if (alcaldes.get(6) != null) {
                Log.i(TAG, "cqs --->> alcalde 7 " + "esta");
                lay24_7.setVisibility(View.VISIBLE);
                textPregunta24_7.setText(alcaldes.get(6).toString());
                textPregunta24_7b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(6).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_7.setVisibility(View.GONE);
            lay24_7a.setVisibility(View.GONE);
            lay24_7b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(7) != null) {
                Log.i(TAG, "cqs --->> alcalde 8 " + "esta");
                lay24_8.setVisibility(View.VISIBLE);
                textPregunta24_8.setText(alcaldes.get(7).toString());
                textPregunta24_8b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(7).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_8.setVisibility(View.GONE);
            lay24_8a.setVisibility(View.GONE);
            lay24_8b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(8) != null) {
                Log.i(TAG, "cqs --->> alcalde 9 " + "esta");
                lay24_9.setVisibility(View.VISIBLE);
                textPregunta24_9.setText(alcaldes.get(8).toString());
                textPregunta24_9b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(8).toString() + "  para alcalde?");
            }
        } catch (Exception e) {
            lay24_9.setVisibility(View.GONE);
            lay24_9a.setVisibility(View.GONE);
            lay24_9b.setVisibility(View.GONE);
        }

        try {
            if (alcaldes.get(9) != null) {
                Log.i(TAG, "cqs --->> alcalde 10 " + "esta");
                lay24_6.setVisibility(View.VISIBLE);
                textPregunta24_6.setText(alcaldes.get(9).toString());
                textPregunta24_6b.setText("�Estar�a usted dispuesto o no a votar por " + alcaldes.get(9).toString() + " para alcalde?");
            }
        } catch (Exception e) {
            lay24_10.setVisibility(View.GONE);
            lay24_10a.setVisibility(View.GONE);
            lay24_10b.setVisibility(View.GONE);
        }


        //////////////  opciones federales  /////////

        try {
            if (federales.get(0) != null) {
                Log.i(TAG, "cqs --->> federales 1 " + "esta");
                lay27_1.setVisibility(View.VISIBLE);
                textPregunta27_1.setText(federales.get(0).toString());
                textPregunta27_1b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(0).toString() + " para diputado federal?");
            }
        } catch (Exception e) {
            lay27_1.setVisibility(View.GONE);
            lay27_1a.setVisibility(View.GONE);
            lay27_1b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(1) != null) {
                Log.i(TAG, "cqs --->> federales 2  " + "esta");
                lay27_2.setVisibility(View.VISIBLE);
                textPregunta27_2.setText(federales.get(1).toString());
                textPregunta27_2b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(1).toString() + " para diputado federal?");
            }
        } catch (Exception e) {
            lay27_2.setVisibility(View.GONE);
            lay27_2a.setVisibility(View.GONE);
            lay27_2b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(2) != null) {
                Log.i(TAG, "cqs --->> federales 3 " + "esta");
                lay27_3.setVisibility(View.VISIBLE);
                textPregunta27_3.setText(federales.get(2).toString());
                textPregunta27_3b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(2).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_3.setVisibility(View.GONE);
            lay27_3a.setVisibility(View.GONE);
            lay27_3b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(3) != null) {
                Log.i(TAG, "cqs --->> federales 4 " + "esta");
                lay27_4.setVisibility(View.VISIBLE);
                textPregunta27_4.setText(federales.get(3).toString());
                textPregunta27_4b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(3).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_4.setVisibility(View.GONE);
            lay27_4a.setVisibility(View.GONE);
            lay27_4b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(4) != null) {
                Log.i(TAG, "cqs --->> federales 5 " + "esta");
                lay27_5.setVisibility(View.VISIBLE);
                textPregunta27_5.setText(federales.get(4).toString());
                textPregunta27_5b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(4).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_5.setVisibility(View.GONE);
            lay27_5a.setVisibility(View.GONE);
            lay27_5b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(5) != null) {
                Log.i(TAG, "cqs --->> federales 6 " + "esta");
                lay27_6.setVisibility(View.VISIBLE);
                textPregunta27_6.setText(federales.get(5).toString());
                textPregunta27_6b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(5).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_6.setVisibility(View.GONE);
            lay27_6a.setVisibility(View.GONE);
            lay27_6b.setVisibility(View.GONE);
        }


        try {
            if (federales.get(6) != null) {
                Log.i(TAG, "cqs --->> federales 7 " + "esta");
                lay27_7.setVisibility(View.VISIBLE);
                textPregunta27_7.setText(federales.get(6).toString());
                textPregunta27_7b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(6).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_7.setVisibility(View.GONE);
            lay27_7a.setVisibility(View.GONE);
            lay27_7b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(7) != null) {
                Log.i(TAG, "cqs --->> federales 8 " + "esta");
                lay27_8.setVisibility(View.VISIBLE);
                textPregunta27_8.setText(federales.get(7).toString());
                textPregunta27_8b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(7).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_8.setVisibility(View.GONE);
            lay27_8a.setVisibility(View.GONE);
            lay27_8b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(8) != null) {
                Log.i(TAG, "cqs --->> federales 9 " + "esta");
                lay27_9.setVisibility(View.VISIBLE);
                textPregunta27_9.setText(federales.get(8).toString());
                textPregunta27_9b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(8).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_9.setVisibility(View.GONE);
            lay27_9a.setVisibility(View.GONE);
            lay27_9b.setVisibility(View.GONE);
        }

        try {
            if (federales.get(9) != null) {
                Log.i(TAG, "cqs --->> federales 10 " + "esta");
                lay27_6.setVisibility(View.VISIBLE);
                textPregunta27_6.setText(federales.get(9).toString());
                textPregunta27_6b.setText("�Estar�a usted dispuesto o no a votar por " + federales.get(9).toString() + "  para diputado federal?");
            }
        } catch (Exception e) {
            lay27_10.setVisibility(View.GONE);
            lay27_10a.setVisibility(View.GONE);
            lay27_10b.setVisibility(View.GONE);
        }


        //////////////  opciones locales  /////////

        try {
            if (locales.get(0) != null) {
                Log.i(TAG, "cqs --->> locales 1 " + "esta");
                lay30_1.setVisibility(View.VISIBLE);
                textPregunta30_1.setText(locales.get(0).toString());
                textPregunta30_1b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(0).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_1.setVisibility(View.GONE);
            lay30_1a.setVisibility(View.GONE);
            lay30_1b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(1) != null) {
                Log.i(TAG, "cqs --->> locales 2  " + "esta");
                lay30_2.setVisibility(View.VISIBLE);
                textPregunta30_2.setText(locales.get(1).toString());
                textPregunta30_2b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(1).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_2.setVisibility(View.GONE);
            lay30_2a.setVisibility(View.GONE);
            lay30_2b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(2) != null) {
                Log.i(TAG, "cqs --->> locales 3 " + "esta");
                lay30_3.setVisibility(View.VISIBLE);
                textPregunta30_3.setText(locales.get(2).toString());
                textPregunta30_3b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(2).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_3.setVisibility(View.GONE);
            lay30_3a.setVisibility(View.GONE);
            lay30_3b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(3) != null) {
                Log.i(TAG, "cqs --->> locales 4 " + "esta");
                lay30_4.setVisibility(View.VISIBLE);
                textPregunta30_4.setText(locales.get(3).toString());
                textPregunta30_4b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(3).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_4.setVisibility(View.GONE);
            lay30_4a.setVisibility(View.GONE);
            lay30_4b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(4) != null) {
                Log.i(TAG, "cqs --->> locales 5 " + "esta");
                lay30_5.setVisibility(View.VISIBLE);
                textPregunta30_5.setText(locales.get(4).toString());
                textPregunta30_5b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(4).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_5.setVisibility(View.GONE);
            lay30_5a.setVisibility(View.GONE);
            lay30_5b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(5) != null) {
                Log.i(TAG, "cqs --->> locales 6 " + "esta");
                lay30_6.setVisibility(View.VISIBLE);
                textPregunta30_6.setText(locales.get(5).toString());
                textPregunta30_6b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(5).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_6.setVisibility(View.GONE);
            lay30_6a.setVisibility(View.GONE);
            lay30_6b.setVisibility(View.GONE);
        }


        try {
            if (locales.get(6) != null) {
                Log.i(TAG, "cqs --->> locales 7 " + "esta");
                lay30_7.setVisibility(View.VISIBLE);
                textPregunta30_7.setText(locales.get(6).toString());
                textPregunta30_7b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(6).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_7.setVisibility(View.GONE);
            lay30_7a.setVisibility(View.GONE);
            lay30_7b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(7) != null) {
                Log.i(TAG, "cqs --->> locales 8 " + "esta");
                lay30_8.setVisibility(View.VISIBLE);
                textPregunta30_8.setText(locales.get(7).toString());
                textPregunta30_8b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(7).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_8.setVisibility(View.GONE);
            lay30_8a.setVisibility(View.GONE);
            lay30_8b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(8) != null) {
                Log.i(TAG, "cqs --->> locales 9 " + "esta");
                lay30_9.setVisibility(View.VISIBLE);
                textPregunta30_9.setText(locales.get(8).toString());
                textPregunta30_9b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(8).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_9.setVisibility(View.GONE);
            lay30_9a.setVisibility(View.GONE);
            lay30_9b.setVisibility(View.GONE);
        }

        try {
            if (locales.get(9) != null) {
                Log.i(TAG, "cqs --->> locales 10 " + "esta");
                lay30_6.setVisibility(View.VISIBLE);
                textPregunta30_6.setText(locales.get(9).toString());
                textPregunta30_6b.setText("�Estar�a usted dispuesto o no a votar por " + locales.get(9).toString() + "  para diputado local?");
            }
        } catch (Exception e) {
            lay30_10.setVisibility(View.GONE);
            lay30_10a.setVisibility(View.GONE);
            lay30_10b.setVisibility(View.GONE);
        }
        
        

//    if(alcaldes.get(0) !=null){
//        String esta="esta";
//        Log.i(TAG,"cqs --->> elemento 0 "+esta);
//        lay24_1.setVisibility(View.VISIBLE);
//        textPregunta24_1.setText(alcaldes.get(0).toString());
//        textPregunta24_1b.setText("�Estar�a usted dispuesto o no a votar por "+alcaldes.get(0).toString()+"  para alcalde?");
//    }else{
//        lay24_1.setVisibility(View.GONE);
//        lay24_1a.setVisibility(View.GONE);
//        lay24_1b.setVisibility(View.GONE);
//    }
//    if(alcaldes.get(1) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 1 "+esta);}
//    if(alcaldes.get(2) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 2 "+esta);}
//    if(alcaldes.get(3) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 3 "+esta);}
//    if(alcaldes.get(4) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 4 "+esta);}
//    if(alcaldes.get(5) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 5 "+esta);}
//    if(alcaldes.get(6) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 6 "+esta);}
//    if(alcaldes.get(7) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 7 "+esta);}
//    if(alcaldes.get(8) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 8 "+esta);}
//    if(alcaldes.get(9) !=null){String esta="esta"; Log.i(TAG,"cqs --->> elemento 9 "+esta);}

        lay17a.setVisibility(View.GONE);


        rdPregunta1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op1 = "0";
                } else if (checkedId == R.id.radio2) {
                    op1 = "1";
                } else if (checkedId == R.id.radio3) {
                    op1 = "Televisi�n";
                } else if (checkedId == R.id.radio4) {
                    op1 = "Radio";
                } else if (checkedId == R.id.radio5) {
                    op1 = "Peri�dico";
                } else if (checkedId == R.id.radio6) {
                    op1 = "Redes sociales";
                } else if (checkedId == R.id.radio7) {
                    op1 = "Internet";
                } else if (checkedId == R.id.radio8) {
                    op1 = "Otra";
                } else if (checkedId == R.id.radio0) {
                    op1 = "No sabe / No contest�";
                }
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op2 = "0";
                } else if (checkedId == R.id.radio2) {
                    op2 = "2";
                } else if (checkedId == R.id.radio3) {
                    op2 = "S�";
                } else if (checkedId == R.id.radio4) {
                    op2 = "No";
                } else if (checkedId == R.id.radio0) {
                    op2 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op3 = "0";
                } else if (checkedId == R.id.radio2) {
                    op3 = "3";
                } else if (checkedId == R.id.radio3) {
                    op3 = "S�";
                } else if (checkedId == R.id.radio4) {
                    op3 = "No";
                } else if (checkedId == R.id.radio0) {
                    op3 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op4 = "0";
                } else if (checkedId == R.id.radio2) {
                    op4 = "4";
                } else if (checkedId == R.id.radio3) {
                    op4 = "S�";
                } else if (checkedId == R.id.radio4) {
                    op4 = "No";
                } else if (checkedId == R.id.radio0) {
                    op4 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op5 = "0";
                } else if (checkedId == R.id.radio2) {
                    op5 = "5";
                } else if (checkedId == R.id.radio3) {
                    op5 = "Mejor";
                } else if (checkedId == R.id.radio4) {
                    op5 = "Igual de bien";
                } else if (checkedId == R.id.radio5) {
                    op5 = "Igual de mal";
                } else if (checkedId == R.id.radio6) {
                    op5 = "Peor";
                } else if (checkedId == R.id.radio0) {
                    op5 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op6 = "6";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio3) {
                    op6 = "Inseguridad";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio4) {
                    op6 = "Desempleo";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio5) {
                    op6 = "Asesinatos/ Violencia";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio6) {
                    op6 = "Mala econom�a/ crisis";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio7) {
                    op6 = "Bajos salarios";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio8) {
                    op6 = "Inflaci�n/ alza de precios";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio9) {
                    op6 = "Corrupci�n";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio10) {
                    op6 = "Pobreza";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio11) {
                    op6 = "Educaci�n";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio12) {
                    op6 = "Otro (registrar)";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio13) {
                    op6 = "Ninguno";
                    editPregunta6.setText("");
                } else if (checkedId == R.id.radio0) {
                    op6 = "No sabe / No contest�";
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
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
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
                } else if (checkedId == R.id.radio2) {
                    op7 = "7";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio3) {
                    op7 = "Inseguridad";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio4) {
                    op7 = "Falta de agua";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio5) {
                    op7 = "Asaltos";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio6) {
                    op7 = "Narcomenudeo/ venta de drogas";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio7) {
                    op7 = "Pavimentaci�n/ baches";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio8) {
                    op7 = "Falta de transporte";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio9) {
                    op7 = "Mala econom�a";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio10) {
                    op7 = "Pobreza";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio11) {
                    op7 = "Educaci�n";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio12) {
                    op7 = "Otro (registrar)";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio13) {
                    op7 = "Ninguno";
                    editPregunta7.setText("");
                } else if (checkedId == R.id.radio0) {
                    op7 = "No sabe / No contest�";
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
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
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
                } else if (checkedId == R.id.radio2) {
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
                } else if (checkedId == R.id.radio2) {
                    oppc_1 = "pc_1";
                } else if (checkedId == R.id.radio3) {
                    oppc_1 = "Bien";
                } else if (checkedId == R.id.radio4) {
                    oppc_1 = "Regular";
                } else if (checkedId == R.id.radio5) {
                    oppc_1 = "Mal";
                } else if (checkedId == R.id.radio0) {
                    oppc_1 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPreguntapc_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    oppc_2 = "0";
                } else if (checkedId == R.id.radio2) {
                    oppc_2 = "pc_2";
                } else if (checkedId == R.id.radio3) {
                    oppc_2 = "Bien";
                } else if (checkedId == R.id.radio4) {
                    oppc_2 = "Regular";
                } else if (checkedId == R.id.radio5) {
                    oppc_2 = "Mal";
                } else if (checkedId == R.id.radio0) {
                    oppc_2 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPreguntapc_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    oppc_3 = "0";
                } else if (checkedId == R.id.radio2) {
                    oppc_3 = "pc_3";
                } else if (checkedId == R.id.radio3) {
                    oppc_3 = "Bien";
                } else if (checkedId == R.id.radio4) {
                    oppc_3 = "Regular";
                } else if (checkedId == R.id.radio5) {
                    oppc_3 = "Mal";
                } else if (checkedId == R.id.radio0) {
                    oppc_3 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPreguntapc_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    oppc_4 = "0";
                } else if (checkedId == R.id.radio2) {
                    oppc_4 = "pc_4";
                } else if (checkedId == R.id.radio3) {
                    oppc_4 = "Bien";
                } else if (checkedId == R.id.radio4) {
                    oppc_4 = "Regular";
                } else if (checkedId == R.id.radio5) {
                    oppc_4 = "Mal";
                } else if (checkedId == R.id.radio0) {
                    oppc_4 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op8 = "8";
                } else if (checkedId == R.id.radio3) {
                    op8 = "De acuerdo";
                } else if (checkedId == R.id.radio4) {
                    op8 = "Ni de acuerdo, ni en desacuerdo";
                } else if (checkedId == R.id.radio5) {
                    op8 = "En desacuerdo";
                } else if (checkedId == R.id.radio0) {
                    op8 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op9 = "0";
                } else if (checkedId == R.id.radio2) {
                    op9 = "9";
                } else if (checkedId == R.id.radio3) {
                    op9 = "De acuerdo";
                } else if (checkedId == R.id.radio4) {
                    op9 = "Ni de acuerdo, ni en desacuerdo";
                } else if (checkedId == R.id.radio5) {
                    op9 = "En desacuerdo";
                } else if (checkedId == R.id.radio0) {
                    op9 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op10 = "0";
                } else if (checkedId == R.id.radio2) {
                    op10 = "10";
                } else if (checkedId == R.id.radio3) {
                    op10 = "De acuerdo";
                } else if (checkedId == R.id.radio4) {
                    op10 = "Ni de acuerdo, ni en desacuerdo";
                } else if (checkedId == R.id.radio5) {
                    op10 = "En desacuerdo";
                } else if (checkedId == R.id.radio0) {
                    op10 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta11.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op11 = "0";
                } else if (checkedId == R.id.radio2) {
                    op11 = "11";
                } else if (checkedId == R.id.radio3) {
                    op11 = "De acuerdo";
                } else if (checkedId == R.id.radio4) {
                    op11 = "Ni de acuerdo, ni en desacuerdo";
                } else if (checkedId == R.id.radio5) {
                    op11 = "En desacuerdo";
                } else if (checkedId == R.id.radio0) {
                    op11 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta12.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op12 = "0";
                } else if (checkedId == R.id.radio2) {
                    op12 = "12";
                }
//            else if (checkedId == R.id.radio3) {
//                op12 = "Combo de alcaldes (aparecer� de acuerdo a la secci�n electoral)";
//            }
                else if (checkedId == R.id.radio4) {
                    op12 = "Correcto";
                } else if (checkedId == R.id.radio5) {
                    op12 = "Incorrecto";
                } else if (checkedId == R.id.radio0) {
                    op12 = "No sabe / No contest�";
                }
            }
        });


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta13.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op13 = "0";
                } else if (checkedId == R.id.radio2) {
                    op13 = "13";
                } else if (checkedId == R.id.radio3) {
                    op13 = "S�";
                } else if (checkedId == R.id.radio4) {
                    op13 = "No";
                } else if (checkedId == R.id.radio0) {
                    op13 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta14.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op14 = "0";
                } else if (checkedId == R.id.radio2) {
                    op14 = "14";
                } else if (checkedId == R.id.radio3) {
                    op14 = "Totalmente";
                } else if (checkedId == R.id.radio4) {
                    op14 = "Bastante";
                } else if (checkedId == R.id.radio5) {
                    op14 = "Poco";
                } else if (checkedId == R.id.radio6) {
                    op14 = "Nada/ no va a votar";
                } else if (checkedId == R.id.radio0) {
                    op14 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta15.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op15 = "0";
                } else if (checkedId == R.id.radio2) {
                    op15 = "15";
                } else if (checkedId == R.id.radio3) {
                    op15 = "Totalmente";
                } else if (checkedId == R.id.radio4) {
                    op15 = "Bastante";
                } else if (checkedId == R.id.radio5) {
                    op15 = "Poco";
                } else if (checkedId == R.id.radio6) {
                    op15 = "Nada/ no va a votar";
                } else if (checkedId == R.id.radio0) {
                    op15 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta16.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op16 = "0";
                } else if (checkedId == R.id.radio2) {
                    op16 = "16";
                } else if (checkedId == R.id.radio3) {
                    op16 = "S�";
                    lay16a .setVisibility(View.VISIBLE);	rdPregunta16a.clearCheck();	op16a="sin datos";
                    lay16b .setVisibility(View.GONE);	rdPregunta16b.clearCheck();	op16b="No aplica";
                } else if (checkedId == R.id.radio4) {
                    op16 = "No";
                    lay16a .setVisibility(View.GONE);	rdPregunta16a.clearCheck();	op16a="No aplica";
                    lay16b .setVisibility(View.VISIBLE);	rdPregunta16b.clearCheck();	op16b="No aplica";
                } else if (checkedId == R.id.radio0) {
                    op16 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op16a = "16a";
                } else if (checkedId == R.id.radio3) {
                    op16a = "Morena";
                } else if (checkedId == R.id.radio4) {
                    op16a = "PRI";
                } else if (checkedId == R.id.radio5) {
                    op16a = "PAN";
                } else if (checkedId == R.id.radio6) {
                    op16a = "PRD";
                } else if (checkedId == R.id.radio7) {
                    op16a = "P.Verde";
                } else if (checkedId == R.id.radio8) {
                    op16a = "Otro";
                } else if (checkedId == R.id.radio0) {
                    op16a = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta16b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op16b = "0";
                } else if (checkedId == R.id.radio2) {
                    op16b = "16b";
                } else if (checkedId == R.id.radio3) {
                    op16b = "Morena";
                } else if (checkedId == R.id.radio4) {
                    op16b = "PRI";
                } else if (checkedId == R.id.radio5) {
                    op16b = "PAN";
                } else if (checkedId == R.id.radio6) {
                    op16b = "PRD";
                } else if (checkedId == R.id.radio7) {
                    op16b = "P.Verde";
                } else if (checkedId == R.id.radio8) {
                    op16b = "Otro";
                } else if (checkedId == R.id.radio0) {
                    op16b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta17.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op17 = "0";
                } else if (checkedId == R.id.radio2) {
                    op17 = "17";
                } else if (checkedId == R.id.radio3) {
                    op17 = "Priista";
                } else if (checkedId == R.id.radio4) {
                    op17 = "Morenista";
                } else if (checkedId == R.id.radio5) {
                    op17 = "Panista";
                } else if (checkedId == R.id.radio6) {
                    op17 = "Perredista";
                } else if (checkedId == R.id.radio7) {
                    op17 = "PT";
                } else if (checkedId == R.id.radio8) {
                    op17 = "Partido Verde";
                } else if (checkedId == R.id.radio9) {
                    op17 = "Movimiento Ciudadano";
                } else if (checkedId == R.id.radio10) {
                    op17 = "De ning�n partido";
                } else if (checkedId == R.id.radio0) {
                    op17 = "No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta17a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op17a = "0";
                } else if (checkedId == R.id.radio2) {
                    op17a = "17a";
                } else if (checkedId == R.id.radio3) {
                    op17a = "Completamente seguro";
                } else if (checkedId == R.id.radio4) {
                    op17a = "Pudiera cambiar de opci�n";
                } else if (checkedId == R.id.radio0) {
                    op17a = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta18.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op18 = "0";
                } else if (checkedId == R.id.radio2) {
                    op18 = "18";
                } else if (checkedId == R.id.radio3) {
                    op18 = "Morena";
                } else if (checkedId == R.id.radio4) {
                    op18 = "PRI";
                } else if (checkedId == R.id.radio5) {
                    op18 = "PAN";
                } else if (checkedId == R.id.radio6) {
                    op18 = "PRD";
                } else if (checkedId == R.id.radio7) {
                    op18 = "PT";
                } else if (checkedId == R.id.radio8) {
                    op18 = "P.Verde";
                } else if (checkedId == R.id.radio9) {
                    op18 = "Movimiento Ciudadano";
                } else if (checkedId == R.id.radio0) {
                    op18 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta19.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op19 = "0";
                } else if (checkedId == R.id.radio2) {
                    op19 = "19";
                } else if (checkedId == R.id.radio3) {
                    op19 = "Claudia Sheinbaum/ Morena/ PT/ Encuentro social";
                    lay19a.setVisibility(View.VISIBLE);
                    rdPregunta19a.clearCheck();
                    op19a = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op19 = "Alejandra Barrales/ PAN/ PRD/ MC";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio5) {
                    op19 = "Mikel Arriola/ PRI";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio6) {
                    op19 = "Mariana Boy/ PVEM";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio7) {
                    op19 = "Lorena Osornio/ Independiente";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio8) {
                    op19 = "Marco Rasc�n/ PHCDMX";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio9) {
                    op19 = "Purificaci�n Carpinteyro/ PANAL";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio10) {
                    op19 = "No voto";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio11) {
                    op19 = "Anul� su voto";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                } else if (checkedId == R.id.radio0) {
                    op19 = "No sabe / No contest�";
                    lay19a.setVisibility(View.GONE);
                    rdPregunta19a.clearCheck();
                    op19a = "No aplica";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta19a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op19a = "0";
                } else if (checkedId == R.id.radio2) {
                    op19a = "19a";
                } else if (checkedId == R.id.radio3) {
                    op19a = "Si, fue la mejor";
                } else if (checkedId == R.id.radio4) {
                    op19a = "En parte";
                } else if (checkedId == R.id.radio5) {
                    op19a = "No fue la mejor";
                } else if (checkedId == R.id.radio0) {
                    op19a = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta19b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op19b = "0";
                } else if (checkedId == R.id.radio2) {
                    op19b = "19b";
                } else if (checkedId == R.id.radio3) {
                    op19b = "Bien";
                } else if (checkedId == R.id.radio4) {
                    op19b = "Regular";
                } else if (checkedId == R.id.radio5) {
                    op19b = "Mal";
                } else if (checkedId == R.id.radio0) {
                    op19b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta20.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op20 = "0";
                } else if (checkedId == R.id.radio2) {
                    op20 = "20";
                } else if (checkedId == R.id.radio3) {
                    op20 = "Que contin�e el mismo partido";
                } else if (checkedId == R.id.radio4) {
                    op20 = "Que se alterne el partido que gobierna";
                } else if (checkedId == R.id.radio0) {
                    op20 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta21.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op21 = "0";
                } else if (checkedId == R.id.radio2) {
                    op21 = "21";
                } else if (checkedId == R.id.radio3) {
                    op21 = "Si";
                } else if (checkedId == R.id.radio4) {
                    op21 = "No";
                } else if (checkedId == R.id.radio5) {
                    op21 = "";
                } else if (checkedId == R.id.radio6) {
                    op21 = "";
                } else if (checkedId == R.id.radio0) {
                    op21 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta22.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op22 = "0";
                } else if (checkedId == R.id.radio2) {
                    op22 = "22";
                } else if (checkedId == R.id.radio3) {
                    op22 = "Muy de acuerdo";
                } else if (checkedId == R.id.radio4) {
                    op22 = "Algo de acuerdo";
                } else if (checkedId == R.id.radio5) {
                    op22 = "Poco de acuerdo";
                } else if (checkedId == R.id.radio6) {
                    op22 = "Nada de acuerdo";
                } else if (checkedId == R.id.radio0) {
                    op22 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta23.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op23 = "0";
                } else if (checkedId == R.id.radio2) {
                    op23 = "23";
                } else if (checkedId == R.id.radio3) {
                    op23 = "Muy dispuesto";
                } else if (checkedId == R.id.radio4) {
                    op23 = "Dispuesto";
                } else if (checkedId == R.id.radio5) {
                    op23 = "Poco dispuesto";
                } else if (checkedId == R.id.radio6) {
                    op23 = "Nada dispuesto";
                } else if (checkedId == R.id.radio0) {
                    op23 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op24_1 = "24_1";
                } else if (checkedId == R.id.radio3) {
                    op24_1 = "No";
                    lay24_1a.setVisibility(View.GONE);
                    rdPregunta24_1a.clearCheck();
                    op24_1a = "No aplica";
                    lay24_1b.setVisibility(View.GONE);
                    rdPregunta24_1b.clearCheck();
                    op24_1b = "No aplica";

                } else if (checkedId == R.id.radio4) {
                    op24_1 = "Si";
                    lay24_1a.setVisibility(View.VISIBLE);
                    rdPregunta24_1a.clearCheck();
                    op24_1a = "sin datos";
                    lay24_1b.setVisibility(View.VISIBLE);
                    rdPregunta24_1b.clearCheck();
                    op24_1b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_1a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_1a = "24_1a";
                } else if (checkedId == R.id.radio3) {
                    op24_1a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_1a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_1a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_1a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_1a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_1a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_1b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_1b = "24_1b";
                } else if (checkedId == R.id.radio3) {
                    op24_1b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_1b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_1b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_2 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_2 = "24_2";
                } else if (checkedId == R.id.radio3) {
                    op24_2 = "No";
                    lay24_2a.setVisibility(View.GONE);
                    rdPregunta24_2a.clearCheck();
                    op24_2a = "sin datos";
                    lay24_2b.setVisibility(View.GONE);
                    rdPregunta24_2b.clearCheck();
                    op24_2b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op24_2 = "Si";
                    lay24_2a.setVisibility(View.VISIBLE);
                    rdPregunta24_2a.clearCheck();
                    op24_2a = "sin datos";
                    lay24_2b.setVisibility(View.VISIBLE);
                    rdPregunta24_2b.clearCheck();
                    op24_2b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_2a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_2a = "24_2a";
                } else if (checkedId == R.id.radio3) {
                    op24_2a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_2a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_2a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_2a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_2a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_2a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_2b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_2b = "24_2b";
                } else if (checkedId == R.id.radio3) {
                    op24_2b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_2b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_2b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_3 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_3 = "24_3";
                } else if (checkedId == R.id.radio3) {
                    op24_3 = "No";
                    lay24_3a.setVisibility(View.GONE);
                    rdPregunta24_3a.clearCheck();
                    op24_3a = "sin datos";
                    lay24_3b.setVisibility(View.GONE);
                    rdPregunta24_3b.clearCheck();
                    op24_3b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op24_3 = "Si";
                    lay24_3a.setVisibility(View.VISIBLE);
                    rdPregunta24_3a.clearCheck();
                    op24_3a = "sin datos";
                    lay24_3b.setVisibility(View.VISIBLE);
                    rdPregunta24_3b.clearCheck();
                    op24_3b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_3a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_3a = "24_3a";
                } else if (checkedId == R.id.radio3) {
                    op24_3a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_3a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_3a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_3a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_3a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_3a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_3b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_3b = "24_3b";
                } else if (checkedId == R.id.radio3) {
                    op24_3b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_3b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_3b = "No sabe / No contest�";
                }
            }
        });

        rdPregunta24_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_4 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_4 = "24_4";
                } else if (checkedId == R.id.radio3) {
                    op24_4 = "No";
                    lay24_4a.setVisibility(View.GONE);
                    rdPregunta24_4a.clearCheck();
                    op24_4a = "sin datos";
                    lay24_4b.setVisibility(View.GONE);
                    rdPregunta24_4b.clearCheck();
                    op24_4b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op24_4 = "Si";
                    lay24_4a.setVisibility(View.VISIBLE);
                    rdPregunta24_4a.clearCheck();
                    op24_4a = "sin datos";
                    lay24_4b.setVisibility(View.VISIBLE);
                    rdPregunta24_4b.clearCheck();
                    op24_4b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_4a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_4a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_4a = "24_4a";
                } else if (checkedId == R.id.radio3) {
                    op24_4a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_4a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_4a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_4a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_4a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op24_4a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_4b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_4b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_4b = "24_4b";
                } else if (checkedId == R.id.radio3) {
                    op24_4b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_4b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_4b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_5 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_5 = "24_5";
                } else if (checkedId == R.id.radio3) {
                    op24_5 = "No";
                    lay24_5a.setVisibility(View.GONE);
                    rdPregunta24_5a.clearCheck();
                    op24_5a = "sin datos";
                    lay24_5b.setVisibility(View.GONE);
                    rdPregunta24_5b.clearCheck();
                    op24_5b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op24_5 = "Si";
                    lay24_5a.setVisibility(View.VISIBLE);
                    rdPregunta24_5a.clearCheck();
                    op24_5a = "sin datos";
                    lay24_5b.setVisibility(View.VISIBLE);
                    rdPregunta24_5b.clearCheck();
                    op24_5b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_5a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_5a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_5a = "24_5a";
                } else if (checkedId == R.id.radio3) {
                    op24_5a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_5a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_5a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_5a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_5a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op24_5a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_5b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_5b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_5b = "24_5b";
                } else if (checkedId == R.id.radio3) {
                    op24_5b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_5b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_5b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_6 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_6 = "24_6";
                } else if (checkedId == R.id.radio3) {
                    op24_6 = "No";
                    lay24_6a.setVisibility(View.GONE);
                    rdPregunta24_6a.clearCheck();
                    op24_6a = "No aplica";
                    lay24_6b.setVisibility(View.GONE);
                    rdPregunta24_6b.clearCheck();
                    op24_6b = "No aplica";

                } else if (checkedId == R.id.radio4) {
                    op24_6 = "Si";
                    lay24_6a.setVisibility(View.VISIBLE);
                    rdPregunta24_6a.clearCheck();
                    op24_6a = "sin datos";
                    lay24_6b.setVisibility(View.VISIBLE);
                    rdPregunta24_6b.clearCheck();
                    op24_6b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_6a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_6a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_6a = "24_6a";
                } else if (checkedId == R.id.radio3) {
                    op24_6a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_6a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_6a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_6a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_6a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_6a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_6b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_6b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_6b = "24_6b";
                } else if (checkedId == R.id.radio3) {
                    op24_6b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_6b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_6b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_7 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_7 = "24_7";
                } else if (checkedId == R.id.radio3) {
                    op24_7 = "No";
                    lay24_7a.setVisibility(View.GONE);
                    rdPregunta24_7a.clearCheck();
                    op24_7a = "sin datos";
                    lay24_7b.setVisibility(View.GONE);
                    rdPregunta24_7b.clearCheck();
                    op24_7b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op24_7 = "Si";
                    lay24_7a.setVisibility(View.VISIBLE);
                    rdPregunta24_7a.clearCheck();
                    op24_7a = "sin datos";
                    lay24_7b.setVisibility(View.VISIBLE);
                    rdPregunta24_7b.clearCheck();
                    op24_7b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_7a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_7a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_7a = "24_7a";
                } else if (checkedId == R.id.radio3) {
                    op24_7a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_7a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_7a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_7a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_7a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_7a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_7b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_7b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_7b = "24_7b";
                } else if (checkedId == R.id.radio3) {
                    op24_7b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_7b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_7b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_8 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_8 = "24_8";
                } else if (checkedId == R.id.radio3) {
                    op24_8 = "No";
                    lay24_8a.setVisibility(View.GONE);
                    rdPregunta24_8a.clearCheck();
                    op24_8a = "sin datos";
                    lay24_8b.setVisibility(View.GONE);
                    rdPregunta24_8b.clearCheck();
                    op24_8b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op24_8 = "Si";
                    lay24_8a.setVisibility(View.VISIBLE);
                    rdPregunta24_8a.clearCheck();
                    op24_8a = "sin datos";
                    lay24_8b.setVisibility(View.VISIBLE);
                    rdPregunta24_8b.clearCheck();
                    op24_8b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_8a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_8a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_8a = "24_8a";
                } else if (checkedId == R.id.radio3) {
                    op24_8a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_8a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_8a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_8a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_8a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op24_8a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_8b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_8b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_8b = "24_8b";
                } else if (checkedId == R.id.radio3) {
                    op24_8b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_8b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_8b = "No sabe / No contest�";
                }
            }
        });

        rdPregunta24_9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_9 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_9 = "24_9";
                } else if (checkedId == R.id.radio3) {
                    op24_9 = "No";
                    lay24_9a.setVisibility(View.GONE);
                    rdPregunta24_9a.clearCheck();
                    op24_9a = "sin datos";
                    lay24_9b.setVisibility(View.GONE);
                    rdPregunta24_9b.clearCheck();
                    op24_9b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op24_9 = "Si";
                    lay24_9a.setVisibility(View.VISIBLE);
                    rdPregunta24_9a.clearCheck();
                    op24_9a = "sin datos";
                    lay24_9b.setVisibility(View.VISIBLE);
                    rdPregunta24_9b.clearCheck();
                    op24_9b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_9a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_9a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_9a = "24_9a";
                } else if (checkedId == R.id.radio3) {
                    op24_9a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_9a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_9a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_9a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_9a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op24_9a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_9b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_9b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_9b = "24_9b";
                } else if (checkedId == R.id.radio3) {
                    op24_9b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_9b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_9b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_10 = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_10 = "24_10";
                } else if (checkedId == R.id.radio3) {
                    op24_10 = "No";
                    lay24_10a.setVisibility(View.GONE);
                    rdPregunta24_10a.clearCheck();
                    op24_10a = "sin datos";
                    lay24_10b.setVisibility(View.GONE);
                    rdPregunta24_10b.clearCheck();
                    op24_10b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op24_10 = "Si";
                    lay24_10a.setVisibility(View.VISIBLE);
                    rdPregunta24_10a.clearCheck();
                    op24_10a = "sin datos";
                    lay24_10b.setVisibility(View.VISIBLE);
                    rdPregunta24_10b.clearCheck();
                    op24_10b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_10a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_10a = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_10a = "24_10a";
                } else if (checkedId == R.id.radio3) {
                    op24_10a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op24_10a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op24_10a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op24_10a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op24_10a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op24_10a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta24_10b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op24_10b = "0";
                } else if (checkedId == R.id.radio2) {
                    op24_10b = "24_10b";
                } else if (checkedId == R.id.radio3) {
                    op24_10b = "No";
                } else if (checkedId == R.id.radio4) {
                    op24_10b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op24_10b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(candidato_1_alcalde!=null){
            radio25_3.setText(candidato_1_alcalde);
        }
        if(candidato_1_alcalde==null){
            radio25_3.setVisibility(View.GONE);
        }

        if(candidato_2_alcalde!=null){
            radio25_4.setText(candidato_2_alcalde);
        }
        if(candidato_2_alcalde==null){
            radio25_4.setVisibility(View.GONE);
        }

        if(candidato_3_alcalde!=null){
            radio25_5.setText(candidato_3_alcalde);
        }
        if(candidato_3_alcalde==null){
            radio25_5.setVisibility(View.GONE);
        }

        if(candidato_4_alcalde!=null){
            radio25_6.setText(candidato_4_alcalde);
        }
        if(candidato_4_alcalde==null){
            radio25_6.setVisibility(View.GONE);
        }



        rdPregunta25.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio25_1) {
                    op25="0";
                } else if (checkedId == R.id.radio25_2) {
                    op25 = "25";
                } else if (checkedId == R.id.radio25_3) {
                    op25 = radio25_3.getText().toString();
                } else if (checkedId == R.id.radio25_4) {
                    op25 = radio25_4.getText().toString();
                } else if (checkedId == R.id.radio25_5) {
                    op25 = radio25_5.getText().toString();
                } else if (checkedId == R.id.radio25_6) {
                    op25 = radio25_6.getText().toString();
                } else if (checkedId == R.id.radio25_7) {
                    op25 = "Otro";
                } else if (checkedId == R.id.radio25_0) {
                    op25 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta26.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio26_1) {
                    op26 = "0";
                } else if (checkedId == R.id.radio26_2) {
                    op26 = "26";
                } else if (checkedId == R.id.radio26_3) {
                    op26 = radio26_3.getText().toString();
                } else if (checkedId == R.id.radio26_4) {
                    op26 = radio26_4.getText().toString();
                } else if (checkedId == R.id.radio26_5) {
                    op26 = radio26_5.getText().toString();
                } else if (checkedId == R.id.radio26_6) {
                    op26 = radio26_6.getText().toString();
                } else if (checkedId == R.id.radio26_7) {
                    op26 = "Otro";
                } else if (checkedId == R.id.radio26_8) {
                    op26 = "Ninguno";
                } else if (checkedId == R.id.radio26_0) {
                    op26 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op27_1 = "27_1";
                } else if (checkedId == R.id.radio3) {
                    op27_1 = "No";
                    lay27_1a.setVisibility(View.GONE);
                    rdPregunta27_1a.clearCheck();
                    op27_1a = "sin datos";
                    lay27_1b.setVisibility(View.GONE);
                    rdPregunta27_1b.clearCheck();
                    op27_1b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op27_1 = "Si";
                    lay27_1a.setVisibility(View.VISIBLE);
                    rdPregunta27_1a.clearCheck();
                    op27_1a = "sin datos";
                    lay27_1b.setVisibility(View.VISIBLE);
                    rdPregunta27_1b.clearCheck();
                    op27_1b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_1a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_1a = "27_1a";
                } else if (checkedId == R.id.radio3) {
                    op27_1a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_1a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_1a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_1a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_1a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_1a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_1b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_1b = "27_1b";
                } else if (checkedId == R.id.radio3) {
                    op27_1b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_1b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_1b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_2 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_2 = "27_2";
                } else if (checkedId == R.id.radio3) {
                    op27_2 = "No";
                    lay27_2a.setVisibility(View.GONE);
                    rdPregunta27_2a.clearCheck();
                    op27_2a = "sin datos";
                    lay27_2b.setVisibility(View.GONE);
                    rdPregunta27_2b.clearCheck();
                    op27_2b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op27_2 = "Si";
                    lay27_2a.setVisibility(View.VISIBLE);
                    rdPregunta27_2a.clearCheck();
                    op27_2a = "sin datos";
                    lay27_2b.setVisibility(View.VISIBLE);
                    rdPregunta27_2b.clearCheck();
                    op27_2b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_2a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_2a = "27_2a";
                } else if (checkedId == R.id.radio3) {
                    op27_2a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_2a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_2a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_2a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_2a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_2a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_2b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_2b = "27_2b";
                } else if (checkedId == R.id.radio3) {
                    op27_2b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_2b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_2b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_3 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_3 = "27_3";
                } else if (checkedId == R.id.radio3) {
                    op27_3 = "No";
                    lay27_3a.setVisibility(View.GONE);
                    rdPregunta27_3a.clearCheck();
                    op27_3a = "sin datos";
                    lay27_3b.setVisibility(View.GONE);
                    rdPregunta27_3b.clearCheck();
                    op27_3b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op27_3 = "Si";
                    lay27_3a.setVisibility(View.VISIBLE);
                    rdPregunta27_3a.clearCheck();
                    op27_3a = "sin datos";
                    lay27_3b.setVisibility(View.VISIBLE);
                    rdPregunta27_3b.clearCheck();
                    op27_3b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_3a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_3a = "27_3a";
                } else if (checkedId == R.id.radio3) {
                    op27_3a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_3a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_3a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_3a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_3a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_3a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_3b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_3b = "27_3b";
                } else if (checkedId == R.id.radio3) {
                    op27_3b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_3b = "Si";
                } else if (checkedId == R.id.radio5) {
                    op27_3b = "No sabe / No contest�";
                } else if (checkedId == R.id.radio0) {
                    op27_3b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rdPregunta27_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_4 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_4 = "27_4";
                } else if (checkedId == R.id.radio3) {
                    op27_4 = "No";
                    lay27_4a.setVisibility(View.GONE);
                    rdPregunta27_4a.clearCheck();
                    op27_4a = "sin datos";
                    lay27_4b.setVisibility(View.GONE);
                    rdPregunta27_4b.clearCheck();
                    op27_4b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op27_4 = "Si";
                    lay27_4a.setVisibility(View.VISIBLE);
                    rdPregunta27_4a.clearCheck();
                    op27_4a = "sin datos";
                    lay27_4b.setVisibility(View.VISIBLE);
                    rdPregunta27_4b.clearCheck();
                    op27_4b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_4a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_4a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_4a = "27_4a";
                } else if (checkedId == R.id.radio3) {
                    op27_4a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_4a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_4a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_4a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_4a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op27_4a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_4b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_4b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_4b = "27_4b";
                } else if (checkedId == R.id.radio3) {
                    op27_4b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_4b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_4b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_5 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_5 = "27_5";
                } else if (checkedId == R.id.radio3) {
                    op27_5 = "No";
                    lay27_5a.setVisibility(View.GONE);
                    rdPregunta27_5a.clearCheck();
                    op27_5a = "sin datos";
                    lay27_5b.setVisibility(View.GONE);
                    rdPregunta27_5b.clearCheck();
                    op27_5b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op27_5 = "Si";
                    lay27_5a.setVisibility(View.VISIBLE);
                    rdPregunta27_5a.clearCheck();
                    op27_5a = "sin datos";
                    lay27_5b.setVisibility(View.VISIBLE);
                    rdPregunta27_5b.clearCheck();
                    op27_5b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_5a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_5a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_5a = "27_5a";
                } else if (checkedId == R.id.radio3) {
                    op27_5a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_5a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_5a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_5a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_5a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op27_5a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_5b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_5b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_5b = "27_5b";
                } else if (checkedId == R.id.radio3) {
                    op27_5b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_5b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_5b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_6 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_6 = "27_6";
                } else if (checkedId == R.id.radio3) {
                    op27_6 = "No";
                    lay27_6a.setVisibility(View.GONE);
                    rdPregunta27_6a.clearCheck();
                    op27_6a = "No aplica";
                    lay27_6b.setVisibility(View.GONE);
                    rdPregunta27_6b.clearCheck();
                    op27_6b = "No aplica";

                } else if (checkedId == R.id.radio4) {
                    op27_6 = "Si";
                    lay27_6a.setVisibility(View.VISIBLE);
                    rdPregunta27_6a.clearCheck();
                    op27_6a = "sin datos";
                    lay27_6b.setVisibility(View.VISIBLE);
                    rdPregunta27_6b.clearCheck();
                    op27_6b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_6a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_6a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_6a = "27_6a";
                } else if (checkedId == R.id.radio3) {
                    op27_6a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_6a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_6a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_6a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_6a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_6a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_6b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_6b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_6b = "27_6b";
                } else if (checkedId == R.id.radio3) {
                    op27_6b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_6b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_6b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_7 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_7 = "27_7";
                } else if (checkedId == R.id.radio3) {
                    op27_7 = "No";
                    lay27_7a.setVisibility(View.GONE);
                    rdPregunta27_7a.clearCheck();
                    op27_7a = "sin datos";
                    lay27_7b.setVisibility(View.GONE);
                    rdPregunta27_7b.clearCheck();
                    op27_7b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op27_7 = "Si";
                    lay27_7a.setVisibility(View.VISIBLE);
                    rdPregunta27_7a.clearCheck();
                    op27_7a = "sin datos";
                    lay27_7b.setVisibility(View.VISIBLE);
                    rdPregunta27_7b.clearCheck();
                    op27_7b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_7a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_7a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_7a = "27_7a";
                } else if (checkedId == R.id.radio3) {
                    op27_7a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_7a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_7a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_7a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_7a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_7a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_7b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_7b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_7b = "27_7b";
                } else if (checkedId == R.id.radio3) {
                    op27_7b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_7b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_7b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_8 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_8 = "27_8";
                } else if (checkedId == R.id.radio3) {
                    op27_8 = "No";
                    lay27_8a.setVisibility(View.GONE);
                    rdPregunta27_8a.clearCheck();
                    op27_8a = "sin datos";
                    lay27_8b.setVisibility(View.GONE);
                    rdPregunta27_8b.clearCheck();
                    op27_8b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op27_8 = "Si";
                    lay27_8a.setVisibility(View.VISIBLE);
                    rdPregunta27_8a.clearCheck();
                    op27_8a = "sin datos";
                    lay27_8b.setVisibility(View.VISIBLE);
                    rdPregunta27_8b.clearCheck();
                    op27_8b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_8a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_8a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_8a = "27_8a";
                } else if (checkedId == R.id.radio3) {
                    op27_8a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_8a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_8a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_8a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_8a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op27_8a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_8b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_8b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_8b = "27_8b";
                } else if (checkedId == R.id.radio3) {
                    op27_8b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_8b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_8b = "No sabe / No contest�";
                }
            }
        });

        rdPregunta27_9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_9 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_9 = "27_9";
                } else if (checkedId == R.id.radio3) {
                    op27_9 = "No";
                    lay27_9a.setVisibility(View.GONE);
                    rdPregunta27_9a.clearCheck();
                    op27_9a = "sin datos";
                    lay27_9b.setVisibility(View.GONE);
                    rdPregunta27_9b.clearCheck();
                    op27_9b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op27_9 = "Si";
                    lay27_9a.setVisibility(View.VISIBLE);
                    rdPregunta27_9a.clearCheck();
                    op27_9a = "sin datos";
                    lay27_9b.setVisibility(View.VISIBLE);
                    rdPregunta27_9b.clearCheck();
                    op27_9b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_9a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_9a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_9a = "27_9a";
                } else if (checkedId == R.id.radio3) {
                    op27_9a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_9a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_9a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_9a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_9a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op27_9a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_9b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_9b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_9b = "27_9b";
                } else if (checkedId == R.id.radio3) {
                    op27_9b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_9b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_9b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_10 = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_10 = "27_10";
                } else if (checkedId == R.id.radio3) {
                    op27_10 = "No";
                    lay27_10a.setVisibility(View.GONE);
                    rdPregunta27_10a.clearCheck();
                    op27_10a = "sin datos";
                    lay27_10b.setVisibility(View.GONE);
                    rdPregunta27_10b.clearCheck();
                    op27_10b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op27_10 = "Si";
                    lay27_10a.setVisibility(View.VISIBLE);
                    rdPregunta27_10a.clearCheck();
                    op27_10a = "sin datos";
                    lay27_10b.setVisibility(View.VISIBLE);
                    rdPregunta27_10b.clearCheck();
                    op27_10b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_10a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_10a = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_10a = "27_10a";
                } else if (checkedId == R.id.radio3) {
                    op27_10a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op27_10a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op27_10a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op27_10a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op27_10a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op27_10a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta27_10b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op27_10b = "0";
                } else if (checkedId == R.id.radio2) {
                    op27_10b = "27_10b";
                } else if (checkedId == R.id.radio3) {
                    op27_10b = "No";
                } else if (checkedId == R.id.radio4) {
                    op27_10b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op27_10b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(candidato_1_federal!=null){
            radio28_3.setText(candidato_1_federal);
        }
        if(candidato_1_federal==null){
            radio28_3.setVisibility(View.GONE);
        }

        if(candidato_2_federal!=null){
            radio28_4.setText(candidato_2_federal);
        }
        if(candidato_2_federal==null){
            radio28_4.setVisibility(View.GONE);
        }

        if(candidato_3_federal!=null){
            radio28_5.setText(candidato_3_federal);
        }
        if(candidato_3_federal==null){
            radio28_5.setVisibility(View.GONE);
        }

        if(candidato_4_federal!=null){
            radio28_6.setText(candidato_4_federal);
        }
        if(candidato_4_federal==null){
            radio28_6.setVisibility(View.GONE);
        }


        rdPregunta28.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio28_1) {
                    op28="0";
                } else if (checkedId == R.id.radio28_2) {
                    op28 = "28";
                } else if (checkedId == R.id.radio28_3) {
                    op28 = radio28_3.getText().toString();
                } else if (checkedId == R.id.radio28_4) {
                    op28 = radio28_4.getText().toString();
                } else if (checkedId == R.id.radio28_5) {
                    op28 = radio28_5.getText().toString();
                } else if (checkedId == R.id.radio28_6) {
                    op28 = radio28_6.getText().toString();
                } else if (checkedId == R.id.radio28_7) {
                    op28 = "Otro";
                } else if (checkedId == R.id.radio28_0) {
                    op28 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta29.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio29_1) {
                    op29 = "0";
                } else if (checkedId == R.id.radio29_2) {
                    op29 = "29";
                } else if (checkedId == R.id.radio29_3) {
                    op29 = radio29_3.getText().toString();
                } else if (checkedId == R.id.radio29_4) {
                    op29 = radio29_4.getText().toString();
                } else if (checkedId == R.id.radio29_5) {
                    op29 = radio29_5.getText().toString();
                } else if (checkedId == R.id.radio29_6) {
                    op29 = radio29_6.getText().toString();
                } else if (checkedId == R.id.radio29_7) {
                    op29 = "Otro";
                } else if (checkedId == R.id.radio29_8) {
                    op29 = "Ninguno";
                } else if (checkedId == R.id.radio29_0) {
                    op29 = "No sabe / No contest�";
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
                } else if (checkedId == R.id.radio2) {
                    op30_1 = "30_1";
                } else if (checkedId == R.id.radio3) {
                    op30_1 = "No";
                    lay30_1a.setVisibility(View.GONE);
                    rdPregunta30_1a.clearCheck();
                    op30_1a = "sin datos";
                    lay30_1b.setVisibility(View.GONE);
                    rdPregunta30_1b.clearCheck();
                    op30_1b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op30_1 = "Si";
                    lay30_1a.setVisibility(View.VISIBLE);
                    rdPregunta30_1a.clearCheck();
                    op30_1a = "sin datos";
                    lay30_1b.setVisibility(View.VISIBLE);
                    rdPregunta30_1b.clearCheck();
                    op30_1b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_1a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_1a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_1a = "30_1a";
                } else if (checkedId == R.id.radio3) {
                    op30_1a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_1a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_1a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_1a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_1a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_1a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_1b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_1b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_1b = "30_1b";
                } else if (checkedId == R.id.radio3) {
                    op30_1b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_1b = "Si";
                } else if (checkedId == R.id.radio5) {
                    op30_1b = "No sabe / No contest�";
                } else if (checkedId == R.id.radio0) {
                    op30_1b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_2 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_2 = "30_2";
                } else if (checkedId == R.id.radio3) {
                    op30_2 = "No";
                    lay30_2a.setVisibility(View.GONE);
                    rdPregunta30_2a.clearCheck();
                    op30_2a = "sin datos";
                    lay30_2b.setVisibility(View.GONE);
                    rdPregunta30_2b.clearCheck();
                    op30_2b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op30_2 = "Si";
                    lay30_2a.setVisibility(View.VISIBLE);
                    rdPregunta30_2a.clearCheck();
                    op30_2a = "sin datos";
                    lay30_2b.setVisibility(View.VISIBLE);
                    rdPregunta30_2b.clearCheck();
                    op30_2b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_2a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_2a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_2a = "30_2a";
                } else if (checkedId == R.id.radio3) {
                    op30_2a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_2a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_2a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_2a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_2a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_2a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_2b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_2b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_2b = "30_2b";
                } else if (checkedId == R.id.radio3) {
                    op30_2b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_2b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_2b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_3 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_3 = "30_3";
                } else if (checkedId == R.id.radio3) {
                    op30_3 = "No";
                    lay30_3a.setVisibility(View.GONE);
                    rdPregunta30_3a.clearCheck();
                    op30_3a = "sin datos";
                    lay30_3b.setVisibility(View.GONE);
                    rdPregunta30_3b.clearCheck();
                    op30_3b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op30_3 = "Si";
                    lay30_3a.setVisibility(View.VISIBLE);
                    rdPregunta30_3a.clearCheck();
                    op30_3a = "sin datos";
                    lay30_3b.setVisibility(View.VISIBLE);
                    rdPregunta30_3b.clearCheck();
                    op30_3b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_3a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_3a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_3a = "30_3a";
                } else if (checkedId == R.id.radio3) {
                    op30_3a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_3a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_3a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_3a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_3a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_3a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_3b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_3b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_3b = "30_3b";
                } else if (checkedId == R.id.radio3) {
                    op30_3b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_3b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_3b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rdPregunta30_4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_4 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_4 = "30_4";
                } else if (checkedId == R.id.radio3) {
                    op30_4 = "No";
                    lay30_4a.setVisibility(View.GONE);
                    rdPregunta30_4a.clearCheck();
                    op30_4a = "sin datos";
                    lay30_4b.setVisibility(View.GONE);
                    rdPregunta30_4b.clearCheck();
                    op30_4b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op30_4 = "Si";
                    lay30_4a.setVisibility(View.VISIBLE);
                    rdPregunta30_4a.clearCheck();
                    op30_4a = "sin datos";
                    lay30_4b.setVisibility(View.VISIBLE);
                    rdPregunta30_4b.clearCheck();
                    op30_4b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_4a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_4a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_4a = "30_4a";
                } else if (checkedId == R.id.radio3) {
                    op30_4a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_4a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_4a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_4a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_4a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op30_4a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_4b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_4b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_4b = "30_4b";
                } else if (checkedId == R.id.radio3) {
                    op30_4b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_4b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_4b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_5 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_5 = "30_5";
                } else if (checkedId == R.id.radio3) {
                    op30_5 = "No";
                    lay30_5a.setVisibility(View.GONE);
                    rdPregunta30_5a.clearCheck();
                    op30_5a = "sin datos";
                    lay30_5b.setVisibility(View.GONE);
                    rdPregunta30_5b.clearCheck();
                    op30_5b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op30_5 = "Si";
                    lay30_5a.setVisibility(View.VISIBLE);
                    rdPregunta30_5a.clearCheck();
                    op30_5a = "sin datos";
                    lay30_5b.setVisibility(View.VISIBLE);
                    rdPregunta30_5b.clearCheck();
                    op30_5b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_5a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_5a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_5a = "30_5a";
                } else if (checkedId == R.id.radio3) {
                    op30_5a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_5a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_5a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_5a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_5a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op30_5a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_5b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_5b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_5b = "30_5b";
                } else if (checkedId == R.id.radio3) {
                    op30_5b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_5b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_5b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_6 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_6 = "30_6";
                } else if (checkedId == R.id.radio3) {
                    op30_6 = "No";
                    lay30_6a.setVisibility(View.GONE);
                    rdPregunta30_6a.clearCheck();
                    op30_6a = "No aplica";
                    lay30_6b.setVisibility(View.GONE);
                    rdPregunta30_6b.clearCheck();
                    op30_6b = "No aplica";

                } else if (checkedId == R.id.radio4) {
                    op30_6 = "Si";
                    lay30_6a.setVisibility(View.VISIBLE);
                    rdPregunta30_6a.clearCheck();
                    op30_6a = "sin datos";
                    lay30_6b.setVisibility(View.VISIBLE);
                    rdPregunta30_6b.clearCheck();
                    op30_6b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_6a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_6a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_6a = "30_6a";
                } else if (checkedId == R.id.radio3) {
                    op30_6a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_6a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_6a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_6a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_6a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_6a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_6b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_6b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_6b = "30_6b";
                } else if (checkedId == R.id.radio3) {
                    op30_6b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_6b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_6b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_7.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_7 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_7 = "30_7";
                } else if (checkedId == R.id.radio3) {
                    op30_7 = "No";
                    lay30_7a.setVisibility(View.GONE);
                    rdPregunta30_7a.clearCheck();
                    op30_7a = "sin datos";
                    lay30_7b.setVisibility(View.GONE);
                    rdPregunta30_7b.clearCheck();
                    op30_7b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op30_7 = "Si";
                    lay30_7a.setVisibility(View.VISIBLE);
                    rdPregunta30_7a.clearCheck();
                    op30_7a = "sin datos";
                    lay30_7b.setVisibility(View.VISIBLE);
                    rdPregunta30_7b.clearCheck();
                    op30_7b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_7a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_7a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_7a = "30_7a";
                } else if (checkedId == R.id.radio3) {
                    op30_7a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_7a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_7a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_7a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_7a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_7a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_7b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_7b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_7b = "30_7b";
                } else if (checkedId == R.id.radio3) {
                    op30_7b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_7b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_7b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_8.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_8 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_8 = "30_8";
                } else if (checkedId == R.id.radio3) {
                    op30_8 = "No";
                    lay30_8a.setVisibility(View.GONE);
                    rdPregunta30_8a.clearCheck();
                    op30_8a = "sin datos";
                    lay30_8b.setVisibility(View.GONE);
                    rdPregunta30_8b.clearCheck();
                    op30_8b = "sin datos";

                } else if (checkedId == R.id.radio4) {
                    op30_8 = "Si";
                    lay30_8a.setVisibility(View.VISIBLE);
                    rdPregunta30_8a.clearCheck();
                    op30_8a = "sin datos";
                    lay30_8b.setVisibility(View.VISIBLE);
                    rdPregunta30_8b.clearCheck();
                    op30_8b = "sin datos";

                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_8a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_8a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_8a = "30_8a";
                } else if (checkedId == R.id.radio3) {
                    op30_8a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_8a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_8a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_8a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_8a = "muy mala";
                } else if (checkedId == R.id.radio8) {
                    op30_8a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_8b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_8b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_8b = "30_8b";
                } else if (checkedId == R.id.radio3) {
                    op30_8b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_8b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_8b = "No sabe / No contest�";
                }
            }
        });

        rdPregunta30_9.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_9 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_9 = "30_9";
                } else if (checkedId == R.id.radio3) {
                    op30_9 = "No";
                    lay30_9a.setVisibility(View.GONE);
                    rdPregunta30_9a.clearCheck();
                    op30_9a = "sin datos";
                    lay30_9b.setVisibility(View.GONE);
                    rdPregunta30_9b.clearCheck();
                    op30_9b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op30_9 = "Si";
                    lay30_9a.setVisibility(View.VISIBLE);
                    rdPregunta30_9a.clearCheck();
                    op30_9a = "sin datos";
                    lay30_9b.setVisibility(View.VISIBLE);
                    rdPregunta30_9b.clearCheck();
                    op30_9b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_9a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_9a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_9a = "30_9a";
                } else if (checkedId == R.id.radio3) {
                    op30_9a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_9a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_9a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_9a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_9a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op30_9a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_9b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_9b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_9b = "30_9b";
                } else if (checkedId == R.id.radio3) {
                    op30_9b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_9b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_9b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_10.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_10 = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_10 = "30_10";
                } else if (checkedId == R.id.radio3) {
                    op30_10 = "No";
                    lay30_10a.setVisibility(View.GONE);
                    rdPregunta30_10a.clearCheck();
                    op30_10a = "sin datos";
                    lay30_10b.setVisibility(View.GONE);
                    rdPregunta30_10b.clearCheck();
                    op30_10b = "sin datos";
                } else if (checkedId == R.id.radio4) {
                    op30_10 = "Si";
                    lay30_10a.setVisibility(View.VISIBLE);
                    rdPregunta30_10a.clearCheck();
                    op30_10a = "sin datos";
                    lay30_10b.setVisibility(View.VISIBLE);
                    rdPregunta30_10b.clearCheck();
                    op30_10b = "sin datos";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_10a.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_10a = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_10a = "30_10a";
                } else if (checkedId == R.id.radio3) {
                    op30_10a = "Muy buena";
                } else if (checkedId == R.id.radio4) {
                    op30_10a = "buena";
                } else if (checkedId == R.id.radio5) {
                    op30_10a = "regular";
                } else if (checkedId == R.id.radio6) {
                    op30_10a = "mala";
                } else if (checkedId == R.id.radio7) {
                    op30_10a = "muy mala";
                } else if (checkedId == R.id.radio0) {
                    op30_10a = "conoce sin opini�n";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta30_10b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio1) {
                    op30_10b = "0";
                } else if (checkedId == R.id.radio2) {
                    op30_10b = "30_10b";
                } else if (checkedId == R.id.radio3) {
                    op30_10b = "No";
                } else if (checkedId == R.id.radio4) {
                    op30_10b = "Si";
                } else if (checkedId == R.id.radio0) {
                    op30_10b = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(candidato_1_local!=null){
            radio31_3.setText(candidato_1_local);
        }
        if(candidato_1_local==null){
            radio31_3.setVisibility(View.GONE);
        }

        if(candidato_2_local!=null){
            radio31_4.setText(candidato_2_local);
        }
        if(candidato_2_local==null){
            radio31_4.setVisibility(View.GONE);
        }

        if(candidato_3_local!=null){
            radio31_5.setText(candidato_3_local);
        }
        if(candidato_3_local==null){
            radio31_5.setVisibility(View.GONE);
        }

        if(candidato_4_local!=null){
            radio31_6.setText(candidato_4_local);
        }
        if(candidato_4_local==null){
            radio31_6.setVisibility(View.GONE);
        }

        rdPregunta31.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio31_1) {
                    op31="0";
                } else if (checkedId == R.id.radio31_2) {
                    op31 = "31";
                } else if (checkedId == R.id.radio31_3) {
                    op31 = radio31_3.getText().toString();
                } else if (checkedId == R.id.radio31_4) {
                    op31 = radio31_4.getText().toString();
                } else if (checkedId == R.id.radio31_5) {
                    op31 = radio31_5.getText().toString();
                } else if (checkedId == R.id.radio31_6) {
                    op31 = radio31_6.getText().toString();
                } else if (checkedId == R.id.radio31_7) {
                    op31 = "Otro";
                } else if (checkedId == R.id.radio31_0) {
                    op31 = "No sabe / No contest�";
                }
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        rdPregunta32.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio32_1) {
                    op32 = "0";
                } else if (checkedId == R.id.radio32_2) {
                    op32 = "32";
                } else if (checkedId == R.id.radio32_3) {
                    op32 = radio32_3.getText().toString();
                } else if (checkedId == R.id.radio32_4) {
                    op32 = radio32_4.getText().toString();
                } else if (checkedId == R.id.radio32_5) {
                    op32 = radio32_5.getText().toString();
                } else if (checkedId == R.id.radio32_6) {
                    op32 = radio32_6.getText().toString();
                } else if (checkedId == R.id.radio32_7) {
                    op32 = "Otro";
                } else if (checkedId == R.id.radio32_8) {
                    op32 = "Ninguno";
                } else if (checkedId == R.id.radio32_0) {
                    op32 = "No sabe / No contest�";
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
//                    opHijos = "No sabe / No contest�";
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
                    opAporta = "No complet� primaria";
                } else if (checkedId == R.id.radio2) {
                    opAporta = "Primaria o secundaria";
                } else if (checkedId == R.id.radio3) {
                    opAporta = "Preparatoria o carrera t�cnica";
                } else if (checkedId == R.id.radio4) {
                    opAporta = "Licenciatura";
                } else if (checkedId == R.id.radio5) {
                    opAporta = "Posgrado";
                } else if (checkedId == R.id.radio0) {
                    opAporta = "No contest�";
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
                    opOcupacion = "No contest�";
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
//                opCoche = "No contest�";
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
                    opCuantosCoches = "Cuatro o m�s";
                } else if (checkedId == R.id.radio0) {
                    opCuantosCoches = "No contest�";
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
                    opCuartos = "No contest�";
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
                    opCuartosDormir = "No contest�";
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
                    opFocos = "21 o m�s";
                } else if (checkedId == R.id.radio0) {
                    opFocos = "No contest�";
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
                    opBanos = "Cuatro o m�s";
                } else if (checkedId == R.id.radio0) {
                    opBanos = "No contest�";
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
                    opRegadera = "No contest�";
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
//                opInternet="No sabe / no contest�";
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
//                opTrabajaron="Cuatro o m�s";
//            }
//            else if (checkedId == R.id.radio0) {
//                opTrabajaron="No sabe / no contest�";
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
                    opEstufa = "No contest�";
                } else {
                    opEstufa = "";

                }

            }
        });

        rdPreguntaEdad.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radio1) {
                    opEdad = "Menor de 18 a�os";
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
                    opEdad = "70 o m�s";
                } else if (checkedId == R.id.radio0) {
                    opEdad = "No contest�";
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
                    opTipoVivienda = "No contest�";
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
                    opTipoPiso = "No contest�";
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
        if (editPregunta6.getText().toString().trim().length() == 0) {
            strText6 = op6;
        } else {
            strText6 = editPregunta6.getText().toString().trim();
            rdPregunta6.clearCheck();
        }


        String strText7;
        if (editPregunta7.getText().toString().trim().length() == 0) {
            strText7 = op7;
        } else {
            strText7 = editPregunta7.getText().toString().trim();
            rdPregunta7.clearCheck();
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
        String str12 = op12;
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
        String str24_4 = op24_4;
        String str24_4a = op24_4a;
        String str24_4b = op24_4b;
        String str24_5 = op24_5;
        String str24_5a = op24_5a;
        String str24_5b = op24_5b;
        String str24_6 = op24_6;
        String str24_6a = op24_6a;
        String str24_6b = op24_6b;
        String str24_7 = op24_7;
        String str24_7a = op24_7a;
        String str24_7b = op24_7b;
        String str24_8 = op24_8;
        String str24_8a = op24_8a;
        String str24_8b = op24_8b;
        String str24_9 = op24_9;
        String str24_9a = op24_9a;
        String str24_9b = op24_9b;
        String str24_10 = op24_10;
        String str24_10a = op24_10a;
        String str24_10b = op24_10b;
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
        String str27_4 = op27_4;
        String str27_4a = op27_4a;
        String str27_4b = op27_4b;
        String str27_5 = op27_5;
        String str27_5a = op27_5a;
        String str27_5b = op27_5b;
        String str27_6 = op27_6;
        String str27_6a = op27_6a;
        String str27_6b = op27_6b;
        String str27_7 = op27_7;
        String str27_7a = op27_7a;
        String str27_7b = op27_7b;
        String str27_8 = op27_8;
        String str27_8a = op27_8a;
        String str27_8b = op27_8b;
        String str27_9 = op27_9;
        String str27_9a = op27_9a;
        String str27_9b = op27_9b;
        String str27_10 = op27_10;
        String str27_10a = op27_10a;
        String str27_10b = op27_10b;
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
        String str30_4 = op30_4;
        String str30_4a = op30_4a;
        String str30_4b = op30_4b;
        String str30_5 = op30_5;
        String str30_5a = op30_5a;
        String str30_5b = op30_5b;
        String str30_6 = op30_6;
        String str30_6a = op30_6a;
        String str30_6b = op30_6b;
        String str30_7 = op30_7;
        String str30_7a = op30_7a;
        String str30_7b = op30_7b;
        String str30_8 = op30_8;
        String str30_8a = op30_8a;
        String str30_8b = op30_8b;
        String str30_9 = op30_9;
        String str30_9a = op30_9a;
        String str30_9b = op30_9b;
        String str30_10 = op30_10;
        String str30_10a = op30_10a;
        String str30_10b = op30_10b;
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
        } else if (strEstudios.matches("No complet� primaria")) {
            strEstudios = "0";
        } else if (strEstudios.matches("Primaria o secundaria")) {
            strEstudios = "22";
        } else if (strEstudios.matches("Preparatoria o carrera t�cnica")) {
            strEstudios = "38";
        } else if (strEstudios.matches("Licenciatura")) {
            strEstudios = "52";
        } else if (strEstudios.matches("Posgrado")) {
            strEstudios = "72";
        } else if (strEstudios.matches("No contest�")) {
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
        } else if (strCocheCuantos.matches("Cuatro o m�s")) {
            strCocheCuantos = "58";
        } else if (strCocheCuantos.matches("No aplica")) {
            strCocheCuantos = "0";
        } else if (strCocheCuantos.matches("No contest�")) {
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
        } else if (strFoco.matches("21 o m�s")) {
            strFoco = "46";
        } else if (strFoco.matches("No contest�")) {
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
        } else if (strCuarto.matches("No contest�")) {
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
        } else if (strBano.matches("Cuatro o m�s")) {
            strBano = "48";
        } else if (strBano.matches("No contest�")) {
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
        } else if (strRega.matches("No contest�")) {
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
        } else if (strEstu.matches("No contest�")) {
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
        } else if (strPiso.matches("No contest�")) {
            strPiso = "0";
        } else if (strPiso.matches("No aplica")) {
            strPiso = "0";
        } else if (strPiso.matches("No sabe / No contest�")) {
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
                values.put("entidad", cachaentidad());
                values.put("alcaldia", cachaalcaldia());
                values.put("dtto_federal", cachafederal());
                values.put("dtto_local", cachalocal());
                values.put("seccion", strSecc);
                values.put("latitud", strLatitud);
                values.put("longitud", strLongitud);

                values.put("pregunta_1", str1);
                values.put("pregunta_2", str2);
                values.put("pregunta_3", str3);
                values.put("pregunta_4", str4);
                values.put("pregunta_5", str5);
                values.put("pregunta_6", str6);
                values.put("pregunta_7", str7);
                values.put("pregunta_pc", strpc);
                values.put("pregunta_pc_1", strpc_1);
                values.put("pregunta_pc_2", strpc_2);
                values.put("pregunta_pc_3", strpc_3);
                values.put("pregunta_pc_4", strpc_4);
                values.put("pregunta_8", str8);
                values.put("pregunta_9", str9);
                values.put("pregunta_10", str10);
                values.put("pregunta_11", str11);
                values.put("pregunta_12", str12);
                values.put("pregunta_13", str13);
                values.put("pregunta_14", str14);
                values.put("pregunta_15", str15);
                values.put("pregunta_16", str16);
                values.put("pregunta_16a", str16a);
                values.put("pregunta_16b", str16b);
                values.put("pregunta_17", str17);
                values.put("pregunta_17a", str17a);
                values.put("pregunta_18", str18);
                values.put("pregunta_19", str19);
                values.put("pregunta_19a", str19a);
                values.put("pregunta_19b", str19b);
                values.put("pregunta_20", str20);
                values.put("pregunta_21", str21);
                values.put("pregunta_22", str22);
                values.put("pregunta_23", str23);
                values.put("pregunta_24", str24);

                values.put("pregunta_24_1", str24_1);
                values.put("pregunta_24_1a", str24_1a);
                values.put("pregunta_24_1b", str24_1b);//perez

                values.put("pregunta_24_2", str24_2);
                values.put("pregunta_24_2a", str24_2a);
                values.put("pregunta_24_2b", str24_2b);//sanchez

                values.put("pregunta_24_3", str24_3);
                values.put("pregunta_24_3a", str24_3a);
                values.put("pregunta_24_3b", str24_3b);//molina

                values.put("pregunta_24_4", str24_4);
                values.put("pregunta_24_4a", str24_4a);
                values.put("pregunta_24_4b", str24_4b);
                values.put("pregunta_24_5", str24_5);
                values.put("pregunta_24_5a", str24_5a);
                values.put("pregunta_24_5b", str24_5b);
                values.put("pregunta_24_6", str24_6);
                values.put("pregunta_24_6a", str24_6a);
                values.put("pregunta_24_6b", str24_6b);
                values.put("pregunta_24_7", str24_7);
                values.put("pregunta_24_7a", str24_7a);
                values.put("pregunta_24_7b", str24_7b);
                values.put("pregunta_24_8", str24_8);
                values.put("pregunta_24_8a", str24_8a);
                values.put("pregunta_24_8b", str24_8b);
                values.put("pregunta_24_9", str24_9);
                values.put("pregunta_24_9a", str24_9a);
                values.put("pregunta_24_9b", str24_9b);
                values.put("pregunta_24_10", str24_10);
                values.put("pregunta_24_10a", str24_10a);
                values.put("pregunta_24_10b", str24_10b);

                values.put("pregunta_25", str25);
                values.put("pregunta_26", str26);
                values.put("pregunta_27", str27);
                values.put("pregunta_27_1", str27_1);
                values.put("pregunta_27_1a", str27_1a);
                values.put("pregunta_27_1b", str27_1b);
                values.put("pregunta_27_2", str27_2);
                values.put("pregunta_27_2a", str27_2a);
                values.put("pregunta_27_2b", str27_2b);
                values.put("pregunta_27_3", str27_3);
                values.put("pregunta_27_3a", str27_3a);
                values.put("pregunta_27_3b", str27_3b);
                values.put("pregunta_27_4", str27_4);
                values.put("pregunta_27_4a", str27_4a);
                values.put("pregunta_27_4b", str27_4b);
                values.put("pregunta_27_5", str27_5);
                values.put("pregunta_27_5a", str27_5a);
                values.put("pregunta_27_5b", str27_5b);
                values.put("pregunta_27_6", str27_6);
                values.put("pregunta_27_6a", str27_6a);
                values.put("pregunta_27_6b", str27_6b);
                values.put("pregunta_27_7", str27_7);
                values.put("pregunta_27_7a", str27_7a);
                values.put("pregunta_27_7b", str27_7b);
                values.put("pregunta_27_8", str27_8);
                values.put("pregunta_27_8a", str27_8a);
                values.put("pregunta_27_8b", str27_8b);
                values.put("pregunta_27_9", str27_9);
                values.put("pregunta_27_9a", str27_9a);
                values.put("pregunta_27_9b", str27_9b);
                values.put("pregunta_27_10", str27_10);
                values.put("pregunta_27_10a", str27_10a);
                values.put("pregunta_27_10b", str27_10b);

                values.put("pregunta_28", str28);
                values.put("pregunta_29", str29);
                values.put("pregunta_30", str30);
                values.put("pregunta_30_1", str30_1);
                values.put("pregunta_30_1a", str30_1a);
                values.put("pregunta_30_1b", str30_1b);
                values.put("pregunta_30_2", str30_2);
                values.put("pregunta_30_2a", str30_2a);
                values.put("pregunta_30_2b", str30_2b);
                values.put("pregunta_30_3", str30_3);
                values.put("pregunta_30_3a", str30_3a);
                values.put("pregunta_30_3b", str30_3b);
                values.put("pregunta_30_4", str30_4);
                values.put("pregunta_30_4a", str30_4a);
                values.put("pregunta_30_4b", str30_4b);
                values.put("pregunta_30_5", str30_5);
                values.put("pregunta_30_5a", str30_5a);
                values.put("pregunta_30_5b", str30_5b);
                values.put("pregunta_30_6", str30_6);
                values.put("pregunta_30_6a", str30_6a);
                values.put("pregunta_30_6b", str30_6b);
                values.put("pregunta_30_7", str30_7);
                values.put("pregunta_30_7a", str30_7a);
                values.put("pregunta_30_7b", str30_7b);
                values.put("pregunta_30_8", str30_8);
                values.put("pregunta_30_8a", str30_8a);
                values.put("pregunta_30_8b", str30_8b);
                values.put("pregunta_30_9", str30_9);
                values.put("pregunta_30_9a", str30_9a);
                values.put("pregunta_30_9b", str30_9b);
                values.put("pregunta_30_10", str30_10);
                values.put("pregunta_30_10a", str30_10a);
                values.put("pregunta_30_10b", str30_10b);

                values.put("pregunta_31", str31);
                values.put("pregunta_32", str32);


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
                    Toast.makeText(getBaseContext(), "Sin conexi�n", Toast.LENGTH_LONG).show();
                    values.put("enviado", "0");
                    db.insert("encuestas", null, values);
                } else {
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
            System.out.println("entidad " + cachaentidad());
            System.out.println("alcaldia " + cachaalcaldia());
            System.out.println("dtto_federal " + cachafederal());
            System.out.println("dtto_local " + cachalocal());
            System.out.println("Seccion " + str);
            System.out.println("Latitud  " + strLatitud);
            System.out.println("Longitud  " + strLongitud);

            System.out.println("pregunta_1  " + str1);
            System.out.println("pregunta_2  " + str2);
            System.out.println("pregunta_3  " + str3);
            System.out.println("pregunta_4  " + str4);
            System.out.println("pregunta_5  " + str5);
            System.out.println("pregunta_6  " + str6);
            System.out.println("pregunta_7  " + str7);
            System.out.println("pregunta_pc  " + strpc);
            System.out.println("pregunta_pc_1  " + strpc_1);
            System.out.println("pregunta_pc_2  " + strpc_2);
            System.out.println("pregunta_pc_3  " + strpc_3);
            System.out.println("pregunta_pc_4  " + strpc_4);
            System.out.println("pregunta_8  " + str8);
            System.out.println("pregunta_9  " + str9);
            System.out.println("pregunta_10  " + str10);
            System.out.println("pregunta_11  " + str11);
            System.out.println("pregunta_12  " + str12);
            System.out.println("pregunta_13  " + str13);
            System.out.println("pregunta_14  " + str14);
            System.out.println("pregunta_15  " + str15);
            System.out.println("pregunta_16  " + str16);
            System.out.println("pregunta_16a  " + str16a);
            System.out.println("pregunta_16b  " + str16b);
            System.out.println("pregunta_17  " + str17);
            System.out.println("pregunta_17a  " + str17a);
            System.out.println("pregunta_18  " + str18);
            System.out.println("pregunta_19  " + str19);
            System.out.println("pregunta_19a  " + str19a);
            System.out.println("pregunta_19b  " + str19b);
            System.out.println("pregunta_20  " + str20);
            System.out.println("pregunta_21  " + str21);
            System.out.println("pregunta_22  " + str22);
            System.out.println("pregunta_23  " + str23);
            System.out.println("pregunta_24  " + str24);
            System.out.println("pregunta_24_1  " + str24_1);
            System.out.println("pregunta_24_1a  " + str24_1a);
            System.out.println("pregunta_24_1b  " + str24_1b);
            System.out.println("pregunta_24_2  " + str24_2);
            System.out.println("pregunta_24_2a  " + str24_2a);
            System.out.println("pregunta_24_2b  " + str24_2b);
            System.out.println("pregunta_24_3  " + str24_3);
            System.out.println("pregunta_24_3a  " + str24_3a);
            System.out.println("pregunta_24_3b  " + str24_3b);
            System.out.println("pregunta_24_4  " + str24_4);
            System.out.println("pregunta_24_4a  " + str24_4a);
            System.out.println("pregunta_24_4b  " + str24_4b);
            System.out.println("pregunta_24_5  " + str24_5);
            System.out.println("pregunta_24_5a  " + str24_5a);
            System.out.println("pregunta_24_5b  " + str24_5b);
            System.out.println("pregunta_24_6  " + str24_6);
            System.out.println("pregunta_24_6a  " + str24_6a);
            System.out.println("pregunta_24_6b  " + str24_6b);
            System.out.println("pregunta_24_7  " + str24_7);
            System.out.println("pregunta_24_7a  " + str24_7a);
            System.out.println("pregunta_24_7b  " + str24_7b);
            System.out.println("pregunta_24_8  " + str24_8);
            System.out.println("pregunta_24_8a  " + str24_8a);
            System.out.println("pregunta_24_8b  " + str24_8b);
            System.out.println("pregunta_24_9  " + str24_9);
            System.out.println("pregunta_24_9a  " + str24_9a);
            System.out.println("pregunta_24_9b  " + str24_9b);
            System.out.println("pregunta_24_10  " + str24_10);
            System.out.println("pregunta_24_10a  " + str24_10a);
            System.out.println("pregunta_24_10b  " + str24_10b);

            System.out.println("pregunta_25  " + str25);
            System.out.println("pregunta_26  " + str26);
            System.out.println("pregunta_27  " + str27);
            System.out.println("pregunta_27_1  " + str27_1);
            System.out.println("pregunta_27_1a  " + str27_1a);
            System.out.println("pregunta_27_1b  " + str27_1b);
            System.out.println("pregunta_27_2  " + str27_2);
            System.out.println("pregunta_27_2a  " + str27_2a);
            System.out.println("pregunta_27_2b  " + str27_2b);
            System.out.println("pregunta_27_3  " + str27_3);
            System.out.println("pregunta_27_3a  " + str27_3a);
            System.out.println("pregunta_27_3b  " + str27_3b);
            System.out.println("pregunta_27_4  " + str27_4);
            System.out.println("pregunta_27_4a  " + str27_4a);
            System.out.println("pregunta_27_4b  " + str27_4b);
            System.out.println("pregunta_27_5  " + str27_5);
            System.out.println("pregunta_27_5a  " + str27_5a);
            System.out.println("pregunta_27_5b  " + str27_5b);
            System.out.println("pregunta_27_6  " + str27_6);
            System.out.println("pregunta_27_6a  " + str27_6a);
            System.out.println("pregunta_27_6b  " + str27_6b);
            System.out.println("pregunta_27_7  " + str27_7);
            System.out.println("pregunta_27_7a  " + str27_7a);
            System.out.println("pregunta_27_7b  " + str27_7b);
            System.out.println("pregunta_27_8  " + str27_8);
            System.out.println("pregunta_27_8a  " + str27_8a);
            System.out.println("pregunta_27_8b  " + str27_8b);
            System.out.println("pregunta_27_9  " + str27_9);
            System.out.println("pregunta_27_9a  " + str27_9a);
            System.out.println("pregunta_27_9b  " + str27_9b);
            System.out.println("pregunta_27_10  " + str27_10);
            System.out.println("pregunta_27_10a  " + str27_10a);
            System.out.println("pregunta_27_10b  " + str27_10b);

            System.out.println("pregunta_28  " + str28);
            System.out.println("pregunta_29  " + str29);
            System.out.println("pregunta_30  " + str30);
            System.out.println("pregunta_30_1  " + str30_1);
            System.out.println("pregunta_30_1a  " + str30_1a);
            System.out.println("pregunta_30_1b  " + str30_1b);
            System.out.println("pregunta_30_2  " + str30_2);
            System.out.println("pregunta_30_2a  " + str30_2a);
            System.out.println("pregunta_30_2b  " + str30_2b);
            System.out.println("pregunta_30_3  " + str30_3);
            System.out.println("pregunta_30_3a  " + str30_3a);
            System.out.println("pregunta_30_3b  " + str30_3b);
            System.out.println("pregunta_30_4  " + str30_4);
            System.out.println("pregunta_30_4a  " + str30_4a);
            System.out.println("pregunta_30_4b  " + str30_4b);
            System.out.println("pregunta_30_5  " + str30_5);
            System.out.println("pregunta_30_5a  " + str30_5a);
            System.out.println("pregunta_30_5b  " + str30_5b);
            System.out.println("pregunta_30_6  " + str30_6);
            System.out.println("pregunta_30_6a  " + str30_6a);
            System.out.println("pregunta_30_6b  " + str30_6b);
            System.out.println("pregunta_30_7  " + str30_7);
            System.out.println("pregunta_30_7a  " + str30_7a);
            System.out.println("pregunta_30_7b  " + str30_7b);
            System.out.println("pregunta_30_8  " + str30_8);
            System.out.println("pregunta_30_8a  " + str30_8a);
            System.out.println("pregunta_30_8b  " + str30_8b);
            System.out.println("pregunta_30_9  " + str30_9);
            System.out.println("pregunta_30_9a  " + str30_9a);
            System.out.println("pregunta_30_9b  " + str30_9b);
            System.out.println("pregunta_30_10  " + str30_10);
            System.out.println("pregunta_30_10a  " + str30_10a);
            System.out.println("pregunta_30_10b  " + str30_10b);

            System.out.println("pregunta_31  " + str31);
            System.out.println("pregunta_32  " + str32);

            System.out.println(" aporta   " + strAporta);
            System.out.println(" ocupacion   " + strOcupacion);
            System.out.println(" cuantos_coches   " + strCuantosCoches);
            System.out.println(" cuartos   " + strCuartos);
            System.out.println(" cuartos_dormir   " + strCuartosDormir);
            System.out.println(" focos   " + strFocos);

            System.out.println(" ba�os   " + strBanos);
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
            System.out.println("algo pas�...1");
        }

    }

    private void guardaEncuestaWS(ContentValues values) {

        showProgress(true);

//RECORRE CONTENTVALUES
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

                if (lay1.getVisibility() == View.VISIBLE && op1.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura1, Toast.LENGTH_LONG).show();
                } else if (lay2.getVisibility() == View.VISIBLE && op2.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura2, Toast.LENGTH_LONG).show();
                } else if (lay3.getVisibility() == View.VISIBLE && op3.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura3, Toast.LENGTH_LONG).show();
                } else if (lay4.getVisibility() == View.VISIBLE && op4.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura4, Toast.LENGTH_LONG).show();
                } else if (lay5.getVisibility() == View.VISIBLE && op5.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura5, Toast.LENGTH_LONG).show();
                } else if (lay6.getVisibility() == View.VISIBLE && op6.matches("sin datos") && editPregunta6.getText().toString().trim().length() == 0) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura6, Toast.LENGTH_LONG).show();
                } else if (lay7.getVisibility() == View.VISIBLE && op7.matches("sin datos") && editPregunta7.getText().toString().trim().length() == 0) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura7, Toast.LENGTH_LONG).show();
                }
//            else if (laypc.getVisibility() == View.VISIBLE && oppc.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  capturapc,Toast.LENGTH_LONG).show();}
                else if (laypc_1.getVisibility() == View.VISIBLE && oppc_1.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + capturapc_1, Toast.LENGTH_LONG).show();
                } else if (laypc_2.getVisibility() == View.VISIBLE && oppc_2.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + capturapc_2, Toast.LENGTH_LONG).show();
                } else if (laypc_3.getVisibility() == View.VISIBLE && oppc_3.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + capturapc_3, Toast.LENGTH_LONG).show();
                } else if (laypc_4.getVisibility() == View.VISIBLE && oppc_4.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + capturapc_4, Toast.LENGTH_LONG).show();
                } else if (lay8.getVisibility() == View.VISIBLE && op8.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura8, Toast.LENGTH_LONG).show();
                } else if (lay9.getVisibility() == View.VISIBLE && op9.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura9, Toast.LENGTH_LONG).show();
                } else if (lay10.getVisibility() == View.VISIBLE && op10.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura10, Toast.LENGTH_LONG).show();
                } else if (lay11.getVisibility() == View.VISIBLE && op11.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura11, Toast.LENGTH_LONG).show();
                } else if (lay12.getVisibility() == View.VISIBLE && op12.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura12, Toast.LENGTH_LONG).show();
                } else if (lay13.getVisibility() == View.VISIBLE && op13.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura13, Toast.LENGTH_LONG).show();
                } else if (lay14.getVisibility() == View.VISIBLE && op14.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura14, Toast.LENGTH_LONG).show();
                } else if (lay15.getVisibility() == View.VISIBLE && op15.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura15, Toast.LENGTH_LONG).show();
                } else if (lay16.getVisibility() == View.VISIBLE && op16.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura16, Toast.LENGTH_LONG).show();
                } else if (lay16a.getVisibility() == View.VISIBLE && op16a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura16a, Toast.LENGTH_LONG).show();
                } else if (lay16b.getVisibility() == View.VISIBLE && op16b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura16b, Toast.LENGTH_LONG).show();
                } else if (lay17.getVisibility() == View.VISIBLE && op17.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura17, Toast.LENGTH_LONG).show();
                } else if (lay17a.getVisibility() == View.VISIBLE && op17a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura17a, Toast.LENGTH_LONG).show();
                } else if (lay18.getVisibility() == View.VISIBLE && op18.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura18, Toast.LENGTH_LONG).show();
                } else if (lay19.getVisibility() == View.VISIBLE && op19.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura19, Toast.LENGTH_LONG).show();
                } else if (lay19a.getVisibility() == View.VISIBLE && op19a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura19a, Toast.LENGTH_LONG).show();
                } else if (lay19b.getVisibility() == View.VISIBLE && op19b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura19b, Toast.LENGTH_LONG).show();
                } else if (lay20.getVisibility() == View.VISIBLE && op20.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura20, Toast.LENGTH_LONG).show();
                } else if (lay21.getVisibility() == View.VISIBLE && op21.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura21, Toast.LENGTH_LONG).show();
                } else if (lay22.getVisibility() == View.VISIBLE && op22.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura22, Toast.LENGTH_LONG).show();
                } else if (lay23.getVisibility() == View.VISIBLE && op23.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura23, Toast.LENGTH_LONG).show();
                }
//            else if (lay24.getVisibility() == View.VISIBLE && op24.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura24,Toast.LENGTH_LONG).show();}
                else if (lay24_1.getVisibility() == View.VISIBLE && op24_1.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_1, Toast.LENGTH_LONG).show();
                } else if (lay24_1a.getVisibility() == View.VISIBLE && op24_1a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_1a, Toast.LENGTH_LONG).show();
                } else if (lay24_1b.getVisibility() == View.VISIBLE && op24_1b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_1b, Toast.LENGTH_LONG).show();
                } else if (lay24_2.getVisibility() == View.VISIBLE && op24_2.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_2, Toast.LENGTH_LONG).show();
                } else if (lay24_2a.getVisibility() == View.VISIBLE && op24_2a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_2a, Toast.LENGTH_LONG).show();
                } else if (lay24_2b.getVisibility() == View.VISIBLE && op24_2b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_2b, Toast.LENGTH_LONG).show();
                } else if (lay24_3.getVisibility() == View.VISIBLE && op24_3.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_3, Toast.LENGTH_LONG).show();
                } else if (lay24_3a.getVisibility() == View.VISIBLE && op24_3a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_3a, Toast.LENGTH_LONG).show();
                } else if (lay24_3b.getVisibility() == View.VISIBLE && op24_3b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_3b, Toast.LENGTH_LONG).show();
                } else if (lay24_4.getVisibility() == View.VISIBLE && op24_4.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_4, Toast.LENGTH_LONG).show();
                } else if (lay24_4a.getVisibility() == View.VISIBLE && op24_4a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_4a, Toast.LENGTH_LONG).show();
                } else if (lay24_4b.getVisibility() == View.VISIBLE && op24_4b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_4b, Toast.LENGTH_LONG).show();
                } else if (lay24_5.getVisibility() == View.VISIBLE && op24_5.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_5, Toast.LENGTH_LONG).show();
                } else if (lay24_5a.getVisibility() == View.VISIBLE && op24_5a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_5a, Toast.LENGTH_LONG).show();
                } else if (lay24_5b.getVisibility() == View.VISIBLE && op24_5b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_5b, Toast.LENGTH_LONG).show();
                } else if (lay24_6.getVisibility() == View.VISIBLE && op24_6.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_6, Toast.LENGTH_LONG).show();
                } else if (lay24_6a.getVisibility() == View.VISIBLE && op24_6a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_6a, Toast.LENGTH_LONG).show();
                } else if (lay24_6b.getVisibility() == View.VISIBLE && op24_6b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_6b, Toast.LENGTH_LONG).show();
                } else if (lay24_7.getVisibility() == View.VISIBLE && op24_7.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_7, Toast.LENGTH_LONG).show();
                } else if (lay24_7a.getVisibility() == View.VISIBLE && op24_7a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_7a, Toast.LENGTH_LONG).show();
                } else if (lay24_7b.getVisibility() == View.VISIBLE && op24_7b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_7b, Toast.LENGTH_LONG).show();
                } else if (lay24_8.getVisibility() == View.VISIBLE && op24_8.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_8, Toast.LENGTH_LONG).show();
                } else if (lay24_8a.getVisibility() == View.VISIBLE && op24_8a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_8a, Toast.LENGTH_LONG).show();
                } else if (lay24_8b.getVisibility() == View.VISIBLE && op24_8b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_8b, Toast.LENGTH_LONG).show();
                } else if (lay24_9.getVisibility() == View.VISIBLE && op24_9.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_9, Toast.LENGTH_LONG).show();
                } else if (lay24_9a.getVisibility() == View.VISIBLE && op24_9a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_9a, Toast.LENGTH_LONG).show();
                } else if (lay24_9b.getVisibility() == View.VISIBLE && op24_9b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_9b, Toast.LENGTH_LONG).show();
                } else if (lay24_10.getVisibility() == View.VISIBLE && op24_10.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_10, Toast.LENGTH_LONG).show();
                } else if (lay24_10a.getVisibility() == View.VISIBLE && op24_10a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_10a, Toast.LENGTH_LONG).show();
                } else if (lay24_10b.getVisibility() == View.VISIBLE && op24_10b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura24_10b, Toast.LENGTH_LONG).show();
                } else if (lay25.getVisibility() == View.VISIBLE && op25.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura25, Toast.LENGTH_LONG).show();
                } else if (lay26.getVisibility() == View.VISIBLE && op26.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura26, Toast.LENGTH_LONG).show();
                }
//            else if (lay27.getVisibility() == View.VISIBLE && op27.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura27,Toast.LENGTH_LONG).show();}
                else if (lay27_1.getVisibility() == View.VISIBLE && op27_1.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_1, Toast.LENGTH_LONG).show();
                } else if (lay27_1a.getVisibility() == View.VISIBLE && op27_1a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_1a, Toast.LENGTH_LONG).show();
                } else if (lay27_1b.getVisibility() == View.VISIBLE && op27_1b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_1b, Toast.LENGTH_LONG).show();
                } else if (lay27_2.getVisibility() == View.VISIBLE && op27_2.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_2, Toast.LENGTH_LONG).show();
                } else if (lay27_2a.getVisibility() == View.VISIBLE && op27_2a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_2a, Toast.LENGTH_LONG).show();
                } else if (lay27_2b.getVisibility() == View.VISIBLE && op27_2b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_2b, Toast.LENGTH_LONG).show();
                } else if (lay27_3.getVisibility() == View.VISIBLE && op27_3.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_3, Toast.LENGTH_LONG).show();
                } else if (lay27_3a.getVisibility() == View.VISIBLE && op27_3a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_3a, Toast.LENGTH_LONG).show();
                } else if (lay27_3b.getVisibility() == View.VISIBLE && op27_3b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_3b, Toast.LENGTH_LONG).show();
                } else if (lay27_4.getVisibility() == View.VISIBLE && op27_4.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_4, Toast.LENGTH_LONG).show();
                } else if (lay27_4a.getVisibility() == View.VISIBLE && op27_4a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_4a, Toast.LENGTH_LONG).show();
                } else if (lay27_4b.getVisibility() == View.VISIBLE && op27_4b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_4b, Toast.LENGTH_LONG).show();
                } else if (lay27_5.getVisibility() == View.VISIBLE && op27_5.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_5, Toast.LENGTH_LONG).show();
                } else if (lay27_5a.getVisibility() == View.VISIBLE && op27_5a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_5a, Toast.LENGTH_LONG).show();
                } else if (lay27_5b.getVisibility() == View.VISIBLE && op27_5b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_5b, Toast.LENGTH_LONG).show();
                } else if (lay27_6.getVisibility() == View.VISIBLE && op27_6.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_6, Toast.LENGTH_LONG).show();
                } else if (lay27_6a.getVisibility() == View.VISIBLE && op27_6a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_6a, Toast.LENGTH_LONG).show();
                } else if (lay27_6b.getVisibility() == View.VISIBLE && op27_6b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_6b, Toast.LENGTH_LONG).show();
                } else if (lay27_7.getVisibility() == View.VISIBLE && op27_7.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_7, Toast.LENGTH_LONG).show();
                } else if (lay27_7a.getVisibility() == View.VISIBLE && op27_7a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_7a, Toast.LENGTH_LONG).show();
                } else if (lay27_7b.getVisibility() == View.VISIBLE && op27_7b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_7b, Toast.LENGTH_LONG).show();
                } else if (lay27_8.getVisibility() == View.VISIBLE && op27_8.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_8, Toast.LENGTH_LONG).show();
                } else if (lay27_8a.getVisibility() == View.VISIBLE && op27_8a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_8a, Toast.LENGTH_LONG).show();
                } else if (lay27_8b.getVisibility() == View.VISIBLE && op27_8b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_8b, Toast.LENGTH_LONG).show();
                } else if (lay27_9.getVisibility() == View.VISIBLE && op27_9.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_9, Toast.LENGTH_LONG).show();
                } else if (lay27_9a.getVisibility() == View.VISIBLE && op27_9a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_9a, Toast.LENGTH_LONG).show();
                } else if (lay27_9b.getVisibility() == View.VISIBLE && op27_9b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_9b, Toast.LENGTH_LONG).show();
                } else if (lay27_10.getVisibility() == View.VISIBLE && op27_10.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_10, Toast.LENGTH_LONG).show();
                } else if (lay27_10a.getVisibility() == View.VISIBLE && op27_10a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_10a, Toast.LENGTH_LONG).show();
                } else if (lay27_10b.getVisibility() == View.VISIBLE && op27_10b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura27_10b, Toast.LENGTH_LONG).show();
                } else if (lay28.getVisibility() == View.VISIBLE && op28.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura28, Toast.LENGTH_LONG).show();
                } else if (lay29.getVisibility() == View.VISIBLE && op29.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura29, Toast.LENGTH_LONG).show();
                }
//            else if (lay30.getVisibility() == View.VISIBLE && op30.matches("sin datos")){Toast.makeText(getBaseContext(),"CAPTURA:  " +  captura30,Toast.LENGTH_LONG).show();}
                else if (lay30_1.getVisibility() == View.VISIBLE && op30_1.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_1, Toast.LENGTH_LONG).show();
                } else if (lay30_1a.getVisibility() == View.VISIBLE && op30_1a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_1a, Toast.LENGTH_LONG).show();
                } else if (lay30_1b.getVisibility() == View.VISIBLE && op30_1b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_1b, Toast.LENGTH_LONG).show();
                } else if (lay30_2.getVisibility() == View.VISIBLE && op30_2.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_2, Toast.LENGTH_LONG).show();
                } else if (lay30_2a.getVisibility() == View.VISIBLE && op30_2a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_2a, Toast.LENGTH_LONG).show();
                } else if (lay30_2b.getVisibility() == View.VISIBLE && op30_2b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_2b, Toast.LENGTH_LONG).show();
                } else if (lay30_3.getVisibility() == View.VISIBLE && op30_3.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_3, Toast.LENGTH_LONG).show();
                } else if (lay30_3a.getVisibility() == View.VISIBLE && op30_3a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_3a, Toast.LENGTH_LONG).show();
                } else if (lay30_3b.getVisibility() == View.VISIBLE && op30_3b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_3b, Toast.LENGTH_LONG).show();
                } else if (lay30_4.getVisibility() == View.VISIBLE && op30_4.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_4, Toast.LENGTH_LONG).show();
                } else if (lay30_4a.getVisibility() == View.VISIBLE && op30_4a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_4a, Toast.LENGTH_LONG).show();
                } else if (lay30_4b.getVisibility() == View.VISIBLE && op30_4b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_4b, Toast.LENGTH_LONG).show();
                } else if (lay30_5.getVisibility() == View.VISIBLE && op30_5.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_5, Toast.LENGTH_LONG).show();
                } else if (lay30_5a.getVisibility() == View.VISIBLE && op30_5a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_5a, Toast.LENGTH_LONG).show();
                } else if (lay30_5b.getVisibility() == View.VISIBLE && op30_5b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_5b, Toast.LENGTH_LONG).show();
                } else if (lay30_6.getVisibility() == View.VISIBLE && op30_6.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_6, Toast.LENGTH_LONG).show();
                } else if (lay30_6a.getVisibility() == View.VISIBLE && op30_6a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_6a, Toast.LENGTH_LONG).show();
                } else if (lay30_6b.getVisibility() == View.VISIBLE && op30_6b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_6b, Toast.LENGTH_LONG).show();
                } else if (lay30_7.getVisibility() == View.VISIBLE && op30_7.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_7, Toast.LENGTH_LONG).show();
                } else if (lay30_7a.getVisibility() == View.VISIBLE && op30_7a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_7a, Toast.LENGTH_LONG).show();
                } else if (lay30_7b.getVisibility() == View.VISIBLE && op30_7b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_7b, Toast.LENGTH_LONG).show();
                } else if (lay30_8.getVisibility() == View.VISIBLE && op30_8.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_8, Toast.LENGTH_LONG).show();
                } else if (lay30_8a.getVisibility() == View.VISIBLE && op30_8a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_8a, Toast.LENGTH_LONG).show();
                } else if (lay30_8b.getVisibility() == View.VISIBLE && op30_8b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_8b, Toast.LENGTH_LONG).show();
                } else if (lay30_9.getVisibility() == View.VISIBLE && op30_9.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_9, Toast.LENGTH_LONG).show();
                } else if (lay30_9a.getVisibility() == View.VISIBLE && op30_9a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_9a, Toast.LENGTH_LONG).show();
                } else if (lay30_9b.getVisibility() == View.VISIBLE && op30_9b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_9b, Toast.LENGTH_LONG).show();
                } else if (lay30_10.getVisibility() == View.VISIBLE && op30_10.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_10, Toast.LENGTH_LONG).show();
                } else if (lay30_10a.getVisibility() == View.VISIBLE && op30_10a.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_10a, Toast.LENGTH_LONG).show();
                } else if (lay30_10b.getVisibility() == View.VISIBLE && op30_10b.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura30_10b, Toast.LENGTH_LONG).show();
                } else if (lay31.getVisibility() == View.VISIBLE && op31.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura31, Toast.LENGTH_LONG).show();
                } else if (lay32.getVisibility() == View.VISIBLE && op32.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + captura32, Toast.LENGTH_LONG).show();
                } else if (opAporta.matches("sin datos")) {
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
                } else {
                    valores();
                    btnGuardar.setEnabled(false);
//                dialogo();
                }

                break;

            case 3:

                if (opGenero.matches("sin datos")) {
                    Toast.makeText(getBaseContext(), "CAPTURA:  " + capturaGenero, Toast.LENGTH_LONG).show();
                } else {
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

//    public void CargaSpinner12() {
//        String var = "Selecciona";
//        if (var == null) {
//            var = "";
//        }
//        final String[] datos = new String[]{"" + var + "",
//                "Layda Sansores",
//                "Vidal Llerenas",
//                "Santiago Taboada",
//                "Manuel Negrete",
//                "Adri�n Rubalcava",
//                "N�stor L�pez N��ez",
//                "Francisco Chiguil",
//                "Armando Quintero",
//                "Clara Brugada",
//                "Patricia Ximena Ortiz Couturier",
//                "V�ctor Hugo Romo",
//                "Octavio Rivero Villase�or",
//                "Raymundo Mart�nez Vite",
//                "Patricia Elena Aceves Pastrana",
//                "Julio C�sar Moreno",
//                "Jos� Carlos Acosta Ruiz"
//        };
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
//                datos);
//        adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
//        spinner12.setAdapter(adaptador);
//        spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
//                rdPregunta12.clearCheck();
//            }
//
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

    public void CargaSpinnerEscala() {
        String var = "Selecciona";
        if (var == null) {
            var = "";
        }
        final String[] datos = new String[]{"" + var + "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "No sabe / No contest�"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.multiline_spinner_dropdown_item,
                datos);
        adaptador.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        spinnerCalifica.setAdapter(adaptador);
        spinnerCalifica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

                Log.i(TAG, "cqs ----------->>" + spinnerCalifica.getSelectedItem().toString());
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
        final String[] datos = new String[]{"" + var + "", "1", "2", "3", "4", "5", "6", "7", "No sabe / No contest�"};
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
                "Se presenta plan de acci�n para rescatar PEMEX", "Se har�n subastas de joyas incautadas",
                "Derrame de �cido en el Mar de Cort�s",
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
                "XOCHIMILCO", "No sabe / No contest�"};
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


    private void cargaAlcaldes(String alcaldia) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        alcaldes = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_alcalde FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_alcalde)>2";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                alcaldes.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }

    private void cargaPartidoAlcaldes(String part_alcaldia) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        partidos_alcaldes = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT (case when  length(partido_alcalde)>5 then (nombre_completo_alcalde || ' por ' || partido_alcalde)  else (nombre_completo_alcalde || ' por el ' || partido_alcalde) END ) as partido FROM candidatos_cdmx WHERE alcaldia='"+part_alcaldia+"' and length(nombre_completo_alcalde)>2";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                partidos_alcaldes.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }


    private void cargaAlcaldesMorena(String alcaldia) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        alcaldes_morena = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_alcalde FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_alcalde)>2 and partido_alcalde='MORENA'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                alcaldes_morena.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }



    private void cargaFederales(String alcaldia,String dttoFedral) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        federales = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_federal FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_federal)>2 and distrito_federal='"+dttoFedral+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                federales.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }

    private void cargaPartidoFederales(String part_alcaldia,String dttoFedral) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        partidos_federales = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT (case when  length(partido_federal)>5 then (nombre_completo_federal || ' por ' || partido_federal)  else (nombre_completo_federal || ' por el ' || partido_federal) END ) as partido FROM candidatos_cdmx WHERE alcaldia='"+part_alcaldia+"' and length(nombre_completo_federal)>2 and distrito_federal='"+dttoFedral+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                partidos_federales.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }


    private void cargaFederalesMorena(String alcaldia,String dttoFedral) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        federales_morena = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_federal FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_federal)>2 and partido_federal='MORENA' and distrito_federal='"+dttoFedral+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                federales_morena.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }


    private void cargaLocales(String alcaldia,String dttoLocal) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        locales = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_local FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_local)>2 and distrito_local='"+dttoLocal+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                locales.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }

    private void cargaPartidoLocales(String part_alcaldia,String dttoLocal) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        partidos_locales = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT (case when  length(partido_local)>5 then (nombre_completo_local || ' por ' || partido_local)  else (nombre_completo_local || ' por el ' || partido_local) END ) as partido FROM candidatos_cdmx WHERE alcaldia='"+part_alcaldia+"' and length(nombre_completo_local)>2 and distrito_local='"+dttoLocal+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                partidos_locales.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }


    private void cargaLocalesMorena(String alcaldia,String dttoLocal) {
        usdbh2 = new UsuariosSQLiteHelper2(this);
        locales_morena = new ArrayList<String>();
        db2 = usdbh2.getWritableDatabase();
        String selectQuery1 = "SELECT nombre_completo_local FROM candidatos_cdmx WHERE alcaldia='" + alcaldia + "' and length(nombre_completo_local)>2 and partido_local='MORENA' and distrito_local='"+dttoLocal+"'";
        Cursor c = db2.rawQuery(selectQuery1, null);
        if (c.moveToFirst()) {
            do {
                locales_morena.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        db2.close();
    }

///////////// FIN SPINNER /////////////

    private String sacaAlcaldia(String seccion) {
        Set<String> set = new HashSet<String>();
        final String F = "File dbfile";
        String Dele = "";
// Abrimos la base de datos 'DBUsuarios' en modo escritura
        usdbh2 = new UsuariosSQLiteHelper2(this);
        db2 = usdbh2.getReadableDatabase();
        String selectQuery = "SELECT alcaldia FROM candidatos_cdmx where seccion='" + seccion + "'";
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
