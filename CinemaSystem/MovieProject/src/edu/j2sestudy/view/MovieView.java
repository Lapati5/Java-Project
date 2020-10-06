package edu.j2sestudy.view;

import edu.j2sestudy.model.Movie;
import edu.j2sestudy.service.MovieService;
import edu.j2sestudy.util.RandomUtil;
import edu.j2sestudy.util.ScannerUtil;

import java.awt.*;
import java.time.LocalDate;
import java.util.Scanner;

public class MovieView {

    private MovieService ms = new MovieService();

    public void Menu() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1.影片管理\t2.查看所有影片\t3.退出系统");
            System.out.print("请选择序号：");
            char i = ScannerUtil.readMenuSelect(3);
            Choice_01(i);
        }
    }

    /**
     * 一级选择
     * @param i choose
     */
    public void Choice_01(char i) {
        switch (i) {
            case '1':
                //影片管理
                System.out.println("1.上传影片\t2.删除影片\t3.修改影片\t4.返回上一级");
                System.out.print("请选择序号：");
                char c = ScannerUtil.readMenuSelect(4);
                Choice_MM(c);
                break;
            case '2':
                //查看所有影片
                System.out.println("---查看所有影片---");
                ms.showMovies();
                break;
            case '3':
                System.exit(0);
        }
    }

    /**
     * 影片管理
     * @param i choose
     */
    public void Choice_MM(char i) {
        switch (i) {
            case '1':
                System.out.println("==请输入要添加的影片属性==");
                Movie movie = addMovie();
                if (ms.addMovies(movie))
                    System.out.println("==影片上传成功==");
                break;
            case '2':
                    ms.showMovies();
                    System.out.println("请输入所要删除影片的id:");
                    int id = ScannerUtil.readID();
                    if (ms.deleteMovies(id)) {
                        System.out.println("==影片删除成功==");
                        break;
                    } else
                        System.out.println("==未找到该影片id==");
                break;
            case '3':
                break;
            default:
                break;
        }
    }

    public Movie addMovie() {
        System.out.println("--影片名称：");
        String name = ScannerUtil.readString(10);

        System.out.println("--影片类型：");
        String type = ScannerUtil.readString(10);

        System.out.println("--影片主演：");
        String performer = ScannerUtil.readString(10);

        System.out.println("--影片导演：");
        String director = ScannerUtil.readString(10);

        System.out.println("--影片上映时间（例如：2020-09-13）：");
        LocalDate date = ScannerUtil.readDate("2020-09-13");

        int id = RandomUtil.getRandomNum();

        return new Movie(id, name, type, performer, date, director, 0 , 0);
    }
}


