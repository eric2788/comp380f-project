package ouhk.comps380f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.repository.UserRepository;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Account login(String username, String password) {
        Optional<Account> user = userRepository.findById(username);
        if (user.isPresent() && user.get().getPassword().equals(hash(password))){
            return user.get();
        }
        return null;
    }

    @Override
    public boolean register(Account account) {
        if (userRepository.findById(account.getUsername()).isPresent()){
            return false;
        }
        account.setPassword(hash(account.getPassword()));
        userRepository.save(account);
        return true;
    }

    @Override
    public boolean hasRoles(Authentication authentication, String... roles) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities == null) return false;
        return Arrays.stream(roles).allMatch(r -> authorities.contains(new SimpleGrantedAuthority(r)));
    }

    @Override
    public Account toAccount(Authentication authentication) {
        if (!authentication.isAuthenticated()) return null;
        return userRepository.findById(authentication.getName()).orElse(null);
    }


    private String hash(String text){
        return Base64.getEncoder().encodeToString(DigestUtils.md5Digest(text.getBytes()));
    }

}
