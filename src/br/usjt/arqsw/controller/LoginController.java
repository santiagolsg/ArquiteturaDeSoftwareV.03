package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Login;
import br.usjt.arqsw.service.LoginService;


/***
 * 
 * @author 81612334 Leonardo Santiago Gonçalves SIN3AN-MCA1
 *  */

@Controller
public class LoginController {
	LoginService loginservice;
	
	@Autowired
	public LoginController(LoginService loginservice){
		this.loginservice = loginservice;
	}
	
	@RequestMapping("loginForm")
	public String loginForm(){
		return "Login";
	}
			
	@RequestMapping("fazer_login")
	public String fazerLogin(Login login, HttpSession session){
		try {			
			if(loginservice.validarLogin(login)){
				session.setAttribute(Login.LOGADO, login);
				return "index";
			}else {
				System.out.println("estou no else");
			}
		} catch (IOException e) {		
			e.printStackTrace();
			return "Erro";			
		}
		System.out.println("continuei");
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		session.setAttribute(Login.LOGADO, null);
		return "index";
	}
	
}


