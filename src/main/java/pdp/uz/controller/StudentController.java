package pdp.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pdp.uz.entity.StudentEntity;
import pdp.uz.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String listStudnet(Model model){
        List<StudentEntity> all = studentService.findAllStudents();
        model.addAttribute("students",all);
        return "student/list";
    }

    @PostMapping("/add")
    public String addStudent(StudentEntity student){
        studentService.addStudent(student);
        return "redirect:/student/list";
    }

    @PostMapping("/delete")
    public String deleteStudent(StudentEntity student){
        studentService.deleteStudent(student);
        return "redirect:/student/list";
    }

//    @PostMapping("/update")
//    public String updateStudent(StudentEntity student){
//        System.out.println(student);
//        studentService.updateStudent(student);
//        return "redirect:/student/list";
//    }

}
