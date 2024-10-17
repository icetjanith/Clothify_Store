package service.custom.impl;

import dto.Item;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemRepository;
import service.custom.ItemService;
import util.DaoType;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
    private final ModelMapper modelMapper = new ModelMapper();
    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        itemRepository.getAll().forEach(itemEntity -> {
            items.add(modelMapper.map(itemEntity, Item.class));
        });
        return items;
    }

    @Override
    public boolean addItem(Item item) {
        return itemRepository.save(modelMapper.map(item, ItemEntity.class));
    }

    @Override
    public boolean deleteItemById(String text) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }
}
