package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data @AllArgsConstructor @Builder @ToString
public class Car {
    private int year;
    private String name;
    private CarMaker carmaker;
    private String color;

}
