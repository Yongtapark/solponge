package com.example.demo.domain.member.login.interceptor;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 로그인 여부 체크 인터셉터
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행{}",requestURI);
        HttpSession session = request.getSession(false);

        session.setAttribute("prevPage",request.getHeader("referer"));

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(session==null||loginMember==null){
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/com.solponge/login?redirectURL="+requestURI);
            return  false;
        }
       // request.setAttribute("member",loginMember);// 이게 없으면, header 에서 로그인을 잡지 못한다.//추후 수정예정
        return true;
    }
}
