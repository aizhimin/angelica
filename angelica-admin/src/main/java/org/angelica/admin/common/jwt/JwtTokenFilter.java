package org.angelica.admin.common.jwt;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.angelica.admin.common.Constant;
import org.angelica.core.response.ResponseError;
import org.angelica.core.response.ResponseResult;
import org.angelica.core.utils.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

/**
 * jwt token 过滤器
 * @author aizhimin
 *
 */
public class JwtTokenFilter extends AuthenticatingFilter {
	
	/**
	 * 获取token，传递给shiro
	 */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
		String token = ((HttpServletRequest) request).getHeader(Constant.HEADER_AUTH);
        if(StringUtils.isBlank(token)){
            return null;
        }
        return new JwtToken(token);
    }
	
    /**
     * 处理请求拒绝
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
    	String token = ((HttpServletRequest) request).getHeader(Constant.HEADER_AUTH);
        if(StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json;charset=utf-8");
            ResponseResult responseResult = ResponseResult.error(ResponseError.TOKEN_INVALID.getCode(), ResponseError.TOKEN_INVALID.getMsg());
            String json = JsonMapper.nonDefaultMapper().toJson(responseResult);
            httpResponse.getWriter().print(json);
            httpResponse.getWriter().close();
            return false;
        }

        return executeLogin(request, response);
    }
    
    /**
     * 处理登录失败异常
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ResponseResult responseResult = ResponseResult.error(ResponseError.TOKEN_INVALID.getCode(), ResponseError.TOKEN_INVALID.getMsg()+throwable.getMessage());
            String json = JsonMapper.nonDefaultMapper().toJson(responseResult);
            httpResponse.getWriter().print(json);
            httpResponse.getWriter().close();
        } catch (IOException e1) {

        }
        return false;
    }

}
