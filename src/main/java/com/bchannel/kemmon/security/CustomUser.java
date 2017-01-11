package com.bchannel.kemmon.security;

import java.util.Collection;

import com.bchannel.kemmon.domain.enumeration.EnumTypeUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by admin on 1/4/2017.
 */
public class CustomUser extends User {

    private static final long serialVersionUID = -6411988532329234916L;

    public void setUserType(final EnumTypeUser userType) {
        this.userType = userType;
    }

    public EnumTypeUser getUserType() {
        return userType;
    }

    private EnumTypeUser userType;

    public CustomUser(String username, String password, EnumTypeUser userType,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userType = userType;
    }




}

