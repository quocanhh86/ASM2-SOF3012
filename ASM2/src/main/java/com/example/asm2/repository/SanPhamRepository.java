package com.example.asm2.repository;

import com.example.asm2.entity.LoaiSp;
import com.example.asm2.entity.SanPham;
import com.example.asm2.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SanPhamRepository {
    private Session s;
    public SanPhamRepository(){
        s = HibernateUtil.getFACTORY().openSession();
    }

    public List<SanPham> getAll(){
        return s.createQuery("FROM SanPham ").list();
    }

    public List<LoaiSp> getCombobox(){
        return s.createQuery("FROM LoaiSp ").list();
    }

    public SanPham getOne(Integer id){
        return s.find(SanPham.class, id);
    }

    public void add(SanPham sp){
        try{
            s.getTransaction().begin();
            s.save(sp);
            s.getTransaction().commit();
        } catch (Exception e){
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
