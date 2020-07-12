package main.java.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="PURCHASED_PRODUCT")
public class PurchasedProduct {

	@Id
	@GeneratedValue
	@Column(name="PURCHASED_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="OREDER_ID")
	private Order orders;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	
	private int orderPrice; // 주문 가격
	private int count; // 주문 수량
	
	public void setOrder(Order order) {
		if(this.orders != null) {
			this.orders.getPurchasedList().remove(this);
		}

		this.orders = order;
		this.orders.getPurchasedList().add(this);
	}
	
}
