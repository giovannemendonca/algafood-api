package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

  public static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe cadastro de cozinha com o código %d";

  @Autowired
  private RestauranteRepository restauranteRepository;
  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Restaurante salvar(Restaurante restaurante){
    Long cozinhaId = restaurante.getCozinha().getId();
    Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA,cozinhaId)));

    restaurante.setCozinha(cozinha);
    return restauranteRepository.save(restaurante);
  }



  public Restaurante buscarOuFalhar(Long cidadeId) {
    return restauranteRepository.findById(cidadeId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
  }


}
