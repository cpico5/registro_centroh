package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;


public class FormatoFirmaActivity extends AppCompatActivity {

    Usuario usuario;

    Entrevista entrevista;

    private static final String TAG  = "FormatoFirmaActivity";

    TextView textNombre, continuar;

    ImageView firma;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
    String formattedDate3 = df3.format(c.getTime());

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

        continuar = findViewById(R.id.continuar);

        if (entrevista.getFirma() != null){

            try {
                File mypath = new File(entrevista.getFirma().getFirmaPath());
                firma.setImageBitmap(BitmapFactory.decodeFile(String.valueOf(mypath)));
            }catch (Exception e){
                new AestheticDialog.Builder(FormatoFirmaActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                        .setTitle("Error")
                        .setMessage("Existió un error cargar la firma" + e.getMessage())
                        .show();
            }

            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FormatoFirmaActivity.this, FotoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(USUARIO, usuario);
                    intent.putExtra(ENTREVISTA, entrevista);
                    finish();
                    startActivity(intent);
                }
            });


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