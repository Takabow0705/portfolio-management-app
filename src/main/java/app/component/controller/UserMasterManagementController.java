package app.component.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.google.common.flogger.FluentLogger;

import app.commons.entities.UserMaster;
import app.component.service.UserMasterManagementService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class UserMasterManagementController {
	
	@Autowired
	private UserMasterManagementService userMasterManagementService;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();
 
	@GetMapping("user_master")
    public String index(Model model){
    	List<UserMaster> users = userMasterManagementService.findAll();
		logger.atInfo().log("ユーザリストを取得しました リスト: %s", users);
    	model.addAttribute("users", users);
       return "management/user_master";
    }
}
