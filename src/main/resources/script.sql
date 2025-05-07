
DROP TABLE IF EXISTS usuario_dto_telefonos_list CASCADE;
DROP TABLE IF EXISTS usuario_dto CASCADE;

CREATE TABLE usuario_dto (
    id VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL,
    contrasenia VARCHAR(255),
    correo VARCHAR(255),
    creado VARCHAR(255),
    modificado VARCHAR(255),
    nombre VARCHAR(255),
    token VARCHAR(255),
    ultimo_login VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE usuario_dto_telefonos_list (
    id VARCHAR(255) NOT NULL,
    codigo_ciudad VARCHAR(255),
    codigo_pais VARCHAR(255),
    numero VARCHAR(255)
);

ALTER TABLE usuario_dto
ADD CONSTRAINT UK_8l8rnjnesfnk7fpgsegtki7rj UNIQUE (correo);

ALTER TABLE usuario_dto_telefonos_list
ADD CONSTRAINT FKbn611ivl1iapk4vd0yrwjio7f FOREIGN KEY (id) REFERENCES usuario_dto(id);