-- Dram ve Hikaye türündeki kitapları listeleyin. JOIN kullanmadan yapın.
SELECT * FROM kitap WHERE turno = 1
UNION ALL
SELECT * FROM kitap WHERE turno = 4;

-- Kitap alan öğrencilerin öğrenci bilgilerini listeleyin.
SELECT * FROM islem;

SELECT o.ogrno, ad, soyad, cinsiyet, sinif, puan, dtarih
FROM ogrenci o
JOIN islem i ON o.ogrno = i.ogrno;

-- Kitap almayan öğrencileri listeleyin.
SELECT ogrno, ad, soyad, sinif, puan, dtarih
FROM ogrenci o
WHERE ogrno NOT IN (
    SELECT i.ogrno
    FROM islem i
);

-- 10A veya 10B sınıfındaki öğrencilerin sınıf ve okuduğu kitap sayısını getirin.
SELECT o.sinif, COUNT(i.islemno) AS kitap_sayisi
FROM public.ogrenci o
LEFT JOIN public.islem i ON o.ogrno = i.ogrno
WHERE o.sinif IN ('10A', '10B')
GROUP BY o.sinif;

-- Öğrenci tablosundaki öğrenci sayısını gösterin
SELECT COUNT(*) AS ogrenci_sayisi
FROM ogrenci;

-- Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
SELECT COUNT(DISTINCT ad) AS ogrenci_sayisi
FROM ogrenci;

-- İsme göre öğrenci sayılarının adedini bulunuz.
SELECT ad, COUNT(*) AS ogrenci_sayisi
FROM ogrenci
GROUP BY ad;


-- Her sınıftaki öğrenci sayısını bulunuz.
SELECT sinif, COUNT(*)
FROM ogrenci
GROUP BY sinif;

-- Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
SELECT ad, soyad, COUNT(i.islemno) AS kitap_sayisi
FROM public.ogrenci o
LEFT JOIN public.islem i ON o.ogrno = i.ogrno
GROUP BY o.ogrno, o.ad, o.soyad
HAVING COUNT(i.islemno) > 0
ORDER BY kitap_sayisi ASC;

-- Tüm kitapların ortalama puanını bulunuz.
SELECT AVG(puan)
FROM public.kitap;









SELECT * FROM ogrenci;
SELECT * FROM islem;