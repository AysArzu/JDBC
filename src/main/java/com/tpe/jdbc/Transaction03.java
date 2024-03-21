package com.tpe.jdbc;

import java.sql.*;
import java.sql.PreparedStatement;

public class Transaction03 {


    public static void main(String[] args) throws SQLException {

        //!!! 2.ADIM : Hangi DB , Hangi kullaniciAdi ve sifre
        Connection con =
                DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                        "techpront",
                        "password");

        //!!! 3.ADIM : statement nesnesi olusturuluyor
        Statement st = con.createStatement();

        st.execute("CREATE TABLE IF NOT EXISTS hesaplar3(hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar3 VALUES(?,?,?)";
        java.sql.PreparedStatement prst1 = con.prepareStatement(sql1);

        prst1.setInt(1, 12345);
        prst1.setString(2, "Fatma");
        prst1.setDouble(3, 9000);
        prst1.executeUpdate();

        prst1.setInt(1, 56789);
        prst1.setString(2, "Gokhan");
        prst1.setDouble(3, 6000);
        prst1.executeUpdate();

        // TASK : hesap no : 12345 den hesap no 56789'a 1000 birimlik para transferi gerceklestirelim

        con.setAutoCommit(false);//default degeri true eger true kalirsa otomatik kaydeder.
        /* Transaction yonetimini bu satirdan sonra elimize aldik*/
        String sql2 = "UPDATE hesaplar3 SET bakiye=bakiye+? WHERE hesap_no=?";
        PreparedStatement prst = con.prepareStatement(sql2);

        // 1.ADIM : Fatma hanimin bakiyesini 1000 birim azaltacagim
        prst.setInt(1, -1000);
        prst.setInt(2, 12345);
        prst.executeUpdate();

        // sistemde hata olustu
        if (true) {
            throw new RuntimeException();
        }

        // 2.ADIM : Gokhan beyin bakiyesini 1000 birim artiracagiz
        prst.setInt(1, 1000);
        prst.setInt(2, 56789);
        prst.executeUpdate();

        con.commit();
        st.close();
        con.close();
    }
}
