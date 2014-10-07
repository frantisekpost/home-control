package com.fannysoft.homecontrol.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.DummyActor;
import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.repository.AgentRepository;

@Controller
public class AgentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentRepository agentRepository;
	
	@RequestMapping(value = "/agents", method = RequestMethod.GET)
	public @ResponseBody List<Agent> getAllAgents() {
		List<Agent> list = new ArrayList<Agent>();
		list.addAll(agentRepository.getAgents());
		return list;
	}
	
	@RequestMapping(value = "/actor/start/{id}", method = RequestMethod.GET)
	public @ResponseBody String startActor(@PathVariable("id") int actorId) {
		logger.info("Start actor, ID="+actorId);
		OnOffActor actor = (OnOffActor) agentRepository.getAgent(actorId);
		actor.setState(OnOffState.ON);
		return "ok";
	}
	
	@RequestMapping(value = "/actor/stop/{id}", method = RequestMethod.GET)
	public @ResponseBody String stopActor(@PathVariable("id") int actorId) {
		logger.info("Stop actor, ID="+actorId);
		OnOffActor actor = (OnOffActor) agentRepository.getAgent(actorId);
		actor.setState(OnOffState.OFF);
		return "ok";
	}
	
}
