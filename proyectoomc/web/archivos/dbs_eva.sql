-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2019 a las 07:25:25
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbs_eva`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_articulo`
--

CREATE TABLE `tbl_articulo` (
  `art_id` int(11) NOT NULL,
  `art_nombre` varchar(45) DEFAULT NULL,
  `art_descripcion` varchar(255) DEFAULT NULL,
  `art_valorUnitario` float DEFAULT NULL,
  `art_cantidad` int(11) DEFAULT NULL,
  `art_marca` varchar(45) DEFAULT NULL,
  `tbl_categoria_cat_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_categoria`
--

CREATE TABLE `tbl_categoria` (
  `cat_id` int(11) NOT NULL,
  `cat_seleccion` varchar(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbl_categoria`
--

INSERT INTO `tbl_categoria` (`cat_id`, `cat_seleccion`) VALUES
(1, 'Vegetales'),
(2, 'Ropa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_ordenpedido`
--

CREATE TABLE `tbl_ordenpedido` (
  `ped_id` int(11) NOT NULL,
  `ped_fecha` date DEFAULT NULL,
  `ped_estado` enum('Entregado','No Entregado','En proceso') DEFAULT NULL,
  `ped_subTotal` float DEFAULT NULL,
  `ped_impuesto` float DEFAULT NULL,
  `ped_precio` float DEFAULT NULL,
  `tbl_usuario_usu_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_ordenpedido_has_tbl_articulo`
--

CREATE TABLE `tbl_ordenpedido_has_tbl_articulo` (
  `ordenPedidoId` int(11) NOT NULL,
  `valorCompra` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `tbl_ordenPedido_ped_id` int(11) NOT NULL,
  `tbl_articulo_art_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_rol`
--

CREATE TABLE `tbl_rol` (
  `rol_idRol` int(11) NOT NULL,
  `rol_nombreRol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbl_rol`
--

INSERT INTO `tbl_rol` (`rol_idRol`, `rol_nombreRol`) VALUES
(1, 'Administrador'),
(2, 'Cliente'),
(3, 'Proveedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_rutasimg`
--

CREATE TABLE `tbl_rutasimg` (
  `rut_id` int(11) NOT NULL,
  `rut_ruta` varchar(255) DEFAULT NULL,
  `rut_estado` varchar(45) DEFAULT NULL,
  `tbl_articulo_art_id` int(11) DEFAULT NULL,
  `tbl_categoria_cat_id` int(11) DEFAULT NULL,
  `tbl_usuario_usu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbl_rutasimg`
--

INSERT INTO `tbl_rutasimg` (`rut_id`, `rut_ruta`, `rut_estado`, `tbl_articulo_art_id`, `tbl_categoria_cat_id`, `tbl_usuario_usu_id`) VALUES
(1, '../ImgTemporal/2019080923024039595897562_b3f899143e_o.jpg', 'Primaria', NULL, 1, NULL),
(2, '../ImgTemporal/2019080923024039595897562_b3f899143e_o.jpg', 'Primaria', NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuario`
--

CREATE TABLE `tbl_usuario` (
  `usu_id` int(11) NOT NULL,
  `usu_nombre` varchar(255) DEFAULT NULL,
  `usu_apellido` varchar(100) DEFAULT NULL,
  `usu_correo` varchar(100) DEFAULT NULL,
  `usu_telefono` varchar(45) DEFAULT NULL,
  `usu_tipoDocumento` varchar(45) DEFAULT NULL,
  `usu_documento` varchar(45) DEFAULT NULL,
  `usu_direccion` varchar(45) DEFAULT NULL,
  `usu_clave` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tbl_usuario`
--

INSERT INTO `tbl_usuario` (`usu_id`, `usu_nombre`, `usu_apellido`, `usu_correo`, `usu_telefono`, `usu_tipoDocumento`, `usu_documento`, `usu_direccion`, `usu_clave`) VALUES
(8, 'admin', 'admin', 'admin@admin', '123', 'cc', '123', 'asd', 'admin'),
(9, 'Alejandro', 'Monroy', 'adm@as', '123', 'C.C', '1234', 'asd', '1234'),
(10, 'Simon', 'Leon', 'ssleon96@gmail.com', '314', 'C.C', '1000312123', 'Asd', 'hola');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuario_has_tbl_articulo`
--

CREATE TABLE `tbl_usuario_has_tbl_articulo` (
  `id_Usuario_Articulo` int(11) NOT NULL,
  `cantidadArticulo` int(11) DEFAULT NULL,
  `tbl_usuario_usu_id` int(11) NOT NULL,
  `tbl_articulo_art_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuario_has_tbl_rol`
--

CREATE TABLE `tbl_usuario_has_tbl_rol` (
  `idUsuarioRol` int(11) NOT NULL,
  `tbl_usuario_usu_id` int(11) DEFAULT NULL,
  `tbl_rol_rol_idRol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbl_articulo`
--
ALTER TABLE `tbl_articulo`
  ADD PRIMARY KEY (`art_id`),
  ADD KEY `fk_tbl_articulo_tbl_categoria1_idx` (`tbl_categoria_cat_id`);

--
-- Indices de la tabla `tbl_categoria`
--
ALTER TABLE `tbl_categoria`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indices de la tabla `tbl_ordenpedido`
--
ALTER TABLE `tbl_ordenpedido`
  ADD PRIMARY KEY (`ped_id`),
  ADD KEY `fk_tbl_ordenPedido_tbl_usuario1_idx` (`tbl_usuario_usu_id`);

--
-- Indices de la tabla `tbl_ordenpedido_has_tbl_articulo`
--
ALTER TABLE `tbl_ordenpedido_has_tbl_articulo`
  ADD PRIMARY KEY (`ordenPedidoId`),
  ADD KEY `fk_tbl_ordenPedido_has_tbl_articulo_tbl_articulo1_idx` (`tbl_articulo_art_id`),
  ADD KEY `fk_tbl_ordenPedido_has_tbl_articulo_tbl_ordenPedido1_idx` (`tbl_ordenPedido_ped_id`);

--
-- Indices de la tabla `tbl_rol`
--
ALTER TABLE `tbl_rol`
  ADD PRIMARY KEY (`rol_idRol`);

--
-- Indices de la tabla `tbl_rutasimg`
--
ALTER TABLE `tbl_rutasimg`
  ADD PRIMARY KEY (`rut_id`),
  ADD KEY `fk_tbl_rutasImg_tbl_articulo1_idx` (`tbl_articulo_art_id`),
  ADD KEY `fk_tbl_rutasImg_tbl_categoria1_idx` (`tbl_categoria_cat_id`),
  ADD KEY `fk_tbl_rutasImg_tbl_usuario1_idx` (`tbl_usuario_usu_id`);

--
-- Indices de la tabla `tbl_usuario`
--
ALTER TABLE `tbl_usuario`
  ADD PRIMARY KEY (`usu_id`);

--
-- Indices de la tabla `tbl_usuario_has_tbl_articulo`
--
ALTER TABLE `tbl_usuario_has_tbl_articulo`
  ADD PRIMARY KEY (`id_Usuario_Articulo`),
  ADD KEY `fk_tbl_usuario_has_tbl_articulo_tbl_articulo1_idx` (`tbl_articulo_art_id`),
  ADD KEY `fk_tbl_usuario_has_tbl_articulo_tbl_usuario1_idx` (`tbl_usuario_usu_id`),
  ADD KEY `tbl_usuario_usu_id` (`tbl_usuario_usu_id`,`tbl_articulo_art_id`);

--
-- Indices de la tabla `tbl_usuario_has_tbl_rol`
--
ALTER TABLE `tbl_usuario_has_tbl_rol`
  ADD PRIMARY KEY (`idUsuarioRol`),
  ADD KEY `fk_tbl_usuario_has_tbl_rol_tbl_rol1_idx` (`tbl_rol_rol_idRol`),
  ADD KEY `fk_tbl_usuario_has_tbl_rol_tbl_usuario1_idx` (`tbl_usuario_usu_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbl_articulo`
--
ALTER TABLE `tbl_articulo`
  MODIFY `art_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbl_categoria`
--
ALTER TABLE `tbl_categoria`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tbl_ordenpedido`
--
ALTER TABLE `tbl_ordenpedido`
  MODIFY `ped_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbl_ordenpedido_has_tbl_articulo`
--
ALTER TABLE `tbl_ordenpedido_has_tbl_articulo`
  MODIFY `ordenPedidoId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbl_rol`
--
ALTER TABLE `tbl_rol`
  MODIFY `rol_idRol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tbl_rutasimg`
--
ALTER TABLE `tbl_rutasimg`
  MODIFY `rut_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tbl_usuario`
--
ALTER TABLE `tbl_usuario`
  MODIFY `usu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `tbl_usuario_has_tbl_articulo`
--
ALTER TABLE `tbl_usuario_has_tbl_articulo`
  MODIFY `id_Usuario_Articulo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tbl_usuario_has_tbl_rol`
--
ALTER TABLE `tbl_usuario_has_tbl_rol`
  MODIFY `idUsuarioRol` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tbl_articulo`
--
ALTER TABLE `tbl_articulo`
  ADD CONSTRAINT `fk_tbl_articulo_tbl_categoria1` FOREIGN KEY (`tbl_categoria_cat_id`) REFERENCES `tbl_categoria` (`cat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_ordenpedido`
--
ALTER TABLE `tbl_ordenpedido`
  ADD CONSTRAINT `fk_tbl_ordenPedido_tbl_usuario1` FOREIGN KEY (`tbl_usuario_usu_id`) REFERENCES `tbl_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_ordenpedido_has_tbl_articulo`
--
ALTER TABLE `tbl_ordenpedido_has_tbl_articulo`
  ADD CONSTRAINT `fk_tbl_ordenPedido_has_tbl_articulo_tbl_articulo1` FOREIGN KEY (`tbl_articulo_art_id`) REFERENCES `tbl_articulo` (`art_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_ordenPedido_has_tbl_articulo_tbl_ordenPedido1` FOREIGN KEY (`tbl_ordenPedido_ped_id`) REFERENCES `tbl_ordenpedido` (`ped_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_rutasimg`
--
ALTER TABLE `tbl_rutasimg`
  ADD CONSTRAINT `fk_tbl_rutasImg_tbl_articulo1` FOREIGN KEY (`tbl_articulo_art_id`) REFERENCES `tbl_articulo` (`art_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_rutasImg_tbl_categoria1` FOREIGN KEY (`tbl_categoria_cat_id`) REFERENCES `tbl_categoria` (`cat_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_rutasImg_tbl_usuario1` FOREIGN KEY (`tbl_usuario_usu_id`) REFERENCES `tbl_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_usuario_has_tbl_articulo`
--
ALTER TABLE `tbl_usuario_has_tbl_articulo`
  ADD CONSTRAINT `fk_tbl_usuario_has_tbl_articulo_tbl_articulo1` FOREIGN KEY (`tbl_articulo_art_id`) REFERENCES `tbl_articulo` (`art_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_usuario_has_tbl_articulo_tbl_usuario1` FOREIGN KEY (`tbl_usuario_usu_id`) REFERENCES `tbl_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tbl_usuario_has_tbl_rol`
--
ALTER TABLE `tbl_usuario_has_tbl_rol`
  ADD CONSTRAINT `fk_tbl_usuario_has_tbl_rol_tbl_rol1` FOREIGN KEY (`tbl_rol_rol_idRol`) REFERENCES `tbl_rol` (`rol_idRol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbl_usuario_has_tbl_rol_tbl_usuario1` FOREIGN KEY (`tbl_usuario_usu_id`) REFERENCES `tbl_usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
