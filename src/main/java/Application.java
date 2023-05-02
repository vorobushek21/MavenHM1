import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();
        Scanner scanner = new Scanner(System.in);

        List<Employee> employees = List.of(
                new Employee("Rock", "Jonson", "M", 55),
                new Employee("Клава", "Котик", "Ж", 24));
        City city = new City( "Ковров", employees);
        cityDAO.createCity(city);

        int runSoft = 0;
        while (runSoft != 5) {
            printMenu();
            switch (runSoft = scanner.nextInt()) {
                case 1:
                    System.out.println("Введите id интересующего сотрудника");
                    int id = scanner.nextInt();
                    Employee employee1 = employeeDAO.getEmployeeById(id);
                    if (employee1 == null) {
                        System.out.println("Такого сотрудника не существует");
                    } else {
                        System.out.println(employee1);
                    }
                    break;
                case 2:
                    List<Employee> employees1 = employeeDAO.getAllEmployees();
                    for (Employee emp : employees1) {
                        System.out.println(emp);
                    }
                    List<City> cities = cityDAO.getAllCities();
                    for (City cit : cities) {
                        System.out.println(cit);
                    }
                    break;
                case 3:
                    System.out.println("Введите id сотрудника для редактирования");
                    id = scanner.nextInt();
                    Employee employee2 = employeeDAO.getEmployeeById(id);
                    System.out.println("Введите новые данные сотрудника:");
                    System.out.println("Имя");
                    String first_name = scanner.nextLine();
                    System.out.println("Фамилия");
                    String last_name = scanner.nextLine();
                    System.out.println("Пол");
                    String gender = scanner.nextLine();
                    System.out.println("Возраст");
                    int age = scanner.nextInt();
                    System.out.println("id города");
                    int cityId = scanner.nextInt();
                    Employee employee3 = new Employee(first_name, last_name, gender, age);
                    employee3.setId(employee2.getId());
                    cityDAO.updateCityById(cityId, employee3);
                    break;
                case 4:
                    System.out.println("Введите id города который хотите удалить");
                    id = scanner.nextInt();
                    City city1 = cityDAO.getCityById(id);
                    cityDAO.deleteCityById(city1);

                    HibernateManager.closeEntityManagerFactory();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Данный вариант отсутствует");
                    break;
            }
        }
    }


    public static void printMenu() {
        System.out.println("Что бы вы хотели сделать?:\n " +
                "1 - Поиск сотрудника \n " +
                "2 - Получить список всех сотрудников и городов \n " +
                "3 - Изменить данные сотрудника \n " +
                "4 - Удалить город из базы \n " +
                "5 - Выход");
    }
}