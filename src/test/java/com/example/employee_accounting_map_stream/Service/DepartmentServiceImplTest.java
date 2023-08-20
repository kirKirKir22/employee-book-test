package com.example.employee_accounting_map_stream.Service;

import com.example.employee_accounting_map_stream.dto.Employee;
import com.example.employee_accounting_map_stream.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl underTest;

    Employee ivan = new Employee("IVAN", "IVANOV", 1, 100);
    Employee taran = new Employee("TARAN", "TARANOV", 1, 200);
    Employee katamaran = new Employee("KATAMARAN", "KATAMARANOV", 2, 5000);

    Collection<Employee> employees;

    @BeforeEach
    void BeforeEach() {
        employees = List.of(ivan, taran, katamaran);
    }

    @Test
    void findMaxSalaryInTheDep_EmployeeFind_returnEmployeeMaxSalaryInTheDep() {
        when(employeeService.printAll()).thenReturn(employees);

        Employee result = underTest.findMaxSalaryInTheDep(1);
        assertEquals(taran, result);

    }

    @Test
    void findMaxSalaryInTheDep_EmployeeFind_returnEmployeeNotFoundException() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        int department = 1;

        EmployeeNotFoundException ex = assertThrows
                (EmployeeNotFoundException.class,
                        () -> underTest.findMaxSalaryInTheDep(1));
        assertEquals("Сотрудник не найден " + department, ex.getMessage());

    }

    @Test
    void findMinSalaryInTheDep_EmployeeFind_returnEmployeeMinSalaryInTheDep() {
        when(employeeService.printAll()).thenReturn(employees);

        Employee result = underTest.findMinSalaryInTheDep(1);
        assertEquals(ivan, result);

    }

    @Test
    void findMinSalaryInTheDep_EmployeeFind_returnEmployeeNotFoundException() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        int department = 1;

        EmployeeNotFoundException ex = assertThrows
                (EmployeeNotFoundException.class,
                        () -> underTest.findMaxSalaryInTheDep(1));
        assertEquals("Сотрудник не найден " + department, ex.getMessage());

    }

    @Test
    void findAllInfoDep_munDepartment_returnedAllInfoDep() {
        when(employeeService.printAll()).thenReturn(employees);

        Collection<Employee> result = underTest.findAllInfoDep(1);
        assertEquals(List.of(ivan, taran), result);
    }

    @Test
    void getGroupingDep_allEmployee_returnedGroupOfDep() {
        when(employeeService.printAll()).thenReturn(employees);

        Map<Integer, List<Employee>> result = underTest.getGroupingDep();
        assertEquals(Map.of(1, List.of(ivan, taran), 2, List.of(katamaran)), result);
    }
}