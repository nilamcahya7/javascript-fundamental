package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.model.Region;
import com.example.demo.model.dto.DepartementDTO;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.RegionService;
import com.example.demo.handler.Utils;

@RestController
@RequestMapping("api")
public class DepartmentRestController {
  @Autowired
  private DepartmentService departmentService;

  @Autowired
  private RegionService regionService;

  @GetMapping("departments")
  public List<Department> get(){
    List<Department> departments = departmentService.get();
    return departments;
  }

  @GetMapping("department/{id}")
  public Department getById(@PathVariable Integer id) {
    Department department = departmentService.get(id);
    return department;
  }

  @PostMapping("department/save")
  public Boolean save(@RequestBody DepartementDTO departementDTO){
    Region region = regionService.get(departementDTO.getRegion_id());
    Department department = new Department(departementDTO.getId(), departementDTO.getName(), region);
    return departmentService.save(department);
  }

  @PostMapping("department/delete/{id}")
  public Boolean delete(@PathVariable Integer id){
    return departmentService.delete(id);
  }
}
