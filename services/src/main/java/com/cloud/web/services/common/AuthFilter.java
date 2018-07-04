package com.cloud.web.services.common;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/services/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = req.getParameter("token");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI().toLowerCase();
        if(url.equals(request.getContextPath() + "/error")){
            chain.doFilter(req,res);
        }else if(token == null || token.isEmpty() || token.length() < 1){
            System.out.println("无权限访问");
//            request.getServletContext().getRequestDispatcher("/error").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }else{
            chain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {

    }
}
