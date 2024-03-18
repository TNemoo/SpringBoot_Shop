package com.svlshop.entity;

import com.svlshop.enums.Order_status;
import com.svlshop.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    private static final String SEQ_NAME = "order_seq";  //генерацию id выполняем в приложении, название переменной обычно соответствует названию таблицы

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)    // генерация id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)                 // генерация id
    private Long id;
    private String shopAddress;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetails> details;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    @Enumerated(EnumType.STRING)
    private Order_status status;
}
