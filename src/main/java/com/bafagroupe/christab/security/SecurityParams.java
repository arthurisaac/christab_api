package com.bafagroupe.christab.security;

public class SecurityParams {
    public static final String SECRET = "Vladimir";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final long TIME = 864_000_000; // System.currentTimeMillis()+10*20*3600; // 10 jours * 24 heures * 3600 secondes
    public static final String CLAIM = "Roles";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String USERNAME = "wendyataps@gmail.com";
    public static String emailRecup ="";
    public static final String PASS = "Wtap@18ag";
    public static final String MESSAGE = "Bonjour, vous avez demandez à ré-initialiser votre mot de passe? Si oui, veuiller entrer ce code:"
            + " ";
}
