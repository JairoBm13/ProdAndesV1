alter table cliente drop constraint fk_c_usuario;
alter table proveedor drop constraint fk_p_usuario;
alter table administrador drop constraint fk_a_usuario;
alter table proceso drop constraint FK_proceso;
alter table etapaproduccion drop constraint FK_cp_eprod;
alter table etapa drop constraint FK_ce_etapa;
alter table etapa drop constraint FK_cp_etapa;
alter table estacionproduccion drop constraint FK_ce_estprod;
alter table requiere drop constraint FK_R_material;
alter table requiere drop constraint FK_R_estacion;
alter table suministro drop constraint FK_S_material;
alter table suministro drop constraint FK_S_proveedor;
alter table pedido drop constraint FK_Ped_producto;
alter table pedido drop constraint FK_ped_cliente;
alter table pedido drop constraint FK_ped_empresa;
alter table pedidoMaterial drop constraint FK_pM_material;
alter table pedidoMaterial drop constraint FK_pm_proveedor;

drop table pedidoMaterial;
drop table proveedor;
drop table pedido;
drop table suministro;
drop table requiere;
drop table material;
drop table estacionProduccion;
drop table etapa;
drop table etapaProduccion;
drop table producto;
drop table proceso;
drop table CLIENTE;
drop table OPERARIO;
drop table ADMINISTRADOR;
drop table USUARIO;
create table USUARIO (
                      ciudad VARCHAR2(50),
                      codigoPostal VARCHAR2(12),
                      direccionElectronica varchar2(60),
                      documentoId NUMBER,
                      login varchar2(50),
                      nacionalidad varchar2(50),
                      clave varchar2(50),
                      telefono varchar2(20),
                      tipoDocumento INTEGER
                      );
alter table usuario add CONSTRAINT NN_ciudad check (ciudad is not null);
alter table usuario add CONSTRAINT NN_postal check (codigoPostal is not null);
alter table usuario add CONSTRAINT NN_documentoID check (documentoID is not null);
alter table usuario add CONSTRAINT NN_nacionalidad check (nacionalidad is not null);
alter table usuario add CONSTRAINT NN_pass check (clave is not null);
alter table usuario add CONSTRAINT NN_telefono check (telefono is not null);
alter table usuario add constraint NN_tipoDocumento check (tipoDocumento is not null);
alter table usuario add constraint PK_USUARIO primary key (login, direccionElectronica);
alter table usuario add constraint ND_log unique (login);
alter table usuario add constraint ND_de unique (direccionElectronica);
create table CLIENTE (
                      codigo number,
                      idLegal NUMBER,
                      nombreLegal VARCHAR2(50),
                      tipoIDLegal INTEGER,
                      registroSINV varchar2(10),
                      login varchar2(50),
                      direccionElectronica varchar2(50)
                      );e
alter table CLIENTE add CONSTRAINT PK_CLIENTE PRIMARY KEY (codigo);
alter table cliente add constraint ND_log_clie unique (login);
alter table cliente add constraint ND_de_clie unique (direccionElectronica);
alter table CLIENTE add CONSTRAINT FK_C_USUARIO FOREIGN KEY(login,direccionElectronica) REFERENCES USUARIO;
alter table CLIENTE add CONSTRAINT NN_idLegal_clie check (idLegal is not null);
alter table CLIENTE add CONSTRAINT NN_nombreLegal_clie check (nombreLegal is not null);
alter table CLIENTE add CONSTRAINT NN_SINV check (registroSINV is not null);
alter table CLIENTE add CONSTRAINT NN_tipoIDLegal_cli check (tipoIDLegal is not null);
create table OPERARIO (
                      login varchar2(50),
                      direccionElectronica varchar2(50),
                      nombre varchar2(50),
                      codigo NUMBER,
                      cargo varchar2(50)
                      );
