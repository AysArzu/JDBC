package com.tpe.jdbc;

import java.sql.*;

public class ExecuteUpdate {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullaniciAdi ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "password");
        //!!! 3.ADIM : statement nesnesi olusturuluyor
        Statement st = con.createStatement();//query leri olusturabilmek icin olusturuyoruz.
        System.out.println("success");

        //!!!update islemi oncesi kayitlarin tamami
        System.out.println("------------update oncesi ---------------");
        ResultSet rs = st.executeQuery("SELECT * FROM developers");
        while (rs.next()) {
            System.out.println(rs.getString("name") + " ------ " + rs.getDouble("salary"));
        }
        String sql1 =
            "UPDATE developers SET salary=(SELECT AVG(salary) from developers) WHERE salary<(SELECT AVG(salary) from developers)";
         int updated = st.executeUpdate(sql1);// integer döner kac data degisti onu verir
        System.out.println("Guncellenen kayit sayisi : " + updated);

        //!!!update islemi oncesi kayitlarin tamami
        System.out.println("------------update sonrasi ---------------");
        ResultSet rs2 = st.executeQuery("SELECT * FROM developers");
        while (rs2.next()) {
            System.out.println(rs2.getString("name") + " ------ " + rs2.getDouble("salary"));
        }



        st.close();
        con.close();
    }
}
