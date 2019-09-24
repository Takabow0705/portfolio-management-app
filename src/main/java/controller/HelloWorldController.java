package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commons.entities.UserMaster;
import service.UserMasterService;

@Controller
public class HelloWorldController {
	
	@Autowired
	private UserMasterService userMasterService;
	
    @RequestMapping(value = "/index")
    public ModelAndView index(ModelAndView mav){
    	List<UserMaster> users = userMasterService.findAll();
       mav.addObject("users", users);
       return mav;
    }
}
