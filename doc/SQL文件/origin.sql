--
-- PostgreSQL database dump
--

\restrict xmBbh6aUx1wfZPM9Wf5GIFRUaO7FKvzVtTSabwv1Td02cQWh1Jgs3ag4O4F265n

-- Dumped from database version 17.6 (Debian 17.6-1.pgdg12+1)
-- Dumped by pg_dump version 17.6 (Debian 17.6-1.pgdg12+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: t_role; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.t_role (
    id bigint NOT NULL,
    role_name character varying(32) NOT NULL,
    role_key character varying(32) NOT NULL
);


ALTER TABLE public.t_role OWNER TO root;

--
-- Name: TABLE t_role; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON TABLE public.t_role IS '角色表';


--
-- Name: COLUMN t_role.id; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_role.id IS 'id';


--
-- Name: COLUMN t_role.role_name; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_role.role_name IS '角色名称';


--
-- Name: COLUMN t_role.role_key; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_role.role_key IS '角色唯一标识';


--
-- Name: t_user; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.t_user (
    id bigint NOT NULL,
    username character varying(60) NOT NULL,
    password character varying(60) NOT NULL,
    create_time timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp(6) without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    is_deleted smallint DEFAULT 0 NOT NULL,
    nickname character varying(50),
    avatar character varying(255),
    email character varying(255),
    phone character varying(11)
);


ALTER TABLE public.t_user OWNER TO root;

--
-- Name: TABLE t_user; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON TABLE public.t_user IS '用户表';


--
-- Name: COLUMN t_user.id; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.id IS 'id';


--
-- Name: COLUMN t_user.username; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.username IS '登录用户名';


--
-- Name: COLUMN t_user.password; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.password IS '密码';


--
-- Name: COLUMN t_user.create_time; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.create_time IS '创建时间';


--
-- Name: COLUMN t_user.update_time; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.update_time IS '最后一次更新时间';


--
-- Name: COLUMN t_user.is_deleted; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.is_deleted IS '逻辑删除：0：未删除 1：已删除';


--
-- Name: COLUMN t_user.nickname; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.nickname IS '用户昵称';


--
-- Name: COLUMN t_user.avatar; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.avatar IS '用户头像';


--
-- Name: COLUMN t_user.email; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.email IS '用户邮箱';


--
-- Name: COLUMN t_user.phone; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user.phone IS '手机号';


--
-- Name: t_user_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.t_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.t_user_id_seq OWNER TO root;

--
-- Name: t_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.t_user_id_seq OWNED BY public.t_user.id;


--
-- Name: t_user_role_rel; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.t_user_role_rel (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.t_user_role_rel OWNER TO root;

--
-- Name: TABLE t_user_role_rel; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON TABLE public.t_user_role_rel IS '用户角色关联表';


--
-- Name: COLUMN t_user_role_rel.id; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user_role_rel.id IS 'id';


--
-- Name: COLUMN t_user_role_rel.user_id; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user_role_rel.user_id IS '用户ID';


--
-- Name: COLUMN t_user_role_rel.role_id; Type: COMMENT; Schema: public; Owner: root
--

COMMENT ON COLUMN public.t_user_role_rel.role_id IS '角色ID';


--
-- Name: t_user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.t_user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.t_user_role_id_seq OWNER TO root;

--
-- Name: t_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.t_user_role_id_seq OWNED BY public.t_user_role_rel.id;


--
-- Name: t_user id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_user ALTER COLUMN id SET DEFAULT nextval('public.t_user_id_seq'::regclass);


--
-- Name: t_user_role_rel id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_user_role_rel ALTER COLUMN id SET DEFAULT nextval('public.t_user_role_id_seq'::regclass);


--
-- Data for Name: t_role; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.t_role (id, role_name, role_key) FROM stdin;
1	系统管理员	ROLE_SYSTEM_ADMIN
2	组织管理员	ROLE_ORG_ADMIN
3	部门管理员	ROLE_PART_ADMIN
4	组织用户	ROLE_ORG_USER
5	普通用户	ROLE_USER
\.


--
-- Data for Name: t_user; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.t_user (id, username, password, create_time, update_time, is_deleted, nickname, avatar, email, phone) FROM stdin;
6	test	$2a$10$9d7IG1WK7WqS.G0MT/BSje9sXSpXqVWXmJAeUkKRd24rOqvqmRe4S	2025-11-04 08:56:34.134152	2025-11-05 09:45:37.330087	0	\N	\N	\N	\N
5	origin	$2a$10$aBOF400tYUqde9s/eM3GYOTPnG51sdDET7Oxu9bK8/VF.vaJwmldO	2025-11-03 10:02:04.28619	2025-11-07 17:37:48.805704	0	\N	\N	\N	\N
\.


--
-- Data for Name: t_user_role_rel; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.t_user_role_rel (id, user_id, role_id) FROM stdin;
4	5	1
5	6	5
\.


--
-- Name: t_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.t_user_id_seq', 8, true);


--
-- Name: t_user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.t_user_role_id_seq', 5, true);


--
-- Name: t_role t_role_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_role
    ADD CONSTRAINT t_role_pkey PRIMARY KEY (id);


--
-- Name: t_role t_role_role_key_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_role
    ADD CONSTRAINT t_role_role_key_key UNIQUE (role_key);


--
-- Name: t_user t_user_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_user
    ADD CONSTRAINT t_user_pkey PRIMARY KEY (id);


--
-- Name: t_user_role_rel t_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_user_role_rel
    ADD CONSTRAINT t_user_role_pkey PRIMARY KEY (id);


--
-- Name: t_user t_user_username_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.t_user
    ADD CONSTRAINT t_user_username_key UNIQUE (username);


--
-- PostgreSQL database dump complete
--

\unrestrict xmBbh6aUx1wfZPM9Wf5GIFRUaO7FKvzVtTSabwv1Td02cQWh1Jgs3ag4O4F265n

