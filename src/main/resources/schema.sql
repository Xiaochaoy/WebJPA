DROP DATABASE jpamagazines;

CREATE DATABASE jpamagazines;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.campeon (
                                id smallint NOT NULL,
                                nom character varying(20),
                                rol character varying(20),
                                historia text
);


ALTER TABLE public.campeon OWNER TO bibliotecari;

ALTER TABLE public.campeon ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.campeon_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.rol (
    rol character varying(20) NOT NULL
);

ALTER TABLE public.rol OWNER TO bibliotecari;

COPY public.campeon (id, nom, rol, historia) FROM stdin;
\.

COPY public.rol (rol) FROM stdin;
\.

SELECT pg_catalog.setval('public.campeon_id_seq', 1, false);

ALTER TABLE ONLY public.campeon
    ADD CONSTRAINT campeon_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (rol);

ALTER TABLE ONLY public.campeon
    ADD CONSTRAINT campeon_rol_fkey FOREIGN KEY (rol) REFERENCES public.rol(rol);
