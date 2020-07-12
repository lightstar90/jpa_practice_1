package main.java.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ID")
	private Long id;
	
	private String name;
	private int price;
	
	@Column(name="REMAINING_QUANTITY")
	private int remainingQuantity;
	
}
