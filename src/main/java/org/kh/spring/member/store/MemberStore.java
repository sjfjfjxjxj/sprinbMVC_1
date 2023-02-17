package org.kh.spring.member.store;

import java.util.List;

import org.kh.spring.member.domain.Member;

public interface MemberStore {

	/**
	 * 회원 전체 조회 Store
	 * @return
	 */
	public List<Member> selectMembers();
	
	/**
	 * 회원 상세 조회 Store
	 * @param memberId
	 * @return
	 */
	public Member selectOneById(String memberId);
	
	/**
	 * 회원 로그인 Store
	 * @param member
	 * @return Member
	 */
	public Member checkMemberLogin(Member member);


}
