package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hello")
    //메소드 작성
    public String niceMeetYou(Model model){
        //모델객체 가져오는 메소드
        model.addAttribute("username", "윤건우");
        return "greetings";
    }

    //요청접수 -bye
    @GetMapping("/bye")
    public String seeYouNext(Model model){
       model.addAttribute("nickname", "홍길동");
       return "goodbye";
    }
}
