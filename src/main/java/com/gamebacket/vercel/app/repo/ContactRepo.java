package com.gamebacket.vercel.app.repo;

import com.gamebacket.vercel.app.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {

}
