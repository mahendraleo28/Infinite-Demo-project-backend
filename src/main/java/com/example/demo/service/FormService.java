//package com.example.demo.service;
//
//import java.text.Normalizer.Form;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.poi.sl.usermodel.Comment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.Repository.FormRepository;
//
//@Service
//public class FormService {
//    
//    @Autowired
//    private FormRepository formRepository;
//    
//    public List<Form> getAllForms() {
//        return formRepository.findAll();
//    }
//    
//    public Form createForm(Form form) {
//        return formRepository.save(form);
//    }
//    
////    public Form updateForm(Long id, Form form) {
////        Optional<Form> existingForm = formRepository.findById(id);
////        if (existingForm.isPresent()) {
////            Form updatedForm = existingForm.get();
////            updatedForm.setTitle(form.getTitle());
////            updatedForm.setDescription(form.getDescription());
////            updatedForm.setStatus(form.getStatus());
////            return formRepository.save(updatedForm);
////        }
////        return null;
////    }
//    
//    public void deleteForm(Long id) {
//        formRepository.deleteById(id);
//    }
//    
////    public Form approveForm(Long id, Comment comment) {
////        Optional<Form> existingForm = formRepository.findById(id);
////        if (existingForm.isPresent()) {
////            Form form = existingForm.get();
////            form.setStatus(FormStatus.APPROVED);
////            form.setComment(comment);
////            return formRepository.save(form);
////        }
////        return null;
////    }
////    
////    public Form rejectForm(Long id, Comment comment) {
////        Optional<Form> existingForm = formRepository.findById(id);
////        if (existingForm.isPresent()) {
////            Form form = existingForm.get();
////            form.setStatus(FormStatus.REJECTED);
////            form.setComment(comment);
////            return formRepository.save(form);
////        }
////        return null;
////    }
//}
