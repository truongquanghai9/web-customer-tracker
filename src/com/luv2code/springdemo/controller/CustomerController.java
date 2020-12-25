package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.entity.CustomerDetail;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Search Name
	private String searchName;
	
	// need to inject the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// add an Initbinder ... to conver trim input strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {	
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> customers = customerService.getCustomers();
		
		// add the customers to the model
		theModel.addAttribute("customers", customers);
		return "list-customer";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer newCustomer = new Customer();
		CustomerDetail customerDetail = new CustomerDetail();
		newCustomer.setCustomerDetail(customerDetail);
		
		theModel.addAttribute("customers",newCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customers") Customer newCustomer, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
		// save the customer with our service
		customerService.saveCustomer(newCustomer);
		return "redirect:/customer/list";
		}
	}
	
	// Main Page
	@GetMapping("/view")
	public String view(@RequestParam("customerId") int id, Model theModel) {
		Customer theCustomer = customerService.getCustomer(id);
		theModel.addAttribute("customers", theCustomer);
		return "view-customer";
	}
	
	@GetMapping("/showFormForUpdate") 
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customers", theCustomer);
		
		// send over to our form
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		//delete the Customer
		customerService.deleteCustomer(theId);	
		return "redirect:/customer/list";
	}
	

	@GetMapping("/search")
	public String searchCustomer(@RequestParam("theSearchName") String searchName, Model theModel) {
		this.searchName = searchName;
		List<Customer> customers = customerService.searchCustomer(searchName);
		theModel.addAttribute("customers",customers);
		return "list-customer";
	}
	
}
