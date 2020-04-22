package com.favoriteboards.userboards.controller;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.model.UserFollow;
import com.favoriteboards.userboards.service.BoardFollowService;
import com.favoriteboards.userboards.service.UserFollowService;
import com.favoriteboards.userboards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userfollow")
public class UserFollowController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserFollowService userFollowService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserFollow>> getAllUsers() {
        return ResponseEntity.ok(userFollowService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFollow> getUserFollowById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userFollowService.getUserFollow(id));
    }

    @GetMapping("/getUsersFollowingByFollower/{follower_id}")
    public ResponseEntity<List<User>> getUsersFollowingByFollower(@PathVariable(value = "follower_id") Long id){
        return ResponseEntity.ok(userFollowService.findFollowingsByFollower(id));
    }

    @PostMapping(path ="/create/userFollowing/{userFollowing}/userFollower/{userFollower}", consumes = "application/json")
    public ResponseEntity<UserFollow> createUserFollow(@PathVariable (value = "userFollowing") Long userFollowing,
                                            @PathVariable (value = "userFollower") Long userFollower) {
        try{

            UserFollow userFollow= new UserFollow();

            User user2= userService.getUser(userFollower);
             User user1= userService.getUser(userFollowing);
             if(user2!=null && user1!=null)
             {
                 userFollow.setUserFollowing(user1);
                 userFollow.setUserFollower(user2);


                 userFollowService.createUserFollow(userFollow);

                 return new ResponseEntity<>(userFollow, HttpStatus.CREATED);

             }
             else{
                 return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
             }

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path ="/delete/{userFollowId}")
    public ResponseEntity<Long> deleteUserFollow(@PathVariable (value = "userFollowId") Long userFollowId) {
        try{
            Long count=userFollowService.countUserFollow(userFollowId);
            if (count==1) {

                UserFollow userFollowDelete = userFollowService.getUserFollow(userFollowId);

                userFollowService.deleteUserFollow(userFollowDelete);
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping(path ="/delete/follower/{follower_id}/following/{following_id}")
    public ResponseEntity<Long> deleteUserFollow(@PathVariable (value = "follower_id") Long follower_id,
                                                 @PathVariable (value = "following_id") Long following_id) {
        try{
            long count=0;
            //Long count=userFollowService.countUserFollow(userFollowId);
            //if (count==1) {

                UserFollow userFollowDelete = userFollowService.getUserFollow(follower_id, following_id);

               userFollowService.deleteUserFollow(userFollowDelete);
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
           /* }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }*/
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }






}
