package mx.gob.cdmx.centroh.centroh;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import mx.gob.cdmx.centroh.BuildConfig;
import mx.gob.cdmx.centroh.FormatoActivity;
import mx.gob.cdmx.centroh.R;
import mx.gob.cdmx.centroh.UsuariosSQLiteHelper3;
import mx.gob.cdmx.centroh.db.DaoManager;
import mx.gob.cdmx.centroh.model.Catalogos;
import mx.gob.cdmx.centroh.model.Usuario;
import mx.gob.cdmx.centroh.service.GPSTracker;
import mx.gob.cdmx.centroh.service.Imei;

import static android.Manifest.permission.READ_CONTACTS;

//import android.support.v7.app.AppCompatActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private static final String TAG            = "DIF-LoginActivity";
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    Calendar c = Calendar.getInstance();

    SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
    String formattedDate3 = df3.format(c.getTime());
    SimpleDateFormat df5 = new SimpleDateFormat("HH:mm a");
    String formattedDate5 = df5.format(c.getTime());
    String latitud, longitud;


    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private Usuario usuario;
    private SQLiteDatabase db;
    private UsuariosSQLiteHelper3 usdbh;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView versionTextView;

    private ImageView imageView;

    private SQLiteDatabase dbs;
    private UsuariosSQLiteHelper3 usdbhs;

    List<Catalogos> catalogosList = new ArrayList<>();

    List<Catalogos> List = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.RECEIVE_BOOT_COMPLETED,
                            Manifest.permission.LOCATION_HARDWARE,
                            Manifest.permission.ACCESS_WIFI_STATE},
                    1);
        }
        usdbh = new UsuariosSQLiteHelper3(LoginActivity.this);
        db = usdbh.getWritableDatabase();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        versionTextView = findViewById(R.id.versionTextView);


        File directory;
        File file;
        File sdCard;
        sdCard = Environment.getExternalStorageDirectory();
        FileOutputStream fout = null;
        try {

            directory = new File(sdCard.getAbsolutePath() + "/Mis_archivos");
            directory.mkdirs();
            directory = new File(sdCard.getAbsolutePath()+ "/Fotos/ApoyoAlimentario_"+formattedDate3);
            directory.mkdirs();
            directory = new File(sdCard.getAbsolutePath()+ "/Fotos/ApoyoAlimentario_"+formattedDate3+"N");
            directory.mkdirs();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        mEmailView.setText("fortaleza@territorial");
//        mPasswordView.setText("fortaleza");

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "¡Permisos autorizados!", Toast.LENGTH_SHORT).show();
                    //Intent i = new Intent(this, MainActivity.class); //start activity
                    //startActivity(i);
                    //startService(new Intent(this, AndroidLocationServices.class));
