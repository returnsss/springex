package com.example.springex.controller;

import com.example.springex.dto.PageRequestDTO;
import com.example.springex.dto.TodoDTO;
import com.example.springex.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(Model model){
        log.info("todo list...");
        //model.addAttribute("dtoList", todoService.getAll());
    }

    //@RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET(){
        log.info("todo GET register...");
    }

    @PostMapping("/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST todo register...");

        if(bindingResult.hasErrors()) {
            log.info("has error....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }


    // 글 상세보기에서 list버튼 눌렀을때 해당 번호의 페이지로 이동하기위해 pageRequestDTO 추가
    @GetMapping({"/read", "/modify"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("---------remove----------");
        log.info("tno : " + tno);
        log.info("tno : " + pageRequestDTO);

        todoService.remove(tno);

        //redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        //redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }

    @PostMapping("modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("---------------modify--------------");
        if(bindingResult.hasErrors()){
            log.info("has error...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);

        //redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        //redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        redirectAttributes.addAttribute("tno", todoDTO.getTno());
        return "redirect:/todo/read";
    }


    //list()는 @Valid를 이용해서 잘못된 파라미터 값들이 들어오는 경우 page는 1, size는 10으로 고정된 값을 처리하도록 구성
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }

}
