package org.thoughtworks.lean.identity.service;

import org.thoughtworks.lean.identity.domain.Authentication;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thoughtworks.lean.identity.domain.User;

import java.util.List;

@FeignClient("identity")
public interface UserService {
    @RequestMapping(method = RequestMethod.GET, value = "/api/identity/users/search")
    User getUserByEmail(@RequestParam("email") String email);

    @RequestMapping(method = RequestMethod.GET, value = "/api/identity/users/auth")
    Authentication getAuthenticationByEmail(@RequestParam("email") String email);


    @RequestMapping(method = RequestMethod.GET, value = "/api/identity/users/searchByName")
    List<User> searchByName(@RequestParam("name") String name);
}