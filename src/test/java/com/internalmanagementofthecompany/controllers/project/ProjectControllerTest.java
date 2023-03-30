package com.internalmanagementofthecompany.controllers.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internalmanagementofthecompany.dao.entities.employee.Employee;
import com.internalmanagementofthecompany.dao.entities.employee.Level;
import com.internalmanagementofthecompany.dao.entities.employee.Specialty;
import com.internalmanagementofthecompany.dao.entities.project.Project;
import com.internalmanagementofthecompany.dao.interfaces.IProjectDao;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    MockMvc mockMvc;
    IProjectDao dao = mock(IProjectDao.class);
    ProjectController controller = new ProjectController(dao);

    private JacksonTester<Project> jsonUser;

    private Project getTestProject() {
        return new Project(1L, "TestProject", Collections.emptySet());
    }
    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    void addProjectTest() throws Exception {
        given(dao.create(getTestProject())).willReturn(getTestProject());
        MockHttpServletResponse response = mockMvc.perform(
                        post("/project")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestProject()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestProject()).getJson());
    }

    @Test
    void getProjectByIdTest() throws Exception {
        given(dao.getById(getTestProject().getId())).willReturn(getTestProject());
        MockHttpServletResponse response = mockMvc.perform(
                get("/project/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestProject()).getJson());
    }

    @Test
    void updateProjectTest() throws Exception {
        given(dao.update(getTestProject())).willReturn(getTestProject());
        MockHttpServletResponse response = mockMvc.perform(
                        put("/project")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonUser.write(getTestProject()).getJson()))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonUser.write(getTestProject()).getJson());
    }

    @Test
    void deleteProjectTest()throws Exception {
        when(dao.delete(getTestProject().getId()))
                .thenReturn("Project with id:" + getTestProject().getId() + " was successfully removed");
        MockHttpServletResponse response = mockMvc.perform(
                delete("/project/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}