package ouhk.comps380f.service;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import ouhk.comps380f.dao.Account;


public interface AuthService {

    @Nullable
    Account login(String username, String password);

    boolean register(Account account);

    boolean hasRoles(Authentication authentication, String... roles);

    Account validateLogon(Authentication authentication);

    String hash(String text);

}
