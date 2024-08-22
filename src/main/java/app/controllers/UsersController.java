package app.controllers;

import app.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersDao usersDao;

    @Autowired
    public UsersController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDao.getUserById(id));
        return "userShow";
    }
}
