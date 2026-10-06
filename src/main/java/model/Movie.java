package model;

public class Movie {

    private int movieId;
    private String title;
    private String genre;
    private String language;
    private int duration;
    private String releaseDate;
    private double rating;
    private String status; // "NOW_SHOWING" or "UPCOMING"

    public Movie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating)
    {
        this(movieId, title, genre, language, duration, releaseDate, rating, "NOW_SHOWING");
    }

    public Movie(int movieId, String title, String genre, String language, int duration, String releaseDate, double rating, String status)
    {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.status = (status != null && !status.trim().isEmpty()) ? status.toUpperCase() : "NOW_SHOWING";
    }

    public int getMovieId()
    {
        return movieId;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getLanguage()
    {
        return language;
    }

    public int getDuration()
    {
        return duration;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public double getRating()
    {
        return rating;
    }

    public String getStatus()
    {
        return status;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void displayMovie()
    {
        System.out.println("+--------------------------------------------------+");
        System.out.printf("| Movie ID    : %-34d |\n", movieId);
        System.out.printf("| Title       : %-34s |\n", (title.length() > 34 ? title.substring(0, 31) + "..." : title));
        System.out.printf("| Genre       : %-34s |\n", genre);
        System.out.printf("| Language    : %-34s |\n", language);
        System.out.printf("| Duration    : %-34s |\n", duration + " minutes");
        System.out.printf("| Release Date: %-34s |\n", releaseDate);
        System.out.printf("| Rating      : %-34s |\n", String.format("%.1f / 10.0 ⭐", rating));
        System.out.printf("| Status      : %-34s |\n", (status.equalsIgnoreCase("NOW_SHOWING") ? "🎬 Now Showing" : "⏳ Coming Soon"));
        System.out.println("+--------------------------------------------------+");
    }
}