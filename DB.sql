PGDMP                  
    |            CafeIPZ    16.5    16.5 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    18153    CafeIPZ    DATABASE     �   CREATE DATABASE "CafeIPZ" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Ukrainian_Ukraine.1251';
    DROP DATABASE "CafeIPZ";
                postgres    false            �            1255    18154 &   after_closing_order_trigger_function()    FUNCTION     �   CREATE FUNCTION public.after_closing_order_trigger_function() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE desk
  SET status = true
  WHERE id IN (SELECT desk_id FROM reserve WHERE id = OLD.reserve_id);
    RETURN NEW;
END;
$$;
 =   DROP FUNCTION public.after_closing_order_trigger_function();
       public          postgres    false            �            1255    18155    update_desk_status_on_reserve()    FUNCTION     �   CREATE FUNCTION public.update_desk_status_on_reserve() RETURNS trigger
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
       public          postgres    false            �            1259    18156    award    TABLE     �   CREATE TABLE public.award (
    id integer NOT NULL,
    title character varying(40) NOT NULL,
    date_of_receipt date NOT NULL,
    institution_id integer NOT NULL
);
    DROP TABLE public.award;
       public         heap    postgres    false            �            1259    18159    award_id_seq    SEQUENCE     �   CREATE SEQUENCE public.award_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.award_id_seq;
       public          postgres    false    215            �           0    0    award_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.award_id_seq OWNED BY public.award.id;
          public          postgres    false    216            �            1259    18160    calculation    TABLE     �   CREATE TABLE public.calculation (
    id integer NOT NULL,
    dish_id integer NOT NULL,
    ingredient_id integer NOT NULL,
    ingredient_weight integer NOT NULL,
    CONSTRAINT calculation_ingredient_weight_check CHECK ((ingredient_weight > 0))
);
    DROP TABLE public.calculation;
       public         heap    postgres    false            �            1259    18164    calculation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.calculation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.calculation_id_seq;
       public          postgres    false    217            �           0    0    calculation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.calculation_id_seq OWNED BY public.calculation.id;
          public          postgres    false    218            �            1259    18165    desk    TABLE     �   CREATE TABLE public.desk (
    id integer NOT NULL,
    number_of_seats integer NOT NULL,
    status character varying(20) DEFAULT 'vacant'::character varying NOT NULL,
    institution_id integer NOT NULL
);
    DROP TABLE public.desk;
       public         heap    postgres    false            �            1259    18169    desk_id_seq    SEQUENCE     �   CREATE SEQUENCE public.desk_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.desk_id_seq;
       public          postgres    false    219            �           0    0    desk_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.desk_id_seq OWNED BY public.desk.id;
          public          postgres    false    220            �            1259    18170    dish    TABLE       CREATE TABLE public.dish (
    id integer NOT NULL,
    title character varying(60) NOT NULL,
    descriptions character varying(200) NOT NULL,
    recipe character varying(100) NOT NULL,
    institution_id integer NOT NULL,
    price numeric(6,2) NOT NULL
);
    DROP TABLE public.dish;
       public         heap    postgres    false            �            1259    18173    favorite_dish    TABLE     �   CREATE TABLE public.favorite_dish (
    id integer NOT NULL,
    user_client_id integer NOT NULL,
    dish_id integer NOT NULL
);
 !   DROP TABLE public.favorite_dish;
       public         heap    postgres    false            �            1259    18176    dish_favorite_dish_view    VIEW       CREATE VIEW public.dish_favorite_dish_view AS
 SELECT d.id,
    d.title,
    d.descriptions,
    d.institution_id,
    d.price,
    fd.id AS favorite_dish_id,
    fd.user_client_id
   FROM (public.dish d
     FULL JOIN public.favorite_dish fd ON ((fd.dish_id = d.id)));
 *   DROP VIEW public.dish_favorite_dish_view;
       public          postgres    false    221    221    222    222    222    221    221    221            �            1259    18180    dish_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.dish_id_seq;
       public          postgres    false    221            �           0    0    dish_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.dish_id_seq OWNED BY public.dish.id;
          public          postgres    false    224            �            1259    18181 
   dish_order    TABLE     �   CREATE TABLE public.dish_order (
    id integer NOT NULL,
    user_worker_id integer NOT NULL,
    dish_id integer NOT NULL,
    order_id integer NOT NULL
);
    DROP TABLE public.dish_order;
       public         heap    postgres    false            �            1259    18184    dish_order_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.dish_order_id_seq;
       public          postgres    false    225            �           0    0    dish_order_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.dish_order_id_seq OWNED BY public.dish_order.id;
          public          postgres    false    226            �            1259    18185    dish_tag    TABLE     u   CREATE TABLE public.dish_tag (
    id integer NOT NULL,
    dish_id integer NOT NULL,
    tag_id integer NOT NULL
);
    DROP TABLE public.dish_tag;
       public         heap    postgres    false            �            1259    18188    dish_tag_id_seq    SEQUENCE     �   CREATE SEQUENCE public.dish_tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.dish_tag_id_seq;
       public          postgres    false    227            �           0    0    dish_tag_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.dish_tag_id_seq OWNED BY public.dish_tag.id;
          public          postgres    false    228            �            1259    18189    favorite_dish_id_seq    SEQUENCE     �   CREATE SEQUENCE public.favorite_dish_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.favorite_dish_id_seq;
       public          postgres    false    222            �           0    0    favorite_dish_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.favorite_dish_id_seq OWNED BY public.favorite_dish.id;
          public          postgres    false    229            �            1259    18190    history_dish_order    TABLE     �   CREATE TABLE public.history_dish_order (
    id integer NOT NULL,
    dish_order_id integer NOT NULL,
    status character varying(40) DEFAULT 'In queue'::character varying NOT NULL,
    status_date timestamp without time zone NOT NULL
);
 &   DROP TABLE public.history_dish_order;
       public         heap    postgres    false            �            1259    18194    history_dish_order_id_seq    SEQUENCE     �   CREATE SEQUENCE public.history_dish_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.history_dish_order_id_seq;
       public          postgres    false    230            �           0    0    history_dish_order_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.history_dish_order_id_seq OWNED BY public.history_dish_order.id;
          public          postgres    false    231            �            1259    18195 
   ingredient    TABLE     �   CREATE TABLE public.ingredient (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    available integer DEFAULT 0 NOT NULL,
    storage_facility_id integer NOT NULL,
    CONSTRAINT ingredient_available_check CHECK ((available >= 0))
);
    DROP TABLE public.ingredient;
       public         heap    postgres    false            �            1259    18200    ingredient_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ingredient_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.ingredient_id_seq;
       public          postgres    false    232            �           0    0    ingredient_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.ingredient_id_seq OWNED BY public.ingredient.id;
          public          postgres    false    233            �            1259    18201    institution    TABLE       CREATE TABLE public.institution (
    id integer NOT NULL,
    title character varying(40) NOT NULL,
    description character varying(100) NOT NULL,
    number_of_stars numeric(3,2),
    time_open time without time zone NOT NULL,
    time_close time without time zone NOT NULL
);
    DROP TABLE public.institution;
       public         heap    postgres    false            �            1259    18204    institution_id_seq    SEQUENCE     �   CREATE SEQUENCE public.institution_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.institution_id_seq;
       public          postgres    false    234            �           0    0    institution_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.institution_id_seq OWNED BY public.institution.id;
          public          postgres    false    235            �            1259    18205    order_table    TABLE     �  CREATE TABLE public.order_table (
    id integer NOT NULL,
    status character varying(20) DEFAULT 'In the queue'::character varying NOT NULL,
    wishes character varying(100),
    date_of_creation timestamp without time zone NOT NULL,
    closing_date timestamp without time zone,
    reserve_id integer NOT NULL,
    user_client_id integer NOT NULL,
    price numeric(6,2) NOT NULL
);
    DROP TABLE public.order_table;
       public         heap    postgres    false            �            1259    18209    orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    236            �           0    0    orders_id_seq    SEQUENCE OWNED BY     D   ALTER SEQUENCE public.orders_id_seq OWNED BY public.order_table.id;
          public          postgres    false    237            �            1259    18210 	   privilege    TABLE     p   CREATE TABLE public.privilege (
    id integer NOT NULL,
    name character varying(40),
    role_id integer
);
    DROP TABLE public.privilege;
       public         heap    postgres    false            �            1259    18213    privilege_id_seq    SEQUENCE     �   CREATE SEQUENCE public.privilege_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.privilege_id_seq;
       public          postgres    false    238            �           0    0    privilege_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.privilege_id_seq OWNED BY public.privilege.id;
          public          postgres    false    239            �            1259    18214    reserve    TABLE     �   CREATE TABLE public.reserve (
    id integer NOT NULL,
    date_of_reserve timestamp without time zone NOT NULL,
    desk_id integer NOT NULL,
    user_client_id integer NOT NULL
);
    DROP TABLE public.reserve;
       public         heap    postgres    false            �            1259    18217    reserve_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reserve_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.reserve_id_seq;
       public          postgres    false    240            �           0    0    reserve_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.reserve_id_seq OWNED BY public.reserve.id;
          public          postgres    false    241            �            1259    18218    review    TABLE     �  CREATE TABLE public.review (
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
       public         heap    postgres    false            �            1259    18229    review_id_seq    SEQUENCE     �   CREATE SEQUENCE public.review_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.review_id_seq;
       public          postgres    false    242            �           0    0    review_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;
          public          postgres    false    243            �            1259    18230    role    TABLE     V   CREATE TABLE public.role (
    id integer NOT NULL,
    name character varying(40)
);
    DROP TABLE public.role;
       public         heap    postgres    false            �            1259    18233    role_id_seq    SEQUENCE     �   CREATE SEQUENCE public.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.role_id_seq;
       public          postgres    false    244            �           0    0    role_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;
          public          postgres    false    245            �            1259    18234    storage_facility    TABLE     �   CREATE TABLE public.storage_facility (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    address character varying(50) NOT NULL,
    institution_id integer
);
 $   DROP TABLE public.storage_facility;
       public         heap    postgres    false            �            1259    18237    storage_facility_id_seq    SEQUENCE     �   CREATE SEQUENCE public.storage_facility_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.storage_facility_id_seq;
       public          postgres    false    246            �           0    0    storage_facility_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.storage_facility_id_seq OWNED BY public.storage_facility.id;
          public          postgres    false    247            �            1259    18238    tag    TABLE     ^   CREATE TABLE public.tag (
    id integer NOT NULL,
    name character varying(40) NOT NULL
);
    DROP TABLE public.tag;
       public         heap    postgres    false            �            1259    18241 
   tag_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.tag_id_seq;
       public          postgres    false    248            �           0    0 
   tag_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;
          public          postgres    false    249            �            1259    18242    user_institution    TABLE     �   CREATE TABLE public.user_institution (
    id integer NOT NULL,
    user_worker_id integer NOT NULL,
    institution_id integer NOT NULL
);
 $   DROP TABLE public.user_institution;
       public         heap    postgres    false            �            1259    18245    user_institution_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_institution_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.user_institution_id_seq;
       public          postgres    false    250            �           0    0    user_institution_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.user_institution_id_seq OWNED BY public.user_institution.id;
          public          postgres    false    251            �            1259    18246 
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
       public         heap    postgres    false            �            1259    18252    user_table_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_table_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.user_table_id_seq;
       public          postgres    false    252            �           0    0    user_table_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_table_id_seq OWNED BY public.user_table.id;
          public          postgres    false    253            �           2604    18253    award id    DEFAULT     d   ALTER TABLE ONLY public.award ALTER COLUMN id SET DEFAULT nextval('public.award_id_seq'::regclass);
 7   ALTER TABLE public.award ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �           2604    18254    calculation id    DEFAULT     p   ALTER TABLE ONLY public.calculation ALTER COLUMN id SET DEFAULT nextval('public.calculation_id_seq'::regclass);
 =   ALTER TABLE public.calculation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �           2604    18255    desk id    DEFAULT     b   ALTER TABLE ONLY public.desk ALTER COLUMN id SET DEFAULT nextval('public.desk_id_seq'::regclass);
 6   ALTER TABLE public.desk ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �           2604    18256    dish id    DEFAULT     b   ALTER TABLE ONLY public.dish ALTER COLUMN id SET DEFAULT nextval('public.dish_id_seq'::regclass);
 6   ALTER TABLE public.dish ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    221            �           2604    18257    dish_order id    DEFAULT     n   ALTER TABLE ONLY public.dish_order ALTER COLUMN id SET DEFAULT nextval('public.dish_order_id_seq'::regclass);
 <   ALTER TABLE public.dish_order ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225            �           2604    18258    dish_tag id    DEFAULT     j   ALTER TABLE ONLY public.dish_tag ALTER COLUMN id SET DEFAULT nextval('public.dish_tag_id_seq'::regclass);
 :   ALTER TABLE public.dish_tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    18259    favorite_dish id    DEFAULT     t   ALTER TABLE ONLY public.favorite_dish ALTER COLUMN id SET DEFAULT nextval('public.favorite_dish_id_seq'::regclass);
 ?   ALTER TABLE public.favorite_dish ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    222            �           2604    18260    history_dish_order id    DEFAULT     ~   ALTER TABLE ONLY public.history_dish_order ALTER COLUMN id SET DEFAULT nextval('public.history_dish_order_id_seq'::regclass);
 D   ALTER TABLE public.history_dish_order ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    230            �           2604    18261    ingredient id    DEFAULT     n   ALTER TABLE ONLY public.ingredient ALTER COLUMN id SET DEFAULT nextval('public.ingredient_id_seq'::regclass);
 <   ALTER TABLE public.ingredient ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            �           2604    18262    institution id    DEFAULT     p   ALTER TABLE ONLY public.institution ALTER COLUMN id SET DEFAULT nextval('public.institution_id_seq'::regclass);
 =   ALTER TABLE public.institution ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    234            �           2604    18263    order_table id    DEFAULT     k   ALTER TABLE ONLY public.order_table ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 =   ALTER TABLE public.order_table ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    236            �           2604    18264    privilege id    DEFAULT     l   ALTER TABLE ONLY public.privilege ALTER COLUMN id SET DEFAULT nextval('public.privilege_id_seq'::regclass);
 ;   ALTER TABLE public.privilege ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    239    238            �           2604    18265 
   reserve id    DEFAULT     h   ALTER TABLE ONLY public.reserve ALTER COLUMN id SET DEFAULT nextval('public.reserve_id_seq'::regclass);
 9   ALTER TABLE public.reserve ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    241    240            �           2604    18266 	   review id    DEFAULT     f   ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);
 8   ALTER TABLE public.review ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    243    242            �           2604    18267    role id    DEFAULT     b   ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);
 6   ALTER TABLE public.role ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    245    244            �           2604    18268    storage_facility id    DEFAULT     z   ALTER TABLE ONLY public.storage_facility ALTER COLUMN id SET DEFAULT nextval('public.storage_facility_id_seq'::regclass);
 B   ALTER TABLE public.storage_facility ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    247    246            �           2604    18269    tag id    DEFAULT     `   ALTER TABLE ONLY public.tag ALTER COLUMN id SET DEFAULT nextval('public.tag_id_seq'::regclass);
 5   ALTER TABLE public.tag ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    249    248            �           2604    18270    user_institution id    DEFAULT     z   ALTER TABLE ONLY public.user_institution ALTER COLUMN id SET DEFAULT nextval('public.user_institution_id_seq'::regclass);
 B   ALTER TABLE public.user_institution ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    251    250            �           2604    18271    user_table id    DEFAULT     n   ALTER TABLE ONLY public.user_table ALTER COLUMN id SET DEFAULT nextval('public.user_table_id_seq'::regclass);
 <   ALTER TABLE public.user_table ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    253    252            �          0    18156    award 
   TABLE DATA           K   COPY public.award (id, title, date_of_receipt, institution_id) FROM stdin;
    public          postgres    false    215   W�       �          0    18160    calculation 
   TABLE DATA           T   COPY public.calculation (id, dish_id, ingredient_id, ingredient_weight) FROM stdin;
    public          postgres    false    217   t�       �          0    18165    desk 
   TABLE DATA           K   COPY public.desk (id, number_of_seats, status, institution_id) FROM stdin;
    public          postgres    false    219   ��       �          0    18170    dish 
   TABLE DATA           V   COPY public.dish (id, title, descriptions, recipe, institution_id, price) FROM stdin;
    public          postgres    false    221   �       �          0    18181 
   dish_order 
   TABLE DATA           K   COPY public.dish_order (id, user_worker_id, dish_id, order_id) FROM stdin;
    public          postgres    false    225   �       �          0    18185    dish_tag 
   TABLE DATA           7   COPY public.dish_tag (id, dish_id, tag_id) FROM stdin;
    public          postgres    false    227   z�       �          0    18173    favorite_dish 
   TABLE DATA           D   COPY public.favorite_dish (id, user_client_id, dish_id) FROM stdin;
    public          postgres    false    222   ��       �          0    18190    history_dish_order 
   TABLE DATA           T   COPY public.history_dish_order (id, dish_order_id, status, status_date) FROM stdin;
    public          postgres    false    230   ��       �          0    18195 
   ingredient 
   TABLE DATA           N   COPY public.ingredient (id, name, available, storage_facility_id) FROM stdin;
    public          postgres    false    232   ��       �          0    18201    institution 
   TABLE DATA           e   COPY public.institution (id, title, description, number_of_stars, time_open, time_close) FROM stdin;
    public          postgres    false    234   �       �          0    18205    order_table 
   TABLE DATA           |   COPY public.order_table (id, status, wishes, date_of_creation, closing_date, reserve_id, user_client_id, price) FROM stdin;
    public          postgres    false    236   ��       �          0    18210 	   privilege 
   TABLE DATA           6   COPY public.privilege (id, name, role_id) FROM stdin;
    public          postgres    false    238   �       �          0    18214    reserve 
   TABLE DATA           O   COPY public.reserve (id, date_of_reserve, desk_id, user_client_id) FROM stdin;
    public          postgres    false    240   ,�       �          0    18218    review 
   TABLE DATA           �   COPY public.review (id, user_client_id, stars_food, stars_service, stars_atmosphere, description, date_of_creation, institution_id) FROM stdin;
    public          postgres    false    242   W�       �          0    18230    role 
   TABLE DATA           (   COPY public.role (id, name) FROM stdin;
    public          postgres    false    244   �       �          0    18234    storage_facility 
   TABLE DATA           M   COPY public.storage_facility (id, name, address, institution_id) FROM stdin;
    public          postgres    false    246   T�       �          0    18238    tag 
   TABLE DATA           '   COPY public.tag (id, name) FROM stdin;
    public          postgres    false    248   ��       �          0    18242    user_institution 
   TABLE DATA           N   COPY public.user_institution (id, user_worker_id, institution_id) FROM stdin;
    public          postgres    false    250   ��       �          0    18246 
   user_table 
   TABLE DATA           `   COPY public.user_table (id, first_name, last_name, phone, role_id, email, password) FROM stdin;
    public          postgres    false    252   �       �           0    0    award_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.award_id_seq', 1, false);
          public          postgres    false    216            �           0    0    calculation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.calculation_id_seq', 1, false);
          public          postgres    false    218            �           0    0    desk_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.desk_id_seq', 23, true);
          public          postgres    false    220            �           0    0    dish_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.dish_id_seq', 12, true);
          public          postgres    false    224            �           0    0    dish_order_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.dish_order_id_seq', 61, true);
          public          postgres    false    226            �           0    0    dish_tag_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.dish_tag_id_seq', 1, false);
          public          postgres    false    228            �           0    0    favorite_dish_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.favorite_dish_id_seq', 17, true);
          public          postgres    false    229            �           0    0    history_dish_order_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.history_dish_order_id_seq', 25, true);
          public          postgres    false    231            �           0    0    ingredient_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.ingredient_id_seq', 1, true);
          public          postgres    false    233            �           0    0    institution_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.institution_id_seq', 7, true);
          public          postgres    false    235            �           0    0    orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 60, true);
          public          postgres    false    237            �           0    0    privilege_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.privilege_id_seq', 1, false);
          public          postgres    false    239            �           0    0    reserve_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.reserve_id_seq', 28, true);
          public          postgres    false    241            �           0    0    review_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.review_id_seq', 11, true);
          public          postgres    false    243            �           0    0    role_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.role_id_seq', 5, true);
          public          postgres    false    245            �           0    0    storage_facility_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.storage_facility_id_seq', 2, true);
          public          postgres    false    247            �           0    0 
   tag_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.tag_id_seq', 1, false);
          public          postgres    false    249            �           0    0    user_institution_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.user_institution_id_seq', 12, true);
          public          postgres    false    251            �           0    0    user_table_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.user_table_id_seq', 25, true);
          public          postgres    false    253            �           2606    18273    award award_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.award
    ADD CONSTRAINT award_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.award DROP CONSTRAINT award_pkey;
       public            postgres    false    215            �           2606    18275    calculation calculation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_pkey;
       public            postgres    false    217            �           2606    18277    desk desk_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.desk
    ADD CONSTRAINT desk_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.desk DROP CONSTRAINT desk_pkey;
       public            postgres    false    219            �           2606    18279    dish_order dish_order_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_pkey;
       public            postgres    false    225            �           2606    18281    dish dish_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.dish
    ADD CONSTRAINT dish_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_pkey;
       public            postgres    false    221            �           2606    18283    dish_tag dish_tag_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_pkey;
       public            postgres    false    227            �           2606    18285     favorite_dish favorite_dish_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_pkey;
       public            postgres    false    222            �           2606    18287 *   history_dish_order history_dish_order_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.history_dish_order
    ADD CONSTRAINT history_dish_order_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.history_dish_order DROP CONSTRAINT history_dish_order_pkey;
       public            postgres    false    230            �           2606    18289    ingredient ingredient_name_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_name_key UNIQUE (name);
 H   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_name_key;
       public            postgres    false    232            �           2606    18291    ingredient ingredient_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_pkey;
       public            postgres    false    232            �           2606    18293    institution institution_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.institution
    ADD CONSTRAINT institution_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.institution DROP CONSTRAINT institution_pkey;
       public            postgres    false    234            �           2606    18295    order_table orders_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);
 A   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_pkey;
       public            postgres    false    236            �           2606    18297    user_table phone_unique 
   CONSTRAINT     S   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT phone_unique UNIQUE (phone);
 A   ALTER TABLE ONLY public.user_table DROP CONSTRAINT phone_unique;
       public            postgres    false    252            �           2606    18299    reserve reserve_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_pkey;
       public            postgres    false    240            �           2606    18301    review review_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.review DROP CONSTRAINT review_pkey;
       public            postgres    false    242            �           2606    18303    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    244            �           2606    18305 *   storage_facility storage_facility_name_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_name_key UNIQUE (name);
 T   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_name_key;
       public            postgres    false    246            �           2606    18307 &   storage_facility storage_facility_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_pkey;
       public            postgres    false    246            �           2606    18309    tag tag_name_key 
   CONSTRAINT     K   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_name_key UNIQUE (name);
 :   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_name_key;
       public            postgres    false    248            �           2606    18311    tag tag_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.tag DROP CONSTRAINT tag_pkey;
       public            postgres    false    248            �           2606    18313    favorite_dish user_dish_unique 
   CONSTRAINT     l   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT user_dish_unique UNIQUE (user_client_id, dish_id);
 H   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT user_dish_unique;
       public            postgres    false    222    222            �           2606    18315 &   user_institution user_institution_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_pkey;
       public            postgres    false    250            �           2606    18317    user_table user_table_email_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_email_key UNIQUE (email);
 I   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_email_key;
       public            postgres    false    252            �           2606    18319    user_table user_table_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_pkey;
       public            postgres    false    252                       2620    18320 '   order_table after_closing_order_trigger    TRIGGER     �   CREATE TRIGGER after_closing_order_trigger AFTER UPDATE OF status ON public.order_table FOR EACH ROW WHEN (((new.status)::text = 'Closed'::text)) EXECUTE FUNCTION public.after_closing_order_trigger_function();
 @   DROP TRIGGER after_closing_order_trigger ON public.order_table;
       public          postgres    false    236    236    254    236                       2620    18321    reserve set_desk_status_true    TRIGGER     �   CREATE TRIGGER set_desk_status_true AFTER INSERT ON public.reserve FOR EACH ROW EXECUTE FUNCTION public.update_desk_status_on_reserve();
 5   DROP TRIGGER set_desk_status_true ON public.reserve;
       public          postgres    false    255    240                        2606    18322    award award_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.award
    ADD CONSTRAINT award_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 I   ALTER TABLE ONLY public.award DROP CONSTRAINT award_institution_id_fkey;
       public          postgres    false    215    234    4839                       2606    18327 $   calculation calculation_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_dish_id_fkey;
       public          postgres    false    217    4823    221                       2606    18332 *   calculation calculation_ingredient_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.calculation
    ADD CONSTRAINT calculation_ingredient_id_fkey FOREIGN KEY (ingredient_id) REFERENCES public.ingredient(id) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.calculation DROP CONSTRAINT calculation_ingredient_id_fkey;
       public          postgres    false    232    4837    217                       2606    18337    desk desk_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.desk
    ADD CONSTRAINT desk_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 G   ALTER TABLE ONLY public.desk DROP CONSTRAINT desk_institution_id_fkey;
       public          postgres    false    234    219    4839                       2606    18342    dish dish_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish
    ADD CONSTRAINT dish_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id);
 G   ALTER TABLE ONLY public.dish DROP CONSTRAINT dish_institution_id_fkey;
       public          postgres    false    221    4839    234                       2606    18347 "   dish_order dish_order_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id);
 L   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_dish_id_fkey;
       public          postgres    false    221    4823    225                       2606    18352 #   dish_order dish_order_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.order_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_order_id_fkey;
       public          postgres    false    225    236    4841            	           2606    18357 )   dish_order dish_order_user_worker_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_order
    ADD CONSTRAINT dish_order_user_worker_id_fkey FOREIGN KEY (user_worker_id) REFERENCES public.user_table(id);
 S   ALTER TABLE ONLY public.dish_order DROP CONSTRAINT dish_order_user_worker_id_fkey;
       public          postgres    false    252    4863    225            
           2606    18362    dish_tag dish_tag_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_dish_id_fkey;
       public          postgres    false    227    221    4823                       2606    18367    dish_tag dish_tag_tag_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dish_tag
    ADD CONSTRAINT dish_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tag(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.dish_tag DROP CONSTRAINT dish_tag_tag_id_fkey;
       public          postgres    false    248    4855    227                       2606    18372 (   favorite_dish favorite_dish_dish_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_dish_id_fkey FOREIGN KEY (dish_id) REFERENCES public.dish(id) ON UPDATE CASCADE ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_dish_id_fkey;
       public          postgres    false    221    4823    222                       2606    18377 /   favorite_dish favorite_dish_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorite_dish
    ADD CONSTRAINT favorite_dish_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 Y   ALTER TABLE ONLY public.favorite_dish DROP CONSTRAINT favorite_dish_user_client_id_fkey;
       public          postgres    false    252    4863    222                       2606    18382 9   history_dish_order history_dish_order_dish_ordeer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.history_dish_order
    ADD CONSTRAINT history_dish_order_dish_ordeer_id_fkey FOREIGN KEY (dish_order_id) REFERENCES public.dish_order(id) ON UPDATE CASCADE ON DELETE CASCADE;
 c   ALTER TABLE ONLY public.history_dish_order DROP CONSTRAINT history_dish_order_dish_ordeer_id_fkey;
       public          postgres    false    230    225    4829                       2606    18387 .   ingredient ingredient_storage_facility_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ingredient
    ADD CONSTRAINT ingredient_storage_facility_id_fkey FOREIGN KEY (storage_facility_id) REFERENCES public.storage_facility(id) ON UPDATE CASCADE ON DELETE CASCADE;
 X   ALTER TABLE ONLY public.ingredient DROP CONSTRAINT ingredient_storage_facility_id_fkey;
       public          postgres    false    232    246    4851                       2606    18392 "   order_table orders_reserve_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_reserve_id_fkey FOREIGN KEY (reserve_id) REFERENCES public.reserve(id) ON UPDATE CASCADE ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_reserve_id_fkey;
       public          postgres    false    236    240    4843                       2606    18397 &   order_table orders_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_table
    ADD CONSTRAINT orders_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id);
 P   ALTER TABLE ONLY public.order_table DROP CONSTRAINT orders_user_client_id_fkey;
       public          postgres    false    4863    236    252                       2606    18402     privilege privilege_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.privilege
    ADD CONSTRAINT privilege_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.privilege DROP CONSTRAINT privilege_role_id_fkey;
       public          postgres    false    238    4847    244                       2606    18407    reserve reserve_desk_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_desk_id_fkey FOREIGN KEY (desk_id) REFERENCES public.desk(id) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_desk_id_fkey;
       public          postgres    false    240    219    4821                       2606    18412 #   reserve reserve_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reserve
    ADD CONSTRAINT reserve_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.reserve DROP CONSTRAINT reserve_user_client_id_fkey;
       public          postgres    false    240    4863    252                       2606    18417 !   review review_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.review DROP CONSTRAINT review_institution_id_fkey;
       public          postgres    false    4839    242    234                       2606    18422 !   review review_user_client_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_user_client_id_fkey FOREIGN KEY (user_client_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.review DROP CONSTRAINT review_user_client_id_fkey;
       public          postgres    false    242    252    4863                       2606    18427 5   storage_facility storage_facility_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.storage_facility
    ADD CONSTRAINT storage_facility_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.storage_facility DROP CONSTRAINT storage_facility_institution_id_fkey;
       public          postgres    false    234    4839    246                       2606    18432 5   user_institution user_institution_institution_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_institution_id_fkey FOREIGN KEY (institution_id) REFERENCES public.institution(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_institution_id_fkey;
       public          postgres    false    234    250    4839                       2606    18437 5   user_institution user_institution_user_worker_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_institution
    ADD CONSTRAINT user_institution_user_worker_id_fkey FOREIGN KEY (user_worker_id) REFERENCES public.user_table(id) ON UPDATE CASCADE ON DELETE CASCADE;
 _   ALTER TABLE ONLY public.user_institution DROP CONSTRAINT user_institution_user_worker_id_fkey;
       public          postgres    false    4863    252    250                       2606    18442 "   user_table user_table_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.role(id);
 L   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_role_id_fkey;
       public          postgres    false    244    4847    252            �      x������ � �      �      x������ � �      �   x   x�U�K�0D��a� 6N��MTyۅ�9��(���������P*��X�'T���q~7W��Z�;
��77-(7�7��]1F�w!�SGM�%����D,�ot�H���-��N��I0��"� 4�G      �   �   x���MNA�u�)<��?p�1�!�n� ��	]�T���LT�+���]%��q�I����]���fr�t5��ƣ�ʮ�\���W�͢Y0jA͙%�h�b�d���QRJ-���QV��'f�#�2�;�xCMxB�Zfg�tE��V��])5r��gX�9���,�dىm��$��˅-���2��N��wE�r��3@f���e�{�I��6�ո"��v��g_��������      �   U   x�E���0�3��C�K����VA���[��.C�P-2�I�[B���;hA��8�;9�y��7��������}x jo`      �      x������ � �      �   F   x�̱� �XW��!�^�棝KN���4l[���h�h�:�ޞv�eJ;Ѷ��}�:�ο�q$�      �   �   x���=ND1F��^���D���8;`�#Q!!1�'�kI}
�:�������x^_���I*�����1][U��\�~�Gt0�<���d����Z��&j�P�~����F>Nt_[2��N�j������bJL�*ȵe���:�bw��h,]��K05�I��,g�]�Ħ��ҫH���7�!Ū�޿z���̒�(v��z]���N��}����%t�e�{c�?D��      �   -   x�" ��1	Картомпля	15000	1
\.


K�H      �   �   x�uO1
�@��^��]rj����6"�6�&6jk'��ј��?r��D�]����9K��r�W�p@��c����dL�ھ�!�.Oui8���hj(p"ݫ����p>��&#�����o}���%.H9��<\8F������p���o���U���B�A� �1Kq�*�x����e���s�F��m�-�[J��ْ�      �     x���=N�0���9�/P�g?��X8A�,R)�j����8 �`���+$7"��P$�Ń��_�6q�ȮJ�ا>�.��|�w��/����)����o���A����
 9X���� �i�`��z軋a��+��q�%�s؉m�ܤ:݊�y	��|��fv�ęY��턆��s�_���K�O���1��%'	#p4����/%3�4�?�ZS�:H�Q�N��9��ݴf.iy�v���s]����UV�������TQ?|�}c      �      x������ � �      �     x�m�ѱ!E��*l`."����:���&�eƿs.����$?A�h�9�M��B�@'{-�?c�b~�pB3�U2j�����,��'lCj0~3��Q�^Z���S���i+߂ɞ��\���aa�۠�����tK���T�t�2�̃ٛ��4ڬ�b`a��L��;�l�J�~�v���@&NO���g/C�W�r�ex4+"�Nݝ٫�םYvg=Y���V�K�e�3�Q�q��P>v�y��/�^�����8�껢���Q��[3�Y%^��GI)��Z��      �   �  x�m�An1E��S� �r��v���$3+DD	��Q�e�Āi`H�B�F|w'�b�ե���_���	�{�.��ӫ�ԟz[O�Y���zcQ6���Fw�����w��G��a�r@t��R*���$���A����[O��;�����N�D� ���'
��K��/`�rl��5�M�GӠ�7u1�����nZ3��"!%#]l:���t>陞����{��Bߢm��s�s��7�K��xǢ�u����Ӻ�'3@��ѱ�lr����-��<��#47@Y��]�Tz�,l[��/�����Ь,�[�7���	�/>��c�G!�F{��[ĺC��5�<�Z�ǎ�e�/�}	�xvy`""?Q���xy8�B���S�6Fm,�'������6xf;�#Rj9�(����/Z�      �   <   x�3���q�w����2���=C\����2>��~!\&��P��qt�������� ��      �   V   x�3估�®�/l��Ő�¼[.l��xa��M�/��S�0Ho��(�$�2��1~\FH�ph�5\l�j3����� Q�A�      �      x������ � �      �   5   x���  ��]�M����A���

L�	.���M�@}RQq���|���b      �   �  x�e�ˮ�0�qy�=FZ�mv@u*�9����U������ä+i�|Y�!�MF�/��ǘ%JR���}�Ot�������/�|ɻ6��Q�T~u�"����ĐRIэ���"p�v)�6�T�� nW�iL���q�J�(�'o��'���Zk��@�:4Z2�[�3�,�*P�:��Nr���Aa��;��� �o��E�Z�Wkl�Z�Q�ocQ�T��e��I����Ŷ�+��g����M�о���%�$�)(~P�*Q4D��l�aot���W�Ѥ�P(t����">�f7���j�a�hMA���P<D�=��-���%�0r��c6'�^]G�"x0iV�S��-�3�,�}�,�k
!���S>&����Cm�'��h�f���|��o�;�˱�d�,�kReV��M��U����`|�&��p�Ĉ�� pnG�����|5I�Vr.����Xf��4=t�%4Ifo�2����(A��e���;���!���Y�x1&���H���n�[͛F�\4U�N�,_�k��=�(��\����ܞMr�[�����uq��;u3O9��Q���gbo]]X���1�ag����i��8�?����0/}��:reu�L屏��K=Α�%��'�#���G��������kZ+cSi����xN�     