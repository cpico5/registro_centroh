package mx.gob.cdmx.centroh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogAnimation;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;
import com.thecode.aestheticdialogs.OnDialogClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;
import mx.gob.cdmx.centroh.centroh.LoginActivity;
import mx.gob.cdmx.centroh.db.DaoManager;
import mx.gob.cdmx.centroh.model.Entrevista;
import mx.gob.cdmx.centroh.model.Firma;
import mx.gob.cdmx.centroh.model.Foto;
import mx.gob.cdmx.centroh.model.Usuario;
import mx.gob.cdmx.centroh.service.Imei;

import static android.os.Environment.getExternalStorageDirectory;

public class FotoActivity extends AppCompatActivity {

    private static final String TAG  = "FotoActivity";

    Usuario usuario;
    Entrevista entrevista = new Entrevista();
    Firma firma = new Firma();

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_CODE = 0;
    static final int REQUEST_TAKE_PHOTO = 1;

    ImageView foto;

    ImageButton imageButton2;

    File directory;

    File file;

    String imagescale;

    SQLiteHelper3 sqLiteHelper3;
    SQLiteDatabase sqLiteDatabase;

    SQLiteHelper4 sqLiteHelper4;
    SQLiteDatabase sqLiteDatabase4;

    private SQLiteDatabase dbs;
    private UsuariosSQLiteHelper3 usdbhs;

    DaoManager daoManager;

    String currentPhotoPath;

    Button guardar;

    Foto foto1 = new Foto();

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        Intent startingIntent = getIntent();

        dialog = new ProgressDialog(FotoActivity.this);

        foto = findViewById(R.id.foto);

