package site.metacoding.red.domain.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateDto {
	private String username;
	private String password;
	private String email;
}
