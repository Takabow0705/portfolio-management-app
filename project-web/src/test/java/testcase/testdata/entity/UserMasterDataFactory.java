package testcase.testdata.entity;

import project.infra.rdb.user.UserAuthentication;
import project.infra.rdb.user.UserMaster;

public class UserMasterDataFactory {

    /**
     * 有効なユーザデータを返す。
     *
     * @return
     */
    public static UserMaster createActiveUser(){
        UserMaster data = new UserMaster();
        data.setUserId(Long.valueOf(2));
        data.setVersion(Long.valueOf(0));
        data.setUserName("root_user1");
        data.setPassword("$2a$10$BQ1OVcmKcoc9VSiqY9A56.m95od6AB.C25NBsjEGlEro/oyxq5nNG");
        data.setMailAddress("root-user01@example.com");
        data.setUserAuthentication(UserAuthentication.ADMIN);
        data.setDeleted(false);
        data.setLocked(false);
        return data;
    }

    /**
     * 削除済みなユーザデータを返す。
     *
     * @return
     */
    public static UserMaster createDeletedUser(){
        UserMaster data = new UserMaster();
        data.setUserId(Long.valueOf(2));
        data.setVersion(Long.valueOf(0));
        data.setUserName("root_user1");
        data.setPassword("$2a$10$BQ1OVcmKcoc9VSiqY9A56.m95od6AB.C25NBsjEGlEro/oyxq5nNG");
        data.setMailAddress("root-user01@example.com");
        data.setUserAuthentication(UserAuthentication.ADMIN);
        data.setDeleted(true);
        data.setLocked(false);
        return data;
    }
}
