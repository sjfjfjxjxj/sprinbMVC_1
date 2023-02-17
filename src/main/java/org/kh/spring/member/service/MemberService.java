package org.kh.spring.member.service;

import java.util.List;

import org.kh.spring.member.domain.Member;

public interface MemberService {

	/**
	 * 회뭔 목록 조회 Service
	 * @return 
	 */
	public List<Member> selectMembers();
	
	/**
	 * 회원로그인 service
	 * @param member
	 * @return Member
	 */
	public Member checkMemberLogin(Member member);

	/**
	 * 회원 상세 조회 Service
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(String memberId);

}
