package com.example.sample1.app;

//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.sample1.app.repositories.PersonRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class HelloController {
	
	@Autowired
	PersonRepository repository;
	
	@PostConstruct
	public void init() {
		Person p1 = new Person();
		p1.setName("taro");
		p1.setAge(39);
		p1.setMail("taro@yamada");
		repository.saveAndFlush(p1);
		
		Person p2 = new Person();
		p2.setName("hanako");
		p2.setAge(28);
		p2.setMail("hanako@flower");
		repository.saveAndFlush(p2);
		
		Person p3 = new Person();
		p3.setName("sachiko");
		p3.setAge(65);
		p3.setMail("sachiko@happy");
		repository.saveAndFlush(p3);
	}
	
	@RequestMapping("/")
	public ModelAndView index(
			@ModelAttribute("formModel")Person Person,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("title", "Hello page");
		mav.addObject("msg", "this is JPA sample data");
		Iterable<Person> list = repository.findAll();
		mav.addObject("data", list);
		return mav;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	@Transactional
	public ModelAndView form(
			@ModelAttribute("formModel") @Validated Person person,
			BindingResult resultl,
			ModelAndView mav) {
		ModelAndView res = null;
		System.out.println(resultl.getGlobalErrors());
		if(!resultl.hasErrors()) {
			repository.saveAndFlush(person);
			res = new ModelAndView("redirect:/");
		} else {
			mav.setViewName("index");
			mav.addObject("msg", "sorry, error is occurred...");
			Iterable<Person> list = repository.findAll();
			mav.addObject("data", list);
			res = mav;
		}
		return res;
	}
	
	@RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
	@Transactional
	public ModelAndView edit(@ModelAttribute Person Person,
			@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit person.");
		Optional<Person> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute Person Person,
			ModelAndView mav) {
		repository.saveAndFlush(Person);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title", "delete person.");
		mav.addObject("msg", "Can I delete this record?");
		Optional<Person> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@Transactional
	public ModelAndView delete(@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}

}
