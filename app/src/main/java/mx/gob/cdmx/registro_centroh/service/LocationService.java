package mx.gob.cdmx.registro_centroh.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class LocationService extends IntentService {

	private String TAG = this.getClass().getSimpleName();

	public LocationService() {
		super("Fused Location");
	}

	public LocationService(String name) {
		super("Fused Location");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		Location location = intent.getParcelableExtra(TAG);
		if (location != null) {
			String CHANNEL_ID = "my_service";
			String CHANNEL_NAME = "My Background Service";
			Log.i(TAG, "onHandleIntent " + location.getLatitude() + ","
					+ location.getLongitude());
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
			.setContentTitle("Sks Location Client")
			.setContentText(location.getLatitude() + ","
					+ location.getLongitude());
			notificationManager.notify(1234, notification.build());

		}

	}

}
