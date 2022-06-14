package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneService phoneService; // = new PhoneDao() X 주입해줘

	// 생성자

	// 메소드 - gs

	// 메소드 - 일반
	// 전화번호 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController > list()");

		// Service를 통해서 personList 주소를 가져온다.
		// PhoneService phoneService = new PhoneService();
		List<PersonVo> personList = phoneService.getPersonList();

		// ds 데이터보내기 ==> request attribute에 넣는다.
		model.addAttribute("personList", personList);

		return "list";
	}

	// 전화번호 업데이트폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("no") int no) {
		System.out.println("PhoneController > updateForm()");

		// Dao만들기
		// PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneService.getPerson(no);

		model.addAttribute("personVo", personVo);

		return "updateForm";
	}

	// 전화번호 업데이트
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update()");

		// 파라미터 꺼내기 + Vo로 묶어서 Ds로 메소드의 파라미터로 보내준다.

		// Dao만들기
		// PhoneDao phoneDao = new PhoneDao();
		// phoneDao.personUpdate(personVo);
		int count = phoneService.update(personVo);
		System.out.println(count);
		return "redirect:/list";
	}

	// 전화번호 삭제 (url에 PathVariable로 사용하는 방법)
	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete2(@PathVariable("no") int no) {
		System.out.println("PhoneController > delete()");

		// Dao만들기
		// PhoneDao phoneDao = new PhoneDao();
		// phoneDao.personDelete(no);
		// System.out.println(no);
		int count = phoneService.delete(no);
		System.out.println(count);

		return "redirect:/list";
	}

	// 전화번호 삭제 (url에 쿼리스트링으로 사용하는 방법)
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController > delete()");

		// Dao만들기
		// PhoneDao phoneDao = new PhoneDao();
		// phoneDao.personDelete(no);
		int count = phoneService.delete(no);
		System.out.println(count);

		return "redirect:/list";
	}

	// 전화번호 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > write()");

		// 파라미터 꺼내기 + Vo로 묶어서 Ds로 메소드의 파라미터로 보내준다.

		// Dao로 저장하기
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personInsert(personVo);
		// System.out.println(count);
		int count = phoneService.insert(personVo);
		System.out.println(count);

		// 리다이렉트
		// 리스트로 리다이텍트 시킬 예정

		return "redirect:/list";
	}

	// 전화번호 등록
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneController > write()");
		/*
		 * System.out.println(name); System.out.println(hp);
		 * System.out.println(company);
		 */
		// Vo만들기
		PersonVo personVo = new PersonVo(name, hp, company);
		int count = phoneService.insert(personVo);
		System.out.println(count);

		// Dao로 저장하기
		// PhoneDao phoneDao = new PhoneDao();
		// int count = phoneDao.personInsert(personVo);
		// System.out.println(count);

		// 리다이렉트
		// 리스트로 리다이텍트 시킬 예정

		return "redirect:/list";
	}

	// 전화번호 등록 폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm()");

		return "writeForm";
	}

	// 테스트
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test() {
		System.out.println("PhoneController > test()");
		// 다오
		return "test";
	}

	// 등록메소드

	// 수정폼메소드

	// 삭제메소드

	// 리스트메소드

}
