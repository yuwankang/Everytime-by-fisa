package com.fisa.land.fisaland.market.entity;

import java.time.LocalDateTime;
<<<<<<< Updated upstream
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fisa.land.fisaland.common.entity.BaseTimeEntity;
import com.fisa.land.fisaland.common.entity.User;
=======

import com.fisa.land.fisaland.common.BaseTimeEntity;
import com.fisa.land.fisaland.common.User;
>>>>>>> Stashed changes

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
<<<<<<< Updated upstream
@EntityListeners(AuditingEntityListener.class)
=======
>>>>>>> Stashed changes
public class MarketReview extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    private String content;

    private double rate;
<<<<<<< Updated upstream
}
=======

}
>>>>>>> Stashed changes
