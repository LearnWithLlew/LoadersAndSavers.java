package com.bookstore.controller;

import org.springframework.ui.ConcurrentModel;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafUtils {

    public static String renderPage(String page, ConcurrentModel model) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context context = new Context(null, model.asMap());
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(page, context);
    }
}