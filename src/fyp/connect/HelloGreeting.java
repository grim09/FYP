package fyp.connect;

import com.google.api.client.json.GenericJson;

public class HelloGreeting extends GenericJson {

	  public String message;

	  public HelloGreeting() {};

	  public HelloGreeting(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

	}