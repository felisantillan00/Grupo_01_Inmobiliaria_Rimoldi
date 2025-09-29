-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 29, 2025 at 09:54 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rimoldi_inmobiliaria`
--

-- --------------------------------------------------------

--
-- Table structure for table `comercial`
--

CREATE TABLE `comercial` (
  `idPropiedad` int NOT NULL,
  `permisos_municipales` bit(1) NOT NULL,
  `cocina` bit(1) NOT NULL,
  `vidriera` bit(1) NOT NULL,
  `deposito` bit(1) NOT NULL,
  `baño` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comercial`
--

INSERT INTO `comercial` (`idPropiedad`, `permisos_municipales`, `cocina`, `vidriera`, `deposito`, `baño`) VALUES
(1, b'1', b'1', b'1', b'1', b'0'),
(2, b'0', b'1', b'0', b'0', b'0'),
(3, b'1', b'0', b'1', b'1', b'1'),
(4, b'1', b'0', b'1', b'1', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `contrato`
--

CREATE TABLE `contrato` (
  `nro_contrato` int NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_cancelacion` date DEFAULT NULL,
  `idPropiedad` int NOT NULL,
  `dniPropietario` bigint NOT NULL,
  `dniInquilino` bigint NOT NULL,
  `dniMartillero` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `contrato`
--

INSERT INTO `contrato` (`nro_contrato`, `fecha_inicio`, `fecha_fin`, `fecha_cancelacion`, `idPropiedad`, `dniPropietario`, `dniInquilino`, `dniMartillero`) VALUES
(1, '2025-03-01', '2026-03-01', NULL, 1, 20123456789, 20345678901, 20123456789);

-- --------------------------------------------------------

--
-- Table structure for table `detallepago`
--

CREATE TABLE `detallepago` (
  `idDetalle` int NOT NULL,
  `concepto` varchar(255) NOT NULL,
  `monto` double NOT NULL,
  `idPago` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `estado`
--

CREATE TABLE `estado` (
  `idEstado` int NOT NULL,
  `estado` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `estado`
--

INSERT INTO `estado` (`idEstado`, `estado`) VALUES
(1, 'Activo'),
(2, 'Cancelado');

-- --------------------------------------------------------

--
-- Table structure for table `estadocontrato`
--

CREATE TABLE `estadocontrato` (
  `nro_contrato` int NOT NULL,
  `idEstado` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `estadocontrato`
--

INSERT INTO `estadocontrato` (`nro_contrato`, `idEstado`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `familiar`
--

CREATE TABLE `familiar` (
  `idPropiedad` int NOT NULL,
  `cant_ambientes` int NOT NULL,
  `piscina` bit(1) NOT NULL,
  `permiteMascota` tinyint NOT NULL,
  `permiteNiños` tinyint NOT NULL,
  `cant_baños` int NOT NULL,
  `cant_cocheras` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `familiar`
--

INSERT INTO `familiar` (`idPropiedad`, `cant_ambientes`, `piscina`, `permiteMascota`, `permiteNiños`, `cant_baños`, `cant_cocheras`) VALUES
(1, 3, b'1', 1, 1, 0, 0),
(2, 2, b'0', 1, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `garante`
--

CREATE TABLE `garante` (
  `dniGarante` bigint NOT NULL,
  `ingresos` double NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `contacto_trabaja` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `garante`
--

INSERT INTO `garante` (`dniGarante`, `ingresos`, `empresa_trabaja`, `contacto_trabaja`) VALUES
(20234567890, 150000, 'Empresa B', 'Contacto B');

-- --------------------------------------------------------

--
-- Table structure for table `inquilino`
--

CREATE TABLE `inquilino` (
  `dniInquilino` bigint NOT NULL,
  `mascotas` tinyint NOT NULL,
  `empresa_trabaja` varchar(45) NOT NULL,
  `cantidad_integrantes` varchar(45) NOT NULL,
  `ingresos` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `inquilino`
--

INSERT INTO `inquilino` (`dniInquilino`, `mascotas`, `empresa_trabaja`, `cantidad_integrantes`, `ingresos`) VALUES
(20345678901, 1, 'Empresa A', '3', 120000);

-- --------------------------------------------------------

--
-- Table structure for table `martillero`
--

CREATE TABLE `martillero` (
  `dniMartillero` bigint NOT NULL,
  `nro_matricula` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `martillero`
--

INSERT INTO `martillero` (`dniMartillero`, `nro_matricula`) VALUES
(20123456789, 1234);

-- --------------------------------------------------------

--
-- Table structure for table `pago`
--

CREATE TABLE `pago` (
  `idPago` int NOT NULL,
  `fecha_pago` date NOT NULL,
  `monto_total` double NOT NULL,
  `nro_contrato` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pago`
--

INSERT INTO `pago` (`idPago`, `fecha_pago`, `monto_total`, `nro_contrato`) VALUES
(1, '2025-04-01', 30000, 1),
(2, '2025-05-01', 30000, 1),
(3, '2025-04-01', 25000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `persona`
--

CREATE TABLE `persona` (
  `dni` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `fecha_nac` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `cuil` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `persona`
--

INSERT INTO `persona` (`dni`, `nombre`, `email`, `celular`, `fecha_nac`, `username`, `cuil`, `password`, `direccion`) VALUES
(20123456789, 'Juan Perez', 'juan@mail.com', '1551234567', '1980-05-10 00:00:00.000000', 'juanp', 20123456789, 'pass123', NULL),
(20234567890, 'Ana Lopez', 'ana@mail.com', '1552345678', '1990-08-22 00:00:00.000000', 'analo', 20234567890, 'pass456', NULL),
(20345678901, 'Carlos Ruiz', 'carlos@mail.com', '1553456789', '1985-12-15 00:00:00.000000', 'carlosr', 20345678901, 'pass789', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `propiedad`
--

CREATE TABLE `propiedad` (
  `idPropiedad` int NOT NULL,
  `dniPropietario` bigint NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `m2_cubiertos` int NOT NULL,
  `m2_descubiertos` int NOT NULL,
  `condiciones_garantes` varchar(255) DEFAULT NULL,
  `expensas` double DEFAULT NULL,
  `gastos` double NOT NULL,
  `fecha_precio_minimo` datetime(6) DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `enVenta` tinyint(1) NOT NULL,
  `enAlquiler` tinyint(1) NOT NULL,
  `valor_alquiler` double NOT NULL,
  `valor_venta` double NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `latitud` double DEFAULT NULL,
  `longitud` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `propiedad`
--

INSERT INTO `propiedad` (`idPropiedad`, `dniPropietario`, `direccion`, `m2_cubiertos`, `m2_descubiertos`, `condiciones_garantes`, `expensas`, `gastos`, `fecha_precio_minimo`, `ciudad`, `enVenta`, `enAlquiler`, `valor_alquiler`, `valor_venta`, `descripcion`, `titulo`, `latitud`, `longitud`) VALUES
(1, 20123456789, 'Calle Falsa 123', 80, 20, 'Con Garante', 5000, 1000, '2025-01-01 00:00:00.000000', 'Ciudad A', 1, 1, 30000, 250000, 'Hermosa propiedad familiar', 'Casa en Venta', -35, -58),
(2, 20234567890, 'Av Siempre Viva 742', 60, 15, 'Sin Garante', 3000, 800, '2025-02-01 00:00:00.000000', 'Ciudad B', 0, 1, 25000, 180000, 'Departamento céntrico', 'Depto en Alquiler', -35, -58),
(3, 20123456789, 'Av. Siempre Viva 123', 100, 50, 'Garantía propietaria', 5000, 4000, '2025-09-29 00:00:00.000000', 'San Antonio Oeste', 0, 0, 0, 100000, 'Local con excelente ubicación', 'Local Comercial en Venta', -40, -65),
(4, 20123456789, 'Av. Siempre Viva 123', 100, 50, 'Garantía propietaria', 5000, 4000, '2025-09-29 00:00:00.000000', 'San Antonio Oeste', 0, 0, 0, 100000, 'Local con excelente ubicación', 'Local Comercial en Venta', -40, -65);

-- --------------------------------------------------------

--
-- Table structure for table `propietario`
--

CREATE TABLE `propietario` (
  `dniPropietario` bigint NOT NULL,
  `cbu` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `propietario`
--

INSERT INTO `propietario` (`dniPropietario`, `cbu`) VALUES
(20123456789, 123456789016789012),
(20234567890, 234567834567890123);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comercial`
--
ALTER TABLE `comercial`
  ADD PRIMARY KEY (`idPropiedad`);

--
-- Indexes for table `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`nro_contrato`),
  ADD KEY `fk_Contrato_Propiedad` (`idPropiedad`),
  ADD KEY `fk_Contrato_Propietario` (`dniPropietario`),
  ADD KEY `fk_Contrato_Inquilino` (`dniInquilino`),
  ADD KEY `fk_Contrato_Martillero` (`dniMartillero`);

--
-- Indexes for table `detallepago`
--
ALTER TABLE `detallepago`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `fk_detalle_pago` (`idPago`);

--
-- Indexes for table `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indexes for table `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD PRIMARY KEY (`nro_contrato`,`idEstado`),
  ADD KEY `fk_Contrato_has_Estado_Estado` (`idEstado`);

--
-- Indexes for table `familiar`
--
ALTER TABLE `familiar`
  ADD PRIMARY KEY (`idPropiedad`);

--
-- Indexes for table `garante`
--
ALTER TABLE `garante`
  ADD PRIMARY KEY (`dniGarante`);

--
-- Indexes for table `inquilino`
--
ALTER TABLE `inquilino`
  ADD PRIMARY KEY (`dniInquilino`);

--
-- Indexes for table `martillero`
--
ALTER TABLE `martillero`
  ADD PRIMARY KEY (`dniMartillero`);

--
-- Indexes for table `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`idPago`),
  ADD KEY `fk_Pago_Propiedad` (`nro_contrato`);

--
-- Indexes for table `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`dni`),
  ADD UNIQUE KEY `celular` (`celular`);

--
-- Indexes for table `propiedad`
--
ALTER TABLE `propiedad`
  ADD PRIMARY KEY (`idPropiedad`),
  ADD KEY `fk_Propiedad_Propietario` (`dniPropietario`);

--
-- Indexes for table `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`dniPropietario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contrato`
--
ALTER TABLE `contrato`
  MODIFY `nro_contrato` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pago`
--
ALTER TABLE `pago`
  MODIFY `idPago` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `propiedad`
--
ALTER TABLE `propiedad`
  MODIFY `idPropiedad` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comercial`
--
ALTER TABLE `comercial`
  ADD CONSTRAINT `fk_Comercial_Propiedad` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Constraints for table `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_Contrato_Inquilino` FOREIGN KEY (`dniInquilino`) REFERENCES `inquilino` (`dniInquilino`),
  ADD CONSTRAINT `fk_Contrato_Martillero` FOREIGN KEY (`dniMartillero`) REFERENCES `martillero` (`dniMartillero`),
  ADD CONSTRAINT `fk_Contrato_Propiedad` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`),
  ADD CONSTRAINT `fk_Contrato_Propietario` FOREIGN KEY (`dniPropietario`) REFERENCES `propietario` (`dniPropietario`);

--
-- Constraints for table `detallepago`
--
ALTER TABLE `detallepago`
  ADD CONSTRAINT `fk_detalle_pago` FOREIGN KEY (`idPago`) REFERENCES `pago` (`idPago`);

--
-- Constraints for table `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD CONSTRAINT `fk_Contrato_has_Estado_Contrato` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`),
  ADD CONSTRAINT `fk_Contrato_has_Estado_Estado` FOREIGN KEY (`idEstado`) REFERENCES `estado` (`idEstado`);

--
-- Constraints for table `familiar`
--
ALTER TABLE `familiar`
  ADD CONSTRAINT `fk_Familiar_Propiedad` FOREIGN KEY (`idPropiedad`) REFERENCES `propiedad` (`idPropiedad`);

--
-- Constraints for table `garante`
--
ALTER TABLE `garante`
  ADD CONSTRAINT `fk_Garante_Persona` FOREIGN KEY (`dniGarante`) REFERENCES `persona` (`dni`);

--
-- Constraints for table `inquilino`
--
ALTER TABLE `inquilino`
  ADD CONSTRAINT `fk_Inquilino_Persona` FOREIGN KEY (`dniInquilino`) REFERENCES `persona` (`dni`);

--
-- Constraints for table `martillero`
--
ALTER TABLE `martillero`
  ADD CONSTRAINT `fk_Martillero_Persona` FOREIGN KEY (`dniMartillero`) REFERENCES `persona` (`dni`);

--
-- Constraints for table `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `fk_Pago_Propiedad` FOREIGN KEY (`nro_contrato`) REFERENCES `contrato` (`nro_contrato`);

--
-- Constraints for table `propiedad`
--
ALTER TABLE `propiedad`
  ADD CONSTRAINT `fk_Propiedad_Propietario` FOREIGN KEY (`dniPropietario`) REFERENCES `propietario` (`dniPropietario`);

--
-- Constraints for table `propietario`
--
ALTER TABLE `propietario`
  ADD CONSTRAINT `fk_Propietario_Persona` FOREIGN KEY (`dniPropietario`) REFERENCES `persona` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
