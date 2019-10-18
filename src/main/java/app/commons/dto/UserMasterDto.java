package app.commons.dto;

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

    @Override
    public String toString() {
        return "UserMasterDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userAuthentication='" + userAuthentication + '\'' +
                '}';
    }
}

