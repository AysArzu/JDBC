package com.tpe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "password");

        Statement st = con.createStatement();


        st.execute("CREATE TABLE IF NOT EXISTS hesaplar2(hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar2 VALUES(?,?,?)";
        PreparedStatement prst1= con.prepareStatement(sql1);

        prst1.setInt(1,1234);
        prst1.setString(2,"Ahmet");
        prst1.setDouble(3,9000);
        prst1.executeUpdate();

        prst1.setInt(1,5678);
        prst1.setString(2,"Emre");
        prst1.setDouble(3,6000);
        prst1.executeUpdate();

        //!!! TASK : hesap no:1234 den hesap no:5678 e 1000 para tranferi yapilsin
        String sql2 = "UPDATE hesaplar2 SET bakiye=bakiye+? WHERE hesap_no=?";
        java.sql.PreparedStatement prst = con.prepareStatement(sql2);
        //1.adim : hesap no 1234 un bakiye guncellenmesi
        prst.setInt(1, -1000);
        prst.setInt(2,1234);
        prst.executeUpdate();


        // 2.adim : hesap no 5678 in bakiyesi guncelleniyor
        prst.setInt(1, 1000);
        prst.setInt(2,5678);
        prst.executeUpdate();

        st.close();
        con.close();



    }
}
