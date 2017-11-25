package movie.model;

import java.util.Observable;

public class MovieObservableClass extends Observable{

	private Movie movie;
	public MovieObservableClass() {
		movie = MovieSingleton.getInstanceMultiThread();
	}
	
	public void update(Observable o, Object arg) {
		if(o instanceof MovieObservableClass) {
			setChanged();
			notifyObservers();
		}
	}

}
