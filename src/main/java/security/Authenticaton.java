package security;

import entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Authenticaton {
	private User user;
	private String token;
}
