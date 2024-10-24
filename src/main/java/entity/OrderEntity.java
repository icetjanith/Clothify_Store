package entity;

import dto.OrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName ="id")
    private CustomerEntity customerId;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName ="id")
    private UserEntity employeeId;
    @OneToMany(mappedBy = "order")
    private List<OrderDetailsEntity> orderDetails;
}
