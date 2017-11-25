package movie.view;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import movie.model.Movie;
import movie.model.MovieObservableClass;
import movie.model.MovieSingleton;
import javafx.fxml.Initializable;

public class MovieController implements Initializable, Observer {
	
	private Movie movie;
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
    	
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		movie = MovieSingleton.getInstanceMultiThread();
		movieObserverDelegate = new MovieObservableClass();
	}


}
