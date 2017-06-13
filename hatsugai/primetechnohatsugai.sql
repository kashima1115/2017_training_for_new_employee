-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017 年 5 朁E29 日 10:02
-- サーバのバージョン： 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `primetechnohatsugai`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `transcation`
--

CREATE TABLE IF NOT EXISTS `transcation` (
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `other_id` int(11) NOT NULL,
  `trans_cash` bigint(20) NOT NULL,
  `trans_flag` char(1) NOT NULL,
  `trans_date` date NOT NULL,
  `inp_date` date NOT NULL,
  PRIMARY KEY (`trans_id`),
  KEY `user_id` (`user_id`),
  KEY `other_id` (`other_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=sjis AUTO_INCREMENT=52 ;

--
-- テーブルのデータのダンプ `transcation`
--

INSERT INTO `transcation` (`trans_id`, `user_id`, `other_id`, `trans_cash`, `trans_flag`, `trans_date`, `inp_date`) VALUES
(2, 1, 2, 50000, '0', '2017-05-23', '2017-05-23'),
(3, 1, 2, 50000, '0', '2017-05-24', '2017-05-24'),
(4, 2, 1, 20000, '0', '2017-05-24', '2017-05-24'),
(5, 4, 1, 12345, '0', '2017-05-24', '2017-05-24'),
(6, 1, 4, 11111, '0', '2017-05-24', '2017-05-24'),
(7, 1, 2, 100, '0', '2017-05-24', '2017-05-24'),
(8, 1, 4, 209, '0', '2017-05-24', '2017-05-24'),
(9, 4, 2, 8000, '1', '2017-06-30', '2017-05-24'),
(10, 1, 4, 5555, '0', '2017-05-25', '2017-05-25'),
(11, 5, 1, 10000, '0', '2017-05-25', '2017-05-25'),
(12, 1, 4, 10000, '0', '2017-05-26', '2017-05-26'),
(17, 1, 2, 1000, '1', '2017-06-11', '2017-05-26'),
(30, 1, 4, 100, '0', '2017-05-29', '2017-05-29'),
(31, 1, 7, 1080, '0', '2017-05-29', '2017-05-29'),
(32, 1, 7, 2160, '0', '2017-05-29', '2017-05-29'),
(33, 1, 7, 4980, '0', '2017-05-29', '2017-05-29'),
(34, 2, 7, 6000, '0', '2017-05-29', '2017-05-29'),
(35, 2, 7, 3980, '0', '2017-05-29', '2017-05-29'),
(36, 2, 7, 1500, '0', '2017-05-29', '2017-05-29'),
(37, 2, 7, 2000, '0', '2017-05-29', '2017-05-29'),
(38, 4, 7, 1000, '0', '2017-05-29', '2017-05-29'),
(39, 4, 7, 2000, '0', '2017-05-29', '2017-05-29'),
(40, 4, 7, 5980, '0', '2017-05-29', '2017-05-29'),
(41, 4, 7, 2800, '0', '2017-05-29', '2017-05-29'),
(42, 5, 7, 108, '0', '2017-05-29', '2017-05-29'),
(43, 5, 7, 108, '0', '2017-05-29', '2017-05-29'),
(44, 5, 7, 108, '0', '2017-05-29', '2017-05-29'),
(45, 5, 7, 108, '0', '2017-05-29', '2017-05-29'),
(46, 5, 7, 108, '0', '2017-05-29', '2017-05-29'),
(47, 6, 7, 100000, '0', '2017-05-29', '2017-05-29'),
(48, 6, 7, 8000, '0', '2017-05-29', '2017-05-29'),
(49, 6, 7, 200000, '0', '2017-05-29', '2017-05-29'),
(50, 7, 5, 540, '0', '2017-05-29', '2017-05-29'),
(51, 7, 6, 308000, '0', '2017-05-29', '2017-05-29');

-- --------------------------------------------------------

--
-- テーブルの構造 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `furigana` varchar(40) NOT NULL,
  `birthday` date NOT NULL,
  `user_ad` varchar(100) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `property` char(1) NOT NULL DEFAULT '0',
  `user_work_nm` varchar(100) DEFAULT NULL,
  `user_work_ad` varchar(100) DEFAULT NULL,
  `user_work_tel` varchar(10) DEFAULT NULL,
  `account_no` varchar(10) NOT NULL,
  `passwd` varchar(15) NOT NULL,
  `passno` varchar(4) NOT NULL,
  `account_balance` bigint(20) NOT NULL,
  `inp_date` date NOT NULL,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `account_no` (`account_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=sjis AUTO_INCREMENT=9 ;

--
-- テーブルのデータのダンプ `user`
--

INSERT INTO `user` (`user_id`, `name`, `furigana`, `birthday`, `user_ad`, `tel`, `mobile`, `mail`, `property`, `user_work_nm`, `user_work_ad`, `user_work_tel`, `account_no`, `passwd`, `passno`, `account_balance`, `inp_date`, `upd_date`) VALUES
(1, '山田一', 'ヤマダハジメ', '1996-06-06', '東京都千代田区秋葉原秋葉原テラス221', '031234567', '09012345678', 'renshu@email.com', '0', '株式会社サンプルクリエイト', '東京都中央区八丁堀1-2-3', '0335551666', '0010000001', 'password', '0000', 101631, '2017-05-22', '2017-05-29 07:10:55'),
(2, '愛植尾', 'アイウエオ', '1998-11-23', '東京都千代田区秋葉原秋葉原テラス222', '031234568', '09012345679', 'aiueo@email.com', '0', '株式会社プライムテクノ', '東京都中央区八丁堀2-33-1', '0335551666', '0010000002', 'password', '0000', 125843, '2017-05-22', '2017-05-29 07:13:19'),
(4, '富良野手九郎', 'フラノテクロウ', '1984-11-11', '東京都新宿区東早稲田早稲田モール1234', '0333332232', '09012333333', 'furano@p-tec.jp', '0', '株式会社プライムテクノ', '東京都中央区八丁堀3-10-3 正和ビル', '0335551666', '0010000003', 'password', '0000', 993686, '2017-05-23', '2017-05-29 07:14:44'),
(5, '佐藤啓太', 'サトウケイタ', '1921-10-02', '東京都中央区朝日町1-2-3', '', '', '', '0', '', '', '', '0010000004', 'password', '0000', 10000, '2017-05-25', '2017-05-29 07:23:29'),
(6, '田中太郎', 'タナカタロウ', '1985-03-04', '福岡県福岡市中央区夕日町1-2-3-44', '0927101234', '09012345678', 'tanaka@email.jp', '0', '田中スチール', '福岡県福岡市中央区夕日町1-2-3-33', '0927100000', '0010000005', 'password', '0000', 0, '2017-05-25', '2017-05-29 07:24:03'),
(7, 'サンプルショッピング', 'サンプルショッピング', '2017-02-01', '東京都中央区銀座1-2-2222', '0334567890', '09012345678', 'sample@email.jp', '1', '', '', '', '0010000006', 'password', '0000', 33480, '2017-05-26', '2017-05-29 07:24:03');

--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `transcation`
--
ALTER TABLE `transcation`
  ADD CONSTRAINT `transcation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `transcation_ibfk_2` FOREIGN KEY (`other_id`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
