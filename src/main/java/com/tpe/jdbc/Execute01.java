package com.tpe.jdbc;

import java.sql.*;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //!!! 1.ADIM : Driver ekleme
        Class.forName("org.postgresql.Driver");//JDK nin 7.versiyonu ile gerek kalmadi.

        //!!! 2.ADIM : Hangi DB , Hangi kullaniciAdi ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                                                    "techpront",
                                                "password");
        //!!! 3.ADIM : statement nesnesi olusturuluyor
        Statement st = con.createStatement();//query leri olusturabilmek icin olusturuyoruz.
        System.out.println("success");

        //!!! 4.ADIM : sorgu olusturulmasi
        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.
        boolean sql1 = st.execute("CREATE TABLE IF NOT EXISTS workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        System.out.println("sql1 = " + sql1);
          /*
            execute:tüm sorguları çalıştırmak için kullanılır
             sorgunun sonucunda ResultSet alınıyorsa TRUE döndürür, aksi halde FALSE döndürür.
             1-DDL (Data Definition Language) -->FALSE ( Create gibi sorgular )
             2-DQL (Data Query Language) -->TRUE (Select gibi sorgular )
             2-DML (Data Manipulation Language) --> FALSE ( update, insert gibi sorgular )
            genellikle DDL için kullanılır.
         */
        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        st.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");


        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        st.execute("DROP TABLE workers");
        //!!! 5.ADIM : kaynaklarin kapatilmasi
        st.close();
        con.close();

    }
}