alter table OPERARIO add CONSTRAINT PK_OPERARIO PRIMARY KEY (codigo);
alter table operario add constraint ND_log_ope unique (login);
alter table operario add constraint ND_de_ope unique (direccionElectronica);
alter table OPERARIO add CONSTRAINT FK_O_USUARIO FOREIGN KEY(login,direccionElectronica) REFERENCES USUARIO;
alter table OPERARIO add CONSTRAINT NN_cargo_ope check (cargo is not null);
alter table operario add constraint NN_nombre_ope check (nombre is not null);
create table PROVEEDOR (
                        login varchar2(50),
                        direccionElectronica varchar2(50),
                        codigo number,
                        tipoIdLegal integer,
                        identificacionLegal number,
                        nombreLegal varchar2(50)
                        );
alter table proveedor add constraint PK_PROVEEDOR primary key (codigo);
alter table proveedor add constraint ND_log_prov unique (login);
alter table proveedor add constraint ND_de_prov unique (direccionElectronica);
alter table PROVEEDOR add constraint FK_P_USUARIO foreign key (login, direccionElectronica) references USUARIO;
alter table proveedor add constraint NN_tipoIDLegal_prove check (tipoIDLegal is not null);
alter table proveedor add constraint NN_identificacionLegal_prove check (identificacionLegal is not null);
alter table proveedor add constraint NN_nombreLegal_PROV check (nombreLegal is not null);
create table ADMINISTRADOR (
                             nombre varchar2(60),
                             codigo number,
                             login varchar2(60),
                             direccionElectronica varchar2(60)
);
alter table administrador add constraint PK_ADMINISTRADOR primary key (codigo);
alter table administrador add constraint ND_log_admin unique (login);
alter table administrador add constraint ND_de_admin unique (direccionElectronica);
alter table ADMINISTRADOR add constraint FK_A_USUARIO foreign key (login,direccionElectronica) REFERENCES USUARIO;
alter table administrador add constraint NN_NOMBRE_ADMIN check (nombre is not null);
create table PRODUCTO (
                       codigo number,
                       nombre varchar2(50),
                       cantidad integer,
                       descripcion varchar2(140),
                       costo number,
                       numEtapas integer
);
alter table producto add constraint PK_producto primary key (codigo);
alter table producto add constraint NN_nombre_produc check (nombre is not null);
alter table producto add constraint ND_nombre_produc unique (nombre);
alter table producto add constraint CK_cantidad_produc check (cantidad >=0);
alter table producto add constraint NN_descripcion_produc check (descripcion is not null);
alter table producto add constraint NN_costo_produc check (costo is not null);
alter table producto add constraint CK_costo_produc check (costo >0);
create table PROCESO (
                      codigo number,
                      codigoProducto number,
                      nombre varchar2(50),
                      tiempoEjecucion NUMBER,
                      descripcion varchar(140),
                      estado integer
);
alter table proceso add constraint PK_proceso primary key (codigo);
alter table proceso add constraint FK_proceso foreign key (codigoProducto) references producto (codigo);
alter table proceso add constraint NN_codProducto_proce check (estado is not null);
alter table proceso add constraint NN_estado_proc check (estado is not null);
alter table proceso add constraint CK_estado_proc check (estado >= 0);
alter table proceso add constraint NN_nombre_Proc check (nombre is not null);
alter table proceso add constraint NN_tiempo_Proc check (tiempoEJecucion is not null);
alter table proceso add constraint CK_tiempo_proc check (tiempoEjecucion >= 0);
alter table proceso add constraint NN_descrip_proc check (descripcion is not null);
create table etapaProduccion (
                              codigo number,
                              etapa integer,
                              nombre varchar2(50),
                              fechaInicio date,
                              fechaFin date,
                              tiempoEjecucion number,
                              descripcion varchar2(50),
                              codigoProducto number,
                              cantidadProducto number,
                              enEperaDe number
);
alter table etapaProduccion add constraint PK_etapaProd primary key (codigo);
alter table etapaProduccion add constraint FK_cp_eprod foreign key (codigoProducto) references producto (codigo);
alter table etapaProduccion add constraint NN_nombre_eProd check (nombre is not null);
alter table etapaProduccion add constraint NN_fechaInicio_eProd check (fechaInicio is not null);
alter table etapaProduccion add constraint NN_fechaFin_eProd check (fechaFin is not null);
alter table etapaProduccion add constraint CK_fechas_eprod check (fechaInicio < fechaFin);
alter table etapaProduccion add constraint NN_tiempoEjecucion_eProd check (tiempoEjecucion is not null);
alter table etapaProduccion add constraint CK_tiempoEjecucion_eprdo check (tiempoEjecucion >=0);
alter table etapaProduccion add constraint NN_descripcion check (descripcion is not null);
alter table etapaProduccion add constraint CK_cantprodu_eprod check(cantidadProducto >= 0);
alter table etapaProduccion add constraint NN_cantidad_eprod check (cantidadProducto is not null);
alter table etapaproduccion add enesperade number;
alter table etapaproduccion add constraint FK_eed_eprod foreign key (enesperade) references estacionproduccion (codigo);
create table etapa(
                    codigoEtapa number,
                    codigoProceso number
);
alter table etapa add constraint PK_etapa primary key (codigoEtapa, codigoProceso);
alter table etapa add constraint FK_ce_etapa foreign key (codigoEtapa) references etapaProduccion (codigo);
alter table etapa add constraint FK_cp_etapa foreign key (codigoProceso) references proceso (codigo);
create table estacionProduccion(
                                codigoEtapa number,
                                capacidad number,
                                codigo number,
                                tiempo number,
                                estado varchar2(12),
                                nombre varchar2(50)
);
alter table estacionproduccion add constraint NN_nombre_esprod check (nombre is not null);
alter table estacionProduccion add constraint PK_estacion primary key (codigo);
alter table estacionProduccion add constraint NN_capacidad_esprod check (capacidad is not null);
alter table estacionProduccion add constraint CK_capacidad_esprod check (capacidad >0);
alter table estacionProduccion add constraint NN_tiempo_esprod check (tiempo is not null);
alter table estacionProduccion add constraint CK_tiempo_esprdo check (tiempo > 0);
alter table estacionProduccion add constraint FK_ce_estprod foreign key (codigoEtapa) references etapaProduccion (codigo);
alter table estacionproduccion add constraint CK_estado_estprod check (estado = 'Activa' OR estado='Inactiva');
alter table estacionproduccion add constraint NN_estado_esprod check (estado is not null);
create table material(
                      cantidad number,
                      codigo number,
                      tipo varchar2(50),
                      unidad varchar2(50),
                      nombre varchar2(50),
                      ultimoAbastecimiento date
);
alter table material add constraint PK_material primary key (codigo);
alter table material add constraint NN_cantidad_mat check (cantidad is not null);
alter table material add constraint CK_cantidad_mat check (cantidad >=0);
alter table material add constraint NN_tipo_mat check (tipo is not null);
alter table material add constraint CK_tipo_mat check (tipo = 'Materia Prima' OR tipo='Componente');
alter table material add constraint NN_unidad_mat check (unidad is not null);
alter table material add constraint NN_nombre_mat check (nombre is not null);
alter table material add constraint NN_ultimo_mat check (ultimoAbastecimiento is not null);
create table requiere(
                      codigoMaterial number,
                      codigoEstacion number,
                      cantidad number,
                      etapa number
);
alter table requiere add constraint PK_requiere primary key (codigoMaterial, codigoEstacion);
alter table requiere add constraint FK_R_material foreign key (codigoMaterial) references material (codigo);
alter table requiere add constraint FK_R_estacion foreign key (codigoEstacion) references estacionProduccion (codigo);
alter table requiere add constraint NN_cantidad_req check (cantidad is not null);
alter table requiere add constraint CK_cantidad_req check (cantidad >0);
alter table requiere add constraint NN_etapa_req check (cantidad is not null);
create table suministro(
                        maximaCantidad number,
                        tiempoEntrega number,
                        codigoProveedor number,
                        codigoMaterial number
);
alter table suministro add constraint PK_suministro primary key (codigoProveedor, codigoMaterial);
alter table suministro add constraint FK_S_material foreign key (codigoMaterial) references material (codigo);
alter table suministro add constraint FK_S_proveedor foreign key (codigoProveedor) references proveedor (codigo);
alter table suministro add constraint CK_Maxcantidad_sumi check (maximacantidad >0);
alter table suministro add constraint CK_tiempoEntrega_sumi check (tiempoEntrega >0);         
alter table suministro add constraint NN_tiempoEntrega_sumi check (tiempoEntrega is not null);
alter table suministro add constraint NN_maxcan_sumi check (maximaCantidad is not null);
create table pedido (
                      codigo number,
                      estado varchar2(50),
                      cantidad number,
                      fechaPedido date,
                      fechaEsperada date,
                      fechaEntrega date,
                      codigoProducto number,
                      codigoEmpresa number,
                      codigoCliente number
);
alter table pedido add constraint PK_pedido primary key (codigo, codigoProducto, codigoCliente, codigoEmpresa);
alter table pedido add constraint FK_Ped_producto foreign key (codigoProducto) references producto (codigo);
alter table pedido add constraint FK_ped_cliente foreign key (codigoCliente) references Cliente (codigo);
alter table pedido add constraint FK_ped_empresa foreign key (codigoEmpresa) references administrador (codigo);
alter table pedido add constraint CK_cantidad_ped check (cantidad >0);
alter table pedido add constraint NN_cantidad_ped check (cantidad is not null);
alter table pedido add constraint NN_estado_ped check (estado is not null);
alter table pedido add constraint NN_fPEdido_ped check (fechaPedido is not null);
alter table pedido add constraint NN_fEsperada_ped check (fechaEsperada is not null);
alter table pedido add constraint CK_pedido_fechas check (fechaEsperada > fechaPedido);
alter table pedido add constraint CK_pedido_fechas2 check (fechaEntrega > fechaPedido);
create table pedidoMaterial (
                      codigo number,
                      cantidadPedida number,
                      fechaPedido date,
                      fechaEsperada date,
                      codigoMaterial number,
                      codigoProveedor number,
                      costo number
);
alter table pedidoMaterial add constraint PK_pedidoMaterial primary key (codigo);
alter table pedidoMaterial add constraint FK_pM_material foreign key (codigoMaterial) references material (codigo);
alter table pedidoMaterial add constraint FK_pm_proveedor foreign key (codigoProveedor) references proveedor (codigo);
alter table pedidoMaterial add constraint NN_fechaPedido_PM check (fechaPedido is not null);
alter table pedidoMaterial add constraint NN_fechaEsperada_PM check (fechaEsperada is not  null);
alter table pedidoMaterial add constraint NN_costo_PM check (costo is not null);
alter table pedidoMaterial add constraint NN_cantidadPedida_PM check (cantidadPedida is not null);
alter table pedidoMaterial add constraint CK_cantidadPedida_PM check (cantidadPedida >0);
alter table pedidoMaterial add constraint CK_costo_PM check (costo >=0);
alter table pedidoMaterial add constraint CK_fechaEsperada_PM check (fechaEsperada > fechaPedido);

CREATE SEQUENCE incremento_id_Pedido INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_cliente INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_esProduccion INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_etapaProduccion INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_material INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_operario INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_PedidoMaterial INCREMENT BY 1 START WITH 100;
CREATE SEQUENCE incremento_id_proceso INCREMENT BY 1 START WITH 100;

select login from administrador;
insert into material (cantidad, codigo, tipo, unidad, nombre, ultimoabastecimiento) values (10.5, incremento_id_material.NextVal, 'Componente','Test','prueba', TO_DATE('2015-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
