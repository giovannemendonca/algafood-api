package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

  public static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
  public static final String MGS_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";


  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Cozinha salvar(Cozinha cozinha){
    return cozinhaRepository.save(cozinha);
  }

  public void excluir(Long cozinhaId) {

    if (!cozinhaRepository.existsById(cozinhaId)) {
      throw new EntidadeNaoEncontradaException(
              String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
    }
    try {
      cozinhaRepository.deleteById(cozinhaId);

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
              String.format(MGS_COZINHA_EM_USO, cozinhaId));
    }
  }

  public Cozinha buscarOuFalhar(Long cozinhaId) {
    return cozinhaRepository.findById(cozinhaId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
  }

}

