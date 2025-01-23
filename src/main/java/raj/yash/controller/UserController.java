package raj.yash.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController("/user")
public class UserController {

    
    @GetMapping("/{id}")
    public ResponseEntity getUserByID(@PathVariable Integer id){

  return null;
    }


}
