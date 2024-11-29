package com.example.asm2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "FilterASM1",
        value = {
                "/san-pham/*",
                "/nhan-vien/*"
        })
public class FilterASM1 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request; // convert kiểu
        HttpServletResponse resp = (HttpServletResponse) response;// convert kiểu

        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (userName != null) {
            if (role.equals("admin")) { // role quản lý
                chain.doFilter(req, resp); // cho phép truy cập thẳng vào trang
            } else if (role.equals("student")) { // với role nhân viên
                String uri = req.getRequestURI(); // lấy ra uri của nhân viên
                if (uri.contains("/nhan-vien/hien-thi")) { // nếu uri là nhân viên
                    chain.doFilter(req, resp); // mới cho phép truy cập
                } else {
                    req.getRequestDispatcher("/test-filter/403.jsp").forward(req, resp);
                }
            }
        } else {
            resp.sendRedirect("/test-filter/login");
        }
    }
}

