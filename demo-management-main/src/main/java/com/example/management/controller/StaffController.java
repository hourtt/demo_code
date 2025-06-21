package com.example.management.controller;

import com.example.management.domain.Staff;
import com.example.management.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/list")
    public String staffList(Model model) {
        model.addAttribute("list", staffService.list());
        return "staffList";
    }

    private final int pageSize = 5;

    // paging 1 - number type
    @GetMapping("/list1")
    public String staffList1(Model model, @RequestParam(required = false, defaultValue = "0") int pageNo) {
        if (pageNo < 0) pageNo = 0;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Page<Staff> list = staffService.listType1(pageRequest);
        model.addAttribute("list", list);
        return "staffList1";
    }

    // paging 2 - prev / next type
    @GetMapping("/list2")
    public String staffList2(Model model, @RequestParam(required = false, defaultValue = "0") int pageNo) {
        if (pageNo < 0) pageNo = 0;
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Staff> list = staffService.listType2(pageRequest);
        model.addAttribute("list", list);
        return "staffList2";
    }

    @GetMapping("/add/form")
    public String staffAddForm() {
        return "staffAddForm";
    }

    @PostMapping("/add/form")
    public String staffAddForm(@Valid StaffRequestForm addRequest) {
        Staff staff = new Staff();
        staff.setLoginId(addRequest.getLoginId());
        staff.setPassword(addRequest.getPassword());
        staff.setName(addRequest.getName());
        staff.setBirthDate(addRequest.getBirthDate());
        staff.setEmail(addRequest.getEmail());
        staffService.addStaff(staff);
        return "redirect:/list";
    }

    @GetMapping("/edit/form/{id}")
    public String staffEditForm(@PathVariable Long id, Model model) {
        Staff staff = staffService.getStaff(id);
        model.addAttribute("staffId", id);
        model.addAttribute("staff", staff);
        return "staffEditForm";
    }

    @PostMapping("/edit/form/{id}")
    public String staffEditForm(@PathVariable Long id, StaffRequestForm editRequest) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setLoginId(editRequest.getLoginId());
        staff.setPassword(editRequest.getPassword());
        staff.setName(editRequest.getName());
        staff.setBirthDate(editRequest.getBirthDate());
        staff.setEmail(editRequest.getEmail());
        staffService.editStaff(staff);
        return "redirect:/list";
    }


}
