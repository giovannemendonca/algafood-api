set
    foreign_key_checks = 0;

delete
from cidade;
delete
from cozinha;
delete
from estado;
delete
from forma_pagamento;
delete
from grupo;
delete
from grupo_permissao;
delete
from permissao;
delete
from produto;
delete
from restaurante;
delete
from restaurante_forma_pagamento;
delete
from restaurante_usuario_responsavel;
delete
from usuario;
delete
from usuario_grupo;
delete
from pedido;
delete
from item_pedido;

set
    foreign_key_checks = 1;

alter table cidade
    auto_increment = 1;
alter table cozinha
    auto_increment = 1;
alter table estado
    auto_increment = 1;
alter table forma_pagamento
    auto_increment = 1;
alter table grupo
    auto_increment = 1;
alter table permissao
    auto_increment = 1;
alter table produto
    auto_increment = 1;
alter table restaurante
    auto_increment = 1;
alter table usuario
    auto_increment = 1;
alter table pedido
    auto_increment = 1;
alter table item_pedido
    auto_increment = 1;

# Inserindo dados de teste na tabela cozinha

insert into cozinha (id, nome)
values (1, 'Tailandesa');
insert into cozinha (id, nome)
values (2, 'Indiana');
insert into cozinha (id, nome)
values (3, 'Argentina');
insert into cozinha (id, nome)
values (4, 'Brasileira');
insert into cozinha (id, nome)
values (5, 'Italiana');
insert into cozinha (id, nome)
values (6, 'Japonesa');
insert into cozinha (id, nome)
values (7, 'Mexicana');
insert into cozinha (id, nome)
values (8, 'Portuguesa');
insert into cozinha (id, nome)
values (9, 'Chinesa');
insert into cozinha (id, nome)
values (10, 'Árabe');
insert into cozinha (id, nome)
values (11, 'Francesa');
insert into cozinha (id, nome)
values (12, 'Alemã');


# Inserindo dados de teste na tabela estado

insert into estado (id, nome)
values (1, 'Minas Gerais');
insert into estado (id, nome)
values (2, 'São Paulo');
insert into estado (id, nome)
values (3, 'Ceará');
insert into estado (id, nome)
values (4, 'Paraná');
insert into estado (id, nome)
values (5, 'Santa Catarina');
insert into estado (id, nome)
values (6, 'Rio Grande do Sul');
insert into estado (id, nome)
values (7, 'Rio de Janeiro');
insert into estado (id, nome)
values (8, 'Bahia');
insert into estado (id, nome)
values (9, 'Pernambuco');
insert into estado (id, nome)
values (10, 'Pará');
insert into estado (id, nome)
values (11, 'Maranhão');
insert into estado (id, nome)
values (12, 'Goiás');

# Inserindo dados de teste na tabela cidade

insert into cidade (id, nome, estado_id)
values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id)
values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id)
values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id)
values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id)
values (5, 'Fortaleza', 3);
insert into cidade (id, nome, estado_id)
values (6, 'Curitiba', 4);
insert into cidade (id, nome, estado_id)
values (7, 'Florianópolis', 5);
insert into cidade (id, nome, estado_id)
values (8, 'Porto Alegre', 6);
insert into cidade (id, nome, estado_id)
values (9, 'Rio de Janeiro', 7);
insert into cidade (id, nome, estado_id)
values (10, 'Salvador', 8);
insert into cidade (id, nome, estado_id)
values (11, 'Recife', 9);
insert into cidade (id, nome, estado_id)
values (12, 'Belém', 10);
insert into cidade (id, nome, estado_id)
values (13, 'São Luís', 11);


# Inserindo dados de teste na tabela restaurante

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000',
        'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (2, 'Indian House', 10, 2, utc_timestamp, utc_timestamp, true, true, 1, '38400-888', 'Rua João Pinheiro', '1000',
        'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (3, 'Casa Argentina', 10, 3, utc_timestamp, utc_timestamp, true, true, 1, '38400-777', 'Rua João Pinheiro',
        '1000',
        'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (4, 'Casa do Churrasco', 10, 4, utc_timestamp, utc_timestamp, true, true, 1, '38400-666', 'Rua João Pinheiro',
        '1000',
        'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (5, 'Casa do Sanduíche', 10, 5, utc_timestamp, utc_timestamp, true, true, 1, '38400-555', 'Rua João Pinheiro',
        '1000',
        'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto,
                         endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values (6, 'Casa do Espetinho', 10, 6, utc_timestamp, utc_timestamp, true, true, 1, '38400-444', 'Rua João Pinheiro',
        '1000',
        'Centro');


