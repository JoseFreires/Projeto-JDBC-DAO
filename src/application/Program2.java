package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao dd = DaoFactory.createDeparmentDao();
		
		System.out.println("--- Testing findById from Department ---");
		Department depart = dd.findById(1);
		System.out.println(depart);
		
		System.out.println("\n--- Testing findByAll from Department ---");
		List<Department> listDeparment = new ArrayList<>();
		listDeparment = dd.findAll();
		for(Department item : listDeparment) {
			System.out.println(item);
		}
		
		System.out.println("\n--- Testing Insert Department ---");
		Department newDepartment = new Department(null, "Games");
		dd.insert(newDepartment);
		System.out.println("Success! New id department: " + newDepartment.getId());
		
		
		System.out.println("\n--- Testing Update Department ---");
		depart = dd.findById(5);
		depart.setName("HR");
		dd.update(depart);
		System.out.println("Updated!");
		
		System.out.println("\n--- Testing Delete Department ---");
		System.out.println("Enter a id Department to delete: ");
		int id = sc.nextInt();
		dd.deleteById(id);
		System.out.println("Deleted!");
		
		sc.close();
	}

}
