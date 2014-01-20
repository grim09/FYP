package fyp.quiz;

import fyp.camera.ImageCapture;
import fyp.maps.R;
import fyp.userinterface.MainActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class QuizActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
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
			startActivity(new Intent(QuizActivity.this, ImageCapture.class));
			return true;
		case R.id.action_home:
			startActivity(new Intent(QuizActivity.this, MainActivity.class));
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
		popup.show();
	}

}
