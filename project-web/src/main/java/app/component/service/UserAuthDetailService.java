package app.component.service;

import app.commons.entities.user.UserAuthInfo;
import com.google.common.flogger.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.infra.rdb.user.UserMaster;
import project.infra.rdb.user.UserMasterRepository;

public class UserAuthDetailService implements UserDetailsService {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    @Autowired
    private UserMasterRepository userMasterRepository;

    /**
     * ここでのuserNameはユーザのメールアドレスのことを指す。
     *
     * @param username
     * @return userDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || "".equals(username)) {
            logger.atWarning().log("UserName is Empty");
            throw new UsernameNotFoundException("Username is empty");
        }

        UserMaster userMaster = this.userMasterRepository.findByMailAddress(username);
        if (userMaster == null) {
            logger.atWarning().log("User not found for name: %s", username);
            throw new UsernameNotFoundException("User not found for name: " + username);
        }

        return new UserAuthInfo(userMaster);
    }
}
