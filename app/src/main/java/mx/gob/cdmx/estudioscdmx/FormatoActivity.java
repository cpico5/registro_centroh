package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cz.msebera.android.httpclient.util.TextUtils;

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


                validations(editTextList);
            }
        });

        editTextDate.setEnabled(false);
        editTextDate.setText(formattedDate3);

    }

    private void validations(List<EditText> editTextList){

        for (EditText editText : editTextList){
            if(TextUtils.isEmpty(editText.getText().toString())) {
                editText.setError("Este campo es obligatorio");
                editText.requestFocus();
                return;
            }
        }

    }
}