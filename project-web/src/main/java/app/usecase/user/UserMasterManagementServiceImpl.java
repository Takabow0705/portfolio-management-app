package app.usecase.user;

import app.domain.user.UserMasterDto;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.infra.rdb.user.UserMaster;
import project.infra.rdb.user.UserMasterRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("userMasterServiceImpl")
public class UserMasterManagementServiceImpl implements UserMasterManagementService{

	private final UserMasterRepository userMasterRepository;

	private final PasswordEncoder passwordEncoder;

	public UserMasterManagementServiceImpl(UserMasterRepository userMasterRepository, PasswordEncoder passwordEncoder) {
		this.userMasterRepository = userMasterRepository;
		this.passwordEncoder = passwordEncoder;
	}

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
		List<UserMaster> result = this.userMasterRepository.findAll();
		return result == null ? Collections.emptyList() : result;
	}

	public UserMaster findByUserId(Long userId){
		return this.userMasterRepository.findByUserId(userId);
	}
	/**
	 * <p>
	 *     ユーザ情報の中で削除フラグの無いものだけを表示します。
	 * </p>
	 * @return
	 */
	public List<UserMaster> findAllActiveUser(){
		return this.findAll().stream().filter(u -> (!u.isDeleted())).collect(Collectors.toList());
	}
	/**
	 * <p>
	 * ユーザ情報を新規に追加します。
	 * UserMasterエンティティをそのまま永続化するメソッドです。
	 * </p>
	 * @param userMaster
	 */
	public void save(UserMaster userMaster){
		userMaster.setPassword(passwordEncoder.encode(userMaster.getPassword()));
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
		userMasterDto.setPassword(passwordEncoder.encode(userMasterDto.getPassword()));
		logger.atInfo().log("以下のDTOを受信：%s", userMasterDto.toString());
		UserMaster userMaster = UserMasterDto.createNewUserMaster(userMasterDto);
        logger.atInfo().log("以下のユーザを保存します。：%s", userMaster.toString());
		this.userMasterRepository.save(userMaster);
	}

	/**
	 * <p>
	 *     ユーザ情報の削除を行う。
	 * </p>
	 * @param id
	 */
	public void delete(Long id){
		UserMaster userMaster = this.userMasterRepository.findByUserId(id);
		logger.atInfo().log("以下のユーザを削除します：%s", userMaster);
		this.userMasterRepository.delete(userMaster);
	}
}


