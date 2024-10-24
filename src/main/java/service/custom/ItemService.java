package service.custom;

import dto.Item;
import javafx.collections.ObservableList;
import service.SuperBo;

public interface ItemService extends SuperBo {
    ObservableList<Item> getAll();

    boolean addItem(Item item);

    boolean deleteItemById(String text);

    boolean updateItem(Item item);

    Item searchItem(String text);
}
