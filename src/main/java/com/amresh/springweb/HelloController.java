package com.amresh.springweb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HelloController {
    @GetMapping
    public String home(){
        return "index.jsp";
    }

    @GetMapping("/sum1")
    public String sum(HttpServletRequest req, HttpSession session){
    int num1 = Integer.parseInt(req.getParameter("num1"));
    int num2 = Integer.parseInt(req.getParameter("num2"));
    int result = num1 + num2;
    session.setAttribute("result",result);
    return "result.jsp";
    }

    @GetMapping("/sum2")
    // name like num1 must match with data send from user , for same name RequestParam() is Optional
    public String sum2(int num1,int num2, HttpSession session){
            int result = num1 + num2;
        session.setAttribute("result",result);
        return "result.jsp";
    }


    @GetMapping("/sum3")
    public String sum3(@RequestParam("num1") int first ,@RequestParam("num2") int second,HttpSession session){
        int result = first + second;
        session.setAttribute("result",result);
        return "result.jsp";
    }
}
