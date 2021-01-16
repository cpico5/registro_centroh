package mx.gob.cdmx.centroh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thecode.aestheticdialogs.AestheticDialog;
import com.thecode.aestheticdialogs.DialogStyle;
import com.thecode.aestheticdialogs.DialogType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import mx.gob.cdmx.centroh.db.DaoManager;
import mx.gob.cdmx.centroh.model.Entrevista;
import mx.gob.cdmx.centroh.model.Firma;


public class FormatoFirmaActivity extends AppCompatActivity {

    private static final String TAG  = "FormatoFirmaActivity";

    TextView textNombre, continuar;

    ImageView firma;

    SQLiteHelper3 sqLiteHelper3;
    SQLiteDatabase sqLiteDatabase;

    DaoManager daoManager;

    Entrevista entrevista = new Entrevista();
    Firma frima = new Firma();

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df3 = new SimpleDateFormat("yyyMMdd");
    String formattedDate3 = df3.format(c.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato_firma);


        sqLiteHelper3 = new SQLiteHelper3(this);
        sqLiteDatabase = sqLiteHelper3.getWritableDatabase();

        daoManager = new DaoManager(sqLiteDatabase);

        entrevista = (Entrevista) daoManager.findOne(Entrevista.class);

        frima = (Firma) daoManager.findOne(Firma.class);

        if (entrevista != null && frima != null ){
            entrevista.setFirma(frima);

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
        finish();
        startActivity(intent);
    }
}