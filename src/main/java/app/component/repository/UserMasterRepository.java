package app.component.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.commons.entities.user.UserMaster;

/**
 * ユーザ情報の検索、永続化を担うJPA
 */
@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, Long>{
    /**
     * <p>
     *     user_masterテーブルのuserIdは一意に決まるので、ひとつだけが検索で見つかる。
     * </p>
     *
     * @param userId
     * @return UserMaster
     */
    public UserMaster findByUserId(Long userId);

    /**
     * <p>
     *     user_masterテーブルのmailAddressは一意に決まるので、ひとつだけが検索で見つかる。
     * </p>
     *
     * @param mailAddress
     * @return UserAuthInfo
     */
    public UserMaster findByMailAddress(String mailAddress);
}
