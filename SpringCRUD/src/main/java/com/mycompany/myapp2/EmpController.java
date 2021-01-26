package com.mycompany.myapp2;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpController {
	
	@Autowired
	EmpDAO dao;
	
	@RequestMapping(value = "/empform", method = RequestMethod.GET)
	public String showform(Model model) {
		model.addAttribute("command",new EmpVO());
		return "empform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("emp") EmpVO emp) {
		dao.save(emp);
		return "redirect:/viewemp";
	}	

	@RequestMapping(value = "/viewemp")
	public String viewemp(Model model){
		List<EmpVO> list= dao.getList();
		model.addAttribute("list",list);
		return "viewemp";
	}
	
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable int id, Model model) {
		EmpVO emp= dao.getEmpById(id);
		model.addAttribute("command",emp);
		return "empeditform";
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("emp") EmpVO emp) {
		dao.update(emp);
		return "redirect:/viewemp";
	}
	
	@RequestMapping(value = "/deleteemp/{id}")
	public String delete(@PathVariable int id) {
		dao.delete(id);
		return "redirect:/viewemp";
	}
}
