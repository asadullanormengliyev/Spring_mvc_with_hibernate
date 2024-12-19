package pdp.uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.dao.RoleDao;
import pdp.uz.entity.RoleEntity;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleDao roleDao;

    public RoleEntity addRole(RoleEntity roleEntity) {
        return roleDao.save(roleEntity);
    }
}
