package ouhk.comps380f.service;

import org.springframework.lang.Nullable;
import ouhk.comps380f.dao.Account;


public interface AuthService {

    @Nullable
    Account login(String username, String password);

    boolean register(Account account);

}
