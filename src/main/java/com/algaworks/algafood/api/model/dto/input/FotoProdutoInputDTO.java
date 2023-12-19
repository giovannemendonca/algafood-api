package com.algaworks.algafood.api.model.dto.input;

import com.algaworks.algafood.core.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FotoProdutoInputDTO {

    @NotNull
    @FileSize(max="500KB")
    private MultipartFile arquivo;

    @NotBlank
    private String descricao;
}
