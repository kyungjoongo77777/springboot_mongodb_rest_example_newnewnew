package kyungjoon.lemon.crud_login12345.board;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/board")
    public BoardModel create(@RequestBody BoardModel board) {
        board.setCreatedDt(new Date().toString());
        return boardRepository.save(board);
    }


    @GetMapping("/board")
    public List<BoardModel> fetchAll() {
        List<BoardModel> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDt"));
        return boards;
    }


    @DeleteMapping("/board/{id}")
    public Map<String, Boolean> deleteOne(@PathVariable(value = "id") String pId) throws Exception {
        BoardModel board = boardRepository.findById(pId).orElseThrow(() -> new Exception("no article"));
        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    @GetMapping("/board/{id}")
    public ResponseEntity<Optional<BoardModel>> getOne(@PathVariable(value = "id") String _id) {

        Optional<BoardModel> client = boardRepository.findById(_id);
        return ResponseEntity.ok().body(client);
    }


    @PutMapping("/board/{id}")
    public ResponseEntity<BoardModel> updateClient(@PathVariable(value = "id") String _id, @Valid @RequestBody BoardModel boardDetail) throws Exception {
        BoardModel board = boardRepository.findById(_id).orElseThrow(() -> new Exception("Client not found for this id :: " + _id));
        board.setContents(boardDetail.getContents());
        board.setTitle(boardDetail.getTitle());
        final BoardModel updatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(updatedBoard);
    }


}
