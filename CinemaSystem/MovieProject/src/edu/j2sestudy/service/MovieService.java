package edu.j2sestudy.service;

import edu.j2sestudy.model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MovieService {
    List<Movie> movies = new ArrayList<>();
    {
        movies.add(new Movie(1234463, "Tenet", "科幻", "123", LocalDate.of(2020,9,4), "abc", 0, 0));
        movies.add(new Movie(1234463, "Tenet", "科幻", "123", LocalDate.of(2020,9,4), "abc", 0, 0));
    }


    public void showMovies() {
        System.out.println("   ID\t  电影名称  电影类型  电影主演  电影导演\t  上映时间\t 点击率\t 推荐率");
        for (Movie m : movies) {
            System.out.println(m);
        }
    }
    /**
     * 添加电影
     * @param movie 一部电影
     * @return 添加成功
     */
    public boolean addMovies(Movie movie) {
        movies.add(movie);
        return true;
    }

    /**
     * 根据电影id删除影片
     * @param id 电影id
     * @return 删除成功
     */
    public boolean deleteMovies(int id) {
        return movies.removeIf(m -> m.getId() == id);
    }

    public Movie changeMovies(int id) {
        Movie oldMovie = null;
        Movie newMovie = null;
        for (Movie m : movies) {
            if (m.getId() == id) {
                oldMovie = m;
            }
        }
        deleteMovies(id);
        addMovies(newMovie);
        return oldMovie;
    }
}
