package com.example.dz6.task1.client.server;

public interface AuthService {

    String getNickname(String login, String password);

    boolean changeNickname(String currentNickname, String newNickname);
}
