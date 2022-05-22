package com.example.springrecipeapp.converter;

import com.example.springrecipeapp.command.NoteCommand;
import com.example.springrecipeapp.model.Note;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteToNoteCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "Notes";
    NoteToNoteCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NoteToNoteCommand();
    }

    @Test
    public void convert() throws Exception {
        //given
        Note note = new Note();
        note.setId(ID_VALUE);
        note.setRecipeNotes(RECIPE_NOTES);

        //when
        NoteCommand notesCommand = converter.convert(note);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Note()));
    }
}