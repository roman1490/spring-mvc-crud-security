package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("admin/adminPage")
    public String getIndex(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminPage";
    }

    @GetMapping("admin/reg")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoleList());
        return "reg";
    }

    @PostMapping("admin/reg")
    public String regUser(@ModelAttribute User user,
                          @RequestParam(value = "ROLE_ADMIN", required = false) Integer roleAdminId,
                          @RequestParam(value = "ROLE_USER", required = false) Integer roleUserId) {
        if (roleAdminId != null) {
            Role role = roleService.getRoleById(roleAdminId);
            user.getRoles().add(role);
        }

        if (roleUserId != null) {
            Role role = roleService.getRoleById(roleUserId);
            user.getRoles().add(role);
        }

        userService.addUser(user);

        return "redirect:/admin/adminPage";
    }

    @GetMapping("admin/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("admin/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id,
                         @RequestParam(value = "ROLE_ADMIN", required = false) Integer roleAdminId,
                         @RequestParam(value = "ROLE_USER", required = false) Integer roleUserId) {
        user.getRoles().clear();
        if (roleAdminId != null) {
            Role role = roleService.getRoleById(roleAdminId);
            user.getRoles().add(role);
        }

        if (roleUserId != null) {
            Role role = roleService.getRoleById(roleUserId);
            user.getRoles().add(role);
        }
        userService.updateUser(id, user);
        return "redirect:/admin/adminPage";
    }

    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("user")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getUserByEmail(auth.getName());
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping("admin/user/{id}")
    public String getPersonalPage(Model model, @PathVariable("id") int id) {

        model.addAttribute("roles", roleService.getRoleList());
        model.addAttribute("user", userService.getUserById(id));
        return "userPageFromAdminPanel";
    }
}
