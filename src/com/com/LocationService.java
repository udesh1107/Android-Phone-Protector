package com.com;

import android.app.Service;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.Method;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.util.Log;


public class LocationService extends Service {
	//private NotificationManager mNM;
    LocationManager locationManager;
    
    Location gpsLocation,netLocation;
    
    String gpsMsg,netMsg,notice,gpsValues,netValues;
    Boolean gpsOk,netOk,netDone,gpsDone;
    Timer gpsTimer,netTimer;
    @Override
    public IBinder onBind(Intent arg0) {
            // TODO Auto-generated method stub
            return null;
    }
    
    @Override  
    public void onCreate() {  
            //mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                
    } 
        
       
    public void onStart(final Intent intent, int startId) {
    super.onStart(intent, startId);
    notice="";
    netDone=false;
    gpsDone=false;
    try{
    	gpsOk=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    	netOk=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    catch(Exception e){
    	
    }
    final LocationListener gpsListener=new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			gpsTimer.cancel();
			gpsLocation=location;
			gpsMsg="Location right now by GPS:";
			locationManager.removeUpdates(this);
			
		}
	};  
	
  final LocationListener netListener=new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			netTimer.cancel();
			netLocation=location;
			netMsg="Location right now by NETWORK:";
			locationManager.removeUpdates(this);
			
		}
	};
	
	class gpsTimeOut extends TimerTask{
		public void run(){
			
			locationManager.removeUpdates(gpsListener);
			gpsMsg="GPS taking more time. so last known location::";
			gpsLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			
		}
	}
	class netTimeOut extends TimerTask{
		public void run(){
			
			locationManager.removeUpdates(netListener);
			netMsg="NETWORK taking more time. so last known location::";
			netLocation=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
	}
    
	
    if(!gpsOk){
    	gpsLocation=null;
    	gpsMsg="can't access GPS in that location";
    }
    if(!netOk){
    	netLocation=null;
    	netMsg="can't access NETWORK in that location";
    }
    
    if(gpsOk){
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,gpsListener);
    	gpsTimer=new Timer();
    	gpsTimer.schedule(new gpsTimeOut(), 20000);
    	//gpsMsg="gp";
    }
    if(netOk){
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0,netListener );
    	netTimer=new Timer();
    	netTimer.schedule(new netTimeOut(), 10000);
    }
    
    
    
    
    
    
    
                                
           
            
           if (gpsLocation == null){
        	   gpsValues="";
           }
           if(netLocation==null){
        	   netValues="";
           }
           if(netLocation != null){
        	   netValues=Consts.getSMS(netLocation, this);
           }
           if(gpsLocation != null){
        	   gpsValues=Consts.getSMS(gpsLocation, this);
           }
           if(gpsLocation!=null && netLocation!=null){
        	   if(gpsLocation.getTime()>netLocation.getTime()){
        		   notice="GPS is the most recent reading";
        	   }
        	   else{
        		   notice="NETWORK is the most recent reading";
        	   }
           }
           
           PendingIntent dummyEvent = PendingIntent.getBroadcast(this, 0, new Intent("p1.p2.IGNORE_ME"), 0);
           String dest = intent.getExtras().getString("dest");
           String sms = "your phone found...\n"+gpsMsg+"test"+gpsValues+"\n"+netMsg+netValues+"\n"+notice;
                    SmsManager.getDefault().sendTextMessage(dest, null, sms, dummyEvent, dummyEvent);
           // }
                       
    
    
    
	
	
	
    }
    
	
    

	
	
	
    public void onDestroy(){
            
    }



}
