package goit.edu.service;

import goit.edu.exception.ResourceNotFoundException;
import goit.edu.dto.NoteCreateOrUpdateDto;
import goit.edu.dto.NoteDto;
import goit.edu.entity.Note;
import goit.edu.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }


    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
    }

    public Note saveNote(Note noteToCreate) {
        return noteRepository.save(noteToCreate);
    }

    public Note updateNote(Long id, Note noteToUpdate) {
        Note updatedNote = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
        updatedNote.setTitle(noteToUpdate.getTitle());
        updatedNote.setContent(noteToUpdate.getContent());
        return noteRepository.save(updatedNote);
    }

    public void deleteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
        noteRepository.delete(note);
    }
}
