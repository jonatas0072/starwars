create table inventario
(
    uuid binary not null,
    id_rebelde binary,
    primary key (uuid)
);
create table item
(
    uuid binary not null,
    nome   varchar(255),
    pontos integer not null,
    id_inventario binary not null,
    primary key (uuid)
);
create table localizacao
(
    uuid binary not null,
    created_at   timestamp,
    updated_at   timestamp,
    latitude     varchar(255),
    longitude    varchar(255),
    nome_base    varchar(255),
    nome_galaxia varchar(255),
    primary key (uuid)
);
create table rebelde
(
    uuid binary not null,
    created_at timestamp,
    updated_at timestamp,
    genero     varchar(255),
    idade      integer not null,
    nome       varchar(255),
    traidor    integer not null,
    id_inventario binary,
    id_localizacao binary,
    primary key (uuid)
);
alter table inventario
    add constraint FKjsn4rgya54isdwifkamccq591 foreign key (id_rebelde) references rebelde;
alter table item
    add constraint FKi8an0y4mejk6uyanh81exk5o7 foreign key (id_inventario) references inventario;
alter table rebelde
    add constraint FKh0to591opdm44unhjl4erqrlb foreign key (id_inventario) references inventario;
alter table rebelde
    add constraint FK6mc499selh2eb5grbgrf96p6u foreign key (id_localizacao) references localizacao;