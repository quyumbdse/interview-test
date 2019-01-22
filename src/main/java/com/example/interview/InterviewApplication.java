package com.example.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.example.interview.Interview.CreateRadioProfileRequest;
import com.example.interview.Interview.DeleteRadioProfileRequest;
import com.example.interview.Interview.SetRadioLocationRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }


    @Bean
    public RadioProfileRepository createTestProfiles() {
        Map<Integer, CreateRadioProfileRequest> createRadioProfilesRequest = new HashMap<>();
       
        Map<Integer, SetRadioLocationRequest> setRadioLocationRequest = new HashMap<>();

        Map<Integer, DeleteRadioProfileRequest> deleteRadioProfilesRequest = new HashMap<>();

        CreateRadioProfileRequest createRadioProfileRequest1 = CreateRadioProfileRequest.newBuilder().setId(100)
                .setAlias("Radio100").addAllowedLocations("CPH-1, CPH-2").build();
        CreateRadioProfileRequest createRadioProfileRequest2 = CreateRadioProfileRequest.newBuilder().setId(101)
                .setAlias("Radio101").addAllowedLocations("CPH-1, CPH-2, CPH-3").build();
        CreateRadioProfileRequest createRadioProfileRequest3 = CreateRadioProfileRequest.newBuilder().setId(102)
                .setAlias("Radio102").addAllowedLocations("CPH-1, CPH-3").build();

        createRadioProfilesRequest.put((int) createRadioProfileRequest1.getId(), createRadioProfileRequest1);
        createRadioProfilesRequest.put((int) createRadioProfileRequest2.getId(), createRadioProfileRequest2);
        createRadioProfilesRequest.put((int) createRadioProfileRequest3.getId(), createRadioProfileRequest3);

        return new RadioProfileRepository() {

            @Override
            public CreateRadioProfileRequest findById(int id) {
                return createRadioProfilesRequest.get(id);
            }

            @Override
            public DeleteRadioProfileRequest deleteProfile(int id) {
                return deleteRadioProfilesRequest.get(id);
            }

            @Override
            public SetRadioLocationRequest setLocationById(int id) {
               

                    SetRadioLocationRequest setRadioLocationRequest1 = SetRadioLocationRequest.newBuilder().setRadioId(100).setLocation("CPH-1").build();
                    SetRadioLocationRequest setRadioLocationRequest2 = SetRadioLocationRequest.newBuilder().setRadioId(101).setLocation("CPH-3").build();
            
                    setRadioLocationRequest.put((int) setRadioLocationRequest1.getRadioId(), setRadioLocationRequest1);
                    setRadioLocationRequest.put((int) setRadioLocationRequest2.getRadioId(), setRadioLocationRequest2);
            
                  return  setRadioLocationRequest.get(id);
            }
        };
    }

}

