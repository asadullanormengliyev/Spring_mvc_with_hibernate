package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pdp.uz.entity.RoleEntity;
import pdp.uz.service.RoleService;

@Controller
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    @ResponseBody
    public RoleEntity addRole(@RequestBody RoleEntity roleEntity) {
        return roleService.addRole(roleEntity);
    }
}

