package com.youotech.shiro;

import com.youotech.entity.RoleInfo;
import com.youotech.entity.UserInfo;
import com.youotech.service.UserInfoService;
import com.youotech.shiro.utils.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 用户认证
 *
 * @author yang
 */
public class ShiroAccountRealm extends AuthorizingRealm {

	@Autowired
	private UserInfoService userInfoService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principalCollection);
			SecurityUtils.getSubject().logout();
			return null;
		}
		String username = (String) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		UserInfo user = ShiroSessionUtils.getLoginAccount();
		Set<String> permissions;
		// 获取权限信息
		if (null != user) {
			// 从session中获取权限
			Object o = ShiroSessionUtils.getAttribute(Const.SHIRO_PERMISSIONS);
			if (o != null) {
				permissions = (Set<String>) o;
			} else {
				permissions = userInfoService.selectForPermissions(username);
				ShiroSessionUtils.setAttribute(Const.SHIRO_PERMISSIONS, permissions);
			}
			if (permissions != null && permissions.size() > 0) {
				authorizationInfo.addStringPermissions(permissions);
			}
			return authorizationInfo;
		} else {
			throw new AuthorizationException();
		}
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		// 根据用户名查找用户
		UserInfo user = userInfoService.selectByUserName(username);
		if (null != user) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());
			ShiroSessionUtils.setAsLogin(user);

			RoleInfo info = userInfoService.selectRoleByUser(user.getId());
			ShiroSessionUtils.setAttribute("roleInfo", info);
			/**
			 * 关闭浏览器，再打开后，虽然授权缓存了，但是认证是必须的，在认证成功后，清除之前的缓存。
			 */
			clearCache(authcInfo.getPrincipals());
			return authcInfo;
		} else {
			// 认证没有通过
			throw new UnknownAccountException();// 没帐号
		}
	}

}
