package jpaboook.jpashop.service;


import jpaboook.jpashop.domain.Delivery;
import jpaboook.jpashop.domain.Member;
import jpaboook.jpashop.domain.Order;
import jpaboook.jpashop.domain.OrderItem;
import jpaboook.jpashop.domain.item.Item;
import jpaboook.jpashop.repository.ItemRepository;
import jpaboook.jpashop.repository.MemberRepository;
import jpaboook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        // 실제로는 배송지 정보 따로 입력할텐데 예제니까 회원 정보에 있는 주소를 사용한다하심
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        // 주문 저장 cascade 옵션덕분에 orderItem, delivery도 함께 persist가 된다.
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long id){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(id);
        
        // 주문 취소
        order.cancel();
    }


    // 검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
//


}
