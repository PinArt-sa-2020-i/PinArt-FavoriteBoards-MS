package com.favoriteboards.userboards.controller;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.modelview.BoardFollowView;
import com.favoriteboards.userboards.service.BoardFollowService;
import com.favoriteboards.userboards.service.BoardService;
import com.favoriteboards.userboards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardfollow")
public class BoardFollowController {

    @Autowired
    private final BoardFollowService boardFollowService;
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<BoardFollow>> getAllUsers() {
        return ResponseEntity.ok(boardFollowService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardFollow> getBoardFollowById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(boardFollowService.getBoardFollow(id));
    }

    @GetMapping("/getAllBoardsFollowByUser/{user_id}")
    public ResponseEntity<List<Board>> getAllBoardsFollowByUser(@PathVariable(value = "user_id") Long user_id){

        return ResponseEntity.ok(boardFollowService.findByUserId(user_id));
    }



    @PostMapping(path ="create/user/{userId}/board/{boardId}", consumes = "application/json")
    public ResponseEntity<Long> createBoardFollow(@PathVariable (value = "userId") Long userId,
                                            @PathVariable (value = "boardId") Long boardId) {
        try{

                BoardFollow boardFollow= new BoardFollow();
                User user= userService.getUser(userId);
                boardFollow.setUser(user);


                Board board = boardService.getBoard(boardId);
                boardFollow.setBoard(board);

                boardFollowService.createBoardFollow(boardFollow);

                return new ResponseEntity<>(boardFollow.getId(), HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }



    @DeleteMapping(path ="/delete/{boardFollowId}")
    public ResponseEntity<Long> deleteBoardFollow(@PathVariable (value = "boardFollowId") Long boardFollowId) {
        try{
            Long count=boardFollowService.countBoardFollow(boardFollowId);
            if (count==1) {

                BoardFollow boardFollowDelete = boardFollowService.getBoardFollow(boardFollowId);

                boardFollowService.deleteBoardFollow(boardFollowDelete);
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }


}
