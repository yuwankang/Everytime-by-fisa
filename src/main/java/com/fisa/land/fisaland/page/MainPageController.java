package com.fisa.land.fisaland.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

	@GetMapping("main")
	public String get() {
		return "index";
	}

	@GetMapping("/create_gathering")
	public String gatheringRegisterPage() {
		return "create_gathering";
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

	@GetMapping("myGathering")
	public String getmyGathering() {
		return "myGathering";
	}

	@GetMapping("joinedGathering")
	public String getJoinedGathering() {
		return "joinedGathering";
	}

	@GetMapping("marketJoin")
	public String getMarketJoinPage() {
		return "marketJoinGathering";
	}

	@GetMapping("myBorrow")
	public String getmyBorrow() {
		return "myBorrow";
	}

	@GetMapping("myLend")
	public String getmyLend() {
		return "myLend";
	}

	@GetMapping("addProduct")
	public String getaddProduct() {
		return "addProduct";
	}

	@GetMapping("myProducts")
	public String getmyProducts() {
		return "myProducts";
	}

	@GetMapping("addMarket")
	public String addMarket() {
		return "addMarket";
	}

	@GetMapping("marketList")
	public String marketList() {
		return "marketList";
	}
	@GetMapping("marketReviews")
	public String marketReviews() {
		return "marketReview";
	}
}