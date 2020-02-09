package com.sample.maven.dto;

import lombok.Data;

@Data
public class PagingDto {
	
	public PagingDto(int pageNo, int totalCount, int pageSize, int blockSize){
		this.pageNo = pageNo;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.startRowNum = (pageNo - 1) * pageSize;
		makePaging();
	}
	private int pageSize;
	private int firstPageNo;
	private int prevPageNo;
	private int startPageNo;
	private int pageNo;
	private int endPageNo;
	private int nextPageNo;
	private int finalPageNo;
	private int totalCount;
	private int blockSize;
	private int startRowNum;
	
	private void makePaging() {
		
		if(this.pageNo == 0)
			this.setPageNo(1);
		
		if (this.totalCount == 0)
			return; // 게시글 전체 수가 없는 경우
		
		int finalPage = (totalCount + (pageSize - 1)) / pageSize; // 마지막 페이지
		if (this.pageNo > finalPage)
			this.setPageNo(finalPage); // 기본 값 설정

		if (this.pageNo < 0 || this.pageNo > finalPage)
			this.pageNo = 1; // 현재 페이지 유효성 체크

		boolean isNowFirst = pageNo == 1 ? true : false; // 시작 페이지 (전체)
		boolean isNowFinal = pageNo == finalPage ? true : false; // 마지막 페이지 (전체)

		int startPage = ((pageNo - 1) / blockSize) * blockSize + 1; // 시작 페이지 (페이징 네비 기준)
		int endPage = startPage + blockSize - 1; // 끝 페이지 (페이징 네비 기준)

		if (endPage > finalPage) { // [마지막 페이지 (페이징 네비 기준) > 마지막 페이지]보다 큰 경우
			endPage = finalPage;
		}

		this.setFirstPageNo(1); // 첫 번째 페이지 번호

		if (isNowFirst) {
			this.setPrevPageNo(1); // 이전 페이지 번호
		} else {
			this.setPrevPageNo(((pageNo - 1) < 1 ? 1 : (pageNo - 1))); // 이전 페이지 번호
		}

		this.setStartPageNo(startPage); // 시작 페이지 (페이징 네비 기준)
		this.setEndPageNo(endPage); // 끝 페이지 (페이징 네비 기준)

		if (isNowFinal) {
			this.setNextPageNo(finalPage); // 다음 페이지 번호
		} else {
			this.setNextPageNo(((pageNo + 1) > finalPage ? finalPage : (pageNo + 1))); // 다음 페이지 번호
		}
		this.setFinalPageNo(finalPage);

	}
	
}