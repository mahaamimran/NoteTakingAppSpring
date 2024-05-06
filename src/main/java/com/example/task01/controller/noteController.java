package com.example.task01.controller;

import com.example.task01.model.Note;
import com.example.task01.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public String showNotesPage(Model model) {
        // your code
        return "notes"; // assuming 'notes.html' is your Thymeleaf template
    }

    @PostMapping("/notes")
    public String addNote(Note note, Model model) {
        note.setCreatedAt(new Date());
        noteRepository.save(note);
        return "redirect:/";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable Long id, Note updatedNote, Model model) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        note.setUpdatedAt(new Date());
        noteRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/";
    }

}
