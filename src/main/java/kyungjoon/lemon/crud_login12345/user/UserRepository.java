package kyungjoon.lemon.crud_login12345.user;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUsersByUserId(String userId);

    User findUsersByCellphoneNo(String cellPhoneNo);
}
