package com.example.locationservices;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;


public class LocationServices extends Service {
	LocationManager mlm;
    MyLocationListener mlistener=new MyLocationListener();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@Override
	   public int onStartCommand(Intent intent, int flags, int startId) {
	      // Let it continue running until it is stopped.
	      Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_LONG).show();
	      mlm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
	      mlm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, mlistener);  //ξεκινάμε το locationlistener   
	      return START_STICKY;
	   }
	
	   @Override
	   public void onDestroy() {//όταν κλέινει το service  εμφανίζει μήνυμα καθώς το κλείνει
	      super.onDestroy();
	      Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
          mlm.removeUpdates(mlistener);
	   }
	 
	   //κάνω κλάση ώστε να μπορώ να αφαιρώ τον locationlistener στην συνέχεια διαφορετικά συνεχίζει να τρέχει το location service
	class MyLocationListener implements LocationListener{

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			Long tsLong = System.currentTimeMillis()/1000;
			//System.out.println(arg0.getLatitude()+" "+arg0.getLongitude()+" "+tsLong);
			try{
				Uri uri=Uri.parse("content://location_provider/Locations"); 
				ContentValues values=new ContentValues();
				values.put("_LONGITUDE", arg0.getLongitude());
				values.put("_LATITUDE", arg0.getLatitude());
				values.put("_TIMESTAMP", tsLong);
				getApplicationContext().getContentResolver().insert(uri, values);//βάζω τις τιμές στην βάση του provider
				
			}catch(Exception ex){
				
				Toast.makeText(getApplicationContext(), "Could not resolve  the uri content://location_provider/Locations", Toast.LENGTH_LONG).show();//σε περίπτωση λάθους για το οποίο πιθανότατα θα φταίει ο provider δηλαδή κάτι με το uri θα ενημερώνεται ο χρήστης 
				
			}
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
