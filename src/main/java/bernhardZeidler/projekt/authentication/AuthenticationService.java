package bernhardZeidler.projekt.authentication;

import bernhardZeidler.projekt.user.User;
import bernhardZeidler.projekt.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private UserService userService;

    @Value("${authenticationService.jwt.secret}")
    private String JWTSecret;

    @Value("${authenticationService.salt}")
    private String salt;

    /**
     * Return object containing a valid user and his corresponding JWT token.
     */
    public static class UserToken {
        public User user;
        public String token;
    }

    /**
     * Create a JWT token and additional user information if the user's credentails are valid.
     *
     * @param email    email
     * @param password password
     * @return a UserToken or null if the credentials are not valid
     */
    public UserToken login(String email, String password) {
        String hashedPassword = hashPassword(password);
        User user = userService.getUser(email, hashedPassword);
        if (user == null) {
            LOG.info("User unable to login. user={}", email);
            return null;
        }
        LOG.info("User successfully logged in. user={}", email);

        String token = Jwts.builder()
                .setSubject(email)
                .setId(user.getId().toString())
                .signWith(SignatureAlgorithm.HS512, JWTSecret)
                .compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }

    /**
     * Validate that a token is valid and returns its body.
     *
     * Throws a SignatureException if the token is not valid.
     * @param jwtToken JWT token
     * @return JWT body
     */
    public Object parseToken(String jwtToken) {
        LOG.debug("Parsing JWT token. JWTtoken={}", jwtToken);
        return Jwts.parser()
                .setSigningKey(JWTSecret)
                .parse(jwtToken)
                .getBody();
    }

    /**
     * Return (salt + password) hashed with SHA-512.
     *
     * The salt is configured in the property authenticationService.salt.
     *
     * @param password plain text password
     * @return hashed password
     */
    private String hashPassword(String password) {
        return DigestUtils.sha512Hex(salt + password);

    }
    
    public UserToken signup(String email, String password, String name, String message)
    {
    	//check if user is actually new (by email)
    	User user = userService.getUser(email);
    	UserToken token = null;
    	if (user == null)
    	{//new User
    		String hashedPassword = hashPassword(password);
    		
    		userService.insertNewUser(email, hashedPassword, name, message);
    		token = login(email, password); 
    		if( token != null)
    			LOG.info("New User succesfully created and logged in");
    		else
    			LOG.error("Signup failed: could not retrieve new user from DB");
    	}
    	else
    	{//user already in DB
    		LOG.error("User with email={} already exists", email);
    	}
    	return token;
    }
}
