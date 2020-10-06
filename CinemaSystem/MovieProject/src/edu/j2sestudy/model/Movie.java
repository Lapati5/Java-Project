package edu.j2sestudy.model;

import java.time.LocalDate;

public class Movie {
    private int id;         //影片id
    private String name;    //影片名字
    private String type;    //类型
    private String performer;   //电影主演
    private LocalDate date; //电影上映时间
    private String director;    //导演
    private int clickRate;  //点击率
    private int recommendRate;  //推荐率

    @Override
    public String toString() {
        return   id + "\t   " + name + " \t" + type + "\t\t" + performer + "\t\t" +
                director + "\t\t" + date + "\t\t" + clickRate + "\t\t" + recommendRate ;
    }

    public Movie(int id, String name, String type, String performer, LocalDate date,
                 String director, int clickRate, int recommendRate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.performer = performer;
        this.date = date;
        this.director = director;
        this.clickRate = clickRate;
        this.recommendRate = recommendRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getClickRate() {
        return clickRate;
    }

    public void setClickRate(int clickRate) {
        this.clickRate = clickRate;
    }

    public int getRecommendRate() {
        return recommendRate;
    }

    public void setRecommendRate(int recommendRate) {
        this.recommendRate = recommendRate;
    }
}
