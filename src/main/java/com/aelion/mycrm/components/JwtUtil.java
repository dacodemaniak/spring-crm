package com.aelion.mycrm.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import com.aelion.mycrm.exceptions.JwtTokenMalformedException;
import com.aelion.mycrm.exceptions.JwtTokenMissingException;
import com.aelion.mycrm.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Value("${jwt.token.validity}")
	private long tokenValidity;
	
	public String getUserName(final String token) {
		try {
			Claims body = Jwts.parserBuilder().setSigningKey(this.jwtSecret).build().parseClaimsJws(token).getBody();
			
			return body.getSubject();
		} catch(Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		
		return null;
	}
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		
		Claims claims = Jwts.claims().setSubject(user.getUserName());
		
		final long now = System.currentTimeMillis();
		final long expire = now + this.tokenValidity;
		
		Date expirationDate = new Date(expire);
		
		return Jwts.builder().setClaims(claims)
				.setIssuedAt(new Date(now))
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret)
				.compact();
	}
	
	public void validateToken(final String token) {
		try {
			Jwts.parserBuilder().setSigningKey(this.jwtSecret).build().parseClaimsJws(token);
			
		} catch (MalformedJwtException e) {
			
			throw new JwtTokenMalformedException("Invalid JWT Token");
		} catch (IllegalArgumentException e) {
			throw new JwtTokenMissingException("JWT claims string is empty");
		}
	}
}
