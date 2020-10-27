package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
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

public class FormatoActivity extends AppCompatActivity {

    EditText editTextDate, editSuscribe, editCaracter, editInmueble, editNoOficial, editNoInterior,
            editColonia, editAlcaldia, editCP, editCuentaPredial, editTelefono;

    Button buttonContinuar;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate3 = df3.format(c.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato);


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
            entrevista.setTelefono(Integer.parseInt(editTelefono.getText().toString()));
        }catch (Exception e){
            new AestheticDialog.Builder(FormatoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                    .setTitle("Error")
                    .setMessage("Existi� un error al capturar los datos")
                    .show();
        }

    }

}