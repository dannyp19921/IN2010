class Movie {
    private String movieId; 
    private String title; 
    private double rating; 

    public Movie(String movieId, String title, double rating) {
        this.movieId = movieId; 
        this.title = title; 
        this.rating = rating; 
    }

    public String getId() {
        return movieId;
    }

    public String getTitle() {
        return title; 
    }

    public double getRating() {
        return rating; 
    }

    @Override
    public String toString() {
        return title + " (" + rating + ")"; 
    }
}
