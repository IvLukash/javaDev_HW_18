package goit.edu.service;

import goit.edu.exception.ResourceNotFoundException;
import goit.edu.mapper.NoteMapper;
import goit.edu.model.dto.NoteCreateOrUpdateDto;
import goit.edu.model.dto.NoteDto;
import goit.edu.model.dto.NotesDtoContainer;
import goit.edu.model.entity.Note;
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
    private final NoteMapper mapper;

    public NotesDtoContainer getNotes() {
        List<NoteDto> notes = noteRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
        return new NotesDtoContainer(notes);
    }


    public NoteDto getById(Long id) {
        return noteRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
    }

    public NoteDto saveNote(NoteCreateOrUpdateDto noteToCreate) {
        Note createdNote = new Note(
                noteToCreate.title(),
                noteToCreate.content()
        );
        return mapper.toDto(noteRepository.save(createdNote));
    }

    public NoteDto updateNote(Long id, NoteCreateOrUpdateDto noteToUpdate) {
        Note updatedNote = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
        updatedNote.setTitle(noteToUpdate.title());
        updatedNote.setContent(noteToUpdate.content());
        return mapper.toDto(noteRepository.save(updatedNote));
    }

    public void deleteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note with id " + id + " not found"
                ));
        noteRepository.delete(note);
    }
}
