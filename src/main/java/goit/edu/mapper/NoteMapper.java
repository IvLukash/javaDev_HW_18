package goit.edu.mapper;

import goit.edu.model.dto.NoteCreateOrUpdateDto;
import goit.edu.model.dto.NoteDto;
import goit.edu.model.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteCreateOrUpdateDto createDto);
}
