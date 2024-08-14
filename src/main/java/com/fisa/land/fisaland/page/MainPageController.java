package com.fisa.land.fisaland.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
	   @GetMapping("main")
	    public String get() {
	    	return "index";
	    }
	   @GetMapping("about")
	   public String get2() {
		   return "about";
	   }
}
