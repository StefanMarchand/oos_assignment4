package movie.model;

import java.util.Observable;

public class MovieObservableClass extends Observable{

	public void delegateNotifyObservers() {
		this.setChanged();
		this.notifyObservers();
	}
}
