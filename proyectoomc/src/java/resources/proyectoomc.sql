-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2019 a las 14:00:11
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

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
  `foto` text COLLATE utf32_spanish_ci NOT NULL,
  `empleado_id_empleado` int(3) NOT NULL,
  `alquiler_id_alqu` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT INTO `agenda` (`id_agenda`, `fecha_programada`, `novedades`, `foto`, `empleado_id_empleado`, `alquiler_id_alqu`) VALUES
(1, '2019-09-26', 'Mantenimiento', '', 1, 4),
(2, '2019-09-25', 'Envios', '', 5, 4),
(3, '2019-09-30', 'Mantenimiento', '', 2, 4),
(4, '2019-09-25', 'Recolecta de cilindros', '', 4, 4),
(5, '2019-09-26', 'Instalacion', '', 21, 4),
(6, '2019-10-02', 'Envios', '', 4, 4),
(7, '2019-10-02', 'Capacitación', '', 3, 4),
(8, '2019-10-03', 'Capacitación', '', 4, 4),
(9, '2019-10-01', 'Capacitación', '', 2, 4),
(10, '2019-09-25', 'Capacitación', '', 21, 4),
(11, '2019-10-16', 'Capacitación', '', 3, 4),
(12, '2019-10-31', 'Falta de pintura', '/proyectoomc/archivos/', 4, 8),
(13, '2019-11-13', 'Falta de pintura', '/proyectoomc/archivos/1482-Article Text-3592-2-10-20170131.pdf', 5, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `id_alquiler` int(10) NOT NULL,
  `fecha_de_entrega` date NOT NULL,
  `novedades` varchar(300) COLLATE utf32_spanish_ci NOT NULL,
  `ruta_id_ruta` int(2) NOT NULL,
  `cilindro_id_cilindro` int(7) NOT NULL,
  `solicitud_id_solicitud` int(7) NOT NULL,
  `estado_alquiler_id_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `alquiler`
--

INSERT INTO `alquiler` (`id_alquiler`, `fecha_de_entrega`, `novedades`, `ruta_id_ruta`, `cilindro_id_cilindro`, `solicitud_id_solicitud`, `estado_alquiler_id_estado`) VALUES
(4, '2002-01-26', 'No', 5, 1060294, 11, 3),
(5, '2019-01-08', 'Ninguna', 1, 1060293, 1, 1),
(8, '2019-01-08', 'No', 1, 1060294, 7, 2),
(9, '2019-10-22', 'No', 1, 1060293, 60, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `id` int(7) NOT NULL,
  `registro` text COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria_mantenimiento`
--

CREATE TABLE `auditoria_mantenimiento` (
  `Id_AudMant` int(11) NOT NULL,
  `id_mantenimiento` int(11) DEFAULT NULL,
  `Antigua_fechaInicioMantenimiento` date DEFAULT NULL,
  `Antigua_fechaFinalMantenimiento` date DEFAULT NULL,
  `Antigua_TipoMantenimiento` varchar(50) COLLATE utf32_spanish_ci DEFAULT NULL,
  `id_agenda` int(11) DEFAULT NULL,
  `id_estado` int(11) DEFAULT NULL,
  `id_cilindro` int(11) DEFAULT NULL,
  `UsuarioAuditor` varchar(45) COLLATE utf32_spanish_ci DEFAULT NULL,
  `FechaAuditoria` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `auditoria_mantenimiento`
--

INSERT INTO `auditoria_mantenimiento` (`Id_AudMant`, `id_mantenimiento`, `Antigua_fechaInicioMantenimiento`, `Antigua_fechaFinalMantenimiento`, `Antigua_TipoMantenimiento`, `id_agenda`, `id_estado`, `id_cilindro`, `UsuarioAuditor`, `FechaAuditoria`) VALUES
(1, 114, '2019-10-09', '2019-10-30', 'preventivo', 2, 2, 1060298, 'root@localhost', '2019-10-10'),
(2, 876, '2019-11-28', '2019-11-29', 'Preventivo', 9, 2, 1060300, 'root@localhost', '2019-11-26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cilindro`
--

CREATE TABLE `cilindro` (
  `id_cilindro` int(7) NOT NULL,
  `tamanio` int(3) NOT NULL,
  `lote` int(8) NOT NULL,
  `fecha_de_creacion` date NOT NULL,
  `estado_id_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `cilindro`
--

INSERT INTO `cilindro` (`id_cilindro`, `tamanio`, `lote`, `fecha_de_creacion`, `estado_id_estado`) VALUES
(120065, 500, 12345, '2019-11-01', 1),
(1060293, 250, 3102249, '2019-06-02', 1),
(1060294, 250, 3102249, '2019-06-02', 1),
(1060295, 300, 3102249, '2019-06-02', 1),
(1060296, 300, 3102208, '2019-06-09', 1),
(1060297, 250, 3102208, '2019-06-09', 1),
(1060298, 300, 3102208, '2019-06-09', 1),
(1060299, 200, 3102208, '2019-06-09', 1),
(1060300, 250, 3102208, '2019-06-09', 1);

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
(1, 10201021, 1),
(3, 23874610, 3),
(6, 23879120, 3),
(7, 10154108, 3),
(9, 10006243, 3),
(11, 10002025, 3),
(13, 10211425, 1),
(17, 15321541, 1),
(18, 10002054, 3),
(20, 10002102, 3),
(21, 10002012, 3),
(22, 23456789, 3),
(23, 43243213, 3),
(24, 213213, 3),
(26, 10003012, 4),
(27, 23294719, 1),
(28, 213213, 1),
(29, 1007296769, 1),
(30, 1000204011, 3),
(31, 1000033789, 1);

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
(10, 'Persona natural', '2019-11-10', '2019-12-10', 5),
(14, 'Hospital', '2019-10-06', '2019-10-30', 8),
(21, 'Hospital', '2019-10-06', '2019-10-30', 8);

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
(1, 1015456456, 1),
(2, 10201021, 1),
(3, 10003012, 1),
(4, 1000201266, 1),
(5, 24981099, 1),
(8, 213213, 1),
(9, 10003251, 1),
(10, 1111222, 1),
(11, 24981099, 1),
(12, 24981099, 1),
(13, 213213, 1),
(15, 213213, 1),
(21, 24981099, 1),
(22, 213213, 1),
(87, 10005015, 1);

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
-- Estructura de tabla para la tabla `estado_cilindro`
--

CREATE TABLE `estado_cilindro` (
  `id_estado` int(2) NOT NULL,
  `estado_col` varchar(50) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `estado_cilindro`
--

INSERT INTO `estado_cilindro` (`id_estado`, `estado_col`) VALUES
(1, 'Activo');

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
(17, '10:19', '10:19', 1, '2019-09-25', '2019-09-25'),
(26, '10:19', '10:19', 1, '2019-09-25', '2019-09-25'),
(42, '10:57', '9:48', 1, '2019-09-25', '2019-10-04'),
(43, '9:48', '9:48', 1, '2019-10-04', '2019-10-04'),
(44, '9:48', '9:59', 1, '2019-10-04', '2019-10-04'),
(45, '9:48', '9:48', 1, '2019-10-04', '2019-10-04'),
(46, '9:56', '9:56', 1, '2019-10-04', '2019-10-04'),
(47, '9:59', '9:59', 1, '2019-10-04', '2019-10-04'),
(48, '9:59', '9:59', 1, '2019-10-04', '2019-10-04'),
(50, '9:59', '9:59', 1, '2019-10-04', '2019-10-04'),
(51, '19:36', '19:36', 1, '2019-11-18', '2019-11-18'),
(52, '19:36', '19:36', 1, '2019-11-18', '2019-11-18'),
(53, '19:45', '19:45', 1, '2019-11-18', '2019-11-18'),
(54, '11:3', '11:3', 3, '2019-11-25', '2019-11-25'),
(55, '15:2', '15:2', 2, '2019-11-27', '2019-11-27'),
(56, '7:8', '7:8', 22, '2019-11-28', '2019-11-28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id_mantenimiento` int(7) NOT NULL,
  `fecha_inicio_mantenimiento` date NOT NULL,
  `fecha_final_mantenimiento` date NOT NULL,
  `tipo_mantenimiento` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
  `agenda_id_agenda` int(7) NOT NULL,
  `estado_mantenimiento` int(2) NOT NULL,
  `cilindro_id_cilindro` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `mantenimiento`
--

INSERT INTO `mantenimiento` (`id_mantenimiento`, `fecha_inicio_mantenimiento`, `fecha_final_mantenimiento`, `tipo_mantenimiento`, `agenda_id_agenda`, `estado_mantenimiento`, `cilindro_id_cilindro`) VALUES
(2, '2019-09-16', '2019-09-16', '', 6, 2, 1060293),
(3, '2019-09-25', '2019-09-25', '', 3, 2, 1060293),
(4, '2019-10-01', '2019-10-01', '', 4, 2, 1060293),
(5, '2019-09-28', '2019-09-28', '', 1, 2, 1060293),
(112, '2019-10-09', '2019-10-30', 'preventivo', 2, 2, 1060298),
(114, '2019-10-09', '2019-10-30', 'preventivo', 2, 2, 1060298),
(876, '2019-11-28', '2019-11-30', 'Preventivo', 9, 2, 1060300);

--
-- Disparadores `mantenimiento`
--
DELIMITER $$
CREATE TRIGGER `tg_AuditoriaMantenimiento` AFTER INSERT ON `mantenimiento` FOR EACH ROW BEGIN
INSERT INTO Auditoria_Mantenimiento(id_mantenimiento, Antigua_fechaInicioMantenimiento, Antigua_fechaFinalMantenimiento, Antigua_TipoMantenimiento, id_agenda, id_estado, id_cilindro, UsuarioAuditor, FechaAuditoria)
VALUES (NEW.id_mantenimiento, NEW.fecha_inicio_mantenimiento, NEW.fecha_final_mantenimiento, NEW.tipo_mantenimiento, NEW.agenda_id_agenda, NEW.estado_mantenimiento,NEW.cilindro_id_cilindro, CURRENT_USER(), NOW());
END
$$
DELIMITER ;

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
(28, 'Agenda', 'Agenda', '../../../moduloMantenimiento/registrarAgenda.xhtml', 'x', 23),
(29, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 23),
(30, 'Agenda', 'Agenda', '../../../moduloMantenimiento/listaAgenda.xhtml', 'x', 24),
(31, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 24),
(32, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/listaMantenimiento.xhtml', 'x', 24),
(35, 'Personal', 'Employees', NULL, 'x', NULL),
(36, 'Registrar', 'Register', NULL, 'x', 35),
(37, 'Consultar', 'Consult', NULL, 'x', 35),
(38, 'Empleado', 'Employees', '../../../moduloPersonal/RegistrarEmpleado.xhtml', 'x', 36),
(39, 'Horario', 'Schedule', '../../../moduloPersonal/RegistrarHorario.xhtml', 'x', 36),
(40, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 36),
(42, 'Turno', 'Turn', '../../../moduloPersonal/RegistrarTurno.xhtml', 'x', 36),
(43, 'Empleado', 'Employees', '../../../moduloPersonal/ListarEmpleado.xhtml', 'x', 37),
(44, 'Horario', 'Schedule', '../../../moduloPersonal/ListarHorario.xhtml', 'x', 37),
(45, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral1.xhtml', 'x', 37),
(47, 'Turno', 'Turn', '../../../moduloPersonal/ListarTurno1.xhtml', 'x', 37),
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
(73, 'Mantenimiento', 'Maintenance', '../../../moduloMantenimiento/registrarMantenimiento.xhtml', 'x', 69),
(75, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 70),
(76, 'Agenda', 'Agenda', '../../../moduloMantenimiento/registrarAgenda.xhtml', 'x', 70),
(78, 'MAntenimiento', 'Maintenance', '../../../moduloMantenimiento/listaMantenimiento.xhtml', 'x', 71),
(80, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 71),
(81, 'Agenda', 'Agenda', '../../../moduloMantenimiento/listaAgenda.xhtml', 'x', 71),
(82, 'Personal', 'Employees', NULL, 'x', NULL),
(83, 'Registra', 'Register', NULL, 'x', 82),
(84, 'Consulta', 'Consult', NULL, 'x', 82),
(85, 'Turno', 'Turn', '../../../moduloPersonal/RegistrarTurno.xhtml', 'x', 83),
(87, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 83),
(88, 'Turno', 'Turn', '../../../moduloPersonal/ListarTurno1.xhtml', 'x', 84),
(90, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral1.xhtml', 'x', 84),
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
(106, 'Estado Cilindro', 'Cylinder State', 'x-x-x-x--x-x--x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 102),
(107, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/registrarCilindro.xhtml', 'x', 101),
(108, 'Cilindro', 'Cylinder', '../../../moduloMantenimiento/listaCilindro.xhtml', 'x', 102),
(109, 'Personal', 'Employees', NULL, 'x', NULL),
(110, 'Registrar', 'Register', NULL, 'x', 109),
(111, 'Consultar', 'Consult', NULL, 'x', 109),
(112, 'Turno', 'Turn', '../../../moduloPersonal/ListarTurno.xhtml', 'x', 111),
(114, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/ListarPermisoLaboral.xhtml', 'x', 111),
(115, 'Permiso Laboral', 'Work permit', '../../../moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 110),
(118, 'Envios', 'Shipping', NULL, 'x', NULL),
(120, 'Personal', 'Employees', NULL, 'x', NULL),
(121, 'consultar', 'Consult', NULL, 'x', 118),
(122, 'Solicitud', 'Request', '../../../moduloEnvios/consultarSolicitudCliente.xhtml', 'x', 121),
(125, 'Reportes', 'Reports', NULL, 'x', NULL),
(131, 'Registrar', 'Register', '', 'x', 118),
(132, 'Solicitud', 'Request', '../../../moduloEnvios/crearSolicitudCliente.xhtml', 'x', 131),
(133, 'Envio masivo', 'Mass sending', '', 'x', 125),
(134, 'Mantenimiento del sistema', 'System maintenance', '../../../moduloPersonal/CorreoMantenimiento.xhtml', 'x', 133),
(135, 'Historial', 'Hystory', '../../../moduloEnvios/historialCliente.xhtml', 'x', 121),
(136, '- - - - - - - - - - - - - - - - - - -', '- - - - - - - - - - - - - - - - - - -', '- - - - - - - - - - - - - - - - - - -', 'x', NULL),
(137, 'Solicitud', 'Request', '', 'x', NULL),
(138, 'Consultar', 'Consult', '../../../moduloEnvios/consultarSolicitudCliente.xhtml', 'x', 137),
(139, 'Registrar', 'Register', '../../../moduloEnvios/crearSolicitudCliente.xhtml', 'x', 137),
(140, 'Envios', 'Shipping', NULL, 'x', NULL),
(141, 'Historial', 'Hystory', '../../../moduloEnvios/historialCliente.xhtml', 'x', 140),
(142, '', '', NULL, '', NULL);

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
(1, '10:00:00', '2019-09-24', 'Cita medica', 2, 21),
(2, '14:30:00', '2019-11-21', 'Viajejjjjj', 2, 2),
(3, '07:00:00', '2019-09-26', 'Reunión en el colegio ', 3, 4),
(4, '15:00:00', '2019-09-28', 'Cita medica', 2, 4),
(5, '16:00:00', '2019-09-28', 'Reunión familiar ', 2, 4),
(6, '08:00:00', '2019-09-30', 'Cita medica', 2, 4),
(7, '17:00:00', '2019-10-02', 'Cumpleaños', 2, 21),
(8, '10:00:00', '2019-10-08', 'Reunión en el colegio ', 3, 4),
(9, '02:15:00', '2016-12-11', 'incapacidad', 2, 3),
(10, '12:15:00', '2016-12-11', 'incapacidad', 3, 3),
(11, '12:15:00', '2016-01-11', 'Cita Medica', 1, 3),
(12, '02:15:00', '2016-12-11', 'incapacidad', 1, 1),
(13, '05:00:30', '2016-01-11', 'Cita Medica', 1, 1),
(14, '05:00:30', '2016-12-11', 'Cita Medica', 1, 1),
(15, '02:15:00', '2019-11-20', 'Cita Medica', 1, 2),
(16, '12:15:00', '2019-11-29', 'qweriii', 1, 5),
(17, '12:15:00', '2019-11-28', 'incapacidad', 1, 5),
(18, '05:00:30', '2019-11-28', 'qwerpp', 3, 5),
(19, '02:15:00', '2019-11-25', 'Cita Medica', 1, 9),
(20, '02:15:00', '2019-11-26', 'Cita Medica', 1, 9),
(21, '02:15:00', '2019-12-20', 'Viajes', 1, 1),
(22, '12:15:00', '2019-11-29', 'Cita Medica', 1, 1),
(23, '02:15:00', '2019-11-29', 'asdfgh', 1, 1),
(24, '05:00:30', '2019-11-28', 'asdfgh', 1, 1),
(25, '20:00:00', '2019-12-04', 'Cita Medica', 1, 21),
(26, '00:13:00', '2019-12-01', 'Cita Medica', 1, 13),
(27, '12:15:00', '2019-11-28', 'prueba', 1, 13),
(28, '01:00:00', '2019-11-29', 'Viaje', 2, 21),
(29, '02:15:00', '2019-11-29', 'Cita Medica', 1, 1),
(30, '03:30:00', '2019-11-29', 'Cita Medica', 1, 1);

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
(1, 28),
(1, 29),
(1, 30),
(1, 31),
(1, 32),
(1, 35),
(1, 36),
(1, 37),
(1, 38),
(1, 39),
(1, 40),
(1, 42),
(1, 43),
(1, 44),
(1, 45),
(1, 47),
(1, 125),
(2, 137),
(2, 140),
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
(3, 73),
(3, 75),
(3, 76),
(3, 78),
(3, 80),
(3, 81),
(3, 82),
(3, 83),
(3, 84),
(3, 85),
(3, 87),
(3, 88),
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
(4, 106),
(4, 107),
(4, 108),
(4, 109),
(4, 110),
(4, 111),
(4, 112),
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
(1, '/proyectoomc/archivos/cert_02.pdf', 250, 3),
(3, '/proyectoomc/archivos/cert_01.pdf', 300, 7),
(5, '/proyectoomc/archivos/cert_02.pdf', 200, 6),
(7, '/proyectoomc/archivos/cert_01.pdf', 250, 9),
(11, '/proyectoomc/archivos/cert_01.pdf', 400, 9),
(48, '/proyectoomc/archivos/cert_03.pdf', 300, 11),
(55, '/proyectoomc/archivos/cert_01.pdf', 300, 21),
(56, '/proyectoomc/archivos/cert_01.pdf', 300, 22),
(57, '/proyectoomc/archivos/cert_03.pdf', 450, 23),
(58, '/proyectoomc/archivos/cert_03.pdf', 400, 24),
(59, '/proyectoomc/archivos/10002102Inventario.pdf', 450, 20),
(60, '/proyectoomc/archivos/1000204011CertificadoDonacion.pdf', 400, 30);

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
  `id_empleado` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `programacion`, `hora_ingreso`, `horas_salida`, `fecha_inicial`, `fecha_final`, `observaciones`, `id_empleado`) VALUES
(2, 'Entregas', '00:06:06', '00:01:15', '2001-03-20', '2003-06-13', 'Entregas', 4),
(3, 'Mantenimiento', '06:00:00', '12:00:00', '2019-11-19', '2019-11-21', 'Mantenimientossss', 5),
(4, 'Entregas', '10:00:00', '17:00:00', '2019-09-16', '2019-09-16', 'Entregas', 5),
(5, 'Mantenimiento', '13:00:00', '21:00:00', '2019-09-09', '2019-09-09', 'Mantenimientos', 4),
(6, 'Mantenimiento', '08:00:00', '07:00:00', '2019-11-29', '2019-11-29', 'Mantenimiento', 2),
(7, 'Capacitacion', '13:00:00', '21:00:00', '2019-09-03', '2019-09-03', 'Capacitacion', 3),
(8, 'Mantenimiento', '09:30:00', '19:30:00', '2019-09-26', '2019-09-26', 'Mantenimiento', 4),
(9, 'Mantenimiento', '12:00:00', '20:00:00', '2019-09-02', '2019-09-02', 'Mantenimiento', 3),
(10, 'Mantenimiento', '14:00:00', '22:00:00', '2019-07-03', '2019-07-03', 'Mantenimiento', 2),
(11, 'Mantenimiento', '12:30:00', '17:30:00', '2019-09-01', '2019-09-01', 'Mantenimiento', 3),
(12, 'Envios', '07:00:00', '17:00:00', '2019-07-03', '2019-07-05', 'Envíos', 2),
(13, 'Envios', '12:00:00', '22:00:00', '2019-05-01', '2019-05-01', 'Envíos', 2),
(14, 'Envíos', '14:00:00', '22:00:00', '2019-09-02', '2019-09-02', 'Envíos', 21),
(15, 'mantenimiento', '05:10:10', '15:00:00', '2019-12-11', '2019-12-31', 'mantenimiento', 1),
(16, 'mantenimiento', '05:00:01', '15:00:00', '2019-11-28', '2019-12-11', 'mantenimiento', 1),
(17, 'mantenimiento', '05:10:10', '15:00:00', '2019-11-27', '2019-11-28', 'mantenimiento Cilindro', 9),
(18, 'mantenimiento', '05:10:10', '15:00:00', '2019-11-29', '2019-11-30', 'mantenimiento Cilindro', 1),
(19, 'mantenimiento', '05:10:10', '15:00:00', '2019-11-29', '2019-11-29', 'mantenimiento Cilindro', 22);

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
(213213, 1, 'Juan Marco', 'Arevalo Martinez', 'Cll 21 No. 53-54', 'kif69875@eveav.com', '1203451', 3112465132, 'Nueva EPS', '1979-09-05', 40, 4),
(1111222, 1, 'Jonh Michael', 'Lopez Jimenez', 'Cll 99 No. 31-24', 'jersonchitan00@gmail.com', 'RiVGImysla', 3119775486, 'Nueva EPS', '2001-02-02', 18, 4),
(10002012, 1, 'Jonh Michael', 'Mendoza Castiblanco', 'chl60957@bcaoo.com', 'chl60957@bcaoo.com', 'chl60957@bcaoo.com', 1212121212, 'Compensar', '1953-11-29', 65, 2),
(10002014, 1, 'Monica Bibiana', 'Mendoza Castellanos', 'Cll x No 14-55', 'mbmendoza6@misena.edu.co', 'claveM123456789', 4123521, 'Aliansalud EPS', '1999-01-12', 20, 1),
(10002025, 1, 'Carol', 'Perez Martinez', 'Cr 101- 70 26', 'juanx@gmail.com', '123454', 3115479652, 'Cafam EPS.', '2012-03-02', 7, 2),
(10002054, 1, 'Juan Camilo', 'Stewart King', 'Cr 101- 70 26', 'c3081485@urhen.com', 'c3081485@urhen.com', 415415615, 'Aliansalud EPS', '1997-06-16', 22, 2),
(10002102, 1, 'Liliana Andrea', 'Bedoya Solanilla', 'Cll 24 No. 31 -  34', 'liandreab2sol@gmail.com', 'claveL123456789', 3112446524, 'Aliansalud EPS', '1999-01-12', 20, 2),
(10003012, 1, 'Juan Camilo', 'Perez Martinez', 'Cr 101- 70 26', 'liandreab2sol@gmail.com', '1234', 122, 'Cafam EPS.', '2001-06-01', 18, 4),
(10003251, 1, 'Nelson Hernan', 'Rodriguez Ayala', 'Cra 13 No. 123 - 23', 'nhrodrigueza@misena.edu.co', 'claveN123456789', 4135331, 'Aliansalud EPS', '1999-01-12', 20, 1),
(10005015, 1, 'Felix Eduardo', 'Barahona Romero', 'Cr 101- 70 26', 'felixbarahona@misena.edu.co', 'claveF123456789', 3112997465, 'Aliansalud EPS', '1999-01-12', 20, 3),
(10006046, 2, 'Santiago', 'Ruiz RincÃ³n', 'cLL X', 'santyago3601@gmail.com', '12345', 4153134, 'Aliansalud EPS', '2001-03-05', 18, 1),
(10006243, 2, 'Carol', 'Martinez', 'Cra 13 No. 123 - 23', 'carolbustos1@gmail.com ', '1234', 4139385, 'Colmedica', '1980-06-03', 39, 2),
(10154108, 1, 'Emili', 'Smith ', 'Cll 24 No. 31 -  34', 'jprodriguez744@misena.edu.co', '454545', 4135331, 'Nueva Eps', '1989-07-12', 29, 2),
(10201021, 1, 'Camila', 'Perez', 'Cll x 24 -  23', 'stbernal0@misena.edu.co', '12345', 12345, 'Salud total', '2011-04-02', 8, 2),
(10211425, 1, 'Marina Yolanda', 'Pedraza Gonzalez', 'Cra 21 No. 45-65', 'fcwf44416@bcaoo.com', '5415fd', 3224119851, 'Comfacundi - CCF de Cundinamarca', '2007-01-16', 12, 2),
(12121210, 1, 'Maria Elvira', 'Hernandez', 'Cll x 24 -  23', 'mlyndaortiz@gmail.com', 'claveM121234', 232323, 'Salud total', '2019-02-05', 0, 4),
(15321541, 1, 'Thomas ', 'Quintana Duarte', 'Dg 23 No. 12-23 Sur', 'sdimhs947ds79@bcaoo.com', '15151fdd', 3112447512, 'Comfacundi - CCF de Cundinamarca', '2019-09-08', 0, 2),
(23294719, 2, 'Salome', 'Diaz duarte', 'cr 14 n 14 113', 'sbustos@misena.edu.co', '546789', 3115447952, 'Comfacundi - CCF de Cundinamarca', '1993-02-02', 26, 2),
(23456789, 3, 'Pablo ', 'Moneros Renteria', 'Cra 21 No. 45-65', 'lvu68354@eveav.com', '324356jhj3', 4153265, 'Comfenalco Valle EPS', '2012-05-09', 7, 2),
(23874610, 2, 'Julie', 'Pott Friedman', 'Cll 32 sur No. 32 - 77', 'shirleybernal70@gmail.com', 'URgbaHRhdl', 4781236, 'Famisanar', '1990-03-29', 29, 3),
(23879120, 2, 'Mark David ', 'Stewart King', 'Cra. 44 No. 21 - 55', 'saquintero074@misena.edu.co', '1234', 4157989, 'Sanitas', '1979-07-15', 39, 2),
(24981099, 2, 'Mark Christopher', 'Green Adams', 'Kra 19 No. 210 - 23', 'stephannybustos@gmail.com', 'KwIkhXyOkb', 4774147, 'Cafam EPS.', '1997-05-05', 22, 4),
(27451749, 1, 'Samuel', 'Villanueva', 'Cr 101- 70 26', 'patriciamongui397@gmail.com', 'VXPwnIpaCc', 4235154, 'Salud total', '2009-03-02', 7, 4),
(27871927, 1, 'Thomas', 'Miller King', 'av 68 n 14- 32', '4n1ma98@gmail.com', '3232', 4151815, 'Cafe salud', '1989-12-19', 30, 4),
(43243213, 1, 'Miguel Alonso', 'Escobar Martinez', 'Cll 21 no. 12-32', 'tli22651@bcaoo.com', 'tli22651@bcaoo.com', 131243245, 'Comfacundi - CCF de Cundinamarca', '1962-06-11', 57, 2),
(1000033789, 1, 'Santiago', 'Reyes', 'Calle 145  Autopista Norte', 'Sreyes98@misena.edu.co', '123456789', 3228565504, 'E.P.S Sanitas', '2001-03-11', 18, 2),
(1000201266, 1, 'Maria Linda', 'Ortiz Baez', 'Cll 22 C No. 24-21', 'mlyndaortiz@gmail.com', 'claveM123456789', 3152249852, 'Aliansalud EPS', '1999-01-12', 20, 3),
(1000204011, 1, 'Juan pablo', 'Murcia', 'Cll 234 - 24', 'gbm35439@eveav.com', '12345678', 31152246325, 'Capital Salud EPSS S.A.S.', '2019-06-30', 0, 2),
(1007296769, 1, 'Jonathan', 'patiÃ±o', 'Cl 124-10', 'japatino967@misena.edu.co', 'Jonatan1234', 3135216393, 'Cafesalud EPS', '2019-10-03', 0, 2),
(1015456456, 1, 'Tomas', 'Tomas', 'av 13', 'correo@correo', '123456', 45454545, 'Nueva EPS', '2001-02-02', 18, 4);

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
(1, 'BOG-155'),
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
(14, 'AZD-290'),
(16, 'JHD-241');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`),
  ADD KEY `alquiler_id_alqu` (`alquiler_id_alqu`);

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
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `auditoria_mantenimiento`
--
ALTER TABLE `auditoria_mantenimiento`
  ADD PRIMARY KEY (`Id_AudMant`),
  ADD KEY `id_mantenimiento` (`id_mantenimiento`),
  ADD KEY `id_agenda` (`id_agenda`),
  ADD KEY `id_estado` (`id_estado`),
  ADD KEY `id_cilindro` (`id_cilindro`);

--
-- Indices de la tabla `cilindro`
--
ALTER TABLE `cilindro`
  ADD PRIMARY KEY (`id_cilindro`),
  ADD KEY `estado_id_estado` (`estado_id_estado`);

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
-- Indices de la tabla `estado_cilindro`
--
ALTER TABLE `estado_cilindro`
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
  ADD KEY `estado_mantenimiento` (`estado_mantenimiento`),
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
  MODIFY `id_agenda` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `id_alquiler` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `auditoria_mantenimiento`
--
ALTER TABLE `auditoria_mantenimiento`
  MODIFY `Id_AudMant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `id_contrato` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `estado_alquiler`
--
ALTER TABLE `estado_alquiler`
  MODIFY `id_estado` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id_mantenimiento` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=877;

--
-- AUTO_INCREMENT de la tabla `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  MODIFY `id_permiso_laboral` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_ruta` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=112;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_agenda_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquilier_id_alquiler` FOREIGN KEY (`alquiler_id_alqu`) REFERENCES `alquiler` (`id_alquiler`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `fk_alquiler_cilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_ruta` FOREIGN KEY (`ruta_id_ruta`) REFERENCES `ruta` (`id_ruta`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_solicitud` FOREIGN KEY (`solicitud_id_solicitud`) REFERENCES `solicitud` (`id_solicitud`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_estado_alquiler` FOREIGN KEY (`estado_alquiler_id_estado`) REFERENCES `estado_alquiler` (`id_estado`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `auditoria_mantenimiento`
--
ALTER TABLE `auditoria_mantenimiento`
  ADD CONSTRAINT `auditoria_mantenimiento_ibfk_1` FOREIGN KEY (`id_mantenimiento`) REFERENCES `mantenimiento` (`id_mantenimiento`),
  ADD CONSTRAINT `auditoria_mantenimiento_ibfk_2` FOREIGN KEY (`id_agenda`) REFERENCES `agenda` (`id_agenda`),
  ADD CONSTRAINT `auditoria_mantenimiento_ibfk_3` FOREIGN KEY (`id_estado`) REFERENCES `estado_mantenimiento` (`id_estado`),
  ADD CONSTRAINT `auditoria_mantenimiento_ibfk_4` FOREIGN KEY (`id_cilindro`) REFERENCES `cilindro` (`id_cilindro`);

--
-- Filtros para la tabla `cilindro`
--
ALTER TABLE `cilindro`
  ADD CONSTRAINT `fk_estado_cilindro` FOREIGN KEY (`estado_id_estado`) REFERENCES `estado_cilindro` (`id_estado`) ON UPDATE CASCADE;

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
  ADD CONSTRAINT `fk_cilindro_id_cilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_estado_mantenimiento` FOREIGN KEY (`estado_mantenimiento`) REFERENCES `estado_mantenimiento` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mantenimiento_agenda` FOREIGN KEY (`agenda_id_agenda`) REFERENCES `agenda` (`id_agenda`) ON UPDATE CASCADE;

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
  ADD CONSTRAINT `fk_cliente_id_cli` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_rol` FOREIGN KEY (`rol_idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tp` FOREIGN KEY (`tp_id`) REFERENCES `tipoid` (`idTipoID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
