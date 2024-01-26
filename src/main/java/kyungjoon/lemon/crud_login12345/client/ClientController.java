package kyungjoon.lemon.crud_login12345.client;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public List<Client> getAllClient() {
        List<Client> clients = clientRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDt"));
        return clients;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Optional<Client>> getClientById(@PathVariable(value = "id") String _id) {

        Optional<Client> client = clientRepository.findById(_id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {

        client.setCreatedDt(new Date().toString());

        return clientRepository.save(client);
    }

    @DeleteMapping("/client/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") String pId) throws Exception {
        Client client = clientRepository.findById(pId).orElseThrow(() -> new Exception("no exmployyyy"));
        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") String _id,
                                               @Valid @RequestBody Client clientDetail) throws Exception {
        Client client = clientRepository.findById(_id)
                .orElseThrow(() -> new Exception("Client not found for this id :: " + _id));

        client.setFullName(clientDetail.getFullName());
        client.setAge(clientDetail.getAge());
        client.setNickname(clientDetail.getNickname());
        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }


}
