package t3h.hibernate1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import t3h.hibernate1.dao.GroupDAO;
import t3h.hibernate1.model.Group;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    GroupDAO groupDAO;

    @GetMapping("/group")
    public ResponseEntity<List<Group>> getGroups() {
        return ResponseEntity.ok().body(groupDAO.getAll());
    }
}
