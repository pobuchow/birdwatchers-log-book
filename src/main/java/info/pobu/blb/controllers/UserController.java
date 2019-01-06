package info.pobu.blb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.pobu.blb.controllers.exceptions.UserValidationFailedException;
import info.pobu.blb.entities.Email;
import info.pobu.blb.entities.Nick;
import info.pobu.blb.entities.User;
import info.pobu.blb.entities.exceptions.NickIsTooLongException;
import info.pobu.blb.entities.exceptions.NickIsTooShortException;
import info.pobu.blb.entities.exceptions.NotValidEmailException;
import info.pobu.blb.repositories.IUserRepository;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private IUserRepository userRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody User addNewUser (@RequestParam String nick, @RequestParam String email) throws UserValidationFailedException {

		Nick usersNick = null;
		try {
			usersNick = new Nick(nick);
		} catch (NickIsTooShortException | NickIsTooLongException e) {
			throw new UserValidationFailedException(e.getMessage());
		}
		Email usersEmail = null;
		try {
			usersEmail = new Email(email);
		} catch (NotValidEmailException e) {
			throw new UserValidationFailedException(e.getMessage());
		}
		return userRepository.save(new User (usersNick, usersEmail));
	}
	
	@GetMapping(path="/all")
	public @ResponseBody List<User> getAllUsers() {
		return userRepository.findAll();
	}
}