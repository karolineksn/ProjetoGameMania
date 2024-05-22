package com.example.gamemania.game;

public class GameResponseDTO {
    private Long id;
    private String title;
    private String image;
    private Integer price;
    private boolean available;

    public GameResponseDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.image = game.getImage();
        this.price = game.getPrice();
        this.available = game.isAvailable();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}
