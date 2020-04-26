package com.favoriteboards.userboards.controller;

import com.favoriteboards.userboards.dto.BoardDTO;
import com.favoriteboards.userboards.dto.BoardFollowDTO;
import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.repository.UserRepository;
import com.favoriteboards.userboards.service.BoardService;
import com.favoriteboards.userboards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @GetMapping("/getAllBoardsByName/{name}")
    public ResponseEntity<List<Board>> getAllBoardsByName(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(boardService.findByName(name));
    }

    @GetMapping("/getAllBoardsByName2/{name}")
    public ResponseEntity<List<BoardDTO>> getAllBoardsByName2(@PathVariable(value = "name") String name){

        List<BoardDTO> boardDTOs = new ArrayList<BoardDTO>();

        List<Board> boards =  boardService.findByName(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
       

        for(Board bw: boards){
            boardDTOs.add(new BoardDTO(bw.getId(),
                    bw.getName(),
                    bw.getDescription(),
                    bw.getCreatedAt().format(formatter),
                    bw.getUser().getId()));
        }
        return ResponseEntity.ok(boardDTOs);
    }

    @GetMapping("/{id}")
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

    @PostMapping(path ="create", consumes = "application/json")
    public ResponseEntity<Board> createBoard(@RequestBody BoardDTO boardDTO) {
        try{
            //Long count=boardService.countBoards(board.getId());
           // if (count==0) {

                User user= userService.getUser(boardDTO.getUser_id());

                Board board  = new Board();
                board.setUser(user);
                board.setName(boardDTO.getName());
                board.setDescription(boardDTO.getDescription());


                boardService.createBoard(board);
                return new ResponseEntity<>(board, HttpStatus.CREATED);
           // }else {
           //     return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
           // }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/update/board/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable (value = "boardId") Long boardId,
                                 @RequestBody Board board) {

        try{
            Long count=boardService.countBoards(boardId);

            if (count>0) {

                Board board_update = boardService.getBoard(boardId);

                board_update.setDescription(board.getDescription());
                board_update.setName(board.getName());

                //User user= userService.getUser(userId);
                //board_update.setUser(user);

                boardService.updateBoard(board_update);
                return new ResponseEntity<>(board_update, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }


    }


   @DeleteMapping(path ="/delete/{boardId}")
    public ResponseEntity<Board> deleteBoard(@PathVariable (value = "boardId") Long boardId) {
        try{
            Long count=boardService.countBoards(boardId);
            if (count==1) {

                Board board_delete = boardService.getBoard(boardId);

                boardService.deleteBoard(board_delete);
                return new ResponseEntity<>(board_delete, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }






}
