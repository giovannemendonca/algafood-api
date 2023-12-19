package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.dto.input.FotoProdutoInputDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId,
                              @Valid FotoProdutoInputDTO fotoProdutoInput
    ) {

        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("/home/giovanne/studies/catalogo", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getArquivo().getContentType());
        System.out.println(fotoProdutoInput.getDescricao());

        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
