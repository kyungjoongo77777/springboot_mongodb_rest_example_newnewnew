package kyungjoon.lemon.crud_login12345.board;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends MongoRepository<BoardModel, String> {


}
