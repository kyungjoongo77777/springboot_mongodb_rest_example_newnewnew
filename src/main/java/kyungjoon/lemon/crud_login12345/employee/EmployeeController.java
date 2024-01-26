package kyungjoon.lemon.crud_login12345.employee;

import jakarta.validation.Valid;
import kyungjoon.lemon.crud_login12345.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        return employeeRepository.save(employee);
    }


    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new Exception("no exmployyyy"));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;


    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee employeeDetails) throws  Exception {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


}
