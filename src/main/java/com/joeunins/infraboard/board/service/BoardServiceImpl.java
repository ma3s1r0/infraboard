package com.joeunins.infraboard.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeunins.infraboard.home.domain.HomeMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	HomeMapper homeMapper;

}
