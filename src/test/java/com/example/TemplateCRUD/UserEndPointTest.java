package com.example.TemplateCRUD;

import java.util.ArrayList; 
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import java.util.Arrays;
import static org.hamcrest.core.IsNot.not;
import org.springframework.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Value;
import com.example.TemplateCRUD.repository.*;
import com.example.TemplateCRUD.models.*;
import static org.junit.matchers.JUnitMatchers.*; 
import java.util.Optional;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserEndPointTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    UserRepository userRepository;

    @Test
    public void teste(){
    	String url="http://localhost:"+port+"/api/all";
        ResponseEntity<UserModel[]> response = restTemplate.getForEntity(
            "http://localhost:8080/api/all",
            UserModel[].class);
            UserModel[] users = response.getBody();
            UserModel user=users[0];
  
            assertThat(Arrays.asList(users), hasItem(user));
  
            assertThat( response.getStatusCode() ).isEqualTo(HttpStatus.OK);

            assertNotEquals(response.getStatusCode(), HttpStatus.BAD_GATEWAY);  

    }
    @Test
    public void testeMokito(){
        UserModel user=new UserModel();
        user.setId(1l);
        user.setNome("Netenha");
        user.setSobrenome("Stompa");
        Optional<UserModel> userOptional= Optional.of(user);
        BDDMockito.when(userRepository.findById(1l)).thenReturn(userOptional);
        ResponseEntity<UserModel[]> response = restTemplate.getForEntity(
            "http://localhost:8080/api/all",
            UserModel[].class);
            UserModel[] users = response.getBody();
            assertThat(Arrays.asList(users), hasItem(user));

    }
}
