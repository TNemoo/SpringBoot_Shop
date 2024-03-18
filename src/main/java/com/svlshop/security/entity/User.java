package com.svlshop.security.entity;

import com.svlshop.entity.Bucket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "user_seq";  //генерацию id выполняем в приложении, название переменной обычно соответствует названию таблицы

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME) // генерация id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)              // генерация id
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    @Enumerated(EnumType.STRING) //позволяет безопасно добавлять новые значения перечисления или изменять порядок нашего перечисления.
    private Role role;
    @OneToOne(cascade = CascadeType.REMOVE) // удаление пользователя ведет к удалению корзины
    private Bucket bucket;
    private boolean active;
}
