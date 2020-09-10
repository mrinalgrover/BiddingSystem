package com.cars24.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cars24.solution.db.BiddingSystem;
import com.cars24.solution.db.User;
import com.cars24.solution.db.UserRepository;


@RestController
public class BiddingSystemController {
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 * GET /auction?status=RUNNING
Response should contain the following attributes
1. Item Code
2. Highest bid amount placed
3. Step Rate
API should be paginated in nature. And you are free to change API structure based on your design
*/
	@GetMapping("/values")
    public @ResponseBody Iterable<BiddingSystem> index(){
        return  userRepository.findAll();
    }
	
	  @PostMapping("/add") // Map ONLY POST Requests
	  public @ResponseBody String addNewUser (@RequestParam int id
	      , @RequestParam int step) {
	    // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request

		BiddingSystem bs = new BiddingSystem();
	    //User n = new User();
	   // n.setName(name);
	    //n.setEmail(email);
		bs.setId(id);
		bs.setMbp(10);
		bs.setRunning(true);
		bs.setStepRate(step);
	    userRepository.save(bs);
	    return "Saved";
	  }

}
