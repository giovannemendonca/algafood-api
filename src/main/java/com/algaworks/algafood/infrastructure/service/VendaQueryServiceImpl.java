package com.algaworks.algafood.infrastructure.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {

        List<Predicate> predicates = new ArrayList<Predicate>();


        var builder = manager.getCriteriaBuilder(); // esse getCriteriaBuilder() é o que cria a query
        var query = builder.createQuery(VendaDiaria.class); // aqui é onde se define o tipo de retorno da query
        var root = query.from(Pedido.class); // aqui é onde se define a entidade que será consultada


        if (filtro.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
        }
        if (filtro.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }
        if (filtro.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }
        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));


        // essa função é para pegar a date no formato yyyy-mm-dd e não yyyy-mm-dd hh:mm:ss como é salvo no banco
        var functionDateDataCriacao = builder.function("date", LocalDate.class, root.get("dataCriacao"));

        // aqui é o select da query
        // no primeiro parâmetro é o tipo de retorno da query
        // no segundo é o select em si
        // ex: select date(p.data_criacao) as data, count(p.id) as total_vendas, sum(p.valor_total) as total_faturado
        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal"))
        );

        query.select(selection); // aqui é onde se define o select da query
        query.groupBy(functionDateDataCriacao); // aqui é onde se define o group by da query ex: group by date(p.data_criacao)
        query.where(predicates.toArray(new Predicate[0]));

        var queryResult = manager.createQuery(query).getResultList(); // aqui é onde se executa a query e se retorna o resultado

        return queryResult;
    }
}
