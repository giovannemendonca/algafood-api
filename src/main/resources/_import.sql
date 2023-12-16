-- Script para popular o banco de dados com dados de teste

-- Inserindo dados na tabela cozinha
insert into cozinha (id, nome)
values (1, 'Tailandesa');
insert into cozinha (id, nome)
values (2, 'Indiana');
insert into cozinha (id, nome)
values (3, 'Argentina');
insert into cozinha (id, nome)
values (4, 'Brasileira');
insert into cozinha (id, nome)
values (5, 'Mexicana');
insert into cozinha (id, nome)
values (6, 'Japonesa');
insert into cozinha (id, nome)
values (7, 'Italiana');
insert into cozinha (id, nome)
values (8, 'Francesa');
insert into cozinha (id, nome)
values (9, 'Portuguesa');
insert into cozinha (id, nome)
values (10, 'Alemã');
insert into cozinha (id, nome)
values (11, 'Espanhola');
insert into cozinha (id, nome)
values (12, 'Peruana');


-- Inserindo dados na tabela estado
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
values (7, 'Distrito Federal');
insert into estado (id, nome)
values (8, 'Goiás');
insert into estado (id, nome)
values (9, 'Bahia');
insert into estado (id, nome)
values (10, 'Pernambuco');
insert into estado (id, nome)
values (11, 'Pará');
insert into estado (id, nome)
values (12, 'Maranhão');


-- Inserindo dados na tabela cidade
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
values (9, 'Brasília', 7);
insert into cidade (id, nome, estado_id)
values (10, 'Goiânia', 8);
insert into cidade (id, nome, estado_id)
values (11, 'Salvador', 9);
insert into cidade (id, nome, estado_id)
values (12, 'Recife', 10);
insert into cidade (id, nome, estado_id)
values (13, 'Belém', 11);
insert into cidade (id, nome, estado_id)
values (14, 'São Luís', 12);


-- Inserindo dados na tabela restaurante

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (1, 'Restaurante Thai Delight', 10, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, 1, '38400-999', 'Avenida dos Sabores',
        '1000', 'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (2, 'Tay San Saboroso', 10, 1, UTC_TIMESTAMP, UTC_TIMESTAMP, 2, '38400-777', 'Rua das Especiarias', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (3, 'Delícia do Oriente', 10, 2, UTC_TIMESTAMP, UTC_TIMESTAMP, 3, '38400-777', 'Alameda dos Aromas', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (4, 'Sabor Oriental', 10, 3, UTC_TIMESTAMP, UTC_TIMESTAMP, 4, '38400-777', 'Praça das Delícias', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (5, 'Gastronomia Exótica', 10, 4, UTC_TIMESTAMP, UTC_TIMESTAMP, 5, '38400-777', 'Rua das Tentadoras', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (6, 'Aromas Asiáticos', 10, 5, UTC_TIMESTAMP, UTC_TIMESTAMP, 6, '38400-777', 'Alameda dos Sabores', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (7, 'Sabores do Oriente', 10, 6, UTC_TIMESTAMP, UTC_TIMESTAMP, 7, '38400-777', 'Rua dos Cheiros', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (8, 'Delícias Asiáticas', 10, 7, UTC_TIMESTAMP, UTC_TIMESTAMP, 8, '38400-777', 'Praça das Especiarias', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (9, 'Sabor do Oriente', 10, 8, UTC_TIMESTAMP, UTC_TIMESTAMP, 9, '38400-777', 'Alameda das Delícias', '1000',
        'Centro');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id,
                         endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
VALUES (10, 'Culinária Exótica', 10, 9, UTC_TIMESTAMP, UTC_TIMESTAMP, 10, '38400-777', 'Rua das Maravilhas', '1000',
        'Centro');

-- Inserindo dados na tabela forma_pagamento
insert into forma_pagamento (id, descricao)
values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao)
values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao)
values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao)
values (4, 'Pix');

-- Inserindo dados na tabela permissao
insert into permissao (id, nome, descricao)
values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao)
values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

-- Inserindo dados na tabela restaurante_forma_pagamento
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
       (6, 3)
       (7, 3),
       (8, 1),
       (8, 2),
       (8, 3),
       (9, 1),
       (9, 2),
       (9, 3),
       (10, 1),
       (10, 2),
       (10, 3);


-- Inserindo dados na tabela produto
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
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
values ('Yakissoba de Frango', 'Macarrão oriental com legumes e frango', 35, 1, 6);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Pizza de Calabresa', 'Pizza com molho de tomate, queijo e calabresa', 45, 1, 7);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Pizza de Marguerita', 'Pizza com molho de tomate, queijo e manjericão', 45, 1, 7);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Pizza de Quatro Queijos', 'Pizza com molho de tomate, queijo, gorgonzola, parmesão e provolone', 45, 1, 7);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Pizza de Frango com Catupiry', 'Pizza com molho de tomate, queijo, frango e catupiry', 45, 1, 7);
insert into produto (nome, descricao, preco, ativo, restaurante_id)
values ('Pizza de Chocolate', 'Pizza com chocolate ao leite e chocolate branco', 45, 1, 7);



-- Inserindo dados na tabela Usuario

insert into usuario (id, nome, email, senha, data_cadastro)
values (1, 'João da Silva', 'joao.ger@algafood.com', '123456', utc_timestamp),
       (2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123456', utc_timestamp),
       (3, 'José Souza', 'jose.aux@algafood.com', '123456', utc_timestamp),
       (4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123456', utc_timestamp);

