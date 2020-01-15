package com.kajes.cimb.inter.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kajes.cimb.inter.dto.UserModel;
import com.kajes.cimb.inter.dto.UserResponse;

@RestController
@RequestMapping(value = "/balanceinfo/api/v1")
public class BalanceController {

	@Autowired
	RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping(path = "/getbalance", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Double> getBalanceforAllUsers() {

		Map<String, Double> usersBalance = new HashMap<>();

		UserResponse response = restTemplate.getForObject("http://localhost:7777/userinfo/api/v1/users/",
				UserResponse.class);

		List<UserModel> allUsers = response.getAllUsersInfo();

		for (UserModel userModel : allUsers) {
			Double totalBalance = userModel.getAccountDetails().stream().map(x -> x.getBalance())
					.collect(Collectors.summingDouble(Double::doubleValue));

			usersBalance.put(userModel.getName(), totalBalance);

		}

		return usersBalance;
	}

}
