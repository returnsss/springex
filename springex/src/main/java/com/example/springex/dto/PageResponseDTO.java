package com.example.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO <E>{
    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;
    // 끝 페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    // 제네릭을 이용해서 설계
    // 제네릭을 이용하는 이유는 나중에 다른 종류의 객체를 이용해서 PageResponseDTO를 구성할 수 있도록 하기 위해서
    // 예를 들어 게시판이나 회원 정보 등도 페이징 처리가 필요할수 있기 때문에 공통적인 처리를 위해서 제네릭으로 구성.
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;

        this.start = this.end - 9;

        int last = (int)(Math.ceil((total / (double)size)));

        this.end = end > last ? last : end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;
    }
}
