package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.gob.cdmx.estudioscdmx.service.SimpleNotification;

public class FormatoActivity extends AppCompatActivity {

    EditText editTextDate, editSuscribe, editCaracter, editInmueble, editNoOficial, editNoInterior,
            editColonia, editAlcaldia, editCP, editCuentaPredial, editTelefono;

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



        editTextDate.setEnabled(false);
        editTextDate.setText(formattedDate3);

    }
}