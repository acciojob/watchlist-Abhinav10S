package com.driver;

import java.util.List;
import java.util.Optional;

public class MovieService {

    MovieRepository movieRepository = new MovieRepository() ;

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie) ;
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director) ;
    }

    public void addMovieDirectorPair(String movie, String director) {
       Optional<Movie> movieOpt = movieRepository.getMovie(movie);
       Optional<Director> directorOpt = movieRepository.getDirector(director) ;

       if(movieOpt.isEmpty()){
           throw new MovieNameInvalidException(movie) ;
       }

       if(directorOpt.isEmpty()){
           throw new DirectorNameInvalidException() ;
       }

       Director director1 = directorOpt.get();

       director1.setNumberOfMovies(director1.getNumberOfMovies()+1);
       movieRepository.addDirector(director1);
       movieRepository.addMovieDirectorPair(movie , director);
    }

    public Movie getMovieByName(String movieName) throws MovieNameInvalidException{
        Optional<Movie> movieOpt = movieRepository.getMovie(movieName) ;
        if(movieOpt.isPresent()){
            return movieOpt.get();
        }
        throw new MovieNameInvalidException(movieName) ;

    }

    public Director getDirectorByName(String director) throws DirectorNameInvalidException  {
        Optional<Director> directorOpt = movieRepository.getDirector(director) ;

        if(directorOpt.isPresent()){
            return directorOpt.get() ;
        }

        throw  new DirectorNameInvalidException() ;
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieRepository.getMoviesByDirectorName(director);
    }

    public List<String> getAllMovies() {
        return movieRepository.getAllMovies() ;

    }
    public void deleteMovieByDirectorName(String director) {
        List<String> movies = getMoviesByDirectorName(director);
        movieRepository.deleteDirector(director);

        for (String movie : movies) {
            movieRepository.deleteMovie(movie);
        }
    }

    public void deleteAllDirectors() {
        List<String> directors = movieRepository.getAllDirector() ;

        for (String director : directors){
            movieRepository.deleteAllDirector(director);
        }
    }
}
