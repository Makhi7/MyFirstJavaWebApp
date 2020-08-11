package net.myfirst.webapp;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        staticFiles.location("/public");


        List<String> usernames = new ArrayList<>();

        get("/greet", (request, response) -> "Hello! ");
        get("/greet/:username", (request, response) -> "Hello! " + request.params(":username"));
        get("/greet/:username/language/:language", (request, response) -> "language is " + request.params(":language"));
        post("/greet", (request, response) -> "Hello " + request.queryParams("username"));


        get("/hello",
                (req, res) -> {

                    Map<String, Object> map = new HashMap<>();
                    return new ModelAndView(map, "hello.handlebars");

                },
                new HandlebarsTemplateEngine());


        post("/hello",
                (req, res) -> {

                    Map<String, Object> map = new HashMap<>();

                    // create the greeting message
                    String username = req.queryParams("username");

                    String greeting = "Hello, " + username;

                    if (!usernames.contains(username)) {
                        usernames.add(username);
                    }

                    // put it in the map which is passed to the template - the value will be merged into the template
                    map.put("greeting", greeting);
                    map.put("users", usernames);

                    return new ModelAndView(map, "hello.handlebars");

                },
                new HandlebarsTemplateEngine());

    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


}
