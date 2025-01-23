package raj.yash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raj.yash.model.User;
import raj.yash.repository.UserRespository;

@Service
public class UserService {

    private final UserRespository userRepo;
    @Autowired
    public UserService(UserRespository repo){
        this.userRepo = repo;
    }

    public User getUserById(String id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    }



}
