package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT o.ogrno, ad, soyad, cinsiyet, sinif, puan, dtarih\n" +
            "FROM ogrenci o\n" +
            "JOIN islem i ON o.ogrno = i.ogrno;";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT ogrno, ad, soyad, cinsiyet, sinif, puan, dtarih\n" +
            "FROM ogrenci o\n" +
            "WHERE ogrno NOT IN (\n" +
            "    SELECT i.ogrno\n" +
            "    FROM islem i\n" +
            ");";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.islemno)\n" +
            "FROM public.ogrenci o\n" +
            "LEFT JOIN public.islem i ON o.ogrno = i.ogrno\n" +
            "WHERE o.sinif IN ('10A', '10B')\n" +
            "GROUP BY o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(*)\n" +
            "FROM ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT(DISTINCT ad)\n" +
            "FROM ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT ad, COUNT(*)\n" +
            "FROM ogrenci\n" +
            "GROUP BY ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "SELECT sinif, COUNT(*)\n" +
            "FROM ogrenci\n" +
            "GROUP BY sinif;";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT ad, soyad, COUNT(i.islemno) AS kitap_sayisi\n" +
            "FROM public.ogrenci o\n" +
            "LEFT JOIN public.islem i ON o.ogrno = i.ogrno\n" +
            "GROUP BY o.ogrno, o.ad, o.soyad\n" +
            "HAVING COUNT(i.islemno) > 0\n" +
            "ORDER BY kitap_sayisi ASC;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
