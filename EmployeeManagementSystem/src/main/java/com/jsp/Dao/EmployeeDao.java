package com.jsp.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Dto.Employee;


@Repository
public class EmployeeDao {
	
	@Autowired
	EntityManager manager;
	
	@Autowired
	EntityTransaction transaction;
	
	// to  insert employee object into DB
	public String insertEmployee(Employee employee)
	{
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
		return "Employee details are stored successfully";
	}
	
	
	// to find an employee object based on ID
	public Employee findEmployeeById(int id)
	{
		Employee emp = manager.find(Employee.class, id);
		if(emp != null) {
			return emp;
		}
		else {
			return null;
		}
	}
	
	
	// to delete an employee object based on ID
	public int deleteEmployeeById(int id) {
		Employee emp = manager.find(Employee.class, id);
		if(emp!=null) {
			transaction.begin();
			manager.remove(emp);
			transaction.commit();
			return 0;
		}
		return 1;
	}
	
	
	// to update Employee details in DB
	public int updateEmployeeById(int id , String newName , long newPhone)
	{
		Employee emp = manager.find(Employee.class , id);
		if(emp != null ) {
			emp.setName(newName);
			emp.setPhone(newPhone);
			transaction.begin();
			manager.merge(emp);
			transaction.commit();
			return 0;
		}
		return 1;
	}
	
	
	// tp fetch all employee objects from DB
	public List<Employee> getAllEmployees()
	{
		Query q = manager.createQuery("select e from Employee e");
		List<Employee> employeeList = q.getResultList();
		return employeeList;
		
	}
	
	
}
