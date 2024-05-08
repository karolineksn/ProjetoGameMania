package com.example.gamemania.controller;

import com.example.gamemania.game.Game;
import com.example.gamemania.game.GameRequestDTO;
import com.example.gamemania.game.GameResponseDTO;
import com.example.gamemania.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

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
    public List<GameResponseDTO> getAll() {
        List<Game> gameList = repository.findAll();
        List<GameResponseDTO> gameResponseList = new ArrayList<>();

        for (Game game : gameList) {
            GameResponseDTO gameResponseDTO = new GameResponseDTO();
            gameResponseDTO.setId(game.getId());
            gameResponseDTO.setTitle(game.getTitle());
            gameResponseDTO.setImage(game.getImage());
            gameResponseDTO.setPrice(game.getPrice());
            gameResponseList.add(gameResponseDTO);
        }
        return gameResponseList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Jogo excluído com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@PathVariable Long id, @RequestBody GameRequestDTO newData) {
        Optional<Game> optionalGame = repository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            // Atualiza os dados do jogo com base nos dados recebidos
            game.setTitle(newData.getTitle());
            game.setPrice(newData.getPrice());
            game.setImage(newData.getImage());
            repository.save(game);
            return ResponseEntity.ok("Jogo atualizado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}/mark-unavailable")
    public ResponseEntity<String> markUnavailable(@PathVariable Long id) {
        Optional<Game> optionalGame = repository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setAvailable(false); // Define o jogo como indisponível
            repository.save(game);
            return ResponseEntity.ok("Jogo marcado como indisponível com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
