package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.util.TextUtils;
import mx.gob.cdmx.estudioscdmx.db.Anotaciones.AutoIncrement;
import mx.gob.cdmx.estudioscdmx.db.Anotaciones.PrimaryKey;
import mx.gob.cdmx.estudioscdmx.model.Entrevista;
import mx.gob.cdmx.estudioscdmx.model.Usuario;
import mx.gob.cdmx.estudioscdmx.service.Utils;

import static mx.gob.cdmx.estudioscdmx.Nombre.ENTREVISTA;
import static mx.gob.cdmx.estudioscdmx.Nombre.USUARIO;

public class FormatoActivity extends AppCompatActivity {

    Usuario usuario;


    EditText editTextDate, editSuscribe, editCaracter, editInmueble, editNoOficial, editNoInterior,
            editColonia, editAlcaldia, editCP, editCuentaPredial, editTelefono;

    Button buttonContinuar;

    int id1,id2;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate3 = df3.format(c.getTime());

    private static final String TAG  = "FormatoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato);



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


        } else {
            // Probably initialize members with default values for a new instance
            usuario = (Usuario) startingIntent.getSerializableExtra(USUARIO);
        }


        editTextDate = findViewById(R.id.editTextDate);
        editSuscribe = findViewById(R.id.editSuscribe);
        editCaracter = findViewById(R.id.editCaracter);
        editInmueble = findViewById(R.id.editInmueble);
        editNoOficial = findViewById(R.id.editNoOficial);
        editNoInterior = findViewById(R.id.editNoInterior);
        editColonia = findViewById(R.id.editColonia);
        editAlcaldia = findViewById(R.id.editAlcaldia);
        editCP = findViewById(R.id.editCP);
        editCuentaPredial = findViewById(R.id.editCuentaPredial);
        editTelefono = findViewById(R.id.editTelefono);


        id1 = editCP.getId();
        id2 = editTelefono.getId();



        buttonContinuar = findViewById(R.id.buttonContinuar);

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<EditText> editTextList = new ArrayList<>();

                editTextList.add(editTextDate);
                editTextList.add(editSuscribe);
                editTextList.add(editCaracter);
                editTextList.add(editInmueble);
                editTextList.add(editNoOficial);
                editTextList.add(editNoInterior);
                editTextList.add(editColonia);
                editTextList.add(editAlcaldia);
                editTextList.add(editCP);
                editTextList.add(editCuentaPredial);
                editTextList.add(editTelefono);

                if (validations(editTextList)){
                   data();
                };

            }
        });

        editTextDate.setEnabled(false);
        editTextDate.setText(formattedDate3);

    }

    private boolean validations(List<EditText> editTextList){

        for (EditText editText : editTextList){
            if(TextUtils.isEmpty(editText.getText().toString())) {

                editText.setError("Este campo es obligatorio");
                editText.requestFocus();
                return false;

            }
            if (editText.getId() == id1){

                if (!Utils.isShort(editText.getText().toString(),6)){
                    editText.setError("Este campo de 6 digitos");
                    editText.requestFocus();
                    return false;
                }
            }
            if (editText.getId() == id2){

                if (!Utils.isShort(editText.getText().toString(), 10)){
                    editText.setError("Este campo de 10 digitos");
                    editText.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    public void data() {

        Entrevista entrevista = new Entrevista();
        try {
            entrevista.setFecha(editTextDate.getText().toString());
            entrevista.setSuscribe(editSuscribe.getText().toString());
            entrevista.setCaracter(editCaracter.getText().toString());
            entrevista.setInmueble(editInmueble.getText().toString());
            entrevista.setNoOficial(editNoOficial.getText().toString());
            entrevista.setNoInterior(editNoInterior.getText().toString());
            entrevista.setColonia(editColonia.getText().toString());
            entrevista.setAlcaldia(editAlcaldia.getText().toString());
            entrevista.setCp(Integer.parseInt(editCP.getText().toString()));
            entrevista.setCuentaPredial(editCuentaPredial.getText().toString());
            entrevista.setTelefono(editTelefono.getText().toString());

            Intent intent = new Intent(FormatoActivity.this, FormatoFirmaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(USUARIO, usuario);
            intent.putExtra(ENTREVISTA, entrevista);
            finish();
            startActivity(intent);


        }catch (Exception e){
            new AestheticDialog.Builder(FormatoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                    .setTitle("Error")
                    .setMessage("Existió un error al capturar los datos" + e.getMessage())
                    .show();
        }

    }

}