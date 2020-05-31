package com.jpabook.jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.*;

import com.jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem
{
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    // == 비즈니스 로직
    public void cancel() {
        getItem().addStock(this.count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
