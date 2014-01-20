package fyp.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fyp.camera.ImageCapture;
import fyp.maps.R;
import fyp.quiz.QuizActivity;
import fyp.userinterface.MainActivity;

public class ProfileActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
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
			startActivity(new Intent(ProfileActivity.this, ImageCapture.class));
			return true;
		case R.id.action_home:
			startActivity(new Intent(ProfileActivity.this, MainActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


}
