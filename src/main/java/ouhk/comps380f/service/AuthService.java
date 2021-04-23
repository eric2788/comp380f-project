package ouhk.comps380f.service;

import org.springframework.lang.Nullable;
import ouhk.comps380f.dao.User;

public interface AuthService {

    @Nullable
    User login(String username, String password);

    boolean register(User user);

}
