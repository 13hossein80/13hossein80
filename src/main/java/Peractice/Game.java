package Peractice;


import com.mycompany.HomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Game extends WebPage {

    private User2 user2;

    public Game() {
        Form<?> form = new Form("form");

        user2 = new User2("", "", "", "", "");

        form.add(new TextField<>("firstNameInput", new PropertyModel<>(user2, "firstName")));

        form.add(new TextField<>("lastNameInput", new PropertyModel<>(user2, "lastName")));

        form.add(new TextField<>("ageInput", new PropertyModel<>(user2, "age")));

        form.add(new TextField<>("countryInput", new PropertyModel<>(user2, "country")));

        form.add(new TextField<>("cityInput", new PropertyModel<>(user2, "city")));

        form.add(new EmptyPanel("messageLabel"));

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

                 List<User2> users = session.createQuery("FROM User2").list();

                boolean shouldSave = true;
                for (User2 user : users) {
                    if (user.getAge().equals(user2.getAge())) {
                        shouldSave = false;
                        System.out.println("hi");
                        break;
                    }
                }
                if (shouldSave) {
                    form.addOrReplace(new Label("messageLabel","Your Information Was Recorded"));
                    session.save(user2);
                }
                else { form.addOrReplace(new Label("messageLabel","Wrong Information"));

                }

                session.getTransaction().commit();
                session.close();
                sessionFactory.close();
                target.add(form);
                setResponsePage(HomePage.class);
            }
        });
        add(form);
    }
}
