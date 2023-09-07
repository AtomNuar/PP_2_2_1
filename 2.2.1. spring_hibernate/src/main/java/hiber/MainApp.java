package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Роман", "Шатилов", "roman@ya.ru");
        User user2 = new User("Александр", "Иванов", "alex@ya.ru");
        User user3 = new User("Дмитрий", "Дмитриев", "dimitr@ya.ru");
        User user4 = new User("Мария", "Кышкыш", "maria@ya.ru");

        Car car1 = new Car("Ferrari", 1984);
        Car car2 = new Car("Mers", 2021);
        Car car3 = new Car("Kia", 2001);
        Car car4 = new Car("Ford", 1993);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
            System.out.println();
        }

        System.out.println(userService.getUserByCar("sad", 2014));
        System.out.println();

        context.close();
    }
}

