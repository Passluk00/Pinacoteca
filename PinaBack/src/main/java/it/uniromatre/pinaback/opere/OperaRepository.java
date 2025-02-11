package it.uniromatre.pinaback.opere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperaRepository extends JpaRepository<Opera, Integer> {
}
