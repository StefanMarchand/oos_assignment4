package movie.model;


public abstract class MovieSingleton{
	private static final Object Lock = new Object();

	private static volatile Movie movie = null;

	private MovieSingleton() {

	}

	public synchronized static Movie getInstanceMultiThread() {
		if(movie == null) {
			synchronized(Lock) {
				if(movie == null) {
					movie = new Movie("", 0, "", "", 0);
				}
			}
		}
		return movie;
	}
}