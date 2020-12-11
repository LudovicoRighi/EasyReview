package it.lea.services;

import javax.ejb.Stateless;

@Stateless
public class UserService {
	public UserService() {
	}
	
	public String metodoDiProva(String nome) {
		  return "ciaooo, "  + nome;
	}
}
