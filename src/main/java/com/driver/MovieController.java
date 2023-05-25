package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("movies")
public class MovieController {

    private MovieService movieService = new MovieService() ;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie (@RequestBody Movie movie){
        movieService.addMovie(movie) ;
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector (@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED) ;
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair (@RequestParam  String movie , @RequestParam String director){
        try{
            movieService.addMovieDirectorPair(movie,director) ;
            return new ResponseEntity<>("New movie-director pair added successfully",HttpStatus.CREATED) ;
        }
        catch(MovieNameInvalidException exception){
            return new ResponseEntity<>("Movie name is invalid: "+ movie , HttpStatus.NOT_FOUND) ;
        }
        catch (DirectorNameInvalidException exception){
            return new ResponseEntity<>("Director name is invalid: "+director , HttpStatus.NOT_FOUND);
        }
        catch(RuntimeException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @GetMapping ("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName (@PathVariable String movieName){
        try{
            Movie movie = movieService.getMovieByName(movieName);
            return new ResponseEntity<>(movie ,HttpStatus.OK);
        }
        catch(MovieNameInvalidException exception){
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST) ;
        }
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName (@PathVariable String directorName){
        Director director = movieService.getDirectorByName(directorName);
        if (Objects.nonNull(directorName)) {
            return new ResponseEntity<>(director, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{teacher}")
    public ResponseEntity<List<String>> getMoviesByDirectorName (@PathVariable String director){
      List<String> listOfStudentsByTeacherName = movieService.getMoviesByDirectorName(director) ;
      return new ResponseEntity<>( listOfStudentsByTeacherName, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public  ResponseEntity<List<String>> getAllMovies (){
        List<String> getAllMovies = movieService.getAllMovies()  ;
        return new ResponseEntity<>(getAllMovies , HttpStatus.CREATED) ;
    }
    @DeleteMapping("/delete-teacher-by-name")
    public ResponseEntity<String> deleteDirectorByName (@RequestParam String director){
        movieService.deleteMovieByName(director) ;
        return new ResponseEntity<>(director + "removed successfully" , HttpStatus.CREATED) ;
    }

    @DeleteMapping("/delete-all-teachers")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllTeachers();
        return  new ResponseEntity<>("All director delted successfully", HttpStatus.CREATED) ;
    }

}
