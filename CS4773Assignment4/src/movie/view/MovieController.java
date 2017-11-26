package movie.view;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import movie.model.Movie;
import movie.model.MovieObservableClass;
import movie.model.MovieSingleton;
import javafx.fxml.Initializable;

public class MovieController implements Initializable, Observer {

	private MovieObservableClass movieObserverDelegate; 

	@FXML
	private TextField movieTitle;

	@FXML
	private TextField director;

	@FXML
	private TextField releaseYear;

	@FXML
	private TextField writer;

	@FXML
	private Label ratingText;

	@FXML
	private Slider ratingSlider;

	public MovieController() {
		Movie movie = MovieSingleton.getInstanceMultiThread();
		movie.addObserver(this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		movieTitle.textProperty().addListener(new movieTitleListener());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MovieObservableClass) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movieTitle.setText(movie.getMovieTitle());
			director.setText(movie.getDirector());
		}
	}

	private class movieTitleListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setMovieTitle(newValue);
			movieTitle.setText(movie.getMovieTitle());
		}


	}
}
