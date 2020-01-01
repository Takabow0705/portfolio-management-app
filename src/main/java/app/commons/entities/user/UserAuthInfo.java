package app.commons.entities.user;

import app.commons.enums.UserAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserAuthInfo implements UserDetails {

    /** ユーザ名はメールアドレスで代用*/
    private String userMailAddress;
    /** パスワード*/
    private String password;
    /** ロックの有無*/
    private boolean isLocked;
    /** 削除の有無*/
    private boolean isDeleted;
    /** ユーザの権限*/
    private Collection<UserAuthentication> authorities;


    public UserAuthInfo(UserMaster userMaster){
        this.userMailAddress = userMaster.getMailAddress();
        this.password = userMaster.getPassword();
        this.isDeleted = userMaster.isDeleted();
        this.isLocked = userMaster.isLocked();
        this.authorities = Arrays.asList(userMaster.getUserAuthentication());
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
}
