package ouhk.comps380f.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final Map<String, Account> notInDatabase = new ConcurrentHashMap<>();

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        Account root = new Account();
        root.setAdmin(true);
        root.setPassword("root1234");
        root.setFullname("root user");
        root.setAddress("no address");
        root.setPhone(12345);
        this.notInDatabase.put("root",root );
    }

    @Override
    public Account login(String username, String password) {
        if (notInDatabase.containsKey(username) && notInDatabase.get(username).getPassword().equals(password)) return notInDatabase.get(username);
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
        account.setPassword("{noop}".concat(account.getPassword()));
        account.setAdmin(false);
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
    public Account validateLogon(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) throw new CustomException("not logging in");
        logger.info("validate login username: "+authentication.getName());
        if (notInDatabase.containsKey(authentication.getName())) return notInDatabase.get(authentication.getName());
        return userRepository.findById(authentication.getName()).orElseThrow(() -> new CustomException("unknown username"));
    }


    public String hash(String text){
        return Base64.getEncoder().encodeToString(DigestUtils.md5Digest(text.getBytes()));
    }

}
