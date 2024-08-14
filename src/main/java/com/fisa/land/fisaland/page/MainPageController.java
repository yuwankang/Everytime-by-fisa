package com.fisa.land.fisaland.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	
	   @GetMapping("main")
	    public String get() {
	    	return "index";
	    }
	   
	    @GetMapping("/gathering_register")
	    public String gatheringRegisterPage() {
	        return "gathering_register"; 
	    }
	    
	    @GetMapping("/login")
	    public String loginPage() {
	        return "login"; 
	    }

	   @GetMapping("village")
	   public String getVillagePage() {
		   return "villageMain";
	   }
	   
	   @GetMapping("market")
	   public String getMarketPage() {
		   return "marketMain";
	   }
}
