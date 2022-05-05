package com.example.dz33.server;

public interface AuthService {

    String getNickname(String login, String password);

    boolean changeNickname(String currentNickname, String newNickname);
}
