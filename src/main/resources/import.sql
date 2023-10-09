-- Ajustando os IDs para a tabela cozinha
INSERT INTO cozinha(id, nom_cozinha) VALUES (1, 'Italiana');
INSERT INTO cozinha(id, nom_cozinha) VALUES (2, 'Chinesa');
INSERT INTO cozinha(id, nom_cozinha) VALUES (3, 'Mexicana');
INSERT INTO cozinha(id, nom_cozinha) VALUES (4, 'Japonesa');
INSERT INTO cozinha(id, nom_cozinha) VALUES (5, 'Brasileira');
INSERT INTO cozinha(id, nom_cozinha) VALUES (6, 'Indiana');
INSERT INTO cozinha(id, nom_cozinha) VALUES (7, 'Francesa');
INSERT INTO cozinha(id, nom_cozinha) VALUES (8, 'Árabe');

-- Ajustando os IDs para a tabela restaurante
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Cantina da Nonna', 0, 1);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Tian', 7, 1);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Pizzaria Bella Napoli', 0, 3);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Sushi House', 12, 4);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Churrascaria Gaúcha', 15, 5);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Curry Palace', 10, 6);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Le Bistro', 20, 7);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ('Almanara', 18, 8);

-- Ajustando os IDs para a tabela estado
INSERT INTO estado (id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (3, 'Ceará');
INSERT INTO estado (id, nome) VALUES (4, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (5, 'Bahia');
INSERT INTO estado (id, nome) VALUES (6, 'Paraná');
INSERT INTO estado (id, nome) VALUES (7, 'Amazonas');
INSERT INTO estado (id, nome) VALUES (8, 'Santa Catarina');

-- Ajustando os IDs para a tabela cidade
INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'São Paulo', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Campinas', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Fortaleza', 3);
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Curitiba', 6);
INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Manaus', 7);
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Florianópolis', 8);

-- Ajustando os IDs para a tabela forma_pagamento
INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de débito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Dinheiro');
INSERT INTO forma_pagamento (id, descricao) VALUES (4, 'Pix');
INSERT INTO forma_pagamento (id, descricao) VALUES (5, 'Cheque');
INSERT INTO forma_pagamento (id, descricao) VALUES (6, 'Vale-refeição');
INSERT INTO forma_pagamento (id, descricao) VALUES (7, 'Transferência bancária');
INSERT INTO forma_pagamento (id, descricao) VALUES (8, 'Bitcoin');

-- Ajustando os IDs para a tabela permissao
INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (3, 'EXCLUIR_COZINHAS', 'Permite excluir cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (4, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
INSERT INTO permissao (id, nome, descricao) VALUES (5, 'EDITAR_RESTAURANTES', 'Permite editar restaurantes');
INSERT INTO permissao (id, nome, descricao) VALUES (6, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
INSERT INTO permissao (id, nome, descricao) VALUES (7, 'EDITAR_ESTADOS', 'Permite editar estados');
INSERT INTO permissao (id, nome, descricao) VALUES (8, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
