package com.example.asm2.servlet;

import com.example.asm2.entity.SanPham;
import com.example.asm2.repository.SanPhamRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;

@WebServlet(name = "ServletSanPham",
        value = {
                "/san-pham/hien-thi",
                "/san-pham/detail",
                "/san-pham/add",
                "/nhan-vien/hien-thi"
})
public class ServletSanPham extends HttpServlet {
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/san-pham/hien-thi")){
            request.setAttribute("listProduct", sanPhamRepository.getAll());
            request.setAttribute("listCombobox", sanPhamRepository.getCombobox());
            request.getRequestDispatcher("/view/hien-thi.jsp").forward(request, response);
        } else if (uri.contains("/nhan-vien/hien-thi")) {
            request.getRequestDispatcher("/view/nhan-vien.jsp").forward(request, response);
        } else {
            Integer id = Integer.parseInt(request.getParameter("id"));
            SanPham sp = sanPhamRepository.getOne(id);
            request.setAttribute("sp", sp);
            request.setAttribute("listProduct", sanPhamRepository.getAll());
            request.setAttribute("listCombobox", sanPhamRepository.getCombobox());
            request.getRequestDispatcher("/view/hien-thi.jsp").forward(request, response);        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String moTa = request.getParameter("mota");
        boolean valid = true;
        if (ma.trim().isEmpty() || ma.equals("")) {
            request.setAttribute("message", "Chưa nhập id");
            valid = false;
        }
        if (ten.trim().isEmpty() || ten.equals("")) {
            request.setAttribute("message1", "Chưa nhập id");
            valid = false;
        }
        if (moTa.trim().isEmpty() || moTa.equals("")) {
            request.setAttribute("message2", "Chưa nhập id");
            valid = false;
        }
        if (valid == false) {
            request.setAttribute("listProduct", sanPhamRepository.getAll());
            request.setAttribute("listCombobox", sanPhamRepository.getCombobox());
            request.getRequestDispatcher("/view/hien-thi.jsp").forward(request, response);
        } else {
            try {
                SanPham sanPham = new SanPham();
                BeanUtils.populate(sanPham, request.getParameterMap());
                sanPhamRepository.add(sanPham);
                response.sendRedirect("/san-pham/hien-thi");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
