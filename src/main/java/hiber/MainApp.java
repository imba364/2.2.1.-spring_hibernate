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

      User petr = new User("Petr", "Ivanov", "petr@gmail.com");
      User kirill = new User("Kirill", "Sergeev", "sergeev@gmail.com");
      User vladislav = new User("Vladislav", "Maksimov", "maksimov@gmail.com");
      User andrey = new User("Andrey", "Valentinov", "valentinov@gmail.com");

      Car toyota = new Car("Toyota", 200);
      Car volvo = new Car("Volvo", 90);
      Car mercedes = new Car("Mercedes", 320);
      Car lexus = new Car("Lexus", 570);

      userService.add(petr.setCar(toyota).setUser(petr));
      userService.add(kirill.setCar(volvo).setUser(kirill));
      userService.add(vladislav.setCar(mercedes).setUser(vladislav));
      userService.add(andrey.setCar(lexus).setUser(andrey));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();

         Car car = user.getCar();
         if(car != null) {
            System.out.println("Car model = "+car.getModel());
            System.out.println("Car series = "+car.getSeries());
         }
      }

      context.close();
   }
}
