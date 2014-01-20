package fyp.maps;

import android.app.Application;

public class RouteSetter extends Application{
	int partOfRoute;
	
	public void setRoute(int r){
		this.partOfRoute = r;
	}
	
	public int getRoute(){
		return partOfRoute;
	}
}
