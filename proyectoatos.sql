-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-10-2018 a las 09:16:04
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoatos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `CODIGO_ROL` tinyint(2) NOT NULL,
  `NOMBRE_ROL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`CODIGO_ROL`, `NOMBRE_ROL`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_tareas`
--

CREATE TABLE `roles_tareas` (
  `CODIGO_ROL` tinyint(2) NOT NULL,
  `ID_TAREA` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles_tareas`
--

INSERT INTO `roles_tareas` (`CODIGO_ROL`, `ID_TAREA`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `ID_TAREA` int(2) NOT NULL,
  `NOMBRE_TAREA` varchar(100) NOT NULL,
  `DESCRIPCION_TAREA` varchar(2000) DEFAULT NULL,
  `ESTADO` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`ID_TAREA`, `NOMBRE_TAREA`, `DESCRIPCION_TAREA`, `ESTADO`) VALUES
(1, 'Tarea01', 'Desc tarea 1', 0),
(2, 'Tarea02', 'Desc tarea 2', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `DAS` varchar(15) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  `INICIO` tinyint(1) NOT NULL,
  `CODIGO_ROL` tinyint(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`DAS`, `NOMBRE`, `APELLIDO`, `PASSWORD`, `ESTADO`, `INICIO`, `CODIGO_ROL`) VALUES
('admin', 'NombreAdmin', 'ApellidoAdmin', 'admin', 1, 1, 1),
('usuario', 'NombreUsuario', 'ApellidoUsuario', 'usuario', 0, 1, 2),
('usuario2', 'Guille', 'Cermeño', 'usuario2', 1, 1, 2),
('usuario3', 'Juan', 'Cermeño', 'user', 1, 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`CODIGO_ROL`);

--
-- Indices de la tabla `roles_tareas`
--
ALTER TABLE `roles_tareas`
  ADD PRIMARY KEY (`CODIGO_ROL`,`ID_TAREA`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`ID_TAREA`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`DAS`),
  ADD KEY `FK_CODIGO_ROL` (`CODIGO_ROL`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `CODIGO_ROL` tinyint(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `ID_TAREA` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `FK_CODIGO_ROL` FOREIGN KEY (`CODIGO_ROL`) REFERENCES `roles` (`CODIGO_ROL`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
