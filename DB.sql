PGDMP      &            
    |            CafeDB    16.4    16.4 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16412    CafeDB    DATABASE     t   CREATE DATABASE "CafeDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE "CafeDB";
                postgres    false            �            1255    40995    update_desk_status_on_reserve()    FUNCTION     �   CREATE FUNCTION public.update_desk_status_on_reserve() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE public.desk
    SET status = 'reserve'
    WHERE id = NEW.desk_id;

    RETURN NEW;
END;
$$;
 6   DROP FUNCTION public.update_desk_status_on_reserve();
       public          postgres    false            �            1259    16520    award    TABLE     �   CREATE TABLE public.award (
    id integer NOT NULL,
    title character varying(40) NOT NULL,
    date_of_receipt date NOT NULL,
    institution_id integer NOT NULL
);
    DROP TABLE public.award;
       public         heap    postgres    false            �            1259    16519    award_id_seq    SEQUENCE     �   CREATE SEQUENCE public.award_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.award_id_seq;
       public          postgres    false    224            �           0    0    award_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.award_id_seq OWNED BY public.award.id;
          public          postgres    false    223            �            1259    24630    calculation    TABLE     �   CREATE TABLE public.calculation (
    id integer NOT NULL,
    dish_id integer NOT NULL,
    ingredient_id integer NOT NULL,
    ingredient_weight integer NOT NULL,
    CONSTRAINT calculation_ingredient_weight_check CHECK ((ingredient_weight > 0))
);
    DROP TABLE public.calculation;
       public         heap    postgres    false            �            1259    24629    calculation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.calculation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.calculation_id_seq;
       public          postgres    false    250            �           0    0    calculation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.calculation_id_seq OWNED BY public.calculation.id;
          public          postgres    false    249            �            1259    16486    desk    TABLE     �   CREATE TABLE public.desk (
    id integer NOT NULL,
    number_of_seats integer NOT NULL,
    status character varying(20) DEFAULT 'vacant'::character varying NOT NULL,
    institution_id integer NOT NULL
);
    DROP TABLE public.desk;
       public         heap    postgres    false            �            1259    16485    desk_id_seq    SEQUENCE     �   CREATE SEQUENCE public.desk_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.desk_id_seq;
       public          postgres    false    218            �           0    0    desk_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.desk_id_seq OWNED BY public.desk.id;
          public          postgres    false    217            �            1259    16542    dish    TABLE       CREATE TABLE public.dish (
    id integer NOT NULL,
    title character varying(60) NOT NULL,
    descriptions character varying(200) NOT NULL,
    recipe character varying(100) NOT NULL,
    institution_id integer NOT NULL,
    price numeric(6,2) NOT NULL
);
    DROP TABLE public.dish;
       public         heap    postgres    false            �            1259    16541    dish_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.dish_id_seq;
       public          postgres    false    228            �           0    0    dish_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.dish_id_seq OWNED BY public.dish.id;
          public          postgres    false    227            �            1259    16685 
   dish_order    TABLE     �   CREATE TABLE public.dish_order (
    id integer NOT NULL,
    user_worker_id integer NOT NULL,
    dish_id integer NOT NULL,
    count_dish integer,
    order_id integer NOT NULL
);
    DROP TABLE public.dish_order;
       public         heap    postgres    false            �            1259    16684    dish_order_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.dish_order_id_seq;
       public          postgres    false    236            �           0    0    dish_order_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.dish_order_id_seq OWNED BY public.dish_order.id;
          public          postgres    false    235            �            1259    16716    dish_tag    TABLE     u   CREATE TABLE public.dish_tag (
    id integer NOT NULL,
    dish_id integer NOT NULL,
    tag_id integer NOT NULL
);
    DROP TABLE public.dish_tag;
       public         heap    postgres    false            �            1259    16715    dish_tag_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.dish_tag_id_seq;
       public          postgres    false    240            �           0    0    dish_tag_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.dish_tag_id_seq OWNED BY public.dish_tag.id;
          public          postgres    false    239            �            1259    16668    favorite_dish    TABLE     �   CREATE TABLE public.favorite_dish (
    id integer NOT NULL,
    user_client_id integer NOT NULL,
    dish_id integer NOT NULL
);
 !   DROP TABLE public.favorite_dish;
       public         heap    postgres    false            �            1259    16667    favorite_dish_id_seq    SEQUENCE     �   CREATE SEQUENCE public.favorite_dish_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.favorite_dish_id_seq;
       public          postgres    false    234            �           0    0    favorite_dish_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.favorite_dish_id_seq OWNED BY public.favorite_dish.id;
          public          postgres    false    233            �            1259    24614 
   ingredient    TABLE     �   CREATE TABLE public.ingredient (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    available integer DEFAULT 0 NOT NULL,
    storage_facility_id integer NOT NULL,
    CONSTRAINT ingredient_available_check CHECK ((available >= 0))
);
    DROP TABLE public.ingredient;
       public         heap    postgres    false            �            1259    24613    ingredient_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ingredient_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.ingredient_id_seq;
       public          postgres    false    248            �           0    0    ingredient_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.ingredient_id_seq OWNED BY public.ingredient.id;
          public          postgres    false    247            �            1259    16479    institution    TABLE       CREATE TABLE public.institution (
    id integer NOT NULL,
    title character varying(40) NOT NULL,
    description character varying(100) NOT NULL,
    number_of_stars numeric(3,2),
    time_open time without time zone NOT NULL,
    time_close time without time zone NOT NULL
);
    DROP TABLE public.institution;
       public         heap    postgres    false            �            1259    16478    institution_id_seq    SEQUENCE     �   CREATE SEQUENCE public.institution_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.institution_id_seq;
       public          postgres    false    216            �           0    0    institution_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.institution_id_seq OWNED BY public.institution.id;
          public          postgres    false    215            �            1259    16647    order_table    TABLE     �  CREATE TABLE public.order_table (
    id integer NOT NULL,
    status character varying(20) DEFAULT 'In the queue'::character varying NOT NULL,
    wishes character varying(100),
    date_of_creation timestamp without time zone NOT NULL,
    closing_date timestamp without time zone,
    desk_id integer NOT NULL,
    user_client_id integer NOT NULL,
    price numeric(6,2) NOT NULL
);
    DROP TABLE public.order_table;
       public         heap    postgres    false            �            1259    16646    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    232            �           0    0    orders_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public.orders_id_seq OWNED BY public.order_table.id;
          public          postgres    false    231            �            1259    16532 	   privilege    TABLE     p   CREATE TABLE public.privilege (
    id integer NOT NULL,
    name character varying(40),
    role_id integer
);
    DROP TABLE public.privilege;
       public         heap    postgres    false            �            1259    16531    privilege_id_seq    SEQUENCE     �   CREATE SEQUENCE public.privilege_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.privilege_id_seq;
       public          postgres    false    226            �           0    0    privilege_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.privilege_id_seq OWNED BY public.privilege.id;
          public          postgres    false    225            �            1259    16630    reserve    TABLE     �   CREATE TABLE public.reserve (
    id integer NOT NULL,
    date_of_creation timestamp without time zone NOT NULL,
    desk_id integer NOT NULL,
    user_client_id integer NOT NULL
);
    DROP TABLE public.reserve;
       public         heap    postgres    false            �            1259    16629    reserve_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reserve_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.reserve_id_seq;
       public          postgres    false    230            �           0    0    reserve_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.reserve_id_seq OWNED BY public.reserve.id;
          public          postgres    false    229            �            1259    16733    review    TABLE     �  CREATE TABLE public.review (
    id integer NOT NULL,
    user_client_id integer NOT NULL,
    stars_food numeric(3,2) DEFAULT 0,
    stars_service numeric(3,2) DEFAULT 0,
    stars_atmosphere numeric(3,2) DEFAULT 0,
    description text,
    date_of_creation timestamp without time zone NOT NULL,
    institution_id integer NOT NULL,
    CONSTRAINT review_stars_atmosphere_check CHECK (((stars_atmosphere <= (5)::numeric) AND (stars_atmosphere >= (0)::numeric))),
    CONSTRAINT review_stars_food_check CHECK (((stars_food <= (5)::numeric) AND (stars_food >= (0)::numeric))),
    CONSTRAINT review_stars_service_check CHECK (((stars_service <= (5)::numeric) AND (stars_service >= (0)::numeric)))
);
    DROP TABLE public.review;
       public         heap    postgres    false            �            1259    16732    review_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.review_id_seq;
       public          postgres    false    242            �           0    0    review_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;
          public          postgres    false    241            �            1259    16503    role    TABLE     V   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(40)
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    16502    role_id_seq    SEQUENCE     �   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    220            �           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    219            �            1259    24605    storage_facility    TABLE     �   CREATE TABLE public.storage_facility (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    address character varying(50) NOT NULL,
    institution_id integer
);
 $   DROP TABLE public.storage_facility;
       public         heap    postgres    false            �            1259    24604    storage_facility_id_seq    SEQUENCE     �   CREATE SEQUENCE public.storage_facility_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.storage_facility_id_seq;
       public          postgres    false    246            �           0    0    storage_facility_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.storage_facility_id_seq OWNED BY public.storage_facility.id;
          public          postgres    false    245            �            1259    16707    tag    TABLE     ^   CREATE TABLE public.tag (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);
    DROP TABLE public.tag;
       public         heap    postgres    false            �            1259    16706 
   tag_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.tag_id_seq;
       public          postgres    false    238            �           0    0 
   tag_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;
          public          postgres    false    237            �            1259    16758    user_institution    TABLE     �   CREATE TABLE public.user_institution (
    id integer NOT NULL,
    user_worker_id integer NOT NULL,
    institution_id integer NOT NULL
);
 $   DROP TABLE public.user_institution;
       public         heap    postgres    false            �            1259    16757    user_institution_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_institution_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.user_institution_id_seq;
       public          postgres    false    244            �           0    0    user_institution_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.user_institution_id_seq OWNED BY public.user_institution.id;
          public          postgres    false    243            �            1259    16510 
   user_table    TABLE     Y  CREATE TABLE public.user_table (
    id integer NOT NULL,
    first_name character varying(40),
    last_name character varying(40),
    phone numeric(12,0) NOT NULL,
    role_id integer NOT NULL,
    email character varying(40),
    password text,
    CONSTRAINT user_table_email_check CHECK (((email)::text ~ '^[^@]+@[^@]+\.[^@]+$'::text))
);
    DROP TABLE public.user_table;
       public         heap    postgres    false            �            1259    16509    user_table_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_table_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.user_table_id_seq;
       public          postgres    false    222            �           0    0    user_table_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_table_id_seq OWNED BY public.user_table.id;
          public          postgres    false    221            �           2604    16523    award id    DEFAULT     d   ALTER TABLE ONLY public.award ALTER COLUMN id SET DEFAULT nextval('public.award_id_seq'::regclass);
 7   ALTER TABLE public.award ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    24633    calculation id    DEFAULT     p   ALTER TABLE ONLY public.calculation ALTER COLUMN id SET DEFAULT nextval('public.calculation_id_seq'::regclass);
 =   ALTER TABLE public.calculation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    250    249    250            �           2604    16489    desk id    DEFAULT     b   ALTER TABLE ONLY public.desk ALTER COLUMN id SET DEFAULT nextval('public.desk_id_seq'::regclass);
 6   ALTER TABLE public.desk ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    16545    dish id    DEFAULT     b   ALTER TABLE ONLY public.dish ALTER COLUMN id SET DEFAULT nextval('public.dish_id_seq'::regclass);
 6   ALTER TABLE public.dish ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            �           2604    16688    dish_order id    DEFAULT     n   ALTER TABLE ONLY public.dish_order ALTER COLUMN id SET DEFAULT nextval('public.dish_order_id_seq'::regclass);
 <   ALTER TABLE public.dish_order ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    236    236            �           2604    16719    dish_tag id    DEFAULT     j   ALTER TABLE ONLY public.dish_tag ALTER COLUMN id SET DEFAULT nextval('public.dish_tag_id_seq'::regclass);
 :   ALTER TABLE public.dish_tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    240    239    240            �           2604    16671    favorite_dish id    DEFAULT     t   ALTER TABLE ONLY public.favorite_dish ALTER COLUMN id SET DEFAULT nextval('public.favorite_dish_id_seq'::regclass);
 ?   ALTER TABLE public.favorite_dish ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    234    234            �           2604    24617    ingredient id    DEFAULT     n   ALTER TABLE ONLY public.ingredient ALTER COLUMN id SET DEFAULT nextval('public.ingredient_id_seq'::regclass);
 <   ALTER TABLE public.ingredient ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    248    247    248            �           2604    16482    institution id    DEFAULT     p   ALTER TABLE ONLY public.institution ALTER COLUMN id SET DEFAULT nextval('public.institution_id_seq'::regclass);
 =   ALTER TABLE public.institution ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    16650    order_table id    DEFAULT     k   ALTER TABLE ONLY public.order_table ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 =   ALTER TABLE public.order_table ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    16535    privilege id    DEFAULT     l   ALTER TABLE ONLY public.privilege ALTER COLUMN id SET DEFAULT nextval('public.privilege_id_seq'::regclass);
 ;   ALTER TABLE public.privilege ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    16633 
   reserve id    DEFAULT     h   ALTER TABLE ONLY public.reserve ALTER COLUMN id SET DEFAULT nextval('public.reserve_id_seq'::regclass);
 9   ALTER TABLE public.reserve ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2604    16736 	   review id    DEFAULT     f   ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);
 8   ALTER TABLE public.review ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    241    242    242            �           2604    16506    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    24608    storage_facility id    DEFAULT     z   ALTER TABLE ONLY public.storage_facility ALTER COLUMN id SET DEFAULT nextval('public.storage_facility_id_seq'::regclass);
 B   ALTER TABLE public.storage_facility ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    245    246    246            �           2604    16710    tag id    DEFAULT     `   ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);
 5   ALTER TABLE public.tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    238    237    238            �           2604    16761    user_institution id    DEFAULT     z   ALTER TABLE ONLY public.user_institution ALTER COLUMN id SET DEFAULT nextval('public.user_institution_id_seq'::regclass);
 B   ALTER TABLE public.user_institution ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    16513    user_table id    DEFAULT     n   ALTER TABLE ONLY public.user_table ALTER COLUMN id SET DEFAULT nextval('public.user_table_id_seq'::regclass);
 <   ALTER TABLE public.user_table ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222                      0    16520    award 
   TABLE DATA           K   COPY public.award (id, title, date_of_receipt, institution_id) FROM stdin;
    public          postgres    false    224   y�       �          0    24630    calculation 
   TABLE DATA           T   COPY public.calculation (id, dish_id, ingredient_id, ingredient_weight) FROM stdin;
    public          postgres    false    250   ��       y          0    16486    desk 
   TABLE DATA           K   COPY public.desk (id, number_of_seats, status, institution_id) FROM stdin;
    public          postgres    false    218   ��       �          0    16542    dish 
   TABLE DATA           V   COPY public.dish (id, title, descriptions, recipe, institution_id, price) FROM stdin;
    public          postgres    false    228   <�       �          0    16685 
   dish_order 
   TABLE DATA           W   COPY public.dish_order (id, user_worker_id, dish_id, count_dish, order_id) FROM stdin;
    public          postgres    false    236   �       �          0    16716    dish_tag 
   TABLE DATA           7   COPY public.dish_tag (id, dish_id, tag_id) FROM stdin;
    public          postgres    false    240   ��       �          0    16668    favorite_dish 
   TABLE DATA           D   COPY public.favorite_dish (id, user_client_id, dish_id) FROM stdin;
    public          postgres    false    234   ��       �          0    24614 
   ingredient 
   TABLE DATA           N   COPY public.ingredient (id, name, available, storage_facility_id) FROM stdin;
    public          postgres    false    248   ��       w          0    16479    institution 
   TABLE DATA           e   COPY public.institution (id, title, description, number_of_stars, time_open, time_close) FROM stdin;
    public          postgres    false    216   '�       �          0    16647    order_table 
   TABLE DATA           y   COPY public.order_table (id, status, wishes, date_of_creation, closing_date, desk_id, user_client_id, price) FROM stdin;
    public          postgres    false    232   ��       �          0    16532 	   privilege 
   TABLE DATA           6   COPY public.privilege (id, name, role_id) FROM stdin;
    public          postgres    false    226   ��       �          0    16630    reserve 
   TABLE DATA           P   COPY public.reserve (id, date_of_creation, desk_id, user_client_id) FROM stdin;
    public          postgres    false    230   ��       �          0    16733    review 
   TABLE DATA           �   COPY public.review (id, user_client_id, stars_food, stars_service, stars_atmosphere, description, date_of_creation, institution_id) FROM stdin;
    public          postgres    false    242   5�       {          0    16503    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    220   ��       �          0    24605    storage_facility 
   TABLE DATA           M   COPY public.storage_facility (id, name, address, institution_id) FROM stdin;
    public          postgres    false    246   )�       �          0    16707    tag 
   TABLE DATA           '   COPY public.tag (id, name) FROM stdin;
    public          postgres    false    238   ��       �          0    16758    user_institution 
   TABLE DATA           N   COPY public.user_institution (id, user_worker_id, institution_id) FROM stdin;
    public          postgres    false    244   ��       }          0    16510 
   user_table 
   TABLE DATA           `   COPY public.user_table (id, first_name, last_name, phone, role_id, email, password) FROM stdin;
    public          postgres    false    222   ��       �           0    0    award_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.award_id_seq', 1, false);
          public          postgres    false    223            �           0    0    calculation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.calculation_id_seq', 1, false);
          public          postgres    false    249            �           0    0    desk_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.desk_id_seq', 23, true);
          public          postgres    false    217            �           0    0    dish_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.dish_id_seq', 10, true);
          public          postgres    false    227            �           0    0    dish_order_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.dish_order_id_seq', 33, true);
          public          postgres    false    235            �           0    0    dish_tag_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dish_tag_id_seq', 1, false);
          public          postgres    false    239            �           0    0    favorite_dish_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.favorite_dish_id_seq', 8, true);
          public          postgres    false    233            �           0    0    ingredient_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.ingredient_id_seq', 1, true);
          public          postgres    false    247            �           0    0    institution_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.institution_id_seq', 7, true);
          public          postgres    false    215            �           0    0    orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 48, true);
          public          postgres    false    231            �           0    0    privilege_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.privilege_id_seq', 1, false);
          public          postgres    false    225            �           0    0    reserve_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.reserve_id_seq', 9, true);
          public          postgres    false    229            �           0    0    review_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.review_id_seq', 11, true);
          public          postgres    false    241            �           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 4, true);
          public          postgres    false    219            �           0    0    storage_facility_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.storage_facility_id_seq', 2, true);
          public          postgres    false    245            �           0    0 
   tag_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.tag_id_seq', 1, false);
          public          postgres    false    237            �           0    0    user_institution_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.user_institution_id_seq', 1, false);
          public          postgres    false    243            �           0    0    user_table_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.user_table_id_seq', 16, true);
          public          postgres    false    221            �           2606    16525    award award_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.award
    ADD CONSTRAINT award_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.award DROP CONSTRAINT award_pkey;
       public            postgres    false    224            �           2606    24636    calculation calculation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_pkey;
       public            postgres    false    250            �           2606    16491    desk desk_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.desk
    ADD CONSTRAINT desk_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.desk DROP CONSTRAINT desk_pkey;
       public            postgres    false    218            �           2606    16690    dish_order dish_order_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_pkey;
       public            postgres    false    236            �           2606    16549    dish dish_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.dish
    ADD CONSTRAINT dish_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_pkey;
       public            postgres    false    228            �           2606    16721    dish_tag dish_tag_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_pkey;
       public            postgres    false    240            �           2606    16673     favorite_dish favorite_dish_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_pkey;
       public            postgres    false    234            �           2606    24623    ingredient ingredient_name_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_name_key UNIQUE (name);
 H   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_name_key;
       public            postgres    false    248            �           2606    24621    ingredient ingredient_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_pkey;
       public            postgres    false    248            �           2606    16484    institution institution_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.institution DROP CONSTRAINT institution_pkey;
       public            postgres    false    216            �           2606    16652    order_table orders_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_pkey;
       public            postgres    false    232            �           2606    16775    user_table phone_unique 
   CONSTRAINT     S   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT phone_unique UNIQUE (phone);
 A   ALTER TABLE ONLY public.user_table DROP CONSTRAINT phone_unique;
       public            postgres    false    222            �           2606    16635    reserve reserve_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_pkey;
       public            postgres    false    230            �           2606    16746    review review_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.review DROP CONSTRAINT review_pkey;
       public            postgres    false    242            �           2606    16508    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    220            �           2606    24612 *   storage_facility storage_facility_name_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_name_key UNIQUE (name);
 T   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_name_key;
       public            postgres    false    246            �           2606    24610 &   storage_facility storage_facility_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_pkey;
       public            postgres    false    246            �           2606    16714    tag tag_name_key 
   CONSTRAINT     K   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_name_key UNIQUE (name);
 :   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_name_key;
       public            postgres    false    238            �           2606    16712    tag tag_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_pkey;
       public            postgres    false    238            �           2606    40999    favorite_dish user_dish_unique 
   CONSTRAINT     l   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT user_dish_unique UNIQUE (user_client_id, dish_id);
 H   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT user_dish_unique;
       public            postgres    false    234    234            �           2606    16763 &   user_institution user_institution_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_pkey;
       public            postgres    false    244            �           2606    16778    user_table user_table_email_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_email_key UNIQUE (email);
 I   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_email_key;
       public            postgres    false    222            �           2606    16628    user_table user_table_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_pkey;
       public            postgres    false    222            �           2620    40996    reserve set_desk_status_true    TRIGGER     �   CREATE TRIGGER set_desk_status_true AFTER INSERT ON public.reserve FOR EACH ROW EXECUTE FUNCTION public.update_desk_status_on_reserve();
 5   DROP TRIGGER set_desk_status_true ON public.reserve;
       public          postgres    false    251    230            �           2606    16526    award award_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.award
    ADD CONSTRAINT award_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 I   ALTER TABLE ONLY public.award DROP CONSTRAINT award_institution_id_fkey;
       public          postgres    false    224    216    4257            �           2606    24637 $   calculation calculation_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_dish_id_fkey;
       public          postgres    false    4271    228    250            �           2606    24642 *   calculation calculation_ingredient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(id) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_ingredient_id_fkey;
       public          postgres    false    250    4299    248            �           2606    16492    desk desk_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.desk
    ADD CONSTRAINT desk_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 G   ALTER TABLE ONLY public.desk DROP CONSTRAINT desk_institution_id_fkey;
       public          postgres    false    218    4257    216            �           2606    16550    dish dish_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish
    ADD CONSTRAINT dish_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 G   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_institution_id_fkey;
       public          postgres    false    4257    228    216            �           2606    16701 "   dish_order dish_order_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id);
 L   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_dish_id_fkey;
       public          postgres    false    228    236    4271            �           2606    16786 #   dish_order dish_order_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.order_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_order_id_fkey;
       public          postgres    false    236    232    4275            �           2606    16691 )   dish_order dish_order_user_worker_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_user_worker_id_fkey FOREIGN KEY (user_worker_id) REFERENCES public.user_table(id);
 S   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_user_worker_id_fkey;
       public          postgres    false    236    222    4267            �           2606    16722    dish_tag dish_tag_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_dish_id_fkey;
       public          postgres    false    240    228    4271            �           2606    16727    dish_tag dish_tag_tag_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_tag_id_fkey;
       public          postgres    false    238    240    4285            �           2606    16679 (   favorite_dish favorite_dish_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_dish_id_fkey;
       public          postgres    false    234    228    4271            �           2606    16674 /   favorite_dish favorite_dish_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 Y   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_user_client_id_fkey;
       public          postgres    false    4267    234    222            �           2606    24624 .   ingredient ingredient_storage_facility_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_storage_facility_id_fkey FOREIGN KEY (storage_facility_id) REFERENCES public.storage_facility(id) ON UPDATE CASCADE ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_storage_facility_id_fkey;
       public          postgres    false    248    4295    246            �           2606    16657    order_table orders_desk_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_desk_id_fkey FOREIGN KEY (desk_id) REFERENCES public.desk(id);
 I   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_desk_id_fkey;
       public          postgres    false    4259    232    218            �           2606    16662 &   order_table orders_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id);
 P   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_user_client_id_fkey;
       public          postgres    false    222    232    4267            �           2606    16536     privilege privilege_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.privilege
    ADD CONSTRAINT privilege_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.privilege DROP CONSTRAINT privilege_role_id_fkey;
       public          postgres    false    4261    220    226            �           2606    16636    reserve reserve_desk_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_desk_id_fkey FOREIGN KEY (desk_id) REFERENCES public.desk(id) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_desk_id_fkey;
       public          postgres    false    218    4259    230            �           2606    16641 #   reserve reserve_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_user_client_id_fkey;
       public          postgres    false    4267    222    230            �           2606    16752 !   review review_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.review DROP CONSTRAINT review_institution_id_fkey;
       public          postgres    false    242    216    4257            �           2606    16747 !   review review_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.review DROP CONSTRAINT review_user_client_id_fkey;
       public          postgres    false    4267    222    242            �           2606    40984 5   storage_facility storage_facility_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_institution_id_fkey;
       public          postgres    false    246    216    4257            �           2606    16769 5   user_institution user_institution_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_institution_id_fkey;
       public          postgres    false    244    216    4257            �           2606    16764 5   user_institution user_institution_user_worker_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_user_worker_id_fkey FOREIGN KEY (user_worker_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_user_worker_id_fkey;
       public          postgres    false    222    4267    244            �           2606    16514 "   user_table user_table_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 L   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_role_id_fkey;
       public          postgres    false    4261    220    222                  x������ � �      �      x������ � �      y   y   x�U�K�@C��aP�̧��Q5��.���'A���q,+�xv(%��̔�}��x�%���K�,��z� a�����O���=�?_mm��D\m��l�NKL���~+$��8�oD�-FAk      �   �   x�m�;
1����*\�$�I��eة��n���S�KЁ���l���\Q�G��|�'$��p<��돻�F}r��Td�b�8P�Ik�D̈�`&��b�X,�f������BN�r� mĚ�<A�+
�P�(Q𴆬*�"#���O%�����d[���3�p	8�d�O�����d�"�q�	/+��c��W罻�v�Q��»A      �   j   x�=��1�3*&���^�A8��3�X����÷0��@]>'����S�G$�+�Bw
S�?���0�i�l]&���zA�ȅ��mD�\���ê�      �      x������ � �      �   ,   x�3�44��2Q�\� ڌ�Dq��(c.e����� ��c      �   -   x�" ��1	Картомпля	15000	1
\.


K�H      w   q   x�3�0��.��[9/̻�������z&���V&&V@��".FpF�F�5��j�9�g^q�!�Kjq2T��%D���Tc��Ɯ~�~�PC���c���� ^�Wc      �   �  x����n�0���S��a-{f�Ĺ-H�
	����M�¶)٬V�!>^N��J|�
�7�I�f3� ����g��M�*D��Ue���&FO ���L�њۮ�p��VZq�/��Xϛ�Z5�x>{60Z�y�Κ0��;�'y.��i!��e����`���h��+���y=X���ń����N�l'L�:/�u)��U�#͌��ք1¢m@��L6e!���X��������]y��� u+�T:G���	H�݀���M)O�Z^T�Z�V�I�th��(�8�p��Ղd��N�w�/*I}\������c��j�(r��@e=i6����d�mrDS�QA���b�&��y}�˺��y�WJ3�IR��1���E�x���x��>�?c~U�%�}����n�8��Ɉ��E�B���K�a�m�u�x�юIRf�Zth�^�����B��k@]����`,ēh����,n��L��M]��JL�013�"Mdxd�%� b�E����:M�� ��2�	���Z1k���YԞҺ�8f��!�>�M#���|���gx%N[��*��Y�Mq,_��b^�5��#�B���Lb��Õ���͛p�y>�͛ͫ�E�O2|?7��ՠ���8��H!!�����D,�%4|��Q�#���p~l޶�ͻ�1\�o�'�C{���$�NX����G�Lt7���#�}0.�`0]��Y�+`t;���>� �L,�2:n�"?��qu�~�ܕ      �      x������ � �      �   ^   x�m��� �o:�K��0���!T����.I�+�h~�f�%٠�!B�F�щ��1rXysT��3ze7��[�Lsέ~�i���k�G�!�      �   �  x�m�An1E��S� �r��v���$3+DD	��Q�e�Āi`H�B�F|w'�b�ե���_���	�{�.��ӫ�ԟz[O�Y���zcQ6���Fw�����w��G��a�r@t��R*���$���A����[O��;�����N�D� ���'
��K��/`�rl��5�M�GӠ�7u1�����nZ3��"!%#]l:���t>陞����{��Bߢm��s�s��7�K��xǢ�u����Ӻ�'3@��ѱ�lr����-��<��#47@Y��]�Tz�,l[��/�����Ь,�[�7���	�/>��c�G!�F{��[ĺC��5�<�Z�ǎ�e�/�}	�xvy`""?Q���xy8�B���S�6Fm,�'������6xf;�#Rj9�(����/Z�      {   3   x�3���q�w����2���=C\����2>��~!\&��P*F��� �E      �   V   x�3估�®�/l��Ő�¼[.l��xa��M�/��S�0Ho��(�$�2��1~\FH�ph�5\l�j3����� Q�A�      �      x������ � �      �      x������ � �      }   �  x�e�[s�0���������^[�N_(�ܔ r��G�vt�d&�'k��^� |��S��x�4"/����wHx?O/N+/�8ޡ�~4H7~��w� �U�]���Q��b,)+�s��pB{={���F����W����KY��Q�w�Ԭ�z�F�&}�f�_��qvJ5�;�<��#=��+��*��6�II�U��Aۑ�P4nb-��R�N�s��'�8B�+|��y|�� �R� ze�-�Nt�Wg�q��NmS�O�?����4�IhO�Ȥ�����9�r_%�n�G�o�^1�0���Q`h҉U�d���Mv�Es~�.��S�lo��Z�7�|G<�>g�@�ҥGH����o�W��4a:��v�W�Paa.�(ُ�r"��9âm(Қ�*%�"�.��1���hV����	��K�1|�g��:�Q��KX��k���_��O�`���~�c�!$t�/����M�     