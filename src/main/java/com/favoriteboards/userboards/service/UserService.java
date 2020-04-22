package com.favoriteboards.userboards.service;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

  public User getUser(Long Id){
       // return userRepository.findByid(Id);
      return userRepository.findUser(Id);

    }


    //public List<Board> findByUserId(Long user_id );

    public Long countUsers(Long id) {
       return userRepository.countByid(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
