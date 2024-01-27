package kyungjoon.lemon.crud_login12345.user;

import kyungjoon.lemon.crud_login12345.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/user")
    public User createUser(@RequestBody User userOne) {
        userOne.setCreatedDt(new Date().toString());
        return userRepository.save(userOne);
    }

    @PostMapping("/user/login")
    public User checkUser(@RequestBody User userOne) {
        userOne.setCreatedDt(new Date().toString());

        String userId = userOne.getUserId();
        String paramPassword = userOne.getPassword();
        User _userOne = userRepository.findUsersByUserId(userId);

        if (_userOne == null) {
            return null;

        } else if (Objects.equals(_userOne.getPassword(), paramPassword)) {//todo: userid, password가 일치하는 경우에만 유저 정보를 리턴
            String accessToken= jwtService.getJwt();
            System.out.println(accessToken);
            return _userOne;
        } else {
            return null;
        }

    }

    @GetMapping("/user")
    public List<User> getUsers() {
        List<User> clients = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDt"));
        return clients;
    }


    /**
     * todo : id,pwd를 체크 해서 맞다면 token을 내려주는 로직 추가__
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUserOne(@PathVariable(value = "userId") String userId) throws Exception {
        Optional<User> user = Optional.ofNullable(userRepository.findUsersByUserId(userId));
        return ResponseEntity.ok().body(user);
    }


    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") String pId) throws Exception {
        User userOne = userRepository.findById(pId).orElseThrow(() -> new Exception("no exmployyyy"));
        userRepository.delete(userOne);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

//    @PutMapping("/user/{id}")
//    public ResponseEntity<User> updateClient(@PathVariable(value = "id") String _id,
//                                             @Valid @RequestBody User clientDetail) throws Exception {
//        User client = userRepository.findById(_id)
//                .orElseThrow(() -> new Exception("Client not found for this id :: " + _id));
//
//        client.setFullName(clientDetail.getFullName());
//        client.setAge(clientDetail.getAge());
//        client.setNickname(clientDetail.getNickname());
//        final User updatedClient = userRepository.save(client);
//        return ResponseEntity.ok(updatedClient);
//    }


}
