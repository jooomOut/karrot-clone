package com.karrot.demo.integrity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karrot.demo.domain.item.Item;
import com.karrot.demo.domain.item.ItemRepository;
import com.karrot.demo.web.dto.comment.AddCommentDto;
import com.karrot.demo.web.dto.item.ItemUploadDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemApiTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ItemRepository itemRepository;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("Item Fetch")
    @WithMockUser
    void getItemsByFetch() throws Exception {
        List<Item> items = itemRepository.findAll();
    }
    @Test
    @DisplayName("Item Fetch with Batch Size")
    @WithMockUser
    void getItems() throws Exception {
        List<Item> items = itemRepository.findAllItemsByFetch();
    }

    @Test
    @DisplayName("아이템 업로드")
    @WithMockUser
    void addComment_Fail() throws Exception {
        for (int i = 100 ; i < 100000 ; i ++){
            uploadItem_bulk(i);
        }
    }

    private void uploadItem_bulk(int idx) throws Exception {
        String strIdx = Integer.toString(idx);
        ItemUploadDto dto = ItemUploadDto.builder()
                .title(strIdx)
                .mainText("Test! " + strIdx)
                .price(10000L)
                .category("DIGITAL_DEVICE")
                .build();

        mockMvc.perform(post("/api/items")
                        .content(objectMapper.writeValueAsString(dto))
                )
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}
