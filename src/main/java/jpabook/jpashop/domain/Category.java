package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
    @ManyToMany // 실무에서는 사용 X
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), // 내가 조인해야하는 컬럼은 이 컬럼이고,
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")) // 상대방이 조인해야하는 컬럼은 이 컬럼이야. 라고 지정해주는것.
    private List<Item> items = new ArrayList<>();
}
