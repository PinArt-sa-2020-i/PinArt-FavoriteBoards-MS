package com.favoriteboards.userboards.controller;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.repository.UserRepository;
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
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private final BoardService boardService;
    @Autowired
    private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<Board>> getAllBoards(){

        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/getAllBoardsByUser/{user_id}")
    public ResponseEntity<List<Board>> getAllBoardsByUser(@PathVariable(value = "user_id") Long user_id){
        return ResponseEntity.ok(boardService.findByUserId(user_id));
    }



    @GetMapping("/boards/{id}")
    public ResponseEntity<Board> getContactById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(boardService.getBoard(id));
    }


    @PostMapping(path ="create/user/{userId}", consumes = "application/json")
    public ResponseEntity<Board> createBoard(@PathVariable (value = "userId") Long userId,
                                            @RequestBody Board board) {
        try{
            Long count=boardService.countBoards(board.getId());
            if (count==0) {

                User user= userService.getUser(userId);
                board.setUser(user);

                boardService.createBoard(board);
                return new ResponseEntity<>(board, HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/users/{userId}/boards/{boardId}")
    public ResponseEntity<Long> updateBoard(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "boardId") Long boardId,
                                 @RequestBody Board board) {

        try{
            Long count=boardService.countBoards(boardId);

            if (count>0) {

                Board board_update = boardService.getBoard(boardId);

                board_update.setDescription(board.getDescription());
                board_update.setName(board.getName());

                User user= userService.getUser(userId);
                board_update.setUser(user);

                boardService.updateBoard(board_update);
                return new ResponseEntity<>(count, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }


    }


   @DeleteMapping(path ="/delete/{boardId}")
    public ResponseEntity<Long> deleteBoard(@PathVariable (value = "boardId") Long boardId) {
        try{
            Long count=boardService.countBoards(boardId);
            if (count==1) {

                Board board_delete = boardService.getBoard(boardId);

                boardService.deleteBoard(board_delete);
                return new ResponseEntity<>(count, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(count,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>((long) -1,HttpStatus.FORBIDDEN);
        }
    }






}
