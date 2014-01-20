package fyp.connect;

import java.io.IOException;

import android.util.Log;

import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;

public class Connect extends AbstractGoogleJsonClient  {

	/**
	 * The default encoded root URL of the service. This is determined when the library is generated
	 * and normally should not be changed.
	 *
	 * @since 1.7
	 */
	public static final String DEFAULT_ROOT_URL = "https://basic-advantage-447.appspot.com/_ah/api/";

	/**
	 * The default encoded service path of the service. This is determined when the library is
	 * generated and normally should not be changed.
	 *
	 * @since 1.7
	 */
	public static final String DEFAULT_SERVICE_PATH = "helloworld/v1";

	/**
	 * Constructor.
	 *
	 * <p>
	 * Use {@link Builder} if you need to specify any of the optional parameters.
	 * </p>
	 *
	 * @param transport HTTP transport
	 * @param jsonFactory JSON factory
	 * @param httpRequestInitializer HTTP request initializer or {@code null} for none
	 * @since 1.7
	 */
	public Connect(HttpTransport transport, JsonFactory jsonFactory,
			HttpRequestInitializer httpRequestInitializer) {
		super(transport,
				jsonFactory,
				DEFAULT_ROOT_URL,
				DEFAULT_SERVICE_PATH,
				httpRequestInitializer,
				false);
	}

	/**
	 * @param transport HTTP transport
	 * @param httpRequestInitializer HTTP request initializer or {@code null} for none
	 * @param rootUrl root URL of the service
	 * @param servicePath service path
	 * @param jsonObjectParser JSON object parser
	 * @param googleClientRequestInitializer Google request initializer or {@code null} for none
	 * @param applicationName application name to be sent in the User-Agent header of requests or
	 *        {@code null} for none
	 * @param suppressPatternChecks whether discovery pattern checks should be suppressed on required
	 *        parameters
	 */
	Connect(HttpTransport transport,
			HttpRequestInitializer httpRequestInitializer,
			String rootUrl,
			String servicePath,
			JsonObjectParser jsonObjectParser,
			GoogleClientRequestInitializer googleClientRequestInitializer,
			String applicationName,
			boolean suppressPatternChecks) {
		super(transport,
				httpRequestInitializer,
				rootUrl,
				servicePath,
				jsonObjectParser,
				googleClientRequestInitializer,
				applicationName,
				suppressPatternChecks);
	}

	public static final class Builder extends AbstractGoogleJsonClient.Builder {

		/**
		 * Returns an instance of a new builder.
		 *
		 * @param transport HTTP transport
		 * @param jsonFactory JSON factory
		 * @param httpRequestInitializer HTTP request initializer or {@code null} for none
		 * @since 1.7
		 */
		public Builder(HttpTransport transport, JsonFactory jsonFactory,
				HttpRequestInitializer httpRequestInitializer) {
			super(
					transport,
					jsonFactory,
					DEFAULT_ROOT_URL,
					DEFAULT_SERVICE_PATH,
					httpRequestInitializer,
					false);
		}

		/** Builds a new instance of {@link Tictactoe}. */
		@Override
		public Connect build() {
			return new Connect(getTransport(),
					getHttpRequestInitializer(),
					getRootUrl(),
					getServicePath(),
					getObjectParser(),
					getGoogleClientRequestInitializer(),
					getApplicationName(),
					getSuppressPatternChecks());
		}

		@Override
		public Builder setRootUrl(String rootUrl) {
			return (Builder) super.setRootUrl(rootUrl);
		}

		@Override
		public Builder setServicePath(String servicePath) {
			return (Builder) super.setServicePath(servicePath);
		}

		@Override
		public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
			return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
		}

		@Override
		public Builder setApplicationName(String applicationName) {
			return (Builder) super.setApplicationName(applicationName);
		}

		@Override
		public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
			return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
		}

		/**
		 * Set the {@link ClientRequestInitializer}.
		 *
		 * @since 1.12
		 */
		public Builder setRequestInitializer(
				ClientRequestInitializer clientRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(clientRequestInitializer);
		}

		@Override
		public Builder setGoogleClientRequestInitializer(
				GoogleClientRequestInitializer googleClientRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
		}
	}

	public Message getGreeting() throws java.io.IOException {
		Message message = new Message();
		initialize(message);
		return message;
	}

	public class Message extends ClientRequest<HelloGreeting> {
		private static final String REST_PATH = "hellogreeting/0";

		public Message() {
			super(Connect.this, "GET", REST_PATH, null, HelloGreeting.class);
			try {
				Log.i("x", this.buildHttpRequest().getUrl().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*	public class List extends ClientRequest<String> {

			private static final String REST_PATH = "getGreeting";
			protected List() {
				super(Connect.this, "GET", REST_PATH, null, String.class);
			}
	}*/
	}

}


