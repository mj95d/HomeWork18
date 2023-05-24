package com.example.home18.Repository;

import com.example.home18.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User WHERE LOWER(username) = LOWER(:username)")
    User findByUsername(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User findByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findByRole(@Param("role") String role);
    @Query("SELECT u FROM User u WHERE u.age >= :age ORDER BY u.age ASC")
    List<User> findByAgeGreaterThanEqualOrderByAgeAsc(@Param("age") Integer age);



    //الخيار الاخر لكتابة الاكواد بطريقة Spring Data JPA

//
//    User findByUsernameIgnoreCase(String username);
//    User findByEmailIgnoreCase(String email);
//    List<User> findByRole(String role);
//    List<User> findByAgeGreaterThanEqualOrderByAgeAsc(Integer age);
}
