package app.commons.entities;

import java.io.Serializable;

import javax.persistence.*;

import app.commons.dto.UserMasterDto;

import app.commons.enums.UserAuthentication;
@Entity
@Table(name = "user_master")
public class UserMaster implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	private UserMaster() {}
	
	/**
	 * 
	 * @param userId
	 * @param version
	 * @param userName
	 * @param password
	 * @param userAuthentication
	 * @param deleted
	 * @param locked
	 */
	public UserMaster(Long userId, long version, String userName, String password,
                      UserAuthentication userAuthentication, boolean deleted, boolean locked) {
		super();
		this.userId = userId;
		this.version = version;
		this.userName = userName;
		this.password = password;
		this.userAuthentication = userAuthentication;
		this.deleted = deleted;
		this.locked = locked;
	}

	/**
	 * <p>
	 * 空のユーザマスタエンティティを返します
	 * </p>
	 */
	public static UserMaster of(UserMasterDto userMasterDto){
		return new UserMaster(
				Long.getLong("-1")
				,0
				,userMasterDto.getUserName()
				,userMasterDto.getPassword()
				,UserAuthentication.convertFrom(userMasterDto.getUserAuthentication())
				,false
				,false
		);
	}
	
    /** ユーザID*/
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;
    /** バージョン*/
    @Column(name="VERSION")
    private long version;
    /** ユーザ名*/
    @Column(name="USER_NAME",unique = true)
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
    private boolean deleted;
    /** ロックフラグ*/    
    @Column(name="IS_LOCKED")
    private boolean locked;
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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
		return deleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.deleted = isDeleted;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean isLocked) {
		this.locked = isLocked;
	}

    @Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", version=" + version + ", userName=" + userName + ", password="
				+ password + ", userAuthentication=" + userAuthentication + ", isDeleted=" + deleted + ", isLocked="
				+ locked + "]";
	}
}
