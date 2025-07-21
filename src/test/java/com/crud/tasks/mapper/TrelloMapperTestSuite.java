package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;


    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "test list 3", true);
        TrelloListDto trelloListDto4 = new TrelloListDto("4", "test list 4", true);
        List<TrelloListDto> trelloListsDto1 = List.of(trelloListDto1, trelloListDto2, trelloListDto3);
        List<TrelloListDto> trelloListsDto2 = List.of(trelloListDto4);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "test board 1", trelloListsDto1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "test board 2", trelloListsDto2);
        List<TrelloBoardDto> trelloBoardDtoList = List.of(trelloBoardDto1, trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals("test board 1", trelloBoardList.getFirst().getName());
        assertEquals(3, trelloBoardList.getFirst().getLists().size());
        assertEquals("test list 1", trelloBoardList.getFirst().getLists().getFirst().getName());
        assertEquals("test list 2", trelloBoardList.getFirst().getLists().get(1).getName());
        assertEquals("3", trelloBoardList.getFirst().getLists().getLast().getId());
        assertEquals("test board 2", trelloBoardList.getLast().getName());
        assertEquals(1, trelloBoardList.getLast().getLists().size());
        assertEquals("test list 4", trelloBoardList.getLast().getLists().getFirst().getName());
    }


    @Test
    void mapToBoardsDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", false);
        TrelloList trelloList3 = new TrelloList("3", "test list 3", true);
        TrelloList trelloList4 = new TrelloList("4", "test list 4", true);
        List<TrelloList> trelloLists1 = List.of(trelloList1, trelloList2, trelloList3);
        List<TrelloList> trelloLists2 = List.of(trelloList4);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "test board 1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test board 2", trelloLists2);
        List<TrelloBoard> trelloBoardList = List.of(trelloBoard1, trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(2, trelloBoardDtoList.size());
        assertEquals("test board 1", trelloBoardDtoList.getFirst().getName());
        assertEquals(3, trelloBoardDtoList.getFirst().getLists().size());
        assertEquals("test list 1", trelloBoardDtoList.getFirst().getLists().getFirst().getName());
        assertEquals("test list 2", trelloBoardDtoList.getFirst().getLists().get(1).getName());
        assertEquals("3", trelloBoardDtoList.getFirst().getLists().getLast().getId());
        assertEquals("test board 2", trelloBoardDtoList.getLast().getName());
        assertEquals(1, trelloBoardDtoList.getLast().getLists().size());
        assertEquals("test list 4", trelloBoardDtoList.getLast().getLists().getFirst().getName());
    }


    @Test
    void mapToListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "test list 1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "test list 2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "test list 3", false);
        List<TrelloListDto> trelloListDtoList = List.of(trelloListDto1, trelloListDto2, trelloListDto3);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(3, trelloLists.size());
        assertEquals("test list 1", trelloLists.getFirst().getName());
        assertTrue(trelloLists.get(1).isClosed());
        assertEquals("3", trelloLists.getLast().getId());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "test list 1", false);
        TrelloList trelloList2 = new TrelloList("2", "test list 2", true);
        TrelloList trelloList3 = new TrelloList("3", "test list 3", false);
        List<TrelloList> trelloLists = List.of(trelloList1, trelloList2, trelloList3);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(3, trelloListsDto.size());
        assertEquals("test list 1", trelloListsDto.getFirst().getName());
        assertTrue(trelloListsDto.get(1).isClosed());
        assertEquals("3", trelloListsDto.getLast().getId());
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card 1", "test", "top", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("card 1", trelloCardDto.getName());
        assertEquals("test", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card 1", "test", "top", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card 1", trelloCard.getName());
        assertEquals("test", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }


}