        imageButton2 = findViewById(R.id.imageButton2);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });

        guardar = findViewById(R.id.guardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foto1.getFotoPath() != null){
                    if (entrevista.getDocument_id() == 0){
                        guardaws();
                    }else if (firma.getDocument_id() != 0 && foto1.getDocument_id() != 0){
                        imageswsFirma(firma);
                    }

                }else{
                    new AestheticDialog.Builder(FotoActivity.this, DialogStyle.FLAT, DialogType.WARNING)
                            .setTitle("Foto")
                            .setMessage("Debe tomar una foto")
                            .show();
                    return;
                }
            }
        });

    }



    private void ssdispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, REQUEST_IMAGE_CODE);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                try {
                    Uri output = Uri.fromFile(new File(photoFile.toURI()));

                    //StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    //StrictMode.setVmPolicy(builder.build());
                    //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                    //startActivityForResult(intent, 0);



                    Uri photoURI = FileProvider.getUriForFile(this,
                            BuildConfig.APPLICATION_ID + ".fileProvider",
                            photoFile);
                    //startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    takePictureIntent.putExtras(Intent.getIntent(MediaStore.ACTION_IMAGE_CAPTURE));
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            // Get the dimensions of the bitmap

            File exit = new File(currentPhotoPath);

            if (exit.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

                int width=640;
                int heigth=640;

                Bitmap imageScaled = Bitmap.createScaledBitmap(bitmap, width, heigth, false);

                UUID uuid = UUID.randomUUID();
                convertBitmapToFile(imageScaled, uuid.toString());

                sqLiteHelper3 = new SQLiteHelper3(this);
                sqLiteDatabase = sqLiteHelper3.getWritableDatabase();
                daoManager = new DaoManager(sqLiteDatabase);

                daoManager.delete(Foto.class);

                foto1.setFotoPath(imagescale);
                foto1.setUuid(uuid);
                foto1.setType_photo("Fachada");
                foto1.setId_type_photo(3);
                GPSTracker gps = new GPSTracker(FotoActivity.this);
                foto1.setLatitude(gps.getLatitude());
                foto1.setLongitude(gps.getLongitude());

                daoManager.insert(foto1);

                foto.setImageBitmap(imageScaled);



                try {
                    new File(currentPhotoPath).delete();
                } catch (Exception e) {
                    new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                            .setTitle("Error")
                            .setMessage("Error al eliminar foto original")
                            .show();
                }
            }else {
                new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                        .setTitle("Error")
                        .setMessage("No se pudo tomar la fotografía")
                        .show();
            }




        }
    }

    private void guardaws(){


        usdbhs = new UsuariosSQLiteHelper3(FotoActivity.this);
        dbs = usdbhs.getWritableDatabase();
        DaoManager daoManagerUser = new DaoManager(dbs);


        usuario = (Usuario) daoManagerUser.findOne(Usuario.class);

        sqLiteHelper3 = new SQLiteHelper3(this);
        sqLiteDatabase = sqLiteHelper3.getWritableDatabase();
        daoManager = new DaoManager(sqLiteDatabase);

        entrevista = (Entrevista) daoManager.findOne(Entrevista.class);

        Imei imei = new Imei(this);
        String device_info = imei.getDeviceInof();
        Log.d(TAG, device_info);
        RequestParams params = new RequestParams();
        params.put("person", entrevista.getSuscribe());
        params.put("person_position", entrevista.getCaracter());
        params.put("street", entrevista.getInmueble());
        params.put("no_ext", entrevista.getNoOficial());
        params.put("no_int", entrevista.getNoInterior());
        params.put("settlement_id", entrevista.getColonia());
        params.put("municipality_id", entrevista.getAlcaldia());
        params.put("cp", entrevista.getCp());
        params.put("account_predial", entrevista.getCuentaPredial());
        params.put("phone", entrevista.getTelefono());


        GPSTracker gps = new GPSTracker(FotoActivity.this);
        double latitud = gps.getLatitude();
        double longitud = gps.getLongitude();

        Log.i(TAG, "----------> la latitud: "+ latitud);
        Log.i(TAG, "----------> la longitud: "+ longitud);
        params.put("latitude", latitud);
        params.put("longitude", longitud);
        AsyncHttpClient client = new AsyncHttpClient();

        dialog.setTitle("Enviando datos");
        dialog.setMessage("Espere...");
        dialog.setCancelable(false);
        dialog.show();


        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Authorization", usuario.getToken_type()+usuario.getAccess_token());
        // client.setBasicAuth("email","password", new AuthScope("example.com", 80, AuthScope.ANY_REALM));
        // client.get("https://example.com");

        RequestHandle requestHandle = client.post(getResources().getString(R.string.url_aplicacion) + "/api/achpredial/document", params, new AsyncHttpResponseHandler() {
            String jsonToken = "";
            int id_user;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                int id = 0;
                String nombreStr = "";
                Log.d(TAG, "Realizo la conexión");

                Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                String json = new String(responseBody);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(json);
                    id = jsonObject.getJSONObject("data").getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                dialog.dismiss();

                sqLiteHelper3 = new SQLiteHelper3(FotoActivity.this);
                sqLiteDatabase = sqLiteHelper3.getWritableDatabase();
                daoManager = new DaoManager(sqLiteDatabase);

                entrevista.setDocument_id(id);

                daoManager.update(entrevista,"id=?", new String[]{String.valueOf(entrevista.getId())});

                firma = (Firma) daoManager.findOne(Firma.class);

                firma.setDocument_id(id);

                daoManager.update(firma,"id=?", new String[]{String.valueOf(firma.getId())});

                foto1 = (Foto) daoManager.findOne(Foto.class);

                foto1.setDocument_id(id);

                daoManager.update(foto1,"id=?",new String[]{String.valueOf(foto1.getId())});


                sqLiteHelper4 = new SQLiteHelper4(FotoActivity.this);
                sqLiteDatabase4 = sqLiteHelper4.getWritableDatabase();
                DaoManager daoManagers = new DaoManager(sqLiteDatabase4);

                try {
                    daoManagers.insert(entrevista);
                    daoManagers.insert(foto1);
                    daoManagers.insert(firma);
                }catch (SQLException e){
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .show();
                        return;
                    }
                }

                imageswsFirma(firma);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                try {
                    Log.e(TAG, "existe un error en la conexión status code: " + statusCode);
                    String json = new String(responseBody);
                    Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        try {
                            new AestheticDialog.Builder(FotoActivity.this, DialogStyle.CONNECTIFY, DialogType.ERROR)
                                    .setTitle("Error")
                                    .setMessage("Existe un error de conexión, intente mas tarde")
                                    .show();
                        } catch (Exception r) {

                        }
                    }
                }catch (Exception e){
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                .setTitle("Error de servidor")
                                .setMessage("Error de servidor, contacte con el administrador")
                                .show();
                    }

                }

            }
        });

    }

    private void imageswsFirma(Firma firma){

        Imei imei = new Imei(this);
        String device_info = imei.getDeviceInof();

        Log.d(TAG, device_info);

        RequestParams params = new RequestParams();

        GPSTracker gpsTracker = new GPSTracker(FotoActivity.this);

        params.put("uuid", firma.getUuid().toString());

        params.put("document_id",firma.getDocument_id());

        try{
            File file = new File(firma.getFirmaPath());
            params.put("photo", file);
        }catch(FileNotFoundException e){
            if(!((Activity) FotoActivity.this).isFinishing()) {
                new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                        .setTitle("Error!")
                        .setMessage("No se encontro la firma")
                        .show();
                return;
            }
        }
        params.put("latitude", gpsTracker.getLatitude());
        params.put("longitude", gpsTracker.getLongitude());
        params.put("type_photo",firma.getId_type_photo());

        dialog.setTitle("Enviando Firma");
        dialog.setMessage("Espere...");
        dialog.setCancelable(false);
        dialog.show();


        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Authorization", usuario.getToken_type()+usuario.getAccess_token());

        client.setConnectTimeout(300000);
        client.setTimeout(300000);

        String url = "";



        RequestHandle requestHandle = client.post( getResources().getString(R.string.url_aplicacion) +"/api/achpredial/uploadImages", params, new AsyncHttpResponseHandler(Looper.myLooper()) {
            String jsonToken = "";
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(TAG, "Realizo la conexión");
                Log.d(TAG, "e2lira -----------> " + new String(responseBody));

                dialog.dismiss();

                    String json = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    imagewsFoto(foto1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                try {
                    Log.e(TAG, "existe un error en la conexión status code: " + statusCode);
                    Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                    String json = new String(responseBody);
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        try {
                            new AestheticDialog.Builder(FotoActivity.this, DialogStyle.CONNECTIFY, DialogType.ERROR)
                                    .setTitle("Error")
                                    .setMessage("Existe un error de conexión, intente mas tarde")
                                    .show();
                        } catch (Exception r) {

                        }
                    }
                }catch (Exception e){
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                .setTitle("Error de servidor")
                                .setMessage("Error de servidor, contacte con el administrador")
                                .show();
                    }

                }

            }
        });

    }

    private void imagewsFoto(Foto foto2){

        Imei imei = new Imei(this);
        String device_info = imei.getDeviceInof();

        Log.d(TAG, device_info);

        RequestParams params = new RequestParams();

        GPSTracker gpsTracker = new GPSTracker(FotoActivity.this);

        params.put("uuid", foto2.getUuid().toString());

        params.put("document_id",foto2.getDocument_id());

        try{
            File file = new File(foto2.getFotoPath());
            params.put("photo", file);
        }catch(FileNotFoundException e){
            if(!((Activity) FotoActivity.this).isFinishing()) {
                new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                        .setTitle("Error!")
                        .setMessage("No se encontro la firma")
                        .show();
                return;
            }
        }
        params.put("latitude", gpsTracker.getLatitude());
        params.put("longitude", gpsTracker.getLongitude());
        params.put("type_photo",foto2.getId_type_photo());

        dialog.setTitle("Enviando Foto");
        dialog.setMessage("Espere...");
        dialog.setCancelable(false);
        dialog.show();


        AsyncHttpClient client = new AsyncHttpClient();
        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Authorization", usuario.getToken_type()+usuario.getAccess_token());

        client.setConnectTimeout(300000);
        client.setTimeout(300000);

        String url = "";



        RequestHandle requestHandle = client.post( getResources().getString(R.string.url_aplicacion) +"/api/achpredial/uploadImages", params, new AsyncHttpResponseHandler(Looper.myLooper()) {
            String jsonToken = "";
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.d(TAG, "Realizo la conexión");
                Log.d(TAG, "e2lira -----------> " + new String(responseBody));

                dialog.dismiss();

                String json = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    sqLiteHelper3 = new SQLiteHelper3(FotoActivity.this);
                    sqLiteDatabase = sqLiteHelper3.getWritableDatabase();
                    daoManager = new DaoManager(sqLiteDatabase);

                    try {
                        daoManager.deleteClausule(Entrevista.class,"document_id=?", new String[]{String.valueOf(entrevista.getDocument_id())});
                        daoManager.deleteClausule(Firma.class,"document_id=?", new String[]{String.valueOf(entrevista.getDocument_id())});
                        daoManager.deleteClausule(Foto.class,"document_id=?", new String[]{String.valueOf(entrevista.getDocument_id())});
                    }catch (SQLException e){
                        if(!((Activity) FotoActivity.this).isFinishing()) {
                            new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                    .setTitle("Error")
                                    .setMessage(e.getMessage())
                                    .show();
                            return;
                        }
                    }
                    new AestheticDialog.Builder(FotoActivity.this, DialogStyle.FLAT, DialogType.SUCCESS)
                            .setTitle("Éxito")
                            .setMessage("Registrado correctamente")
                            .setCancelable(false)
                            .setDarkMode(false)
                            .setGravity(Gravity.CENTER)
                            .setAnimation(DialogAnimation.SHRINK)
                            .setOnClickListener(new OnDialogClickListener() {
                                @Override
                                public void onClick(AestheticDialog.Builder builder) {
                                    AlertDialog.Builder builderDialog = new AlertDialog.Builder(FotoActivity.this);
                                    builderDialog.setMessage("¿Desea hacer un nuevo registro?")
                                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent(FotoActivity.this, FormatoActivity.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent(FotoActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    builderDialog.create();
                                    builderDialog.show();
                                }
                            }).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                try {
                    Log.e(TAG, "existe un error en la conexión status code: " + statusCode);
                    Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                    String json = new String(responseBody);
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        try {
                            new AestheticDialog.Builder(FotoActivity.this, DialogStyle.CONNECTIFY, DialogType.ERROR)
                                    .setTitle("Error")
                                    .setMessage("Existe un error de conexión, intente mas tarde")
                                    .show();
                        } catch (Exception r) {

                        }
                    }
                }catch (Exception e){
                    if(!((Activity) FotoActivity.this).isFinishing()) {
                        new AestheticDialog.Builder(FotoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                                .setTitle("Error de servidor")
                                .setMessage("Error de servidor, contacte con el administrador")
                                .show();
                    }

                }

            }
        });

    }

    private void convertBitmapToFile(Bitmap bitmap, String name) {


        File sdCard;
        sdCard = getExternalStorageDirectory();
        FileOutputStream fout = null;
        try {
            sdCard = new File(sdCard.getAbsolutePath()+ "/Fotos/CentroH");
            sdCard.mkdirs();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        File imageFile = new File(sdCard, name + ".jpg");



        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();

            imagescale = imageFile.getAbsolutePath();
        } catch (Exception e) {
            Log.e(String.valueOf(FotoActivity.class), "Error writing bitmap", e);
        }
    }



    public File createImageFile() throws IOException {
        // Create an image file name
        File sdCard;
        sdCard = getExternalStorageDirectory();
        FileOutputStream fout = null;
        try {
            sdCard = new File(sdCard.getAbsolutePath()+ "/Fotos/CentroH");
            sdCard.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }


        UUID uuid = UUID.randomUUID();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = uuid.toString();
        File storageDir = sdCard;
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}