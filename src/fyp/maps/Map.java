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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class Map extends Activity implements OnMenuItemClickListener {
	private GoogleMap myMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		
		myMap =((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		myMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(54.008901,-2.787566)));
		
		loadRoute();
		
		myMap.addMarker(new MarkerOptions()
		.position(new LatLng(54.004735,-2.784959))
		.title("Hello world"))
		.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tree_icon));

		
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
			startActivity(new Intent(Map.this, ImageCapture.class));
			return true;
		case R.id.action_home:
			startActivity(new Intent(Map.this, MainActivity.class));
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
		case R.id.route_all:
			editor.putInt("route_setting", 0).commit();
			return true;
		case R.id.route_one:
			editor.putInt("route_setting", 1).commit();
			startActivity(new Intent(Map.this, Map2.class));
			return true;
		case R.id.route_two:
			editor.putInt("route_setting", 2).commit();
			startActivity(new Intent(Map.this, Map3.class));
			return true;
		case R.id.route_three:
			editor.putInt("route_setting", 3).commit();
			startActivity(new Intent(Map.this, Map4.class));
			return true;
		
		}
		return false;
	}


	

}

