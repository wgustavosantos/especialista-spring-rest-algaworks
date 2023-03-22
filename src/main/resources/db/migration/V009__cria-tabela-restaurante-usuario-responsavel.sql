create table restaurante_usuario_responsavel
(
    usuario_id     bigint not null,
    restaurante_id bigint not null,
    constraint fk_usuario_restaurante_responsavel foreign key (usuario_id) references usuario (id),
    constraint fk_usuario_restaurante_restaurante foreign key (restaurante_id) references restaurante (id)
) engine = InnoDB
  default charset = utf8;