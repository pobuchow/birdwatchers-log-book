package info.pobu.blb.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Integer id;

	@NotNull
	@Getter @Setter
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "nick_id", referencedColumnName = "id")
    private Nick nick;

	@NotNull
	@Getter @Setter
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "email_id", referencedColumnName = "id")
    private Email email;
	
	@OneToMany
	private List<Observation> observations;
	
	public User(@NotNull Nick nick, @NotNull Email email) {
		this.nick = nick;
		this.email = email;
	}
	
	public User() {}
}
