package jpaboook.jpashop.repository;

import jpaboook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        // 이건 신규 등록
        if (item.getId() == null) {
            em.persist(item);
        }
        // 이건 이미 DB에 등록되어있던것 (update와 유사함)
        else {
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
    }

}
