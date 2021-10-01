package commons.enums;

import org.junit.jupiter.api.Test;
import project.infra.rdb.user.UserAuthentication;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserAuthenticationTest {

    @Test
    public void convertFromAdminString() {
        assertThat(UserAuthentication.ADMIN, equalTo(UserAuthentication.convertFrom("ADMIN")));
    }

    @Test
    public void convertFromGeneralString() {
        assertThat(UserAuthentication.GENERAL, equalTo(UserAuthentication.convertFrom("GENERAL")));
    }

    @Test
    public void convertFromUndefinedString(){
        assertThat(UserAuthentication.UNDEFINED, equalTo(UserAuthentication.convertFrom("UNDEFINED")));
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