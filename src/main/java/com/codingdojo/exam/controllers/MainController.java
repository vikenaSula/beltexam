package com.codingdojo.exam.controllers;

import com.codingdojo.exam.models.Idea;
import com.codingdojo.exam.models.LoginUser;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.services.IdeaService;
import com.codingdojo.exam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    IdeaService ideaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
                           HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        User user = userService.register(newUser, result);
        if (user == null) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
        return "redirect:/ideas";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
                        HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        User user = userService.login(newLogin, result);
        if (user == null) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("userId", user.getId());
        return "redirect:/ideas";
    }

    @GetMapping("/ideas")
    public String ideasPage(HttpSession session,Model model){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        User logedUser = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("user",logedUser);

        if(session.getAttribute("orderAscending")!=null)
              model.addAttribute("allIdeas",ideaService.getAllAscendingOrder());
        else if(session.getAttribute("orderDescending")!=null )
            model.addAttribute("allIdeas",ideaService.getAllDescendingOrder());
        //if no sorting is selected, by default is descending
        else model.addAttribute("allIdeas",ideaService.getAllDescendingOrder());
        return "ideas.jsp";
    }

    @GetMapping("orderDescending")
    public String showInDescedingOrder(HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        session.setAttribute("orderDescending","true");
        session.removeAttribute("orderAscending");
        return "redirect:/ideas";
    }

     @GetMapping("orderAscending")
        public String showInAscedingOrder(HttpSession session){
         if(session.getAttribute("userId") == null)
             return "redirect:/";
         session.setAttribute("orderAscending","false");
         session.removeAttribute("orderDescending");
         return "redirect:/ideas";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("userId",null);
        return "redirect:/";
    }

     @GetMapping("/ideas/new")
     public String newIdeaPage(@ModelAttribute("idea") Idea idea,
                               HttpSession session,Model model){
     if(session.getAttribute("userId") == null)
             return "redirect:/";
        model.addAttribute("logedUser",(Long)session.getAttribute("userId"));
        return "new_idea.jsp";
     }

    @PostMapping("ideas/new")
    public String createShow(@Valid @ModelAttribute("idea") Idea idea, BindingResult result,
                             HttpSession session)
    {
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        if(result.hasErrors())
            return "new_idea.jsp";

        ideaService.create(idea);
        return "redirect:/ideas";
    }

    @GetMapping("ideas/{id}")
    public String ideaDetails(@PathVariable("id") Long id, Model model, HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        Idea idea = ideaService.findIdea(id);
        model.addAttribute("idea",idea);
        User logedUser = userService.findUser((Long)session.getAttribute("userId"));
        model.addAttribute("user",logedUser);
        model.addAttribute("usersThatLiked",userService.getAllByIdea(idea));
        return "idea_details.jsp";
    }

    @GetMapping("/ideas/{id}/edit")
    public String editIdeaPage(@PathVariable("id")Long id,Model model,HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        Idea idea = ideaService.findIdea(id);
        User logedUser = userService.findUser((Long)session.getAttribute("userId"));
        if(logedUser.getId() != idea.getCreator().getId())
            return "redirect:/ideas";
        model.addAttribute("idea",idea);
        model.addAttribute("user",logedUser);
        return "edit.jsp";
    }

    @PutMapping("ideas/{id}/edit")
    public String editIdea(@Valid @ModelAttribute("idea")Idea idea,
                           BindingResult result,
                           Model model,
                           @PathVariable("id") Long id,
                           HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        if(result.hasErrors()){
            User logedUser = userService.findUser((Long)session.getAttribute("userId"));
            //If the new content has errors, display the existing name of the content
            String ideaContent = ideaService.findIdea(id).getContent();
            idea.setContent(ideaContent);
            model.addAttribute("idea",idea);
            model.addAttribute("user",logedUser);
            return "edit.jsp";
        }
       //For update are needed dhe liking users of preUpdated idea
        ideaService.update(idea,ideaService.findIdea(id));
        return "redirect:/ideas";
    }

    @DeleteMapping("ideas/delete/{id}")
    public String deleteIdea(@PathVariable("id") Long id,HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        ideaService.delete(id);
        return "redirect:/ideas";
    }

    @GetMapping("/addLike/{id}")
    public String addLikeToIdea(@PathVariable("id") Long id, HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        Idea idea = ideaService.findIdea(id);
        User user = userService.findUser((Long)session.getAttribute("userId"));
        ideaService.addLike(idea,user);
        return "redirect:/ideas";
    }
    @GetMapping("/removeLike/{id}")
    public String removeLikeFromIdea(@PathVariable("id") Long id, HttpSession session){
        if(session.getAttribute("userId") == null)
            return "redirect:/";
        Idea idea = ideaService.findIdea(id);
        User user = userService.findUser((Long)session.getAttribute("userId"));
        ideaService.removeLike(idea,user);
        return "redirect:/ideas";
    }
}