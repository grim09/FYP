package fyp.maps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import fyp.camera.ImageCapture;
import fyp.userinterface.MainActivity;

//import fyp.userinterface.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class Map4 extends Activity implements OnMenuItemClickListener{
	private GoogleMap myMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);


		myMap =((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		myMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(54.006934,-2.782265)));

		loadRoute();

		// Instantiates a new Polyline object and adds points to define a rectangle
		PolylineOptions routeOptions2 = new PolylineOptions()
		.add(new LatLng(54.010665,-2.781906))
		.add(new LatLng(54.010793,-2.782003))  
		.add(new LatLng(54.011026,-2.782065))
		.add(new LatLng(54.011076,-2.782078))  
		.add(new LatLng(54.011138,-2.782116))
		.add(new LatLng(54.011188,-2.782167))
		.add(new LatLng(54.011222,-2.782229))
		.add(new LatLng(54.011247,-2.782283))
		.add(new LatLng(54.011288,-2.782308))
		.add(new LatLng(54.011348,-2.782258))
		.add(new LatLng(54.011409,-2.782286))
		.add(new LatLng(54.011645,-2.782231))
		.add(new LatLng(54.011752,-2.782204))
		.add(new LatLng(54.011774,-2.782203))
		.add(new LatLng(54.012112,-2.782245))
		.add(new LatLng(54.012187,-2.782208))
		.add(new LatLng(54.012266,-2.782236))
		.add(new LatLng(54.012293,-2.782250))
		.add(new LatLng(54.012448,-2.782326))
		.add(new LatLng(54.012604,-2.782347))// Closes the polyline.

		.width(5)
		.color(Color.YELLOW); 

		// Get back the mutable Polyline
		Polyline polyline1 = myMap.addPolyline(routeOptions2);

		myMap.addMarker(new MarkerOptions()
		.position(new LatLng(54.004735,-2.784959))
		.title("Hello world"));


	}

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
						latLongList.add(new LatLng(lat, lng));
					}
				}
			}

			reader.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		PolylineOptions fullRoute = new PolylineOptions();
		for(LatLng latlng: latLongList){
			fullRoute.add(latlng);
		}
		fullRoute.width(5)
		.color(Color.BLUE);

		Polyline routeLine = myMap.addPolyline(fullRoute);
		return latLongList;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_items, menu);
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		//handle presses on the action bar
		switch (item.getItemId()){
		case R.id.action_camera:
			//startActivity(new Intent(MainActivity.this, ImageCapture.class));
			//Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//int actionCode = 1;
			//startActivityForResult(takePictureIntent,actionCode);
			startActivity(new Intent(Map4.this, ImageCapture.class));
			return true;
		case R.id.action_home:
			startActivity(new Intent(Map4.this, MainActivity.class));
			return true;
		case R.id.action_route:
			showPopup();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showPopup(){
		View menuItemView = findViewById(R.id.action_route);
		PopupMenu popup = new PopupMenu(this, menuItemView);
		MenuInflater inflate = popup.getMenuInflater();
		inflate.inflate(R.menu.route_menu, popup.getMenu());
		popup.setOnMenuItemClickListener(this);
		popup.show();
	}

	public boolean onMenuItemClick(MenuItem item) {

		SharedPreferences routePref = getSharedPreferences("AppPrefs", MODE_PRIVATE);
	    SharedPreferences.Editor editor = routePref.edit();
	    
		switch (item.getItemId()) {
		case R.id.route_one:
			editor.putInt("route_setting", 1).commit();
			startActivity(new Intent(Map4.this, Map2.class));
			return true;
		case R.id.route_two:
			editor.putInt("route_setting", 1).commit();
			startActivity(new Intent(Map4.this, Map3.class));
			return true;
		case R.id.route_three:
			editor.putInt("route_setting", 1).commit();
			return true;
		case R.id.route_all:
			editor.putInt("route_setting", 1).commit();
			startActivity(new Intent(Map4.this, Map.class));
			return true;

		}
		return false;
	}

}

