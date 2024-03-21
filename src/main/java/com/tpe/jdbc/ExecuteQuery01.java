package com.tpe.jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullaniciAdi ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "password");
        //!!! 3.ADIM : statement nesnesi olusturuluyor
        Statement st = con.createStatement();//query leri olusturabilmek icin olusturuyoruz.
        System.out.println("success");

        //!!! 4.ADIM : sorgu olusturulmasi

        //!!!   ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String sql1 = "SELECT country_name from countries WHERE id BETWEEN 5 AND 10";
        boolean query1= st.execute(sql1);
        System.out.println("----------------- Örnek 1 --------------------");
        System.out.println("query1 = " + query1);

        //Verileri almak istersem:
       ResultSet rs= st.executeQuery(sql1);

       while (rs.next()){
           System.out.println(" Ülke adi : " + rs.getString("country_name"));
           // System.out.println(" Ulke adi : " + rs.getString(1)); aynisini verir ama ustteki daha kullanisli
       }
        //!!! 5.ADIM : kaynaklarin kapatilmasi
         st.close();
         con.close();
    }
}
