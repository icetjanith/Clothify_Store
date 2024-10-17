package service.custom.impl;

import dto.User;
import entity.UserEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserRepository;
import service.custom.UserService;
import util.DaoType;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Override
    public boolean addUser(User user) {
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserRepository userRepository = DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userRepository.save(entity);

    }

    @Override
    public Map<String,Object> authenticateUser(String email, String password) {
        UserRepository userRepository = DaoFactory.getInstance().getDaoType(DaoType.USER);
        UserEntity userEntity=userRepository.authenticateUser(email, password);
        Map<String,Object> map = new HashMap<>();
        map.put("userExists", userEntity!=null);
        if(userEntity!=null){
            map.put("userType", userEntity.getUserType());
        }else {
            map.put("userType", null);
        }
        return map;
    }
}
