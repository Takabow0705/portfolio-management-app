package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.entities.UserMaster;
import repository.UserMasterRepository;

@Service
public class UserMasterManagementService {
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
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
}
