package movie.model;

import java.util.Observable;

public class MovieObservableClass extends Observable{

	public void go() {

		this.setChanged();
		this.notifyObservers();
	
	}

	


}
