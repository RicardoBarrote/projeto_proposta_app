package tech.ricardo.proposta_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ricardo.proposta_app.entity.PropostaEntity;

import java.util.UUID;

public interface PropostaRepository extends JpaRepository<PropostaEntity, UUID> {
}
