package fyp.maps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//import fyp.userinterface.R;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class RouteLoader extends Activity{
	//Method loads the route by reading in a file of waypoints containing a latitude and longitude
	public List loadRoute(){
		List<LatLng> latLongList = new ArrayList<LatLng>();
		
		try{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(getAssets().open("waypoints1.txt")));
			// do reading, usually loop until end of file reading  

			String mLine = null;
			while ((mLine = reader.readLine()) != null) {
				//process line
				if(mLine.startsWith("<coordinates>")){
					String [] bits = mLine.substring(13).split("\\,");
					if(bits.length>=2){
						
						double lng = Double.parseDouble(bits[0]);
						double lat = Double.parseDouble(bits[1]);
						Log.i("HELLO42", " "+lng);
						latLongList.add(new LatLng(lat, lng));
					}
				}
			}

			reader.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		return latLongList;
	}
}