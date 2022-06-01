package com.mihail.suppubot.repository;

import com.mihail.suppubot.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Integer> {

    Chat findByChatId(Long chatId);
}
