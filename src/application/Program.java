package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sd = DaoFactory.createSellerDao();
		
		System.out.println("--- Testing findById ---");
		Seller seller = sd.findById(3);
		
		System.out.println(seller);
		
		System.out.println("\n--- Testing findByDepartment ---");
		Department dep = new Department(2, null);
		List<Seller> listSeller = sd.findByDepartment(dep);
		for(Seller item : listSeller) {
			System.out.println(item);
		}
		
		System.out.println("\n--- Testing findByAll ---");
		listSeller = sd.findAll();
		for(Seller item : listSeller) {
			System.out.println(item);
		}
	}

}