package info.pobu.blb.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity 
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
    private Integer id;

	@NotNull
	@Getter @Setter
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="nick_id")
    private Nick nick;

	@NotNull
	@Getter @Setter
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="email_id")
    private Email email;
	
	public User(@NotNull Nick nick, @NotNull Email email) {
		this.nick = nick;
		this.email = email;
	}
	
	public User() {}
}
