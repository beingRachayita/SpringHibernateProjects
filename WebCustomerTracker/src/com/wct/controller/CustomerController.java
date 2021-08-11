package com.wct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wct.entity.Customer;
import com.wct.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String customerList(Model theModel) {

		// get customers from dao
		List<Customer> customers = customerService.getCustomers();
		// add customers to Model
		theModel.addAttribute("customers", customers);

		return "list-customers";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer customer = new Customer();
		theModel.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//Save customer using service
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel) {
		//get Customer from service
		Customer theCustomer = customerService.getCustomer(id);
		//Set it in Model
		theModel.addAttribute("customer", theCustomer);
		//send it to form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id, Model theModel) {
		
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
}
