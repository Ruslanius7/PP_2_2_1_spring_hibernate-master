package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Petrov", "petrov@mail.ru");
        Car car1 = new Car("Toyota", 1001);
        user1.setCar(car1);
        User user2 = new User("User2", "Ivanov", "ivanov@mail.ru");
        Car car2 = new Car("Mercedes", 1002);
        user2.setCar(car2);
        User user3 = new User("User3", "Sidorov", "sidorov@mail.ru");
        Car car3 = new Car("BMW", 1003);
        user3.setCar(car3);
        User user4 = new User("User4", "Medvedev", "medvedev@mail.ru");
        Car car4 = new Car("Audi", 1004);
        user4.setCar(car4);


        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().toString());
            System.out.println();
        }

        List<User> usersWithCars  = userService.findUserByCar("Toyota", 1001);
        System.out.println("Users with certain cars: ");
        for (User userWithCar : usersWithCars) {
            System.out.println("Id = " + userWithCar.getId());
            System.out.println("First Name = " + userWithCar.getFirstName());
            System.out.println("Last Name = " + userWithCar.getLastName());
            System.out.println("Email = " + userWithCar.getEmail());
            System.out.println("Car = " + userWithCar.getCar().toString());
            System.out.println();
        }

        context.close();
    }
}
