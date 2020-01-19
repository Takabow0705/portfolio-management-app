package app.commons.dto;

import java.util.Objects;

/**
 * <p>
 *      新規ユーザ作成時に使う入力フォーマット
 * </p>
 */
public class UserMasterDto {

    /** 新規登録ユーザ名*/
    private String userName;
    /** パスワード*/
    private String password;
    /** ユーザ権限*/
    private String userAuthentication;
    /** メールアドレス*/
    private String mailAddress;

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

    public String getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(String userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "UserMasterDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userAuthentication='" + userAuthentication + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMasterDto that = (UserMasterDto) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userAuthentication, that.userAuthentication) &&
                Objects.equals(mailAddress, that.mailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, userAuthentication, mailAddress);
    }
}

