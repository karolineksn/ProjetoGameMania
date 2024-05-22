package com.example.gamemania.controller;

import com.example.gamemania.game.Game;
import com.example.gamemania.game.GameRequestDTO;
import com.example.gamemania.game.GameResponseDTO;
import com.example.gamemania.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/game")
public class GameManiaController {

    @Autowired
    private GameRepository repository;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameRequestDTO gameRequestDTO) {
        Game game = new Game(gameRequestDTO);
        repository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(game);
    }

    @GetMapping
    public List<GameResponseDTO> getAll() {
        return repository.findAll().stream().map(GameResponseDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Jogo excluído com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGame(@PathVariable Long id, @RequestBody GameRequestDTO newData) {
        Optional<Game> optionalGame = repository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setTitle(newData.getTitle());
            game.setPrice(newData.getPrice());
            game.setImage(newData.getImage());
            repository.save(game);
            return ResponseEntity.ok("Jogo atualizado com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-unavailable")
    public ResponseEntity<String> markUnavailable(@PathVariable Long id) {
        Optional<Game> optionalGame = repository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setAvailable(false);
            repository.save(game);
            return ResponseEntity.ok("Jogo marcado como indisponível com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
