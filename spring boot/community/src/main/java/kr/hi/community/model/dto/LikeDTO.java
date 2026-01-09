package kr.hi.community.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDTO {
	int postNum; //추천 게시글
	int state;	 //추천(1)/비추천(
	String id;	 //추천한 사용자 id

	public LikeDTO(int postNum, String username) {
		this.postNum = postNum;
		this.id = username;
	}
}
