package commons.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import commons.enums.UserAuthentication;
@Entity
@Table(name="UserMaster")
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserAuthentication getUserAuthentication() {
		return userAuthentication;
	}
	public void setUserAuthentication(UserAuthentication userAuthentication) {
		this.userAuthentication = userAuthentication;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
