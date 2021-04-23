package ouhk.comps380f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ouhk.comps380f.dao.User;
import ouhk.comps380f.repository.UserRepository;

import java.util.Base64;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String username, String password) {
        return userRepository.findById(username).map(u -> u.getPassword().equals(hash(password))).orElse(false);
    }

    @Override
    public boolean register(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    private String hash(String text){
        return Base64.getEncoder().encodeToString(DigestUtils.md5Digest(text.getBytes()));
    }

}
