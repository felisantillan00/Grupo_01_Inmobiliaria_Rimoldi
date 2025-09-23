-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-11-2024 a las 16:31:41
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rimoldi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comercial`
--

CREATE TABLE `comercial` (
  `idComercial` int NOT NULL,
  `permisos_municipales` varchar(45) NOT NULL,
  `baño` tinyint NOT NULL,
  `cocina` tinyint NOT NULL,
  `vidriera` tinyint NOT NULL,
  `deposito` tinyint NOT NULL,
  `idPropiedad` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `comercial`
--

INSERT INTO `comercial` (`idComercial`, `permisos_municipales`, `baño`, `cocina`, `vidriera`, `deposito`, `idPropiedad`) VALUES
(1, 'Permit A', 1, 1, 1, 1, 26),
(2, 'Permit B', 0, 1, 0, 1, 27),
(3, 'Permit C', 1, 0, 1, 0, 28),
(4, 'Permit D', 1, 1, 0, 1, 29),
(5, 'Permit E', 0, 1, 1, 0, 30),
(6, 'Permit F', 1, 0, 1, 1, 31),
(7, 'Permit G', 0, 1, 0, 1, 32),
(8, 'Permit H', 1, 1, 1, 0, 33),
(9, 'Permit I', 0, 0, 1, 1, 34),
(10, 'Permit J', 1, 1, 0, 0, 35),
(11, 'Permit K', 0, 1, 1, 1, 36),
(12, 'Permit L', 1, 0, 0, 1, 37),
(13, 'Permit M', 0, 1, 1, 0, 38),
(14, 'Permit N', 1, 1, 0, 1, 39),
(15, 'Permit O', 0, 0, 1, 1, 40),
(16, 'Permit P', 1, 1, 1, 0, 41),
(17, 'Permit Q', 0, 1, 0, 1, 42),
(18, 'Permit R', 1, 0, 1, 1, 43),
(19, 'Permit S', 0, 1, 1, 0, 44),
(20, 'Permit T', 1, 1, 0, 1, 45),
(21, 'Permit U', 0, 0, 1, 1, 46),
(22, 'Permit V', 1, 1, 1, 0, 47),
(23, 'Permit W', 0, 1, 0, 1, 48),
(24, 'Permit X', 1, 0, 1, 1, 49),
(25, 'Permit Y', 0, 1, 1, 0, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `nro_contrato` int NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_cancelacion` date DEFAULT NULL,
  `idPropiedad` int NOT NULL,
  `idPersona` int NOT NULL,
  `idMartillero` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `idEstado` int NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`idEstado`, `estado`) VALUES
