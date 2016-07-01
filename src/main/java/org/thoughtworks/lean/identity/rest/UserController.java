package org.thoughtworks.lean.identity.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thoughtworks.lean.identity.domain.User;
import org.thoughtworks.lean.identity.service.UserService;

import java.security.Principal;

@RestController()
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/me")
    public User me(Principal user) {
        return userService.getUserByEmail(user.getName());
    }

    @RequestMapping("")
    public ResponseEntity searUsers(@RequestParam("name") String name, Principal principal) {
        return new ResponseEntity(userService.searchByName(name), HttpStatus.OK);
    }
}
