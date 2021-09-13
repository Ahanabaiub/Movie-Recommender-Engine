public class GenreFilter implements Filter {
    private final String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {

        String genres = MovieDatabase.getGenres(id);

        return genres.contains(genre);
    }
}
