package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orederDetailId;
    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")
    private ItemEntity item;
    private Integer qty;
    private Double discount;
}
