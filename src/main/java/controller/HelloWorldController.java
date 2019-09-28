package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.google.common.flogger.FluentLogger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import commons.entities.UserMaster;
import service.UserMasterManagementService;

@Controller
@RequestMapping("/")
public class HelloWorldController {
	
	@Autowired
	private UserMasterManagementService userMasterManagementService;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
 
	@GetMapping("index")
    public String index(Model model){
    	List<UserMaster> users = userMasterManagementService.findAll();
		logger.atInfo().log("ユーザリストを取得しました リスト: %s", users);
    	model.addAttribute("users", users);
       return "index";
    }
}
