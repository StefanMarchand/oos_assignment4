package movie.model;

import movie.view.MovieController;

public class Movie {
	private String movieTitle;
	private int releaseYear;
	private String director;
	private String writer;
	private int rating;
	private MovieObservableClass movieDelegateObserver;
	public Movie(String title, int releaseYear, String director, String writer, int rating) {
		this.movieTitle = title;
		this.releaseYear = releaseYear;
		this.director = director;
		this.writer = writer;
		this.rating = rating;
		movieDelegateObserver = new MovieObservableClass();
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
		update();
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
		update();
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
		update();
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
		update();
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
		update();
	}

	public void addObserver(MovieController movieController) {
		movieDelegateObserver.addObserver(movieController);
		
	}
	private void update() {
		movieDelegateObserver.delegateNotifyObservers();}
}
