-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-10-2016 a las 02:38:47
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parcial01_poo2_alvinbaires`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `codi_alum` int(11) NOT NULL,
  `nomb_alum` varchar(100) NOT NULL,
  `apel_alum` varchar(100) NOT NULL,
  `fech_naci_alum` date NOT NULL,
  `mail_alum` varchar(100) NOT NULL,
  `tele_alum` varchar(20) NOT NULL,
  `dire_alum` varchar(200) NOT NULL,
  `gene_alum` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`codi_alum`, `nomb_alum`, `apel_alum`, `fech_naci_alum`, `mail_alum`, `tele_alum`, `dire_alum`, `gene_alum`) VALUES
(1, 'Alvin Gerardo', 'Baires Ramírez', '1997-10-15', 'alvinbaires@hotmail.com', '70707070', 'Direccion', 'M'),
(2, 'Jennyfer Liseth', 'Soto Chacon', '1997-10-15', 'jennyfer@hotmail.com', '64646464', 'Direccion', 'M'),
(3, 'Angel Eduardo', 'Rivas Canales', '1997-12-03', 'angel@hotmail.com', '60606060', 'Direccion', 'M'),
(6, 'Camila', 'Baires', '1997-12-03', 'camila@hotmail.com', '1231', '123', 'F');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `applog`
--

CREATE TABLE `applog` (
  `DATED` datetime NOT NULL,
  `LOGGER` varchar(50) NOT NULL,
  `LEVEL` varchar(10) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `applog`
--

INSERT INTO `applog` (`DATED`, `LOGGER`, `LEVEL`, `MESSAGE`) VALUES
('2016-09-30 18:22:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Consultado Angel Rivas'),
('2016-09-30 18:23:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Modificado: Angel Eduardo Rivas Canales'),
('2016-09-30 18:24:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Guardado: Carla Mejia'),
('2016-09-30 18:24:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Consultado Carla Mejia'),
('2016-09-30 18:24:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Eliminado: Carla Mejia'),
('2016-09-30 18:25:00', 'com.sv.udb.controlador.AlumnosBean', 'INFO', 'Consultado Camila Baires'),
('2016-09-30 18:36:00', 'com.sv.udb.controlador.GruposAlumnosBean', 'INFO', 'Consultado Angel Eduardo Angel Eduardo en Grupo 2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `codi_curs` int(11) NOT NULL,
  `nomb_curs` varchar(100) NOT NULL,
  `dura_curs` int(11) NOT NULL,
  `cost_curs` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`codi_curs`, `nomb_curs`, `dura_curs`, `cost_curs`) VALUES
(1, 'Cisco', 200, 200),
(2, 'Ricaldone', 2, 200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `codi_grup` int(11) NOT NULL,
  `nomb_grup` varchar(100) NOT NULL,
  `fech_inic_grup` date NOT NULL,
  `fech_fina_grup` date NOT NULL,
  `hora_inic_grup` varchar(5) NOT NULL,
  `hora_fina_grup` varchar(5) NOT NULL,
  `codi_curs` int(11) NOT NULL,
  `codi_prof` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`codi_grup`, `nomb_grup`, `fech_inic_grup`, `fech_fina_grup`, `hora_inic_grup`, `hora_fina_grup`, `codi_curs`, `codi_prof`) VALUES
(1, 'Grupo 1', '2016-08-02', '2016-08-05', '12:30', '1:00', 1, 1),
(3, 'Grupo 2', '2016-08-07', '2016-08-24', '12:30', '12:30', 2, 1),
(4, 'Ricaldone', '1997-10-15', '1997-10-15', '13:30', '13:50', 2, 1),
(5, 'Ricaldone', '1997-10-15', '1997-10-15', '13:30', '13:50', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos_alumnos`
--

CREATE TABLE `grupos_alumnos` (
  `codi_grup_alum` int(11) NOT NULL,
  `codi_grup` int(11) NOT NULL,
  `codi_alum` int(11) NOT NULL,
  `esta_grup_alum` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `grupos_alumnos`
--

INSERT INTO `grupos_alumnos` (`codi_grup_alum`, `codi_grup`, `codi_alum`, `esta_grup_alum`) VALUES
(1, 1, 1, '1'),
(3, 1, 2, 'A'),
(5, 3, 1, 'A'),
(6, 3, 3, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `codi_prof` int(11) NOT NULL,
  `nomb_prof` varchar(100) NOT NULL,
  `apel_prof` varchar(100) NOT NULL,
  `mail_prof` varchar(100) NOT NULL,
  `tele_prof` varchar(20) NOT NULL,
  `dire_pro` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`codi_prof`, `nomb_prof`, `apel_prof`, `mail_prof`, `tele_prof`, `dire_pro`) VALUES
(1, 'Raul ito', 'Bermudez', 'raul@gmail.com', '39393939', 'Direccion');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`codi_alum`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`codi_curs`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`codi_grup`),
  ADD KEY `profesores_grupos_fk` (`codi_prof`),
  ADD KEY `cursos_grupos_fk` (`codi_curs`);

--
-- Indices de la tabla `grupos_alumnos`
--
ALTER TABLE `grupos_alumnos`
  ADD PRIMARY KEY (`codi_grup_alum`),
  ADD KEY `grupos_grupos_alumnos_fk` (`codi_grup`),
  ADD KEY `alumnos_grupos_alumnos_fk` (`codi_alum`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`codi_prof`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `codi_alum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `cursos`
--
ALTER TABLE `cursos`
  MODIFY `codi_curs` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `codi_grup` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `grupos_alumnos`
--
ALTER TABLE `grupos_alumnos`
  MODIFY `codi_grup_alum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `profesores`
--
ALTER TABLE `profesores`
  MODIFY `codi_prof` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD CONSTRAINT `cursos_grupos_fk` FOREIGN KEY (`codi_curs`) REFERENCES `cursos` (`codi_curs`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `profesores_grupos_fk` FOREIGN KEY (`codi_prof`) REFERENCES `profesores` (`codi_prof`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `grupos_alumnos`
--
ALTER TABLE `grupos_alumnos`
  ADD CONSTRAINT `alumnos_grupos_alumnos_fk` FOREIGN KEY (`codi_alum`) REFERENCES `alumnos` (`codi_alum`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `grupos_grupos_alumnos_fk` FOREIGN KEY (`codi_grup`) REFERENCES `grupos` (`codi_grup`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
