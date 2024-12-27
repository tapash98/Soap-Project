package com.alfaris.ipsh.soapService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.alfaris.ipsh.soapService.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> ,JpaSpecificationExecutor<Employee>{
	
	Employee findByEmployeeId(long employeeId);

}
