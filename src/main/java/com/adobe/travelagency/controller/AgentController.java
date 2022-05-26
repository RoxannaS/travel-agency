package com.adobe.travelagency.controller;

import com.adobe.travelagency.entity.Agent;
import com.adobe.travelagency.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/agent")
public class AgentController {

    AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<Agent> save(@RequestBody Agent agent) {

        Optional<Agent> savedAgent = agentService.save(agent);
        if (savedAgent.isPresent()) {
            return new ResponseEntity<>(savedAgent.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
