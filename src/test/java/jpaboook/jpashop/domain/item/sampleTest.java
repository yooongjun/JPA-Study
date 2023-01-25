package jpaboook.jpashop.domain.item;

import jpaboook.jpashop.domain.sample;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class sampleTest {

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 등록() throws Exception {
        //given
        sample sample1 = new sample();
        sample1.setSampleName("수정 전2");

        //when
        em.persist(sample1);
        em.flush();

        sample find = em.find(sample.class, 1l);

        if (find != null) {
            find.setSampleName("수정 후");
            
        }
//        jpaboook.jpashop.domain.sample find = em.find(jpaboook.jpashop.domain.sample.class, 1);
//        find.setSampleName("수정 후");
        
        //then
    }

    @Test
    @Rollback(value = false)
    public void 수정테스트() throws Exception {
        //given
        sample findSample = em.find(sample.class, 1l);

        if (findSample == null) {
            System.out.println("null");
            return;
        }

        //when
        findSample.setSampleName("수정");

        //then

    }



}