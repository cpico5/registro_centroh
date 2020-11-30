package mx.gob.cdmx.centroh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mx.gob.cdmx.centroh.db.DaoManager;
import mx.gob.cdmx.centroh.model.Catalogos;
import mx.gob.cdmx.centroh.model.Entrevista;
import mx.gob.cdmx.centroh.model.Usuario;
import mx.gob.cdmx.centroh.service.Utils;

public class FormatoActivity extends AppCompatActivity {

    Usuario usuario = new Usuario();
    Entrevista entrevista = new Entrevista();

    private SQLiteDatabase dbs;
    private UsuariosSQLiteHelper3 usdbhs;


    EditText editTextDate, editSuscribe, editCaracter, editInmueble, editNoOficial, editNoInterior, editCP, editCuentaPredial, editTelefono;

    Button buttonContinuar;

    Spinner spinnerColonia, spinnerAlcaldia;

    int id1,id2;

    Calendar c = Calendar.getInstance();
    SQLiteHelper3 sqLiteHelper3;
    SQLiteDatabase sqLiteDatabase;

    DaoManager daoManager;

    SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate3 = df3.format(c.getTime());

    private static final String TAG  = "FormatoActivity";

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
        spinnerColonia = findViewById(R.id.spinnerColonia);
        spinnerAlcaldia = findViewById(R.id.spinnerAlcaldia);
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
                editTextList.add(editCP);
                editTextList.add(editCuentaPredial);
                editTextList.add(editTelefono);

                List<Spinner> spinnerList = new ArrayList<>();

                spinnerList.add(spinnerAlcaldia);
                spinnerList.add(spinnerColonia);

                if (validations(editTextList, spinnerList)){
                   data();
                };

            }
        });

        editTextDate.setEnabled(false);
        editTextDate.setText(formattedDate3);

        loadSpinners();

    }

    private boolean validations(List<EditText> editTextList, List<Spinner> spinnerList){

        for (EditText editText : editTextList){
            if(Utils.isEmpty(editText.getText().toString())) {

                editText.setError("Este campo es obligatorio");
                editText.requestFocus();
                return false;

            }
            if (editText.getId() == id1){

                if (!Utils.isShort(editText.getText().toString(),5)){
                    editText.setError("Este campo de 5 digitos");
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

        for (Spinner spinner : spinnerList){

            if (spinner.getSelectedItemPosition() == 0){
                ((TextView)spinner.getSelectedView()).setError("Seleccione una opción");
                spinner.requestFocusFromTouch();
                return false;
            }
        }
        return true;
    }

    public void data() {

        try {
            entrevista.setFecha(editTextDate.getText().toString());
            entrevista.setSuscribe(editSuscribe.getText().toString());
            entrevista.setCaracter(editCaracter.getText().toString());
            entrevista.setInmueble(editInmueble.getText().toString());
            entrevista.setNoOficial(editNoOficial.getText().toString());
            entrevista.setNoInterior(editNoInterior.getText().toString());
            entrevista.setCp(Integer.parseInt(editCP.getText().toString()));
            entrevista.setCuentaPredial(editCuentaPredial.getText().toString());
            entrevista.setTelefono(editTelefono.getText().toString());


            sqLiteHelper3 = new SQLiteHelper3(this);
            sqLiteDatabase = sqLiteHelper3.getWritableDatabase();

            daoManager = new DaoManager(sqLiteDatabase);

            daoManager.insert(entrevista);

            Intent intent = new Intent(FormatoActivity.this, FormatoFirmaActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);


        }catch (Exception e){
            new AestheticDialog.Builder(FormatoActivity.this, DialogStyle.RAINBOW, DialogType.ERROR)
                    .setTitle("Error")
                    .setMessage("Existió un error al capturar los datos" + e.getMessage())
                    .show();
        }

    }

    private void loadSpinners(){
        fillAlcaldia("Alcaldía");
    }


    private void fillAlcaldia(final String first){

        usdbhs = new UsuariosSQLiteHelper3(FormatoActivity.this);
        dbs = usdbhs.getWritableDatabase();
        DaoManager daoManager = new DaoManager(dbs);

        List<Catalogos> catalogosList = new ArrayList<>();

        try {

            catalogosList = daoManager.find(Catalogos.class, "catalog=?", new String[]{"municipalities"},null,null,null,null);

            List<String> datos = new ArrayList<String>();
            datos.add(first);

            for (Catalogos catalogosAlcaldia : catalogosList){
                datos.add(catalogosAlcaldia.getName());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAlcaldia.setAdapter(adaptador);
            final List<Catalogos> finalCatalogosList = catalogosList;
            spinnerAlcaldia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(FormatoActivity.this,spinnerAlcaldia.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    if (!spinnerAlcaldia.getSelectedItem().toString().equals(first)){
                        for (Catalogos catalogos: finalCatalogosList){

                            if (spinnerAlcaldia.getSelectedItem().toString() == catalogos.getName()){
                                entrevista.setAlcaldia(catalogos.getId());
                                fillColonia("Colonia",catalogos.getId());
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });

        }catch (SQLException E){

        }

    }

    private void fillColonia(final String first, int Municipality){

        usdbhs = new UsuariosSQLiteHelper3(FormatoActivity.this);
        dbs = usdbhs.getWritableDatabase();
        DaoManager daoManager = new DaoManager(dbs);

        List<Catalogos> catalogosList = new ArrayList<>();

        try {

            catalogosList = daoManager.find(Catalogos.class, "catalog=? AND municipality_id=?", new String[]{"settlements", String.valueOf(Municipality)},null,null,null,null);

            List<String> datos = new ArrayList<String>();
            datos.add(first);

            for (Catalogos catalogosAlcaldia : catalogosList){
                datos.add(catalogosAlcaldia.getName());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerColonia.setAdapter(adaptador);
            final List<Catalogos> finalCatalogosList = catalogosList;
            spinnerColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(FormatoActivity.this,spinnerColonia.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                    if (!spinnerColonia.getSelectedItem().toString().equals(first)){
                        for (Catalogos catalogos: finalCatalogosList){

                            if (spinnerColonia.getSelectedItem().toString().equals(catalogos.getName())){
                                entrevista.setColonia(catalogos.getId());
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });

        }catch (SQLException E){
            System.console().printf("SQLException-------------->", E.getMessage().toString());
        }

    }

}