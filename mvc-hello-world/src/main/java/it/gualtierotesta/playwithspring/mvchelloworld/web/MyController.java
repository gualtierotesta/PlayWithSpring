package it.gualtierotesta.playwithspring.mvchelloworld.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MyController {

    @GetMapping("/home")
    public ModelAndView home(final Model model) {
        model.addAttribute("message", "Hello world");
        return new ModelAndView("welcomePage");
    }

    @GetMapping("/home/{param}/detail")
    public ModelAndView detail(
            @PathVariable(name = "param") final String param,
            final Model model) {
        System.out.println("param="+param);
        model.addAttribute("title", "Detail");
        model.addAttribute("p", param);
        return new ModelAndView("detail");
    }


}
