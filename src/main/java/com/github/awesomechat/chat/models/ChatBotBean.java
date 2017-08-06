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
        list.add("Did you see this? https://habrahabr.ru/post/334964/");
        list.add("Now I'm afraid of this - https://habrahabr.ru/post/334966/");
        list.add("This topic made my day - https://habrahabr.ru/post/334992/");
        list.add("Lol! https://habrahabr.ru/post/334982/");
        list.add("https://habrahabr.ru/post/334970/");
        list.add("https://habrahabr.ru/company/yandex/blog/335002/");
        list.add("https://habrahabr.ru/company/solarsecurity/blog/334796/");
        list.add("And mail.ru is here! https://habrahabr.ru/company/mailru/blog/334712/");
        list.add("This is our future - https://habrahabr.ru/post/334804/");
        list.add("You knew that? - https://habrahabr.ru/company/erlyvideo/blog/334912/");
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
        return response;
    }
}
