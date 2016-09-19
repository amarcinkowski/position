package com.marcinkowski.position;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import pl.position.R;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class PositionActivity extends Activity implements LocationListener {

	LocationManager lm;
	String provider = LocationManager.GPS_PROVIDER;
	double lat;
	double lon;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1000,
				this);

	}

	@Override
	public void onLocationChanged(Location location) {
		// Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			lat = location.getLatitude();
			lon = location.getLongitude();
			String pos = lat + "_" + lon;
			// ((TextView) findViewById(R.string.hello)).setText("+pos="
			// + String.valueOf(pos));
			HttpClient hc = new DefaultHttpClient();
			HttpGet get = new HttpGet(
					"http://gdziejestiga.waw.pl/siata.php?position=" + pos);
			try {
				HttpResponse rp = hc.execute(get);
				rp.toString();
			} catch (Exception e) {
			}
		} else {
			((TextView) findViewById(R.string.hello))
					.setText("location unavail");
		}

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}