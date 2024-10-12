package com.abhilash.linkedin.connections_service.contoller;

import com.abhilash.linkedin.connections_service.entity.Person;
import com.abhilash.linkedin.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {

    private final ConnectionsService connectionsService;

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getMyFirstDegreeConnections(@RequestHeader("X-User-Id") Long userId){
        return ResponseEntity.ok(connectionsService.getMyFirstDegreeConnections());
    }
}
