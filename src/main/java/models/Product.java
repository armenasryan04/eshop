package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product  {
  private int id;
  private String name;
  private String description;
  private int price;
  private int quantity;
  private Category category;
}
