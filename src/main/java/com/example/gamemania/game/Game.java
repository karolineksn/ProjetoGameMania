package com.example.gamemania.game;

import jakarta.persistence.*;
import lombok.*;

@Table(name= "games")
@Entity(name= "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private Integer price;
    private boolean available;

    public Game(GameRequestDTO data){
        this.image = data.getImage();
        this.price = data.getPrice();
        this.title = data.getTitle();
    }
}
