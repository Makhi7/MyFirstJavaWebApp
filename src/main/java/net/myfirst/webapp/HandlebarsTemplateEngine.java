package net.myfirst.webapp;

import spark.ModelAndView;
import spark.ResponseTransformer;
import spark.TemplateEngine;

public class HandlebarsTemplateEngine extends TemplateEngine implements ResponseTransformer {
    @Override
    public String render(ModelAndView modelAndView) {
        return null;
    }
}
