package mx.gob.cdmx.estudioscdmx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.gob.cdmx.estudioscdmx.service.SimpleNotification;

public class FormatoActivity extends AppCompatActivity {

    EditText editTextDate, editSuscribe, editCaracter;

    Calendar c = Calendar.getInstance();

    SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate3 = df3.format(c.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato);


        SimpleNotification simpleNotification = new SimpleNotification();

        simpleNotification.simpleNotification(FormatoActivity.class, FormatoActivity.this, "simple", "simplename","Notification","Message", "Details", 1, false);

        editTextDate = findViewById(R.id.editTextDate);
        editSuscribe = findViewById(R.id.editSuscribe);

        editCaracter = findViewById(R.id.editCaracter);

        editTextDate.setEnabled(false);
        editTextDate.setText(formattedDate3);

    }
}