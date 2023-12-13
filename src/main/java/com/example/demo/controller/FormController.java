//package com.example.demo.controller;
//
//import java.text.Normalizer.Form;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.service.FormService;
//
//@RestController
//@RequestMapping("/api")
//public class FormController {
//    
//    @Autowired
//    private FormService formService;
//    
//    @GetMapping("/forms")
//    public List<Form> getAllForms() {
//        return formService.getAllForms();
//    }
//    
//    @PostMapping("/forms")
//    public Form createForm(@RequestBody Form form) {
//        return formService.createForm(form);
//    }
//    
////    @PutMapping("/forms/{id}")
////    public Form updateForm(@PathVariable Long id, @RequestBody Form form) {
////        return formService.updateForm(id, form);
////    }
//    
//    @DeleteMapping("/forms/{id}")
//    public ResponseEntity<?> deleteForm(@PathVariable Long id) {
//        formService.deleteForm(id);
//        return ResponseEntity.ok().build();
//    }
//    
////    @PostMapping("/forms/{id}/approve")
////    public Form approveForm(@PathVariable Long id, @RequestBody Comment comment) {
////        return formService.approveForm(id, comment);
////    }
////    
////    @PostMapping("/forms/{id}/reject")
////    public Form rejectForm(@PathVariable Long id, @RequestBody Comment comment) {
////        return formService.rejectForm(id, comment);
////    }
//}
