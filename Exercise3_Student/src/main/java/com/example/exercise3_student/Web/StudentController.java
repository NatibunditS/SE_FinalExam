package com.example.exercise3_student.Web;
import com.example.exercise3_student.Entities.Student;
import com.example.exercise3_student.Repositories.StudentRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    //add a messagebox to signify if the user successfully added or edited. We set a flag to this.
    static int num = 0;

    @GetMapping(path = "/")
    public String students2(Model model, ModelMap mm,
                            @RequestParam(name="keyword",defaultValue = "") String
                                    keyword,HttpSession session){
        List<Student> students;
        if (keyword.isEmpty()) {
            students = studentRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            long key = Long.parseLong(keyword);
            students = studentRepository.findStudentById(key);
        }
        model.addAttribute("listStudents", students);
        return "students";
    }

    //Find all will show the entire student lists
    //Search function
    @GetMapping(path="/index")
    public String students1(Model model, @RequestParam(name="keyword",defaultValue = "")
    String keyword){
        List<Student> students;
        if (keyword.isEmpty()) {
            students = studentRepository.findAll();
        } else {
            long key = Long.parseLong(keyword);
            students = studentRepository.findStudentById(key);
        }
        model.addAttribute("listStudents", students);
        return "students";
    }

    //Delete Button
    @GetMapping("/delete")
    public String delete(Long id){
        studentRepository.deleteById(id);
        return "redirect:/index";
    }

    //Form add students Page
    @GetMapping("/formStudent")
    public String formStudents(Model model){
        model.addAttribute("student", new Student());
        return "formStudent";
    }

    //it will call the save method once the user submits the new information.
    @PostMapping(path = "/save")
    public String save(
            Model model,
            Student student,
            BindingResult bindingResult,
            ModelMap mm,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formStudent";
        } else {
            //If it's not error, user inputs will be saved and set flag "a":add or "e":edit
            studentRepository.save(student);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }

    @GetMapping("/editStudent")
    public String editStudents(Model model, Long id, HttpSession session) {
        num = 2;
        session.setAttribute("info", 0);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) throw new RuntimeException("Student does not exist");
        model.addAttribute("student", student);
        return "editStudent";
    }




}
