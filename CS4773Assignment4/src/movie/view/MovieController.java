package movie.view;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
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
	
	private Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
	
	private TextFormatter<Integer> textFormatter = new TextFormatter<Integer>(new IntegerStringConverter(), 0, 
            change -> {
                String newText = change.getControlNewText() ;
                if (validDoubleText.matcher(newText).matches()) {
                    return change ;
                } else return null ;
            });
	
	

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
		ratingSlider.valueProperty().addListener(new sliderListener());
		releaseYear.setTextFormatter(textFormatter);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof MovieObservableClass) {
			Movie movie = MovieSingleton.getInstanceMultiThread();
			movieTitle.setText(movie.getMovieTitle());
			director.setText(movie.getDirector());
			writer.setText(movie.getWriter());
			String str = Integer.toString(movie.getReleaseYear());
			if(str == "" || str == "0"){
				releaseYear.setText("");
			}else{
				releaseYear.setText(str);
			}
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
			//int newInt = 0;
			
			Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
			
			if (validDoubleText.matcher(newValue).matches()){
				//int str = Integer.parseInt(newValue);
				try{
					movie.setReleaseYear(Integer.parseInt(newValue));
				}catch(NumberFormatException e){
					return;
				}
			}else{
				movie.setReleaseYear(0);
			}
		}
	}
	
	
}


