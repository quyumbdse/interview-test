package com.example.interview;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import com.example.interview.Interview.CreateRadioProfileRequest;
import com.googlecode.protobuf.format.JsonFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InterviewApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class InterviewApplicationTests {

	private static final String PROFILE1_URL = "http://localhost:8080/profiles/100";


    @Autowired
    private RestTemplate restTemplate;

	@Test
    public void whenUsingRestTemplate_thenSucceed() {
        ResponseEntity<CreateRadioProfileRequest> createRadioProfileRequest = restTemplate.getForEntity(PROFILE1_URL, CreateRadioProfileRequest.class);
        assertResponse(createRadioProfileRequest.toString());
	}

	@Test
    public void whenUsingHttpClient_thenSucceed() throws IOException {
        InputStream responseStream = executeHttpRequest(PROFILE1_URL);
        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
        assertResponse(jsonOutput);
	}
	

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        return httpResponse.getEntity().getContent();
    }

    private String convertProtobufMessageStreamToJsonString(InputStream protobufStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        CreateRadioProfileRequest createRadioProfileRequest = CreateRadioProfileRequest.parseFrom(protobufStream);
        return jsonFormat.printToString(createRadioProfileRequest);
    }
	
	private void assertResponse(String response) {
        assertThat(response, containsString("100"));
        assertThat(response, containsString("Radio100"));
		assertThat(response, containsString("CPH-1, CPH-2"));	
    }

}

