package com.mycompany;

import Peractice.Game;
import org.apache.wicket.ajax.AjaxClientInfoBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import sun.misc.FormattedFloatingDecimal;

public class HomePage extends WebPage {

    private User user;

    public HomePage() {

        Form<?> form = new Form("form");

        user = new User("", "", "", "");

        form.add(new TextField<>("userNameInput", new PropertyModel<>(user, "userName")).setRequired(true));

        form.add(new TextField<>("ageInput", new PropertyModel<>(user, "age")).setRequired(true));

        form.add(new TextField<>("emailInput", new PropertyModel<>(user, "email")).setRequired(true));

        form.add(new TextField<>("cityInput", new PropertyModel<>(user, "city")).setRequired(true));

        form.add(new AjaxButton("submit2") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {

            }
        });

        form.add(new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                SessionFactory sessionFactory;
                StandardServiceRegistry serviceRegistry;

                Configuration configuration = new Configuration();
                configuration.configure();

                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
                session.close();
                sessionFactory.close();
                setResponsePage(Game.class);
            }
        });
        add(form);
    }
}