package goit.edu.controller;

import goit.edu.dto.NoteCreateOrUpdateDto;
import goit.edu.dto.NoteDto;
import goit.edu.entity.Note;
import goit.edu.mapper.NoteMapper;
import goit.edu.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NoteController {
    private final NoteService noteService;
    private final NoteMapper mapper;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getNotes() {
        List<NoteDto> notes = noteService.getNotes().stream()
                .map(mapper::toDto)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getById(@PathVariable Long id) {
        NoteDto note = mapper.toDto(noteService.getById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(note);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> saveNote(@Valid @RequestBody NoteCreateOrUpdateDto noteToCreate) {
        Note note = mapper.toEntity(noteToCreate);
        NoteDto savedNote = mapper.toDto(noteService.saveNote(note));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteDto> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteCreateOrUpdateDto noteToUpdate
    ) {
        Note note = mapper.toEntity(noteToUpdate);
        NoteDto updatedNote = mapper.toDto(noteService.updateNote(id, note));
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
