package ru.itis.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 11.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null ||
                req.getRequestURI().contains("sign")) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/signin");
        }
    }

    @Override
    public void destroy() {

    }
}
