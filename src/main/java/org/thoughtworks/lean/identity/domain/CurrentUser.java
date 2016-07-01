package org.thoughtworks.lean.identity.domain;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private Authentication authObj;

    public CurrentUser(Authentication auth) {
        super(auth.getName(), auth.getCredentials(), AuthorityUtils.createAuthorityList("ADMIN"));
        this.authObj = auth;
    }

    public Authentication getAuthObj() {
        return authObj;
    }

    public void setAuthObj(Authentication authObj) {
        this.authObj = authObj;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + authObj +
                "} " + super.toString();
    }
}