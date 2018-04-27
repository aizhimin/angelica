package org.angelica.admin.common.resolver;

import org.angelica.admin.common.annotation.LoginUser;
import org.angelica.admin.common.shiro.ShiroUser;
import org.angelica.admin.common.shiro.ShiroUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 向请求方法中注入登录用户对象
 * @author aizhimin
 *
 */
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(ShiroUser.class) && parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		ShiroUser user =  ShiroUtils.getShiroUser();
        return user;
	}

}
