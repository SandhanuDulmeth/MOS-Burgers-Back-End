package edu.icet.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class Item {

    String itemNo;
    String itemType;
    String name;
    Double price;
    String imageUrl;

    public Item() {
    }

    public Item(String itemNo, String itemType, String name, Double price, String imageUrl) {
        this.itemNo = itemNo;
        this.itemType = itemType;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
