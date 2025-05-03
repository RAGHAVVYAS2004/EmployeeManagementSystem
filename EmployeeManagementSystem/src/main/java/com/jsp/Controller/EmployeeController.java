package com.jsp.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.Dao.EmployeeDao;
import com.jsp.Dto.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeDao dao;
	
	@RequestMapping("/emp")
	public ModelAndView getEmployeeForm() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee" , new Employee());
		mv.setViewName("create");
		return mv;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String saveEmployee(@ModelAttribute Employee employee)
	{
		return dao.insertEmployee(employee);
	}
	
	//API TO SEARCH FOR AN EMPLOYEE BASED ON ID
	
	@RequestMapping("/search")
	public String getSearchForm() {
		return "search";
	}
	
	@RequestMapping("/get")
	public ModelAndView displayEmployeeById(@RequestParam int id) {
		
		Employee emp = dao.findEmployeeById(id);
		if(emp !=null) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("employee",emp);
			mv.setViewName("display");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("message", "Employee ID doesnt exist");
			mv.setViewName("error");
			return mv;
		}
	}
	
	// to get delete form
	@RequestMapping("/delete")
	public String getDeleteForm() {
		return "delete";
	}
	
	
	// to accept the form data (ID) and send it to DAO class for processing and 
	// then send back to user
	
	@RequestMapping("/remove")
	public ModelAndView removeEmployee(@RequestParam int id) {
		int res = dao.deleteEmployeeById(id);
		if(res == 0) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("successmsg" , "EMPLOYEE DETAILS ARE DELETED SUCCESSFULY");
			mv.setViewName("deletesuccess");
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("message" , "EMPLOYEE ID DOESNT EXIST");
			mv.setViewName("error");
			return mv;
		}
	}
	
	// REST API TO UPDATE EMPLOYEE DETAILS IN DB
	
	@RequestMapping("/edit")
	public String getUpdatedForm() {
		return "edit";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateEmployee(@RequestParam int id , @RequestParam String name , @RequestParam long phone) {
		int res = dao.updateEmployeeById(id, name, phone);
		if(res == 0) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("successmsg" , "EMPLOYEE DETAILS ARE UPDATED SUCCESSFULLY.");
			mv.setViewName("deletesuccess");
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView();
			mv.addObject("message" , "EMPLOYEE ID DOESNT EXIST.");
			mv.setViewName("error");
			return mv;
		}
	}
	
	// REST API TO FETCH ALL EMPLOYEE OBJECTS FROM DB AND DISPLAY
	@RequestMapping("/all")
	public ModelAndView displayAllEmployees()
	{
		List<Employee> list = dao.getAllEmployees();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("employeelist",list);
		mv.setViewName("displayall");
		
		return mv;
	}
}
