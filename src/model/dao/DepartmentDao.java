package model.dao;

import java.util.List;

import model.entitiel.Department;

public interface DepartmentDao {
	
	void insert(Department obj); // inserir
	void update(Department obj); // editar
	void deleteById(Integer id); // deletar solicitando um id
	Department findById(Integer id); // buscar um department
	List<Department> findAll(); // buscar todos
	
}
