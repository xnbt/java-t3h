package t3h.hibernate1.dao;

import t3h.hibernate1.model.Group;

import java.util.List;

public interface GroupDAO {
    void insert(Group group);

    List<Group> getAll();
}
