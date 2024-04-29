package org.boot.blog.controller;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.boot.blog.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.boot.blog.model.MemberModel;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Resource(name="memberService")
    private MemberService memberService;


    @GetMapping(value= "/listMembers.do")
    public void listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("1 @@@@@@@@@@");

        List<MemberModel> membersList = memberService.listMembers();

        System.out.println("membersList : " + membersList);


        // membersList를 JSON으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(membersList);

        // JSON 출력
        System.out.println(json);

        // ModelAndView mav = new ModelAndView("/member/listMembers");

        // mav.addObject("membersList", membersList);
        // return mav;
    }

    @PostMapping(value="/addMember.do")
    public ModelAndView addMember(@ModelAttribute("member") MemberModel member, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        int result = 0;
        result = memberService.addMember(member);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }


    @GetMapping(value="/removeMember.do")
    public ModelAndView removeMember(@RequestParam("id") String id,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");
        memberService.removeMember(id);
        ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
        return mav;
    }


    @PostMapping(value = "/login.do")
    public ModelAndView login(@ModelAttribute("member") MemberModel member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {


        MemberModel memberVO = new MemberModel();

        ModelAndView mav = new ModelAndView();
        memberVO = memberService.login(member);
        if(memberVO != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", memberVO);
            session.setAttribute("isLogOn", true);

            String action = (String)session.getAttribute("action");
            session.removeAttribute("action");
            if(action!= null) {
                mav.setViewName("redirect:"+action);
            }else {
                mav.setViewName("redirect:/member/listMembers.do");
            }

        }else {
            rAttr.addAttribute("result","loginFailed");
            mav.setViewName("redirect:/member/loginForm.do");
        }


        return mav;
    }


    @GetMapping(value = "/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("member");
        session.setAttribute("isLogOn",false);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/member/listMembers.do");
        return mav;
    }


    @GetMapping(value = "/*Form.do")
    private ModelAndView form(@RequestParam(value= "result", required=false) String result,
                              @RequestParam(value= "action", required=false) String action,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String viewName = (String)request.getAttribute("viewName");
        HttpSession session = request.getSession();
        session.setAttribute("action", action);

        ModelAndView mav = new ModelAndView();
        mav.addObject("result",result);
        mav.setViewName(viewName);
        return mav;
    }

    @GetMapping(value = { "/","/main.do"})
    private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        String viewName = (String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        return mav;
    }
}