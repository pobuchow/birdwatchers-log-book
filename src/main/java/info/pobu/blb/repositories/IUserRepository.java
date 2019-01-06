package info.pobu.blb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import info.pobu.blb.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
	public User findByNickLiteral(String nick);

}
