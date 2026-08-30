package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entitiel.Department;
import model.entitiel.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn; // objeto conn vai esta disponivel em qualquer lugar dessa classe
	
	public SellerDaoJDBC(Connection conn) { //dependencia 
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery(); //esse sql comando vai ser executado e vai cair dentro de resultSet
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			return null;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException { //tratei a exceção que estava dando, com throws
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException { //tratei a exceção que estava dando, com throws
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE departmentId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, department.getId());
			
			rs = st.executeQuery(); 
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>(); //criado map vazio, dentro dele vai ser guardado qualquer departamento que for instanciar
			
			while (rs.next()) { //testa se o departamento ja existe
				
				Department dep = map.get(rs.getInt("DepartmentId")); //metodo get busca um departmento q tem esse id passado no parenteses
				
				if (dep == null) { // se for null, instancia o departamento
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep); //guardando departamento dentro do map
				}
			
				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
}
