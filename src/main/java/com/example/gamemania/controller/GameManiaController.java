package com.example.gamemania.controller;

import com.example.gamemania.game.Game;
import com.example.gamemania.game.GameRequestDTO;
import com.example.gamemania.game.GameResponseDTO;
import com.example.gamemania.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("game")
public class GameManiaController {
    @Autowired
    private GameRepository repository;
    @CrossOrigin(origins = "*", allowedHeaders = "*") //conf para o frontEnd
    @PostMapping
    public void saveGame(@RequestBody GameRequestDTO data){
        Game gameData = new Game(data);
        repository.save(gameData);
        return;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<GameResponseDTO> getAll(){
        List<GameResponseDTO> gameList = repository.findAll().stream().map(GameResponseDTO::new).toList();
        return gameList;
    }
}
