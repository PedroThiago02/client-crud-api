package br.com.devsuperior.clientescrud.services;

import br.com.devsuperior.clientescrud.dto.ClientDTO;
import br.com.devsuperior.clientescrud.entities.Client;
import br.com.devsuperior.clientescrud.repositories.ClientRepository;
import br.com.devsuperior.clientescrud.services.exceptions.DatabaseException;
import br.com.devsuperior.clientescrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> entity = clientRepository.findAll(pageable);
        return entity.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client entity = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID não encontrado"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        copyDtoToEntity(client, dto);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyDtoToEntity(client, dto);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(Client entity, ClientDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
