package app.controllers;

import app.dao.UsersDao;
import app.models.User;
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

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", usersDao.showUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDao.getUserById(id));
        return "show";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        usersDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDao.getUserById(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        usersDao.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersDao.deleteUser(id);
        return "redirect:/users";
    }



}
