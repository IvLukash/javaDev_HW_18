package goit.edu.mapper;

import goit.edu.dto.NoteCreateOrUpdateDto;
import goit.edu.dto.NoteDto;
import goit.edu.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteCreateOrUpdateDto createDto);
}
