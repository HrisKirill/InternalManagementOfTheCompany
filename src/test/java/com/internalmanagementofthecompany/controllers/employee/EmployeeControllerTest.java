package com.internalmanagementofthecompany.controllers.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internalmanagementofthecompany.dto.employee.EmployeeDTO;
import com.internalmanagementofthecompany.entities.employee.Employee;
import com.internalmanagementofthecompany.entities.employee.Level;
import com.internalmanagementofthecompany.entities.employee.Specialty;
import com.internalmanagementofthecompany.dao.interfaces.IEmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    MockMvc mockMvc;

    IEmployeeDao dao = mock(IEmployeeDao.class);

    EmployeeController controller = new EmployeeController(dao);

    private JacksonTester<EmployeeDTO> jsonUser;

    private Employee getTestEmployee() {
        return Employee.builder()
                .id(1L)
                .level(Level.JUN)
                .name("Test")
                .typeOfSpecialty("DevOps")
                .specialty(Specialty.MANAGER)
                .projects(Collections.emptySet())
                .build();

    }

    private EmployeeDTO getTestEmployeeDTO() {
        return EmployeeDTO.builder()
                .id(1L)
                .level(Level.JUN)
                .name("Test")
                .typeOfSpecialty("DevOps")
                .specialty(Specialty.MANAGER)
                .projects(Collections.emptySet())
                .build();

    }

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void addEmployeeTest() throws Exception {
        given(dao.create(getTestEmployee())).willReturn(getTestEmployee());
        MockHttpServletResponse response = mockMvc.perform(
                        post("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestEmployeeDTO()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestEmployeeDTO()).getJson());
    }

    @Test
    void getEmployeeByIdTest() throws Exception {
        given(dao.getById(getTestEmployee().getId())).willReturn(getTestEmployee());
        MockHttpServletResponse response = mockMvc.perform(
                get("/employee/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestEmployeeDTO()).getJson());
    }

    @Test
    void updateEmployeeTest() throws Exception {
        given(dao.update(getTestEmployee())).willReturn(getTestEmployee());
        MockHttpServletResponse response = mockMvc.perform(
                        put("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestEmployeeDTO()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestEmployeeDTO()).getJson());
    }

    @Test
    void deleteEmployeeTest() throws Exception {
        when(dao.delete(getTestEmployee().getId()))
                .thenReturn("Employee with id:" + getTestEmployee().getId() + " was successfully removed");
        MockHttpServletResponse response = mockMvc.perform(
                delete("/employee/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}