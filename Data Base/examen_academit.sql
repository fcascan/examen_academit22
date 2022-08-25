-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-08-2022 a las 06:38:45
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `examen_academit`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` bigint(20) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `codigo_producto` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `categoria`, `codigo_producto`, `nombre`, `precio`) VALUES
(1, 'Ropa Hombre', 112233, 'Remera H #1', 3500),
(2, 'Ropa Hombre', 223344, 'Remera H #3', 2500),
(3, 'Ropa Hombre', 334455, 'Pantalon H #4', 5500),
(4, 'Ropa Mujer', 445566, 'Remera M #9', 1750),
(5, 'Ropa Mujer', 556677, 'Remera M #12', 3000),
(6, 'Ropa Mujer', 667788, 'Pantalon M #7', 4500),
(7, 'Accesorios', 778899, 'Pack Medias', 1500),
(8, 'Accesorios', 889900, 'Gorro Lana', 2000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `id_vendedor` bigint(20) NOT NULL,
  `codigo_vendedor` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `sueldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`id_vendedor`, `codigo_vendedor`, `nombre`, `sueldo`) VALUES
(1, 111, 'Pablo', 115000),
(2, 222, 'Juan', 125000),
(3, 333, 'Roxana', 140000),
(4, 444, 'Susana', 95000),
(5, 555, 'Juana', 200000),
(6, 777, 'Ariel', 165000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id_venta` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `codigo_venta` bigint(20) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `codigo_producto` bigint(20) DEFAULT NULL,
  `codigo_vendedor` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id_venta`, `cantidad`, `codigo_venta`, `monto`, `codigo_producto`, `codigo_vendedor`) VALUES
(2, 1, 123456, 7449.99, 112233, 444),
(4, 10, 32324, 35000, 112233, 777),
(5, 5, 65738, 12500, 223344, 222),
(6, 2, 123777, 11000, 334455, 555),
(7, 1, 1234723, 1750, 445566, 222),
(8, 4, 724531, 12000, 556677, 333),
(9, 2, 555112, 9000, 667788, 444),
(10, 10, 278345, 15000, 778899, 555),
(11, 3, 672377, 6000, 889900, 444),
(12, 3, 115645, 4500, 778899, 222),
(13, 1, 163735, 2000, 889900, 777);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD UNIQUE KEY `UK_1nk4ttgpqciqys08mooa4ruo` (`codigo_producto`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`id_vendedor`),
  ADD UNIQUE KEY `UK_d7wc9vb6jeqh6ffr9l9jubbjh` (`codigo_vendedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `FK7bb093utidev90eglvmkwylg0` (`codigo_producto`),
  ADD KEY `FK5ber1y2fsb7q19nkuvb53okdt` (`codigo_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  MODIFY `id_vendedor` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id_venta` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FK5ber1y2fsb7q19nkuvb53okdt` FOREIGN KEY (`codigo_vendedor`) REFERENCES `vendedores` (`codigo_vendedor`),
  ADD CONSTRAINT `FK7bb093utidev90eglvmkwylg0` FOREIGN KEY (`codigo_producto`) REFERENCES `productos` (`codigo_producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
