package jpaboook.jpashop.domain.item;

import jpaboook.jpashop.domain.Category;
import jpaboook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==//
    
    // 객체 지향적으로 설계 시 데이터가 있는 부분에 로직이 있는 것이 좋다, setter을 사용하기보다 이처럼 별도의 메소드를 만들어 사용하기
    
    /**
     * stock 증가
     */
    public void addStock(int stock){
        this.stockQuantity += stock;
    }

    /**
     * stock 감소
     */
    public void removeStock(int stock){
        int resultStock = this.stockQuantity - stock;
        if (resultStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= stock;
    }













}
