package service.custom;

import dto.User;
import service.SuperBo;

import java.util.HashMap;
import java.util.Map;

public interface UserService extends SuperBo {
    boolean addUser(User user);
    public Map authenticateUser(String email, String password);
}
