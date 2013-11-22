package dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="TAMAGOTCHIES")
public class Tamagotchi {

	@Id
	@GeneratedValue
	int id;
	String name;
	int expo;
	int level;
	@OneToOne(cascade = {CascadeType.ALL})
	Student owner;

	public Tamagotchi() {
		super();
	}

	public Tamagotchi(String name) {
		super();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExpo() {
		return expo;
	}

	public void setExpo(int expo) {
		this.expo = expo;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Student getOwner() {
		return owner;
	}

	public void setOwner(Student owner) {
		this.owner = owner;
	}

}
