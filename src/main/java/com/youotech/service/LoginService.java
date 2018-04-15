package com.youotech.service;

import java.util.Map;

public interface LoginService {

	Map<String, Object> checkLogin(String username, String password);

	Map<String, Object> getMenuDate();

}
