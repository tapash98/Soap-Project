package com.alfaris.ipsh.soapService.endpoint;




import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.alfaris.ipsh.soapService.AddEmployeeRequest;
import com.alfaris.ipsh.soapService.AddEmployeeResponse;
import com.alfaris.ipsh.soapService.DeleteEmployeeRequest;
import com.alfaris.ipsh.soapService.DeleteEmployeeResponse;
import com.alfaris.ipsh.soapService.GetAllEmployeeRequest;
import com.alfaris.ipsh.soapService.GetAllEmployeeResponse;
import com.alfaris.ipsh.soapService.GetEmployeeByIdRequest;
import com.alfaris.ipsh.soapService.GetEmployeeResponse;
import com.alfaris.ipsh.soapService.SearchEmployeeRequest;
import com.alfaris.ipsh.soapService.SearchEmployeeResponse;
import com.alfaris.ipsh.soapService.UpdateEmployeeRequest;
import com.alfaris.ipsh.soapService.UpdateEmployeeResponse;
import com.alfaris.ipsh.soapService.exception.RecordCreateException;
import com.alfaris.ipsh.soapService.exception.RecordNotFoundException;
import com.alfaris.ipsh.soapService.services.EmployeeService;

import lombok.AllArgsConstructor;

@Endpoint
@AllArgsConstructor
public class EmployeeEndPoint {

	private static final String NAMESPACE_URI = "http://com.alfaris.ipsh.soapService.allapis";

	private final EmployeeService employeeService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) throws RecordCreateException{
		return employeeService.AddEmployee(request);

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request)throws RecordNotFoundException {
		return employeeService.getEmployeeById(request.getEmployeeId());

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		return employeeService.updateEmployee(request);

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		return employeeService.deleteEmployee(request.getEmployeeId());

	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEmployeeRequest")
	@ResponsePayload
	public GetAllEmployeeResponse getAllEmployees(@RequestPayload GetAllEmployeeRequest request) {
	    		return employeeService.getAllEmployees(request); 
  
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchEmployeeRequest")
	@ResponsePayload
	public SearchEmployeeResponse searchEmployee(@RequestPayload SearchEmployeeRequest request) {
		System.out.println("tapas");
		return employeeService.searchEmployee(request);
		
	}
	
	

}
