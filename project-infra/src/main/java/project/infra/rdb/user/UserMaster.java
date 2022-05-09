package project.infra.rdb.user;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_master")
public class UserMaster implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public UserMaster() {}
	
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
                      UserAuthentication userAuthentication, boolean deleted, boolean locked, String mailAddress) {
		super();
		this.userId = userId;
		this.version = version;
		this.userName = userName;
		this.password = password;
		this.userAuthentication = userAuthentication;
		this.deleted = deleted;
		this.locked = locked;
		this.mailAddress = mailAddress;
	}
	
    /** ユーザID*/
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    /** バージョン*/
    @Column(name = "VERSION")
    private long version;
    /** ユーザ名*/
    @Column(name = "USER_NAME",unique = true)
    private String userName;
    /** パスワード*/
    @Column(name = "ENCODED_PASSWORD")
    private String password;
    /** ユーザ権限*/
    @Column(name = "USER_AUTHENTICATION")
    @Enumerated(EnumType.STRING)
    private UserAuthentication userAuthentication;
    /** 削除フラグ*/
    @Column(name = "IS_DELETED")
    private boolean deleted;
    /** ロックフラグ*/    
    @Column(name = "IS_LOCKED")
    private boolean locked;
    /** メールアドレス*/
    @Column(name = "MAIL_ADDRESS")
    private String mailAddress;

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserMaster that = (UserMaster) o;
		return version == that.version &&
				deleted == that.deleted &&
				locked == that.locked &&
				Objects.equals(userId, that.userId) &&
				Objects.equals(userName, that.userName) &&
				Objects.equals(password, that.password) &&
				userAuthentication == that.userAuthentication &&
				Objects.equals(mailAddress, that.mailAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, version, userName, password, userAuthentication, deleted, locked, mailAddress);
	}
}
