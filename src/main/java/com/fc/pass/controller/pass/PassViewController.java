package com.fc.pass.controller.pass;

import com.fc.pass.repository.user.UserEntity;
import com.fc.pass.repository.user.UserRepository;
import com.fc.pass.service.pass.Pass;
import com.fc.pass.service.pass.PassService;
import com.fc.pass.service.user.User;
import com.fc.pass.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passes")
public class PassViewController {
    private final UserService userService;
    private final PassService passService;
    private final UserRepository userRepository;

    public PassViewController(UserService userService, PassService passService,
                              UserRepository userRepository) {
        this.userService = userService;
        this.passService = passService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView getPasses(@RequestParam("userId") String userId, ModelAndView modelAndView) {
        List<Pass> passes = passService.getPasses(userId);
        System.out.println("passes = " + passes);
        User user = userService.getUser(userId);
        System.out.println("user = " + user);
        modelAndView.addObject("passes", passes);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("pass/index");
        return modelAndView;
    }
}
