package ru.avalon.javapp.devj140.writerjavafx;

import java.sql.*;
import java.util.Enumeration;

public class DbServer implements AutoCloseable {
    private String url;
    private String user;
    private String psw;
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
        try (DbServer dbServer = new DbServer("jdbc:derby://localhost:1527/j140", "j140", "j140")) {
            dbServer.info();
            dbServer.init();
            //dbServer.addAuthor(new Author(6, "Santa Claus3"));
        } catch (SQLException e) {
            System.out.println("Bad connection with database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    @Override
    public void close() throws Exception {

    }
}