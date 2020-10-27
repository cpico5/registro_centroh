package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;


public class FormatoFirmaActivity extends AppCompatActivity {

    Usuario usuario;

    Entrevista entrevista;

    private static final String TAG  = "FormatoFirmaActivity";

    TextView textNombre;

    ImageButton firma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato_firma);

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

        textNombre = findViewById(R.id.textNombre);

        textNombre.setText(entrevista.getSuscribe());

        firma = findViewById(R.id.imageButtonFirma);

        if (entrevista.getFirma() != null){

            try {
                int targetW = firma.getWidth()/2;
                int targetH = firma.getHeight()/2;

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(entrevista.getFirma().getFirmaPath(), bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(entrevista.getFirma().getFirmaPath(), bmOptions);
                firma.setImageBitmap(bitmap);
            }catch (Exception e){
                new AestheticDialog.Builder(FormatoFirmaActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                        .setTitle("Error")
                        .setMessage("Existió un error cargar la imagen" + e.getMessage())
                        .show();
            }


        }

    }

    public void leermas(View v){
        Dialog dialog=new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.activity_texto);
        dialog.show();
    }

    public void firma(View v){
        Intent intent = new Intent(FormatoFirmaActivity.this, CapturaFirma.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(USUARIO, usuario);
        intent.putExtra(ENTREVISTA, entrevista);
        finish();
        startActivity(intent);
    }
}