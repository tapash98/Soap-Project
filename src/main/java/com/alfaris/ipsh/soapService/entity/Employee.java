package com.alfaris.ipsh.soapService.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="employee")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private long employeeId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;

}
