package com.blck.sosyalag.sosyalag.repo;

import com.blck.sosyalag.sosyalag.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat,Long> {

    List<Chat> findBySenderAndReceiver(String sen,String rec);
}
