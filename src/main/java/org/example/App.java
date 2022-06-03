package org.example;

import org.example.util.Db;
import peaksoft.City;
import peaksoft.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {

//        addCountry(1, "Кыргызстан");
//        addCountry(2, "Казакстан");
//        addCountry(3, "Таджикстан");
//        addCountry(4, "Россия");
//        addCountry(5, "Узбекстан");
//        addCity(1,"Бишкек");
//        addCity(2,"Ош");
//        addCity(3,"Баткен");
//        addCity(4,"Талас");
//        addCity(5,"Наарын");
//        addPresident(1,"Аскар","Акаев");
//        addPresident(2,"Курбанбек","Бакиев");
//        addPresident(3,"Алмазбек","Атамбаев");
//        addPresident(4,"Сооронбай","Жеенбеков");
//        addPresident(5,"Садыр","Жапаров");
        System.out.println(getCountry());
        System.out.println( getCity());
        geAllPresident();
        geByIdCity(1);

    }

    static public void createTable() {
        try (Connection connection = Db.connection();
             Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS city" +
                    "(id INTEGER PRIMARY KEY," +
                    "name VARCHAR(55) NOT NULL)";
            statement.executeUpdate(SQL);
            System.out.println("Table is successfully created ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCountry(int id, String name) throws SQLException {
        String SQL = "insert into country(id,name) values(?,?)";
        Connection connection = Db.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        System.out.println("Successfully added: " + name);
        connection.close();
    }


    public static void addCity(int id, String name) throws SQLException {
        String SQL = "insert into city(id,name) values(?,?)";
        Connection connection = Db.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
        System.out.println("Successfully added: " + name);
        connection.close();
    }
    public static void addPresident(int id, String last_name,String first_name) throws SQLException {
        String SQL = "insert into president(id,last_name,first_name) values(?,?,?)";
        Connection connection = Db.connection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, last_name);
        preparedStatement.setString(3, first_name);
        preparedStatement.executeUpdate();
        System.out.println("Successfully added: " + last_name);
        connection.close();
    }


    public static List<Country> getCountry(){
        String SQL = "SELECT * FROM country";
        List<Country>countries = new ArrayList<>();
        try (Connection connection = Db.connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setName(resultSet.getString("name"));
                countries.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
    public static List<City>getCity(){
        String SQL = "SELECT * FROM city";
        List<City>cities = new ArrayList<>();
        try (Connection connection = Db.connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                cities.add(city);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    public static void geAllPresident() {
        String SQL = "SELECT * FROM president";
        try (Connection connection = Db.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getString("last_name") + " " +
                        resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void geByIdCity(int id) {
        String SQL = "SELECT * FROM city WHERE id = "+id;
        try (Connection connection = Db.connection();
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(SQL)){
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getString("name"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
