package com.github.awesomechat.chat.models;


import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatBotBean {

    private static final List<String> urls;
    private static final Map<String, List<String>> usersUrls = new ConcurrentHashMap<>();
    private static final int NUMBER_OF_URLS = 10;
    private static final Object addUserLock = new Object();

    static {
        List<String> list = new ArrayList<>(NUMBER_OF_URLS);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("0");
        urls = Collections.unmodifiableList(list);
    }

    public static void addUser(String name) {
        if(usersUrls.containsKey(name)) return;
        List<String> userUrls;
        synchronized (addUserLock) {
            userUrls = new ArrayList<>(NUMBER_OF_URLS);
            userUrls.addAll(urls);
        }
        usersUrls.put(name, userUrls);
    }

    public static void removeUser(String name) {

    }

    public String response(String msg, String user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int urlsLeft = usersUrls.get(user).size();
        if (urlsLeft == 0) return "Nothing to say to you, pearl.";

        Random generator = new Random();
        int urlIndex = generator.nextInt(urlsLeft + 1 - 1);

        String response = usersUrls.get(user).get(urlIndex);
        usersUrls.get(user).remove(response);
        response += "> urlsLeft: " + urlsLeft;
        return response;
    }

    private void removeUsersUrl(String name, String url) {
        usersUrls.get(name).remove(url);
    }

}
