package org.sid.dao;

import org.sid.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository  extends JpaRepository<Personne, Long>{

}
