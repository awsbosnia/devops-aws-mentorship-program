package devops.mentorship.program.s3webappdemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@SpringBootTest
@Sql("/import.sql")
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
  @Autowired
  private WebApplicationContext wac;
  
  @MockBean
  private S3Client s3Client;
  
  @MockBean
  private SecretsManagerClient secretsManagerClient;
  
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  void testUsers() throws Exception {
    this.mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(view().name("users"))
        .andExpect(model().attributeExists("users"));
  }
  
  @Test
  void testUser() throws Exception {
    this.mockMvc.perform(get("/user/1")).andExpect(status().isOk()).andExpect(view().name("user"))
        .andExpect(model().attributeExists("user"));
  }
  
  @Test
  void testUserNotFound() throws Exception {
    this.mockMvc.perform(get("/user/13")).andExpect(status().isNotFound());
  }

}
