package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sd = DaoFactory.createSellerDao();
		
		System.out.println("--- Testing findById from Seller ---");
		Seller seller = sd.findById(3);
		
		System.out.println(seller);
		
		System.out.println("\n--- Testing findByDepartment from Seller ---");
		Department dep = new Department(2, null);
		List<Seller> listSeller = sd.findByDepartment(dep);
		for(Seller item : listSeller) {
			System.out.println(item);
		}
		
		System.out.println("\n--- Testing findByAll from Seller ---");
		listSeller = sd.findAll();
		for(Seller item : listSeller) {
			System.out.println(item);
		}
		
		System.out.println("\n--- Testing Insert Seller ---");
		Seller seller2 = new Seller(null, "Thiago Fritz", "fritz@gmail.com", new Date(), 4500.0, dep);
		sd.insert(seller2);
		System.out.println("Success! New id seller: " + seller2.getId());
		
		System.out.println("\n--- Testing Update Seller ---");
		seller = sd.findById(3);
		seller.setName("Bob Pink");
		seller.setEmail("bobPink@gmail.com");
		sd.update(seller);
		System.out.println("Updated!");
		
		System.out.println("\n--- Testing Delete Seller ---");
		System.out.println("Enter a id Seller to delete: ");
		int id = sc.nextInt();
		sd.deleteById(id);
		System.out.println("Deleted!");
		
		sc.close();
	}

}