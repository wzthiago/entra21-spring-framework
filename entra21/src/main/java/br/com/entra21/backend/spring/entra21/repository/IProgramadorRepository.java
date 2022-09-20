package br.com.entra21.backend.spring.entra21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.entra21.backend.spring.entra21.model.Programador;

public interface IProgramadorRepository extends JpaRepository<Programador, Integer>{
	
	

}
