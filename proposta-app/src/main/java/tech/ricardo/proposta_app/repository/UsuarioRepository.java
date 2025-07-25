package tech.ricardo.proposta_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ricardo.proposta_app.entity.UsuarioEntity;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
