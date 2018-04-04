package jp.TsudaJun.spring.mysample.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jp.TsudaJun.spring.mysample.DAO.UserDao;
import jp.TsudaJun.spring.mysample.model.User;

public class ApplicationUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	UserDao dao;
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

//         Obtain info form request
//        ※今回使ったUsernamePasswordAuthenticationFilterはPOSTデータの"username"と"password"というパラメータをとれるようになっている。
//        ※他のパラメータ名で処理したい場合やパラメータを追加したい場合は自分で拡張する。
        
//        ※"username"の値をとる。
        final String user_id = request.getParameter("username");
//        ※"password"の値をとる。
        final String password = request.getParameter("password");
        if (user_id == null || password == null) {
            throw new AuthenticationServiceException("Authentication info must be set");
        }        

//         Validate info
//        DBに接続しても外のAPIにアクセスして検証してもよい。
        
//        User user  = dao.getUser(user_id, password);
        User user = dao.getUser(user_id, password);
        if (user == null) {
            throw new AuthenticationServiceException("Authentication Error");
        }
        
//         Create token form input
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user_id, password);
        
//         Move to identify user phase
        return this.getAuthenticationManager().authenticate(authToken);
    }

}
