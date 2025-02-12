package it.uniromatre.pinaback.artisti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    @Query("""
    select a 
    from Artista a
    """)
    List<Artista> getAll();

    @Query("""
    SELECT a FROM Artista a ORDER BY a.name ASC
    """)
    List<Artista> getAllOrdered();

}
