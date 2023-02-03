package com.example.TemplateCRUD.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.example.TemplateCRUD.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.TemplateCRUD.models.*;
import java.util.List;
import java.util.Optional;
@RestController

@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
	@GetMapping("/all")
	public  ResponseEntity<List<UserModel>> teste() {
        
        List<UserModel> lista= userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(lista);

	}

	@GetMapping("/findone")
	public  ResponseEntity<Object> findByTd() {
        Long id=1l;
        Optional<UserModel> userOptional= userRepository.findById(id);
        if(userOptional.isPresent()){
            UserModel user=userOptional.get();

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
	}
}
