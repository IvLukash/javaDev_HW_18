package goit.edu.controller;

import goit.edu.model.dto.NoteCreateOrUpdateDto;
import goit.edu.model.dto.NoteDto;
import goit.edu.model.dto.NotesDtoContainer;
import goit.edu.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<NotesDtoContainer> getNotes() {
        NotesDtoContainer body = noteService.getNotes();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getById(@PathVariable Long id) {
        NoteDto note = noteService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(note);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> saveNote(@Valid @RequestBody NoteCreateOrUpdateDto noteToCreate) {
        NoteDto savedNote = noteService.saveNote(noteToCreate);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteDto> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteCreateOrUpdateDto noteToUpdate
    ) {
        NoteDto updatedNote = noteService.updateNote(id, noteToUpdate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedNote);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
