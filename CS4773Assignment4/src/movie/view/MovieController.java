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
		director.textProperty().addListener(new directorListener());
		writer.textProperty().addListener(new writerListener());
		releaseYear.textProperty().addListener(new releaseYearListener());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MovieObservableClass) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movieTitle.setText(movie.getMovieTitle());
			director.setText(movie.getDirector());
			writer.setText(movie.getWriter());
			String str = Integer.toString(movie.getReleaseYear());
			releaseYear.setText(str);
			
		};
	}

	private class movieTitleListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setMovieTitle(newValue);
			movieTitle.setText(movie.getMovieTitle());
		}


	}
	
	private class directorListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setDirector(newValue);
			director.setText(movie.getDirector());
		}


	}
	
	private class writerListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setWriter(newValue);
			writer.setText(movie.getWriter());
		}


	}
	
	private class releaseYearListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			if(newValue == ""){
				releaseYear.setText("");
			}else{
				int newInt = Integer.parseInt(newValue);
				movie.setReleaseYear(newInt);
				String str = Integer.toString(movie.getReleaseYear());
				releaseYear.setText(str);
			}
		}


	}
	
}
