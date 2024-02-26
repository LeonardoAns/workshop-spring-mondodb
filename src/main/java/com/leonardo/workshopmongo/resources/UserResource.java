package com.leonardo.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.workshopmongo.domain.User;
import com.leonardo.workshopmongo.dto.UserDTO;
import com.leonardo.workshopmongo.services.UserService;

@RestController
public class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream()
                                    .map(UserDTO::new)
                                    .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    	User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
    
    
}
