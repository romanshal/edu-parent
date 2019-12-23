package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    User findById(long id);

    User findByLogin(String login);

    @Modifying
    @Transactional
    @Query(value = "insert into tbl_friends(user_id, friend_id) values (?1,?2)", nativeQuery = true)
    void subscription(@Param("userId")Long userId, @Param("friendId")Long friendId);

    @Modifying
    @Transactional
    @Query(value = "delete from tbl_friends where user_id=?1 and friend_id=?2", nativeQuery = true)
    void unsubscription(@Param("userId")Long userId, @Param("friendId")Long friendId);

    @Transactional
    @Query(value = "select user_id from tbl_friends where user_id=?1 and friend_id=?2", nativeQuery = true)
    Optional<Long> checkSubscription(@Param("userId")Long userId, @Param("friendId")Long friendId);
}
