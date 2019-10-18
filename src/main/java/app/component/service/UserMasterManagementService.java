package app.component.service;

import java.util.List;

import app.commons.dto.UserMasterDto;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.commons.entities.UserMaster;
import app.component.repository.UserMasterRepository;

@Service
public class UserMasterManagementService {
	
	@Autowired
	private UserMasterRepository userMasterRepository;
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	
	/**
	 * <p>
	 * ユーザ情報を検索してコレクションとして返却します。
	 * その際、削除フラグ、ロックフラグは無視されます。
	 * </p>
	 * 
	 * @return List<UserMaster> ユーザマスタの情報
	 */
	public List<UserMaster> findAll(){
		return this.userMasterRepository.findAll();
	}

	/**
	 * <p>
	 * ユーザ情報を新規に追加します。
	 * UserMasterエンティティをそのまま永続化するメソッドです。
	 * </p>
	 * @param userMaster
	 */
	public void save(UserMaster userMaster){
		this.userMasterRepository.save(userMaster);
	}

	/**
	 * <p>
	 * 新規ユーザ情報を追加するために必要な処理を行った上で永続化処理を行います。
	 * </p>
	 *
	 * @param userMasterDto
	 */
	public void saveNewUser(UserMasterDto userMasterDto){
		logger.atInfo().log("以下のDTOを受信：%s", userMasterDto.toString());
		UserMaster userMaster = UserMaster.of(userMasterDto);
        logger.atInfo().log("以下のユーザを保存します。：%s", userMaster.toString());
		this.userMasterRepository.save(userMaster);
	}
}

