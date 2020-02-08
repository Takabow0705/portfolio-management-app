package app.component.service.userMaster;

import app.commons.dto.UserMasterDto;
import app.commons.entities.user.UserMaster;

import java.util.List;

public interface UserMasterManagementService {
    abstract List<UserMaster> findAll();

    abstract void saveNewUser(UserMasterDto userMasterDto);

    abstract UserMaster findByUserId(Long id);

    abstract void save(UserMaster userMaster);

    abstract void delete(Long id);

    abstract List<UserMaster> findAllActiveUser();
}
