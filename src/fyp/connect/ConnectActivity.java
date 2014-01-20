package fyp.connect;

import java.io.IOException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import fyp.maps.R;
import com.google.api.client.json.gson.GsonFactory;

public class ConnectActivity extends Activity{
	Connect service;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		Log.i("1", "in connect");

		Connect.Builder builder = new Connect.Builder(
				AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		
		Log.i("2", "in connect");

		service = builder.build();
		Log.i("3", "in connect");

		QueryTask qt = new QueryTask(this);
		Log.i("4", "in connect");
		
		qt.execute();
		
		Log.i("5", "in connect");

	}
	/**
	 * Handles the request to the Score Endpoint, to retrieve scores, without
	 * blocking the UI.
	 */
	private class QueryTask extends AsyncTask<Void, Void, HelloGreeting> {
		Activity activity;

		public QueryTask(Activity activity) {
			this.activity = activity;
		}

		@Override
		protected HelloGreeting doInBackground(Void... unused) {
			Log.i("a", "in connect");

			HelloGreeting response = null;
			try {
				Log.i("b", "in connect");

				response = (HelloGreeting)service.getGreeting().execute();
				
				Log.i("c", "in connect");

			} catch (IOException e) {
				Log.i("c1", "in connect catch");

				e.printStackTrace();
			}

			//Log.i("Message1:", response.getMessage());
			return response;
		}

		protected void onPostExecute(HelloGreeting message) {
			Log.i("d", "in connect");
			
			Log.i("Message:", message.toString());
		}

	}
}