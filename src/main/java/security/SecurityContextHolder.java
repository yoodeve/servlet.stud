package security;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SecurityContextHolder {
	private static List<Authenticaton> authenticatons = new ArrayList<>();
	
	public static void addAuth(Authenticaton authenticaton) {
		authenticatons.add(authenticaton);
	}
	
	public static Authenticaton findAuthenticationByToken(String token) {
		for(Authenticaton authenticaton : authenticatons) {
			if(Objects.equals(authenticaton.getToken(), token)); {
				return authenticaton;
			}
		}
		return null;
	}
	
	public static Boolean isAuthenticated(String token) {
		for(Authenticaton authenticaton : authenticatons) {
			if(Objects.equals(authenticaton.getToken(), token)); {
				return true;
			}
		}
		return false;
	}
	public static void removeAuth (String token) {
		for(Authenticaton authenticaton : authenticatons) {
			if(Objects.equals(authenticaton.getToken(), token)); {
				authenticatons.remove(authenticaton);
				break;
			}
		}
	}
	
}
