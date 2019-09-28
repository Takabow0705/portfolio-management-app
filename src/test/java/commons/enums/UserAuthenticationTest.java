package commons.enums;

import org.junit.Test;

import app.commons.enums.UserAuthentication;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class UserAuthenticationTest {

    @Test
    public void convertFromAdminString() {
        assertThat(UserAuthentication.ADMIN, equalTo(UserAuthentication.convertFrom("admin")));
    }

    @Test
    public void convertFromGeneralString() {
        assertThat(UserAuthentication.GENERAL, equalTo(UserAuthentication.convertFrom("general")));
    }

    @Test
    public void convertFromUndefinedString(){
        assertThat(UserAuthentication.UNDEFINED, equalTo(UserAuthentication.convertFrom("undefined")));
    }

    @Test
    public void convertFromNullReturnsUndefined(){
        assertThat(UserAuthentication.UNDEFINED, equalTo(UserAuthentication.convertFrom(null)));
    }

    @Test
    public void convertFromOtherStringReturnsUndefined(){
        assertThat(UserAuthentication.UNDEFINED, equalTo(UserAuthentication.convertFrom("other")));
    }
}