-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 30 May 2021, 18:23:54
-- Sunucu sürümü: 8.0.18
-- PHP Sürümü: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `elektronik`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `islemtip`
--

CREATE TABLE `islemtip` (
  `ID` int(11) NOT NULL,
  `ISLEM_AD` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `ID` int(11) NOT NULL,
  `KULLANICI_ADI` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `SIFRE` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `kullanicilar`
--

INSERT INTO `kullanicilar` (`ID`, `KULLANICI_ADI`, `SIFRE`) VALUES
(4, 'mert', 'mert111');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteriler`
--

CREATE TABLE `musteriler` (
  `ID` int(11) NOT NULL,
  `ADSOYAD` varchar(150) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `TELEFON` varchar(20) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `ADRES` varchar(200) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `musteriler`
--

INSERT INTO `musteriler` (`ID`, `ADSOYAD`, `TELEFON`, `ADRES`) VALUES
(2, 'asdasd', 'asdasd', 'asdasd'),
(3, 'asdas', 'asdas', 'asds'),
(4, 'asd', 'asdas', 'asdsa'),
(5, 'asd', 'asd', 'asd'),
(6, '', '', ''),
(7, '', '', ''),
(8, '', '', ''),
(9, '', '', ''),
(10, 'hasan mert öktem', '5388880882', 'kürtül mah'),
(11, 'hasan mert öktem', '5388880882', 'kürtül mah'),
(12, 'hasan', '538888', 'asdasd');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `satislar`
--

CREATE TABLE `satislar` (
  `ID` int(11) NOT NULL,
  `TARIH` date NOT NULL,
  `URUN_AD` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `ADET` int(10) NOT NULL,
  `fiyat` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `satislar`
--

INSERT INTO `satislar` (`ID`, `TARIH`, `URUN_AD`, `ADET`, `fiyat`) VALUES
(1, '2021-05-05', 'Apple', 2, 2),
(2, '2021-05-05', 'Bosch a44', 5, 1000),
(3, '2021-05-04', 'Samsung', 2, 1000),
(4, '2021-05-04', 'Apple', 2, 1000),
(5, '2021-05-06', 'samsung smart tv', 2, 1000);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sepet`
--

CREATE TABLE `sepet` (
  `ID` int(11) NOT NULL,
  `fiyat` int(10) NOT NULL,
  `urunadi` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `adet` int(100) NOT NULL,
  `kategori` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `ID` int(11) NOT NULL,
  `URUNAD` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `URUNGRUP` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `FIYAT` int(15) NOT NULL,
  `ADET` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`ID`, `URUNAD`, `URUNGRUP`, `FIYAT`, `ADET`) VALUES
(1, 'Samsung', 'Telefon', 2000, 3),
(2, 'Apple', 'Bilgisayar', 10000, 1),
(3, 'hp', 'BİLGİSAYAR', 20000, 2),
(4, 'samsung smart tv', 'TV SET ve GÖRÜNTÜ SİSTEMLERİ', 1500, 5),
(5, 'Bosch a44', 'BEYAZ EŞYA', 2000, 5);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ürün_grup`
--

CREATE TABLE `ürün_grup` (
  `ID` int(11) NOT NULL,
  `URUNGRUPAD` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Tablo döküm verisi `ürün_grup`
--

INSERT INTO `ürün_grup` (`ID`, `URUNGRUPAD`) VALUES
(1, 'BİLGİSAYAR'),
(2, 'TELEFON'),
(3, 'TV SET ve GÖRÜNTÜ SİSTEMLERİ'),
(4, 'BEYAZ EŞYA'),
(5, 'ELEKTRİKLİ EV ALETLERİ'),
(6, 'OYUN KONSOLLARI'),
(7, 'AKSESUARLAR');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `islemtip`
--
ALTER TABLE `islemtip`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `musteriler`
--
ALTER TABLE `musteriler`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `satislar`
--
ALTER TABLE `satislar`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `sepet`
--
ALTER TABLE `sepet`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`ID`);

--
-- Tablo için indeksler `ürün_grup`
--
ALTER TABLE `ürün_grup`
  ADD PRIMARY KEY (`ID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `islemtip`
--
ALTER TABLE `islemtip`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `musteriler`
--
ALTER TABLE `musteriler`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `satislar`
--
ALTER TABLE `satislar`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `sepet`
--
ALTER TABLE `sepet`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `ürün_grup`
--
ALTER TABLE `ürün_grup`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
