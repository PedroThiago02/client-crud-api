package br.com.devsuperior.clientescrud.repositories;

import br.com.devsuperior.clientescrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
