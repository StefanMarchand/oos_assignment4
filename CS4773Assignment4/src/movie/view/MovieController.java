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
		ratingSlider.valueProperty().addListener(new sliderListener());;
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
			ratingText.setText(Integer.toString(movie.getRating()));
			ratingSlider.setValue((double)movie.getRating()); 
		};
	}

	private class sliderListener implements ChangeListener<Number>{

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			int updatedValue = (int) ((double) newValue * 1);
			movie.setRating(updatedValue);
		}
	}

	private class movieTitleListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setMovieTitle(newValue);
		}
	}

	private class directorListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setDirector(newValue);
		}
	}

	private class writerListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movie.setWriter(newValue);
		}
	}

	private class releaseYearListener implements ChangeListener<String>{
		@Override
		public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			int newInt = 0;
			try{
				newInt = Integer.parseInt(newValue);
				movie.setReleaseYear(newInt);
				String str = Integer.toString(movie.getReleaseYear());
				releaseYear.setText(str);
			} catch (NumberFormatException e){
				releaseYear.setText(newValue);
				return;
			}
		}
	}
}


