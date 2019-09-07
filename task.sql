-- Table: public.task

-- DROP TABLE public.task;

CREATE TABLE public.task
(
  id serial NOT NULL,
  name character varying,
  description character varying,
  creation_date timestamp without time zone,
  limit_date timestamp without time zone,
  update_date timestamp without time zone,
  deleted boolean,
  file bytea,
  CONSTRAINT pk_task PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.task
  OWNER TO postgres;