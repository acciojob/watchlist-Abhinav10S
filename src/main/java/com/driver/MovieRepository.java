package com.driver;

import java.util.*;

public class MovieRepository {
    Map<String ,Movie> movieMap = new HashMap<>() ;
    Map<String ,Director> directorMap = new HashMap () ;
    Map<String , ArrayList<String>> movieDirectorPair = new HashMap<>() ;
    public void addMovie(Movie movie) {
          movieMap.put(movie.getName() , movie)  ;
    }

    public void addDirector(Director director) {
        directorMap.put(director.getName() , director) ;
    }

    public void addMovieDirectorPair(String movie, String director) {
        ArrayList <String> movies = movieDirectorPair.getOrDefault(director , new ArrayList<>()) ;
        movies.add(movie) ;
        movieDirectorPair.put(director , movies);
    }

//      if (studentData.containsKey(student)) {
//        return Optional.of(studentData.get(student));
//    }
//        return Optional.empty();
    public Optional<Movie> getMovie(String movie) {
        if(movieMap.containsKey(movie)){
            return Optional.of(movieMap.get(movie)) ;
        }
        return Optional.empty() ;
    }

    public Optional<Director> getDirector(String director) {
        if(directorMap.containsKey(director)){
            return Optional.of(directorMap.get(director)) ;
        }
        return Optional.empty() ;
    }

    public List<String> getMoviesByDirectorName(String director) {
        if(movieDirectorPair.containsKey(director)){
            return movieDirectorPair.get(director) ;
        }
        return null ;
    }

    public List<String> getAllMovies() {
        retunr
    }

    public void deleteDirector(String director) {
    }

    public void deleteMovie(String movie) {
    }

    public List<String> getAllDirector() {
    }
}
