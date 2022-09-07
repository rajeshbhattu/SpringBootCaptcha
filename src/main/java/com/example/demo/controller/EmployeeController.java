package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.CaptchUtil;
import com.example.demo.model.Employee;
import com.example.demo.service.IEmployeeServiceImpl;

import cn.apiclub.captcha.Captcha;

@Controller
@RequestMapping("/employee")
//hello iam kumar
//hello Mr Heart Miss ayye
public class EmployeeController {
	@Autowired
	private IEmployeeServiceImpl service;

	private void setupCaptcha(Employee e) {
		Captcha captcha=CaptchUtil.createdCaptcha(250, 80);
	    e.setHidden(captcha.getAnswer());
	    e.setCaptcha("");//user entered value
	    e.setImage(CaptchUtil.encodeBase64(captcha));
	}
	
	//1.show reg page
	@GetMapping("/register")
	public String showReg(Model model){
		Employee e = new Employee();
		setupCaptcha(e);
		model.addAttribute("employee",e);
		return "EmpReg";
	}
	
	
	
	
	//2.save emp
	@PostMapping("/save")
	public String save(@ModelAttribute Employee employee,Model model) {
	    if(employee.getCaptcha().equals(employee.getHidden())) {
		//save
		service.createEmployee(employee);
		model.addAttribute("message","Employee Added");
		return "redirect:all";
	    }
	    else {
	    	model.addAttribute("message","Invalid Captcha");
	    	setupCaptcha(employee);
	    	model.addAttribute("employee",employee);
	    }
		//Employee e = new Employee();
		
		//Employee e = new Employee();
		
		return "EmpReg";
	}
	
	
	//3.display all employess
	@GetMapping("/all")
	public String showAll(Model model) {
		List<Employee> list = service.getAlEmployees();
		model.addAttribute("list",list);
		
		return "EmpData";
		
	}

}
