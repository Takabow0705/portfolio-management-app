package commons.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import commons.enums.UserAuthentication;
import lombok.Data;

@Entity
@Data
public class UserMaster {

    /** シリアルバージョンUID*/
    private static long serialVersionUID = 1L;
    /** ユーザID*/
    @Id
    @Column(nullable = false)
    private String userId;
    /** バージョン*/
    @Column(nullable = false)
    private long version;
    /** ユーザ名*/
    @Column(nullable = false, unique = true)
    private String userName;
    /** パスワード*/
    @Column(nullable = false)
    private String password;
    /** ユーザ権限*/
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthentication userAuthentication;
    /** 削除フラグ*/
    @Column(nullable = false)
    private boolean isDeleted;
    /** ロックフラグ*/
    @Column(nullable = false)
    private boolean isLocked;

}
