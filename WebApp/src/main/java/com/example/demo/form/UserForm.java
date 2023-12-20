package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForm {
	private Integer userId;
	
	@Email(message = "正しい形式で入力してください。")
	@NotBlank(message = "入力がありません。")
	private String email;
	
	@NotBlank(message="パスワードを入力してください。")
	private String password;
}
