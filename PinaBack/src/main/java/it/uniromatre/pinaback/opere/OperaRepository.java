package it.uniromatre.pinaback.opere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperaRepository extends JpaRepository<Opera, Integer> {

    @Query("""
    select o
    from Opera o
    """)
    List<Opera> getAll();

    @Query("""
    select o
    from Opera o
    where o.posizione is null
    """)
    List<Opera> getAllByPosizione();
}
