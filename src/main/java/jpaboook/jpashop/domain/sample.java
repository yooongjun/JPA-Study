package jpaboook.jpashop.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class sample {


    @Id
    @GeneratedValue
    @Column(name = "sample_id")
    private Long id;

    private String sampleName;
}
