package com.alfaris.ipsh.soapService.services;

import java.util.List;

import com.alfaris.ipsh.soapService.AddEmployeeRequest;
import com.alfaris.ipsh.soapService.AddEmployeeResponse;
import com.alfaris.ipsh.soapService.DeleteEmployeeResponse;
import com.alfaris.ipsh.soapService.GetAllEmployeeRequest;
import com.alfaris.ipsh.soapService.GetAllEmployeeResponse;
import com.alfaris.ipsh.soapService.GetEmployeeResponse;
import com.alfaris.ipsh.soapService.SearchEmployeeRequest;
import com.alfaris.ipsh.soapService.SearchEmployeeResponse;
import com.alfaris.ipsh.soapService.UpdateEmployeeRequest;
import com.alfaris.ipsh.soapService.UpdateEmployeeResponse;
import com.alfaris.ipsh.soapService.entity.Employee;
import com.alfaris.ipsh.soapService.exception.RecordCreateException;
import com.alfaris.ipsh.soapService.exception.RecordNotFoundException;

public interface EmployeeService {

	AddEmployeeResponse AddEmployee(AddEmployeeRequest request) throws RecordCreateException;
	 
	GetEmployeeResponse getEmployeeById(long employeeId) throws RecordNotFoundException;
	 
	UpdateEmployeeResponse updateEmployee(UpdateEmployeeRequest request);
  
	DeleteEmployeeResponse deleteEmployee(long employeeId);

	GetAllEmployeeResponse  getAllEmployees(GetAllEmployeeRequest request);

	SearchEmployeeResponse searchEmployee(SearchEmployeeRequest request);
}