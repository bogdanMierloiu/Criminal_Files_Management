package com.bogdanmierloiu.criminal_files_management.controller.web;

import com.bogdanmierloiu.criminal_files_management.dto.AuthorRequest;
import com.bogdanmierloiu.criminal_files_management.dto.AuthorResponse;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeRequest;
import com.bogdanmierloiu.criminal_files_management.dto.CrimeTypeResponse;
import com.bogdanmierloiu.criminal_files_management.service.CrimeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/crime-type")
public class CrimeTypeWebController {

    private final CrimeTypeService crimeTypeService;

    @GetMapping("/goToAddCrimeType")
    public String goToAddCrimeType() {
        return "crime-type-form";
    }

    @PostMapping("/add-crime-type")
    public String addCrimeType(@ModelAttribute CrimeTypeRequest request, Model model) {
        crimeTypeService.add(request);
        model.addAttribute("crimeTypeList", crimeTypeService.getAll());
        return "crime-types";
    }

    @GetMapping("/deleteById/{id}")
    public String delete(@PathVariable Long id) {
        crimeTypeService.delete(id);
        return "redirect:/goToCrimeTypes";
    }

    @GetMapping("/updateById/{id}")
    public String goToUpdateForm(@PathVariable Long id, Model model) {
        CrimeTypeResponse crimeType = crimeTypeService.findById(id);
        model.addAttribute("crimeTypeSelected", crimeType);
        return "crime-type-form-update";
    }

    @PostMapping("/update-crime-type")
    public String updateAuthor(@ModelAttribute CrimeTypeResponse request, Model model) {
        CrimeTypeRequest crimeTypeToUpdate = new CrimeTypeRequest();
        BeanUtils.copyProperties(request, crimeTypeToUpdate);
        crimeTypeService.update(crimeTypeToUpdate);
        model.addAttribute("crimeTypeList", crimeTypeService.getAll());
        return "crime-types";
    }
}
