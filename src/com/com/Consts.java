package com.com;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

public final class Consts {
   


static public String getSMS(Location location, Context ctx){
			//String provider=location.getProvider();
            Double lat = location.getLatitude();
            Double lon = location.getLongitude();
            String slocation="";
            slocation += "Lantitude:" + String.format("%f",lat);
            slocation += "\nLongitude:" + String.format("%f",lon);
            return slocation;
    }


}
