package com.example.gamemania.game;

public record GameResponseDTO(Long id, String title, String image, Integer price) {
    public GameResponseDTO(Game game){
        this(game.getId(), game.getTitle(), game.getImage(), game.getPrice());
    }
}
