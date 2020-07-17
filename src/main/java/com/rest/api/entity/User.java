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

@Builder //builder�� ��밡��
@Entity // jpa entity���� ǥ��
@Getter // getter �ڵ�����
@NoArgsConstructor // ���ھ��� ������ �ڵ�����
@AllArgsConstructor // ��ü ���� �ִ� ������ �ڵ�����
@Table(name = "user") // 'user' ���̺�� ���ε�
public class User {
	@Id // �⺻Ű
	@GeneratedValue(strategy = GenerationType.IDENTITY) // pk���������� db�� ���� : pk �ڵ�����
	private long msrl;
	@Column(nullable = false, unique = true, length = 30) // column ��� : not null, unique, ���̴� 30
	private String uid;
	@Column(nullable = false, length = 100)
	private String name;
}
