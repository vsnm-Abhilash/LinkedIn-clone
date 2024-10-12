package com.abhilash.linkedin.connections_service.service;

import com.abhilash.linkedin.connections_service.auth.UserContextHolder;
import com.abhilash.linkedin.connections_service.entity.Person;
import com.abhilash.linkedin.connections_service.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsService {

    private final PersonsRepository personsRepository;

    public List<Person> getMyFirstDegreeConnections(){
        Long userId= UserContextHolder.getCurrentUserId();
        log.info("Getting first degree connections for user with id : {} ",userId);
        return personsRepository.getFirstDegreeConnections(userId);
    }

}
