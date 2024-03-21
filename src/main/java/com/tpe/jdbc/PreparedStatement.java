package com.tpe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatement {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "password");

        Statement st = con.createStatement();//query leri olusturabilmek icin olusturuyoruz.

        //!!!   ÖRNEK1: bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        String sql1 = "UPDATE bolumler SET taban_puani=475 WHERE bolum ILIKE 'Matematik'";

        int updated = st.executeUpdate(sql1);
        System.out.println("updated 1 = " + updated);

        //parametreli sorgu yazalim
        //!!!   ÖRNEK2: bolumler tablosunda Matematik bölümünün taban_puanı'nı 499 olarak güncelleyiniz.
        String sql2 = "UPDATE bolumler SET taban_puani=? WHERE bolum ILIKE ?";
        java.sql.PreparedStatement prst = con.prepareStatement(sql2);// connetion uzerinden gidildigi icin buradan cagirdik
        prst.setInt(1, 499);
        prst.setString(2, "Matematik");

        int updated2 = prst.executeUpdate();
        System.out.println("updated 2 = " + updated2);

        //!!!   Örnek3: Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı
        // 477 olarak güncelleyiniz.
        prst.setString(2, "Edebiyat");
        prst.setInt(1, 477);
        int updated3 = prst.executeUpdate();
        System.out.println("updated 3 = " + updated3);

        //!!!   Örnek 4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.
        String sql3 = "INSERT INTO bolumler VALUES(?,?,?,?)";
        java.sql.PreparedStatement prst2 = con.prepareStatement(sql3);
        prst2.setInt(1, 5006);
        prst2.setString(2, "Yazılım Mühendisliği");
        prst2.setInt(3, 475);
        prst2.setString(4, "Merkez");

        int updated4 = prst2.executeUpdate();
        System.out.println("updated 4 = " + updated4);
        st.close();
        con.close();
    }
}
