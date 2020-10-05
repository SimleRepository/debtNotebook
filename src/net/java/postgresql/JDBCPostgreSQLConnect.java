package net.java.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCPostgreSQLConnect {

    private final String url="jdbc:postgresql://localhost//mydb";
    private final String user="postgresql";
    private final String password="root";

    private void connect(){

        try (Connection connection= DriverManager.getConnection(url,user,password);){

            if(connection!=null){
                System.out.println("It is right");

            }else {
                System.out.println("Not connect");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCPostgreSQLConnect sqlConnect=new JDBCPostgreSQLConnect();
        sqlConnect.connect();
    }
}

