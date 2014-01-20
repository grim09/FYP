package fyp.camera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import fyp.maps.R;
import fyp.userinterface.MainActivity;

public class ImageCapture extends Activity{

	int actionCode = 1;
	ImageView mImageView;
	String imageFilePath;
	String mCurrentPhotoPath;

	protected void onCreate(Bundle savedInstanceState){


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {

			File f = createImageFile();

			mCurrentPhotoPath = f.getAbsolutePath();
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

			startActivityForResult(takePictureIntent,actionCode);

		} catch (IOException e) {
			Log.i("F", "MESG");
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			//Bitmap photo = (Bitmap) data.getExtras().get("data");
			//Uri uri = data.getData();
			//	ImageView test = (ImageView) findViewById(R.id.showPicture);

			//test.setImageBitmap(photo);
			//test.setImageURI(uri);
			galleryAddPic();
			AlertDialog alert = generatePictureSavedDialog();
			alert.show();
		}
	}

	private void galleryAddPic() {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

		File f = new File(mCurrentPhotoPath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		this.sendBroadcast(mediaScanIntent);
	}
	
	private AlertDialog generatePictureSavedDialog(){
		AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Image Saved");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(ImageCapture.this, MainActivity.class));
            }
        });
        builder1.setNegativeButton("Take Another",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(ImageCapture.this, ImageCapture.class));
            }
        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
		return alert11;
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
				imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */
				);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		return image;
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
			startActivity(new Intent(ImageCapture.this, ImageCapture.class));
			return true;
		case R.id.action_home:
			startActivity(new Intent(ImageCapture.this, MainActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
