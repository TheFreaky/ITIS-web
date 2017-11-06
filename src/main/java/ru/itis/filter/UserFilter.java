package ru.itis.filter;

import com.google.common.collect.Sets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * 11.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserFilter implements Filter {
    Set<String> permitedWithoutSignin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        permitedWithoutSignin = Sets.newHashSet("", "signin", "signup");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String path = req.getRequestURI().replace(req.getContextPath() + "/", "");

        if (session.getAttribute("user") == null) {
            if (permitedWithoutSignin.contains(path)) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        } else {
            if (permitedWithoutSignin.contains(path)) {
                resp.sendRedirect(req.getContextPath() + "/trainings");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
