package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll(){
		
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Computer Science"));
		list.add(new Department(2, "Technology"));
		list.add(new Department(3, "Java"));
		list.add(new Department(4, "Programming"));
		list.add(new Department(5, "Support"));
		list.add(new Department(6, "Debugging"));
		list.add(new Department(7, "Analysis"));
		
		return list;
		
		
	}

}
