package info.pobu.blb.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import info.pobu.blb.controllers.exceptions.UserValidationFailedException;
import info.pobu.blb.entities.Email;
import info.pobu.blb.entities.Nick;
import info.pobu.blb.entities.User;
import info.pobu.blb.entities.exceptions.EmailValidationFailedException;
import info.pobu.blb.entities.exceptions.NickValidationFailedException;
import info.pobu.blb.repositories.IUserRepository;

@Controller
@RequestMapping(path = "/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private IUserRepository userRepository;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/greet")
    public  @ResponseBody  String greet() {
        System.out.println("jesttttes w greet");
        return "hello Angular. Welcome on birdwatchers-log-book";
    }

    @GetMapping(path = "/add")
    public @ResponseBody User addNewUser(@RequestParam String nick, @RequestParam String email)
            throws UserValidationFailedException {
        
        logger.info("adding new user: " + nick);
        
        Nick usersNick = getNick(nick);
        Email usersEmail = getEmail(email);
        
        final User user = userRepository.save(new User(usersNick, usersEmail));
        
        logger.info("user: " + user.getNick().getLiteral() + " added with ID: " + user.getId());
        
        return user;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<User> getAllUsers() {
        logger.info("getting all users");
        return userRepository.findAll();
    }

    @GetMapping(path = "/findBy", params = "nick")
    public @ResponseBody User findByNickLiteral(@RequestParam String nick) {
        final List<User> result = userRepository.findAll().stream().filter(u -> nick.equals(u.getNick().getLiteral()))
                .collect(Collectors.toList());
        Assert.isTrue(result.size() < 2, "result list should contain one or no users");

        return result.isEmpty() ? null : result.get(0);
    }
    
    @GetMapping(path = "/findBy", params = "email")
    public @ResponseBody User findByEmailLiteral(@RequestParam String email) {
        final List<User> result = userRepository.findAll().stream().filter(u -> email.equals(u.getEmail().getLiteral()))
                .collect(Collectors.toList());
        Assert.isTrue(result.size() < 2, "result list should contain one or no users");

        return result.isEmpty() ? null : result.get(0);
    }
    
    private Email getEmail(String email) throws UserValidationFailedException {
        Email usersEmail = null;
        try {
            usersEmail = new Email(email);
        } catch (EmailValidationFailedException e) {
            throw new UserValidationFailedException(e.getMessage());
        }
        return usersEmail;
    }

    private Nick getNick(String nick) throws UserValidationFailedException {
        Nick usersNick = null;
        try {
            usersNick = new Nick(nick);
        } catch (NickValidationFailedException e) {
            throw new UserValidationFailedException(e.getMessage());
        }
        return usersNick;
    }

    protected Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }
}