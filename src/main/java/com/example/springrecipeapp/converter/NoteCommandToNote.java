package com.example.springrecipeapp.converter;

import com.example.springrecipeapp.command.NoteCommand;
import com.example.springrecipeapp.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if (source == null) {
            return null;
        }

        final Note note = new Note();
        note.setId(source.getId());
        note.setRecipeNotes(source.getRecipeNotes());
        return note;
    }
}
