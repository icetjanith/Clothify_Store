package repository.custom;

import entity.UserEntity;
import repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity> {
    UserEntity authenticateUser(String email, String password);
}
