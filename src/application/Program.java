package application;

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
		
	}

}
