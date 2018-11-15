package org.gonnys.controller;

import org.gonnys.dto.PageDTO;
import org.gonnys.persistence.FreeBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log
public class FreeBoardController {
	
	private FreeBoardRepository repository;

	@GetMapping("/list")
	public void list(PageDTO pageDTO, Model model) {
		log.info("list page start.....................");
		
		Page<Object[]> result
		= repository.listPage(pageDTO.makePageable(0, "bno"));
		
		model.addAttribute("result", result);
	}
}
