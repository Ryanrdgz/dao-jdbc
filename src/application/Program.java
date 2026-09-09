package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entitiel.Department;
import model.entitiel.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.createSellerDao();
		
		System.out.println("=== TESTE 1: Seller findById ===");
		Seller seller = sellerdao.findById(3);		
		System.out.println(seller);
		
		System.out.println("\n=== TESTE 2: Seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerdao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 3: Seller findAll ===");
		list = sellerdao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TESTE 4: Seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department );
		sellerdao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
		
		System.out.println("\n=== TESTE 5: Seller update ===");
		seller = sellerdao.findById(1); //Procurar vendedor de id 1, carrega os dados dele no objeto seller
		seller.setName("MarthA Waine"); //Dei novo pra ele
		sellerdao.update(seller); // Salvo novo vendedor, atualiza dados dele
		System.out.println("Update completed");
		
	}

}
