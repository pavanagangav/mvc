package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.mvc;
import com.example.demo.repository.mvcrepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class mvccontroller {
	@Autowired
	mvcrepository repo;
	@RequestMapping("/welcome")
	public String home() {
		return "welcome.html";
	}
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}
	@PostMapping("/insert")
	public String insertion(@ModelAttribute mvc m,HttpSession session) {
		 repo.save(m);
		 session.setAttribute("message", "successfully inserted");
		 return "redirect:/loadform";
	}
	@DeleteMapping("/delete/{id}")
	public String deletebyid(@PathVariable(value = "id") int id,HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "successfully deleted");
		return "redirect:/";
	}
	@GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value = "id")int id,HttpSession session,Model mo) {
		Optional<mvc> m= repo.findById(id);
		mvc mv=m.get();
		mo.addAttribute("product","mv");
		return "edit";
		
	}
	@GetMapping("/getall")
	public String findall(Model m ) {
		List<mvc>li=(List<mvc>) repo.findAll();
		m.addAttribute("all_products",li);
		return "home";
	}
	@PutMapping("/update")
	public String update(@ModelAttribute mvc mv,HttpSession session) {
		repo.save(mv);
		session.setAttribute("message", "successfully updated");
		return "redirect:/";
		
	}

}
