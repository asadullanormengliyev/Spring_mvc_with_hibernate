package pdp.uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pdp.uz.entity.UserEntity;
import pdp.uz.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public UserEntity addAuthor(@RequestBody UserEntity userEntity) {
         userService.addUser(userEntity);
         return userEntity;
    }

}
