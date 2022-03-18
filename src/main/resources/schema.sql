CREATE TABLE rol(rol varchar(20) primary key);

CREATE TABLE campeon(id smallint primary key GENERATED ALWAYS AS IDENTITY,
                    nom varchar(20),
                    rol varchar(20) references rol(rol),
                    historia text);