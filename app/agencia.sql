-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-02-2025 a las 00:03:00
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agencia`
--

CREATE DATABASE agencia;

USE agencia;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `apellido`, `nombre`) VALUES
(1, 'Blanco', 'Lucia'),
(2, 'Otero', 'Pablo'),
(3, 'Gomez', 'Nuria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `id_habitacion` bigint(20) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `precio_habit` double NOT NULL,
  `tipo_habit` enum('DOBLE','MULTIPLE','SINGLE','TRIPLE') NOT NULL,
  `id_empleado` bigint(20) DEFAULT NULL,
  `id_hotel` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`id_habitacion`, `fecha_fin`, `fecha_inicio`, `precio_habit`, `tipo_habit`, `id_empleado`, `id_hotel`) VALUES
(1, '2025-03-16', '2025-03-12', 1523, 'DOBLE', 1, 1),
(2, '2025-01-24', '2025-01-14', 251, 'SINGLE', 2, 1),
(3, '2025-11-12', '2025-11-01', 7512, 'SINGLE', 3, 2),
(4, '2025-02-14', '2025-02-10', 2510, 'DOBLE', 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` bigint(20) NOT NULL,
  `cod_hotel` varchar(255) NOT NULL,
  `lugar` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `cod_hotel`, `lugar`, `nombre`) VALUES
(1, 'PO-1580', 'Barcelona', 'Piolot'),
(2, 'HL-1852', 'Miami', 'Hillon'),
(3, 'AT-9631', 'Miami', 'Attica'),
(4, 'LI-1524', 'Barcelona', 'Linconl'),
(5, 'UE-2366', 'Madrid', 'Unetop'),
(6, 'HT-1452', 'Madrid', 'Hilton');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` bigint(20) NOT NULL,
  `empleado_id` bigint(20) DEFAULT NULL,
  `vuelo_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `empleado_id`, `vuelo_id`) VALUES
(1, 1, 1),
(2, 3, 2),
(3, 2, 2),
(4, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE `vuelo` (
  `id_vuelo` bigint(20) NOT NULL,
  `asiento` enum('BUSINESS','ECONOMY') NOT NULL,
  `cod_vuelo` varchar(255) NOT NULL,
  `destino` varchar(255) NOT NULL,
  `fecha_ida` date NOT NULL,
  `fecha_vuelta` date NOT NULL,
  `origen` varchar(255) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`id_vuelo`, `asiento`, `cod_vuelo`, `destino`, `fecha_ida`, `fecha_vuelta`, `origen`, `precio`) VALUES
(1, 'ECONOMY', 'BAMA-1515', 'Madrid', '2025-02-10', '2025-02-15', 'Barcelona', 781),
(2, 'BUSINESS', 'MIMA-2547', 'Madrid', '2025-02-10', '2025-02-20', 'Miami', 1820),
(3, 'ECONOMY', 'BAPO-1821', 'Polonia', '2025-02-10', '2025-03-11', 'Barcelona', 781),
(4, 'ECONOMY', 'POMA-3667', 'Madrid', '2025-03-14', '2025-03-20', 'Polonia', 631);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`id_habitacion`),
  ADD KEY `FKibyth5gvy9irelq1n4l0aawji` (`id_empleado`),
  ADD KEY `FKnati1hwmlnaiemvjcwuql8web` (`id_hotel`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `FKaue4a085lqob6799bf4ergio7` (`empleado_id`),
  ADD KEY `FK4tvli56vtc61fgd5dktdd24l` (`vuelo_id`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`id_vuelo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `id_habitacion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  MODIFY `id_vuelo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `FKibyth5gvy9irelq1n4l0aawji` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`),
  ADD CONSTRAINT `FKnati1hwmlnaiemvjcwuql8web` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_hotel`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `FK4tvli56vtc61fgd5dktdd24l` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelo` (`id_vuelo`),
  ADD CONSTRAINT `FKaue4a085lqob6799bf4ergio7` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id_empleado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;