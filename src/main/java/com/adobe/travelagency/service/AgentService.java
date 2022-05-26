package com.adobe.travelagency.service;

import com.adobe.travelagency.entity.Agent;
import com.adobe.travelagency.repository.AgentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Optional<Agent> save(Agent agent) {
        return Optional.of(agentRepository.save(agent));
    }

}
