USE computec;

CREATE TABLE cliente (
                         rut VARCHAR(12) PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         direccion VARCHAR(150) NOT NULL,
                         comuna VARCHAR(50) NOT NULL,
                         correo VARCHAR(100) NOT NULL,
                         telefono VARCHAR(20) NOT NULL
);

CREATE TABLE equipo (
                        id_equipo INT AUTO_INCREMENT PRIMARY KEY,
                        modelo VARCHAR(100) NOT NULL,
                        cpu VARCHAR(50) NOT NULL,
                        disco_mb INT NOT NULL,
                        ram_gb INT NOT NULL,
                        precio DECIMAL(10,2) NOT NULL,
                        tipo ENUM('Desktop', 'Laptop') NOT NULL,
                        potencia_fuente VARCHAR(20),
                        factor_forma VARCHAR(20),
                        pantalla_pulgadas DECIMAL(4,1),
                        touch BOOLEAN,
                        puertos_usb INT
);

CREATE TABLE venta (
                       id_venta INT AUTO_INCREMENT PRIMARY KEY,
                       rut_cliente VARCHAR(12) NOT NULL,
                       id_equipo INT NOT NULL,
                       fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
                       descuento DECIMAL(5,2) DEFAULT 0,
                       total DECIMAL(10,2) NOT NULL,
                       FOREIGN KEY (rut_cliente) REFERENCES cliente(rut),
                       FOREIGN KEY (id_equipo) REFERENCES equipo(id_equipo)
);