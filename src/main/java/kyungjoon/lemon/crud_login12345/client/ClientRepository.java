package kyungjoon.lemon.crud_login12345.client;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {


}
