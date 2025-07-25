package tech.ricardo.proposta_app.service;

import org.springframework.data.domain.Page;
import tech.ricardo.proposta_app.controller.dto.PropostaPaginadaResponseDto;
import tech.ricardo.proposta_app.controller.dto.PropostaRequestDto;
import tech.ricardo.proposta_app.controller.dto.PropostaResponseDto;

public interface IPropostaService {

    PropostaResponseDto criarProposta(PropostaRequestDto dto);

    Page<PropostaPaginadaResponseDto> listarPropostas(Integer page, Integer pageSize);
}
