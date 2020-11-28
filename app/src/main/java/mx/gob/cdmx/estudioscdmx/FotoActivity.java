package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;
import mx.gob.cdmx.estudioscdmx.centroh.LoginActivity;
import mx.gob.cdmx.estudioscdmx.db.DaoManager;
import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Firma;
import mx.gob.cdmx.estudioscdmx.model.Foto;
import mx.gob.cdmx.estudioscdmx.model.Usuario;
import mx.gob.cdmx.estudioscdmx.service.Imei;

import static android.os.Environment.getExternalStorageDirectory;
import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;

public class FotoActivity extends AppCompatActivity {

    private static final String TAG  = "FotoActivity";

    Usuario usuario;
    Entrevista entrevista;
    Firma firma;

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

    private SQLiteDatabase dbs;
    private UsuariosSQLiteHelper3 usdbhs;

    DaoManager daoManager;

    String currentPhotoPath;

    Button guardar;

    Foto foto1 = new Foto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        Intent startingIntent = getIntent();

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
                    guardaws();
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
                foto1.setType_photo("Firma");
                foto1.setId_type_photo(1);
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

        firma = (Firma) daoManager.findOne(Firma.class);

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


        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
        client.addHeader("Authorization", usuario.getToken_type()+usuario.getAccess_token());
        // client.setBasicAuth("email","password", new AuthScope("example.com", 80, AuthScope.ANY_REALM));
        // client.get("https://example.com");

        RequestHandle requestHandle = client.post(getResources().getString(R.string.url_aplicacion) + "/api/achpredial/document", params, new AsyncHttpResponseHandler() {
            String jsonToken = "";
            int id_user;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String nombreStr = "";
                Log.d(TAG, "Realizo la conexión");

                Log.d(TAG, "e2lira -----------> " + new String(responseBody));
                String json = new String(responseBody);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                try {
                    Log.e(TAG, "existe un error en la conexión status code: " + statusCode);
                    String json = new String(responseBody);
                }catch (Exception e){

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