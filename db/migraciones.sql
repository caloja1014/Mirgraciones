create database migraciones;
use migraciones;

create table MIGRANTE (
	cedula varchar(10) primary key,
    nombre varchar(100),
    sexo char(1),
    anio_nac int,
    edad int,
    nacionalidad varchar(100),
    clase_migratoria long
    );

create table EMPLEADO (
	cedula varchar(10) primary key,
    nombre varchar(100),
    estado varchar(100) default("disponible")
);

create table PUESTO (
	id int(10) primary key auto_increment,
    estado varchar(50) default("libre")
);

create table REGISTRO (
	id int auto_increment,
    fecha_registro date not null,
    migrante varchar(10) not null,
    tipo_movilizacion varchar(10) not null,
    via_transporte varchar(100) not null,
    pais_dest varchar(1000) not null,
    tiempo_estadia varchar(100) null,
    fecha_salida date null,
    fecha_regreso date null,
    pais_res varchar(100),
    estado varchar(100) default("disponible"),
    primary key(id, migrante),
foreign key (migrante) references MIGRANTE(cedula)
);
insert into migrante values("0990040217", "nicole", "F",1999,20,"ecuatoriana","normal");
insert into migrante values("0927644825", "nicole", "F",1999,20,"ecuatoriana","normal");
insert into registro (fecha_registro,migrante,tipo_movilizacion,via_transporte,pais_dest,tiempo_estadia,fecha_salida,fecha_regreso,pais_res) values ("2019-10-1","0990040217","entrada","transporte","pais",10,"2019-10-1","2019-01-01","ecuador");
insert into registro (fecha_registro,migrante,tipo_movilizacion,via_transporte,pais_dest,tiempo_estadia,fecha_salida,fecha_regreso,pais_res) values ("2019-10-1","0927644825","entrada","transporte","pais",10,"2019-10-1","2019-01-01","ecuador");


select *  from migrante m join registro r on (m.cedula=r.migrante) where estado ="disponible" ;