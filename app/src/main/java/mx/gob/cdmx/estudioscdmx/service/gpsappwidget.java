package mx.gob.cdmx.estudioscdmx.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

import mx.gob.cdmx.estudioscdmx.R;


public class gpsappwidget extends Activity {
/** Called when the activity is first created. */
	final static String TAG = "gpsappwidget";

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.main);

	File directory;
	File file;
	File sdCard;
	sdCard = Environment.getExternalStorageDirectory();
	FileOutputStream fout = null;
	try {
		directory = new File(sdCard.getAbsolutePath() + "/Mis_archivos");
		directory.mkdirs();
	} catch (Exception e) {
		String stackTrace = Log.getStackTraceString(e);
		Log.i(TAG, "mkdir error" + stackTrace);
	}

}
}