/*                    bandera=    pregunta(AndroidLocationServices.class);
                    if(bandera){
                        Toast.makeText(this, "EL SERVICIO YA ESTÁ ARRRIBA", Toast.LENGTH_LONG).show();

                        finish();
                    }
                    else {
                        Intent intent = new Intent();
                        ComponentName comp = new ComponentName(this.getPackageName(),
                                AndroidLocationServices.class.getName());
                        //  startService(new Intent(this, AndroidLocationServices.class));
                        //  AndroidLocationServices.enqueueWork(this, (intent.setComponent(comp)));

                        ContextCompat.startForegroundService(this,intent.setComponent(comp));
                        finish();
                    }*/

                }

                else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void attemptLogin() {

        // Reset errors.
        //mEmailView.setText("admin@territorial.cdmx.gob.mx");
        //mPasswordView.setText("admin");
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
//        if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
            Imei imei = new Imei(this);
            String device_info = imei.getDeviceInof();
            Log.d(TAG, device_info);
            RequestParams params = new RequestParams();
            params.put("email", email);
            params.put("password", password);
            params.put("project", getString(R.string.project_object));
            params.put("imei", imei.getImei());
            params.put("app_version", BuildConfig.VERSION_NAME);
            params.put("device_info", imei.getDeviceInof()); // adicionar de la clase IMEI
            GPSTracker gps = new GPSTracker(LoginActivity.this);
            double latitud = gps.getLatitude();
            double longitud = gps.getLongitude();

            Log.i(TAG, "----------> la latitud: "+ latitud);
            Log.i(TAG, "----------> la longitud: "+ longitud);
            params.put("latitude", latitud);
            params.put("longitude", longitud);
            AsyncHttpClient client = new AsyncHttpClient();


            // client.setBasicAuth("email","password", new AuthScope("example.com", 80, AuthScope.ANY_REALM));
            // client.get("https://example.com");

            RequestHandle requestHandle = client.post(getResources().getString(R.string.url_aplicacion) + "/api/auth/login", params, new AsyncHttpResponseHandler() {
                String jsonToken = "";
                DaoManager daoManager = new DaoManager(db);
                int id_user;
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String nombreStr = "";
                    Log.d(TAG, "Realizo la conexión");

                    Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                    String json = new String(responseBody);
                    try {

                        JSONObject jsonObject = new JSONObject(json);
                        Log.d(TAG, "e2lira -----------> Data: " + jsonObject.get("data"));
                        //
                        // para acceder al token
                        //

                        jsonToken = jsonObject.getJSONObject("data").getString("access_token");
                        JSONObject jsonUser = jsonObject.getJSONObject("data").getJSONObject("user");
                        int id_user = jsonUser.getInt("user_id");
                        nombreStr = jsonUser.getString("name");

                        Gson gson  = new Gson();
                        Type collectionType = new TypeToken<Usuario>() { }.getType();
                        usuario = gson.fromJson(jsonUser.toString(), collectionType);

                        usuario.setAccess_token(jsonObject.getJSONObject("data").getString("access_token"));
                        usuario.setToken_type(jsonObject.getJSONObject("data").getString("token_type"));

                        Log.d(TAG, "e2lira -----------> Token: " + jsonToken );
                        Log.d(TAG, "e2lira ----------->  id: " + id_user);

                        usdbhs = new UsuariosSQLiteHelper3(LoginActivity.this);
                        dbs = usdbhs.getWritableDatabase();
                        DaoManager daoManager = new DaoManager(dbs);

                        daoManager.delete(Usuario.class);

                        daoManager.insert(usuario);


                    } catch (JSONException e){
                        Log.e(TAG, e.getMessage());
                    }

                    catalogos();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    try {
                        Log.e(TAG, "existe un error en la conexión status code: " + statusCode);
                        String json = new String(responseBody);
                    }catch (Exception e){

                    }
                    showProgress(false);
                    AlertDialog.Builder bd = new AlertDialog.Builder(LoginActivity.this);
                    AlertDialog ad = bd.create();
                    ad.setTitle(getString(R.string.app_name));
                    ad.setMessage("Existe un error en la conexión con el sistema central, o la contraseña esta incorrecta");
                    ad.show();
                }
            });

        }
    }

    public void catalogos(){

        catalogosList.clear();

        Imei imei = new Imei(this);
        String device_info = imei.getDeviceInof();

        Log.d(TAG, device_info);

        RequestParams params = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Authorization", usuario.getToken_type()+usuario.getAccess_token());

        RequestHandle requestHandle = client.get(getResources().getString(R.string.url_aplicacion) + "/api/achpredial/catalogs", params, new AsyncHttpResponseHandler() {
            String jsonToken = "";
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(TAG, "Realizo la conexión");
                Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                try {

                    String json = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(json);
                    //String jsonUsuarios = jsonObject.getJSONObject("data").getJSONObject("usuarios").toString();
                    String jsonCatalogos = jsonObject.getJSONObject("data").getJSONArray("catalogs").toString();

                    Gson gson  = new Gson();
                    Type collectionType = new TypeToken<List<Catalogos>>() { }.getType();
                    List<Catalogos> listaCatalogo = gson.fromJson(jsonCatalogos, collectionType);


                    //arraylist = new ArrayList<>();


                    usdbhs = new UsuariosSQLiteHelper3(LoginActivity.this);
                    dbs = usdbhs.getWritableDatabase();
                    DaoManager daoManager = new DaoManager(dbs);

                    if (!listaCatalogo.isEmpty() || listaCatalogo.size() > 0 || listaCatalogo != null){

                        daoManager.delete(Catalogos.class);

                        for (Catalogos catalogos : listaCatalogo){

                            daoManager.insert(catalogos);

                        }

                        showProgress(false);
                        Intent intent = new Intent(LoginActivity.this, FormatoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Bundle bundle = new Bundle();
                        intent.putExtras(bundle);
                        finish();
                        startActivity(intent);
                    }else{
                        new AestheticDialog.Builder(LoginActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                .setTitle("Error de servidor")
                                .setMessage("Error al cargar los catalogos")
                                .show();
                    }

                } catch (JSONException e){
                    Log.e(TAG, e.getMessage());
                    new AestheticDialog.Builder(LoginActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setTitle("Error de servidor")
                            .setMessage("Error de servidor, contacte con el administrador")
                            .show();
                }


                showProgress(false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "existe un error en la conexión");
                if(responseBody != null){
                    Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                }
                try {
                    String json = new String(responseBody);
                }catch (Exception e){

                }

                showProgress(false);
                new AestheticDialog.Builder(LoginActivity.this, DialogStyle.CONNECTIFY, DialogType.ERROR)
                        .setTitle("Error")
                        .setMessage("Existe un error de conexión, intente mas tarde")
                        .show();

            }
        });

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


}