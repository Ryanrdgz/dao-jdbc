package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entitiel.Department;
import model.entitiel.Seller;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentdao = DaoFactory.createDepartmentDao();
		System.out.println("\n=== TESTE 1: Department insert ===");
		Department newDepartment = new Department(null, "Barcelona");
		departmentdao.insert(newDepartment);
		System.out.println("Inserted! New id = " + newDepartment.getId());
		
		System.out.println("\n=== TESTE 2: Department findById ===");
		Department department = departmentdao.findById(1);
		System.out.println(department);
				
		System.out.println("\n=== TESTE 3: Department update ===");
		department = departmentdao.findById(3); //Procurar departmento de id 1, carrega os dados dele no objeto department
		department.setName("Scrums"); //Dei novo nome pra ele
		departmentdao.update(department); // Salvo novo departmento, atualiza dados dele
		System.out.println("Update completed");
		
		System.out.println("\n=== TESTE 4: Department findAll ===");
		List<Department> list = departmentdao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 5: Department delete ===");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();sc.nextLine();
		departmentdao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();
	}

}
