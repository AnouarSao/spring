package com.example.demoThymeleaf.controller;

import com.example.demoThymeleaf.business.DirectoryService;
import com.example.demoThymeleaf.business.HelloService;
import com.example.demoThymeleaf.business.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    DirectoryService directoryService;

    @GetMapping("/hello")
    public String hello(Model model){
        String message = helloService.sayTime();
        model.addAttribute("monmessage", message);
        return "hello"; // ou return hello.html;
    }

    @GetMapping("/people")
    public String people(Model model){

        //model.addAttribute("people", directoryService.getPeople());
        //model.addAttribute("people", new ArrayList<Person>());

//        ArrayList<Person> people = new ArrayList<Person>();
//        people.add(new Person("Alain", "Delon"));
//        model.addAttribute("people", people);

        model.addAttribute("people", directoryService.getPeople());
        return "people";
    }

    @PostMapping("/people")
    public String addPerson(Person newPerson, Model model) {
        directoryService.addPerson(newPerson);
        model.addAttribute("people", directoryService.getPeople());
        return "people.html";
    }
}
