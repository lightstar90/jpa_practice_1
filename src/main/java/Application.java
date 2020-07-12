package main.java;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import main.java.entities.Order;
import main.java.entities.OrderStatus;
import main.java.entities.Product;
import main.java.entities.PurchasedProduct;
import main.java.entities.Users;

public class Application {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_practice");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		try{
			tx.begin();
			
			order(em);
			printList(em);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			em.close();
		} finally {
			em.close();
		}
		
		emf.close();
	}
	
	private static void order(EntityManager em) {
		// 제품 생성
		Product p1 = new Product();
		p1.setName("세제");
		p1.setPrice(1000);
		p1.setRemainingQuantity(100);
		
		Product p2 = new Product();
		p2.setName("물");
		p2.setPrice(800);
		p2.setRemainingQuantity(50);
		
		Product p3 = new Product();
		p3.setName("과자");
		p3.setPrice(500);
		p3.setRemainingQuantity(200);
		
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		System.out.println("2");
		
		// 유저 생성
		Users u1 = new Users();
		u1.setName("유저1");
		u1.setContry("한국");
		u1.setCity("서울");
		u1.setStreet("봉은사로");
		u1.setZeepcode("12345");
		
		Users u2 = new Users();
		u2.setName("유저2");
		u2.setContry("한국");
		u2.setCity("수원");
		u2.setStreet("월드컵로");
		u2.setZeepcode("4321");
		
		em.persist(u1);
		em.persist(u2);
		
		// 제품 주문
		// 1
		Order order1 = new Order();
		order1.setStatus(OrderStatus.ORDER);
		order1.setUser(u1);
		order1.setOrderDate(new Date());
		
		em.persist(order1);
		
		PurchasedProduct pc1 = new PurchasedProduct();
		pc1.setOrder(order1);
		pc1.setProduct(p1);
		pc1.setCount(10);
		
		PurchasedProduct pc2 = new PurchasedProduct();
		pc2.setOrder(order1);
		pc2.setProduct(p2);
		pc2.setCount(12);
		
		em.persist(pc1);
		em.persist(pc2);
		
		// 2
		Order order2 = new Order();
		order2.setStatus(OrderStatus.ORDER);
		order2.setUser(u2);
		order2.setOrderDate(new Date());
		em.persist(order2);
		
		PurchasedProduct pc3 = new PurchasedProduct();
		pc3.setOrder(order2);
		pc3.setProduct(p3);
		pc3.setCount(1);
		em.persist(pc3);
	}
	
	private static void printList(EntityManager em) {
		String jpql = "select u from Users u";

		// 유저 호출
		List<Users> users = em.createQuery(jpql, Users.class).getResultList();
		System.out.println("Users count : " + users.size());
		
		// 주문 확인
		for(Users u : users) {
			System.out.println("User ID : " + u.getId() + ", User Name : " + u.getName() + ", Order count : " + u.getOrders().size());
			List<Order> orders = u.getOrders();
			for(Order o : orders) {
				System.out.println("Order ID : " + o.getId() + ", Order Date : " + o.getOrderDate() + ", Purchased Size : " + o.getPurchasedList().size());
				List<PurchasedProduct> purchases = o.getPurchasedList();
				for(PurchasedProduct p : purchases) {
					System.out.println("Purchased Id : " + p.getId() + ", Product ID : " 
				+ p.getProduct().getId() + ", Product Name : " + p.getProduct().getName());
				}
			}
			System.out.println();
		}
	}
	
}