# Inserindo dados de teste na tabela forma_pagamento

insert into forma_pagamento (id, descricao)
values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao)
values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao)
values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao)
values (4, 'Vale refeição');

# Inserindo dados de teste na tabela permissao

insert into permissao (id, nome, descricao)
values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao)
values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

# Inserindo dados de teste na tabela restaurante_forma_pagamento

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 3),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (5, 1),
       (5, 2),
       (6, 3),
       (6, 4),
       (6, 1),
       (6, 2);

# Inserindo dados de teste na tabela produto

insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 0, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Salada picante com carne grelhada',
        'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20,
        1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé',
        79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('T-Bone',
        'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89,
        1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Espetinho de Kafta', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Espetinho de Coração', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

# Inserindo dados de teste na tabela grupo

insert into grupo (id, nome)
values (1, 'Gerente'),
       (2, 'Vendedor'),
       (3, 'Secretária'),
       (4, 'Cadastrador');

# Inserindo dados de teste na tabela grupo_permissao

insert into grupo_permissao (grupo_id, permissao_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1);

# Inserindo dados de teste na tabela usuario

insert into usuario (id, nome, email, senha, data_cadastro)
values (1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
       (2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
       (3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
       (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),
       (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

# Inserindo dados de teste na tabela usuario_grupo

insert into usuario_grupo (usuario_id, grupo_id)
values (1, 1),
       (1, 2),
       (2, 2);

# Inserindo dados de teste na tabela restaurante_usuario_responsavel

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id)
values (1, 5),
       (3, 5);


# Inserindo dados de teste na tabela pedido

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 'f9981ca4-5a5e-4da3-af04-933861df3e55', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801',
        'Brasil',
        'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, 'd178b637-a785-4768-a3cb-aa1ce5a8cdab', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro',
        'CRIADO', utc_timestamp, 79, 0, 79);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (3, 'b5741512-8fbc-47fa-9ac1-b530354fc0ff', 1, 1, 1, 1, '38400-222', 'Rua Natal', '200', null, 'Brasil',
        'ENTREGUE', '2019-10-30 21:10:00', '2019-10-30 21:10:45', '2019-10-30 21:55:44', 110, 10, 120);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (4, '5c621c9a-ba61-4454-8631-8aabefe58dc2', 2, 2, 1, 1, '38400-800', 'Rua Fortaleza', '900', 'Apto 504',
        'Centro',
        'ENTREGUE', '2019-11-02 20:34:04', '2019-11-02 20:35:10', '2019-11-02 21:10:32', 174.4, 5, 179.4);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (5, '8d774bcf-b238-42f3-aef1-5fb388754d63', 1, 3, 2, 1, '38400-200', 'Rua 10', '930', 'Casa 20', 'Martins',
        'ENTREGUE', '2019-11-02 21:00:30', '2019-11-02 21:01:21', '2019-11-02 21:20:10', 87.2, 10, 97.2);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (6, 'b7b3c2e0-0a6a-4b1f-9b0e-0a1a7ae8a8a4', 3, 4, 1, 1, '38400-300', 'Rua 20', '123', 'Casa 10', 'Martins',
        'ENTREGUE', '2019-11-03 20:34:04', '2019-11-03 20:35:10', '2019-11-03 21:10:32', 110, 10, 120);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (7, 'b7b3c2e0-0a6a-4b1f-9b0e-0a1a7ae8a8a1', 2, 5, 1, 1, '38400-300', 'Rua 20', '123', 'Casa 10', 'Martins',
        'ENTREGUE', '2019-11-03 20:34:04', '2019-11-03 20:35:10', '2019-11-03 21:10:32', 110, 10, 120);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id,
                    endereco_cep,
                    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
                    status, data_criacao, data_confirmacao, data_entrega, subtotal, taxa_frete, valor_total)
values (8, 'b7b3c2e0-0a6a-4b1f-9b0e-0a1a7ae8a8a2', 1, 5, 1, 1, '38400-300', 'Rua 20', '123', 'Casa 10', 'Martins',
        'ENTREGUE', '2019-11-03 20:34:04', '2019-11-03 20:35:10', '2019-11-03 21:10:32', 110, 10, 120);


# Inserindo dados de teste na tabela item_pedido

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 2, 1, 110, 110, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 3, 2, 87.2, 174.4, null);


insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 3, 1, 87.2, 87.2, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (7, 6, 2, 1, 110, 110, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (8, 7, 2, 1, 110, 110, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (9, 8, 2, 1, 110, 110, null);
