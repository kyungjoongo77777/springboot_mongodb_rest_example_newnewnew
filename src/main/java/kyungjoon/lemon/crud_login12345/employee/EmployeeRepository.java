package kyungjoon.lemon.crud_login12345.employee;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {


}
