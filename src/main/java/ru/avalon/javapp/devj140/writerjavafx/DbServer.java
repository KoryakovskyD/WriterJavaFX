package ru.avalon.javapp.devj140.writerjavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DbServer implements AutoCloseable {
    private static String url;
    private static String user;
    private static String psw;
    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;


    public DbServer(String url, String user, String psw) {
        this.url = url;
        this.user = user;
        this.psw = psw;
    }

    private void info() {
        Enumeration<Driver> e = DriverManager.getDrivers();
        Driver drv = null;
        while (e.hasMoreElements()) {
            drv = e.nextElement();
            System.out.println(drv.getClass().getCanonicalName());
        }
    }

    private void init() throws SQLException {
        con = DriverManager.getConnection(url,user,psw);
        if (con == null)
            throw new IllegalArgumentException("Connection is null");
    }

    public static void start() {
        try (DbServer dbServer = new DbServer(url, user, psw)) {
            dbServer.info();
            dbServer.init();
        } catch (SQLException e) {
            System.out.println("Bad connection with database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addCar(Integer num, String model) {

        try (Connection conn = DriverManager.getConnection(url, user, psw)) {
            int maxOrderId = 0;
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("select max (num) from cars")) {
                rs.next();
                maxOrderId = rs.getInt(1);
                // если id меньше максимального, то обновим таблицу, иначе добавим нового пользователя
                if (num <= maxOrderId) {
                    System.out.println("update");

                    try (PreparedStatement pstmt = conn.prepareStatement(
                            "update cars\n" +
                                    "set model = ?\n" +
                                    "where num = ?")) {
                        pstmt.setString(1, model);
                        pstmt.setInt(2, num);
                        pstmt.executeUpdate();
                    }
                    return false;
                } else {
                    System.out.println("add");
                    try (PreparedStatement pstmt = conn.prepareStatement(
                            "insert into cars (num, model)\n" +
                                    "values (?, ?)")) {
                        pstmt.setInt(1, num);
                        pstmt.setString(2, model);
                        pstmt.executeUpdate();
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Car> getCars() {

        try (Connection conn = DriverManager.getConnection(url, user, psw)) {
            try (PreparedStatement pstmt = conn.prepareStatement(
                    "select *\n" +
                            "from cars\n"
            )) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    return createCarArray(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Car> createCarArray(ResultSet rs) throws SQLException {
        List<Car> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Car(rs.getInt(1), rs.getString(2)));
        }
        return (ArrayList<Car>) list;
    }


    public static void main(String[] args) {

    }

    @Override
    public void close() throws Exception {

    }
}