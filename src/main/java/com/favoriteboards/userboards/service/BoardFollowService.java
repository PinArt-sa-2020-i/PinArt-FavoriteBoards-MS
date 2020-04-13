package com.favoriteboards.userboards.service;

import com.favoriteboards.userboards.model.BoardFollow;
import com.favoriteboards.userboards.model.User;
import com.favoriteboards.userboards.repository.BoardFollowRepository;
import com.favoriteboards.userboards.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFollowService {
    @Autowired
    private BoardFollowRepository boardFollowRepository;

    public List<BoardFollow> findAll(){
        return boardFollowRepository.findAll();
    }

    public BoardFollow getBoardFollow(Long id){
        return boardFollowRepository.findByid(id);
    }

    public Long countBoardFollow(Long id) {
        return boardFollowRepository.countByid(id);
    }

    public BoardFollow createBoardFollow(BoardFollow boardFollow){
        return boardFollowRepository.save(boardFollow);
    }

    public void deleteBoardFollow(BoardFollow boardFollow) {
        boardFollowRepository.delete(boardFollow);
    }

}
