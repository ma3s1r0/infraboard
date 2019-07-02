package com.joeunins.infraboard.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeunins.infraboard.home.domain.HomeMapper;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	HomeMapper homeMapper;

}
