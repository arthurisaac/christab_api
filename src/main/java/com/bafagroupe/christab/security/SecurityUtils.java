package com.bafagroupe.christab.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;


public final class SecurityUtils {
	
	public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
                /*System.out.println("************ Utilisateur connecté ****************");
                System.out.println(userName);
                System.out.println("****************************");*/
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
                /*System.out.println("************ Utilisateur connecté ****************");
                System.out.println(userName);
                System.out.println("****************************");*/
            }
        }
        return userName;
    }
	
	public static String getCurrentUserLoginAuthority() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String authority = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                authority = springSecurityUser.getAuthorities().toString();
            } else if (authentication.getPrincipal() instanceof String) {
            	authority = (String) authentication.getPrincipal();
            }
        }
        return authority;
    }

}
