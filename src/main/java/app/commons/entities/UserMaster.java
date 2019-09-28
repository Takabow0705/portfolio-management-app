package app.commons.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import app.commons.enums.UserAuthentication;
@Entity
@Table(name = "USER_MASTER")
public class UserMaster implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	protected UserMaster() {}
	
	/**
	 * 
	 * @param userId
	 * @param version
	 * @param userName
	 * @param password
	 * @param userAuthentication
	 * @param isDeleted
	 * @param isLocked
	 */
	public UserMaster(String userId, long version, String userName, String password,
			UserAuthentication userAuthentication, boolean isDeleted, boolean isLocked) {
		super();
		this.userId = userId;
		this.version = version;
		this.userName = userName;
		this.password = password;
		this.userAuthentication = userAuthentication;
		this.isDeleted = isDeleted;
		this.isLocked = isLocked;
	}

	
    /** ユーザID*/
    @Id
    @Column(name="USER_ID")
    private String userId;
    /** バージョン*/
    @Column(name="VERSION")
    private long version;
    /** ユーザ名*/
    @Column(name="USER_NAME")
    private String userName;
    /** パスワード*/
    @Column(name="PASSWORD")
    private String password;
    /** ユーザ権限*/
    @Column(name="USER_AUTHENTICATION")
    @Enumerated(EnumType.STRING)
    private UserAuthentication userAuthentication;
    /** 削除フラグ*/
    @Column(name="IS_DELETED")
    private boolean isDeleted;
    /** ロックフラグ*/    
    @Column(name="IS_LOCKED")
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

    @Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", version=" + version + ", userName=" + userName + ", password="
				+ password + ", userAuthentication=" + userAuthentication + ", isDeleted=" + isDeleted + ", isLocked="
				+ isLocked + "]";
	}
}
