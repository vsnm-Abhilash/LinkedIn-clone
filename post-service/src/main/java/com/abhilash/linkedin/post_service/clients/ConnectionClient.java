package com.abhilash.linkedin.post_service.clients;

import com.abhilash.linkedin.post_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="connections-service",path="/connections")
public interface ConnectionClient {

    @GetMapping("/core/first-degree")
    List<PersonDto> getMyFirstDegreeConnections();
}
