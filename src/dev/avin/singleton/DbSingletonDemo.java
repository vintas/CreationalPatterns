package dev.avin.singleton;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingletonDemo {
    public static void main(String[] args) {
        DbSingleton instance = DbSingleton.getInstance();
//        DbSingleton testConst = new DbSingleton();  //does not work because singleton pattern private constructor.

//        System.out.println(instance);
//
//        DbSingleton anotherInstance = DbSingleton.getInstance();
//        System.out.println(anotherInstance);

        long timeBefore = 0;
        long timeAfter = 0;

        System.out.println(instance);
        timeBefore = System.currentTimeMillis();
        Connection conn = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);

        Statement sta;
        try {
            sta = conn.createStatement();
            int count = sta
                    .executeUpdate("CREATE table Address (ID INT, StreetName VARCHAR(20),"
                            + "City VARCHAR(20))");
            System.out.println("Table created");
            sta.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        timeBefore = System.currentTimeMillis();
        conn = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);



    }
}
