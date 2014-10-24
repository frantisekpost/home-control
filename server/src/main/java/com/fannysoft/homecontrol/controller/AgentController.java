package com.fannysoft.homecontrol.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.DataProvider;
import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.queue.AgentRepo;
import com.fannysoft.homecontrol.queue.ServerAgent;

@Controller
public class AgentController {

	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	private AgentRepo agentRepo = AgentRepo.getInstance();
	
	@RequestMapping(value = "/agents", method = RequestMethod.GET)
	public @ResponseBody List<Agent> getAllAgents() {
		List<Agent> list = new ArrayList<Agent>();
		list.addAll(agentRepo.getAllAgents());
		return list;
	}
	
	@RequestMapping(value = "/actor/start/{id}", method = RequestMethod.GET)
	public @ResponseBody String startActor(@PathVariable("id") int actorId) {
		logger.info("Start actor, ID="+actorId);
		ServerAgent agent = agentRepo.getAgent(actorId);
		agent.sendState(OnOffState.ON);
		return "ok";
	}
	
	@RequestMapping(value = "/actor/stop/{id}", method = RequestMethod.GET)
	public @ResponseBody String stopActor(@PathVariable("id") int actorId) {
		logger.info("Stop actor, ID="+actorId);
		ServerAgent agent = agentRepo.getAgent(actorId);
		agent.sendState(OnOffState.OFF);
		return "ok";
	}
	
	@RequestMapping(value = "/data/get/{id}", method = RequestMethod.GET)
	public @ResponseBody DataProvider getData(@PathVariable("id") int providerId) {
		return (DataProvider) agentRepo.getAgent(providerId);
	}
	
}
