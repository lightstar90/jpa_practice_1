package main.java.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;

@Entity
@Getter
@Table(name="ORDERS")
public class Order {

	@Id
	@GeneratedValue
	@Column(name="ORDER_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@OneToMany(mappedBy="orders")
	private List<PurchasedProduct> purchasedList = new LinkedList<PurchasedProduct>();
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private OrderStatus status;
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public void setUser(Users user) {
		System.out.println("this.user : " + this.user);
		if(this.user != null) {
			this.user.getOrders().remove(this);
		}

		this.user = user;
		System.out.println("setting user");
		List<Order> list = user.getOrders();
		System.out.println("user get orders, list : " + list);
		list.add(this);
		System.out.println("list.add");
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
