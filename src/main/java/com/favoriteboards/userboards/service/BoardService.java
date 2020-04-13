package com.favoriteboards.userboards.service;

import com.favoriteboards.userboards.model.Board;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> findAll(){
        return boardRepository.findAll();
    }
    public Board getBoard(Long id){
        return boardRepository.findByid(id);
    }


    public Board createBoard(Board board){

        return boardRepository.save(board);
    }

    public Board updateBoard(Board board){

        return boardRepository.save(board);
    }

    public Long countBoards(Long id) {
        return boardRepository.countByid(id);
    }

    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }


}