(1, 'borrador'),
(2, 'en revision'),
(3, 'aprobado'),
(4, 'esperando firmas'),
(5, 'firmado'),
(6, 'activo'),
(7, 'cancelado'),
(8, 'terminado'),
(9, 'en proceso de renovacion'),
(10, 'archivado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadocontrato`
--

CREATE TABLE `estadocontrato` (
  `nro_contrato` int NOT NULL,
  `idEstado` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `familiar`
--

CREATE TABLE `familiar` (
  `idFamiliar` int NOT NULL,
  `cant_ambientes` int NOT NULL,
  `cant_baños` int NOT NULL,
  `cant_autos_cochera` int NOT NULL,
  `piscina` tinyint NOT NULL,
  `permite_mascotas` tinyint NOT NULL,
  `permite_niños` tinyint NOT NULL,
  `idPropiedad` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `familiar`
--

INSERT INTO `familiar` (`idFamiliar`, `cant_ambientes`, `cant_baños`, `cant_autos_cochera`, `piscina`, `permite_mascotas`, `permite_niños`, `idPropiedad`) VALUES
(1, 3, 2, 1, 1, 1, 1, 1),
(2, 4, 3, 2, 0, 0, 1, 2),
(3, 2, 1, 1, 1, 1, 0, 3),
(4, 5, 4, 3, 0, 0, 1, 4),
(5, 3, 2, 1, 1, 1, 0, 5),
(6, 4, 3, 2, 0, 0, 1, 6),
(7, 2, 1, 1, 1, 1, 0, 7),
(8, 5, 4, 3, 0, 0, 1, 8),
(9, 3, 2, 1, 1, 1, 0, 9),
(10, 4, 3, 2, 0, 0, 1, 10),
(11, 2, 1, 1, 1, 1, 0, 11),
(12, 5, 4, 3, 0, 0, 1, 12),
(13, 3, 2, 1, 1, 1, 0, 13),
(14, 4, 3, 2, 0, 0, 1, 14),
(15, 2, 1, 1, 1, 1, 0, 15),
(16, 5, 4, 3, 0, 0, 1, 16),
(17, 3, 2, 1, 1, 1, 0, 17),
(18, 4, 3, 2, 0, 0, 1, 18),
(19, 2, 1, 1, 1, 1, 0, 19),
(20, 5, 4, 3, 0, 0, 1, 20),
(21, 3, 2, 1, 1, 1, 0, 21),
(22, 4, 3, 2, 0, 0, 1, 22),
(23, 2, 1, 1, 1, 1, 0, 23),
(24, 5, 4, 3, 0, 0, 1, 24),
(25, 3, 2, 1, 1, 1, 0, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `firma`
--

CREATE TABLE `firma` (
  `nro_contrato` int NOT NULL,
  `idGarante` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `garante`
--

CREATE TABLE `garante` (
  `idGarante` int NOT NULL,
  `ingresos` int NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `contacto_trabaja` varchar(45) NOT NULL,
  `idPersona` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `garante`
--

INSERT INTO `garante` (`idGarante`, `ingresos`, `empresa_trabaja`, `contacto_trabaja`, `idPersona`) VALUES
(1, 150000, 'Tecro', '5492302555398', 11),
(2, 160000, 'Google', '(650) 253-0000', 12),
(3, 170000, 'Microsoft', '+34 917 547 010', 13),
(4, 180000, 'Amazon', 'contact4', 14),
(5, 190000, 'Mercado Libre', '(54 11) 4640-8000', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen_prop`
--

CREATE TABLE `imagen_prop` (
  `idPropiedad` int NOT NULL,
  `URL` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `imagen_prop`
--

INSERT INTO `imagen_prop` (`idPropiedad`, `URL`) VALUES
(1, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(2, 'https://images.unsplash.com/photo-1560185009-dddeb820c7b7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(3, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(4, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(5, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(6, 'https://plus.unsplash.com/premium_photo-1673014202349-38687dd47f94?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(7, 'https://plus.unsplash.com/premium_photo-1673014201324-2eccc35d8387?q=80&w=1489&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(8, 'https://images.unsplash.com/photo-1649769425621-e4a3af8e7c97?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(9, 'https://images.unsplash.com/photo-1680703486830-1b5af60635d7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(10, 'https://plus.unsplash.com/premium_photo-1661963198655-a671a1f06d25?q=80&w=1631&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(11, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(12, 'https://images.unsplash.com/photo-1560185009-dddeb820c7b7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(13, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(14, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(15, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(16, 'https://plus.unsplash.com/premium_photo-1673014202349-38687dd47f94?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(17, 'https://plus.unsplash.com/premium_photo-1673014201324-2eccc35d8387?q=80&w=1489&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(18, 'https://images.unsplash.com/photo-1649769425621-e4a3af8e7c97?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(19, 'https://images.unsplash.com/photo-1680703486830-1b5af60635d7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(20, 'https://plus.unsplash.com/premium_photo-1661963198655-a671a1f06d25?q=80&w=1631&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(21, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(22, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(23, 'https://images.unsplash.com/photo-1560185009-dddeb820c7b7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(24, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(25, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(26, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(27, 'https://plus.unsplash.com/premium_photo-1673014202349-38687dd47f94?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(28, 'https://plus.unsplash.com/premium_photo-1673014201324-2eccc35d8387?q=80&w=1489&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(29, 'https://images.unsplash.com/photo-1649769425621-e4a3af8e7c97?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(30, 'https://images.unsplash.com/photo-1680703486830-1b5af60635d7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(31, 'https://plus.unsplash.com/premium_photo-1661963198655-a671a1f06d25?q=80&w=1631&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(32, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(33, 'https://images.unsplash.com/photo-1560185009-dddeb820c7b7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(34, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(35, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(36, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(37, 'https://plus.unsplash.com/premium_photo-1673014202349-38687dd47f94?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(38, 'https://plus.unsplash.com/premium_photo-1673014201324-2eccc35d8387?q=80&w=1489&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(39, 'https://images.unsplash.com/photo-1649769425621-e4a3af8e7c97?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(40, 'https://images.unsplash.com/photo-1680703486830-1b5af60635d7?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(41, 'https://plus.unsplash.com/premium_photo-1661963198655-a671a1f06d25?q=80&w=1631&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(42, 'https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(43, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(44, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(45, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(46, 'https://plus.unsplash.com/premium_photo-1673014202349-38687dd47f94?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(47, 'https://plus.unsplash.com/premium_photo-1673014201324-2eccc35d8387?q=80&w=1489&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(48, 'https://images.unsplash.com/photo-1560185007-c5ca9d2c014d?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(49, 'https://images.unsplash.com/photo-1560184897-0e5d96d86acd?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
(50, 'https://images.unsplash.com/photo-1697462247996-51fdc8a1fe0a?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inquilino`
--

CREATE TABLE `inquilino` (
  `idInquilino` int NOT NULL,
  `mascotas` tinyint NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `cantidad_integrantes` varchar(45) NOT NULL,
  `ingresos` int NOT NULL,
  `idPersona` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `inquilino`
--

INSERT INTO `inquilino` (`idInquilino`, `mascotas`, `empresa_trabaja`, `cantidad_integrantes`, `ingresos`, `idPersona`) VALUES
(1, 1, 'Microsoft', '3', 50000, 1),
(2, 0, 'Apple', '2', 60000, 2),
(3, 1, 'Nvidia', '4', 70000, 3),
(4, 0, 'AMD', '1', 80000, 4),
(5, 1, 'Goole', '5', 90000, 5),
(6, 0, 'Facebook', '3', 100000, 6),
(7, 1, 'Mercado Libre', '2', 110000, 7),
(8, 0, 'Tesla', '4', 120000, 8),
(9, 1, 'Tecro', '1', 130000, 9),
(10, 0, 'Oracle', '5', 140000, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `martillero`
--

CREATE TABLE `martillero` (
  `idMartillero` int NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `nro_matricula` int NOT NULL,
  `idPersona` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `martillero`
--

INSERT INTO `martillero` (`idMartillero`, `contraseña`, `nro_matricula`, `idPersona`) VALUES
(1, 'password1', 1001, 20),
(2, 'password2', 1002, 21),
(3, 'password3', 1003, 22),
(4, 'password4', 1004, 23),
(5, 'password5', 1005, 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `dni` bigint NOT NULL,
  `email` varchar(45) NOT NULL,
  `celular` bigint NOT NULL,
  `fecha_nac` date NOT NULL,
  `username` varchar(45) NOT NULL,
  `cuil` bigint NOT NULL,
  `token` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`),  -- Definición de la clave primaria aquí
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `celular_UNIQUE` (`celular`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `dni`, `email`, `celular`, `fecha_nac`, `username`, `cuil`, `token`, `password`) VALUES
(1, 'John Doe', 12345678, 'john.doe@example.com', 1234567890, '1980-01-01', 'johndoe', 20123456789, '', ''),
(2, 'Jane Smith', 23456789, 'jane.smith@example.com', 2345678901, '1985-02-02', 'janesmith', 20234567890, '', ''),
(3, 'Alice Johnson', 34567890, 'alice.johnson@example.com', 3456789012, '1990-03-03', 'alicejohnson', 20345678901, '', ''),
(4, 'Bob Brown', 45678901, 'bob.brown@example.com', 4567890123, '1995-04-04', 'bobbrown', 20456789012, '', ''),
(5, 'Charlie Davis', 56789012, 'charlie.davis@example.com', 5678901234, '2000-05-05', 'charliedavis', 20567890123, '', ''),
(6, 'David Evans', 67890123, 'david.evans@example.com', 6789012345, '1981-06-06', 'davidevans', 20678901234, '', ''),
(7, 'Eva Green', 78901234, 'eva.green@example.com', 7890123456, '1986-07-07', 'evagreen', 20789012345, '', ''),
(8, 'Frank Harris', 89012345, 'frank.harris@example.com', 8901234567, '1991-08-08', 'frankharris', 20890123456, '', ''),
(9, 'Grace Lee', 90123456, 'grace.lee@example.com', 9012345678, '1996-09-09', 'gracelee', 20901234567, '', ''),
(10, 'Henry Martin', 12345679, 'henry.martin@example.com', 1234567891, '1982-10-10', 'henrymartin', 20123456790, '', ''),
(11, 'Ivy Nelson', 23456780, 'ivy.nelson@example.com', 2345678902, '1987-11-11', 'ivynelson', 20234567891, '', ''),
(12, 'Jack Owens', 34567891, 'jack.owens@example.com', 3456789013, '1992-12-12', 'jackowens', 20345678902, '', ''),
(13, 'Karen Perez', 45678902, 'karen.perez@example.com', 4567890124, '1997-01-13', 'karenperez', 20456789013, '', ''),
(14, 'Leo Quinn', 56789013, 'leo.quinn@example.com', 5678901235, '1983-02-14', 'leoquinn', 20567890124, '', ''),
(15, 'Mia Roberts', 67890124, 'mia.roberts@example.com', 6789012346, '1988-03-15', 'miaroberts', 20678901235, '', ''),
(16, 'Nina Scott', 78901235, 'nina.scott@example.com', 7890123457, '1993-04-16', 'ninascott', 20789012346, '', ''),
(17, 'Oscar Turner', 89012346, 'oscar.turner@example.com', 8901234568, '1998-05-17', 'oscarturner', 20890123457, '', ''),
(18, 'Paul Walker', 90123457, 'paul.walker@example.com', 9012345679, '1984-06-18', 'paulwalker', 20901234568, '', ''),
(19, 'Quinn Young', 12345680, 'quinn.young@example.com', 1234567892, '1989-07-19', 'quinnyoung', 20123456791, '', ''),
(20, 'Rachel Adams', 23456781, 'rachel.adams@example.com', 2345678903, '1994-08-20', 'racheladams', 20234567892, '', ''),
(21, 'Sam Baker', 34567892, 'sam.baker@example.com', 3456789014, '1999-09-21', 'sambaker', 20345678903, '', ''),
(22, 'Tina Clark', 45678903, 'tina.clark@example.com', 4567890125, '1985-10-22', 'tinaclark', 20456789014, '', ''),
(23, 'Uma Davis', 56789014, 'uma.davis@example.com', 5678901236, '1990-11-23', 'umadavis', 20567890125, '', ''),
(24, 'Victor Evans', 67890125, 'victor.evans@example.com', 6789012347, '1995-12-24', 'victorevans', 20678901236, '', ''),
(25, 'Wendy Foster', 78901236, 'wendy.foster@example.com', 7890123458, '2000-01-25', 'wendyfoster', 20789012347, '', ''),
(26, 'Felipe Santillan', 42234730, 'felipon@gmail.com', 2302628823, '2000-01-10', 'felipon', 20422347307, 'eyJhbGciOiJSUzI1NiIsImtpZCI6ImU2YWMzNTcyNzY3ZGUyNjE0ZmM1MTA4NjMzMDg3YTQ5MjMzMDNkM2IiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vYXBwcmVhY3RuYXRpdmUtZGNmYzEiLCJhdWQiOiJhcHByZWFjdG5hdGl2ZS1kY2ZjMSIsImF1dGhfdGltZSI6MTczMDU2NDcwNSwidXNlcl9pZCI6Ik5ydG9FOWdCMWhmRDg4ZFZuajRibDk4QXFDMTIiLCJzdWIiOiJOcnRvRTlnQjFoZkQ4OGRWbmo0Ymw5OEFxQzEyIiwiaWF0IjoxNzMwNTY0NzA1LCJleHAiOjE3MzA1NjgzMDUsImVtYWlsIjoiZmVsaXBvbkBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiZmVsaXBvbkBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.AlX81ILt-N2XAc98C7v48CudhgRPrj5GBfSHEwscRldxvDu5OB1Pz2uBwDf5LC4wgiLmxLC3Va6wa65062FqiA5I95G4XUnyuuETT3h7VH8EDlC935jKZeadDaumYlkB2enqy_k3fULOVYxeWtwhiku2nsPNzUjKmOHuFIVrWOYXgNwQLnf1U7JQQgTKnWbKFxtvmeKCGvKcMEd7En7DbENlTjTfGzuR6q-79Kz-n_Jf8paP_wAHwCScO-CtP3ni_XVj6bAjbPOVThw_EN7WUGq3nx9vjpV_kqFVHoPXShpTzaUonmJKz3Xti3AMSmMd2269_1-nH7mbgJqe-FlI2g', 'felipon1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propiedad`
--

CREATE TABLE `propiedad` (
  `idPropiedad` int NOT NULL,
  `direccion` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `alquiler` double NOT NULL,
  `m2_cubiertos` int NOT NULL,
  `m2_descubiertos` int NOT NULL,
  `condiciones_garantes` varchar(45) NOT NULL,
  `expensas` double NOT NULL,
  `gastos` double NOT NULL,
  `fecha_precio_minimo` date NOT NULL,
  `cuidad` varchar(45) NOT NULL,
  `idPropietario` int NOT NULL,
  `enVenta` tinyint(1) NOT NULL,
  `enAlquiler` tinyint(1) NOT NULL,
  `precio` int NOT NULL,
  `descripcion` text NOT NULL,
  `titulo` text NOT NULL,
  `latitud` decimal(9,6) DEFAULT NULL,
  `longitud` decimal(9,6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `propiedad`
--

INSERT INTO `propiedad` (`idPropiedad`, `direccion`, `alquiler`, `m2_cubiertos`, `m2_descubiertos`, `condiciones_garantes`, `expensas`, `gastos`, `fecha_precio_minimo`, `cuidad`, `idPropietario`, `enVenta`, `enAlquiler`, `precio`, `descripcion`, `titulo`, `latitud`, `longitud`) VALUES
(1, 'Av. Corrientes 1234, CABA, Buenos Aires', 1200, 100, 50, 'Good credit', 200, 100, '2023-01-01', 'City A', 1, 1, 0, 80816516, 'Hermosa casa familiar en una tranquila zona residencial, ideal para quienes buscan un hogar acogedor. Cuenta con amplias habitaciones, un jardín privado y espacio suficiente para disfrutar de actividades al aire libre con la familia. La cocina es moderna y funcional, perfecta para preparar deliciosas comidas. Además, está cerca de escuelas y parques, lo que la convierte en un lugar ideal para criar niños. Su cercanía a tiendas y servicios la hace aún más atractiva para aquellos que valoran la conveniencia y el confort en su día a día.', 'Propiedad Familiar', -34.603700, -58.381600),
(2, 'Calle San Martín 4567, General Pico, La Pampa', 1300, 110, 60, 'No pets', 210, 110, '2023-02-01', 'City B', 2, 1, 0, 155674, 'Casa de dos pisos ubicada en un vecindario amigable y seguro. Con una distribución que favorece la convivencia, esta propiedad ofrece un espacio perfecto para disfrutar en familia. El salón es luminoso y acogedor, ideal para pasar tiempo juntos o recibir visitas. Además, la casa cuenta con un garaje amplio y un jardín que invita a disfrutar del aire libre. La proximidad a centros educativos y áreas recreativas hace que esta propiedad sea especialmente atractiva para familias con niños pequeños.', 'Propiedad Familiar', -35.659800, -63.757600),
(3, 'Calle 25 de Mayo 101, Córdoba, Córdoba', 1400, 120, 70, 'Stable job', 220, 120, '2023-03-01', 'City C', 3, 0, 1, 277331, 'Amplia casa con un diseño contemporáneo y acabados de alta calidad. La propiedad ofrece varias habitaciones, lo que permite que cada miembro de la familia tenga su propio espacio. El jardín es perfecto para reuniones familiares o simplemente relajarse después de un largo día. También hay una terraza donde se pueden disfrutar de las noches cálidas. Situada en una comunidad tranquila, esta casa está cerca de supermercados, restaurantes y otros servicios, lo que facilita la vida diaria de cualquier familia.', 'Propiedad Familiar', -31.416700, -64.183300),
(4, 'Av. 9 de Julio 4321, CABA, Buenos Aires', 1500, 130, 80, 'No smoking', 230, 130, '2023-04-01', 'City D', 4, 1, 0, 43311195, 'Encantadora casa en una zona muy solicitada, ideal para aquellos que buscan un lugar donde criar a sus hijos. Dispone de un gran espacio exterior donde se puede disfrutar de actividades al aire libre, así como de un interior luminoso y bien distribuido. La propiedad incluye una cocina equipada y un cómodo salón. Además, está a solo unos minutos de las mejores escuelas del área, así como de centros de salud y recreación, asegurando que todas las necesidades de la familia estén a su alcance.', 'Propiedad Familiar', -34.607900, -58.373200),
(5, 'Calle San Luis 87, Mendoza, Mendoza', 1600, 140, 90, 'Good references', 240, 140, '2023-05-01', 'City E', 1, 1, 0, 22998162, 'Casa familiar de estilo rústico, rodeada de naturaleza, que proporciona un ambiente relajante y apacible. Esta propiedad es perfecta para quienes desean escapar del bullicio de la ciudad y disfrutar de un estilo de vida más tranquilo. Las amplias habitaciones permiten personalizar cada espacio según las necesidades familiares, y el amplio terreno ofrece oportunidades para jardinería y actividades al aire libre. Además, la casa está bien situada, permitiendo fácil acceso a rutas de senderismo y áreas de recreo para disfrutar en familia.', 'Propiedad Familiar', -31.420100, -64.188800),
(6, 'Calle Libertad 1000, Rosario, Santa Fe', 1700, 150, 100, 'No pets', 250, 150, '2023-06-01', 'City F', 2, 0, 1, 60450829, 'Propiedad familiar con diseño arquitectónico moderno, ubicada en un barrio en crecimiento. Este hogar cuenta con una distribución abierta, ideal para disfrutar de la convivencia familiar. El salón es amplio y luminoso, mientras que la cocina cuenta con electrodomésticos de última generación. Las habitaciones están diseñadas para ser acogedoras y funcionales. Con acceso cercano a escuelas, supermercados y transporte público, esta casa es perfecta para familias que buscan comodidad y un estilo de vida activo.', 'Propiedad Familiar', -34.928700, -57.953600),
(7, 'Calle Mitre 2222, Bahía Blanca, Buenos Aires', 1800, 160, 110, 'Good credit', 260, 160, '2023-07-01', 'City G', 3, 1, 0, 35116119, 'Casa familiar situada en una urbanización cerrada que ofrece seguridad y tranquilidad. La propiedad incluye un hermoso jardín que es ideal para que los niños jueguen y para disfrutar de reuniones familiares. Las habitaciones son amplias y están bien iluminadas, proporcionando un ambiente acogedor. La cocina está diseñada para ser funcional y práctica, perfecta para cocinar en familia. La cercanía a centros comerciales y servicios de salud es un gran atractivo para quienes buscan comodidad y acceso a todo lo necesario.', 'Propiedad Familiar', -37.956400, -57.558400),
(8, 'Av. Libertador 4444, CABA, Buenos Aires', 1900, 170, 120, 'Stable job', 270, 170, '2023-08-01', 'City H', 4, 1, 0, 57117286, 'Amplia propiedad que combina elegancia y funcionalidad. Esta casa cuenta con múltiples habitaciones, un gran salón y un espacio exterior ideal para disfrutar de la naturaleza. La cocina está equipada con lo último en tecnología, facilitando la preparación de comidas en familia. Está ubicada en una zona tranquila, ideal para quienes buscan alejarse del estrés urbano. Además, su proximidad a parques y actividades recreativas la convierte en una opción ideal para familias con niños que desean un entorno seguro y amigable.', 'Propiedad Familiar', -38.416100, -62.067200),
(9, 'Calle Catamarca 789, Tucumán, Tucumán', 2000, 180, 130, 'No smoking', 280, 180, '2023-09-01', 'City I', 1, 1, 0, 36247826, 'Casa acogedora en un barrio familiar, perfecta para quienes buscan un hogar donde crecer y crear recuerdos. Dispone de un amplio jardín, ideal para jugar y disfrutar del aire libre. Las habitaciones son cómodas y el salón está diseñado para fomentar la convivencia. La propiedad está cerca de escuelas, parques y comercios, ofreciendo a las familias la conveniencia de tener todo lo necesario a un paso. Esta casa es una inversión en calidad de vida y bienestar familiar.', 'Propiedad Familiar', -34.895100, -57.955600),
(10, 'Calle Independencia 12345, San Juan, San Juan', 2100, 190, 140, 'Good references', 290, 190, '2023-10-01', 'City J', 2, 1, 0, 67659456, 'Propiedad familiar de diseño moderno, con acabados de alta calidad y una distribución funcional. Cada habitación ha sido pensada para ofrecer comodidad y calidez, haciendo de este hogar un lugar ideal para la vida diaria. El espacio exterior permite disfrutar de actividades al aire libre y está diseñado para aprovechar al máximo el sol. Además, su ubicación estratégica cerca de escuelas y centros comerciales la hace atractiva para familias que valoran el acceso a servicios.', 'Propiedad Familiar', -31.540100, -64.324300),
(11, 'Calle Rivadavia 50, La Plata, Buenos Aires', 2200, 200, 150, 'No pets', 300, 200, '2023-11-01', 'City K', 3, 1, 0, 253133, 'Casa familiar con encanto y carácter, que ofrece un ambiente cálido y acogedor. Dispone de varias habitaciones y espacios abiertos que fomentan la interacción familiar. El jardín es ideal para disfrutar de días soleados y organizar reuniones. Ubicada en una comunidad con excelentes servicios, esta propiedad es perfecta para aquellos que buscan un hogar donde criar a sus hijos en un entorno seguro y agradable. Cada rincón de esta casa invita a crear recuerdos inolvidables en familia.', 'Propiedad Familiar', -34.608300, -58.381400),
(12, 'Av. de Mayo 1000, CABA, Buenos Aires', 2300, 210, 160, 'Good credit', 310, 210, '2023-12-01', 'City L', 4, 1, 0, 38423154, 'Encantadora casa de un solo piso, ideal para familias que buscan comodidad y accesibilidad. Esta propiedad cuenta con un jardín bien cuidado y espacios internos que son perfectos para la convivencia. La cocina es amplia y está diseñada para facilitar la preparación de comidas familiares. Además, la ubicación de la casa ofrece fácil acceso a escuelas y parques, lo que la convierte en una excelente opción para quienes desean un hogar donde sus hijos puedan crecer y desarrollarse en un ambiente seguro.', 'Propiedad Familiar', -27.482300, -58.096800),
(13, 'Calle Moreno 350, Neuquén, Neuquén', 2400, 220, 170, 'Stable job', 320, 220, '2024-01-01', 'City M', 1, 0, 1, 24087929, 'Espaciosa propiedad familiar que combina estilo y comodidad. Cuenta con un gran salón, ideal para reuniones familiares, y un comedor donde se pueden disfrutar de deliciosas comidas en compañía. Las habitaciones son luminosas y están diseñadas para proporcionar tranquilidad y confort. El jardín ofrece un espacio perfecto para jugar y disfrutar del aire libre. La propiedad está situada en una zona con excelentes servicios y buena comunicación, lo que facilita el día a día de cualquier familia.', 'Propiedad Familiar', -31.731900, -64.990700),
(14, 'Calle Juan B. Justo 1500, San Fernando, Buenos Aires', 2500, 230, 180, 'No smoking', 330, 230, '2024-02-01', 'City N', 2, 0, 1, 78263, 'Casa familiar de estilo contemporáneo, que ofrece un ambiente cálido y acogedor. Cada habitación ha sido diseñada para maximizar la luz natural y la funcionalidad. El jardín es un lugar ideal para disfrutar de momentos al aire libre y cuenta con suficiente espacio para actividades recreativas. La propiedad está ubicada cerca de escuelas y centros comerciales, lo que la hace muy conveniente para familias. Este hogar es perfecto para quienes buscan una vida cómoda y tranquila, rodeados de todos los servicios.', 'Propiedad Familiar', -34.598400, -58.381900),
(15, 'Calle Salta 1300, Salta, Salta', 2600, 240, 190, 'Good references', 340, 240, '2024-03-01', 'City O', 3, 1, 0, 28366146, 'Magnífica casa familiar, donde cada rincón está diseñado para brindar comodidad y estilo. El amplio salón es perfecto para reuniones familiares y ofrece un ambiente acogedor. La cocina, equipada con electrodomésticos de alta gama, facilita la preparación de comidas. Las habitaciones son cómodas y tienen suficiente espacio de almacenamiento. La ubicación de la casa, cerca de parques y áreas recreativas, permite disfrutar de la naturaleza y de actividades al aire libre en familia, convirtiendo este hogar en una opción ideal.', 'Propiedad Familiar', -38.004900, -57.543800),
(16, 'Av. Belgrano 2000, Mar del Plata, Buenos Aires', 2700, 250, 200, 'No pets', 350, 250, '2024-04-01', 'City P', 4, 1, 0, 59842045, 'Casa familiar con un diseño encantador, que combina lo moderno y lo clásico. Dispone de un jardín espacioso y una terraza ideal para disfrutar del aire libre. El interior de la casa está diseñado para ofrecer comodidad y funcionalidad, con espacios amplios y bien iluminados. La propiedad está situada en un vecindario tranquilo, lo que la convierte en un lugar ideal para criar a los niños. Su cercanía a escuelas y comercios la hace perfecta para familias que buscan un entorno seguro y conveniente.', 'Propiedad Familiar', -38.708300, -62.285400),
(17, 'Calle Rincón 33, San Miguel, Buenos Aires', 2800, 260, 210, 'Good credit', 360, 260, '2024-05-01', 'City Q', 1, 1, 0, 96477803, 'Casa acogedora en una ubicación privilegiada, ideal para familias. Con una distribución práctica, cada habitación ofrece privacidad y confort. El salón es perfecto para momentos en familia y la cocina está equipada con lo necesario para disfrutar de la gastronomía. El jardín trasero es un espacio seguro para que los niños jueguen y se diviertan al aire libre. La propiedad se encuentra cerca de colegios y centros comerciales, brindando a las familias todo lo que necesitan a solo unos minutos de casa.', 'Propiedad Familiar', -31.344900, -64.048200),
(18, 'Calle Tucumán 60, Resistencia, Chaco', 2900, 270, 220, 'Stable job', 370, 270, '2024-06-01', 'City R', 2, 1, 0, 160262, 'Encantadora casa familiar con una distribución que maximiza el espacio y la luz natural. La propiedad incluye un jardín que invita a disfrutar del aire libre y a organizar reuniones. Cada habitación ha sido diseñada pensando en la comodidad y funcionalidad. La cocina es moderna y cuenta con electrodomésticos eficientes. Ubicada en un vecindario amigable, esta casa está cerca de escuelas y áreas recreativas, lo que la convierte en un lugar ideal para vivir y criar a los hijos en un entorno seguro.', 'Propiedad Familiar', -32.972500, -60.689300),
(19, 'Calle Sarmiento 45, San Carlos de Bariloche, Río Negro', 3000, 280, 230, 'No smoking', 380, 280, '2024-07-01', 'City S', 3, 0, 1, 150676, 'Casa de dos niveles con un diseño contemporáneo, que ofrece una excelente distribución del espacio. Las habitaciones son amplias y luminosas, proporcionando un ambiente ideal para la vida familiar. La cocina está equipada con lo último en tecnología y tiene un diseño funcional. La propiedad cuenta con un jardín perfecto para disfrutar de actividades al aire libre. Situada en una zona tranquila, con fácil acceso a servicios y escuelas, esta casa es una opción ideal para familias que buscan comodidad y calidad de vida.', 'Propiedad Familiar', -34.624700, -58.371800),
(20, 'Calle Mendoza 90, Formosa, Formosa', 3100, 290, 240, 'Good references', 390, 290, '2024-08-01', 'City T', 4, 0, 1, 127017, 'Magnífica casa familiar, rodeada de naturaleza, que ofrece un ambiente tranquilo y acogedor. La propiedad cuenta con amplios espacios internos, ideales para la convivencia y el descanso. La cocina es moderna y funcional, perfecta para preparar comidas en familia. El jardín es amplio y está diseñado para disfrutar de actividades al aire libre. Su ubicación proporciona un fácil acceso a servicios, escuelas y áreas recreativas, convirtiéndola en una opción perfecta para familias que buscan un hogar donde crecer y crear recuerdos juntos.', 'Propiedad Familiar', -34.737600, -58.205000),
(21, 'Av. San Juan 200, CABA, Buenos Aires', 3200, 300, 250, 'No pets', 400, 300, '2024-09-01', 'City U', 1, 1, 0, 243079, 'Casa familiar con un diseño acogedor, que ofrece un ambiente cálido y amigable. Con espacios amplios y bien distribuidos, es perfecta para disfrutar en familia. La cocina está equipada con electrodomésticos modernos y un comedor ideal para reuniones familiares. Además, el jardín ofrece un espacio seguro para que los niños jueguen. Ubicada en un vecindario tranquilo, esta propiedad está cerca de colegios y tiendas, lo que la convierte en una excelente opción para familias que valoran la conveniencia y el confort.', 'Propiedad Familiar', -34.724200, -58.251200),
(22, 'Calle Soler 80, Villa María, Córdoba', 3300, 310, 260, 'Good credit', 410, 310, '2024-10-01', 'City V', 2, 1, 0, 166888, 'Propiedad familiar de estilo tradicional, que ofrece un ambiente acogedor y familiar. Con un diseño que prioriza la comodidad, esta casa cuenta con un gran salón y varias habitaciones espaciosas. La cocina es funcional y perfecta para disfrutar de comidas en familia. El jardín es un espacio ideal para que los niños jueguen y se diviertan al aire libre. Situada en un vecindario tranquilo, cerca de escuelas y parques, esta propiedad es perfecta para quienes buscan un hogar donde criar a sus hijos en un entorno seguro.', 'Propiedad Familiar', -31.415800, -64.183300),
(23, 'Calle Entre Ríos 25, Comodoro Rivadavia, Chubut', 3400, 320, 270, 'Stable job', 420, 320, '2024-11-01', 'City W', 3, 1, 0, 187894, 'Casa familiar bien ubicada, que combina comodidad y estilo. El salón es espacioso y perfecto para reuniones familiares, mientras que la cocina está equipada para facilitar la preparación de comidas. Las habitaciones son amplias y luminosas, proporcionando un ambiente ideal para el descanso. La propiedad cuenta con un jardín que invita a disfrutar de actividades al aire libre. Además, su proximidad a colegios y áreas recreativas la convierte en una opción ideal para familias que buscan un entorno seguro y conveniente.', 'Propiedad Familiar', -34.800800, -58.417600),
(24, 'Calle San Luis 45, San Salvador de Jujuy, Jujuy', 3500, 330, 280, 'No smoking', 430, 330, '2024-12-01', 'City X', 4, 0, 1, 175554, 'Amplia casa familiar que combina un diseño moderno con la calidez de un hogar acogedor. La propiedad cuenta con múltiples habitaciones, ideales para cada miembro de la familia. El jardín es perfecto para jugar y organizar reuniones. La cocina está diseñada para ser funcional y moderna, facilitando la preparación de comidas. Ubicada en un vecindario amigable y seguro, esta casa está cerca de servicios, escuelas y áreas recreativas, lo que la hace ideal para familias que valoran la comodidad y la calidad de vida.', 'Propiedad Familiar', -34.913500, -57.949000),
(25, 'Calle Maipú 45, San Nicolás, Buenos Aires', 3600, 340, 290, 'Good references', 440, 340, '2025-01-01', 'City Y', 1, 0, 1, 121207, 'Casa familiar de estilo contemporáneo, con una distribución que favorece la convivencia. Cada habitación ha sido diseñada para maximizar el espacio y la luz natural. El jardín es amplio y ofrece un espacio seguro para que los niños jueguen. La cocina es moderna y funcional, ideal para preparar comidas familiares. Su ubicación permite un fácil acceso a colegios, parques y comercios, lo que la convierte en una excelente opción para quienes buscan un hogar donde criar a sus hijos con comodidad y seguridad.', 'Propiedad Familiar', -37.970700, -57.540700),
(26, 'Calle Aguero 15, La Plata, Buenos Aires', 3700, 350, 300, 'No pets', 450, 350, '2025-02-01', 'City Z', 2, 0, 1, 34233863, 'Este local comercial se encuentra en una ubicación estratégica, con alta visibilidad y flujo de clientes. Ideal para todo tipo de negocios, cuenta con un amplio espacio interior que puede adaptarse a diversas configuraciones. La propiedad está equipada con servicios básicos y tiene un acceso fácil para proveedores y clientes. Además, su cercanía a otras empresas y centros comerciales potencia el negocio, asegurando un flujo constante de potenciales consumidores. Este es un lugar ideal para aquellos emprendedores que buscan establecerse en un área dinámica y en crecimiento.', 'Propiedad Comercial', -32.943600, -60.640600),
(27, 'Calle Costa Rica 40, Río Cuarto, Córdoba', 3800, 360, 310, 'Good credit', 460, 360, '2025-03-01', 'City AA', 3, 0, 1, 50031563, 'Local comercial en una zona de alto tráfico peatonal, ideal para tiendas de retail o servicios. Este espacio ofrece una gran vitrina que asegura la visibilidad de su negocio. Con una distribución interna que permite la optimización del espacio, puede ser fácilmente adaptado según las necesidades de su empresa. La propiedad también cuenta con todas las instalaciones necesarias para iniciar operaciones de inmediato. Su ubicación en una comunidad en desarrollo garantiza que su negocio pueda crecer y atraer a nuevos clientes constantemente.', 'Propiedad Comercial', -32.867600, -68.828900),
(28, 'Calle La Pampa 5000, Salta, Salta', 3900, 370, 320, 'Stable job', 470, 370, '2025-04-01', 'City BB', 4, 1, 0, 265326, 'Amplio local comercial con un diseño moderno y funcional. Este espacio es perfecto para oficinas o cualquier tipo de negocio que requiera un ambiente profesional. La propiedad cuenta con acceso a servicios básicos y un sistema de climatización adecuado. Además, su ubicación en un área de crecimiento empresarial asegura un constante flujo de clientes. Este local es una excelente inversión para quienes desean establecerse en un mercado en expansión, proporcionando una base sólida para el éxito comercial.', 'Propiedad Comercial', -27.742600, -64.283100),
(29, 'Calle Echeverría 33, Córdoba, Córdoba', 4000, 380, 330, 'No smoking', 480, 380, '2025-05-01', 'City CC', 1, 1, 0, 244167, 'Este local está diseñado para atraer a una amplia variedad de negocios. Con una fachada atractiva y un interior versátil, puede adaptarse a cualquier tipo de comercio. La propiedad se encuentra en una zona de gran afluencia, lo que garantiza una visibilidad constante. Está equipada con todas las comodidades necesarias para operar sin inconvenientes. Además, la cercanía a otros establecimientos comerciales potencia la posibilidad de atraer más clientes, haciendo de este un lugar ideal para emprendedores y negocios establecidos.', 'Propiedad Comercial', -38.951300, -57.553500),
(30, 'Calle Avellaneda 300, San Fernando del Valle de Catamarca, Catamarca', 4100, 390, 340, 'Good references', 490, 390, '2025-06-01', 'City DD', 2, 1, 0, 269548, 'Espacio comercial en un edificio de oficinas, ideal para empresas que buscan un entorno profesional. Este local ofrece un diseño elegante y funcional, perfecto para oficinas o consultorios. Con buena iluminación natural y una disposición que permite el trabajo en equipo, este espacio es perfecto para fomentar la colaboración. La ubicación es estratégica, cerca de transporte público y otros servicios, lo que facilita el acceso tanto a empleados como a clientes. Una excelente opción para quienes buscan un espacio para crecer.', 'Propiedad Comercial', -32.884700, -68.832300),
(31, 'Calle Barracas 88, Luján, Buenos Aires', 4200, 400, 350, 'No pets', 500, 400, '2025-07-01', 'City EE', 3, 1, 0, 39253075, 'Local comercial con gran potencial, ubicado en un área de alta densidad poblacional. Este espacio es ideal para un supermercado, tienda de conveniencia o restaurante. Con un diseño que permite una fácil circulación de clientes, la propiedad se beneficia de una buena visibilidad desde la calle. Está equipada con las instalaciones necesarias para iniciar operaciones de inmediato. La cercanía a residencias y otras empresas garantiza un flujo constante de clientes, convirtiéndolo en una oportunidad atractiva para emprendedores.', 'Propiedad Comercial', -38.391200, -57.817500),
(32, 'Calle Santa Rosa 15, San Martín, Mendoza', 4300, 410, 360, 'Good credit', 510, 410, '2025-08-01', 'City FF', 4, 0, 1, 24795696, 'Este local comercial cuenta con un ambiente moderno y atractivo, ideal para boutiques o tiendas de moda. La propiedad está situada en una zona de alto tránsito, lo que garantiza que su negocio será visto por una gran cantidad de potenciales clientes. Con un diseño interior adaptable, puede personalizarse según las necesidades de su empresa. Además, su cercanía a otras tiendas y restaurantes aumenta las posibilidades de atraer más visitantes, convirtiéndolo en una opción estratégica para cualquier negocio.', 'Propiedad Comercial', -30.658700, -61.374900),
(33, 'Calle Vera 55, Santiago del Estero, Santiago del Estero', 4400, 420, 370, 'Stable job', 520, 420, '2025-09-01', 'City GG', 1, 1, 0, 58822782, 'Amplio local de oficinas en un edificio moderno, ideal para empresas que buscan un espacio profesional. Este ambiente bien iluminado es perfecto para el trabajo colaborativo y ofrece la flexibilidad necesaria para adaptarse a diferentes estilos de trabajo. Con acceso a servicios y buena conexión de transporte, este espacio se convierte en una excelente opción para atraer a empleados y clientes. La propiedad está diseñada para fomentar la productividad y el bienestar laboral, asegurando que su empresa prospere en un entorno positivo.', 'Propiedad Comercial', -29.384200, -66.840100),
(34, 'Calle Neuquén 120, Santa Fe, Santa Fe', 4500, 430, 380, 'No smoking', 530, 430, '2025-10-01', 'City HH', 2, 0, 1, 83350, 'Este local comercial se ubica en una de las calles más transitadas de la ciudad, lo que lo convierte en una excelente opción para cualquier tipo de negocio. Con un diseño funcional y amplio, puede adaptarse a diversas configuraciones, desde tiendas minoristas hasta oficinas. La propiedad cuenta con todas las instalaciones necesarias para iniciar operaciones de inmediato. Su ubicación estratégica, en un área de crecimiento constante, garantiza que su negocio pueda captar la atención de un amplio público.', 'Propiedad Comercial', -30.440400, -61.305500),
(35, 'Calle San Juan 200, Ushuaia, Tierra del Fuego', 4600, 440, 390, 'Good references', 540, 440, '2025-11-01', 'City II', 3, 1, 0, 78895959, 'Local ideal para emprendedores, con un ambiente acogedor y moderno. Este espacio es perfecto para cafeterías o pequeñas tiendas, ofreciendo una excelente visibilidad gracias a su ubicación. Con un diseño interior que fomenta la interacción, los clientes se sentirán cómodos y bienvenidos. Además, su proximidad a áreas residenciales garantiza un flujo constante de clientes. Este local es una inversión segura para quienes desean establecer un negocio en un entorno vibrante y en constante desarrollo.', 'Propiedad Comercial', -27.352000, -58.459600),
(36, 'Calle Juramento 2400, Mendoza, Mendoza', 4700, 450, 400, 'No pets', 550, 450, '2025-12-01', 'City JJ', 4, 1, 0, 60526912, 'Espacio comercial versátil, ubicado en una zona estratégica que garantiza un alto tráfico de clientes. Este local es ideal para una variedad de negocios, desde restaurantes hasta tiendas especializadas. Con un diseño adaptable, el espacio puede ser configurado de diversas maneras para satisfacer las necesidades de su negocio. La propiedad cuenta con instalaciones modernas y está equipada con lo necesario para comenzar a operar rápidamente. La ubicación, rodeada de otras empresas, potencia la visibilidad y el acceso a un amplio público.', 'Propiedad Comercial', -35.142000, -60.596300),
(37, 'Calle O\'Brien 150, Comodoro Rivadavia, Chubut', 4800, 460, 410, 'Good credit', 560, 460, '2026-01-01', 'City KK', 1, 0, 1, 188124, 'Local comercial de diseño contemporáneo, situado en un área de crecimiento. Este espacio es perfecto para negocios que buscan atraer a una clientela diversa. La propiedad cuenta con una entrada principal amplia y un diseño interior que maximiza el uso del espacio. Además, su ubicación en una zona con alto tráfico peatonal asegura que su negocio esté constantemente en el ojo del público. Una gran oportunidad para emprendedores que desean establecerse en un mercado en expansión.', 'Propiedad Comercial', -37.776500, -58.291900),
(38, 'Calle Rivadavia 1440, Morón, Buenos Aires', 4900, 470, 420, 'Stable job', 570, 470, '2026-02-01', 'City LL', 2, 1, 0, 234555, 'Este local comercial es ideal para una tienda de conveniencia o un pequeño supermercado, gracias a su ubicación en un área densamente poblada. Con una distribución que permite una fácil circulación de clientes y un ambiente acogedor, este espacio es perfecto para satisfacer las necesidades diarias de los residentes cercanos. La propiedad está equipada con todas las instalaciones necesarias y tiene un gran potencial para generar ingresos constantes. Una excelente opción para emprendedores que buscan una oportunidad de negocio segura.', 'Propiedad Comercial', -31.535000, -64.051700),
(39, 'Calle Humboldt 345, Villa María, Córdoba', 5000, 480, 430, 'No smoking', 580, 480, '2026-03-01', 'City MM', 3, 1, 0, 85088, 'Amplio local comercial en una de las principales calles de la ciudad, ideal para cualquier tipo de negocio. La propiedad cuenta con una fachada atractiva que garantiza visibilidad, así como un interior flexible que puede adaptarse a diversas configuraciones. Está equipada con servicios básicos y tiene un acceso conveniente para clientes y proveedores. Su ubicación en un área en constante desarrollo la convierte en una opción atractiva para quienes buscan establecerse en un mercado competitivo.', 'Propiedad Comercial', -37.271300, -59.194200),
(40, 'Calle Saavedra 60, San Juan, San Juan', 5100, 490, 440, 'Good references', 590, 490, '2026-04-01', 'City NN', 4, 1, 0, 40164646, 'Este local comercial se encuentra en una zona de alto flujo de clientes, ideal para un negocio de venta al por menor. Con un diseño contemporáneo, ofrece un espacio amplio y luminoso que puede adaptarse a diversas necesidades. La propiedad está equipada con las instalaciones necesarias para comenzar a operar de inmediato. Su ubicación, rodeada de otras empresas, asegura un flujo constante de potenciales compradores, convirtiéndolo en una gran oportunidad para emprendedores que buscan crecer.', 'Propiedad Comercial', -34.187800, -58.315900),
(41, 'Calle Alsina 110, San Martín, Río Negro', 5200, 500, 450, 'No pets', 600, 500, '2026-05-01', 'City OO', 1, 0, 1, 91067315, 'Local comercial bien ubicado, que ofrece un espacio perfecto para todo tipo de negocios. Este local cuenta con un diseño versátil que permite múltiples configuraciones, ideal para adaptarse a las necesidades de diferentes empresas. Con una gran vitrina y un ambiente atractivo, es ideal para captar la atención de los clientes. Además, su cercanía a otras empresas y servicios garantiza un flujo constante de personas, haciendo de este un lugar ideal para emprendedores que buscan establecerse en un área dinámica.', 'Propiedad Comercial', -34.882800, -58.265000),
(42, 'Calle Defensa 500, San Nicolás, Buenos Aires', 5300, 510, 460, 'Good credit', 610, 510, '2026-06-01', 'City PP', 2, 1, 0, 65939854, 'Este local comercial es una excelente opción para quienes buscan iniciar un negocio en un entorno amigable. Con un diseño moderno y atractivo, el espacio está preparado para ofrecer una experiencia única a los clientes. Ubicado en una zona de fácil acceso, su visibilidad es óptima para atraer nuevos clientes. La propiedad cuenta con todas las instalaciones necesarias para comenzar a operar de inmediato, convirtiéndola en una gran oportunidad para quienes desean establecerse en un mercado en crecimiento.', 'Propiedad Comercial', -34.589000, -58.278100),
(43, 'Calle San Martín 180, Resistencia, Chaco', 5400, 520, 470, 'Stable job', 620, 520, '2026-07-01', 'City QQ', 3, 0, 1, 81370937, 'Local comercial en una ubicación privilegiada, ideal para empresas que buscan ser parte de un entorno dinámico. Este espacio está diseñado para atraer a una amplia variedad de clientes, con un diseño interior que fomenta la comodidad y la interacción. Con buena iluminación y un ambiente acogedor, es perfecto para cafeterías, tiendas o servicios. Su ubicación garantiza una gran visibilidad y acceso, convirtiéndolo en un lugar ideal para emprendedores que buscan posicionar su negocio en un área en expansión.', 'Propiedad Comercial', -31.000000, -58.000000),
(44, 'Calle Garay 1600, Trelew, Chubut', 5500, 530, 480, 'No smoking', 630, 530, '2026-08-01', 'City RR', 4, 0, 1, 121468, 'Amplio local comercial, ideal para restaurantes o cafeterías. Con un diseño abierto que fomenta la interacción, este espacio es perfecto para atraer a un público diverso. La propiedad cuenta con todas las instalaciones necesarias para comenzar operaciones de inmediato y su ubicación en un área de gran afluencia asegura un flujo constante de clientes. Además, su atractivo diseño y ambiente acogedor lo convierten en un lugar ideal para crear experiencias memorables para los visitantes.', 'Propiedad Comercial', -35.174500, -58.205600),
(45, 'Calle Castro Barros 1500, San Luis, San Luis', 5600, 540, 490, 'Good references', 640, 540, '2026-09-01', 'City SS', 1, 0, 1, 246792, 'Este local comercial está situado en una de las zonas más concurridas de la ciudad, lo que garantiza un alto flujo de clientes. Con un diseño versátil y acogedor, puede ser utilizado para diversas actividades comerciales. La propiedad está equipada con las instalaciones necesarias y su acceso fácil lo hace conveniente para todos los clientes. Ideal para emprendedores que buscan establecer su negocio en un área vibrante y con gran potencial de crecimiento.', 'Propiedad Comercial', -34.655300, -58.618700),
(46, 'Calle Mendoza 123, San Miguel de Tucumán, Tucumán', 5700, 550, 500, 'No pets', 650, 550, '2026-10-01', 'City TT', 2, 1, 0, 113758, 'Este espacio comercial es ideal para aquellos que buscan establecer un negocio en un área activa y en crecimiento. Con un diseño moderno y atractivo, el local puede adaptarse a las necesidades de diferentes tipos de empresas. Ubicado en una zona de fácil acceso, su visibilidad es excelente, asegurando que su negocio sea notado. Equipado con todas las comodidades necesarias, este local es una gran oportunidad para emprendedores que desean aprovechar un mercado en expansión.', 'Propiedad Comercial', -32.954900, -60.689400),
(47, 'Calle Jujuy 900, Río Gallegos, Santa Cruz', 5800, 560, 510, 'Good credit', 660, 560, '2026-11-01', 'City UU', 3, 1, 0, 27667869, 'Local comercial bien iluminado, ideal para empresas que buscan un espacio profesional y acogedor. Este local cuenta con un diseño moderno y eficiente, perfecto para cualquier tipo de negocio. Su ubicación estratégica asegura un alto flujo de clientes, mientras que su ambiente agradable y cómodo invita a permanecer y disfrutar de la experiencia. Una gran opción para emprendedores que desean establecerse en un lugar donde puedan atraer y retener a su clientela.', 'Propiedad Comercial', -38.415300, -62.080200),
(48, 'Calle Corrientes 5600, Gualeguaychú, Entre Ríos', 5900, 570, 520, 'Stable job', 670, 570, '2026-12-01', 'City VV', 4, 0, 1, 18129846, 'Este local es una excelente opción para aquellos que desean abrir un negocio en una comunidad vibrante. Con un diseño contemporáneo y versátil, puede adaptarse a las necesidades de diferentes tipos de comercios. Ubicado en una zona de alto tráfico peatonal, su visibilidad es óptima para captar la atención de nuevos clientes. La propiedad está equipada con todas las instalaciones necesarias para comenzar a operar rápidamente, convirtiéndola en una opción atractiva para emprendedores que buscan una buena ubicación.', 'Propiedad Comercial', -34.924400, -57.953900),
(49, 'Calle Córdoba 999, Lanús, Buenos Aires', 6000, 580, 530, 'No smoking', 680, 580, '2027-01-01', 'City WW', 1, 0, 1, 93355346, 'Local comercial con gran potencial en un área de rápido crecimiento. Este espacio es ideal para una variedad de negocios, desde tiendas minoristas hasta oficinas. Con un diseño moderno y funcional, puede ser fácilmente adaptado según las necesidades de su empresa. La propiedad cuenta con acceso a servicios básicos y su ubicación en un área en expansión asegura que su negocio pueda crecer y atraer a nuevos clientes constantemente.', 'Propiedad Comercial', -38.400000, -57.550000),
(50, 'Calle La Pampa 600, Concordia, Entre Ríos', 6100, 590, 540, 'Good references', 690, 590, '2027-02-01', 'City XX', 2, 1, 0, 238006, 'Este local se encuentra en un área privilegiada, ideal para cualquier tipo de comercio. Con un diseño atractivo y un interior versátil, puede adaptarse a diversas configuraciones. La propiedad está equipada con todas las instalaciones necesarias y tiene un acceso fácil para clientes y proveedores. Su ubicación en un vecindario dinámico la convierte en una excelente opción para quienes buscan establecer un negocio en un área con gran potencial de crecimiento.', 'Propiedad Comercial', -35.235700, -58.215000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `idPropietario` int NOT NULL,
  `cbu` bigint NOT NULL,
  `idPersona` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`idPropietario`, `cbu`, `idPersona`) VALUES
(1, 1234567890123456, 16),
(2, 2345678901234567, 17),
(3, 3456789012345678, 18),
(4, 4567890123456789, 19);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comercial`
--
ALTER TABLE `comercial`
  ADD PRIMARY KEY (`idComercial`,`idPropiedad`),
  ADD KEY `fk_Comercial_Propiedad1` (`idPropiedad`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`nro_contrato`),
  ADD KEY `fk_Contrato_Propiedad1_idx` (`idPropiedad`),
  ADD KEY `fk_Contrato_Inquilino1_idx` (`idPersona`),
  ADD KEY `fk_Contrato_Martillero1_idx` (`idMartillero`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`idEstado`),
  ADD UNIQUE KEY `idEstado_UNIQUE` (`idEstado`);

--
-- Indices de la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD PRIMARY KEY (`nro_contrato`,`idEstado`),
  ADD KEY `fk_Contrato_has_Estado_Estado1_idx` (`idEstado`),
  ADD KEY `fk_Contrato_has_Estado_Contrato1_idx` (`nro_contrato`);

--
-- Indices de la tabla `familiar`
--
ALTER TABLE `familiar`
  ADD PRIMARY KEY (`idFamiliar`,`idPropiedad`),
  ADD KEY `fk_Familiar_Propiedad1` (`idPropiedad`);

--
-- Indices de la tabla `firma`
--
ALTER TABLE `firma`
  ADD PRIMARY KEY (`nro_contrato`,`idGarante`),
  ADD KEY `fk_Contrato_has_Garante_Garante1_idx` (`idGarante`);

--
-- Indices de la tabla `garante`
--
ALTER TABLE `garante`
  ADD PRIMARY KEY (`idGarante`,`idPersona`),
  ADD KEY `fk_Garante_Persona1_idx` (`idPersona`);

--
-- Indices de la tabla `imagen_prop`
--
ALTER TABLE `imagen_prop`
  ADD KEY `idPropiedad` (`idPropiedad`);

--
-- Indices de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  ADD PRIMARY KEY (`idInquilino`,`idPersona`),
  ADD KEY `fk_Inquilino_Persona1` (`idPersona`);

--
-- Indices de la tabla `martillero`
--
ALTER TABLE `martillero`
  ADD PRIMARY KEY (`idMartillero`,`idPersona`),
  ADD KEY `fk_Martillero_Persona1_idx` (`idPersona`);


--
-- Indices de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD PRIMARY KEY (`idPropiedad`),
  ADD UNIQUE KEY `idPropiedad_UNIQUE` (`idPropiedad`),
  ADD KEY `fk_Propiedad_Propietario1_idx` (`idPropietario`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`idPropietario`,`idPersona`),
  ADD KEY `fk_Propietario_Persona1_idx` (`idPersona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `nro_contrato` int NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comercial`
--
ALTER TABLE `comercial`
  ADD CONSTRAINT `fk_Comercial_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_Contrato_Inquilino1` FOREIGN KEY (`idPersona`) REFERENCES `inquilino` (`idInquilino`),
  ADD CONSTRAINT `fk_Contrato_Martillero1` FOREIGN KEY (`idMartillero`) REFERENCES `martillero` (`idMartillero`),
  ADD CONSTRAINT `fk_Contrato_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Filtros para la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD CONSTRAINT `fk_Contrato_has_Estado_Contrato1` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`),
  ADD CONSTRAINT `fk_Contrato_has_Estado_Estado1` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`);

--
-- Filtros para la tabla `familiar`
--
ALTER TABLE `familiar`
  ADD CONSTRAINT `fk_Familiar_Propiedad1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Filtros para la tabla `firma`
--
ALTER TABLE `firma`
  ADD CONSTRAINT `fk_Contrato_has_Garante_Contrato1` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`),
  ADD CONSTRAINT `fk_Contrato_has_Garante_Garante1` FOREIGN KEY (`idGarante`) REFERENCES `garante` (`idGarante`);

--
-- Filtros para la tabla `garante`
--
ALTER TABLE `garante`
  ADD CONSTRAINT `fk_Garante_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `imagen_prop`
--
ALTER TABLE `imagen_prop`
  ADD CONSTRAINT `imagen_prop_ibfk_1` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Filtros para la tabla `martillero`
--
ALTER TABLE `martillero`
  ADD CONSTRAINT `fk_Martillero_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD CONSTRAINT `fk_Propiedad_Propietario1` FOREIGN KEY (`idPropietario`) REFERENCES `propietario` (`idPropietario`);

--
-- Filtros para la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD CONSTRAINT `fk_Propietario_Persona1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
