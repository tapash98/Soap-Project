package com.alfaris.ipsh.soapService.repository.specification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.alfaris.ipsh.soapService.entity.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeSpecification {
	
	public static Specification<Employee> getSearchPage(String searchParam) {

		return new Specification<Employee>() { 

			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = null;
				JSONParser parser = new JSONParser(); 
				String [] date =null;
				String fromDate ="";
				String toDate ="";

				try {
					List<String> statusList = new ArrayList<>();
					
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

					if (StringUtils.hasLength(searchParam)) {
						JSONObject searchObject = (JSONObject) parser.parse(searchParam);
					String	employeeId = (String) searchObject.get("employeeId");
					String	name = (String) searchObject.get("name");
					String	department = (String) searchObject.get("department");
					String	phone = (String) searchObject.get("phone");
					String valueDate = (String) searchObject.get("valueDate");//				
					String	address = (String) searchObject.get("address"); 
					
					if(StringUtils.hasLength(valueDate)) {
						date = valueDate.split("-");
						fromDate = date[0].trim();
						toDate = date[1].trim();
					}

						if (StringUtils.hasLength(employeeId)) { // this is for composite key we
							Predicate bookIdPredicate = criteriaBuilder.equal(root.get("employeeId"),
									employeeId);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, bookIdPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(bookIdPredicate);
							}
						}
						if (StringUtils.hasLength(name)) {
							Predicate carNumberPredicate = criteriaBuilder.like(root.get("name"),
									name + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, carNumberPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(carNumberPredicate);
							}
						}
						if (StringUtils.hasLength(department)) {
							Predicate namePredicate = criteriaBuilder.like(root.get("department"),
									"%" + department + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(namePredicate);
							}
						}
						if (StringUtils.hasLength(phone)) {
							Predicate namePredicate = criteriaBuilder.equal(root.get("phone"), phone);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(namePredicate);
							}
						}
						if (StringUtils.hasLength(address)) {
							Predicate carTypePredicate = criteriaBuilder.equal(root.get("address"), address);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, carTypePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(carTypePredicate);
							}
						}
						if (StringUtils.hasLength(fromDate) && StringUtils.hasLength(toDate)) {
							
							Date start = format.parse(fromDate);
							Date end = format.parse(toDate);
							
//					

							Predicate datePredicate = criteriaBuilder.between(root.get("arrivingDate"), start, end);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, datePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(datePredicate);
							}
						}
//						query.orderBy(criteriaBuilder.desc(root.get("createdTime")));

					}
				} catch (Exception e) {
					log.error("Error" + e.getMessage());
				}

				return finalPredicate;
			}
		};
	}

}
