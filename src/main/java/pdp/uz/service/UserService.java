package pdp.uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.dao.RoleDao;
import pdp.uz.dao.UserDao;
import pdp.uz.entity.RoleEntity;
import pdp.uz.entity.UserEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserEntity addUser(UserEntity userEntity) {
        List<RoleEntity> all = roleDao.findAll();
        userEntity.setRoles(all);
        userDao.save(userEntity);
        return userEntity;
    }
}
