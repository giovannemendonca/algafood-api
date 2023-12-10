
use algafood;

show tables;

select * from restaurante;

select * from restaurante_forma_pagamento;

select * from cidade;

select * from estado;

select * from cozinha;

select * from algafood.grupo;

select * from usuario;

select * from algafood.produto p;

select * from permissao;

select * from algafood.grupo_permissao;

select * from algafood.grupo;

select * from algafood.usuario_grupo;

select * from flyway_schema_history;


SELECT * 
FROM restaurante r1 
JOIN cozinha c1 ON c1.id = r1.cozinha_id 
WHERE c1.id = 1;


SELECT r.*, fp.*
FROM restaurante r
JOIN restaurante_forma_pagamento rfp ON r.id = rfp.restaurante_id
JOIN forma_pagamento fp ON rfp.forma_pagamento_id = fp.id;
 

SELECT r.nome, r.endereco_cep, p.*
FROM restaurante r 
JOIN produto p ON p.restaurante_id=r.id
WHERE r.id=1;
	
select * from produto;


select * from pedido;


