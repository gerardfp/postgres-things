package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("\033[1;92m(~ APP STARTED ~)\033[0m");

        Connection connection = DriverManager.getConnection("jdbc:postgresql://database_container/thingsdb?user=gerard&password=gerard123");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\033[1;94mEnter something (leave blank to finsih):\033[0m");
            String thing = scanner.nextLine();
            if (thing.isEmpty()) {
                break;
            }
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO thing(title) VALUES(?)");
            preparedStatement.setString(1, thing);
            preparedStatement.executeUpdate();
        }

        System.out.println("\033[1;93mYou entered:\033[0m");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM thing");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        statement.close();

        connection.close();
    }
}