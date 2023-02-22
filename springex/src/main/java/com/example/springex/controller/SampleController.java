package com.example.springex.controller;

import com.example.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(){
        log.info("hello...");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1...");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "aaa") String name,
                    @RequestParam(name = "age", defaultValue = "16") int age){
        log.info("ex2...");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3...");
        log.info("dueDate : " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("-----");
        model.addAttribute("message", "Hello World");
    }

    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model){
        log.info(todoDTO);
        /* ${dto}같은 이름의 변수로 사용 가능 */
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(){

    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info("p1..." + p1);
        log.info("p2..." + p2);
    }


    @GetMapping("/test01")
    public void test(){
        log.info("test01...");
    }

    @PostMapping("/test02")
    public void test(TodoDTO todoDTO, Model model){
        log.info("test02...");
        model.addAttribute("todoDTO", todoDTO);
    }

}
