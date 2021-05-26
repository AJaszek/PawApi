package paw.timetable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import paw.timetable.controller.ClassController;
import paw.timetable.controller.StudentController;
import paw.timetable.controller.SubjectController;
import paw.timetable.controller.TeacherController;
import paw.timetable.model.Student;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"TEACHER"})
class TimetableApplicationTests {

    @Autowired
    private ClassController classController;
    @Autowired
    private StudentController studentController;
    @Autowired
    private SubjectController subjectController;
    @Autowired
    private TeacherController teacherController;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext webApplicationContext;

    /* @Test
    void contextLoads() {
        //assertThat(controller).isNotNull();
    }*/
    @Test
    void loadControllers() {
        assertThat(classController).isNotNull();
        assertThat(studentController).isNotNull();
        assertThat(subjectController).isNotNull();
        assertThat(teacherController).isNotNull();
    }

    @Test
    @WithAnonymousUser
    public void unauthorisedUserCantGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/all"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(username = "admin", roles = {"STUDENT"})
    public void forbiddenPostForStudent() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.post(("/student"))
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
        mockMvc.perform(MockMvcRequestBuilders.get("/class/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
        mockMvc.perform(MockMvcRequestBuilders.get("/subject/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
        mockMvc.perform(MockMvcRequestBuilders.get("/teacher/all"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void studentListIsNotEmpty() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/student/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Student> students = mapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Student>>() {
        });

        assertThat(students).isNotNull();
        assertThat(students.size()).isGreaterThan(0);
    }

    @Test
    public void studentIsNotEmpty() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Student student = mapper.readValue(result.getResponse().getContentAsString(),
                Student.class);

        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1);
        assertThat(student.getFirst_name()).isNotNull();
        assertThat(student.getLast_name()).isNotNull();

    }

    @Test
    public void postEmptyStudentAndGet400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(("/student"))
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void uploadFile()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                        "file",
                        "hello.txt",
                        MediaType.TEXT_PLAIN_VALUE,
                        "Hello, World!".getBytes()
                );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadFile").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
