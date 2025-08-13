CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje VARCHAR(500) NOT NULL UNIQUE,
    fecha_creacion DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);