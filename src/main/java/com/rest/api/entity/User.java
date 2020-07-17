package com.rest.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder //builder를 사용가능
@Entity // jpa entity임을 표시
@Getter // getter 자동생성
@NoArgsConstructor // 인자없는 생성자 자동생성
@AllArgsConstructor // 전체 인자 있는 생성자 자동생성
@Table(name = "user") // 'user' 테이블과 매핑됨
public class User {
	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // pk생성전략을 db에 위임 : pk 자동증가
	private long msrl;
	@Column(nullable = false, unique = true, length = 30) // column 명시 : not null, unique, 길이는 30
	private String uid;
	@Column(nullable = false, length = 100)
	private String name;
}
