package app.component.service;

import app.commons.entities.user.UserAuthInfo;
import app.commons.entities.user.UserMaster;
import app.component.repository.UserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailService implements UserDetailsService {

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
            throw new UsernameNotFoundException("Username is empty");
        }

        UserMaster userMaster = this.userMasterRepository.findByMailAddress(username);
        if (userMaster == null) {
            throw new UsernameNotFoundException("User not found for name: " + username);
        }

        return new UserAuthInfo(userMaster);
    }
}
