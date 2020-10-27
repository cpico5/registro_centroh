package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Usuario;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;


public class FormatoFirmaActivity extends AppCompatActivity {

    Usuario usuario;

    Entrevista entrevista;

    private static final String TAG  = "FormatoFirmaActivity";

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

    }
}