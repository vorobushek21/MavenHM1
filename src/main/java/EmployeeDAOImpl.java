import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void createEmployee(Employee employee) {
        EntityManager entityManager = HibernateManager.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
    }
    @Override
    public Employee getEmployeeById(int id) {
        EntityManager entityManager = HibernateManager.getEntityManager();
        Employee employee = null;
        entityManager.getTransaction().begin();
        employee = entityManager.find(Employee.class, id);
        entityManager.getTransaction().commit();

        entityManager.close();
        return employee;
    }
    @Override
    public List<Employee> getAllEmployees() {
        EntityManager entityManager = HibernateManager.getEntityManager();

        entityManager.getTransaction().begin();
        String getAll = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(getAll, Employee.class);
        List<Employee> employeeList =query.getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();
        return employeeList;
    }
    @Override
    public void updateEmployeeById(Employee employee) {
        EntityManager entityManager = HibernateManager.getEntityManager();

        entityManager.getTransaction().begin();
        Employee employeeId = entityManager.find(Employee.class, employee.getId());
        employeeId.setFirstName(employee.getFirstName());
        employeeId.setLastName(employee.getLastName());
        employeeId.setGender(employee.getGender());
        employeeId.setAge(employee.getAge());
        employeeId.setCityId(employee.getCityId());
        entityManager.merge(employeeId);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
    @Override
    public void deleteEmployeeById(Employee employee) {
        EntityManager entityManager = HibernateManager.getEntityManager();

        entityManager.getTransaction().begin();
        Employee employee1 = entityManager.find(Employee.class, employee.getId());
        entityManager.remove(employee1);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}