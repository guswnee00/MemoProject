package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemoController {

  private final Map<Long,  Memo> memoList = new HashMap<>();

  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
    Memo memo = new Memo(requestDto);

    Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) : null;
    memo.setId(maxId);

    memoList.put(memo.getId(), memo);

    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;
  }

  @GetMapping("/memos")
  public List<MemoResponseDto> getMemos(){
    List<MemoResponseDto> memoResponseDtos = memoList.values().stream().map(MemoResponseDto::new).toList();
    return memoResponseDtos;
  }
}
