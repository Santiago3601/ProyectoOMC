-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2019 at 12:40 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `proyectoomc`
--

-- --------------------------------------------------------

--
-- Table structure for table `agenda`
--

CREATE TABLE `agenda` (
  `id_agenda` int(7) NOT NULL,
  `fecha_programada` date NOT NULL,
  `novedades` varchar(300) COLLATE utf32_spanish_ci DEFAULT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alquiler`
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
-- Dumping data for table `alquiler`
--

INSERT INTO `alquiler` (`id_alquiler`, `fecha_de_entrega`, `novedades`, `cilindro_id_cilindro`, `ruta_id_ruta`, `solicitud_id_solicitud`, `estado_alquiler_id_estado`) VALUES
(1, '2019-07-20', 'La persona cancelo el envio', 1060293, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cilindro`
--

CREATE TABLE `cilindro` (
  `id_cilindro` int(7) NOT NULL,
  `estado` varchar(30) COLLATE utf32_spanish_ci NOT NULL,
  `tamanio` int(3) NOT NULL,
  `lote` int(8) NOT NULL,
  `fecha_de_creacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `cilindro`
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
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(7) NOT NULL,
  `usuario_id` bigint(10) NOT NULL,
  `estado_id_estado` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `usuario_id`, `estado_id_estado`) VALUES
(3, 23874610, 1),
(6, 23879120, 1),
(7, 1000283977, 1);

-- --------------------------------------------------------

--
-- Table structure for table `contrato`
--

CREATE TABLE `contrato` (
  `id_contrato` int(7) NOT NULL,
  `tipo_contrato` varchar(20) COLLATE utf32_spanish_ci NOT NULL,
  `fecha_inicio_contrato` date NOT NULL,
  `fecha_final_contrato` date NOT NULL,
  `alquiler_id_alquiler` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `contrato`
--

INSERT INTO `contrato` (`id_contrato`, `tipo_contrato`, `fecha_inicio_contrato`, `fecha_final_contrato`, `alquiler_id_alquiler`) VALUES
(4, ' ', '2019-07-01', '2019-08-01', 1),
(8, 'x', '2000-12-31', '2001-01-31', 1),
(9, 'x', '2001-12-31', '2002-01-31', 1);

-- --------------------------------------------------------

--
-- Table structure for table `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(3) NOT NULL,
  `usuario_id` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `usuario_id`) VALUES
(1, 23874610);

-- --------------------------------------------------------

--
-- Table structure for table `estado_alquiler`
--

CREATE TABLE `estado_alquiler` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(70) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `estado_alquiler`
--

INSERT INTO `estado_alquiler` (`id_estado`, `estado`) VALUES
(1, 'Enlistado'),
(2, 'Entregado'),
(3, 'Regresado a la empresa'),
(4, 'Cancelado');

-- --------------------------------------------------------

--
-- Table structure for table `estado_cliente`
--

CREATE TABLE `estado_cliente` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `estado_cliente`
--

INSERT INTO `estado_cliente` (`id_estado`, `estado`) VALUES
(1, 'Activo'),
(2, 'Innactivo'),
(3, 'Solicito Cilindro'),
(4, 'Recibio Cilindro');

-- --------------------------------------------------------

--
-- Table structure for table `estado_mantenimiento`
--

CREATE TABLE `estado_mantenimiento` (
  `id_estado` int(2) NOT NULL,
  `estado` varchar(50) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `horario`
--

CREATE TABLE `horario` (
  `id_horario` int(5) NOT NULL,
  `hora_ingreso` time DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  `empleado_id_empleado` int(3) NOT NULL,
  `fecha_de_ingreso` date DEFAULT NULL,
  `fecha_de_salida` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `horario`
--

INSERT INTO `horario` (`id_horario`, `hora_ingreso`, `hora_salida`, `empleado_id_empleado`, `fecha_de_ingreso`, `fecha_de_salida`) VALUES
(1, '02:00:00', NULL, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id_mantenimiento` int(7) NOT NULL,
  `fecha_inicio_mantenimiento` date NOT NULL,
  `fecha_final_mantenimiento` date NOT NULL,
  `agenda_id_agenda` int(7) NOT NULL,
  `estado_mantenimiento` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mantenimiento_cilindro`
--

CREATE TABLE `mantenimiento_cilindro` (
  `id_mantenimiento_cilindro` int(7) NOT NULL,
  `mantenimiento_id_mantenimiento` int(7) NOT NULL,
  `cilindro_id_cilindro` int(7) NOT NULL,
  `tipo_mantenimiento` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permiso`
--

CREATE TABLE `permiso` (
  `id_permiso` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `url` text COLLATE utf32_spanish_ci,
  `icon` varchar(45) COLLATE utf32_spanish_ci NOT NULL,
  `permiso_padre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `permiso`
--

INSERT INTO `permiso` (`id_permiso`, `nombre`, `url`, `icon`, `permiso_padre`) VALUES
(1, '-----Administrador-----', NULL, 'x', NULL),
(2, 'Envios-----', NULL, 'x', 1),
(3, 'Crear', NULL, 'x', 2),
(4, 'Consultar', NULL, 'x', 2),
(5, 'Programar', NULL, 'x', 2),
(6, 'Cliente', 'moduloEnvios/crearCliente.xhtml', 'x', 3),
(7, 'Contrato', 'moduloEnvios/crearContrato.xhtml', 'x', 3),
(8, 'Usuario', 'moduloEnvios/crearUsuario.xhtml', 'x', 3),
(9, 'Vehiculo', 'moduloEnvios/crearVehiculo.xhtml', 'x', 3),
(10, 'Solicitud', 'moduloEnvios/crearSolicitud.xhtml', 'x', 3),
(11, 'Alquiler', 'moduloEnvios/listaAlquiler.xhtml', 'x', 4),
(12, 'Cliente', 'moduloEnvios/listaClientes.xhtml', 'x', 4),
(13, 'Contrato', 'moduloEnvios/listaContrato.xhtml', 'x', 4),
(14, 'Roles', 'moduloEnvios/listaRoles.xhtml', 'x', 4),
(15, 'Ruta', 'moduloEnvios/listaRuta.xhtml', 'x', 4),
(16, 'Vehiculos', 'moduloEnvios/listaVehiculos.xhtml', 'x', 4),
(17, 'Usuarios', 'moduloEnvios/listaSolicitud.xhtml', 'x', 4),
(18, 'Solicitud', 'moduloEnvios/listaSolicitud.xhtml', 'x', 4),
(19, 'Alquiler', 'moduloEnvios/crearAlquiler.xhtml', 'x', 5),
(20, 'Ruta', 'moduloEnvios/crearRuta.xhtml', 'x', 5),
(21, 'Mantenimiento----', NULL, 'x', 1),
(22, 'Programar', NULL, 'x', 21),
(23, 'Registrar', NULL, 'x', 21),
(24, 'Consultar', NULL, 'x', 21),
(25, 'Mantenimiento', 'moduloMantenimiento/registrarMantenimiento.xhtml', 'x', 22),
(26, 'Mantenimiento cilindro', 'moduloMantenimiento/registrarMantenimientoCilindro.xhtml', 'x', 22),
(27, 'Estado mantenimiento', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 22),
(28, 'Agenda', 'moduloMantenimiento/registrarAgenda.xhtml', 'x', 23),
(29, 'Cilindro', 'moduloMantenimiento/registrarCilindro.xhtml', 'x', 23),
(30, 'Agenda', 'moduloMantenimiento/listaAgenda.xhtml', 'x', 24),
(31, 'Cilindro', 'moduloMantenimiento/listaCilindro.xhtml', 'x', 24),
(32, 'Mantenimiento', 'moduloMantenimiento/listaMantenimiento.xhtml', 'x', 24),
(33, 'Mantenimiento Cilindro', 'moduloMantenimiento/listaMantenimientoCilindro.xhtml', 'x', 24),
(34, 'Estado Mantenimiento', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 24),
(35, 'Personal---------', NULL, 'x', 1),
(36, 'Registrar', NULL, 'x', 35),
(37, 'Consultar', NULL, 'x', 35),
(38, 'Empleado', 'moduloPersonal/RegistrarEmpleado.xhtml', 'x', 36),
(39, 'Horario', 'x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 36),
(40, 'Permiso Laboral', 'moduloPersonal/RegistrarPermisoLaboral.xhtml', 'x', 36),
(41, 'Punto', 'moduloPersonal/RegistrarPunto.xhtml', 'x', 36),
(42, 'Turno', 'moduloPersonal/RegistrarTurno.xhtml', 'x', 36),
(43, 'Empleado', 'moduloPersonal/listaEmpleado.xhtml', 'x', 37),
(44, 'Horario', 'x-x-x--x-x-x-x-x-x-x-x-x-x-x-x-x-x', 'x', 37),
(45, 'Permiso Laboral', 'moduloPersonal/ListarPermisoLaboral.xhtml', 'x', 37),
(46, 'Punto', 'moduloPersonal/ListaPunto.xhtml', 'x', 37),
(47, 'Turno', 'moduloPersonal/ListarTurno.xhtml', 'x', 37),
(48, '---- Jefe de planta -----', NULL, 'x', NULL),
(49, 'Envios-----', NULL, 'x', 48),
(50, 'Mantenimiento ------', NULL, 'x', 48),
(51, 'Personal -------', NULL, 'x', 48),
(52, 'Crear', NULL, 'x', 49),
(53, 'Programar', NULL, 'x', 49),
(54, 'Consultar', NULL, 'x', 49),
(55, 'Vehiculo', NULL, 'x', 52);

-- --------------------------------------------------------

--
-- Table structure for table `permiso_laboral`
--

CREATE TABLE `permiso_laboral` (
  `id_permiso_laboral` int(5) NOT NULL,
  `hora_permiso` time NOT NULL,
  `fecha_permiso` date NOT NULL,
  `obvservaciones` varchar(200) COLLATE utf32_spanish_ci DEFAULT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `punto`
--

CREATE TABLE `punto` (
  `id_punto` int(3) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `descripcion` varchar(70) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `punto`
--

INSERT INTO `punto` (`id_punto`, `cantidad`, `descripcion`) VALUES
(1, 50, 'Ha trabajado 50 horas extras');

-- --------------------------------------------------------

--
-- Table structure for table `rol`
--

CREATE TABLE `rol` (
  `idRol` int(1) NOT NULL,
  `rol` varchar(45) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `rol`
--

INSERT INTO `rol` (`idRol`, `rol`) VALUES
(1, 'Administrador'),
(2, 'Cliente'),
(3, 'Jefe de planta'),
(4, 'Tecnico');

-- --------------------------------------------------------

--
-- Table structure for table `rol_tiene_permiso`
--

CREATE TABLE `rol_tiene_permiso` (
  `rol_idRol` int(1) NOT NULL,
  `permiso_id_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ruta`
--

CREATE TABLE `ruta` (
  `id_ruta` int(2) NOT NULL,
  `direccion` varchar(40) COLLATE utf32_spanish_ci NOT NULL,
  `planilla` varchar(200) COLLATE utf32_spanish_ci NOT NULL,
  `vehiculo_id_vehiculo` int(2) NOT NULL,
  `empleado_id_empleado` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `ruta`
--

INSERT INTO `ruta` (`id_ruta`, `direccion`, `planilla`, `vehiculo_id_vehiculo`, `empleado_id_empleado`) VALUES
(1, 'Cll 26 Entre Cra 68 y Cra 7', 'Cll 25 No. 23 - 27', 3, 1),
(6, 'Cll 45 entre carrera 17 y 32', 'clle', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `solicitud`
--

CREATE TABLE `solicitud` (
  `id_solicitud` int(7) NOT NULL,
  `formula` longblob NOT NULL,
  `tamanio_cilindro` int(5) NOT NULL,
  `cliente_id_cliente` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `solicitud`
--

INSERT INTO `solicitud` (`id_solicitud`, `formula`, `tamanio_cilindro`, `cliente_id_cliente`) VALUES
(1, 0xefbbbf2020207075626c696320537472696e672076616c696461724c6f67696e2829207b0d0a0d0a2020202020202020537472696e672072656469726563696f6e6172203d2022223b0d0a0d0a2020202020202020747279207b0d0a2020202020202020202020205573756172696f207573756172696f4c6f67756561646f203d207573756172696f4661636164652e6c6f67696e287573756172696f293b0d0a202020202020202020202020696620287573756172696f4c6f67756561646f20213d206e756c6c29207b0d0a0d0a2020202020202020202020202020202053797374656d2e6f75742e7072696e746c6e28227573756172696f4c6f676561646f2022202b207573756172696f4c6f67756561646f2e6765744e6f6d6272652829293b0d0a202020202020202020202020202020204661636573436f6e746578742e67657443757272656e74496e7374616e636528292e67657445787465726e616c436f6e7465787428292e67657453657373696f6e4d617028292e7075742822736573696f6e4c6f67696e222c207573756172696f4c6f67756561646f293b0d0a2020202020202020202020202020202073776974636820287573756172696f4c6f67756561646f2e676574526f6c6964526f6c28292e6765744964526f6c282929207b0d0a20202020202020202020202020202020202020206361736520313a0d0a20202020202020202020202020202020202020202020202072656469726563696f6e6172203d2264617368626f6172642f53492f3161646d696e2f696e646578223b0d0a202020202020202020202020202020202020202020202020627265616b3b0d0a20202020202020202020202020202020202020206361736520323a0d0a20202020202020202020202020202020202020202020202072656469726563696f6e6172203d2264617368626f6172642f53492f32636c69656e74652f696e646578223b0d0a202020202020202020202020202020202020202020202020627265616b3b0d0a20202020202020202020202020202020202020206361736520333a0d0a20202020202020202020202020202020202020202020202072656469726563696f6e6172203d2264617368626f6172642f53492f336a6566655f706c616e74612f696e646578223b0d0a202020202020202020202020202020202020202020202020627265616b3b0d0a20202020202020202020202020202020202020206361736520343a0d0a20202020202020202020202020202020202020202020202072656469726563696f6e6172203d2264617368626f6172642f53492f347465636e69636f2f696e646578223b0d0a202020202020202020202020202020202020202020202020627265616b3b0d0a202020202020202020202020202020202020200d0a202020202020202020202020202020202020202064656661756c743a0d0a2020202020202020202020202020202020202020202020207468726f77206e657720417373657274696f6e4572726f7228293b0d0a202020202020202020202020202020207d0d0a2020202020202020202020207d656c73657b0d0a2020202020202020202020202020202072656469726563696f6e6172203d22696e6465785f31223b0d0a202020202020202020202020202020200d0a2020202020202020202020207d0d0a0d0a20202020202020207d2063617463682028457863657074696f6e206529207b0d0a20202020202020202020202053797374656d2e6f75742e7072696e746c6e28224572726f7222202b20652e6765744d6573736167652829293b0d0a20202020202020207d0d0a0d0a202020202020202072657475726e2072656469726563696f6e61723b0d0a202020207d0d0a, 250, 3);

-- --------------------------------------------------------

--
-- Table structure for table `turno`
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
-- Dumping data for table `turno`
--

INSERT INTO `turno` (`id_turno`, `programacion`, `hora_ingreso`, `horas_salida`, `fecha_inicial`, `fecha_final`, `observaciones`, `punto_id_puntos`, `id_empleado`) VALUES
(1, 'Realizar envios', '06:00:00', '12:00:00', '2019-07-07', '2019-07-13', 'El empleado debera realizar envios en la semana del 7 - 13 de julio', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(10) NOT NULL,
  `tp_id` varchar(50) COLLATE utf32_spanish_ci NOT NULL,
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
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `tp_id`, `nombre`, `apellido`, `direccion`, `correo`, `contrasenia`, `telefono`, `eps`, `fecha_de_nacimiento`, `edad`, `rol_idRol`) VALUES
(100, 'X', 'X', 'X', 'X', 'X', 'X', 1, 'X', '1990-01-02', 29, 2),
(2398010, 'C.C', 'Martha Sophia', 'Magno Hill', 'Cra 98 No. 23 - 68', 'martha.mag2@msn.com', '1234', 4120981, 'Compensar EPS.', '1968-10-02', 50, 3),
(23063810, 'C.C', 'James Daniels ', 'Sugar Winchester', 'Cra 15 No. 230 - 35', 'mariwelch96@aol.com', '1234', 4125124, 'Nueva EPS', '2000-01-23', 19, 1),
(23109821, 'C.E', 'Mark Thomas ', 'Lavagnino Douglas', 'Dg.23 No. 190 -34', 'mark.douglas21@aol.com', '1234', 4129800, 'N/A', '1992-12-21', 26, 2),
(23874610, 'C.C', 'Julie', 'Pott Friedman', 'Cll 32 sur No. 32 - 77', 'julie.15@aol.com', '1234', 4169210, 'Famisanar', '1990-03-29', 29, 3),
(23879120, 'C.C', 'Mark David ', 'Stewart King', 'Cra. 44 No. 21 - 55', 'mark.sk15@aol.com', '1234', 3108971923, 'N/A', '1979-07-15', 39, 2),
(24981099, 'C.E', 'Mark Christopher', 'Green Adams', 'Kra 19 No. 210 - 23', 'mark.adam5@aol.com', '1234', 4774147, 'Cafam EPS.', '1997-05-05', 22, 4),
(27871927, 'C.C', 'Thomas', 'Miller King', 'x', 'x', 'x', 1, 'x', '1989-01-01', 0, 4),
(1000283977, 'T.I', 'Amy Michelle', 'Smith Allen', 'Cll 24 No. 31 -  34', 'amy.smith12@msn.com', '1234', 4135331, 'Nueva Eps', '1989-07-12', 29, 2),
(1000624311, 'C.E', 'Jonh Nicolas', 'Welch Martinez', 'Cra 13 No. 123 - 23', 'jonhwelch@aol.com', '1234', 4139385, 'N/A', '1980-06-03', 39, 2);

-- --------------------------------------------------------

--
-- Table structure for table `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id_vehiculo` int(2) NOT NULL,
  `matricula` varchar(10) COLLATE utf32_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_spanish_ci;

--
-- Dumping data for table `vehiculo`
--

INSERT INTO `vehiculo` (`id_vehiculo`, `matricula`) VALUES
(1, 'BOG-154'),
(2, 'BOG-264'),
(3, 'BOG-219'),
(4, 'SOR-419'),
(5, 'SOR-221'),
(6, 'AGD-390');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id_agenda`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`);

--
-- Indexes for table `alquiler`
--
ALTER TABLE `alquiler`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD KEY `cilindro_id_cilindro` (`cilindro_id_cilindro`),
  ADD KEY `ruta_id_ruta` (`ruta_id_ruta`),
  ADD KEY `solicitud_id_solicitud` (`solicitud_id_solicitud`),
  ADD KEY `estado_alquiler_id_estado` (`estado_alquiler_id_estado`);

--
-- Indexes for table `cilindro`
--
ALTER TABLE `cilindro`
  ADD PRIMARY KEY (`id_cilindro`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `fk_cliente_usuario` (`usuario_id`),
  ADD KEY `estado_id_estado` (`estado_id_estado`);

--
-- Indexes for table `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`id_contrato`),
  ADD KEY `alquiler_id_alquiler` (`alquiler_id_alquiler`);

--
-- Indexes for table `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indexes for table `estado_alquiler`
--
ALTER TABLE `estado_alquiler`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indexes for table `estado_cliente`
--
ALTER TABLE `estado_cliente`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indexes for table `estado_mantenimiento`
--
ALTER TABLE `estado_mantenimiento`
  ADD PRIMARY KEY (`id_estado`);

--
-- Indexes for table `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`);

--
-- Indexes for table `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `agenda_id_agenda` (`agenda_id_agenda`),
  ADD KEY `estado_mantenimiento` (`estado_mantenimiento`);

--
-- Indexes for table `mantenimiento_cilindro`
--
ALTER TABLE `mantenimiento_cilindro`
  ADD PRIMARY KEY (`id_mantenimiento_cilindro`),
  ADD KEY `mantenimiento_id_mantenimiento` (`mantenimiento_id_mantenimiento`),
  ADD KEY `cilindro_id_cilindro` (`cilindro_id_cilindro`);

--
-- Indexes for table `permiso`
--
ALTER TABLE `permiso`
  ADD PRIMARY KEY (`id_permiso`),
  ADD KEY `permiso_id_permiso` (`permiso_padre`);

--
-- Indexes for table `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  ADD PRIMARY KEY (`id_permiso_laboral`),
  ADD KEY `empleado_id_empleado` (`empleado_id_empleado`);

--
-- Indexes for table `punto`
--
ALTER TABLE `punto`
  ADD PRIMARY KEY (`id_punto`);

--
-- Indexes for table `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indexes for table `rol_tiene_permiso`
--
ALTER TABLE `rol_tiene_permiso`
  ADD PRIMARY KEY (`rol_idRol`,`permiso_id_permiso`),
  ADD KEY `rol_idRol` (`rol_idRol`,`permiso_id_permiso`),
  ADD KEY `permiso_id_permiso` (`permiso_id_permiso`);

--
-- Indexes for table `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`id_ruta`),
  ADD KEY `fk_ruta_empleado` (`empleado_id_empleado`),
  ADD KEY `fk_ruta_vehiculo` (`vehiculo_id_vehiculo`);

--
-- Indexes for table `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `cliente_id_cliente` (`cliente_id_cliente`);

--
-- Indexes for table `turno`
--
ALTER TABLE `turno`
  ADD PRIMARY KEY (`id_turno`),
  ADD KEY `punto_id_puntos` (`punto_id_puntos`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rol_idRol` (`rol_idRol`);

--
-- Indexes for table `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id_vehiculo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id_agenda` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `id_alquiler` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `contrato`
--
ALTER TABLE `contrato`
  MODIFY `id_contrato` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `estado_alquiler`
--
ALTER TABLE `estado_alquiler`
  MODIFY `id_estado` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id_mantenimiento` int(7) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  MODIFY `id_permiso_laboral` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_ruta` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `id_solicitud` int(7) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `turno`
--
ALTER TABLE `turno`
  MODIFY `id_turno` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `fk_agenda_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Constraints for table `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `fk_alquiler_cilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_ruta` FOREIGN KEY (`ruta_id_ruta`) REFERENCES `ruta` (`id_ruta`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_alquiler_solicitud` FOREIGN KEY (`solicitud_id_solicitud`) REFERENCES `solicitud` (`id_solicitud`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_estado_alquiler` FOREIGN KEY (`estado_alquiler_id_estado`) REFERENCES `estado_alquiler` (`id_estado`) ON UPDATE CASCADE;

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_estado` FOREIGN KEY (`estado_id_estado`) REFERENCES `estado_cliente` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `fk_contrato_alquiler` FOREIGN KEY (`alquiler_id_alquiler`) REFERENCES `alquiler` (`id_alquiler`) ON UPDATE CASCADE;

--
-- Constraints for table `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleado_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `fk_horario_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Constraints for table `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `fk_estado_mantenimiento` FOREIGN KEY (`estado_mantenimiento`) REFERENCES `estado_mantenimiento` (`id_estado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mantenimiento_agenda` FOREIGN KEY (`agenda_id_agenda`) REFERENCES `agenda` (`id_agenda`) ON UPDATE CASCADE;

--
-- Constraints for table `mantenimiento_cilindro`
--
ALTER TABLE `mantenimiento_cilindro`
  ADD CONSTRAINT `fk_mantenimiento_tiene_cilindro_idCilindro` FOREIGN KEY (`cilindro_id_cilindro`) REFERENCES `cilindro` (`id_cilindro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mantenimiento_tiene_cilindro_idMantenimiento` FOREIGN KEY (`mantenimiento_id_mantenimiento`) REFERENCES `mantenimiento` (`id_mantenimiento`) ON UPDATE CASCADE;

--
-- Constraints for table `permiso`
--
ALTER TABLE `permiso`
  ADD CONSTRAINT `fk_permiso_permiso1` FOREIGN KEY (`permiso_padre`) REFERENCES `permiso` (`id_permiso`) ON UPDATE CASCADE;

--
-- Constraints for table `permiso_laboral`
--
ALTER TABLE `permiso_laboral`
  ADD CONSTRAINT `fk_permiso_laboral_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE;

--
-- Constraints for table `rol_tiene_permiso`
--
ALTER TABLE `rol_tiene_permiso`
  ADD CONSTRAINT `fk_ROL_TIENE_PERMISOS_PERMISOS` FOREIGN KEY (`permiso_id_permiso`) REFERENCES `permiso` (`id_permiso`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ROL_TIENE_PERMISOS_ROL` FOREIGN KEY (`rol_idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE;

--
-- Constraints for table `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `fk_ruta_empleado` FOREIGN KEY (`empleado_id_empleado`) REFERENCES `empleado` (`id_empleado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ruta_vehiculo` FOREIGN KEY (`vehiculo_id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`) ON UPDATE CASCADE;

--
-- Constraints for table `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_cliente` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE;

--
-- Constraints for table `turno`
--
ALTER TABLE `turno`
  ADD CONSTRAINT `fk_turno_punto` FOREIGN KEY (`punto_id_puntos`) REFERENCES `punto` (`id_punto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `turno_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`rol_idRol`) REFERENCES `rol` (`idRol`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
