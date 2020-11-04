package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;

public class FotoActivity extends AppCompatActivity {

    private static final String TAG  = "FotoActivity";

    Usuario usuario;
    Entrevista entrevista;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView foto;

    ImageButton imageButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        Intent startingIntent = getIntent();

        if(startingIntent == null) {
            Log.e(TAG,"No Intent?  We're not supposed to be here...");
            finish();
            return;
        }
        /*
         * Paso de parametros entre Intent
         */
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            usuario = (Usuario) savedInstanceState.getSerializable(USUARIO);
            entrevista = (Entrevista) savedInstanceState.getSerializable(ENTREVISTA);


        } else {
            // Probably initialize members with default values for a new instance
            usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
            entrevista = (Entrevista) startingIntent.getSerializableExtra(ENTREVISTA);
        }

        foto = findViewById(R.id.foto);

        imageButton2 = findViewById(R.id.imageButton2);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dispatchTakePictureIntent();

            }
        });

    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            foto.setImageBitmap(imageBitmap);
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        UUID uuid = UUID.randomUUID();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = uuid.toString();
        File storageDir = getExternalFilesDir(String.valueOf(Environment.getExternalStorageDirectory()));
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