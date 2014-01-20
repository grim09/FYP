package fyp.userinterface;

import java.io.FileOutputStream;

import fyp.camera.ImageCapture;
import fyp.connect.ConnectActivity;
import fyp.maps.Map;
import fyp.maps.Map2;
import fyp.maps.Map3;
import fyp.maps.Map4;
import fyp.maps.R;
import fyp.profile.ProfileActivity;
import fyp.qrscanner.QRActivity;
import fyp.quiz.QuizActivity;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.app.ActionBar;

public class MainActivity extends Activity implements OnClickListener, OnMenuItemClickListener {

	ImageView qr;
	ImageView map;
	ImageView quiz;
	ImageView profile;
	int routeSetting;
	View menuItemView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();
		//actionBar.setDisplayShowTitleEnabled(false);


		//find the imageviews and set click listeners
		qr = (ImageView)findViewById(R.id.qr);
		map = (ImageView)findViewById(R.id.map);
		quiz = (ImageView)findViewById(R.id.quiz);
		profile = (ImageView)findViewById(R.id.profile);

		qr.setOnClickListener(this);
		map.setOnClickListener(this);
		quiz.setOnClickListener(this);
		profile.setOnClickListener(this);

		//create the shared preferences
		SharedPreferences routePref = this.getSharedPreferences("AppPrefs", MODE_PRIVATE); 
	}


	
	//handles clicks to the 4 main imageview buttons
	@Override
	public void onClick(View v) {
		if (v == qr){
			startActivity(new Intent(MainActivity.this, QRActivity.class));
		} else if (v == map){
			SharedPreferences routePref = getSharedPreferences("AppPrefs", MODE_PRIVATE);
			routeSetting = routePref.getInt("route_setting", 0);
			Log.i("route", "" + routeSetting);
			if (routeSetting == 0){
				startActivity(new Intent(MainActivity.this, Map.class));
			} else if (routeSetting == 1){
				startActivity(new Intent(MainActivity.this, Map2.class));
			} else if (routeSetting == 2){
				startActivity(new Intent(MainActivity.this, Map3.class));
			} else if (routeSetting == 3){
				startActivity(new Intent(MainActivity.this, Map4.class));
			}			
			//startActivity(new Intent(MainActivity.this, Map.class));
		} else if (v == quiz){
			Log.i("HERE", "calling start activity");
			startActivity(new Intent(MainActivity.this, ConnectActivity.class));
			Log.i("HERE2", "called");
		} else if(v == profile){
			startActivity(new Intent(MainActivity.this, ProfileActivity.class));
		}
	}

	//creates and displays the popup menu with the route settings
	public void showPopup(){
		View menuItemView = findViewById(R.id.action_route);
		PopupMenu popup = new PopupMenu(this, menuItemView);
		MenuInflater inflate = popup.getMenuInflater();
		inflate.inflate(R.menu.route_menu, popup.getMenu());
		popup.setOnMenuItemClickListener(this);
		popup.show();
	}

	// Inflate the menu items for use in the action bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_items, menu);
		return super.onCreateOptionsMenu(menu);
	}

	//handle presses on the action bar
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.action_camera:
			startActivity(new Intent(MainActivity.this, ImageCapture.class));
			return true;
		case R.id.action_route:
			showPopup();
			return true;		
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	//handles clicks of the popup menu that appears when pressing route on the action bar
	public boolean onMenuItemClick(MenuItem item) {
		
		SharedPreferences routePref = getSharedPreferences("AppPrefs", MODE_PRIVATE);
	    SharedPreferences.Editor editor = routePref.edit();

		switch (item.getItemId()) {
		case R.id.route_one:
			editor.putInt("route_setting", 1).commit();
			Log.i("route",""+routePref.getInt("route_setting", 14));
			return true;
		case R.id.route_two:
			editor.putInt("route_setting", 2).commit();
			Log.i("route", ""+routePref.getInt("route_setting", 14));
			return true;
		case R.id.route_three:
			editor.putInt("route_setting", 3).commit();
			Log.i("route", ""+routePref.getInt("route_setting", 14));
			return true;
		case R.id.route_all:	
			editor.putInt("route_setting", 0).commit();
			Log.i("route", ""+routePref.getInt("route_setting", 14));
			return true;
		}
		return false;
	}
}
