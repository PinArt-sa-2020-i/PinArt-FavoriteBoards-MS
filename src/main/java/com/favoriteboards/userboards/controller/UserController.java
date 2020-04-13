package com.favoriteboards.userboards.controller;

import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {

        @Autowired
        private final UserService userService;

        @GetMapping("/getall")
        public ResponseEntity<List<User>> getAllUsers(){
                return ResponseEntity.ok(userService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
                return ResponseEntity.ok(userService.getUser(id));
        }

        @PostMapping(path ="/create", consumes = "application/json")
        public ResponseEntity<Long> createUser(@RequestBody User user) {
                try{
                        Long count=userService.countUsers(user.getId());
                        if (count==0) {
                                userService.createUser(user);
                                return new ResponseEntity<>(count, HttpStatus.CREATED);
                        }else {
                                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
                        }
                }catch (Exception e){
                        return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
                }
        }

       @DeleteMapping("/delete/{id}")
        public ResponseEntity<Long> deleteUser(@PathVariable(value = "id") Long id) {
                Long count = userService.countUsers(id);
                try{
                        if (count==1) {
                                userService.deleteUser(userService.getUser(id));
                                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
                        }else {
                                return new ResponseEntity<>(count, HttpStatus.BAD_REQUEST);
                        }
                }catch (Exception e) {
                        return new ResponseEntity<>((long)-1,HttpStatus.FORBIDDEN);
                }
        }
}
