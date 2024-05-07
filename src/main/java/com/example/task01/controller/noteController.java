package com.example.task01.controller;

import com.example.task01.model.Note;
import com.example.task01.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/notes")
    public String showNotesPage(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        logger.info("Retrieved {} notes from the database", notes.size());
        return "notes";
    }

    @PostMapping("/notes")
    public String addNote(Note note, Model model) {
        note.setCreatedAt(new Date());
        noteRepository.save(note);
        logger.info("Added a new note: {}", note);
        return "redirect:/notes";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable Long id, Note updatedNote, Model model) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        note.setUpdatedAt(new Date());
        noteRepository.save(note);
        logger.info("Updated note with id {}: {}", id, note);
        return "redirect:/notes";
    }

    @PostMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        logger.info("Deleted note with id {}", id);
        return "redirect:/notes"; 
    }

}
