package app.commons.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.annotation.SessionScope;
import project.infra.rdb.user.UserAuthentication;
import project.infra.rdb.user.UserMaster;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@SessionScope
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthInfo implements UserDetails, Serializable {

    /**
     * serial version UID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * ユーザID
     */
    private Long userId;
    /**
     * ユーザ名はメールアドレスで代用
     */
    private String userMailAddress;
    /**
     * パスワード
     */
    private String password;
    /**
     * ロックの有無
     */
    private boolean isLocked;
    /**
     * 削除の有無
     */
    private boolean isDeleted;
    /**
     * ユーザの権限
     */
    private Collection<UserAuthentication> authorities;

    public UserAuthInfo(UserMaster userMaster) {
        this.userId = userMaster.getUserId();
        this.userMailAddress = userMaster.getMailAddress();
        this.password = userMaster.getPassword();
        this.isDeleted = userMaster.isDeleted();
        this.isLocked = userMaster.isLocked();
        this.authorities = Arrays.asList(userMaster.getUserAuthentication());
    }

    public UserAuthInfo() {
    }

    public void setAuthorities(Collection<UserAuthentication> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userMailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMailAddress() {
        return userMailAddress;
    }

    public void setUserMailAddress(String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
