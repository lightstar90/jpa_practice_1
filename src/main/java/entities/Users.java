package main.java.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	private String name;
	private String contry;
	private String city;
	private String street;
	private String zeepcode;
	
	@OneToMany(mappedBy="user")
	private List<Order> orders = new LinkedList<Order>();
	
}
