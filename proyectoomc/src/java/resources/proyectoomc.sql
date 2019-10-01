-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-10-2019 a las 19:03:11
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoomc`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_fecha_salida` (IN `_hora_salida` VARCHAR(20), IN `_fecha_salida` DATE, IN `_id_empleado` INT)  begin
declare id_horario_actualizar int;
Set id_horario_actualizar=(SELECT max(id_horario) FROM horario h WHERE h.empleado_id_empleado=_id_empleado and h.fecha_de_salida is null);

UPDATE horario SET hora_salida =_hora_salida, fecha_de_salida =_fecha_salida  WHERE id_horario=id_horario_actualizar;
END$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `sf_edad` (`_fechaNac` DATE) RETURNS INT(11) begin
declare edad int;
declare _año date;
set _año = curdate();
set edad =(TIMESTAMPDIFF(year, _fechaNac , _año));
return edad;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` int(7) NOT NULL,
  `fecha_programada` date NOT NULL,
  `novedades` varchar(300) COLLATE utf32_spanish_ci DEFAULT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `fecha_programada`, `novedades`, `empleado_id_empleado`) VALUES
(1, '2019-09-26', 'Mantenimiento', 1),
(2, '2019-09-25', 'Envios', 5),
(3, '2019-09-30', 'Mantenimiento', 2),
(4, '2019-09-25', 'Recolecta de cilindros', 4),
(5, '2019-09-26', 'Instalacion', 21),
(6, '2019-10-02', 'Envios', 4),
(7, '2019-10-02', 'Capacitación', 3),
(8, '2019-10-03', 'Capacitación', 4),
(9, '2019-10-01', 'Capacitación', 2),
(10, '2019-09-25', 'Capacitación', 21),
(11, '2019-10-16', 'Capacitación', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `id_alquiler` int(10) NOT NULL,
  `fecha_de_entrega` date NOT NULL,
  `novedades` varchar(300) COLLATE utf32_spanish_ci NOT NULL,
  `cilindro_id_cilindro` int(7) NOT NULL,
  `ruta_id_ruta` int(2) NOT NULL,
  `solicitud_id_solicitud` int(7) NOT NULL,
  `estado_alquiler_id_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `alquiler`
--

