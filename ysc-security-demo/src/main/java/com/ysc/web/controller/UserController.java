package com.ysc.web.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.ysc.dto.User;
import com.ysc.dto.UserQueryCondition;
import com.ysc.exception.UserNotExistException;
//import com.ysc.security.app.social.AppSignUpUtils;
import com.ysc.security.core.properties.SecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
//	@Autowired
//	private AppSignUpUtils appSignUpUtils;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//		appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	
	}
	//不用jwt
	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
//	@GetMapping("/me")
//	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws Exception {
//		String header = request.getHeader("Authorization");
//		String token = StringUtils.substringAfter(header, "bearer ");
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//		
//		String company = (String) claims.get("company");
//		System.out.println("company: " + company);
//		
//		return user;
//	}
	
//	public Object getCurrentUser(Authentication authentication) {
////		return SecurityContextHolder.getContext().getAuthentication();
//		return authentication;
//	}
	
	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(
					error -> {
//						FieldError fieldError = (FieldError)error;
//						String message = fieldError.getField() + " " +error.getDefaultMessage();
						System.out.println(error.getDefaultMessage());
					});
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

	//@RequestMapping(value = "/user", method = RequestMethod.GET)
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询接口")
	public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username,asc")Pageable pageable) {
		
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		
		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	//@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
		
//		throw new UserNotExistException(id);
//		throw new RuntimeException("ASC");
		System.out.println("进入getUserInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
}
