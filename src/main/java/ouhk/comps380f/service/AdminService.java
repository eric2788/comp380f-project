package ouhk.comps380f.service;

import ouhk.comps380f.dao.User;

public interface AdminService {

    boolean login(String username, String password);

    boolean register(User user);

}