INSERT INTO `alquiler` (`id_alquiler`, `fecha_de_entrega`, `novedades`, `cilindro_id_cilindro`, `ruta_id_ruta`, `solicitud_id_solicitud`, `estado_alquiler_id_estado`) VALUES
(3, '2001-01-27', 'No', 1060296, 6, 1, 2),
(4, '2002-01-27', 'No', 1060296, 6, 1, 1),
(5, '2019-01-08', 'Ninguna', 1060293, 1, 1, 1),
(6, '2019-01-08', 'Ninguna', 1060293, 1, 6, 1),
(7, '2019-01-08', 'Ninguna', 1060293, 1, 6, 2),
(8, '2019-01-08', 'No', 1060294, 1, 7, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cilindro`
--

CREATE TABLE `cilindro` (
  `id_cilindro` int(7) NOT NULL,
  `estado` varchar(30) COLLATE utf32_spanish_ci NOT NULL,
  `tamanio` int(3) NOT NULL,
  `lote` int(8) NOT NULL,
  `fecha_de_creacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `cilindro`
--

INSERT INTO `cilindro` (`id_cilindro`, `estado`, `tamanio`, `lote`, `fecha_de_creacion`) VALUES
(1060293, 'Dañado', 250, 3102249, '2019-06-02'),
(1060294, 'Funcional', 250, 3102249, '2019-06-02'),
(1060295, 'Dañado', 300, 3102249, '2019-06-02'),
(1060296, 'Funcional', 300, 3102208, '2019-06-09'),
(1060297, 'Funcional', 250, 3102208, '2019-06-09'),
(1060298, 'Dañado', 300, 3102208, '2019-06-09'),
(1060299, 'Funcional', 200, 3102208, '2019-06-09'),
(1060300, 'Dañado', 250, 3102208, '2019-06-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(7) NOT NULL,
  `usuario_id` bigint(10) NOT NULL,
  `estado_id_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `usuario_id`, `estado_id_estado`) VALUES
(1, 10002010212, 1),
(3, 23874610, 3),
(6, 23879120, 1),
(7, 1015410897, 1),
(8, 1111222, 3),
(9, 1000624311, 1),
(10, 23, 1),
(11, 100020251, 3),
(12, 32131, 3),
(13, 10211425, 1),
(14, 11111, 1),
(15, 41521452, 1),
(16, 645452, 1),
(17, 15321541, 1),
(18, 1000205411, 3),
(19, 102154151515, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `id_contrato` int(7) NOT NULL,
  `tipo_contrato` varchar(20) COLLATE utf32_spanish_ci NOT NULL,
  `fecha_inicio_contrato` date NOT NULL,
  `fecha_final_contrato` date NOT NULL,
  `alquiler_id_alquiler` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `contrato`
--

INSERT INTO `contrato` (`id_contrato`, `tipo_contrato`, `fecha_inicio_contrato`, `fecha_final_contrato`, `alquiler_id_alquiler`) VALUES
(10, 'persona natural', '2010-08-12', '2019-09-22', 5),
(11, 'persona natural', '2019-08-01', '2019-09-30', 6),
(12, 'persona natural', '2009-08-01', '2019-08-01', 7),
(13, 'persona natural', '2009-01-01', '2019-01-01', 7),
(14, 'Hospital', '2009-01-01', '2019-01-01', 4),
(15, 'persona natural', '2012-01-01', '2012-09-09', 7),
(16, 'persona natural', '2013-09-09', '2014-09-09', 6),
(17, 'persona natural', '2012-09-01', '2002-09-30', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(3) NOT NULL,
  `usuario_id` bigint(10) NOT NULL,
  `fk_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `usuario_id`, `fk_estado`) VALUES
(1, 23874610, 1),
(2, 10002010212, 1),
(3, 100030122, 1),
(4, 1111222, 1),
(5, 24981099, 1),
(21, 24981099, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_alquiler`
--

CREATE TABLE `estado_alquiler` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(70) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_alquiler`
--

INSERT INTO `estado_alquiler` (`id_estado`, `estado`) VALUES
(1, 'Enlistado'),
(2, 'Entregado'),
(3, 'Regresado a la empresa'),
(4, 'Cancelado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_cliente`
--

CREATE TABLE `estado_cliente` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_cliente`
--

INSERT INTO `estado_cliente` (`id_estado`, `estado`) VALUES
(1, 'Activo'),
(2, 'Innactivo'),
(3, 'Solicito Cilindro'),
(4, 'Recibio Cilindro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_empleado`
--

CREATE TABLE `estado_empleado` (
  `id_estado_empleado` int(2) NOT NULL,
  `estado` varchar(50) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_empleado`
--

INSERT INTO `estado_empleado` (`id_estado_empleado`, `estado`) VALUES
(1, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_mantenimiento`
--

CREATE TABLE `estado_mantenimiento` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(50) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_mantenimiento`
--

INSERT INTO `estado_mantenimiento` (`id_estado`, `estado`) VALUES
(1, 'Realizado'),
(2, 'Falta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_permiso_laboral`
--

CREATE TABLE `estado_permiso_laboral` (
  `id_estado` int(2) NOT NULL,
  `estado_permiso` varchar(50) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_permiso_laboral`
--

INSERT INTO `estado_permiso_laboral` (`id_estado`, `estado_permiso`) VALUES
(1, 'pendiente'),
(2, 'aprobado'),
(3, 'rechazado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `id_horario` int(5) NOT NULL,
  `hora_ingreso` varchar(20) COLLATE utf32_spanish_ci DEFAULT NULL,
  `hora_salida` varchar(20) COLLATE utf32_spanish_ci DEFAULT NULL,
  `empleado_id_empleado` int(3) NOT NULL,
  `fecha_de_ingreso` date DEFAULT NULL,
  `fecha_de_salida` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_horario`, `hora_ingreso`, `hora_salida`, `empleado_id_empleado`, `fecha_de_ingreso`, `fecha_de_salida`) VALUES
(16, '9:44', '9:44', 1, '2019-09-25', '2019-09-25'),
(17, '10:19', '10:19', 1, '2019-09-25', '2019-09-25'),
(18, '10:19', NULL, 1, '2019-09-25', NULL),
(19, '10:19', NULL, 1, '2019-09-25', NULL),
(20, '10:19', NULL, 1, '2019-09-25', NULL),
(21, '10:19', NULL, 1, '2019-09-25', NULL),
(22, '10:19', NULL, 1, '2019-09-25', NULL),
(23, '10:19', NULL, 1, '2019-09-25', NULL),
(24, '10:19', NULL, 1, '2019-09-25', NULL),
(25, '10:19', NULL, 1, '2019-09-25', NULL),
(26, '10:19', '10:19', 1, '2019-09-25', '2019-09-25'),
(27, '10:19', NULL, 1, '2019-09-25', NULL),
(28, '10:19', NULL, 1, '2019-09-25', NULL),
(29, '10:19', NULL, 1, '2019-09-25', NULL),
(30, '10:50', NULL, 1, '2019-09-25', NULL),
(31, '10:50', NULL, 1, '2019-09-25', NULL),
(32, '10:50', NULL, 1, '2019-09-25', NULL),
(33, '10:50', NULL, 1, '2019-09-25', NULL),
(34, '10:50', NULL, 1, '2019-09-25', NULL),
(35, '10:50', NULL, 1, '2019-09-25', NULL),
(36, '10:50', NULL, 1, '2019-09-25', NULL),
(37, '10:50', NULL, 1, '2019-09-25', NULL),
(38, '10:50', NULL, 1, '2019-09-25', NULL),
(39, '10:50', NULL, 1, '2019-09-25', NULL),
(40, '10:57', NULL, 1, '2019-09-25', NULL),
(41, '10:57', NULL, 1, '2019-09-25', NULL),
(42, '10:57', NULL, 1, '2019-09-25', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id_mantenimiento` int(7) NOT NULL,
  `fecha_inicio_mantenimiento` date NOT NULL,
  `fecha_final_mantenimiento` date NOT NULL,
  `agenda_id_agenda` int(7) NOT NULL,
  `estado_mantenimiento` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `mantenimiento`
--

INSERT INTO `mantenimiento` (`id_mantenimiento`, `fecha_inicio_mantenimiento`, `fecha_final_mantenimiento`, `agenda_id_agenda`, `estado_mantenimiento`) VALUES
(2, '2019-09-16', '2019-09-16', 6, 2),
(3, '2019-09-25', '2019-09-25', 3, 2),
(4, '2019-10-01', '2019-10-01', 4, 2),
(5, '2019-09-28', '2019-09-28', 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento_cilindro`
--

CREATE TABLE `mantenimiento_cilindro` (
  `id_mantenimiento_cilindro` int(7) NOT NULL,
  `mantenimiento_id_mantenimiento` int(7) NOT NULL,
  `cilindro_id_cilindro` int(7) NOT NULL,
  `tipo_mantenimiento` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso`
--

CREATE TABLE `permiso` (
  `id_permiso` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `nombre_en` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `url` text COLLATE utf32_spanish_ci DEFAULT NULL,
  `icon` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `permiso_padre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `permiso`
--

INSERT INTO `permiso` (`id_permiso`, `nombre`, `nombre_en`, `url`, `icon`, `permiso_padre`) VALUES
(2, 'Envios', 'Shipping', NULL, 'x', NULL),
(3, 'Crear', 'Create', NULL, 'x', 2),
(4, 'Consultar', 'Consult', NULL, 'x', 2),
(5, 'Programar', 'Program', NULL, 'x', 2),
(6, 'Cliente', 'Custumer', '../../../moduloEnvios/crearCliente.xhtml', 'x', 3),
(7, 'Contrato', 'Contract', '../../../moduloEnvios/crearContrato.xhtml', 'x', 3),
(8, 'Usuario', 'User', '../../../moduloEnvios/crearUsuario.xhtml', 'x', 3),
(9, 'Vehiculo', 'Vehicle', '../../../moduloEnvios/crearVehiculo.xhtml', 'x', 3),
(10, 'Solicitud', 'Request', '../../../moduloEnvios/crearSolicitud.xhtml', 'x', 3),
(11, 'Alquiler', 'Rent', '../../../moduloEnvios/listaAlquiler.xhtml', 'x', 4),
(12, 'Cliente', 'Custumer', '../../../moduloEnvios/listaClientes.xhtml', 'x', 4),
(13, 'Contrato', 'Contract', '../../../moduloEnvios/listaContrato.xhtml', 'x', 4),
(14, 'Roles', 'Roles', '../../../moduloEnvios/listaRoles.xhtml', 'x', 4),
(15, 'Ruta', 'Route', '../../../moduloEnvios/listaRuta.xhtml', 'x', 4),
(16, 'Vehiculos', 'Vehicle', '../../../moduloEnvios/listaVehiculos.xhtml', 'x', 4),
(17, 'Usuarios', 'User', '../../../moduloEnvios/listaUsuario.xhtml', 'x', 4),
(18, 'Solicitud', 'Request', '../../../moduloEnvios/listaSolicitud.xhtml', 'x', 4),
(19, 'Alquiler', 'Rent', '../../../moduloEnvios/crearAlquiler.xhtml', 'x', 5),
(20, 'Ruta', 'Route', '../../../moduloEnvios/crearRuta.xhtml', 'x', 5),
(21, 'Mantenimiento', 'Maintenance', NULL, 'x', NULL),
(22, 'Programar', 'Program', NULL, 'x', 21),
(23, 'Registrar', 'Register', NULL, 'x', 21),
(24, 'Consultar', 'Consult', NULL, 'x', 21),
(25, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/registrarMantenimiento.xhtml', 'x', 22),
(26, 'Mantenimiento cilindro', 'Cylinder Maintenace', '../../../moduloMantenimiento/registrarMantenimientoCilindro.xhtml', 'x', 22),
(27, 'Estado mantenimiento', 'Maintenance State', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 22),
(28, 'Agenda', 'Agenda', '../../../moduloMantenimiento/registrarAgenda.xhtml', 'x', 23),
(29, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 23),
(30, 'Agenda', 'Agenda', '../../../moduloMantenimiento/listaAgenda.xhtml', 'x', 24),
(31, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 24),
(32, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/listaMantenimiento.xhtml', 'x', 24),
(33, 'Mantenimiento Cilindro', 'Cylinder Maintenace', '../../../moduloMantenimiento/listaMantenimientoCilindro.xhtml', 'x', 24),
(34, 'Estado Mantenimiento', 'Maintenance State', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 24),
(35, 'Personal', 'Employees', NULL, 'x', NULL),
(36, 'Registrar', 'Register', NULL, 'x', 35),
(37, 'Consultar', 'Consult', NULL, 'x', 35),
(38, 'Empleado', 'Employees', '../../../moduloPersonal/RegistrarEmpleado.xhtml', 'x', 36),
(39, 'Horario', 'Schedule', '../../../moduloPersonal/RegistrarHorario.xhtml', 'x', 36),
(40, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 36),
(41, 'Punto', 'Point', '../../../moduloPersonal/RegistrarPunto.xhtml', 'x', 36),
(42, 'Turno', 'Turn', '../../../moduloPersonal/RegistrarTurno.xhtml', 'x', 36),
(43, 'Empleado', 'Employees', '../../../moduloPersonal/ListarEmpleado.xhtml', 'x', 37),
(44, 'Horario', 'Schedule', '../../../moduloPersonal/ListarHorario.xhtml', 'x', 37),
(45, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral.xhtml', 'x', 37),
(46, 'Punto', 'Point', '../../../moduloPersonal/ListarPunto.xhtml', 'x', 37),
(47, 'Turno', 'Turn', '../../../moduloPersonal/ListarTurno.xhtml', 'x', 37),
(48, '---- Jefe de planta -----', '', ' ', 'x', NULL),
(49, 'Envios', 'Shipping', NULL, 'x', NULL),
(52, 'Crear', 'Create', '', 'x', 49),
(53, 'Consultar', 'Consult', NULL, 'x', 49),
(54, 'Programar', 'Point', NULL, 'x', 49),
(55, 'Usuarios', 'User', '../../../moduloEnvios/crearUsuario.xhtml', 'x', 52),
(56, 'Cliente', 'Custumer', '../../../moduloEnvios/crearCliente.xhtml', 'x', 52),
(57, 'Alquiler', 'Rent', '../../../moduloEnvios/crearAlquiler.xhtml', 'x', 54),
(58, 'Contrato', 'Contract', '../../../moduloEnvios/crearContrato.xhtml', 'x', 52),
(59, 'Ruta', 'Route', '../../../moduloEnvios/crearRuta.xhtml', 'x', 54),
(60, 'Vehiculo', 'Vehicle', '../../../moduloEnvios/crearVehiculo.xhtml', 'x', 52),
(61, 'Usuarios', 'User', '../../../moduloEnvios/listaUsuario.xhtml', 'x', 53),
(62, 'Cliente', 'Custumer', '../../../moduloEnvios/listaClientes.xhtml', 'x', 53),
(63, 'Alquiler', 'Rent', '../../../moduloEnvios/listaAlquiler.xhtml', 'x', 53),
(64, 'Contrato', 'Contract', '../../../moduloEnvios/listaContrato.xhtml', 'x', 53),
(65, 'Ruta', 'Route', '../../../moduloEnvios/listaRuta.xhtml', 'x', 53),
(67, 'Vehiculo', 'Vehicle', '../../../moduloEnvios/listaVehiculos.xhtml', 'x', 53),
(68, 'Mantenimiento', 'Maintenance', NULL, 'x', NULL),
(69, 'Programa', 'Program', NULL, 'x', 68),
(70, 'Registra', 'Register', NULL, 'x', 68),
(71, 'Consulta', 'Consult', NULL, 'x', 68),
(72, 'Mantenimiento Cilindro', 'Cylinder Maintenace', '../../../moduloMantenimiento/registrarMantenimientoCilindro.xhtml', 'x', 69),
(73, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/registrarMantenimiento.xhtml', 'x', 69),
(74, 'Estado Mantenimiento', 'Maintenance State', 'x-x-x-x-x--x-x--x-x--x-x--x-x--x-x-x--x-x--x-x-x-x--x', 'x', 69),
(75, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 70),
(76, 'Agenda', 'Agenda', '../../../moduloMantenimiento/registrarAgenda.xhtml', 'x', 70),
(77, 'Mantenimineto Cilindro', 'Cylinder Maintenace', '../../../moduloMantenimiento/listaMantenimientoCilindro.xhtml', 'x', 71),
(78, 'MAntenimiento', 'Maintenance', '../../../moduloMantenimiento/listaMantenimiento.xhtml', 'x', 71),
(79, 'Estado Mantenimiento', 'Maintenance State', 'x-x-x-x-x-x--x-x-x-x-x-x-x-x-x-x-x-x-x-x-', 'x', 71),
(80, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 71),
(81, 'Agenda', 'Agenda', '../../../moduloMantenimiento/listaAgenda.xhtml', 'x', 71),
(82, 'Personal', 'Employees', NULL, 'x', NULL),
(83, 'Registra', 'Register', NULL, 'x', 82),
(84, 'Consulta', 'Consult', NULL, 'x', 82),
(85, 'Turno', 'Turn', '../../../moduloPersonal/RegistrarTurno.xhtml', 'x', 83),
(86, 'Punto', 'Point', '../../../moduloPersonal/RegistrarPunto.xhtml', 'x', 83),
(87, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 83),
(88, 'Turno', 'Turn', '../../../moduloPersonal/ListarTurno.xhtml', 'x', 84),
(89, 'Punto', 'Point', '../../../moduloPersonal/ListarPunto.xhtml', 'x', 84),
(90, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral.xhtml', 'x', 84),
(91, 'Tecnico', 'Technical', NULL, 'x', NULL),
(92, 'Envios', 'Shipping', NULL, 'x', NULL),
(95, 'Consultar', 'Consult', NULL, 'x', 92),
(96, 'Alquiler', 'Rent', '../../../moduloEnvios/listaAlquiler.xhtml', 'x', 95),
(97, 'Ruta', 'Route', '../../../moduloEnvios/listaRuta.xhtml', 'x', 95),
(98, 'Vehiculo', 'Vehicle', '../../../moduloEnvios/listaVehiculos.xhtml', 'x', 95),
(99, 'Usuario', 'User', '../../../moduloEnvios/listaUsuario.xhtml', 'x', 95),
(100, 'Mantenimiento', 'Maintenance', '', 'x', NULL),
(101, 'Registrar', 'Register', NULL, 'x', 100),
(102, 'Consultar', 'Consult', NULL, 'x', 100),
(103, 'Agenda', 'Agenda', '../../../moduloMantenimiento/listaAgenda.xhtml', 'x', 102),
(104, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/listaMantenimiento.xhtml', 'x', 102),
(105, 'Mantenimiento Cilindro', 'Cylinder Maintenace', '../../../moduloMantenimiento/listaMantenimientoCilindro.xhtml', 'x', 102),
(106, 'Estado Cilindro', 'Cylinder State', 'x-x-x-x--x-x--x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 102),
(107, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 101),
(108, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 102),
(109, 'Personal', 'Employees', NULL, 'x', NULL),
(110, 'Registrar', 'Register', NULL, 'x', 109),
(111, 'Consultar', 'Consult', NULL, 'x', 109),
(112, 'Turno', 'Turn', '../../../moduloPersonal/ListarPunto.xhtml', 'x', 111),
(113, 'Punto', 'Point', '../../../moduloPersonal/ListarPunto.xhtml', 'x', 111),
(114, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral.xhtml', 'x', 111),
(115, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 110),
(118, 'Envios', 'Shipping', NULL, 'x', NULL),
(120, 'Personal', 'Employees', NULL, 'x', NULL),
(121, 'consultar', 'Consult', NULL, 'x', 118),
(122, 'Solicitud', 'Request', '../../../moduloEnvios/consultarSolicitudCliente.xhtml', 'x', 121),
(123, 'Alquiler', 'Rent', '../../../moduloEnvios/listaAlquiler.xhtml', 'x', 121),
(124, 'Contrato', 'Contract', '../../../moduloEnvios/listaContrato.xhtml', 'x', 121),
(125, 'Reportes', 'Reports', NULL, 'x', NULL),
(126, 'Graficos', 'Graphics', 'xx-x-x--x-x-x-x-x-x-x-x-x-x-x-x-x-x--x-x', 'x', 125),
(127, 'Pdf', 'Pdf', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 125),
(128, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/vistaControlador.xhtml', 'x', 126),
(131, 'Registrar', 'a', '', 'x', 118),
(132, 'Solicitud', 'a', '../../../moduloEnvios/crearSolicitudCliente.xhtml', 'x', 131),
(133, 'Envio masivo', 'Mass sending', '', 'x', 125),
(134, 'Mantenimiento del sistema', 'System maintenance', '../../../moduloPersonal/CorreoMantenimiento.xhtml', 'x', 133);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permiso_laboral`
--

CREATE TABLE `permiso_laboral` (
  `id_permiso_laboral` int(5) NOT NULL,
  `hora_permiso` time NOT NULL,
  `fecha_permiso` date NOT NULL,
  `obvservaciones` varchar(200) COLLATE utf32_spanish_ci DEFAULT NULL,
  `estado` int(2) NOT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `permiso_laboral`
--

INSERT INTO `permiso_laboral` (`id_permiso_laboral`, `hora_permiso`, `fecha_permiso`, `obvservaciones`, `estado`, `empleado_id_empleado`) VALUES
(1, '10:00:00', '2019-09-24', 'Cita medica', 1, 21),
(2, '14:30:00', '2019-09-28', 'Viaje', 1, 5),
(3, '07:00:00', '2019-09-26', 'Reunión en el colegio ', 1, 4),
(4, '15:00:00', '2019-09-28', 'Cita medica', 1, 4),
(5, '16:00:00', '2019-09-28', 'Reunión familiar ', 1, 4),
(6, '08:00:00', '2019-09-30', 'Cita medica', 1, 4),
(7, '17:00:00', '2019-10-02', 'Cumpleaños', 1, 21),
(8, '10:00:00', '2019-10-08', 'Reunión en el colegio ', 3, 4),
(9, '02:15:00', '2016-12-11', 'incapacidad', 1, 3),
(10, '12:15:00', '2016-12-11', 'incapacidad', 1, 3),
(11, '12:15:00', '2016-01-11', 'Cita Medica', 1, 3),
(12, '02:15:00', '2016-12-11', 'incapacidad', 1, 1),
(13, '05:00:30', '2016-01-11', 'Cita Medica', 1, 1),
(14, '05:00:30', '2016-12-11', 'Cita Medica', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `punto`
--

CREATE TABLE `punto` (
  `id_punto` int(3) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `descripcion` varchar(70) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `punto`
--

INSERT INTO `punto` (`id_punto`, `cantidad`, `descripcion`) VALUES
(1, 2, 'quince minutos'),
(2, 5, 'una hora'),
(3, 10, 'tres horas de permiso'),
(4, 15, 'cuatro horas de permiso'),
(5, 17, 'cinco horas de permiso'),
(6, 19, 'seis horas de permiso'),
(7, 21, 'un dia de permiso'),
(8, 0, 'tes horas de permiso en fin de semana'),
(9, 24, 'seis horas de permiso en fin de semana'),
(10, 27, 'un dia de permiso en fin de semana'),
(11, 29, 'un dia de permiso cuando lo solicite ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(1) NOT NULL,
  `rol` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `rol`) VALUES
(1, 'Administrador'),
(2, 'Cliente'),
(3, 'Jefe de planta'),
(4, 'Tecnico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_tiene_permiso`
--

CREATE TABLE `rol_tiene_permiso` (
  `rol_idRol` int(1) NOT NULL,
  `permiso_id_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `rol_tiene_permiso`
--

INSERT INTO `rol_tiene_permiso` (`rol_idRol`, `permiso_id_permiso`) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26),
(1, 27),
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 33),
(1, 34),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 41),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(1, 46),
(1, 47),
(1, 125),
(1, 126),
(1, 127),
(2, 118),
(3, 49),
(3, 53),
(3, 54),
(3, 55),
(3, 56),
(3, 57),
(3, 58),
(3, 59),
(3, 60),
(3, 61),
(3, 62),
(3, 63),
(3, 64),
(3, 65),
(3, 67),
(3, 68),
(3, 69),
(3, 70),
(3, 71),
(3, 72),
(3, 73),
(3, 74),
(3, 75),
(3, 76),
(3, 77),
(3, 78),
(3, 79),
(3, 80),
(3, 81),
(3, 82),
(3, 83),
(3, 84),
(3, 85),
(3, 86),
(3, 87),
(3, 88),
(3, 89),
(3, 90),
(4, 92),
(4, 95),
(4, 96),
(4, 97),
(4, 98),
(4, 99),
(4, 100),
(4, 101),
(4, 102),
(4, 103),
(4, 104),
(4, 105),
(4, 106),
(4, 107),
(4, 108),
(4, 109),
(4, 110),
(4, 111),
(4, 112),
(4, 113),
(4, 114),
(4, 115);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `id_ruta` int(2) NOT NULL,
  `direccion` varchar(40) COLLATE utf32_spanish_ci NOT NULL,
  `planilla` varchar(200) COLLATE utf32_spanish_ci NOT NULL,
  `vehiculo_id_vehiculo` int(2) NOT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`id_ruta`, `direccion`, `planilla`, `vehiculo_id_vehiculo`, `empleado_id_empleado`) VALUES
(1, 'Cll 26 Entre Cra 68 y Cra 7', 'Cll 25 No. 23 - 27', 3, 1),
(2, 'Cra. 44 No. 21 - 55', 'calera', 12, 2),
(3, 'cll 68 n 14 32', 'Fontibón', 3, 2),
(4, 'av 14 n 12 12', 'soacha', 9, 1),
(5, 'cll 101 n 70 26 ', 'toberin', 4, 21),
(6, 'Cll 45 entre carrera 17 y 32', 'clle', 1, 1),
(107, 'Cra. 44 No. 21 - 55', 'bosa', 7, 21),
(108, 'Cra. 46 No. 21 - 55', 'suba', 13, 3),
(109, 'Cra. 14 No. 21 - 55', 'usme', 10, 4),
(110, 'Cra. 24 No. 21 - 55', 'usme', 7, 21),
(111, 'Cra. 44 No. 21 - 50', 'usme', 11, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `id_solicitud` int(7) NOT NULL,
  `formula` text COLLATE utf32_spanish_ci NOT NULL,
  `tamanio_cilindro` int(5) DEFAULT NULL,
  `cliente_id_cliente` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `solicitud`
--

INSERT INTO `solicitud` (`id_solicitud`, `formula`, `tamanio_cilindro`, `cliente_id_cliente`) VALUES
(1, '2', 250, 3),
(2, '/proyectoOMC/archivos/Tarea 2. Fundamentos_php.pdf', 32, 7),
(3, '/proyectoOMC/archivos/Tarea 2. Fundamentos_php.pdf', 32, 7),
(5, '/proyectoomc/archivos/tabla (3).xls', 12332, 6),
(6, '/proyectoomc/archivos/Grupo 1.txt', 250, 8),
(7, '/proyectoomc/archivos/4.txt', 250, 9),
(8, '/proyectoomc/archivos/1.csv', NULL, 1),
(9, '/proyectoomc/archivos/1.csv', NULL, 9),
(10, '/proyectoomc/archivos/GC-F-004_Formato_Plantilla_sustentacionF1 (1).pptx', NULL, 9),
(11, '/proyectoomc/archivos/1.csv', NULL, 9),
(12, '/proyectoomc/archivos/1.csv', NULL, 9),
(13, '/proyectoomc/archivos/CASO DE USO MODULO 3 CLASIFICACION.docx', NULL, 9),
(14, '/proyectoomc/archivos/', NULL, 9),
(15, '/proyectoomc/archivos/1.csv', NULL, 9),
(16, '/proyectoomc/archivos/GUIA_APRENDIZAJE 2.doc.docx', NULL, 9),
(17, '/proyectoomc/archivos/ev1_plantillastakeholders (1) (1).xlsx', NULL, 9),
(18, 'x2', NULL, 8),
(19, 'sa', NULL, 7),
(45, '/proyectoomc/archivos/UNIDAD 3.pdf', NULL, 1),
(46, '/proyectoomc/archivos/UNIDAD 3.pdf', NULL, 1),
(47, '/proyectoomc/archivos/Material_Formacion_3.pdf', 250, 1),
(48, '/proyectoomc/archivos/UNIDAD 3.pdf', 300, 11);

--
-- Disparadores `solicitud`
--
DELIMITER $$
CREATE TRIGGER `eliminarSolicitud` AFTER UPDATE ON `solicitud` FOR EACH ROW BEGIN
update cliente SET cliente.estado_id_estado = 1 where old.cliente_id_cliente = id_cliente;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `eliminarSolicitudEstado` AFTER DELETE ON `solicitud` FOR EACH ROW BEGIN
update cliente SET cliente.estado_id_estado = 1 where old.cliente_id_cliente = id_cliente;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertarNuevoEstadoSolicitud` AFTER INSERT ON `solicitud` FOR EACH ROW BEGIN
update cliente SET cliente.estado_id_estado = 3 where new.cliente_id_cliente = id_cliente;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoid`
--

CREATE TABLE `tipoid` (
  `idTipoID` int(2) NOT NULL,
  `tipoID` varchar(30) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `tipoid`
--

INSERT INTO `tipoid` (`idTipoID`, `tipoID`) VALUES
(1, 'Cédula de ciudadanía'),
(2, 'Tarjeta de identidad'),
(3, 'Pasaporte'),
(4, 'Cédula de extranjería ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE `turno` (
  `id_turno` int(4) NOT NULL,
  `programacion` varchar(30) COLLATE utf32_spanish_ci NOT NULL,
  `hora_ingreso` time NOT NULL,
  `horas_salida` time NOT NULL,
  `fecha_inicial` date NOT NULL,
  `fecha_final` date NOT NULL,
  `observaciones` varchar(70) COLLATE utf32_spanish_ci NOT NULL,
  `punto_id_puntos` int(3) NOT NULL,
  `id_empleado` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `programacion`, `hora_ingreso`, `horas_salida`, `fecha_inicial`, `fecha_final`, `observaciones`, `punto_id_puntos`, `id_empleado`) VALUES
(2, 'Entregas', '00:06:06', '00:01:15', '2001-03-20', '2003-06-13', 'Entregas', 2, 4),
(3, 'Mantenimiento', '06:00:00', '12:00:00', '2019-09-25', '2019-09-25', 'Mantenimiento', 1, 5),
(4, 'Entregas', '10:00:00', '17:00:00', '2019-09-16', '2019-09-16', 'Entregas', 1, 5),
(5, 'Mantenimiento', '13:00:00', '21:00:00', '2019-09-09', '2019-09-09', 'Mantenimientos', 2, 4),
(6, 'Mantenimiento', '08:00:00', '18:00:00', '2019-09-25', '2019-09-25', 'Mantenimiento', 2, 2),
(7, 'Capacitacion', '13:00:00', '21:00:00', '2019-09-03', '2019-09-03', 'Capacitacion', 2, 3),
(8, 'Mantenimiento', '09:30:00', '19:30:00', '2019-09-26', '2019-09-26', 'Mantenimiento', 3, 4),
(9, 'Mantenimiento', '12:00:00', '20:00:00', '2019-09-02', '2019-09-02', 'Mantenimiento', 4, 3),
(10, 'Mantenimiento', '14:00:00', '22:00:00', '2019-07-03', '2019-07-03', 'Mantenimiento', 8, 2),
(11, 'Mantenimiento', '12:30:00', '17:30:00', '2019-09-01', '2019-09-01', 'Mantenimiento', 7, 3),
(12, 'Envios', '07:00:00', '17:00:00', '2019-07-03', '2019-07-05', 'Envíos', 5, 2),
(13, 'Envios', '12:00:00', '22:00:00', '2019-05-01', '2019-05-01', 'Envíos', 4, 2),
(14, 'Envíos', '14:00:00', '22:00:00', '2019-09-02', '2019-09-02', 'Envíos', 8, 21),
(15, 'mantenimiento', '05:10:10', '15:00:00', '2019-12-11', '2019-12-31', 'mantenimiento', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(10) NOT NULL,
  `tp_id` int(2) NOT NULL,
  `nombre` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `direccion` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `correo` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `contrasenia` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `telefono` bigint(10) NOT NULL,
  `eps` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `fecha_de_nacimiento` date NOT NULL,
  `edad` int(2) NOT NULL,
  `rol_idRol` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `tp_id`, `nombre`, `apellido`, `direccion`, `correo`, `contrasenia`, `telefono`, `eps`, `fecha_de_nacimiento`, `edad`, `rol_idRol`) VALUES
(23, 1, 'Salome', 'Diaz', 'cr 14 n 14 113', 'sbustos@misena.edu.co', '12345', 121212, 'Salud total', '1993-02-03', 26, 2),
(11111, 1, 'fxk84307@bcaoo.com', 'fxk84307@bcaoo.com', 'ffxk84307@bcaoo.com', 'ffxk84307@bcaoo.com', 'ffxk84307@bcaoo.com', 32125145, 'ffxk84307@bcaoo.com', '2019-09-07', 0, 2),
(32131, 1, 'c2991589@urhen.com', 'c2991589@urhen.com', 'c2991589@urhen.com', 'c2991589@urhen.com', 'c2991589@urhen.com', 320323, 'c2991589@urhen.com', '2019-09-18', 0, 2),
(54123, 1, 'Samuel', 'Villanueva', 'Cr 101- 70 26', 'patriciamongui397@gmail.com', 'VXPwnIpaCc', 12121234, 'Salud total', '2009-03-02', 7, 4),
(645452, 1, 'imhs947ds79@bcaoo.com', 'imhs947ds79@bcaoo.com', 'imhs947ds79@bcaoo.com', 'imhs947ds79@bcaoo.com', 'imhs947ds79@bcaoo.com', 51545, 'imhs947ds79@bcaoo.com', '2019-10-15', 0, 2),
(1111222, 1, 'assa', 'sasas', 'sasas', 'santyago3601@gmail.com', 'RiVGImysla', 123123, 'sa', '2001-02-02', 18, 2),
(10211425, 1, 'cwf44416@bcaoo.com', 'cwf44416@bcaoo.com', 'fcwf44416@bcaoo.com', 'fcwf44416@bcaoo.com', 'fcwf44416@bcaoo.com', 533514654, 'fcwf44416@bcaoo.com', '2007-01-16', 12, 2),
(15321541, 1, 'sdimhs947ds79@bcaoo.com', 'sdimhs947ds79@bcaoo.com', 'sdimhs947ds79@bcaoo.com', 'sdimhs947ds79@bcaoo.com', 'sdimhs947ds79@bcaoo.com', 534564, 'sdimhs947ds79@bcaoo.com', '2019-09-08', 0, 2),
(23874610, 2, 'Julie', 'Pott Friedman', 'Cll 32 sur No. 32 - 77', 'shirleybernal70@gmail.com', 'URgbaHRhdl', 4169210, 'Famisanar', '1990-03-29', 29, 3),
(23879120, 2, 'Mark David ', 'Stewart King', 'Cra. 44 No. 21 - 55', 'saquintero074@misena.edu.co', '1234', 3108971923, 'Sanitas', '1979-07-15', 39, 2),
(24981099, 2, 'Mark Christopher', 'Green Adams', 'Kra 19 No. 210 - 23', 'stephannybustos@gmail.com', 'KwIkhXyOkb', 4774147, 'Cafam EPS.', '1997-05-05', 22, 4),
(27871927, 1, 'Thomas', 'Miller King', 'av 68 n 14- 32', 'dybautista2@misena.edu.co', '3232', 12121230, 'Cafe salud', '1989-12-19', 30, 4),
(41521452, 1, 'imh94779@bcaoo.com', 'imh94779@bcaoo.com', 'imh94779@bcaoo.com', 'imh94779@bcaoo.com', 'imh94779@bcaoo.com', 56546, 'imh94779@bcaoo.com', '2019-09-10', 0, 2),
(100020251, 1, 'aaaaa', 'aaaaaa', 'algo', 'juanx@gmail.com', '123454', 456135, 'No tiene', '2012-03-02', 7, 2),
(100030122, 1, 'Juan Camilo', 'Perez Martinez', 'Correo@mail.com', 'santyago3601@gmail.com', '1234', 122, 'No tiene', '2001-06-01', 18, 2),
(1000205411, 1, 'c3081485@urhen.com', 'c3081485@urhen.com', 'c3081485@urhen.com', 'c3081485@urhen.com', 'c3081485@urhen.com', 415415615, 'Aliansalud EPS', '1997-06-16', 22, 2),
(1000604688, 2, 'Santiago', 'Ruiz RincÃ³n', 'cLL X', 'santyago3601@gmail.com', '12345', 4153134, 'NO', '2001-03-05', 18, 1),
(1000624311, 2, 'Carol', 'Martinez', 'Cra 13 No. 123 - 23', 'carolbustos1@gmail.com ', '1234', 4139385, 'Colmedica', '1980-06-03', 39, 2),
(1015410897, 1, 'Emili', 'Smith ', 'Cll 24 No. 31 -  34', 'jprodriguez744@misena.edu.co', '454545', 4135331, 'Nueva Eps', '1989-07-12', 29, 2),
(10002010212, 1, 'Camila', 'Perez', 'Cll x 24 -  23', 'stbernal0@misena.edu.co', '12345', 12345, 'Salud total', '2011-04-02', 8, 2),
(102154151515, 1, 'wim33398@bcaoo.com', 'wim33398@bcaoo.com', 'wim33398@bcaoo.com', 'wim33398@bcaoo.com', 'wim33398@bcaoo.com', 15154152415, 'Universidad Industrial de Santander', '2011-01-16', 8, 2);

--
-- Disparadores `usuario`
--
DELIMITER $$
CREATE TRIGGER `insertarEdad` BEFORE INSERT ON `usuario` FOR EACH ROW BEGIN
  SET NEW.edad = sf_edad(new.fecha_de_nacimiento);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `insertarNuevoCliente` AFTER INSERT ON `usuario` FOR EACH ROW BEGIN
if new.rol_idRol = 2 then
insert into cliente (estado_id_estado, usuario_id) values (1, new.id);
end if;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id_vehiculo` int(2) NOT NULL,
  `matricula` varchar(10) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`id_vehiculo`, `matricula`) VALUES
(1, 'BOG-154'),
(2, 'BOG-264'),
(3, 'BOG-219'),
(4, 'SOR-419'),
(5, 'SOR-221'),
(6, 'AGD-390'),
(7, 'AGD-391'),
(8, 'AGD-392'),
(9, 'AGD-360'),
(10, 'ATD-190'),
(11, 'ASD-330'),
(12, 'AMD-350'),
(13, 'AGR-391'),
(14, 'AZD-290');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`);

--
-- Indices de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD KEY `cilindro_id_cilindro` (`cilindro_id_cilindro`),
  ADD KEY `ruta_id_ruta` (`ruta_id_ruta`),
  ADD KEY `solicitud_id_solicitud` (`solicitud_id_solicitud`),
  ADD KEY `estado_alquiler_id_estado` (`estado_alquiler_id_estado`);

--
-- Indices de la tabla `cilindro`
--
ALTER TABLE `cilindro`
  ADD PRIMARY KEY (`id_cilindro`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `fk_cliente_usuario` (`usuario_id`),
  ADD KEY `estado_id_estado` (`estado_id_estado`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`id_contrato`),
  ADD KEY `alquiler_id_alquiler` (`alquiler_id_alquiler`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `fk_estado` (`fk_estado`);

--
-- Indices de la tabla `estado_alquiler`
--
ALTER TABLE `estado_alquiler`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estado_cliente`
--
ALTER TABLE `estado_cliente`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estado_empleado`
--
ALTER TABLE `estado_empleado`
  ADD PRIMARY KEY (`id_estado_empleado`);

--
-- Indices de la tabla `estado_mantenimiento`
--
ALTER TABLE `estado_mantenimiento`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `estado_permiso_laboral`
--
ALTER TABLE `estado_permiso_laboral`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`);

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `agenda_id_agenda` (`agenda_id_agenda`),
  ADD KEY `estado_mantenimiento` (`estado_mantenimiento`);

--
-- Indices de la tabla `mantenimiento_cilindro`
--
ALTER TABLE `mantenimiento_cilindro`
  ADD PRIMARY KEY (`id_mantenimiento_cilindro`),
  ADD KEY `mantenimiento_id_mantenimiento` (`mantenimiento_id_mantenimiento`),
  ADD KEY `cilindro_id_cilindro` (`cilindro_id_cilindro`);

--
-- Indices de la tabla `permiso`
--
ALTER TABLE `permiso`
  ADD PRIMARY KEY (`id_permiso`),
  ADD KEY `permiso_id_permiso` (`permiso_padre`);

--
-- Indices de la tabla `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  ADD PRIMARY KEY (`id_permiso_laboral`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `punto`
--
ALTER TABLE `punto`
  ADD PRIMARY KEY (`id_punto`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `rol_tiene_permiso`
--
ALTER TABLE `rol_tiene_permiso`
  ADD PRIMARY KEY (`rol_idRol`,`permiso_id_permiso`),
  ADD KEY `rol_idRol` (`rol_idRol`,`permiso_id_permiso`),
  ADD KEY `permiso_id_permiso` (`permiso_id_permiso`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`id_ruta`),
  ADD KEY `fk_ruta_empleado` (`empleado_id_empleado`),
  ADD KEY `fk_ruta_vehiculo` (`vehiculo_id_vehiculo`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `cliente_id_cliente` (`cliente_id_cliente`);

--
-- Indices de la tabla `tipoid`
--
ALTER TABLE `tipoid`
  ADD PRIMARY KEY (`idTipoID`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id_turno`),
  ADD KEY `punto_id_puntos` (`punto_id_puntos`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rol_idRol` (`rol_idRol`),
  ADD KEY `tp_id` (`tp_id`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id_vehiculo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `id_alquiler` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `id_contrato` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `estado_alquiler`
--
ALTER TABLE `estado_alquiler`
  MODIFY `id_estado` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id_mantenimiento` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  MODIFY `id_permiso_laboral` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_ruta` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_agenda_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `fk_alquiler_cilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_ruta` FOREIGN KEY (`ruta_id_ruta`) REFERENCES `ruta` (`id_ruta`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_solicitud` FOREIGN KEY (`solicitud_id_solicitud`) REFERENCES `solicitud` (`id_solicitud`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_estado_alquiler` FOREIGN KEY (`estado_alquiler_id_estado`) REFERENCES `estado_alquiler` (`id_estado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_estado` FOREIGN KEY (`estado_id_estado`) REFERENCES `estado_cliente` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_contrato_alquiler` FOREIGN KEY (`alquiler_id_alquiler`) REFERENCES `alquiler` (`id_alquiler`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleado_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_estado_id_estado` FOREIGN KEY (`fk_estado`) REFERENCES `estado_empleado` (`id_estado_empleado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `fk_horario_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `fk_estado_mantenimiento` FOREIGN KEY (`estado_mantenimiento`) REFERENCES `estado_mantenimiento` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mantenimiento_agenda` FOREIGN KEY (`agenda_id_agenda`) REFERENCES `agenda` (`id_agenda`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mantenimiento_cilindro`
--
ALTER TABLE `mantenimiento_cilindro`
  ADD CONSTRAINT `fk_mantenimiento_tiene_cilindro_idCilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mantenimiento_tiene_cilindro_idMantenimiento` FOREIGN KEY (`mantenimiento_id_mantenimiento`) REFERENCES `mantenimiento` (`id_mantenimiento`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `permiso`
--
ALTER TABLE `permiso`
  ADD CONSTRAINT `fk_permiso_permiso1` FOREIGN KEY (`permiso_padre`) REFERENCES `permiso` (`id_permiso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  ADD CONSTRAINT `fk_permiso_estado_permiso` FOREIGN KEY (`estado`) REFERENCES `estado_permiso_laboral` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_permiso_laboral_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `rol_tiene_permiso`
--
ALTER TABLE `rol_tiene_permiso`
  ADD CONSTRAINT `fk_ROL_TIENE_PERMISOS_PERMISOS` FOREIGN KEY (`permiso_id_permiso`) REFERENCES `permiso` (`id_permiso`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ROL_TIENE_PERMISOS_ROL` FOREIGN KEY (`rol_idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `fk_ruta_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ruta_vehiculo` FOREIGN KEY (`vehiculo_id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_cliente` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `fk_turno_punto` FOREIGN KEY (`punto_id_puntos`) REFERENCES `punto` (`id_punto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_tp_id` FOREIGN KEY (`tp_id`) REFERENCES `tipoid` (`idTipoID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
