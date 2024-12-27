package com.alfaris.ipsh.soapService.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alfaris.ipsh.soapService.AddEmployeeRequest;
import com.alfaris.ipsh.soapService.AddEmployeeResponse;
import com.alfaris.ipsh.soapService.DeleteEmployeeResponse;
import com.alfaris.ipsh.soapService.EmployeeInfo;
import com.alfaris.ipsh.soapService.GetAllEmployeeRequest;
import com.alfaris.ipsh.soapService.GetAllEmployeeResponse;
import com.alfaris.ipsh.soapService.GetEmployeeResponse;
import com.alfaris.ipsh.soapService.SearchEmployeeRequest;
import com.alfaris.ipsh.soapService.SearchEmployeeResponse;
import com.alfaris.ipsh.soapService.SearchEmployeeResponse.AaData;
import com.alfaris.ipsh.soapService.ServiceStatus;
import com.alfaris.ipsh.soapService.UpdateEmployeeRequest;
import com.alfaris.ipsh.soapService.UpdateEmployeeResponse;
import com.alfaris.ipsh.soapService.entity.Employee;
import com.alfaris.ipsh.soapService.exception.RecordCreateException;
import com.alfaris.ipsh.soapService.exception.RecordNotFoundException;
import com.alfaris.ipsh.soapService.repository.EmployeeRepository;
import com.alfaris.ipsh.soapService.repository.specification.EmployeeSpecification;
import com.alfaris.ipsh.soapService.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public GetEmployeeResponse getEmployeeById(long employeeId) throws RecordNotFoundException {
		Employee entity = employeeRepository.findById(employeeId).orElseThrow(() ->new RecordNotFoundException("Record Not found with id "+employeeId));
		try {
			GetEmployeeResponse response = new GetEmployeeResponse();
			EmployeeInfo employeeInfo = new EmployeeInfo();
			BeanUtils.copyProperties(entity, employeeInfo);
			response.setEmployeeInfo(employeeInfo);
			return response;
		} catch (Exception e) {
			log.error("Exception at getEmployeeByid", e.getMessage(), e);
		}
		return null;

	}

	@Override
	public AddEmployeeResponse AddEmployee(AddEmployeeRequest request) throws RecordCreateException {
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();
		Optional<Employee> optional = employeeRepository.findById(request.getEmployeeInfo().getEmployeeId());
		if (optional.isPresent()) {
			throw new RecordCreateException("Employee Id Already Exist");
		}
		try {
			Employee employee = new Employee();
			BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
			employeeRepository.save(employee);
			serviceStatus.setStatus(Constants.SUCCESS);
			serviceStatus.setMessage("Content Added Successfully");
			response.setServiceStatus(serviceStatus);
			return response;
		} catch (Exception e) {
			log.error("exception", e.getMessage());
		}
		return null;

	}

	@Override
	public UpdateEmployeeResponse updateEmployee(UpdateEmployeeRequest request) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		employeeRepository.save(employee);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus(Constants.SUCCESS);
		serviceStatus.setMessage("Content Updated Successfully");
		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		return response;

	}

	@Override
	public DeleteEmployeeResponse deleteEmployee(long employeeId) {
		employeeRepository.deleteById(employeeId);
		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus(Constants.SUCCESS);
		serviceStatus.setMessage("Content Deleted Successfully");
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		return response;

	}

	@Override
	public GetAllEmployeeResponse getAllEmployees(GetAllEmployeeRequest request) {
		GetAllEmployeeResponse response = new GetAllEmployeeResponse();
		List<Employee> employees = employeeRepository.findAll();
		for (Employee employee : employees) {
			GetAllEmployeeResponse.EmployeeInfo employeeInfo = new GetAllEmployeeResponse.EmployeeInfo();
			BeanUtils.copyProperties(employee, employeeInfo);
			response.getEmployeeInfo().add(employeeInfo);
		}
		return response;
	}

	@Override
	public SearchEmployeeResponse searchEmployee(SearchEmployeeRequest request) {
		SearchEmployeeResponse response = new SearchEmployeeResponse();
		try {

			Pageable pageable = PageRequest.of(request.getIDisplayStart() / request.getIDisplayLength(), request.getIDisplayLength());
			Page<Employee> headerList = employeeRepository.findAll(EmployeeSpecification.getSearchPage(request.getSearchParam()),
					pageable);
			AaData data = new AaData();
			for (Employee entity : headerList) {

				SearchEmployeeResponse.AaData.EmployeeInfo list= new SearchEmployeeResponse.AaData.EmployeeInfo();
				BeanUtils.copyProperties(entity, list);

				data.getEmployeeInfo().add(list);
			}
			response.setAaData(data);
			response.setITotalDisplayRecord(headerList.getTotalElements());
			response.setITotalRecord(headerList.getTotalElements());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			response.setAaData(new AaData());
			response.setITotalDisplayRecord(0);
			response.setITotalRecord(0);
		}

		return response;
	}

}
