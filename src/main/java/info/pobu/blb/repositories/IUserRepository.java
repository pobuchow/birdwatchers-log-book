package info.pobu.blb.repositories;

import org.springframework.data.repository.CrudRepository;

import info.pobu.blb.entities.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

}
