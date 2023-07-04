package com.shravani.TimeSheetApp.repository;
import com.shravani.TimeSheetApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);

    public User findByName(String name);
}
