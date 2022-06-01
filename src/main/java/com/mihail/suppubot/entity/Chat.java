package com.mihail.suppubot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Long chatId;

    private ChatStatus status;
}
