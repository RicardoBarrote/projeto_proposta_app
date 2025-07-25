package tech.ricardo.proposta_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ricardo.proposta_app.controller.dto.*;
import tech.ricardo.proposta_app.service.implementation.PropostaServiceImp;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/propostas")
public class PropostaController {

    private final PropostaServiceImp propostaServiceImp;

    public PropostaController(PropostaServiceImp propostaServiceImp) {
        this.propostaServiceImp = propostaServiceImp;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDto> criarProposta(@RequestBody @Valid PropostaRequestDto dto) {
        var proposta = propostaServiceImp.criarProposta(dto);

        return ResponseEntity.created(URI.create("/api/v1/propostas/" + proposta.propostaId().toString())).build();
    }

    @GetMapping
    public ResponseEntity<ApiPaginationResponseDto<PropostaPaginadaResponseDto>> listarProposta(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var pageResponse = propostaServiceImp.listarPropostas(page, pageSize);

        return ResponseEntity
                .ok(new ApiPaginationResponseDto<>(
                        pageResponse.getContent(),
                        new PaginationResponseDto(page, pageSize, pageResponse.getTotalElements(), pageResponse.getTotalPages())
                ));
    }
}
