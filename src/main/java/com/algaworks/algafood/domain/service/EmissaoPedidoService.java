package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmissaoPedidoService {

  private PedidoRepository pedidoRepository;
  private CadastroCidadeService cadastroCidade;
  private CadastroUsuarioService cadastroUsuario;
  private CadastroRestauranteService cadastroRestaurante;
  private CadastroFormaPagamentoService cadastroFormaPagamento;
  private CadastroProdutoService cadastroProduto;


  EmissaoPedidoService(PedidoRepository pedidoRepository,
                       CadastroCidadeService cadastroCidade,
                       CadastroUsuarioService cadastroUsuario,
                       CadastroRestauranteService cadastroRestaurante,
                       CadastroFormaPagamentoService cadastroFormaPagamento,
                       CadastroProdutoService cadastroProduto
                       ) {
    this.pedidoRepository = pedidoRepository;
    this.cadastroCidade = cadastroCidade;
    this.cadastroUsuario = cadastroUsuario;
    this.cadastroRestaurante = cadastroRestaurante;
    this.cadastroFormaPagamento = cadastroFormaPagamento;
    this.cadastroProduto = cadastroProduto;
  }

  @Transactional
  public Pedido emitir(Pedido pedido){
    validarPedido(pedido);
    validarItens(pedido);

    pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
    pedido.calcularValorTotal();

    return pedidoRepository.save(pedido);
  }

  public Pedido buscarOuFalhar(String codigo) {
    return pedidoRepository.findByCodigo(codigo).orElseThrow(
            () -> new PedidoNaoEncontradoException(codigo)
    );
  }


  private void validarPedido(Pedido pedido){
    Cidade cidade = cadastroCidade.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
    Usuario cliente = cadastroUsuario.buscarOuFalhar(pedido.getCliente().getId());
    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(pedido.getRestaurante().getId());
    FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(pedido.getFormaPagamento().getId());

    pedido.getEnderecoEntrega().setCidade(cidade);
    pedido.setCliente(cliente);
    pedido.setRestaurante(restaurante);
    pedido.setFormaPagamento(formaPagamento);
    
    if (restaurante.naoAceitaFormaPagamento(formaPagamento)){
      throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
              formaPagamento.getDescricao()));
    }
  }

  private void validarItens(Pedido pedido){
    pedido.getItens().forEach(item -> {
      Produto produto = cadastroProduto.buscarOuFalhar(pedido.getRestaurante().getId(), item.getProduto().getId());
      item.setPedido(pedido);
      item.setProduto(produto);
      item.setPrecoUnitario(produto.getPreco());
    });
  }
}
