package com.example.employee_accounting_map_stream.Service;

import com.example.employee_accounting_map_stream.dto.Employee;
import com.example.employee_accounting_map_stream.exception.EmployeeAlreadyAddedException;
import com.example.employee_accounting_map_stream.exception.EmployeeNotFoundException;
import com.example.employee_accounting_map_stream.exception.EmployeeStorageIsFullException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private Map<String, Employee> employeeMap;
    EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    Employee employeeTest = new Employee
            ("Ivan", "Ivanov", 2, 100);
    private static final int MAX_EMPLOYEES = 100;

    @Test
    void addEmployee_allEmployeeFields_thrownEmployeeStorageIsFullException() {

        for (int i = 0; i < MAX_EMPLOYEES; i++) {
            underTest.addEmployee(employeeTest.getFirstName() + i,
                    employeeTest.getLastName() + i,
                    employeeTest.getDepartment(),
                    employeeTest.getSalary());
        }
        EmployeeStorageIsFullException ex = assertThrows
                (EmployeeStorageIsFullException.class, () -> underTest.addEmployee
                        ("Ivan", "Ivanov", 1, 2000));

        assertEquals("превышен лимит количества сотрудников в фирме", ex.getMessage());


    }

    @Test
    void addEmployee_allEmployeeFields_thrownEmployeeAlreadyAddedException() {

        underTest.addEmployee("Ivan", "Ivanov", 1, 2000);

        EmployeeAlreadyAddedException ex = assertThrows
                (EmployeeAlreadyAddedException.class, () -> underTest.addEmployee
                        ("Ivan", "Ivanov", 1, 2000));

        assertEquals("в коллекции уже есть такой сотрудник", ex.getMessage());


    }
    @Test
    void addEmployee_allEmployeeFields_returnedNewEmployee(){

        Employee employeeTest = new Employee
                ("Ivan", "Ivanov", 2, 100);

        Employee result = underTest.addEmployee("Ivan", "Ivanov", 1, 2000);

        assertEquals(employeeTest,result);

    }



    @Test
    void removeEmployee_employeeIsNotInMap_thrownException() {

        EmployeeNotFoundException ex = assertThrows
                (EmployeeNotFoundException.class, () -> underTest.removeEmployee("Ivan", "Ivanov"));
        assertEquals("сотрудник не найден", ex.getMessage());
    }

    @Test
    void removeEmployee_employeeIsInMap_employeeRemovedAndReturned() {

        underTest.addEmployee(employeeTest.
                getFirstName(), employeeTest.
                getLastName(), employeeTest.
                getDepartment(), employeeTest.
                getSalary());

        Employee result = underTest.removeEmployee(
                employeeTest.getFirstName(),
                employeeTest.getLastName());

        assertEquals(employeeTest, result);
        assertFalse(underTest.printAll().contains(employeeTest));

    }


    @Test
    void findEmployee_firstNameAndLastName_returnedEmployee() {
        underTest.addEmployee("Ivan", "Ivanov", 1, 2000);

        Employee result = underTest.findEmployee("Ivan", "Ivanov");

        assertEquals(employeeTest,result);

    }

    @Test
    void printAll() {
    }
}