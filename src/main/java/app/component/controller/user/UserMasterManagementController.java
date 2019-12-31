package app.component.controller.user;

import java.util.List;

import app.commons.dto.UserMasterDto;
import app.commons.enums.UserAuthentication;
import app.component.service.userMaster.UserMasterManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.google.common.flogger.FluentLogger;

import app.commons.entities.UserMaster;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/management/users")
public class UserMasterManagementController {

    /** ユーザ情報を管理する*/
	@Qualifier("userMasterServiceImpl")
	@Autowired
	private UserMasterManagementService userMasterManagementService;
	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * <p>
     *     ユーザ情報画面で最初に出てくる画面
     * </p>
     *
     * @param model
     * @return ユーザ情報画面
     */
	@GetMapping
    public String index(Model model){
    	List<UserMaster> users = userMasterManagementService.findAll();
		logger.atInfo().log("ユーザリストを取得しました リスト: %s", users);
    	model.addAttribute("users", users);
        return "management/users/index";
    }

    /**
     * <p>
     *     ユーザ登録画面を返す。
     *     Dtoが入力された値を受け取る。
     * </p>
     * @param model
     * @return ユーザ新規登録画面
     */
	@GetMapping("create")
	public String create(Model model) {
		logger.atInfo().log("新規作成画面へ遷移");
        model.addAttribute("userMasterForm", new UserMasterDto());
        model.addAttribute("auth", UserAuthentication.values());
	    return "management/users/create";
	}

    /**
     * <p>
     *     入力情報を永続化する。
     *     その後、トップページにリダイレクトする。
     * </p>
     * @param userMasterDto
     * @return ユーザ情報画面
     */
    @PostMapping
    public String saveUser(@ModelAttribute UserMasterDto userMasterDto){
	    logger.atInfo().log("新規ユーザの保存開始");
		userMasterManagementService.saveNewUser(userMasterDto);
		return "redirect:/management/users/";
    }

    @GetMapping("{id}/update")
	public String update(@PathVariable Long id, Model model){
		UserMaster userMaster = this.userMasterManagementService.findByUserId(id);
		logger.atInfo().log("ユーザ詳細情報画面に遷移");
		model.addAttribute("userMaster", userMaster);
		model.addAttribute("auth", UserAuthentication.values());
    	return "management/users/update";
	}
	/**
	 *<p>
	 *     ユーザ情報の更新を行います。
	 *     更新後はトップページにリダイレクトする。
	 *</p>
	 * @param userMaster
	 * @return
	 */
	@PutMapping(path = "{id}")
	public String updateUserMaster(@ModelAttribute UserMaster userMaster){
    	logger.atInfo().log("ユーザ情報の更新を開始");
    	this.userMasterManagementService.save(userMaster);
		return "redirect:/management/users/";
	}
	
	@DeleteMapping(path = "{id}")
	public String delete(@PathVariable Long id){
		this.userMasterManagementService.delete(id);
		logger.atInfo().log("削除完了");
		return "redirect:/management/users/";
	}

}
