package tech.ricardo.proposta_app.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.ricardo.proposta_app.controller.dto.PropostaPaginadaResponseDto;
import tech.ricardo.proposta_app.controller.dto.PropostaRequestDto;
import tech.ricardo.proposta_app.controller.dto.PropostaResponseDto;
import tech.ricardo.proposta_app.controller.dto.UsuarioResponseDto;
import tech.ricardo.proposta_app.entity.PropostaEntity;
import tech.ricardo.proposta_app.entity.UsuarioEntity;
import tech.ricardo.proposta_app.entity.enums.StatusProposta;
import tech.ricardo.proposta_app.exception.PropostaNaoEncontradaException;
import tech.ricardo.proposta_app.repository.PropostaRepository;
import tech.ricardo.proposta_app.repository.UsuarioRepository;
import tech.ricardo.proposta_app.service.IPropostaService;

import java.util.Optional;

@Service
public class PropostaServiceImp implements IPropostaService {

    private final UsuarioRepository usuarioRepository;
    private final PropostaRepository propostaRepository;

    public PropostaServiceImp(UsuarioRepository usuarioRepository, PropostaRepository propostaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.propostaRepository = propostaRepository;
    }

    @Transactional
    @Override
    public PropostaResponseDto criarProposta(PropostaRequestDto dto) {
        var usuario = persistirUsuario(dto);
        var proposta = persistirProposta(dto, usuario);

        if (proposta.isEmpty()) {
            throw new PropostaNaoEncontradaException("Falha ao criar a proposta.");
        }

        return proposta.map(response -> mapToPropostaResponse(response, usuario)).get();
    }

    @Override
    public Page<PropostaPaginadaResponseDto> listarPropostas(Integer page, Integer pageSize) {
        return propostaRepository
                .findAll(PageRequest.of(page, pageSize))
                .map(proposta -> new PropostaPaginadaResponseDto(
                        proposta.getPropostaId(),
                        proposta.getValorSolicitado(),
                        proposta.getPrazoPagamento(),
                        proposta.getStatusProposta(),
                        proposta.getIntegrada(),
                        proposta.getObservacao(),
                        new UsuarioResponseDto(
                                proposta.getUsuarioEntity().getNome(),
                                proposta.getUsuarioEntity().getTelefone(),
                                proposta.getUsuarioEntity().getRenda())

                ));
    }

    private PropostaResponseDto mapToPropostaResponse(PropostaEntity response, UsuarioEntity usuario) {
        return new PropostaResponseDto(
                response.getPropostaId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getTelefone(),
                usuario.getCpf(),
                usuario.getRenda(),
                response.getValorSolicitado(),
                response.getPrazoPagamento(),
                response.getStatusProposta(),
                response.getObservacao()
        );
    }

    private Optional<PropostaEntity> persistirProposta(PropostaRequestDto dto, UsuarioEntity usuario) {
        final var proposta = new PropostaEntity();

        proposta.setValorSolicitado(dto.valorSolicitado());
        proposta.setPrazoPagamento(dto.prazoPagamento());
        proposta.setStatusProposta(StatusProposta.ANALISE);

        proposta.setUsuarioEntity(usuario);

        propostaRepository.save(proposta);

        return Optional.of(proposta);
    }

    private UsuarioEntity persistirUsuario(PropostaRequestDto dto) {
        final var usuario = new UsuarioEntity();

        usuario.setNome(dto.nome());
        usuario.setSobrenome(dto.sobrenome());
        usuario.setTelefone(dto.telefone());
        usuario.setCpf(dto.cpf());
        usuario.setRenda(dto.renda());

        return usuarioRepository.save(usuario);
    }


}
