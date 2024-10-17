package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    private String itemCode;
    private String category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private SupplierEntity supplier;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer qty;
}
